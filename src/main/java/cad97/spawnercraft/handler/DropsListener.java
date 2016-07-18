package cad97.spawnercraft.handler;

import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftItems;
import cad97.spawnercraft.init.SpawnerCraftMobAlias;
import cad97.spawnercraft.items.ItemMobSoul;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropsListener {
    public static final DropsListener instance = new DropsListener();

    private DropsListener() {
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        if (event.getSource().getSourceOfDamage() instanceof EntityPlayer && (
                !ConfigHandler.dropsRequireFishing ||
                        new ItemStack(SpawnerCraftItems.mobRod).isItemEqualIgnoreDurability(
                                ((EntityPlayer) event.getSource().getSourceOfDamage()).getHeldItemMainhand()
                        )
        )) {
            Entity entity = event.getEntity();

            if (EntityList.ENTITY_EGGS.containsKey(EntityList.getEntityString(entity))) {
                ItemStack stack = new ItemStack(SpawnerCraftItems.mobEssence);

                String customID = null;

                if (ConfigHandler.witherSkeletonSoul && entity instanceof EntitySkeleton &&
                        ((EntitySkeleton) entity).getSkeletonType() == SkeletonType.WITHER) {
                    customID = "WitherSkeleton";
                } else if (ConfigHandler.straySoul && entity instanceof EntitySkeleton &&
                        ((EntitySkeleton) entity).getSkeletonType() == SkeletonType.STRAY) {
                    customID = "Stray";
                } else if (ConfigHandler.huskSoul && entity instanceof EntityZombie &&
                        ((EntityZombie) entity).getZombieType() == ZombieType.HUSK) {
                    customID = "Husk";
                } else if (ConfigHandler.elderGuardianSoul && entity instanceof EntityGuardian &&
                        ((EntityGuardian) entity).isElder()) {
                    customID = "ElderGuardian";
                } else if (ConfigHandler.donkeySoul && entity instanceof EntityHorse &&
                        ((EntityHorse) entity).getType() == HorseType.DONKEY) {
                    customID = "Donkey";
                } else if (ConfigHandler.muleSoul && entity instanceof EntityHorse &&
                        ((EntityHorse) entity).getType() == HorseType.MULE) {
                    customID = "Mule";
                } else if (ConfigHandler.skeletonHorseSoul && entity instanceof EntityHorse &&
                        ((EntityHorse) entity).getType() == HorseType.SKELETON) {
                    customID = "SkeletonHorse";
                }

                if (customID != null) {
                    stack.setTagCompound(new NBTTagCompound());
                    //noinspection ConstantConditions
                    stack.getTagCompound().setTag("EntityTag", SpawnerCraftMobAlias.customNBT.get(customID));
                } else {
                    ItemMobSoul.applyEntityIdToItemStack(stack, EntityList.getEntityString(entity));
                }

                entity.entityDropItem(stack, 0.0F);
            }
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() instanceof BlockMobSpawner) {
            // event.isSilkTouching() is only true when the block is silk-touch-able,
            // so we need to get the silk touch modifier manually.
            if (event.getHarvester() != null &&
                    EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
                            event.getHarvester().getHeldItemMainhand()) > ConfigHandler.spawnerDropSilkLevel
                    ) {
                event.getDrops().add(new ItemStack(SpawnerCraftBlocks.mobCage));
            }
        }
    }
}
