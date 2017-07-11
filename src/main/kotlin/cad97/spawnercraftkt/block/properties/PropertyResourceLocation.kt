package cad97.spawnercraftkt.block.properties

import com.google.common.base.MoreObjects
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.property.IUnlistedProperty

class PropertyResourceLocation(private val name: String) : IUnlistedProperty<ResourceLocation> {
    override fun getName() = name
    override fun isValid(value: ResourceLocation?) = true
    override fun getType() = ResourceLocation::class.java
    override fun valueToString(value: ResourceLocation?) = value.toString()
    override fun toString() = MoreObjects.toStringHelper(this).add("name", this.name).toString()
}