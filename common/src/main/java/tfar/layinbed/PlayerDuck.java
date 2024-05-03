package tfar.layinbed;

import net.minecraft.world.entity.player.Player;

public interface PlayerDuck {

    boolean actuallySleeping();
    void setActuallySleeping(boolean layingInBed);

    static PlayerDuck of(Player player) {
        return (PlayerDuck) player;
    }
}
