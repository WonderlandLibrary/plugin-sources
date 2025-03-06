package com.elevatemc.anticheat.base.check.impl.misc.prediction;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

public class PredictionC extends PacketCheck {

    public PredictionC(PlayerData playerData) {
        super(playerData, "Velocity Bruteforce", "", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.SIMULATION, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (!PacketUtil.isFlying(event.getPacketType())) return;

        int tick = velocityTracker.getTicksSinceVelocity();

        velocity: {
            boolean exempt = playerData.getTicksExisted() < 100 || playerData.getPlayer().getAllowFlight()
                    || collisionTracker.isOnSoulSand();
            boolean verify = velocityTracker.canVerifyVelocity();
            if (exempt || !verify) break velocity;
            if (tick > 5) break velocity;

            double distance = predictionTracker.getDistance();

            double combined = 0;

            switch (tick) {
                case 1: {
                    combined += distance;
                }
                case 2: {
                    combined += distance;
                }
                case 3: {
                    combined += distance;
                }
                case 4: {
                    combined += distance;
                }
                case 5: {
                    combined += distance;
                    break;
                }
            }


            if (distance != Double.MAX_VALUE && distance > 1.0E-7 && increaseBuffer() > 3) {
                debug("distance " + distance + " T " + combined);
                decreaseBufferBy(2);
            }
        }
    }
}
