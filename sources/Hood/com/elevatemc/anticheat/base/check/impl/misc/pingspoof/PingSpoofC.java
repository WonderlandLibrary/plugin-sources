package com.elevatemc.anticheat.base.check.impl.misc.pingspoof;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import org.bukkit.Bukkit;

public class PingSpoofC extends PacketCheck {

    long lastAccept = 0L;
    public PingSpoofC(PlayerData playerData) {
        super(playerData, "Ping Spoof C", "Response check.", new ViolationHandler(1, 30000L), Category.MISC, SubCategory.PING_SPOOF, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
         if (event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION) {
             if (teleportTracker.isJoined()) return;
             if (teleportTracker.isTeleport()) return;
            // I could do this check on serverTick as it's more efficient but eh idc.
             long keepAlive = keepAliveTracker.getLastKeepAlive();

             long delay = System.currentTimeMillis() - keepAlive;

             if (delay > 30000L) {
                 handleViolation(new DetailedPlayerViolation(this, "DK " + delay));
                 Bukkit.getScheduler().runTask(Hood.get(), () -> playerData.getPlayer().kickPlayer("Timed out"));
             }

             lastAccept = System.currentTimeMillis();
        }
    }
}

