package cad97.spawnercraft.items;

import cad97.spawnercraft.creativetabs.SpawnerCraftTabs;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

import static cad97.spawnercraft.SpawnerCraft.MOD_ID;

abstract class SpawnerCraftItem extends Item {
    SpawnerCraftItem() {
        super();
        setCreativeTab(SpawnerCraftTabs.tab);
    }

    @Override
    @Nonnull
    public Item setUnlocalizedName(@Nonnull String name) {
        return super.setUnlocalizedName(MOD_ID + "." + name);
    }
}
