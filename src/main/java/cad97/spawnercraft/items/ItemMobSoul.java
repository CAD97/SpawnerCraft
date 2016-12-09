package cad97.spawnercraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * Base class for MOB_ESSENCE, MOB_AGGLOMERATION, and MOB_SPIRIT
 */
public abstract class ItemMobSoul extends SpawnerCraftItem {
    ItemMobSoul() {
        super();
        setMaxStackSize(64);
        setHasSubtypes(true);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String itemName = I18n.translateToLocal(this.getUnlocalizedName() + ".name").trim();
        String mobName = EntityList.getTranslationName(ItemMonsterPlacer.getNamedIdFrom(stack));

        if (mobName != null) {
            mobName = I18n.translateToLocal("entity." + mobName + ".name");
        }

        return String.format(itemName, mobName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        Items.SPAWN_EGG.getSubItems(itemIn, tab, subItems);
/*
        for (EntityList.EntityEggInfo egg : EntityList.ENTITY_EGGS.values()) {
            ItemStack stack = new ItemStack(itemIn);
            ItemMobSoul.applyEntityIdToItemStack(stack, egg.spawnedID);
            subItems.add(stack);
        }
*/
    }

    // Stolen from @SideOnly(CLIENT) in ItemMonsterPlacer. I need to access it on server side
    // to enable applying entity id to dropped item stacks

    /**
     * Applies the given entity ID to the given ItemStack's NBT data.
     */
    public static void applyEntityIdToItemStack(ItemStack stack, ResourceLocation entityId) {
        NBTTagCompound nbttagcompound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        assert nbttagcompound != null;
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setString("id", entityId.toString());
        nbttagcompound.setTag("EntityTag", nbttagcompound1);
        stack.setTagCompound(nbttagcompound);
    }
}
