package tfar.layinbed.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import tfar.layinbed.LayInBed;
import tfar.layinbed.PlayerDuck;

public class C2SSetActuallySleepingPacket implements C2SModPacket {


    public C2SSetActuallySleepingPacket() {
    }

    public C2SSetActuallySleepingPacket(FriendlyByteBuf buf) {
    }

    @Override
    public void handleServer(ServerPlayer player) {
        PlayerDuck playerDuck = PlayerDuck.of(player);
        playerDuck.setActuallySleeping(true);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
    }

    static final ResourceLocation ID = new ResourceLocation(LayInBed.MOD_ID,"set_actually_sleeping");
    @Override
    public ResourceLocation id() {
        return ID;
    }

}
