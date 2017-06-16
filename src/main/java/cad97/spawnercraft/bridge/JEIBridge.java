package cad97.spawnercraft.bridge;

import cad97.spawnercraft.init.SpawnerCraftItems;
import cad97.spawnercraft.items.ItemMobSoul;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JEIPlugin
public class JEIBridge extends BlankModPlugin {
    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.useNbtForSubtypes(
                SpawnerCraftItems.MOB_AGGLOMERATION,
                SpawnerCraftItems.MOB_ESSENCE,
                SpawnerCraftItems.MOB_SPIRIT
        );
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        registry.addRecipes(EntityList.getEntityNameList().stream().flatMap(resourceLocation -> Stream.of(
                new NBTPreservingShapedRecipeWrapper(
                        resourceLocation,
                        Arrays.asList(
                                new ItemStack(SpawnerCraftItems.MOB_ESSENCE),
                                new ItemStack(SpawnerCraftItems.MOB_ESSENCE),
                                new ItemStack(SpawnerCraftItems.MOB_ESSENCE),
                                new ItemStack(SpawnerCraftItems.MOB_ESSENCE)
                        ),
                        new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION)
                ),
                new NBTPreservingShapedRecipeWrapper(
                        resourceLocation,
                        Arrays.asList(
                                new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION),
                                new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION),
                                new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION),
                                new ItemStack(SpawnerCraftItems.MOB_AGGLOMERATION)
                        ),
                        new ItemStack(SpawnerCraftItems.MOB_SPIRIT)
                )
        )).collect(Collectors.toList()), VanillaRecipeCategoryUid.CRAFTING);
    }
}

class NBTPreservingShapedRecipeWrapper extends BlankRecipeWrapper {
    private final List<ItemStack> input;
    private final ItemStack output;

    NBTPreservingShapedRecipeWrapper(ResourceLocation variant, List<ItemStack> input, ItemStack output) {
        this.input = input.stream().map(itemStack -> {
            ItemStack copy = itemStack.copy();
            ItemMobSoul.applyEntityIdToItemStack(copy, variant);
            return copy;
        }).collect(Collectors.toList());
        this.output = output.copy();
        ItemMobSoul.applyEntityIdToItemStack(this.output, variant);
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }
}
