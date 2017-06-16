package cad97.spawnercraft.items;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.init.SpawnerCraftBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class ItemMobSpirit extends ItemMobSoul {
    public ItemMobSpirit() {
        super();
        setUnlocalizedName("mob_spirit");
        setRegistryName(SpawnerCraft.MOD_ID, "mob_spirit");
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos,
                                      EnumHand hand, EnumFacing facing,
                                      float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        ResourceLocation id = ItemMonsterPlacer.getNamedIdFrom(stack);
        if (id == null || !EntityList.ENTITY_EGGS.containsKey(id)) {
            return EnumActionResult.FAIL;
        } else if (world.isRemote) {
            return EnumActionResult.SUCCESS;
        } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            // Create mob spawner when clicking on empty mob spawner
            if (block == SpawnerCraftBlocks.MOB_CAGE) {
                world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState());
                TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(pos);
                Objects.requireNonNull(spawner);

                MobSpawnerBaseLogic logic = spawner.getSpawnerBaseLogic();
                logic.setEntityId(ItemMonsterPlacer.getNamedIdFrom(stack));
                spawner.markDirty();
                world.notifyBlockUpdate(pos, state, state, 3);

                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
                return EnumActionResult.SUCCESS;
            }

            if (block == Blocks.MOB_SPAWNER) {
                // Overwriting spawners is dangerous;
                // it could be pulling from a rotating set.
                // Fail fast is the most consistent behavior.
                return EnumActionResult.FAIL;
            }

            // Spawn creature copied directly from ItemMonsterPlacer::onItemUse

            pos = pos.offset(facing);
            double d0 = this.getYOffset(world, pos);
            Entity entity = ItemMonsterPlacer.spawnCreature(world, ItemMonsterPlacer.getNamedIdFrom(stack),
                    pos.getX() + 0.5, pos.getY() + d0, pos.getZ() + 0.5);

            if (entity != null) {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                    entity.setCustomNameTag(stack.getDisplayName());
                }

                ItemMonsterPlacer.applyItemEntityDataToEntity(world, player, stack, entity);

                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }

        return EnumActionResult.SUCCESS;
    }

    // Copied directly from ItemMonsterPlacer::getYOffset
    private double getYOffset(World world, BlockPos pos) {
        AxisAlignedBB aabb = (new AxisAlignedBB(pos)).expand(0.0D, -1.0D, 0.0D);
        List<AxisAlignedBB> list = world.getCollisionBoxes(null, aabb);

        if (list.isEmpty()) {
            return 0.0D;
        } else {
            double d0 = aabb.minY;

            for (AxisAlignedBB axisalignedbb1 : list) {
                d0 = Math.max(axisalignedbb1.maxY, d0);
            }

            return d0 - (double) pos.getY();
        }
    }
}
