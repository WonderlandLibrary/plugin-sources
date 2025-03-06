package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import org.bukkit.GameMode;

public class BadPacketsV extends PacketCheck {


    public BadPacketsV(PlayerData playerData) {
        super(playerData, "Bad Packets V", "Sprinting whilst in inventory", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.BAD_PACKETS, 3);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            if (velocityTracker.getTicksSinceVelocity() < 20) return;

            final double deltaXZ = positionTracker.getDeltaXZ();

            final boolean invalid = actionTracker.getSprintingTicks() > 10 && deltaXZ > 0.2;
            final boolean exempt = !playerData.getPlayer().getGameMode().equals(GameMode.SURVIVAL) || velocityTracker.isOnFirstTick();

            if (invalid && !exempt) {
                handleViolation(new DetailedPlayerViolation(this,""));
            }
        }
    }
}
