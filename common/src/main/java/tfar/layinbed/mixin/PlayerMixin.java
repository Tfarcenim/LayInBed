package tfar.layinbed.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import tfar.layinbed.PlayerDuck;
import tfar.layinbed.network.client.S2CSyncActuallySleepingPacket;
import tfar.layinbed.platform.Services;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerDuck {

    boolean actuallySleeping;

    protected PlayerMixin(EntityType<? extends LivingEntity> $$0, Level $$1) {
        super($$0, $$1);
    }

    @Override
    public boolean actuallySleeping() {
        return actuallySleeping;
    }

    @Override
    public void setActuallySleeping(boolean layingInBed) {
        if (!this.level().isClientSide && layingInBed != actuallySleeping) {
            Services.PLATFORM.sendToClient(new S2CSyncActuallySleepingPacket(layingInBed),(ServerPlayer)(Object)this);
        }
        this.actuallySleeping = layingInBed;

    }
}