@file:Suppress("PackageDirectoryMismatch")

package cad97.spawnercraftkt.extensions.IBlockAccess

import net.minecraft.util.math.BlockPos
import net.minecraft.world.ChunkCache
import net.minecraft.world.IBlockAccess
import net.minecraft.world.chunk.Chunk

fun IBlockAccess.cacheSafeGetTileEntity(pos: BlockPos) =
        (this as? ChunkCache)?.getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) ?: getTileEntity(pos)
