@file:Suppress("PackageDirectoryMismatch")

package cad97.spawnercraftkt.extensions.Block

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs

var Block.creativeTab: CreativeTabs
    get() = creativeTabToDisplayOn
    set(value) {
        setCreativeTab(value)
    }
