package me.frep.vulcan.spigot.check.impl.player.inventory;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Inventory", type = 'B', complexType = "Move", description = "Moved while clicking in inventory.")
public class InventoryB extends AbstractCheck
{
    public InventoryB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isWindowClick()) {
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double lastDeltaXZ = this.data.getPositionProcessor().getLastDeltaXZ();
            final boolean accelerating = deltaXZ > lastDeltaXZ && deltaXZ > 0.2;
            final boolean exempt = this.isExempt(ExemptType.VELOCITY, ExemptType.ENDER_PEARL, ExemptType.FLIGHT, ExemptType.SPECTATOR, ExemptType.RIPTIDE, ExemptType.GLIDING, ExemptType.ELYTRA, ExemptType.SHULKER_BOX, ExemptType.SHULKER, ExemptType.ICE, ExemptType.DRAGON_DAMAGE, ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.PISTON, ExemptType.WATERLOGGED, ExemptType.LIQUID, ExemptType.SWIMMING, ExemptType.SERVER_POSITION, ExemptType.BOAT, ExemptType.SLIME, ExemptType.WORLD_CHANGE, ExemptType.SOUL_SPEED, ExemptType.DOLPHINS_GRACE, ExemptType.BUBBLE_COLUMN, ExemptType.HIGH_SPEED, ExemptType.VEHICLE);
            if (accelerating && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaXZ=" + deltaXZ + " lastDeltaXZ=" + lastDeltaXZ);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
