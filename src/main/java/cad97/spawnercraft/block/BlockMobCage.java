package cad97.spawnercraft.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;

import static cad97.spawnercraft.SpawnerCraft.MOD_ID;

public class BlockMobCage extends SpawnerCraftBlock {
    public BlockMobCage() {
        super(Material.ROCK);

        blockHardness = 5.0F;
        blockSoundType = SoundType.METAL;
        setUnlocalizedName("mob_cage");
        setRegistryName(MOD_ID, "mob_cage");
        setHarvestLevel("pickaxe", Item.ToolMaterial.STONE.getHarvestLevel());
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
