package cad97.spawnercraftkt.item

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobBlock
import cad97.spawnercraftkt.extensions.Item.getLocalizedName
import cad97.spawnercraftkt.init.SpawnerCraftBlocks
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityList
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityMobSpawner
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemMobBlock : ItemBlock(SpawnerCraftBlocks.mob_block) {
    companion object {
        const val id = BlockMobBlock.id
    }

    init {
        registryName = ResourceLocation(SpawnerCraft.modid, id)
    }

    @Suppress("DEPRECATION")
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val itemName = getLocalizedName(stack)
        val mobName = EntityList.getTranslationName(ItemMonsterPlacer.getNamedIdFrom(stack))
        return itemName.format(net.minecraft.util.text.translation.I18n.translateToLocal("entity.$mobName.name"))
    }

    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            for (entityEggInfo in EntityList.ENTITY_EGGS.values) {
                val stack = ItemStack(this)
                ItemMonsterPlacer.applyEntityIdToItemStack(stack, entityEggInfo.spawnedID)
                items.add(stack)
            }
        }
    }

    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val stack = player.getHeldItem(hand)
        val mob = ItemMonsterPlacer.getNamedIdFrom(stack)

        if (mob == null
                || mob !in EntityList.ENTITY_EGGS
                || world.getBlockState(pos).block != SpawnerCraftBlocks.mob_cage
                || player.isSneaking
                )
            return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ)

        world.setBlockState(pos, Blocks.MOB_SPAWNER.defaultState)
        val tileEntity = world.getTileEntity(pos) as TileEntityMobSpawner
        val baseLogic = tileEntity.spawnerBaseLogic

        baseLogic.setEntityId(mob)
        tileEntity.markDirty()

        if (!player.capabilities.isCreativeMode) stack.shrink(1)
        return EnumActionResult.SUCCESS
    }

    // Always claim being able to place block in any location
    // Otherwise PlayerControllerMP#L457 cancels the onItemUse event before it reaches us
    override fun canPlaceBlockOnSide(worldIn: World, pos: BlockPos, side: EnumFacing, player: EntityPlayer, stack: ItemStack) = true
}
