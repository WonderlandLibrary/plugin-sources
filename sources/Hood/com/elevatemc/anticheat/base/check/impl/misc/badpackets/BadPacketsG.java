package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import org.bukkit.GameMode;

public class BadPacketsG extends PacketCheck {

    public BadPacketsG(PlayerData playerData) {
        super(playerData, "Bad Packets G", "Invalid placement", new ViolationHandler(1, 3000L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.HELD_ITEM_CHANGE) {
            final boolean invalid = actionTracker.isBlocking();

            final boolean exempt = Hood.get().isLagging() || teleportTracker.isTeleport() || playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE);

            if (invalid && !exempt) {
                handleViolation(new DetailedPlayerViolation(this,""));
                staffAlert(new PlayerViolation(this));
            }
        }
    }
}
