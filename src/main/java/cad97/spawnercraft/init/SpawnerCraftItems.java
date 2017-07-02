package cad97.spawnercraft.init;

import cad97.spawnercraft.items.ItemMobAgglomeration;
import cad97.spawnercraft.items.ItemMobEssence;
import cad97.spawnercraft.items.ItemMobRod;
import cad97.spawnercraft.items.ItemMobSpirit;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpawnerCraftItems {

    public static final ItemMobEssence MOB_ESSENCE = new ItemMobEssence();
    @SuppressWarnings("WeakerAccess")
    public static final ItemMobAgglomeration MOB_AGGLOMERATION = new ItemMobAgglomeration();
    @SuppressWarnings("WeakerAccess")
    public static final ItemMobSpirit MOB_SPIRIT = new ItemMobSpirit();
    public static final ItemMobRod MOB_ROD = new ItemMobRod();

    public static void registerItems() {
        GameRegistry.register(MOB_ESSENCE);
        GameRegistry.register(MOB_AGGLOMERATION);
        GameRegistry.register(MOB_SPIRIT);
        GameRegistry.register(MOB_ROD);
        GameRegistry.register(new ItemBlock(SpawnerCraftBlocks.MOB_CAGE), SpawnerCraftBlocks.MOB_CAGE.getRegistryName());
        LogHelper.logInfo("Items initialized.");
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("ConstantConditions")
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(MOB_ESSENCE, 0,
                new ModelResourceLocation(MOB_ESSENCE.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(MOB_AGGLOMERATION, 0,
                new ModelResourceLocation(MOB_AGGLOMERATION.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(MOB_SPIRIT, 0,
                new ModelResourceLocation(MOB_SPIRIT.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(MOB_ROD, 0,
                new ModelResourceLocation(MOB_ROD.getRegistryName(), "inventory"));
        LogHelper.logInfo("Item models initialized.");
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors(ItemColors itemColors) {
        itemColors.registerItemColorHandler((stack, tintIndex) -> {
            EntityList.EntityEggInfo eggInfo = EntityList.ENTITY_EGGS.get(ItemMonsterPlacer.getNamedIdFrom(stack));
            return eggInfo == null ? -1 : (tintIndex == 0 ? eggInfo.primaryColor : eggInfo.secondaryColor);
        }, MOB_ESSENCE, MOB_AGGLOMERATION, MOB_SPIRIT);
        LogHelper.logInfo("Item colors initialized.");
    }
}
