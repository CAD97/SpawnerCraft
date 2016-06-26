package cad97.spawnercraft.init;

import cad97.spawnercraft.block.BlockMobCage;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpawnerCraftBlocks {

    public static final Block mobCage = new BlockMobCage();

    public static void registerBlocks() {
        GameRegistry.register(mobCage);
        LogHelper.logInfo("Blocks initialized.");
    }

    @SuppressWarnings("ConstantConditions")
    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(mobCage),
                0, new ModelResourceLocation(mobCage.getRegistryName(), "inventory")
        );
        LogHelper.logInfo("Block models initialized.");
    }
}
