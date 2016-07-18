package cad97.spawnercraft.items;

import cad97.spawnercraft.init.SpawnerCraftMobAlias;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Base class for mobEssence, mobAgglomeration, and mobSpirit
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
        String mobName = ItemMonsterPlacer.getEntityIdFromItem(stack);

        if (mobName != null) {
            mobName = I18n.translateToLocal("entity." + mobName + ".name");
        }

        return String.format(itemName, mobName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull Item item, @Nonnull CreativeTabs tab, @Nonnull List<ItemStack> subItems) {
        for (EntityList.EntityEggInfo egg : EntityList.ENTITY_EGGS.values()) {
            ItemStack stack = new ItemStack(item);
            ItemMobSoul.applyEntityIdToItemStack(stack, egg.spawnedID);
            subItems.add(stack);
        }
        for (String id : SpawnerCraftMobAlias.customEggs.keySet()) {
            ItemStack stack = new ItemStack(item);
            NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
            assert nbt != null;
            nbt.setTag("EntityTag", SpawnerCraftMobAlias.customNBT.get(id));
            ((NBTTagCompound) nbt.getTag("EntityTag")).setString("id", id);
            stack.setTagCompound(nbt);
            subItems.add(stack);
        }
    }

    // Stolen from @SideOnly(CLIENT) in ItemMonsterPlacer. I need to access it on server side
    // to enable applying entity id to dropped item stacks
    public static void applyEntityIdToItemStack(ItemStack stack, String entityId) {
        NBTTagCompound nbttagcompound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        assert nbttagcompound != null;
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setString("id", entityId);
        nbttagcompound.setTag("EntityTag", nbttagcompound1);
        stack.setTagCompound(nbttagcompound);
    }
}
