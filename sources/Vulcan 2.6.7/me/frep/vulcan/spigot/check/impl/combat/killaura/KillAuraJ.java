package me.frep.vulcan.spigot.check.impl.combat.killaura;

import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Kill Aura", type = 'J', complexType = "Frequency", experimental = false, description = "Attack frequency.")
public class KillAuraJ extends AbstractCheck
{
    private int movements;
    private int lastMovements;
    private int total;
    private int invalid;
    
    public KillAuraJ(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                final boolean proper = this.data.getClickProcessor().getCps() > 7.2 && this.movements < 4 && this.lastMovements < 4;
                if (proper) {
                    final boolean flag = this.movements == this.lastMovements;
                    if (flag) {
                        ++this.invalid;
                    }
                    if (++this.total == 30) {
                        if (this.invalid > 28) {
                            if (this.increaseBuffer() > this.MAX_BUFFER) {
                                this.fail("invalid=" + this.invalid);
                            }
                        }
                        else {
                            this.decayBuffer();
                        }
                        this.invalid = 0;
                        this.total = 0;
                    }
                }
                this.lastMovements = this.movements;
                this.movements = 0;
            }
        }
        else if (packet.isFlying()) {
            ++this.movements;
        }
    }
}
