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
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientWindowConfirmation;

public class DisablerO extends PacketCheck {

    private long lastAcceptedTransaction = System.currentTimeMillis();
    public DisablerO(PlayerData playerData) {
        super(playerData, "Disabler O", "What in the lol", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY || event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT || event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {
            long timeSinceTransactionAccept = System.currentTimeMillis() - lastAcceptedTransaction;

            debug("Since check: " + timeSinceTransactionAccept / 1000);
            debug("Since sent: " + (transactionTracker.getLastSent()- transactionTracker.getLastReceived()) / 1000);

            if (timeSinceTransactionAccept > 30_000L) {
                //Bukkit.getScheduler().runTask(Hood.get(), () -> playerData.getPlayer().kickPlayer("Sorry but your connection is too unstable."));
                staffAlert(new PlayerViolation(this));
                handleViolation(new DetailedPlayerViolation(this, "0 ms."));
            }

        } else if (event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION) {
            WrapperPlayClientWindowConfirmation wrapper = new WrapperPlayClientWindowConfirmation(event);

            if (wrapper.getWindowId() == 0 && wrapper.getActionId() == -1) {
                lastAcceptedTransaction = System.currentTimeMillis();
            }
        }
    }
}
