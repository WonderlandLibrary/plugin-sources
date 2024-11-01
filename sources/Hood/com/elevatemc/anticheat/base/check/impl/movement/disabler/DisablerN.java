package com.elevatemc.anticheat.base.check.impl.movement.disabler;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAnimation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClientStatus;

public class DisablerN extends PacketCheck {

    public DisablerN(PlayerData playerData) {
        super(playerData, "Disabler N", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ANIMATION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION) {
            if (velocityTracker.getTicksSinceVelocity() <= 5) {

                boolean situationOne = positionTracker.getDelayedFlyingTicks() > 8;
                boolean situationTwo = predictionTracker.getDistance() == 0.0 || predictionTracker.getDistance() > 1;
                boolean situationThree = positionTracker.getX() + positionTracker.getY() + positionTracker.getZ() == 0.0;
                boolean situationFour = positionTracker.getDeltaY() < -3.92;

                if (situationOne) increaseBufferBy(2);
                if (situationThree) increaseBufferBy(2);
                if (situationTwo) increaseBuffer();
                if (situationFour) increaseBuffer();

                if (getBuffer() > 8) {
                    handleViolation(new DetailedPlayerViolation(this, "total: " + getBuffer()));
                }

            }
        }
    }
}
