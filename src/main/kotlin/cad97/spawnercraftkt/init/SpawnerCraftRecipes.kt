package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobCage
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.Ingredient
import net.minecraft.item.crafting.ShapedRecipes
import net.minecraft.util.NonNullList
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object SpawnerCraftRecipes {
    @JvmStatic
    @SubscribeEvent
    fun registerRecipes(event: RegistryEvent.Register<IRecipe>) {
        if (Config.spawnerCraftable) {
            val ironBars = Ingredient.fromItem(Item.getItemFromBlock(Blocks.IRON_BARS))
            event.registry.register(ShapedRecipes(
                    "",
                    3, 3,
                    NonNullList.from(null,
                            ironBars, ironBars, ironBars,
                            ironBars, Ingredient.EMPTY, ironBars,
                            ironBars, ironBars, ironBars
                    ),
                    ItemStack(SpawnerCraftBlocks.mob_cage)
            ).setRegistryName(SpawnerCraft.modid, BlockMobCage.id))
        }
        SpawnerCraft.logger.info("Recipes registered.")
    }
}
