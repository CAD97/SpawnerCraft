package cad97.spawnercraft.handler;

import cad97.spawnercraft.block.SpawnerCraftBlocks;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropsListener {
    public static final DropsListener instance = new DropsListener();

    private DropsListener() {
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() instanceof BlockMobSpawner) {
            // event.isSilkTouching() is only true when the block is silk-touch-able,
            // so we need to get the silk touch modifier manually.
            if (!ConfigHandler.spawnerDropRequireSilk || (
                    event.getHarvester() != null &&
                            EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
                                    event.getHarvester().getHeldItemMainhand()) > 0
            )) {
                event.getDrops().add(new ItemStack(SpawnerCraftBlocks.mobCage));
            }
        }
    }
}
