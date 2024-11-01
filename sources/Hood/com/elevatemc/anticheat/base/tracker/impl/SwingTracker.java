package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
@Getter
@Setter
public class SwingTracker extends Tracker {

    private long delay, lastSwing;
    private double cps;
    private final Deque<Long> samples = Lists.newLinkedList();
    public SwingTracker(PlayerData playerData) {
        super(playerData);
    }
    @Override
    public void registerIncomingPostHandler(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ANIMATION) {
            long now = System.currentTimeMillis();
            delay = now - lastSwing;
            lastSwing = now;
            if (delay > 5000L) return;

            samples.add(delay);

            if (samples.size() == 20) {
                this.cps = MathUtil.getCPS(samples);
                samples.clear();
            }
        }
    }
}
