package me.frep.vulcan.spigot.check.impl.movement.step;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Step", type = 'A', complexType = "Vanilla", description = "Invalid Step height.")
public class StepA extends AbstractCheck
{
    public StepA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean onGround = this.data.getPositionProcessor().isClientOnGround();
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.SHULKER_BOX, ExemptType.SPECTATOR, ExemptType.WORLD_CHANGE, ExemptType.RIPTIDE, ExemptType.BOAT, ExemptType.VEHICLE, ExemptType.PISTON, ExemptType.SHULKER, ExemptType.ENDER_PEARL, ExemptType.SLEEPING, ExemptType.BED);
            final boolean invalid = onGround && deltaY > 0.6000000238418579;
            if (invalid && !exempt) {
                this.fail("deltaY=" + deltaY);
            }
        }
    }
}
