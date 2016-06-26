package cad97.spawnercraft.init;

import cad97.spawnercraft.utility.NBTPreservingShapedRecipe;
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
    }
}
