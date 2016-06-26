package cad97.spawnercraft.block;

import cad97.spawnercraft.creativetabs.SpawnerCraftTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import javax.annotation.Nonnull;

import static cad97.spawnercraft.SpawnerCraft.MOD_ID;

abstract class SpawnerCraftBlock extends Block {
    SpawnerCraftBlock(Material material) {
        super(material);
        setCreativeTab(SpawnerCraftTabs.tab);
    }

    @Override
    @Nonnull
    public Block setUnlocalizedName(@Nonnull String name) {
        return super.setUnlocalizedName(MOD_ID + "." + name);
    }
}
