package com.elevatemc.anticheat.base.check.impl.movement.ground;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class GroundC extends PositionCheck {

    public GroundC(PlayerData playerData) {
        super(playerData, "Ground C", "Spoofed ground packets", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.GROUND, 0);
    }

    @Override
    public void handle() {
        boolean clientGround = collisionTracker.isClientGround();
        boolean serverGround = collisionTracker.isServerGround();

        boolean exempt = playerData.getPlayer().getAllowFlight();

        if (exempt) return;

        if (clientGround && !serverGround || !clientGround && serverGround) {
            if (increaseBuffer() > 30) {
                handleViolation(new DetailedPlayerViolation(this, "C " + clientGround + " S " + serverGround));
                staffAlert(new PlayerViolation(this));
                decreaseBufferBy(10);
            }
        } else {
            resetBuffer();
        }
    }
}
