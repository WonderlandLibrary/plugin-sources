package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;
import org.bukkit.GameMode;

public class BadPacketsL extends PacketCheck {

    public BadPacketsL(PlayerData playerData) {
        super(playerData, "Bad Packets L", "Placed a block from too far away", new ViolationHandler(2, 3000), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT) {
            final WrapperPlayClientPlayerBlockPlacement wrapper = new WrapperPlayClientPlayerBlockPlacement(event);

            if (wrapper.getBlockPosition().getX() == -1 && wrapper.getBlockPosition().getZ() == -1) return;

            final double distanceX = wrapper.getBlockPosition().getX() - positionTracker.getX();
            final double distanceZ = wrapper.getBlockPosition().getZ() - positionTracker.getZ();
            final double distanceXZ = MathUtil.hypot(distanceX, distanceZ);
            final boolean exempt = teleportTracker.isTeleport() || Hood.get().isLagging() || playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE);

            if (distanceXZ > 6.5 && distanceXZ < 50 && !exempt) {
                if (increaseBuffer() > 2) {
                    handleViolation(new DetailedPlayerViolation(this,"D " + distanceXZ));
                    staffAlert(new PlayerViolation(this));
                    multiplyBuffer(.25);
                }
            } else {
                decreaseBufferBy(.25);
            }
        }
    }
}
