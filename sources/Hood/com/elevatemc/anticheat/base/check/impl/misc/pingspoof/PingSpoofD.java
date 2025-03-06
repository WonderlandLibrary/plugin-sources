package com.elevatemc.anticheat.base.check.impl.misc.pingspoof;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import org.bukkit.GameMode;

public class PingSpoofD extends PacketCheck {

    private Long lastKeepAlive = 0L, lastFlying = 0L;

    public PingSpoofD(PlayerData playerData) {
        super(playerData, "Ping Spoof D", "Invalid differences", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.PING_SPOOF, 100);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {
            if (keepAliveTracker.isAcceptedKeepAlive() && keepAliveTracker.getKeepAlivePing() != -1) {
                long sinceFlying = System.currentTimeMillis() - lastFlying;
                long keepAlivePing = keepAliveTracker.getKeepAlivePing();

                boolean invalid = sinceFlying - keepAlivePing > 100 && lastKeepAlive > 15;
                boolean exempt = playerData.getPlayer().getGameMode().equals(GameMode.SPECTATOR);

                if (invalid && !exempt) {
                    if (increaseBuffer() > 5) {
                        handleViolation(new DetailedPlayerViolation(this, ""));
                        staffAlert(new PlayerViolation(this));
                    }
                } else {
                    decreaseBuffer();
                }
            }
            lastKeepAlive = System.currentTimeMillis();
        }else if (PacketUtil.isFlying(event.getPacketType())) {
            lastFlying = System.currentTimeMillis();
        }
    }
}
