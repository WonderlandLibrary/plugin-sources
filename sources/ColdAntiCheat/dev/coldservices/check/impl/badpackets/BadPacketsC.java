package dev.coldservices.check.impl.badpackets;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.PositionUpdate;

@CheckManifest(name = "BadPackets", type = "C", description = "Detects if the dist is big (unloaded chunks etc)")
public class BadPacketsC extends Check implements PositionCheck {

    private double lastDist;

    public BadPacketsC(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(PositionUpdate update) {
        boolean exempt = this.isExempt(ExemptType.JOIN, ExemptType.TELEPORT, ExemptType.FLIGHT, ExemptType.CHUNK,
                ExemptType.TELEPORTED_RECENTLY);

        if(exempt) return;

        double dist = calculateDistance(
                new Vector3D((float) update.getX(), (float) update.getY(), (float) update.getZ()),
                new Vector3D((float) update.getLastX(), (float) update.getLastY(), (float) update.getLastZ())
        );

        if(dist >= 100) {
            this.failNoBan("dist: " + dist);
            this.executeSetback(true, true);
        }


    }

    public double calculateDistance(Vector3D to, Vector3D from) {
        double x = to.getX() - from.getX();
        double y = to.getY() - from.getY();
        double z = to.getZ() - from.getZ();

        return Math.sqrt(x * x + y * y + z * z);
    }
}
