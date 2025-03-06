package com.elevatemc.anticheat.base.check.impl.misc.refill;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class RefillE1 extends PacketCheck {

    private Long lastFlying = 0L;
    private EvictingList<Long> samples = new EvictingList<>(10);
    public RefillE1(PlayerData playerData) {
        super(playerData, "Refill E1", "Invalid average times", new ViolationHandler(10, 30000L), Category.MISC, SubCategory.REFILL, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            lastFlying = System.currentTimeMillis();
        } else if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            long delay = System.currentTimeMillis() - lastFlying;

            check: {
                if (teleportTracker.isTeleport() || keepAliveTracker.getKeepAlivePing() > 400) break check;
                if (delay > 5000) break check;

                samples.add(delay);

                if (samples.isFull()) {
                    double average = MathUtil.getAverage(samples);

                    //debug("A: " + average);

                    // Legit impossible
                    if (average <= 35 && average > 2) {
                        if (increaseBuffer() > 4) {
                            handleViolation(new DetailedPlayerViolation(this, "A " + format(average)));
                        }
                    } else {
                        decreaseBufferBy(0.5D);
                    }
                    samples.clear();
                }
            }
        }
    }
}
