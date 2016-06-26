package cad97.spawnercraft.items;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.creativetabs.SpawnerCraftTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import javax.annotation.Nonnull;

import static cad97.spawnercraft.SpawnerCraft.MOD_ID;

public class ItemMobRod extends ItemSword /*, SpawnerCraftItem */ {
    public ItemMobRod() {
        super(ToolMaterial.IRON);
        setCreativeTab(SpawnerCraftTabs.tab);
        setUnlocalizedName("mobRod");
        setRegistryName(SpawnerCraft.MOD_ID, "mobRod");
    }

    @Override
    @Nonnull
    public Item setUnlocalizedName(@Nonnull String name) {
        return super.setUnlocalizedName(MOD_ID + "." + name);
    }
}
