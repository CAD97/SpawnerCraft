package cad97.spawnercraft.client.gui;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.handler.ConfigHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class SpawnerCraftGuiConfig extends GuiConfig {
    public SpawnerCraftGuiConfig(GuiScreen guiScreen) {
        super(guiScreen,
                getConfigElements(),
                SpawnerCraft.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<>();
        elements.addAll(new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements());
        elements.addAll(new ConfigElement(ConfigHandler.config.getCategory("Custom Souls")).getChildElements());
        return elements;
    }
}
