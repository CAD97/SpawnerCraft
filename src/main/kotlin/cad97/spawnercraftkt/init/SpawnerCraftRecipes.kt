package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.SpawnerCraft
import com.google.common.base.Objects
import net.minecraft.init.Blocks
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.Item
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
    @SubscribeEvent
    fun registerRecipes(event: RegistryEvent.Register<IRecipe>) {
        val essence = Ingredient.fromItem(SpawnerCraftItems.mob_essence)
        event.registry.register(NBTMatchingShapedRecipe(
                SpawnerCraft.modid,
                2, 2,
                NonNullList.withSize(4, essence),
                ItemStack(SpawnerCraftItems.mob_agglomeration)
        ))
        if (Config.spawnerCraftable) {
            val ironBars = Ingredient.fromItem(Item.getItemFromBlock(Blocks.IRON_BARS))
            event.registry.register(ShapedRecipes(
                    SpawnerCraft.modid,
                    3, 3,
                    NonNullList.from(null,
                            ironBars, ironBars, ironBars,
                            ironBars, Ingredient.EMPTY, ironBars,
                            ironBars, ironBars, ironBars
                    ),
                    ItemStack(Item.getItemFromBlock(SpawnerCraftBlocks.mob_cage))
            ))
        }
        SpawnerCraft.logger.info("Recipes registered.")
    }
}
