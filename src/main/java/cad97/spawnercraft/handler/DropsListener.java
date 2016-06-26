package cad97.spawnercraft.handler;

import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftItems;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropsListener {
    public static final DropsListener instance = new DropsListener();

    private DropsListener() {
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        if (event.getSource().getSourceOfDamage() instanceof EntityPlayer) {
            Entity entity = event.getEntity();
            if (EntityList.ENTITY_EGGS.containsKey(EntityList.getEntityString(entity))) {
                ItemStack stack = new ItemStack(SpawnerCraftItems.mobEssence);
                ItemMonsterPlacer.applyEntityIdToItemStack(stack, EntityList.getEntityString(entity));
                entity.entityDropItem(stack, 0.0F);
            }
        }
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
