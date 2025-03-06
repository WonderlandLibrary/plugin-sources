package com.elevatemc.anticheat.base.check.impl.movement.scaffold;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;

public class ScaffoldD extends PacketCheck {

    public ScaffoldD(PlayerData playerData) {
        super(playerData, "Scaffold D", "Invalid acceleration whilst placing blocks", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SCAFFOLD, 2);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT) {

            if (!playerData.getPlayer().getItemInHand().getType().isBlock()) return;
            if (!actionTracker.isSneaking() || !playerData.getPlayer().isSneaking()) return;


            double acceleration = positionTracker.getAcceleration();

            if (acceleration > 0.06) {
                if (increaseBuffer() > 7) {
                    setBuffer(1);
                    handleViolation(new DetailedPlayerViolation(this, "A " + acceleration));
                }
            } else {
                decreaseBufferBy(2.0);
            }
        }
    }
}
