package com.elevatemc.anticheat.base.check.impl.movement.water;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public class WaterSpeedD extends PositionCheck {

    public WaterSpeedD(PlayerData playerData) {
        super(playerData, "Water Speed Type D", "JESUS", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.WATERSPEED,2);
    }

    /*
        Lets fix this dumbass check real quick.
        this check will false if you stand on the edge of a block, and the method Im using to check for
        liquid is retarded but catches a lot of Jesus cheats.

        ^^ that was how it falsed, this is the version where it's not broken.
     */

    @Override
    public void handle() {
        if (System.currentTimeMillis() - playerData.lastJoin < 3000L) return;
        // Grab the deltaY as when they sometimes spoof and act like they're on ground, of course their deltaY is 0.
        double deltaY = positionTracker.getDeltaY();
        // Grab their airTicks, we'll use this to also check for ground spoof and you'll see below how.
        int airTicks = collisionTracker.getServerAirTicks();
        // Necessary to check for spoofing.
        boolean packetGround = collisionTracker.isClientGround();
        // The liquid method.
        boolean liquid = playerData.getPlayer().getLocation().clone().add(new Location(playerData.getPlayer().getWorld(), 0, -1, 0)).getBlock().getRelative(BlockFace.DOWN).isLiquid();
        // Necessary to check if the surroundings are liquid.
        boolean surrounded =  playerData.getPlayer().getLocation().add(0, 1, 0).getBlock().isLiquid();
        // Check if they are not falling into liquid like they're supposed to.
        boolean fall = playerData.getPlayer().getFallDistance() < 0.1F && playerData.getPlayer().getVelocity().getY() <= -0.8;
        /*
         We'll add a backup to "fall" to avoid getting bypassed.
         This backup will check if they're in the "air" for a period of time whilst their deltaY is 0 (liquid spoof)
         */
        boolean backup = airTicks > 40 && (deltaY == 0.0 || packetGround);
        // Patched Jesus in less than 10 minutes.
        if (liquid && surrounded) {
            if (fall || backup) {
                handleViolation(new DetailedPlayerViolation(this, ""));
                staffAlert(new PlayerViolation(this));
            }
        }
    }
}
