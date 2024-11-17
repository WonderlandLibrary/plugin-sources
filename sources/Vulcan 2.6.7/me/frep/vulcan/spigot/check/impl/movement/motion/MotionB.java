package me.frep.vulcan.spigot.check.impl.movement.motion;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Motion", type = 'B', complexType = "Inverse", description = "Repeated vertical motions.")
public class MotionB extends AbstractCheck
{
    public MotionB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double lastDeltaY = this.data.getPositionProcessor().getLastDeltaY();
            final boolean chorus = this.data.getActionProcessor().getSinceChorusFruitTeleportTicks() < 50;
            final boolean lectern = this.data.getPositionProcessor().isNearLectern() || this.data.getPositionProcessor().isNearRepeater();
            final boolean exempt = this.isExempt(ExemptType.WEB, ExemptType.LILY_PAD, ExemptType.JOINED, ExemptType.ELYTRA, ExemptType.ANVIL, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.PLACED_WEB, ExemptType.TELEPORT, ExemptType.RIPTIDE, ExemptType.CREATIVE, ExemptType.SPECTATOR, ExemptType.BUKKIT_VELOCITY, ExemptType.BOAT, ExemptType.WORLD_CHANGE, ExemptType.GLIDING, ExemptType.ENDER_PEARL, ExemptType.LIQUID, ExemptType.CHORUS_FRUIT, ExemptType.TRAPDOOR, ExemptType.CHUNK, ExemptType.PISTON, ExemptType.ILLEGAL_BLOCK, ExemptType.VELOCITY, ExemptType.LENIENT_SCAFFOLDING, ExemptType.CHORUS_FRUIT, ExemptType.FARMLAND, ExemptType.COLLIDING_VERTICALLY, ExemptType.TRAPDOOR, ExemptType.STAIRS, ExemptType.SLAB);
            final boolean invalid = deltaY > 0.0 && deltaY == -lastDeltaY;
            if (invalid && !exempt && !chorus && !lectern) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
