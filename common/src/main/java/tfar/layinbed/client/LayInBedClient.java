package tfar.layinbed.client;

import net.minecraft.client.Minecraft;
import tfar.layinbed.PlayerDuck;

public class LayInBedClient {

    public static void handleSync(boolean actuallySleeping) {
        PlayerDuck.of(Minecraft.getInstance().player).setActuallySleeping(actuallySleeping);
    }

}
