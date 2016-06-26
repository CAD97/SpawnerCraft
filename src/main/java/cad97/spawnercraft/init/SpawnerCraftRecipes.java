package cad97.spawnercraft.init;

import cad97.spawnercraft.handler.ConfigHandler;
import cad97.spawnercraft.utility.LogHelper;
import cad97.spawnercraft.utility.NBTPreservingShapedRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpawnerCraftRecipes {
    public static void registerRecipes() {
        ItemStack essenceStack = new ItemStack(SpawnerCraftItems.mobEssence);
        GameRegistry.addRecipe(new NBTPreservingShapedRecipe(
                2, 2,
                new ItemStack[]{essenceStack, essenceStack, essenceStack, essenceStack},
                new ItemStack(SpawnerCraftItems.mobAgglomeration)
        ));
        ItemStack agglomerationStack = new ItemStack(SpawnerCraftItems.mobAgglomeration);
        GameRegistry.addRecipe(new NBTPreservingShapedRecipe(
                2, 2,
                new ItemStack[]{agglomerationStack, agglomerationStack, agglomerationStack, agglomerationStack},
                new ItemStack(SpawnerCraftItems.mobSpirit)
        ));
        if (ConfigHandler.spawnerCraftable) {
            GameRegistry.addShapedRecipe(
                    new ItemStack(SpawnerCraftBlocks.mobCage),
                    "III",
                    "I I",
                    "III",
                    'I', new ItemStack(Blocks.IRON_BARS)
            );
        }
        LogHelper.logInfo("Recipes registered.");
    }
}
