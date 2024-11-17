package me.frep.vulcan.spigot.check.impl.player.timer;

import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Timer", type = 'D', complexType = "Balance", description = "Increased game speed.")
public class TimerD extends AbstractCheck
{
    private long lastFlying;
    private long balance;
    private int ticks;
    
    public TimerD(final PlayerData data) {
        super(data);
        this.lastFlying = 0L;
        this.balance = 0L;
        this.ticks = 0;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying() && !this.fuckedPosition()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.TPS, ExemptType.VEHICLE);
            if (this.data.getActionProcessor().isSitting()) {
                return;
            }
            if (this.lastFlying != 0L && !exempt) {
                this.balance += 50L;
                this.balance -= delay;
                if (this.balance > Config.TIMER_D_MAX_BALANCE) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("balance=" + this.balance + " delay=" + delay);
                    }
                    else {
                        this.decayBuffer();
                    }
                    this.balance = -200L;
                }
                if (PlayerUtil.isHigherThan1_9(this.data.getPlayer())) {
                    if (this.ticks % 400 == 0) {
                        this.balance = -200L;
                        this.ticks = 0;
                    }
                }
                else if (this.ticks % 3000 == 0) {
                    this.balance = -200L;
                    this.ticks = 0;
                }
            }
            ++this.ticks;
            this.lastFlying = System.currentTimeMillis();
        }
        else if (packet.isTeleport()) {
            if (ServerUtil.isHigherThan1_9()) {
                this.balance -= 150L;
            }
            else {
                this.balance -= 50L;
            }
        }
    }
}
