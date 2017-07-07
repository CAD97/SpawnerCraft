package cad97.spawnercraft.init;

import cad97.spawnercraft.handler.ConfigHandler;
import cad97.spawnercraft.utility.LogHelper;
import cad97.spawnercraft.utility.NBTPreservingShapedRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpawnerCraftRecipes {
    public static void registerRecipes() {
        Ingredient essence = Ingredient.fromItem(SpawnerCraftItems.MOB_ESSENCE);
        ForgeRegistries.RECIPES.register(new NBTPreservingShapedRecipe(
                "spawnercraft",
                2, 2,
                NonNullList.withSize(4, essence),
                new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION)
        ).setRegistryName("craft_mob_agglomeration"));
        Ingredient agglomeration = Ingredient.fromItem(SpawnerCraftItems.MOB_AGGLOMERATION);
        ForgeRegistries.RECIPES.register(new NBTPreservingShapedRecipe(
                "spawnercraft",
                2, 2,
                NonNullList.withSize(4, agglomeration),
                new ItemStack(SpawnerCraftItems.MOB_SPIRIT)
        ).setRegistryName("craft_mob_spirit"));
        if (ConfigHandler.spawnerCraftable) {
            GameRegistry.addShapedRecipe(
                    new ResourceLocation("spawnercraft", "craft_mob_cage"),
                    null,
                    new ItemStack(SpawnerCraftBlocks.MOB_CAGE),
                    "III",
                    "I I",
                    "III",
                    'I', new ItemStack(Blocks.IRON_BARS)
            );
        }
        GameRegistry.addShapedRecipe(
                new ResourceLocation("spawnercraft", "craft_mob_rod"),
                null,
                new ItemStack(SpawnerCraftItems.MOB_ROD),
                "F",
                "S",
                'F', new ItemStack(Items.FISHING_ROD),
                'S', new ItemStack(SpawnerCraftBlocks.MOB_CAGE)
        );
        LogHelper.logInfo("Recipes registered.");
    }
}
