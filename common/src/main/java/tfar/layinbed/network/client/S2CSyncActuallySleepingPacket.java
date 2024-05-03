package tfar.layinbed.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import tfar.layinbed.LayInBed;
import tfar.layinbed.client.LayInBedClient;

public class S2CSyncActuallySleepingPacket implements S2CModPacket {


    private final boolean actuallySleeping;

    public S2CSyncActuallySleepingPacket(boolean actuallySleeping) {
        this.actuallySleeping = actuallySleeping;
    }

    public S2CSyncActuallySleepingPacket(FriendlyByteBuf buf) {
        actuallySleeping = buf.readBoolean();
    }

    @Override
    public void handleClient() {
        LayInBedClient.handleSync(actuallySleeping);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(actuallySleeping);
    }

    static final ResourceLocation ID = new ResourceLocation(LayInBed.MOD_ID,"sync");

    @Override
    public ResourceLocation id() {
        return ID;
    }
}