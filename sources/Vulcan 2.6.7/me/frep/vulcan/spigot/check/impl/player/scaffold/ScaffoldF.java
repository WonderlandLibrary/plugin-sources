package me.frep.vulcan.spigot.check.impl.player.scaffold;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'F', complexType = "Packet", description = "Invalid rotations")
public class ScaffoldF extends AbstractCheck
{
    private long lastPlace;
    private long delay;
    private long lastSneak;
    private long sneakDelay;
    private boolean bridging;
    private boolean sneak;
    
    public ScaffoldF(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled() || BlockUtil.isScaffolding(event.getBlock().getType())) {
                return;
            }
            this.bridging = PlayerUtil.isBridging(event);
            this.delay = System.currentTimeMillis() - this.lastPlace;
            this.lastPlace = System.currentTimeMillis();
        }
        else if (packet.isEntityAction()) {
            final WrappedPacketInEntityAction wrapper = this.data.getEntityActionWrapper();
            if (wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.START_SNEAKING || wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.STOP_SNEAKING) {
                this.sneakDelay = System.currentTimeMillis() - this.lastSneak;
                this.sneak = true;
                this.lastSneak = System.currentTimeMillis();
            }
        }
        else if (packet.isPosition()) {
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double max = PlayerUtil.getBaseSpeed(this.data, 0.27f);
            final boolean exempt = this.isExempt(ExemptType.LIQUID, ExemptType.FLIGHT, ExemptType.CREATIVE);
            final boolean invalid = this.sneak && this.bridging && deltaXZ > max && this.delay < 400L;
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("delay=" + this.delay + " delta=" + deltaXZ + " sd=" + this.sneakDelay);
                }
            }
            else {
                this.decayBuffer();
            }
            this.sneak = false;
        }
    }
}
