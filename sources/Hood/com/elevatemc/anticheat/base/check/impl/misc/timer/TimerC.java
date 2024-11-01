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

public class TimerC extends PacketCheck {
    private long previousTick, balance, lastBalance;
    private int deceleration, acceleration, maxCatchupTicks;
    public TimerC(PlayerData playerData) {
        super(playerData, "Timer C", "Client CANNOT Catchup.", new ViolationHandler(25, 30000L), Category.MISC, SubCategory.TIMER, 1);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            long currentTick = System.currentTimeMillis();
            if (teleportTracker.isJoined()) {
                return;
            }
            if (this.previousTick != 0L) {
                long delta = currentTick - this.previousTick;
                if (delta > 90L) {
                    this.maxCatchupTicks = (int)(Math.ceil((double)delta / 50.0) + 10.0);
                } else if (delta < 75L && this.maxCatchupTicks > 0) {
                    --this.maxCatchupTicks;
                }
                this.balance += 50L;
                this.balance -= delta;
                if (this.balance > 50L) {
                    this.balance = -50L;
                    if (increaseBuffer() > 3) handleViolation(new DetailedPlayerViolation(this, "BAL " + format(balance)));
                } else {
                    decreaseBuffer();
                }
            }
            if (this.balance < -20000L) {
                this.balance = -20000L;
            }
            if (this.balance < this.lastBalance && this.balance < -200L) {
                if (++this.deceleration > 5) {
                    this.balance = -100L;
                    this.deceleration = 0;
                }
            } else if (this.deceleration > 0) {
                --this.deceleration;
            }
            if (this.balance > this.lastBalance) {
                ++this.acceleration;
                if (this.maxCatchupTicks == 0 && this.acceleration > 15 && this.balance < -100L) {
                    this.balance = -100L;
                    this.acceleration = 0;
                }
            } else {
                this.acceleration = 0;
            }
            this.previousTick = currentTick;
            this.lastBalance = this.balance;
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.SPAWN_POSITION || event.getPacketType() == PacketType.Play.Server.PLAYER_POSITION_AND_LOOK) {
            balance -= 100L;
            if (event.getPacketType() == PacketType.Play.Server.PLAYER_POSITION_AND_LOOK) {
                balance -= 750L;
            }
        }
    }
}
