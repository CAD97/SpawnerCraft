package cad97.spawnercraft.items;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.init.SpawnerCraftBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
    // Copied from ItemMonsterPlacer
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return EnumActionResult.SUCCESS;
        } else if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState state = world.getBlockState(pos);

            // Create mob spawner when clicking on empty mob spawner
            if (state.getBlock() == SpawnerCraftBlocks.mobCage) {
                world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState());
            }

            return Items.SPAWN_EGG.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
        }
    }
}
