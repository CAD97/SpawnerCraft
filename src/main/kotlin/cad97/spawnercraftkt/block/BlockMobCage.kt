package cad97.spawnercraftkt.block

import cad97.spawnercraftkt.SpawnerCraft
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object BlockMobCage : Block(Material.ROCK) {
    const val id = "mob_cage"

    init {
        registryName = ResourceLocation(SpawnerCraft.modid, id)
        unlocalizedName = "${SpawnerCraft.modid}.$id"
        blockHardness = 5.0f
        soundType = SoundType.METAL
        setHarvestLevel("pickaxe", Item.ToolMaterial.STONE.harvestLevel)
        setCreativeTab(SpawnerCraft.tab)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun isOpaqueCube(state: IBlockState) = false
    @SideOnly(Side.CLIENT)
    override fun getBlockLayer() = BlockRenderLayer.CUTOUT
}