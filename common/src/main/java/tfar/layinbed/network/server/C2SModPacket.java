package tfar.layinbed.network.server;

import net.minecraft.server.level.ServerPlayer;
import tfar.layinbed.network.ModPacket;

public interface C2SModPacket extends ModPacket {

    void handleServer(ServerPlayer player);

}
