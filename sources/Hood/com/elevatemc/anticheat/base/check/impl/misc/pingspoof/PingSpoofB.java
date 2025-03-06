package com.elevatemc.anticheat.base.check.impl.misc.pingspoof;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

public class PingSpoofB extends PacketCheck {


    public PingSpoofB(PlayerData playerData) {
        super(playerData, "Blink", "Trying to cook.", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.TIMER, 3);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {

            if (playerData.getTicksExisted() < 100 || teleportTracker.isTeleport() ||
                    playerData.getPlayer().isDead() || playerData.getPlayer().isInsideVehicle()) return;

            long flyingDelay = keepAliveTracker.getFlyingDelay();
            long keepAlivePing = keepAliveTracker.getKeepAlivePing();

            if (keepAlivePing - 150 > 100 && flyingDelay <= 1) {
                if (increaseBuffer() > 5) {
                    handleViolation(new DetailedPlayerViolation(this, "K " + keepAlivePing + " D " + flyingDelay));
                }
            } else {
                decreaseBufferBy(0.25);
            }
        }
    }
}
