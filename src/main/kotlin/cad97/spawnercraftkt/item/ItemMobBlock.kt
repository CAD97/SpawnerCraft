package cad97.spawnercraftkt.item

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobBlock
import cad97.spawnercraftkt.extensions.Item.getLocalizedName
import cad97.spawnercraftkt.init.SpawnerCraftBlocks
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityList
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation

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
}