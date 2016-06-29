package cad97.spawnercraft.items;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftMobAlias;
import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemMobSpirit extends ItemMobSoul {
    public ItemMobSpirit() {
        super();
        setUnlocalizedName("mobSpirit");
        setRegistryName(SpawnerCraft.MOD_ID, "mobSpirit");
    }

    @Nonnull
    @Override
    // A lot is taken from ItemMonsterPlacer
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {
        String id = ItemMonsterPlacer.getEntityIdFromItem(stack);
        if (id == null || !(EntityList.ENTITY_EGGS.containsKey(id) || SpawnerCraftMobAlias.customEggs.containsKey(id))) {
            return EnumActionResult.FAIL;
        } else if (world.isRemote) {
            return EnumActionResult.SUCCESS;
        } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState state = world.getBlockState(pos);

            // Create mob spawner when clicking on empty mob spawner
            if (state.getBlock() == SpawnerCraftBlocks.mobCage) {
                world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState());
                TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(pos);

                assert spawner != null;
                MobSpawnerBaseLogic mobspawnerbaselogic = spawner.getSpawnerBaseLogic();
                if (SpawnerCraftMobAlias.aliasedIDs.containsKey(id)) {
                    // It's hacky to write the spawner using NBT
                    // But it's Minecraft-y because it's similar to commands
                    // And it's more consistent than reflecting my way in
                    NBTTagCompound nbt = new NBTTagCompound();
                    NBTTagCompound spawnData = SpawnerCraftMobAlias.customNBT.get(id);
                    spawnData.setString("id", SpawnerCraftMobAlias.aliasedIDs.get(id));
                    nbt.setTag("SpawnData", spawnData);
                    mobspawnerbaselogic.readFromNBT(nbt);
                } else {
                    mobspawnerbaselogic.setEntityName(id);
                }
                spawner.markDirty();
                world.notifyBlockUpdate(pos, state, state, 3);

                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }

                return EnumActionResult.SUCCESS;
            }

            if (state.getBlock() == Blocks.MOB_SPAWNER) {
                // I'd like to change the entity in the spawner
                // IF AND ONLY IF the entity isn't already what the MobSpirit is
                // Unfortunately, the Spawner isn't telling :P
                return EnumActionResult.FAIL;
            }

            // Spawn creature copied directly from ItemMonsterPlacer::onItemUse

            pos = pos.offset(facing);
            double d0 = 0.0D;

            if (facing == EnumFacing.UP && state.getBlock() instanceof BlockFence) {
                d0 = 0.5D;
            }

            Entity entity = ItemMonsterPlacer.spawnCreature(world,
                    SpawnerCraftMobAlias.aliasedIDs.containsKey(id) ? SpawnerCraftMobAlias.aliasedIDs.get(id) : id,
                    (double) pos.getX() + 0.5D, (double) pos.getY() + d0, (double) pos.getZ() + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                    entity.setCustomNameTag(stack.getDisplayName());
                }

                ItemMonsterPlacer.applyItemEntityDataToEntity(world, player, stack, entity);

                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
            }

            return EnumActionResult.SUCCESS;
        }
    }
}
