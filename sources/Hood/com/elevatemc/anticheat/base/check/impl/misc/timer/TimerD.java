package com.elevatemc.anticheat.base.check.impl.misc.timer;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;

public class TimerD extends PacketCheck {

    private final EvictingList<Long> samples = new EvictingList<>(50);
    private long lastFlying;

    public TimerD(PlayerData playerData) {
        super(playerData, "Timer D", "Tampered game speed", new ViolationHandler(20, 300), Category.MISC, SubCategory.TIMER, 3);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            long delay = System.currentTimeMillis() - lastFlying;

            boolean exempt = Hood.get().isLagging() || teleportTracker.getSinceTeleportTicks() < 5 || keepAliveTracker.getKeepAlivePing() > 530;

            add: {
                if (delay < 5 || exempt) break add;

                samples.add(delay);
            }

            if (samples.isFull()) {
                final double average = MathUtil.getAverage(samples);
                final double deviation = MathUtil.getStandardDeviation(samples);
                final double speed = 50 / average;


                if (speed > 1.01 && deviation < 1.1) {
                    if (increaseBuffer() > 35) {
                        handleViolation(new DetailedPlayerViolation(this,"S " + format(speed * 100) + "%"));
                        staffAlert(new PlayerViolation(this));
                        multiplyBuffer(.25);
                    }
                } else {
                    decreaseBufferBy(2.25);
                }
            }
            lastFlying = System.currentTimeMillis();
        }
    }

}
