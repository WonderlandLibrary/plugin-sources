package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.GameMode;

public class MotionN extends PacketCheck {

    public MotionN(PlayerData playerData) {
        super(playerData, "Motion N", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            int clientAirTicks = collisionTracker.getClientAirTicks();
            int serverAirTicks = collisionTracker.getServerAirTicks();

            boolean exempt = playerData.getPlayer().getAllowFlight()|| teleportTracker.isTeleport()
                    || collisionTracker.isInWater() || collisionTracker.isInLava() || collisionTracker.isInWeb() || collisionTracker.isOnClimbable();

            boolean gamemode = playerData.getPlayer().getGameMode().equals(GameMode.SURVIVAL);

            if (exempt) resetBuffer();

            if (playerData.getPlayer().isInsideVehicle() || !playerData.getWorldTracker().isChunkLoaded(positionTracker.x, positionTracker.z)) {
                clientAirTicks = 0;
                serverAirTicks = 0;
            }

            if (clientAirTicks > 60 && serverAirTicks == 0 && !exempt && positionTracker.getY() > 4.0 && gamemode) {
                if (increaseBuffer() > 20) {
                    handleViolation(new DetailedPlayerViolation(this, "C " + clientAirTicks + " S " + serverAirTicks));
                    resetBuffer();
                }
            }
        }
    }
}
