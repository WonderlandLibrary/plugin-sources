package me.frep.vulcan.spigot.check.impl.combat.killaura;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Kill Aura", type = 'D', complexType = "Multi Aura", experimental = false, description = "Attacked two entities at once.")
public class KillAuraD extends AbstractCheck
{
    private int lastEntityId;
    private int ticks;
    
    public KillAuraD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                return;
            }
            final boolean exempt = this.isExempt(ExemptType.CLIENT_VERSION, ExemptType.SERVER_VERSION);
            final int id = wrapper.getEntityId();
            if (id != this.lastEntityId && !exempt && ++this.ticks > 1) {
                this.fail("id=" + id + " lastId=" + this.lastEntityId);
            }
            this.lastEntityId = id;
        }
        else if (packet.isFlying()) {
            this.ticks = 0;
        }
    }
}
