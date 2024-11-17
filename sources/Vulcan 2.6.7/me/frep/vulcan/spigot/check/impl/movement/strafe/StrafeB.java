package me.frep.vulcan.spigot.check.impl.movement.strafe;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Strafe", type = 'B', complexType = "Ground", description = "Moving incorrectly on the ground.")
public class StrafeB extends AbstractCheck
{
    public StrafeB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            final int groundTicks = this.data.getPositionProcessor().getClientGroundTicks();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final float moveForward = this.data.getPositionProcessor().getMoveForward();
            final float moveStrafing = this.data.getPositionProcessor().getMoveStrafing();
            final boolean onGround = this.data.getPositionProcessor().isClientOnGround();
            final boolean sprinting = this.data.getActionProcessor().isSprinting();
            final boolean env = onGround && sprinting;
            final boolean flag = moveForward < 0.0f || (moveForward == 0.0f && moveStrafing != 0.0f);
            final boolean exempt = this.isExempt(ExemptType.GLIDING, ExemptType.ELYTRA, ExemptType.DOLPHINS_GRACE, ExemptType.BUKKIT_VELOCITY, ExemptType.DEPTH_STRIDER, ExemptType.ICE, ExemptType.SLIME, ExemptType.PISTON, ExemptType.SWIMMING, ExemptType.SERVER_POSITION, ExemptType.LIQUID, ExemptType.SOUL_SPEED, ExemptType.WEB, ExemptType.ENTITY_COLLISION, ExemptType.TELEPORT, ExemptType.VELOCITY, ExemptType.SERVER_POSITION);
            if (env && flag && groundTicks > 3 && !exempt && deltaXZ > 0.25) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("forward=" + moveForward + " strafe=" + moveStrafing + " deltaXZ=" + deltaXZ);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
