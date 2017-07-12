package cad97.spawnercraftkt.item

import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.init.SpawnerCraftBlocks
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemMonsterPlacer.*
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemMobSpirit : ItemMobSoul() {
    companion object {
        const val id = "mob_spirit"

        // Copied verbatim from ItemMonsterPlacer
        // It's protected and not static for some reason
        private fun getYOffset(world: World, pos: BlockPos): Double {
            val axisAlignedBB = AxisAlignedBB(pos).expand(0.0, -1.0, 0.0)
            val list = world.getCollisionBoxes(null as Entity?, axisAlignedBB)
            if (list.isEmpty()) return 0.0

            val newY = list
                    .map(AxisAlignedBB::maxY::get)
                    .max() ?: axisAlignedBB.minY
            return newY - pos.y.toDouble()
        }
    }

    init {
        registryName = ResourceLocation(SpawnerCraft.modid, id)
        unlocalizedName = "${SpawnerCraft.modid}.$id"
    }

    // Kindly borrowed and edited from ItemMonsterPlacer
    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val stack = player.getHeldItem(hand)

        if (world.isRemote)
            return EnumActionResult.SUCCESS
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack))
            return EnumActionResult.FAIL

        val state = world.getBlockState(pos)
        val block = state.block

        // Cancel mob spawn if player right clicks a mob spawner
        // Default spawn egg behavior is to change the entity spawned and we aren't doing that
        // Same with empty spawner, as the spirit used to be what set that
        // Allow sneaking to cancel the cancel and allow mob placement
        if ((block == Blocks.MOB_SPAWNER || block == SpawnerCraftBlocks.mob_cage) && !player.isSneaking)
            return EnumActionResult.FAIL

        val spawnPos = pos.offset(facing)
        val yOffset = getYOffset(world, spawnPos)

        spawnCreature(world, getNamedIdFrom(stack),
                spawnPos.x.toDouble() + 0.5,
                spawnPos.y.toDouble() + yOffset,
                spawnPos.z.toDouble() + 0.5
        )?.let { entity ->
            if (stack.hasDisplayName())
                (entity as? EntityLivingBase)?.customNameTag = stack.displayName
            applyItemEntityDataToEntity(world, player, stack, entity)
            if (!player.capabilities.isCreativeMode)
                stack.shrink(1)
        }

        return EnumActionResult.SUCCESS
    }
}