package me.frep.vulcan.spigot.check.impl.combat.criticals;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Criticals", type = 'A', complexType = "Ground", description = "Tried to hit Critical hit on ground.")
public class CriticalsA extends AbstractCheck
{
    public CriticalsA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity() && this.hitTicks() < 2) {
            final double deltaY = Math.abs(this.data.getPositionProcessor().getDeltaY());
            final double modulo = this.data.getPositionProcessor().getY() % 0.015625;
            final boolean exempt = this.isExempt(ExemptType.LIQUID, ExemptType.TRAPDOOR, ExemptType.COLLIDING_VERTICALLY, ExemptType.TELEPORT, ExemptType.BOAT, ExemptType.TELEPORT, ExemptType.WEB, ExemptType.ENDER_PEARL, ExemptType.CHORUS_FRUIT, ExemptType.ELYTRA, ExemptType.SERVER_POSITION, ExemptType.CARPET, ExemptType.VEHICLE, ExemptType.FARMLAND, ExemptType.GLIDING, ExemptType.SLAB) || this.data.getPositionProcessor().isNearPath();
            if (Math.abs(deltaY - 7.504558659832128E-4) < 0.001) {
                return;
            }
            final boolean invalid = (deltaY < 0.001 && deltaY > 0.0 && modulo < 1.0E-5) || deltaY == 0.0625;
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " modulo=" + modulo);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
