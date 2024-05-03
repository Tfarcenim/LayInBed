package tfar.layinbed.platform;

import net.minecraft.server.level.ServerPlayer;
import tfar.layinbed.network.client.S2CModPacket;
import tfar.layinbed.network.server.C2SModPacket;
import tfar.layinbed.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void sendToClient(S2CModPacket msg, ServerPlayer player) {

    }

    @Override
    public void sendToServer(C2SModPacket msg) {

    }
}
