package me.frep.vulcan.spigot.check.impl.player.improbable;

import me.frep.vulcan.spigot.config.Config;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Improbable", type = 'A', complexType = "Combat", description = "Too many combined combat violations.")
public class ImprobableA extends AbstractCheck
{
    private long lastFlag;
    
    public ImprobableA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                if (System.currentTimeMillis() - this.lastFlag >= 60000L) {
                    final int combatViolations = this.data.getCombatViolations();
                    if (combatViolations > Config.IMPROBABLE_A_MAX_VIOLATIONS) {
                        this.fail("combatViolations=" + combatViolations);
                        this.lastFlag = System.currentTimeMillis();
                    }
                }
            }
        }
    }
}
