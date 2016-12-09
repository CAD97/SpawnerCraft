package cad97.spawnercraft.init;

import cad97.spawnercraft.handler.ConfigHandler;
import cad97.spawnercraft.utility.LogHelper;
import cad97.spawnercraft.utility.NBTPreservingShapedRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpawnerCraftRecipes {
    public static void registerRecipes() {
        ItemStack essenceStack = new ItemStack(SpawnerCraftItems.MOB_ESSENCE);
        GameRegistry.addRecipe(new NBTPreservingShapedRecipe(
                2, 2,
                new ItemStack[]{essenceStack, essenceStack, essenceStack, essenceStack},
                new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION)
        ));
        ItemStack agglomerationStack = new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION);
        GameRegistry.addRecipe(new NBTPreservingShapedRecipe(
                2, 2,
                new ItemStack[]{agglomerationStack, agglomerationStack, agglomerationStack, agglomerationStack},
                new ItemStack(SpawnerCraftItems.MOB_SPIRIT)
        ));
        if (ConfigHandler.spawnerCraftable) {
            GameRegistry.addShapedRecipe(
                    new ItemStack(SpawnerCraftBlocks.MOB_CAGE),
                    "III",
                    "I I",
                    "III",
                    'I', new ItemStack(Blocks.IRON_BARS)
            );
        }
        GameRegistry.addShapedRecipe(
                new ItemStack(SpawnerCraftItems.MOB_ROD),
                "F",
                "S",
                'F', new ItemStack(Items.FISHING_ROD),
                'S', new ItemStack(SpawnerCraftBlocks.MOB_CAGE)
        );
        LogHelper.logInfo("Recipes registered.");
    }
}
