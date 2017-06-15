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

import java.util.HashMap;
import java.util.Map;

public class DropsListener {
    public static final DropsListener instance = new DropsListener();
    private static final Map<String, String> entityEggModSupport = new HashMap<>();

    static {
        entityEggModSupport.put("MCA.EntityHuman", "Villager");
    }

    private DropsListener() {
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        Entity sourceOfDamage = event.getSource().getSourceOfDamage();
        if (sourceOfDamage instanceof EntityPlayer) {
            ItemStack heldItem = ((EntityPlayer) sourceOfDamage).getHeldItemMainhand();
            if (heldItem.getItem() == SpawnerCraftItems.MOB_ROD || !ConfigHandler.dropsRequireFishing) {
                dropFor(event.getEntity());
            }
        }
    }

    private void dropFor(Entity entity) {
        ResourceLocation entityString = EntityList.getKey(entity);
        if (entityString == null) return;
        ItemStack stack = new ItemStack(SpawnerCraftItems.MOB_ESSENCE);
        if (EntityList.ENTITY_EGGS.containsKey(entityString) &&
                !ConfigHandler.disabledMobs.contains(entityString.getResourcePath())) {
            ItemMobSoul.applyEntityIdToItemStack(stack, entityString);
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
