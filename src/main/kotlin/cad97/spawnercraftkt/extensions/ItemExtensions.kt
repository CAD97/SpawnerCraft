@file:Suppress("DEPRECATION", "UsePropertyAccessSyntax", "unused", "PackageDirectoryMismatch")

package cad97.spawnercraftkt.extensions.Item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.text.translation.I18n

fun Item.getLocalizedName() = I18n.translateToLocal("${getUnlocalizedName()}.name")!!
fun Item.getLocalizedName(stack: ItemStack) = I18n.translateToLocal("${getUnlocalizedName(stack)}.name")!!
