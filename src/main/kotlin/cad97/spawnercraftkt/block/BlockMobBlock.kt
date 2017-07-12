package cad97.spawnercraftkt.block

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.properties.PropertyResourceLocation
import cad97.spawnercraftkt.extensions.Block.creativeTab
import cad97.spawnercraftkt.extensions.EntityEggInfo.color
import cad97.spawnercraftkt.extensions.IBlockAccess.cacheSafeGetTileEntity
import cad97.spawnercraftkt.extensions.IExtendedBlockState.get
import cad97.spawnercraftkt.tileentity.TileEntityMobBlock
import net.minecraft.block.BlockBreakable
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityList
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockMobBlock : BlockBreakable(Material.CLAY, false, MapColor.GRASS), ITileEntityProvider {
    companion object {
        const val id = "mob_block"
        val mobProperty = PropertyResourceLocation("mob")

        val colorHandler = IBlockColor { state, world, pos, tintIndex ->
            val extendedState = state.block.getExtendedState(state, world, pos)
            val mob = (extendedState as IExtendedBlockState)[mobProperty]
            EntityList.ENTITY_EGGS[mob].color[tintIndex] ?: -1
        }
    }

    init {
        registryName = ResourceLocation(SpawnerCraft.modid, id)
        unlocalizedName = "${SpawnerCraft.modid}.$id"
        creativeTab = SpawnerCraft.tab
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer() = BlockRenderLayer.CUTOUT_MIPPED

    // TODO: Remove when not using ItemBlock
    override fun getSubBlocks(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        for (entityEggInfo in EntityList.ENTITY_EGGS.values) {
            val stack = ItemStack(this)
            ItemMonsterPlacer.applyEntityIdToItemStack(stack, entityEggInfo.spawnedID)
            items.add(stack)
        }
    }

    override fun createNewTileEntity(world: World, meta: Int) = TileEntityMobBlock()
    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) =
            (world.getTileEntity(pos) as TileEntityMobBlock).setFrom(stack)

    override fun createBlockState() = BlockStateContainer.Builder(this).add(mobProperty).build()!!
    override fun getExtendedState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState {
        val tileEntity = world.cacheSafeGetTileEntity(pos)
        val mob = (tileEntity as? TileEntityMobBlock)?.mob ?: ResourceLocation("null")
        return (state as IExtendedBlockState).withProperty(mobProperty, mob)
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        val mob = (getExtendedState(state, world, pos) as IExtendedBlockState)[mobProperty]
        val stack = ItemStack(this)
        ItemMonsterPlacer.applyEntityIdToItemStack(stack, mob)
        return stack
    }
}