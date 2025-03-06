package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;

public class KillAuraL extends PacketCheck {

    private int lastID = -1;
    public KillAuraL(PlayerData playerData) {
        super(playerData, "Aura L", "Head Snap", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity entity = new WrapperPlayClientInteractEntity(event);

            if (entity.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                int id = entity.getEntityId();

                float deltaYaw = rotationTracker.getDeltaYaw(), deltaPitch = rotationTracker.getDeltaPitch();

                if (id != lastID && (deltaYaw == 0.0 && actionTracker.getLastAttack() < 5 || deltaPitch == 0.0 && actionTracker.getLastAttack() <= 2)) {
                    if (increaseBuffer() > 3) {
                        handleViolation(new DetailedPlayerViolation(this, "T " + actionTracker.getLastAttack() + " Y "  + format(deltaYaw) + " P " + format(deltaPitch)));
                    }
                } else {
                    decreaseBuffer();
                }
                lastID = id;
            }
        }
    }
}
