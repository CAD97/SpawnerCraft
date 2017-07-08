package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.item.ItemMobSoul
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.entity.EntityList
import net.minecraft.item.Item
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber(modid = SpawnerCraft.modid)
object SpawnerCraftItems {
    @JvmStatic
    @GameRegistry.ObjectHolder("${SpawnerCraft.modid}:${ItemMobSoul.essenceId}")
    lateinit var mob_essence: Item private set

    @JvmStatic
    @GameRegistry.ObjectHolder("${SpawnerCraft.modid}:${ItemMobSoul.agglomerationId}")
    lateinit var mob_agglomeration: Item private set

    @JvmStatic
    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.register(
                ItemMobSoul()
                        .setRegistryName(SpawnerCraft.modid, ItemMobSoul.essenceId)
                        .setUnlocalizedName("${SpawnerCraft.modid}.${ItemMobSoul.essenceId}")
        )
        event.registry.register(
                ItemMobSoul()
                        .setRegistryName(SpawnerCraft.modid, ItemMobSoul.agglomerationId)
                        .setUnlocalizedName("${SpawnerCraft.modid}.${ItemMobSoul.agglomerationId}")
        )
        SpawnerCraft.logger.info("Items registered.")
    }

    @JvmStatic
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        ModelLoader.setCustomModelResourceLocation(
                mob_essence,
                0,
                ModelResourceLocation(mob_essence.registryName, "inventory")
        )
        ModelLoader.setCustomModelResourceLocation(
                mob_agglomeration,
                0,
                ModelResourceLocation(mob_agglomeration.registryName, null)
        )
        SpawnerCraft.logger.info("Item models registered.")
    }

    @SideOnly(Side.CLIENT)
    fun registerColors(itemColors: ItemColors) {
        itemColors.registerItemColorHandler(IItemColor { stack: ItemStack, tintIndex: Int ->
            val eggInfo = EntityList.ENTITY_EGGS[ItemMonsterPlacer.getNamedIdFrom(stack)]
            (if (tintIndex == 0) eggInfo?.primaryColor else eggInfo?.secondaryColor) ?: -1
        }, mob_essence, mob_agglomeration)
        SpawnerCraft.logger.info("Item colors registered.")
    }
}