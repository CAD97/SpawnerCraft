package cad97.spawnercraft.handler;

import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftItems;
import cad97.spawnercraft.items.ItemMobSoul;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropsListener {
    public static final DropsListener instance = new DropsListener();

    private DropsListener() {
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        Entity sourceOfDamage = event.getSource().getTrueSource();
        if (sourceOfDamage instanceof EntityPlayer) {
            ItemStack heldItem = ((EntityPlayer) sourceOfDamage).getHeldItemMainhand();
            if (heldItem.getItem() == SpawnerCraftItems.MOB_ROD || !ConfigHandler.dropsRequireFishing) {
                dropFor(event.getEntity());
            }
        }
    }

    private void dropFor(Entity entity) {
        ResourceLocation entityResource = EntityList.getKey(entity);
        if (entityResource == null) return;
        String entityString = entityResource.toString();
        if (ConfigHandler.eggMapping.containsKey(entityString)) {
            entityString = ConfigHandler.eggMapping.get(entityString);
            entityResource = new ResourceLocation(entityString);
        }
        ItemStack stack = new ItemStack(SpawnerCraftItems.MOB_ESSENCE);
        if (EntityList.ENTITY_EGGS.containsKey(entityResource) &&
                (ConfigHandler.mobEssenceToggleList.contains(entityString) ^ ConfigHandler.isListBlacklist)
                ) {
            ItemMobSoul.applyEntityIdToItemStack(stack, entityResource);
            entity.entityDropItem(stack, 0.0F);
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() instanceof BlockMobSpawner) {
            // event.isSilkTouching() is only true when the block is silk-touch-able,
            // so we need to get the silk touch modifier manually.
            if (event.getHarvester() != null &&
                    EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
                            event.getHarvester().getHeldItemMainhand()) >= ConfigHandler.spawnerDropSilkLevel
                    ) {
                event.getDrops().add(new ItemStack(SpawnerCraftBlocks.MOB_CAGE));
            }
        }
    }
}
