package cad97.spawnercraftkt.recipe

import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.world.World
import net.minecraftforge.registries.IForgeRegistryEntry.Impl

class NullRecipe : Impl<IRecipe>(), IRecipe {
    override fun canFit(width: Int, height: Int) = false
    override fun matches(inv: InventoryCrafting, worldIn: World) = false
    override fun getRecipeOutput() = ItemStack.EMPTY!!
    override fun getCraftingResult(inv: InventoryCrafting?) = ItemStack.EMPTY!!
}