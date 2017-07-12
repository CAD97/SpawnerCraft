package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.item.ItemMobSoul
import cad97.spawnercraftkt.item.ItemMobSpirit
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
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
    @GameRegistry.ObjectHolder("${SpawnerCraft.modid}:${ItemMobSpirit.id}")
    lateinit var mob_spirit: Item private set

    @JvmStatic
    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        for (id in setOf(ItemMobSoul.essenceId, ItemMobSoul.agglomerationId)) {
            event.registry.register(
                    ItemMobSoul()
                            .setRegistryName(SpawnerCraft.modid, id)
                            .setUnlocalizedName("${SpawnerCraft.modid}.$id")
            )
        }
        event.registry.register(ItemMobSpirit())
        SpawnerCraft.logger.info("Items registered.")
    }

    @JvmStatic
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        for (item in setOf(mob_essence, mob_agglomeration, mob_spirit)) {
            ModelLoader.setCustomModelResourceLocation(
                    item, 0,
                    ModelResourceLocation(item.registryName.toString())
            )
        }
        SpawnerCraft.logger.info("Item models registered.")
    }

    @SideOnly(Side.CLIENT)
    fun registerColors(itemColors: ItemColors) {
        itemColors.registerItemColorHandler(ItemMobSoul.colorHandler, mob_essence, mob_agglomeration, mob_spirit)
        SpawnerCraft.logger.info("Item colors registered.")
    }
}