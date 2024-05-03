package tfar.layinbed.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public interface ModPacket {
    void write(FriendlyByteBuf to);
    ResourceLocation id();
}
