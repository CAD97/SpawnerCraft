package cad97.spawnercraftkt.tileentity

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.block.BlockMobBlock
import com.google.common.base.MoreObjects
import net.minecraft.item.ItemMonsterPlacer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation

class TileEntityMobBlock : TileEntity() {
    companion object {
        const val id = BlockMobBlock.id
    }

    var mob: ResourceLocation = ResourceLocation("null")
        private set

    fun setFrom(stack: ItemStack) {
        mob = ItemMonsterPlacer.getNamedIdFrom(stack) ?: ResourceLocation("null")
        markDirty()
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setString("mob", mob.toString())
        return super.writeToNBT(compound)
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)
        SpawnerCraft.logger.info(compound)
        mob = ResourceLocation(compound.getString("mob") ?: "null")
    }

    override fun getUpdateTag() = writeToNBT(NBTTagCompound())
    override fun getUpdatePacket() = SPacketUpdateTileEntity(pos, 1, updateTag)
    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) = readFromNBT(pkt.nbtCompound)

    override fun toString() = MoreObjects.toStringHelper(this).add("mob", mob).toString()
}