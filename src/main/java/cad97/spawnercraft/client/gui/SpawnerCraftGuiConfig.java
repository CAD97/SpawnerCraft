package cad97.spawnercraft.client.gui;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.handler.ConfigHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

@SuppressWarnings("WeakerAccess")
public class SpawnerCraftGuiConfig extends GuiConfig {
    public SpawnerCraftGuiConfig(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                SpawnerCraft.MOD_ID,
                SpawnerCraft.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
