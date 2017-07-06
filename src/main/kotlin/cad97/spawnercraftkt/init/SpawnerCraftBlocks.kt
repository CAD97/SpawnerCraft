package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobCage
import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod.EventBusSubscriber(modid = SpawnerCraft.modid)
object SpawnerCraftBlocks {
    @JvmStatic
    @GameRegistry.ObjectHolder("${SpawnerCraft.modid}:${BlockMobCage.id}")
    lateinit var mob_cage: Block private set

    @JvmStatic
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.register(BlockMobCage)
        SpawnerCraft.logger.info("Blocks registered.")
    }

    @JvmStatic
    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.register(ItemBlock(mob_cage).setRegistryName(BlockMobCage.registryName))
        SpawnerCraft.logger.info("Block items registered.")
    }

    @JvmStatic
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        val mob_cage_item = Item.getItemFromBlock(mob_cage)
        ModelLoader.setCustomModelResourceLocation(
                mob_cage_item,
                0,
                ModelResourceLocation(mob_cage_item.registryName, null)
        )
        SpawnerCraft.logger.info("Block models registered.")
    }
}
