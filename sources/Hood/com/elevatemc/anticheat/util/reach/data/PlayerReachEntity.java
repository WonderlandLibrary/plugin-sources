package com.elevatemc.anticheat.util.reach.data;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.util.reach.box.GetBoundingBox;
import com.elevatemc.anticheat.util.reach.box.SimpleCollisionBox;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.util.Vector3d;
import lombok.Getter;
import lombok.Setter;

public class PlayerReachEntity
{
    public Vector3d serverPos;
    public ReachInterpolationData oldPacketLocation;
    public ReachInterpolationData newPacketLocation;
    @Getter
    @Setter
    public int lastTransactionHung;

    public PlayerReachEntity(PlayerData data, double x, double y, double z) {
        this.serverPos = new Vector3d(x, y, z);
        this.newPacketLocation = new ReachInterpolationData(GetBoundingBox.getBoundingBoxFromPosAndSize(x, y, z, 0.6f, 1.8f), serverPos.getX(), serverPos.getY(), serverPos.getZ(), data.getUser().getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9));
    }

    // Set the old packet location to the new one
    // Set the new packet location to the updated packet location
    public void onFirstTransaction(boolean relative, boolean hasPos, double relX, double relY, double relZ, PlayerData data) {
        // Bukkit.broadcastMessage("first trans has arrived");

        if (hasPos) {
            if (relative) {
                serverPos = serverPos.add(new Vector3d(relX, relY, relZ));
            } else {
                serverPos = new Vector3d(relX, relY, relZ);
            }
        }

        this.oldPacketLocation = newPacketLocation;
        this.newPacketLocation = new ReachInterpolationData(oldPacketLocation.getPossibleLocationCombined(), serverPos.getX(), serverPos.getY(), serverPos.getZ(), data.getUser().getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9));
    }

    // Remove the possibility of the old packet location
    public void onSecondTransaction() {
        // Bukkit.broadcastMessage("first trans has arrived no uncertainity");
        // System.out.println("nullify");
        this.oldPacketLocation = null;
    }


    // If the old and new packet location are split, we need to combine bounding boxes
    public void onMovement(boolean tickingReliably) {
        this.newPacketLocation.tickMovement(this.oldPacketLocation == null, tickingReliably);

        // Handle uncertainty of second transaction spanning over multiple ticks
        if (this.oldPacketLocation != null) {
            this.oldPacketLocation.tickMovement(true, tickingReliably);
            this.newPacketLocation.updatePossibleStartingLocation(this.oldPacketLocation.getPossibleLocationCombined());
        }
    }

    public SimpleCollisionBox getPossibleCollisionBoxes() {
        if (this.oldPacketLocation == null) {
            return this.newPacketLocation.getPossibleLocationCombined();
        }
        return ReachInterpolationData.combineCollisionBox(this.oldPacketLocation.getPossibleLocationCombined(), this.newPacketLocation.getPossibleLocationCombined());
    }

    @Override
    public String toString() {
        return "PlayerReachEntity{serverPos=" + this.serverPos + ", oldPacketLocation=" + this.oldPacketLocation + ", newPacketLocation=" + this.newPacketLocation + '}';
    }
}
