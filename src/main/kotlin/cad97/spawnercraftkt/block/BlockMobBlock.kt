package cad97.spawnercraftkt.block

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.properties.PropertyResourceLocation
import cad97.spawnercraftkt.tileentity.TileEntityMobBlock
import net.minecraft.block.BlockBreakable
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityList
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.ChunkCache
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraft.world.chunk.Chunk
import net.minecraftforge.common.property.ExtendedBlockState
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockMobBlock : BlockBreakable(Material.CLAY, false, MapColor.GRASS), ITileEntityProvider {
    companion object {
        const val id = "mob_block"
        val mobProperty = PropertyResourceLocation("mob")
    }

    init {
        registryName = ResourceLocation(SpawnerCraft.modid, id)
        unlocalizedName = "${SpawnerCraft.modid}.$id"
        setCreativeTab(SpawnerCraft.tab)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer() = BlockRenderLayer.CUTOUT_MIPPED

    override fun createNewTileEntity(world: World, meta: Int) = TileEntityMobBlock()

    override fun getSubBlocks(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        for (entityEggInfo in EntityList.ENTITY_EGGS.values) {
            val stack = ItemStack(this)
            ItemMonsterPlacer.applyEntityIdToItemStack(stack, entityEggInfo.spawnedID)
            items.add(stack)
        }
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        SpawnerCraft.logger.info(state)
        SpawnerCraft.logger.info((getExtendedState(state, world, pos) as IExtendedBlockState).unlistedProperties)
        SpawnerCraft.logger.info(world.getTileEntity(pos))
        return super.onBlockActivated(world, pos, state, playerIn, hand, facing, hitX, hitY, hitZ)
    }

    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        (world.getTileEntity(pos) as? TileEntityMobBlock)?.setFrom(stack)
    }

    override fun createBlockState() = ExtendedBlockState(this, arrayOf(), arrayOf(mobProperty))
    override fun getExtendedState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState {
        val tileEntity =
                if (world is ChunkCache) world.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK)
                else world.getTileEntity(pos)
        val mob = (tileEntity as? TileEntityMobBlock)?.mob
        return (state as? IExtendedBlockState)?.withProperty(mobProperty, mob) ?: state
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        val mob = (getExtendedState(state, world, pos) as IExtendedBlockState).getValue(mobProperty)
        val stack = ItemStack(this)
        if (mob != null) ItemMonsterPlacer.applyEntityIdToItemStack(stack, mob)
        return stack
    }
}