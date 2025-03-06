package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

/**
 * @author Moose1301
 * @date 5/6/2024
 */
public class DisablerG extends PacketCheck {

    public DisablerG(PlayerData playerData) {
        super(playerData, "Disabler G", "5cb1bd9f04a2ebb5612e5b5412b928f06c854e01", new ViolationHandler(10, 30000L), Category.MOVEMENT, SubCategory.DISABLER, 5);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if(event.getPacketType() != PacketType.Play.Client.KEEP_ALIVE) {
            return;
        }
        int diff = transactionTracker.getTransSent().get() - transactionTracker.getTransReceived().get();

        if (diff > 100) {
            if (increaseBuffer() > 6) {
                handleViolation(new DetailedPlayerViolation(this, "DIFF " + diff));
                resetBuffer();
            }
        } else {
            resetBuffer();
            vl -= vl > 0 ? 1 : 0;
        }
    }
}
