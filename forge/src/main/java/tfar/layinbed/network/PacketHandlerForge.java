package tfar.layinbed.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import tfar.layinbed.LayInBed;
import tfar.layinbed.network.client.S2CModPacket;
import tfar.layinbed.network.client.S2CSyncActuallySleepingPacket;
import tfar.layinbed.network.server.C2SModPacket;
import tfar.layinbed.network.server.C2SSetActuallySleepingPacket;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketHandlerForge {

    public static SimpleChannel INSTANCE;
    static int i = 0;

    public static void registerMessages() {

        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(LayInBed.MOD_ID, LayInBed.MOD_ID), () -> "1.0", s -> true, s -> true);

        serverMessage(C2SSetActuallySleepingPacket.class, C2SSetActuallySleepingPacket::write, C2SSetActuallySleepingPacket::new);


        clientMessage(S2CSyncActuallySleepingPacket.class, S2CSyncActuallySleepingPacket::write, S2CSyncActuallySleepingPacket::new);
    }

    public static <MSG extends S2CModPacket> void clientMessage(Class<MSG> msgClass, BiConsumer<MSG, FriendlyByteBuf> writer, Function<FriendlyByteBuf,MSG> reader) {
        INSTANCE.registerMessage(i++, msgClass, writer, reader, wrapS2C());
    }

    public static <MSG extends C2SModPacket> void serverMessage(Class<MSG> msgClass, BiConsumer<MSG, FriendlyByteBuf> writer, Function<FriendlyByteBuf,MSG> reader) {
        INSTANCE.registerMessage(i++, msgClass, writer, reader, wrapC2S());
    }

    private static <MSG extends S2CModPacket> BiConsumer<MSG, Supplier<NetworkEvent.Context>> wrapS2C() {
        return ((msg, contextSupplier) -> {
            contextSupplier.get().enqueueWork(msg::handleClient);
            contextSupplier.get().setPacketHandled(true);
        });
    }

    private static <MSG extends C2SModPacket> BiConsumer<MSG, Supplier<NetworkEvent.Context>> wrapC2S() {
        return ((msg, contextSupplier) -> {
            ServerPlayer player = contextSupplier.get().getSender();
            contextSupplier.get().enqueueWork(() -> msg.handleServer(player));
            contextSupplier.get().setPacketHandled(true);
        });
    }

    public static <MSG> void sendToClient(MSG packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void sendToServer(MSG packet) {
        INSTANCE.sendToServer(packet);
    }
}
