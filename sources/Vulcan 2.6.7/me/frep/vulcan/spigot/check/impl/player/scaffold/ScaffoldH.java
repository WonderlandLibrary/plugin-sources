package me.frep.vulcan.spigot.check.impl.player.scaffold;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'H', complexType = "Rotations [3]", experimental = false, description = "Invalid rotations.")
public class ScaffoldH extends AbstractCheck
{
    private long lastPlace;
    private long delay;
    private boolean bridging;
    private int placeTicks;
    
    public ScaffoldH(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled()) {
                return;
            }
            this.bridging = PlayerUtil.isBridging(event);
            this.delay = System.currentTimeMillis() - this.lastPlace;
            this.placeTicks = 0;
            this.lastPlace = System.currentTimeMillis();
        }
        else if (packet.isRotation() && ++this.placeTicks < 5 && this.bridging && this.delay < 400L) {
            final double finalSensitivity = this.data.getRotationProcessor().getFinalSensitivity();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final boolean invalid = finalSensitivity < -2.0 || (finalSensitivity > 200.0 && deltaPitch > 0.25f);
            final boolean exempt = this.isExempt(ExemptType.CINEMATIC);
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("final=" + finalSensitivity);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
