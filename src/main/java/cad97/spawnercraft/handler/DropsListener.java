package cad97.spawnercraft.handler;

import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftItems;
import cad97.spawnercraft.init.SpawnerCraftMobAlias;
import cad97.spawnercraft.items.ItemMobSoul;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
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
            if ((heldItem != null && heldItem.getItem() == SpawnerCraftItems.mobRod) ||
                    !ConfigHandler.dropsRequireFishing) {
                dropFor(event.getEntity());
            }
        }
    }

    private void dropFor(Entity entity) {
        String entityString = getCustomIdFor(entity);
        ItemStack stack = new ItemStack(SpawnerCraftItems.mobEssence);
        if (EntityList.ENTITY_EGGS.containsKey(entityString) ||
                SpawnerCraftMobAlias.customEggs.containsKey(entityString)) {
            ItemMobSoul.applyEntityIdToItemStack(stack, entityString);
            entity.entityDropItem(stack, 0.0F);
        }
    }

    private String getCustomIdFor(Entity entity) {
        String entityString = EntityList.getEntityString(entity);
        if (entityEggModSupport.containsKey(EntityList.getEntityString(entity))) {
            entityString = entityEggModSupport.get(EntityList.getEntityString(entity));
        } else if (ConfigHandler.witherSkeletonSoul && entity instanceof EntitySkeleton &&
                ((EntitySkeleton) entity).getSkeletonType() == 1) {
            entityString = "WitherSkeleton";
        } else if (ConfigHandler.elderGuardianSoul && entity instanceof EntityGuardian &&
                ((EntityGuardian) entity).isElder()) {
            entityString = "ElderGuardian";
        } else if (ConfigHandler.donkeySoul && entity instanceof EntityHorse &&
                ((EntityHorse) entity).getType() == HorseType.DONKEY) {
            entityString = "Donkey";
        } else if (ConfigHandler.muleSoul && entity instanceof EntityHorse &&
                ((EntityHorse) entity).getType() == HorseType.MULE) {
            entityString = "Mule";
        }
        return entityString;
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        System.out.println(event.getState().getBlock());
        if (event.getState().getBlock() instanceof BlockMobSpawner) {
            // event.isSilkTouching() is only true when the block is silk-touch-able,
            // so we need to get the silk touch modifier manually.
            if (event.getHarvester() != null &&
                    EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
                            event.getHarvester().getHeldItemMainhand()) >= ConfigHandler.spawnerDropSilkLevel
                    ) {
                event.getDrops().add(new ItemStack(SpawnerCraftBlocks.mobCage));
            }
        }
    }
}
