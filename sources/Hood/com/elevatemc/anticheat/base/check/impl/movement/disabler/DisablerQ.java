package com.elevatemc.anticheat.base.check.impl.movement.disabler;


import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;

import java.util.Deque;

public class DisablerQ extends PacketCheck {

    private final Deque<Teleport> teleports = Lists.newLinkedList();
    public DisablerQ(PlayerData playerData) {
        super(playerData, "Disabler N", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            final double x = positionTracker.getX();
            final double y = positionTracker.getY();
            final double z = positionTracker.getZ();
            final Teleport latest = this.teleports.peek();
            if (latest != null && latest.getX() == x && latest.getY() == y && latest.getZ() == z) {
                this.teleports.poll();
            }
            if (this.teleports.stream().filter(teleport -> playerData.getTicksExisted() - teleport.getTick() > 600).count() > 3L) {
                Bukkit.getScheduler().runTask(Hood.get(), () -> playerData.getPlayer().kickPlayer("Lost location"));
            }
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.PLAYER_POSITION_AND_LOOK) {
            WrapperPlayServerPlayerPositionAndLook wrapper = new WrapperPlayServerPlayerPositionAndLook(event);

            teleports.add(new Teleport(playerData.getTicksExisted(), wrapper.getX(), wrapper.getY(), wrapper.getZ()));
        }
    }

    private static final class Teleport
    {
        private final int tick;
        private final double x;
        private final double y;
        private final double z;

        public Teleport(final int tick, final double x, final double y, final double z) {
            this.tick = tick;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getTick() {
            return this.tick;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }

        public double getZ() {
            return this.z;
        }
    }
}

