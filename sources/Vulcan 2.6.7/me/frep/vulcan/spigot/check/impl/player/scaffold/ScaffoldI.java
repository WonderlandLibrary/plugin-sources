package me.frep.vulcan.spigot.check.impl.player.scaffold;

import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'I', complexType = "Acceleration", description = "Invalid acceleration.")
public class ScaffoldI extends AbstractCheck
{
    public ScaffoldI(final PlayerData data) {
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
                final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
                final boolean invalid = acceleration < 0.01 && deltaYaw > 150.0f && deltaPitch > 10.0f;
                if (invalid) {
                    this.fail("acceleration=" + acceleration + " deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch);
                }
            }
        }
    }
}
