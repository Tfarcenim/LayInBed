package tfar.layinbed.platform;

import net.minecraft.server.level.ServerPlayer;
import tfar.layinbed.network.PacketHandlerForge;
import tfar.layinbed.network.client.S2CModPacket;
import tfar.layinbed.network.server.C2SModPacket;
import tfar.layinbed.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public void sendToClient(S2CModPacket msg, ServerPlayer player) {
        PacketHandlerForge.sendToClient(msg,player);
    }

    @Override
    public void sendToServer(C2SModPacket msg) {
        PacketHandlerForge.sendToServer(msg);
    }
}