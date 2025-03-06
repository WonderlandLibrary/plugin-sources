package com.elevatemc.anticheat.base.check.impl.movement.ground;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import org.bukkit.GameMode;

public class GroundA extends PositionCheck {

    public GroundA(PlayerData playerData) {
        super(playerData, "Ground A", "Checks for groundspoof", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.GROUND, 7);
    }

    @Override
    public void handle() {

        boolean exempt =  playerData.getPlayer().getAllowFlight()
                || Hood.get().isLagging()
                || playerData.getPlayer().isInsideVehicle()
                || collisionTracker.isInWeb()
                || collisionTracker.isInWater()
                || collisionTracker.isOnIce()
                || collisionTracker.isLastOnIce()
                || teleportTracker.isTeleport()
                || collisionTracker.isOnFence()
                || collisionTracker.isLastOnFence()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastLastInWater()
                || collisionTracker.isLastLastInLava()
                || !playerData.getPlayer().getGameMode().equals(GameMode.SURVIVAL);

        // Get the players' fall distance.
        double fallDistance = playerData.getPlayer().getFallDistance();
        // Server Ticks in the air.
        int airTicks = collisionTracker.getServerAirTicks();
        // Packet to confirm they were on the ground.
        boolean onGround = collisionTracker.isClientGround();
        boolean capping = fallDistance > 1 && airTicks > 0 && onGround;

        if (exempt) {
            airTicks = 0;
        }

        if (!exempt) {
            if (capping) {
             handleViolation(new DetailedPlayerViolation(this, "T " + airTicks + " D 0" ));
             staffAlert(new PlayerViolation(this));
            }
        }
    }
}