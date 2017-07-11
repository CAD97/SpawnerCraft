package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobBlock
import cad97.spawnercraftkt.block.BlockMobCage
import cad97.spawnercraftkt.item.ItemMobSoul
import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber(modid = SpawnerCraft.modid)
object SpawnerCraftBlocks {
    @JvmStatic
    @GameRegistry.ObjectHolder("${SpawnerCraft.modid}:${BlockMobCage.id}")
    lateinit var mob_cage: Block private set

    @JvmStatic
    @GameRegistry.ObjectHolder("${SpawnerCraft.modid}:${BlockMobBlock.id}")
    lateinit var mob_block: Block private set

    @JvmStatic
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.register(BlockMobCage())
        event.registry.register(BlockMobBlock())
        SpawnerCraft.logger.info("Blocks registered.")
    }

    @JvmStatic
    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.register(ItemBlock(mob_cage).setRegistryName(mob_cage.registryName))
        event.registry.register(ItemBlock(mob_block).setRegistryName(mob_block.registryName))
        SpawnerCraft.logger.info("Block items registered.")
    }

    @JvmStatic
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        for (item in setOf(mob_cage, mob_block)) {
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(item), 0,
                    ModelResourceLocation(item.registryName.toString())
            )
        }
        SpawnerCraft.logger.info("Block models registered.")
    }

    @SideOnly(Side.CLIENT)
    fun registerColors(itemColors: ItemColors, blockColors: BlockColors) {
        itemColors.registerItemColorHandler(ItemMobSoul.colorHandler, mob_block)
        blockColors.registerBlockColorHandler(BlockMobBlock.colorHandler, mob_block)
        SpawnerCraft.logger.info("Block colors registered.")
    }
}
