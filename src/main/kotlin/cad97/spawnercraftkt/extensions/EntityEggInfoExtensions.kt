@file:Suppress("PackageDirectoryMismatch")

package cad97.spawnercraftkt.extensions.EntityEggInfo

import net.minecraft.entity.EntityList

val EntityList.EntityEggInfo?.color
    get() = EntityEggInfoColor(this)

class EntityEggInfoColor(val entityEggInfo: EntityList.EntityEggInfo?) {
    operator fun get(index: Int) = when (index) {
        0 -> entityEggInfo?.primaryColor
        1 -> entityEggInfo?.secondaryColor
        else -> null
    }
}
