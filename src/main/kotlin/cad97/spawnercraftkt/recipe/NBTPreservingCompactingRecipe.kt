package cad97.spawnercraftkt.recipe

import com.google.gson.JsonObject
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.Ingredient
import net.minecraft.world.World
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.common.crafting.IRecipeFactory
import net.minecraftforge.common.crafting.JsonContext
import net.minecraftforge.registries.IForgeRegistryEntry.Impl

@Suppress("unused")
class NBTPreservingCompactingRecipe(val input: Ingredient, val count: Int, val output: ItemStack) : Impl<IRecipe>(), IRecipe {
    init {
        if (count <= 0) throw IllegalArgumentException("input count must be positive")
    }

    override fun canFit(width: Int, height: Int) = width * height <= count
    override fun matches(inv: InventoryCrafting, worldIn: World): Boolean {
        val stacks = (0 until inv.sizeInventory).map(inv::getStackInSlot).filter { !it.isEmpty }
        if (stacks.size != count) return false
        val nbt = stacks.first().tagCompound
        return stacks.all { input.test(it) && it.tagCompound == nbt }
    }

    override fun getRecipeOutput() = output
    override fun getCraftingResult(inv: InventoryCrafting) =
            output.copy()!!.apply {
                tagCompound =
                        (0 until inv.sizeInventory)
                                .map(inv::getStackInSlot)
                                .filter { !it.isEmpty }
                                .first()
                                .tagCompound
            }

    class Factory : IRecipeFactory {
        override fun parse(context: JsonContext, json: JsonObject): IRecipe {
            val input = CraftingHelper.getIngredient(json["input"], context)
            val count = json["count"].asInt
            val output = CraftingHelper.getItemStack(json["output"].asJsonObject, context)
            return NBTPreservingCompactingRecipe(input, count, output)
        }
    }
}