package cad97.spawnercraftkt.bridge

import cad97.spawnercraftkt.init.SpawnerCraftBlocks
import cad97.spawnercraftkt.init.SpawnerCraftItems
import cad97.spawnercraftkt.recipe.NBTPreservingCompactingRecipe
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.ISubtypeRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.api.recipe.VanillaRecipeCategoryUid
import net.minecraft.entity.EntityList
import net.minecraft.item.Item
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries

@JEIPlugin
// TODO: Recipe Handler for NBTPreservingCompactingRecipe?
class JEIBridge : IModPlugin {
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) =
            subtypeRegistry.useNbtForSubtypes(
                    SpawnerCraftItems.mob_essence,
                    SpawnerCraftItems.mob_agglomeration,
                    SpawnerCraftItems.mob_spirit,
                    Item.getItemFromBlock(SpawnerCraftBlocks.mob_block)
            )

    override fun register(registry: IModRegistry) =
            registry.addRecipes(
                    ForgeRegistries.RECIPES
                            .map { it as? NBTPreservingCompactingRecipe }
                            .filterNotNull()
                            .flatMap(NBTPreservingCompactingRecipeWrapper.Companion::wrappersFor)
                    , VanillaRecipeCategoryUid.CRAFTING
            )

    class NBTPreservingCompactingRecipeWrapper(variant: ResourceLocation, input: List<ItemStack>, output: ItemStack) : IRecipeWrapper {
        companion object {
            fun wrappersFor(recipe: NBTPreservingCompactingRecipe) =
                    EntityList.ENTITY_EGGS.keys.map { entity ->
                        NBTPreservingCompactingRecipeWrapper(
                                entity,
                                NonNullList.withSize(recipe.count, recipe.input.matchingStacks[0]),
                                recipe.recipeOutput
                        )
                    }
        }

        val input = input.map { it.copy().also { ItemMonsterPlacer.applyEntityIdToItemStack(it, variant) } }
        val output = output.copy()!!.also { ItemMonsterPlacer.applyEntityIdToItemStack(it, variant) }
        override fun getIngredients(ingredients: IIngredients) {
            ingredients.setInputs(ItemStack::class.java, input)
            ingredients.setOutput(ItemStack::class.java, output)
        }
    }
}
