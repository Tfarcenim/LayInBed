package tfar.layinbed.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import tfar.layinbed.network.server.C2SSetActuallySleepingPacket;
import tfar.layinbed.platform.Services;

public class LayInBedClientForge {

    public static void setup() {
        MinecraftForge.EVENT_BUS.addListener(LayInBedClientForge::addButton);
    }

    static void addButton(ScreenEvent.Init event) {
        Screen screen = event.getScreen();
        if (screen instanceof InBedChatScreen inBedChatScreen) {
            event.addListener(Button.builder(Component.literal("Sleep till Dawn"),pButton -> {
                Services.PLATFORM.sendToServer(new C2SSetActuallySleepingPacket());
            }).bounds(inBedChatScreen.width / 2 - 100, inBedChatScreen.height - 70, 200, 20).build());
        }
    }
}
