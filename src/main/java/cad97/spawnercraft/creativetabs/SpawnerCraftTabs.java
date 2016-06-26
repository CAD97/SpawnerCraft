package cad97.spawnercraft.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class SpawnerCraftTabs {
    public static final CreativeTabs tab = new CreativeTabs("spawnercraft.tab") {
        @SuppressWarnings("NullableProblems")
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.MOB_SPAWNER);
        }
    };
}
