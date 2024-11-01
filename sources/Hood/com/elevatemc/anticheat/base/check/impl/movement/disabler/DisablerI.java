package com.elevatemc.anticheat.base.check.impl.movement.disabler;


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
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import de.gerrygames.viarewind.utils.math.Vector3d;
import org.bukkit.GameMode;


public class DisablerI extends PacketCheck {

    private Long lastFlag = 0L;
    private int streak = 0;
    public DisablerI(PlayerData playerData) {
        super(playerData, "Disabler I", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.DISABLER, 5);
    }

    /*
        Yes this check can false and I will get to fixing it, thing is this check works are the following:
        When a player sends a PLAYER_POSITION_AND_ROTATION packet, teleporting, etc.
        Their deltas reset to 0, everything resets to 0, now, what these cringe fucks are doing is they
        abuse teleport packets so they can bypass movement checks, what I'm doing here is checking if
        their deltas are not equal to 0, any single one of them.
        This check can false if you get block glitched tp'd.
        Enderpearl (some cases)
        But it doesn't flood, whenever it gets flooded, the player is cheating.
        Cringe as shit yes I know.

        I'll be countering this (hopefully doesn't kill the check) with a streak.
        Another way to make this check is to check if they're sending a rotation packet, and their deltaYaw & deltaPitch
        Combined are equal to 0.0, of course, we will check if they're in fight.
        With this "streak" The false flags aren't happening.
     */

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {

            Vector3d teleportPosition = teleportTracker.getTeleports().peek();
            if (teleportPosition != null && event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {
                if (playerData.getPlayer().getAllowFlight() || playerData.getPlayer().getGameMode().equals(GameMode.SPECTATOR) || teleportTracker.getSinceTeleportTicks() > 10) return;
                WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerPositionAndRotation(event);
                double x = wrapper.getLocation().getX();
                double y = wrapper.getLocation().getY();
                double z = wrapper.getLocation().getZ();

                double differenceX = Math.abs(x - teleportPosition.getX());
                double differenceY = Math.abs(y - teleportPosition.getY());
                double differenceZ = Math.abs(z - teleportPosition.getZ());

                boolean closeTeleport = Math.abs(differenceX)  < 0.03125D &&  Math.abs(differenceY) < 0.015625D &&  Math.abs(differenceZ) < 0.03125D;

                if (closeTeleport) return;

                if (System.currentTimeMillis() - lastFlag < 2000L) {
                    streak++;

                    if (streak > 15) {
                        handleViolation(new DetailedPlayerViolation(this, "S " + streak + " X " + format(differenceX) + " Y " + format(differenceY) + " Z " + format(z)));
                        staffAlert(new PlayerViolation(this));
                        // reset.
                        streak = 5;
                    }
                } else {
                    streak = 0;
                }

                if (differenceX != 0.0 || differenceY != 0.0 || differenceZ != 0.0) {
                    if (increaseBuffer() > 4) {
                        lastFlag = System.currentTimeMillis();
                    }
                } else {
                    resetBuffer();
                }
            }
        }
    }
}