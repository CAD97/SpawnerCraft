package cad97.spawnercraftkt.proxy

import cad97.spawnercraftkt.init.SpawnerCraftBlocks
import cad97.spawnercraftkt.init.SpawnerCraftItems
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.event.FMLInitializationEvent

class ClientProxy : Proxy() {
    companion object {
        // ClientProxy::class.java.qualifiedName
        const val qualifiedName = "cad97.spawnercraftkt.proxy.ClientProxy"
    }

    override fun init(event: FMLInitializationEvent) {
        super.init(event)
        SpawnerCraftItems.registerColors(Minecraft.getMinecraft().itemColors)
        SpawnerCraftBlocks.registerColors(Minecraft.getMinecraft().itemColors, Minecraft.getMinecraft().blockColors)
    }
}
