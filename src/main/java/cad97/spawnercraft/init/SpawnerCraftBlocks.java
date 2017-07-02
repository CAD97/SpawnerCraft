package cad97.spawnercraft.init;

import cad97.spawnercraft.block.BlockMobCage;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpawnerCraftBlocks {

    public static final Block MOB_CAGE = new BlockMobCage();

    public static void registerBlocks() {
        ForgeRegistries.BLOCKS.register(MOB_CAGE);
        LogHelper.logInfo("Blocks initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MOB_CAGE),
                0, new ModelResourceLocation(MOB_CAGE.getRegistryName(), "inventory")
        );
        LogHelper.logInfo("Block models initialized.");
    }
}
