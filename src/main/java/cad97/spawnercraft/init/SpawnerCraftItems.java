package cad97.spawnercraft.init;

import cad97.spawnercraft.items.ItemMobEssence;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class SpawnerCraftItems {

    public static final ItemMobEssence mobEssence = new ItemMobEssence();

    public static void registerItems() {
        // items
        GameRegistry.register(mobEssence);
        // block items
        GameRegistry.register(new ItemBlock(SpawnerCraftBlocks.mobCage), SpawnerCraftBlocks.mobCage.getRegistryName());
        LogHelper.logInfo("Items initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(mobEssence,
                0, new ModelResourceLocation(mobEssence.getRegistryName(), "inventory"));
        LogHelper.logInfo("Item models initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors(ItemColors itemColors) {
        itemColors.registerItemColorHandler(new IItemColor() {
            public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
                EntityList.EntityEggInfo eggInfo = EntityList.ENTITY_EGGS.get(ItemMonsterPlacer.getEntityIdFromItem(stack));
                return eggInfo == null ? -1 : (tintIndex == 0 ? eggInfo.primaryColor : eggInfo.secondaryColor);
            }
        }, SpawnerCraftItems.mobEssence);
    }
}
