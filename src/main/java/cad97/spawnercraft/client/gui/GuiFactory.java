package cad97.spawnercraft.client.gui;

import cad97.spawnercraft.SpawnerCraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.DefaultGuiFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public class GuiFactory extends DefaultGuiFactory {
    public GuiFactory() {
        super(SpawnerCraft.MOD_ID, SpawnerCraft.MOD_NAME);
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new SpawnerCraftGuiConfig(parentScreen);
    }
}
