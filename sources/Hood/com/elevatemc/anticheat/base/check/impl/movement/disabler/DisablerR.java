package com.elevatemc.anticheat.base.check.impl.movement.disabler;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class DisablerR extends PacketCheck {

    int lastTicks;
    public DisablerR(PlayerData playerData) {
        super(playerData, "Disabler R", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ANIMATION || event.getPacketType() == PacketType.Play.Client.PONG ||
                event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {

            int transaction = transactionTracker.getLastTransactionSent() - transactionTracker.getTransReceived().get();
            int keepAlive = keepAliveTracker.getKeepAliveSent().get() - keepAliveTracker.getKeepAliveReceived().get();

            int existed = playerData.getTicksExisted();

            boolean duplicate = existed == lastTicks && (transaction > 20 || keepAlive > 20);
            boolean response = existed < 10 && (transaction > 20 || keepAlive > 20);
            boolean velocity = velocityTracker.getTicksSinceVelocity() < 10 && existed - lastTicks <= 2 && (transaction > 20 || keepAlive > 20);

            if (duplicate || response || velocity) {
                if (increaseBuffer() > 6) handleViolation(new PlayerViolation(this));
            } else {
                decreaseBufferBy(2);
            }
            lastTicks = playerData.getTicksExisted();
        }
    }
}
