package com.elevatemc.anticheat.base.check.impl.misc.timer;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class TimerB extends PacketCheck {

    private long balance = -100L;
    private Long lastFlying = 0L;
    public TimerB(PlayerData playerData) {
        super(playerData, "Timer B", "Client is not able to catchup.", new ViolationHandler(25, 300000L), Category.MISC, SubCategory.TIMER, 1);
    }

    @Override
    public void handle(PacketReceiveEvent packet) {
        if (PacketUtil.isFlying(packet.getPacketType())) {

            boolean valid = keepAliveTracker.isAcceptedKeepAlive();
            if (valid) {

                if (lastFlying != null) {
                    boolean fast = System.currentTimeMillis() - lastFlying < 5L;

                    if (fast) return;

                    balance += 50L;
                    balance -= (System.currentTimeMillis() - lastFlying);

                    if (balance < -500) {
                        balance = -500;
                    }

                    if (balance > 50L) {
                        if (increaseBuffer() > 2) {
                            handleViolation(new DetailedPlayerViolation(this,"balance= " + balance));
                        }

                        balance = 0L;
                    } else {
                        decreaseBufferBy(0.05);
                    }
                }
            }
            lastFlying = System.currentTimeMillis();
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.SPAWN_POSITION || event.getPacketType() == PacketType.Play.Server.PLAYER_POSITION_AND_LOOK) {
            //debug(balance);
            balance -= 100L;

            // what the actual fuck?
            if (event.getPacketType() == PacketType.Play.Server.PLAYER_POSITION_AND_LOOK) {
                //debug(balance);
                balance -= 750L;
            }

        }
    }
}
