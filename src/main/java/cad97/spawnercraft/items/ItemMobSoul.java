package cad97.spawnercraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Base class for mobEssence, mobAgglomeration, and mobSpirit
 */
abstract class ItemMobSoul extends SpawnerCraftItem {
    public ItemMobSoul() {
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
            ItemMonsterPlacer.applyEntityIdToItemStack(stack, egg.spawnedID);
            subItems.add(stack);
        }
    }
}
