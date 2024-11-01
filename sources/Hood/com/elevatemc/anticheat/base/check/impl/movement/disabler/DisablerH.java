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

public class DisablerH extends PacketCheck {
    public DisablerH(PlayerData playerData) {
        super(playerData, "Disabler H", "", new ViolationHandler(5, 3000L), Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) return;

        int diff = keepAliveTracker.getKeepAliveSent().get() - keepAliveTracker.getKeepAliveReceived().get();
        if(diff < 100) {
            decreaseBuffer();
            return;
        }
        if(increaseBuffer()  > 2) {
            handleViolation(new DetailedPlayerViolation(this, "DIFF: " + diff));
            staffAlert(new PlayerViolation(this));
            resetBuffer();
        }
    }
}
