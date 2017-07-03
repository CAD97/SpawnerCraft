package cad97.spawnercraftkt.gui

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.SpawnerCraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.common.config.ConfigElement
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.client.DefaultGuiFactory
import net.minecraftforge.fml.client.config.GuiConfig

@Suppress("unused")
class GuiFactory : DefaultGuiFactory(SpawnerCraft.modid, SpawnerCraft.name) {
    override fun createConfigGui(parent: GuiScreen) =
            GuiConfig(
                    parent,
                    ConfigElement(Config.config.getCategory(Configuration.CATEGORY_GENERAL)).childElements,
                    SpawnerCraft.modid,
                    false,
                    false,
                    GuiConfig.getAbridgedConfigPath(Config.config.toString())
            )
}
