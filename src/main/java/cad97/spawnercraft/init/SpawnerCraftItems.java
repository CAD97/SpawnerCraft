package cad97.spawnercraft.init;

import cad97.spawnercraft.items.ItemMobEssence;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpawnerCraftItems {

    public static final ItemMobEssence mobEssence = new ItemMobEssence();

    public static void registerItems() {
        // items
        GameRegistry.register(mobEssence);
        // block items
        GameRegistry.register(new ItemBlock(SpawnerCraftBlocks.mobCage), SpawnerCraftBlocks.mobCage.getRegistryName());
        LogHelper.logInfo("Items initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(mobEssence,
                0, new ModelResourceLocation(mobEssence.getRegistryName(), "inventory"));
        LogHelper.logInfo("Item models initialized.");
    }
}
