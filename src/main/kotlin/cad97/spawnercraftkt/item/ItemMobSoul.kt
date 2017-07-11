package cad97.spawnercraftkt.item

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.minecraft.getLocalizedName
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityList
import net.minecraft.entity.EntityList.getTranslationName
import net.minecraft.item.Item
import net.minecraft.item.ItemMonsterPlacer.applyEntityIdToItemStack
import net.minecraft.item.ItemMonsterPlacer.getNamedIdFrom
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

class ItemMobSoul : Item() {
    companion object {
        const val essenceId = "mob_essence"
        const val agglomerationId = "mob_agglomeration"
    }

    init {
        maxStackSize = 64
        hasSubtypes = true
        creativeTab = SpawnerCraft.tab
    }

    @Suppress("DEPRECATION")
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val itemName = getLocalizedName(stack)
        val mobName = getTranslationName(getNamedIdFrom(stack))
        return itemName.format(net.minecraft.util.text.translation.I18n.translateToLocal("entity.$mobName.name"))
    }

    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            for (entityEggInfo in EntityList.ENTITY_EGGS.values) {
                val stack = ItemStack(this)
                applyEntityIdToItemStack(stack, entityEggInfo.spawnedID)
                items.add(stack)
            }
        }
    }
}