package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobCage
import cad97.spawnercraftkt.item.ItemMobSoul
import com.google.common.base.Objects
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.Ingredient
import net.minecraft.item.crafting.ShapedRecipes
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod.EventBusSubscriber
object SpawnerCraftRecipes {
    class NBTMatchingShapedRecipe(
            group: String,
            width: Int,
            height: Int,
            items: NonNullList<Ingredient>,
            output: ItemStack
    ) : ShapedRecipes(group, width, height, items, output) {
        private var matchingNBT: NBTTagCompound? = null
        override fun matches(inv: InventoryCrafting, worldIn: World): Boolean {
            matchingNBT = (0 until inv.sizeInventory)
                    .map { inv.getStackInSlot(it) }
                    .firstOrNull { !it.isEmpty }
                    ?.tagCompound
            return if ((0 until inv.sizeInventory)
                    .map { inv.getStackInSlot(it) }
                    .any { !it.isEmpty && !Objects.equal(it.tagCompound, matchingNBT) })
                false
            else super.matches(inv, worldIn)
        }
    }

    @JvmStatic
    @GameRegistry.ItemStackHolder("${SpawnerCraft.modid}:${ItemMobSoul.essenceId}")
    lateinit var essenceStack: ItemStack

    @JvmStatic
    @GameRegistry.ItemStackHolder("${SpawnerCraft.modid}:${ItemMobSoul.agglomerationId}")
    lateinit var agglomerationStack: ItemStack

    @JvmStatic
    @GameRegistry.ItemStackHolder("minecraft:iron_bars")
    lateinit var ironBarsStack: ItemStack

    @JvmStatic
    @GameRegistry.ItemStackHolder("${SpawnerCraft.modid}:${BlockMobCage.id}")
    lateinit var mobCageStack: ItemStack

    @JvmStatic
    @SubscribeEvent
    fun registerRecipes(event: RegistryEvent.Register<IRecipe>) {
        val essenceIngredient = Ingredient.fromStacks(essenceStack)
        event.registry.register(NBTMatchingShapedRecipe(
                SpawnerCraft.modid,
                2, 2,
                NonNullList.withSize(4, essenceIngredient),
                ItemStack(SpawnerCraftItems.mob_agglomeration)
        ))
        if (Config.spawnerCraftable) {
            val ironBarsIngredient = Ingredient.fromStacks(ironBarsStack)
            event.registry.register(ShapedRecipes(
                    SpawnerCraft.modid,
                    3, 3,
                    NonNullList.from(null,
                            ironBarsIngredient, ironBarsIngredient, ironBarsIngredient,
                            ironBarsIngredient, Ingredient.EMPTY, ironBarsIngredient,
                            ironBarsIngredient, ironBarsIngredient, ironBarsIngredient
                    ),
                    mobCageStack
            ))
        }
        SpawnerCraft.logger.info("Recipes registered.")
    }
}
