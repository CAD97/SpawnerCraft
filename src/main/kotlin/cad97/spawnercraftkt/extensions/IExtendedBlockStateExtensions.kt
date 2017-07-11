@file:Suppress("PackageDirectoryMismatch")

package cad97.spawnercraftkt.extensions.IExtendedBlockState

import net.minecraft.block.properties.IProperty
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.common.property.IUnlistedProperty

operator fun <T : Comparable<T>> IExtendedBlockState.get(property: IProperty<T>) = getValue(property)!!
operator fun <T : Comparable<T>> IExtendedBlockState.get(property: IUnlistedProperty<T>) = getValue(property)!!
