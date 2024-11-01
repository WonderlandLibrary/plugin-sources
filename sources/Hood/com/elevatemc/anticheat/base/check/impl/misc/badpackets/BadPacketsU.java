package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.google.common.collect.Lists;

import java.util.Deque;

public class BadPacketsU extends PacketCheck {
    private final Deque<Long> samples = Lists.newLinkedList();

    public BadPacketsU(PlayerData playerData) {
        super(playerData, "Bad Packets U", "CPS!", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.BAD_PACKETS, 0);
    }



    @Override
    public void handle(final PacketReceiveEvent packet) {

        if (packet.getPacketType() == PacketType.Play.Client.ANIMATION) {
            boolean exempt = actionTracker.isDigging();

            check: {
                if (exempt) break check;

                final long delay = swingTracker.getDelay();

                samples.add(delay);

                if (samples.size() == 20) {
                    final double cps = MathUtil.getCps(samples);

                    if (cps > 19.0 && cps < 50) {
                        handleViolation(new DetailedPlayerViolation(this,"cps=" + format(cps)));
                    }
                    samples.clear();
                }
            }
        }
    }
}
