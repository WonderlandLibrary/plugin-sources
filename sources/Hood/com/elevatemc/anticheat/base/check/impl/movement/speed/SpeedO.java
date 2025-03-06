package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;
import org.bukkit.GameMode;

public class SpeedO extends PositionCheck {

    public SpeedO(PlayerData playerData) {
        super(playerData, "Speed O", "Breached limit", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SPEED, 0);
    }


    @Override
    public void handle() {

        boolean exempt = velocityTracker.getTicksSinceVelocity() < 5
                || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE)
                || teleportTracker.isTeleport()
                || playerData.getPlayer().isInsideVehicle();

        if (exempt) return;

        double xzChange = positionTracker.getDeltaXZ() - positionTracker.getLastDeltaXZ();
        double baseLimit = PlayerUtil.getBaseSpeed(playerData);

        //debug("Change " + xzChange + " limit " + baseLimit);

        if (xzChange > baseLimit) {
            if (increaseBuffer() > 5) handleViolation(new DetailedPlayerViolation(this, "C " + xzChange + " L " + baseLimit));
        } else {
            decreaseBufferBy(1.5);
        }
    }
}
