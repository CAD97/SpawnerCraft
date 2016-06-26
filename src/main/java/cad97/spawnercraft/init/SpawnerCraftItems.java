package cad97.spawnercraft.init;

import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpawnerCraftItems {

    public static void registerItems() {
        // items
        // block items
        GameRegistry.register(new ItemBlock(SpawnerCraftBlocks.mobCage), SpawnerCraftBlocks.mobCage.getRegistryName());
        LogHelper.logInfo("Items initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        LogHelper.logInfo("Item models initialized.");
    }
}
