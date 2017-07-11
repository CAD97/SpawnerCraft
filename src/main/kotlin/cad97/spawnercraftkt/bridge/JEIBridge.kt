package cad97.spawnercraftkt.bridge

import cad97.spawnercraftkt.init.SpawnerCraftItems
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.ISubtypeRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeWrapper
import mezz.jei.api.recipe.VanillaRecipeCategoryUid
import net.minecraft.entity.EntityList
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation

@JEIPlugin
class JEIBridge : IModPlugin {
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) =
            subtypeRegistry.useNbtForSubtypes(
                    SpawnerCraftItems.mob_essence,
                    SpawnerCraftItems.mob_agglomeration
            )

    override fun register(registry: IModRegistry) {
        val essence = ItemStack(SpawnerCraftItems.mob_essence)
        val agglomeration = ItemStack(SpawnerCraftItems.mob_agglomeration)
        registry.addRecipes(EntityList.ENTITY_EGGS.keys.flatMap {
            listOf(
                    NBTPreservingCompactingRecipeWrapper(
                            it,
                            NonNullList.withSize(4, essence),
                            agglomeration
                    )
            )
        }, VanillaRecipeCategoryUid.CRAFTING)
    }

    class NBTPreservingCompactingRecipeWrapper(variant: ResourceLocation, input: List<ItemStack>, output: ItemStack) : IRecipeWrapper {
        val input = input.map { it.copy().also { ItemMonsterPlacer.applyEntityIdToItemStack(it, variant) } }
        val output = output.copy()!!.also { ItemMonsterPlacer.applyEntityIdToItemStack(it, variant) }
        override fun getIngredients(ingredients: IIngredients) {
            ingredients.setInputs(ItemStack::class.java, input)
            ingredients.setOutput(ItemStack::class.java, output)
        }
    }
}
