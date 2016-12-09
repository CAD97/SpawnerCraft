package cad97.spawnercraft.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SpawnerCraftTabs {
    public static final CreativeTabs tab = new CreativeTabs("spawnercraft.tab") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Item.getItemFromBlock(Blocks.MOB_SPAWNER));
        }
    };
}
