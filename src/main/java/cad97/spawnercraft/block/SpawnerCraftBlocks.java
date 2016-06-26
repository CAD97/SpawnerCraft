package cad97.spawnercraft.block;

import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpawnerCraftBlocks {

    @SuppressWarnings("WeakerAccess")
    public static final Block mobCage = new BlockMobCage();

    public static void registerBlocks() {
        GameRegistry.register(mobCage);
        GameRegistry.register(new ItemBlock(mobCage), mobCage.getRegistryName());
        LogHelper.logInfo("Blocks initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        //noinspection ConstantConditions
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(mobCage),
                0, new ModelResourceLocation(mobCage.getRegistryName(), "inventory")
        );
        LogHelper.logInfo("Block models initialized.");
    }
}
