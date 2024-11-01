package com.elevatemc.anticheat.base.check.impl.misc.refill;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import org.bukkit.GameMode;

public class RefillE extends PacketCheck {

    private long lastFlying;
    private boolean sent;
    public RefillE(PlayerData playerData) {
        super(playerData, "Refill E", "Post window click", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.REFILL, 5);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            final long delay = System.currentTimeMillis() - lastFlying;

            if (sent) {
                boolean exempt = playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE);

                if (delay > 55L && delay < 100L && !exempt) {
                    if (increaseBuffer() > 7) {
                        handleViolation(new DetailedPlayerViolation(this,"D " + delay));
                        multiplyBuffer(.25);
                    }
                } else {
                    decreaseBufferBy(.25);
                }
                sent = false;
            }

            lastFlying = System.currentTimeMillis();
        } else if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            final long delay = System.currentTimeMillis() - lastFlying;

            if (delay < 10) {
                sent = true;
            }
        }
    }
}
