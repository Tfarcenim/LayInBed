package tfar.layinbed;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tfar.layinbed.client.LayInBedClientForge;
import tfar.layinbed.mixin.PlayerAccess;
import tfar.layinbed.network.PacketHandlerForge;

@Mod(LayInBed.MOD_ID)
public class LayInBedForge {
    public LayInBedForge() {
        LayInBed.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.addListener(this::sleepingTimeEvent);
        MinecraftForge.EVENT_BUS.addListener(this::playerTick);
        MinecraftForge.EVENT_BUS.addListener(this::wakeup);
        if (FMLEnvironment.dist.isClient()) {
            LayInBedClientForge.setup();
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PacketHandlerForge.registerMessages();
    }

    private void wakeup(PlayerWakeUpEvent event) {
        Player player = event.getEntity();
        PlayerDuck playerDuck = PlayerDuck.of(player);
        playerDuck.setActuallySleeping(false);
    }
    private void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Player player = event.player;
            PlayerDuck playerDuck = PlayerDuck.of(player);
            if (!playerDuck.actuallySleeping()) {
                ((PlayerAccess)player).setSleepCounter(0);
            }
        }
    }

    private void sleepingTimeEvent(SleepingTimeCheckEvent event) {
        event.setResult(Event.Result.ALLOW);
    }
}