package com.elevatemc.anticheat.base.check.impl.misc.refill;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.google.common.collect.Lists;

import java.util.Deque;


public class RefillC extends PacketCheck {

    private final Deque<Long> samples = Lists.newLinkedList();
    private final EvictingList<Long> delays = new EvictingList<>(5);
    private long lastClick;

    public RefillC(PlayerData playerData) {
        super(playerData, "Refill C", "ay! ay? where the problem at", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.REFILL, 1);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow wrapper = new WrapperPlayClientClickWindow(event);
            if (wrapper.getWindowId() == 0 && wrapper.getSlot() >= 9 && wrapper.getSlot() <= 35 && wrapper.getButton() == 0 && wrapper.getWindowClickType() == WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE) {

                final long now = System.currentTimeMillis();
                long delay = now - this.lastClick;
                this.lastClick = now;

                if (delay > 5000L) {
                    return;
                }

                samples.add(delay);

                if (samples.size() >= 5) {
                    delays.addAll(samples);
                    double average = MathUtil.getAverage(delays);
                    double dev = MathUtil.getStandardDeviation(delays);
                    if (average > 0) {
                        double cps = MathUtil.getCps(samples);
                        samples.clear();
                        delays.clear();
                        if (Double.isInfinite(cps)) return;
                        if (average >= 30 && cps > 7.5 && dev < 8) {
                            handleViolation(new DetailedPlayerViolation(this, "DEV " + format(dev) + " C " + format(cps)));
                        }
                    }
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            delays.clear();
            samples.clear();
        }
    }
}
