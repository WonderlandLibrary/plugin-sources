package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class SpeedH extends PacketCheck {

    public SpeedH(PlayerData playerData) {
        super(playerData, "Speed H", "Invalid speed appliance", new ViolationHandler(10, 300), Category.MOVEMENT, SubCategory.SPEED,3);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {
            boolean sprinting = actionTracker.isSprinting();

            double deltaX = positionTracker.getDeltaX();
            double deltaZ = positionTracker.getDeltaZ();

            double deltaXZ = positionTracker.getDeltaXZ();
            double lastDeltaX = positionTracker.getLastDeltaX();
            double lastDeltaZ = positionTracker.getLastDeltaZ();

            int airTicks = collisionTracker.getServerAirTicks();

            double blockSlipperiness = 0.91F;
            double attributeSpeed = sprinting ? 0.026 : 0.02;

            double predictedDeltaX = lastDeltaX * blockSlipperiness + attributeSpeed;
            double predictedDeltaZ = lastDeltaZ * blockSlipperiness + attributeSpeed;
            double diffX = deltaX - predictedDeltaX;
            double diffZ = deltaZ - predictedDeltaZ;

            boolean exempt = Hood.get().isLagging()
                    || collisionTracker.isOnClimbable()
                    || collisionTracker.isInWater()
                    || collisionTracker.isInLava()
                    || collisionTracker.isVerticallyColliding()
                    || playerData.getPlayer().getAllowFlight();

            boolean invalid = (diffX > 0.01 || diffZ > 0.01) && deltaXZ > 0.175 && airTicks > 2;
            if (!positionTracker.isSentMotion()) return;

            if (invalid && !exempt) {
                if (increaseBuffer() > 5.0) {
                    handleViolation(new DetailedPlayerViolation(this,"X " + format(diffX) + " T " + airTicks));
                }
            } else {
                decreaseBufferBy(0.1);
            }
        }
    }
}
