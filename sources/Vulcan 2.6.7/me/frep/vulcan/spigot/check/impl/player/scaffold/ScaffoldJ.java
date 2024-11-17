package me.frep.vulcan.spigot.check.impl.player.scaffold;

import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'J', complexType = "Acceleration [2]", experimental = false, description = "Invalid pitch change.")
public class ScaffoldJ extends AbstractCheck
{
    public ScaffoldJ(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled()) {
                return;
            }
            if (PlayerUtil.isBridging(event)) {
                final double acceleration = this.data.getPositionProcessor().getAcceleration();
                final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
                final boolean invalid = MathUtil.isExponentiallySmall(acceleration) && deltaPitch > 18.0f;
                if (invalid) {
                    this.fail("acceleration=" + acceleration + " deltaPitch=" + deltaPitch);
                }
            }
        }
    }
}
