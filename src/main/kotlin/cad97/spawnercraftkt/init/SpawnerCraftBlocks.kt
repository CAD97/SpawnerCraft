package cad97.spawnercraftkt.init

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobBlock
import cad97.spawnercraftkt.block.BlockMobCage
import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.entity.EntityList
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemMonsterPlacer
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.property.IExtendedBlockState
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
        val mob_cage_item = Item.getItemFromBlock(mob_cage)
        ModelLoader.setCustomModelResourceLocation(
                mob_cage_item,
                0,
                ModelResourceLocation(mob_cage_item.registryName, null)
        )
        val mob_block_item = Item.getItemFromBlock(mob_block)
        ModelLoader.setCustomModelResourceLocation(
                mob_block_item,
                0,
                ModelResourceLocation(mob_block_item.registryName, null)
        )
        SpawnerCraft.logger.info("Block models registered.")
    }

    @SideOnly(Side.CLIENT)
    fun registerColors(itemColors: ItemColors, blockColors: BlockColors) {
        itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            val eggInfo = EntityList.ENTITY_EGGS[ItemMonsterPlacer.getNamedIdFrom(stack)]
            (if (tintIndex == 0) eggInfo?.primaryColor else eggInfo?.secondaryColor) ?: -1
        }, mob_block)
        blockColors.registerBlockColorHandler(IBlockColor { state, world, pos, tintIndex ->
            val extendedState = state.block.getExtendedState(state, world, pos)
            val mob = (extendedState as? IExtendedBlockState)?.getValue(BlockMobBlock.mobProperty)
            val eggInfo = EntityList.ENTITY_EGGS[mob]
            (if (tintIndex == 0) eggInfo?.primaryColor else eggInfo?.secondaryColor) ?: -1
        }, mob_block)
        SpawnerCraft.logger.info("Block colors registered.")
    }
}
