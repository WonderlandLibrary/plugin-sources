/*
 * This file is part of Hawk Anticheat.
 * Copyright (C) 2018 Hawk Development Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.islandscout.hawk.check.movement.position;

import me.islandscout.hawk.Hawk;
import me.islandscout.hawk.HawkPlayer;
import me.islandscout.hawk.check.MovementCheck;
import me.islandscout.hawk.event.MoveEvent;
import me.islandscout.hawk.util.*;
import me.islandscout.hawk.wrap.block.WrappedBlock;
import me.islandscout.hawk.wrap.entity.WrappedEntity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

public class Strafe extends MovementCheck {

    //This unintentionally trashes yet another handful of killauras and aimassists.
    //Flags poorly implemented aimbots (i.e. aimbots implemented after the motion update in the tick stack).

    private final double THRESHOLD;
    private final Map<UUID, Long> lastIdleTick;
    private final Set<UUID> bouncedSet;
    private final Set<UUID> wasSneakingOnEdgeSet;

    public Strafe() {
        super("strafe", true, 5, 5, 0.99, 5000, "%player% failed strafe, VL: %vl%", null);
        lastIdleTick = new HashMap<>();
        bouncedSet = new HashSet<>();
        wasSneakingOnEdgeSet = new HashSet<>();
        THRESHOLD = Math.toRadians((double)customSetting("yawErrorThreshold", "", 0.5));
    }

    @Override
    protected void check(MoveEvent e) {
        HawkPlayer pp = e.getHawkPlayer();

        boolean bounced = bouncedSet.contains(pp.getUuid());
        boolean collidingHorizontally = collidingHorizontally(e);

        Block footBlock = ServerUtils.getBlockAsync(pp.getPlayer().getLocation().clone().add(0, -0.2, 0));
        if(footBlock == null)
            return;

        long ticksSinceIdle = pp.getCurrentTick() - lastIdleTick.getOrDefault(pp.getUuid(), pp.getCurrentTick());
        double friction = e.getFriction();

        //A really rough check to handle sneaking on edge of blocks.
        boolean sneakEdge = pp.isSneaking()
                && (!WrappedBlock.getWrappedBlock(footBlock, pp.getClientVersion()).isSolid() || footBlock.getType() == Material.LADDER /*false band aid*/)
                && e.isOnGround();
        boolean wasSneakingOnEdge = wasSneakingOnEdgeSet.contains(pp.getUuid());

        Vector prevVelocity = pp.getVelocity().clone();
        if(e.hasHitSlowdown()) {
            prevVelocity.multiply(0.6);
        }

        Set<Material> collidedMats = WrappedEntity.getWrappedEntity(e.getPlayer()).getCollisionBox(e.getFrom().toVector()).getMaterials(pp.getWorld());
        List<Block> activeBlocks = e.getActiveBlocks();
        for(Block b : activeBlocks) {
            if(b.getType() == Material.SOUL_SAND) {
                prevVelocity.multiply(0.4); //which would you prefer: iterative multiplication or using Math.pow()?
            }
            if(b.getType() == Material.WEB) {
                prevVelocity.multiply(0);
                break; //Any number times 0 is 0; no reason to continue looping.
            }
        }

        boolean onSlimeblock = Hawk.getServerVersion() > 7 && (pp.wasOnGround() || pp.isOnGround()) && footBlock.getType() == Material.SLIME_BLOCK;
        boolean nearLiquid = testLiquid(collidedMats);

        if(Math.abs(prevVelocity.getX() * friction) < 0.005) {
            prevVelocity.setX(0);
        }
        if(Math.abs(prevVelocity.getZ() * friction) < 0.005) {
            prevVelocity.setZ(0);
        }

        double dX = e.getTo().getX() - e.getFrom().getX();
        double dZ = e.getTo().getZ() - e.getFrom().getZ();
        dX /= friction;
        dZ /= friction;
        dX -= prevVelocity.getX();
        dZ -= prevVelocity.getZ();

        Vector accelDir = new Vector(dX, 0, dZ);
        Vector yaw = MathPlus.getDirection(e.getTo().getYaw(), 0);

        //Return if player hasn't sent at least 2 moves in a row. Let Speed handle any bypasses for this.
        if(e.isTeleportAccept() || e.hasAcceptedKnockback() || bounced || collidingHorizontally ||
                !e.isUpdatePos() || sneakEdge || e.isJump() || ticksSinceIdle <= 2 || nearLiquid || //TODO get rid of e.isJump() from here and actually try to handle it
                pp.getCurrentTick() - pp.getLastVelocityAcceptTick() == 1 || collidedMats.contains(Material.LADDER) ||
                collidedMats.contains(Material.VINE) || wasSneakingOnEdge || onSlimeblock || (e.isStep() && pp.isSprinting())) {
            prepareNextMove(e, pp, pp.getCurrentTick(), sneakEdge);
            return;
        }

        //You aren't pressing a WASD key
        if(accelDir.lengthSquared() < 0.000001) {
            prepareNextMove(e, pp, pp.getCurrentTick(), sneakEdge);
            return;
        }

        boolean vectorDir = accelDir.clone().crossProduct(yaw).dot(new Vector(0, 1, 0)) >= 0;
        double angle = (vectorDir ? 1 : -1) * MathPlus.angle(accelDir, yaw);

        if(!isValidStrafe(angle)) {
            punishAndTryRubberband(pp, e);
        }
        else
            reward(pp);

        prepareNextMove(e, pp, pp.getCurrentTick(), sneakEdge);
    }

    private boolean collidingHorizontally(MoveEvent e) {
        for(Direction dir : e.getBoxSidesTouchingBlocks()) {
            if(dir == Direction.EAST || dir == Direction.NORTH || dir == Direction.SOUTH || dir == Direction.WEST) {
                bouncedSet.add(e.getPlayer().getUniqueId());
                return true;
            }
        }
        bouncedSet.remove(e.getPlayer().getUniqueId());
        return false;
    }

    private boolean testLiquid(Set<Material> mats) {
        for(Material mat : mats) {
            if(mat == Material.WATER || mat == Material.STATIONARY_WATER || mat == Material.LAVA || mat == Material.STATIONARY_LAVA)
                return true;
        }
        return false;
    }

    private boolean isValidStrafe(double angle) {
        double modulo = (angle % (Math.PI / 4)) * (4 / Math.PI); //scaled so that legit values should be close to either 0 or +/-1
        double error = Math.abs(modulo - Math.round(modulo)) * (Math.PI / 4); //compute error (and then scale back to radians)
        return error <= THRESHOLD; //in radians
    }

    private void prepareNextMove(MoveEvent event, HawkPlayer pp, long currentTick, boolean sneakOnEdge) {
        UUID uuid = pp.getUuid();

        if(!event.isUpdatePos())
            lastIdleTick.put(uuid, currentTick);

        if(sneakOnEdge) {
            wasSneakingOnEdgeSet.add(uuid);
        } else {
            wasSneakingOnEdgeSet.remove(uuid);
        }
    }

    @Override
    public void removeData(Player p) {
        UUID uuid = p.getUniqueId();
        lastIdleTick.remove(uuid);
        bouncedSet.remove(uuid);
        wasSneakingOnEdgeSet.remove(uuid);
    }
}
