package me.frep.vulcan.spigot.check.impl.combat.killaura;

import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Kill Aura", type = 'F', complexType = "Switch", experimental = false, description = "Switching targets too quickly.")
public class KillAuraF extends AbstractCheck
{
    private int lastEntityId;
    private int hits;
    private int swings;
    private long lastSwitch;
    
    public KillAuraF(final PlayerData data) {
        super(data);
        this.lastSwitch = -1L;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                return;
            }
            if (wrapper.getEntityId() != this.lastEntityId) {
                this.lastSwitch = System.currentTimeMillis();
            }
            ++this.hits;
            this.lastEntityId = wrapper.getEntityId();
        }
        else if (packet.isArmAnimation()) {
            ++this.swings;
        }
        else if (packet.isFlying() && this.hitTicks() < 2 && this.swings > 0) {
            final double ratio = this.hits / (double)this.swings * 100.0;
            final double deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final long delay = System.currentTimeMillis() - this.lastSwitch;
            if (this.hits > 20 && ratio > 85.0 && delay < 5L && deltaYaw > 15.0) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("ratio=" + ratio + " delay=" + delay);
                }
            }
            else {
                this.decayBuffer();
            }
            if (this.hits > 40 || this.swings > 40) {
                final int n = 0;
                this.swings = n;
                this.hits = n;
            }
        }
    }
}
