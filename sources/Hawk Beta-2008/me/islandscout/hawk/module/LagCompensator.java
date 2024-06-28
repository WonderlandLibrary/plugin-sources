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

package me.islandscout.hawk.module;

import me.islandscout.hawk.util.*;
import me.islandscout.hawk.Hawk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LagCompensator implements Listener {

    //https://developer.valvesoftware.com/wiki/Lag_compensation
    //https://www.youtube.com/watch?v=6EwaW2iz4iA
    //http://www.gabrielgambetta.com/lag-compensation.html
    //http://www.gabrielgambetta.com/client-side-prediction-live-demo.html
    //https://en.wikipedia.org/wiki/Lag#Rewind_time

    //Yes, I use System.currentTimeMillis()! Should the main thread start lagging
    //behind, the lag compensator will not be affected. The lag compensator
    //should be dependent on system time and not server ticks. System time is
    //stable for this application and is thus a reliable time reference for
    //measuring latency.

    private final Map<Entity, List<Pair<Location, Long>>> trackedEntities;
    private final List<PistonPush> pistonPushes;
    private final int historySize;
    private final int pingOffset;
    private final boolean DEBUG;
    private final int SEARCH_WIDTH;
    private final int SEARCH_HEIGHT;
    private final int POLL_RATE;
    private final boolean ALWAYS_TICK_PLAYERS;

    public LagCompensator(Hawk hawk) {
        this.trackedEntities = new ConcurrentHashMap<>();
        this.pistonPushes = new CopyOnWriteArrayList<>();
        historySize = ConfigHelper.getOrSetDefault(20, hawk.getConfig(), "lagCompensation.historySize");
        pingOffset = ConfigHelper.getOrSetDefault(175, hawk.getConfig(), "lagCompensation.pingOffset");
        SEARCH_WIDTH = ConfigHelper.getOrSetDefault(40, hawk.getConfig(), "lagCompensation.entityTracking.searchWidth") / 2;
        SEARCH_HEIGHT = ConfigHelper.getOrSetDefault(20, hawk.getConfig(), "lagCompensation.entityTracking.searchHeight") / 2;
        POLL_RATE = ConfigHelper.getOrSetDefault(20, hawk.getConfig(), "lagCompensation.entityTracking.searchRate");
        ALWAYS_TICK_PLAYERS = ConfigHelper.getOrSetDefault(true, hawk.getConfig(), "lagCompensation.entityTracking.alwaysTickPlayers");
        DEBUG = ConfigHelper.getOrSetDefault(false, hawk.getConfig(), "lagCompensation.debug");
        Bukkit.getPluginManager().registerEvents(this, hawk);

        hawk.getHawkSyncTaskScheduler().addRepeatingTask(new Runnable() {
            @Override
            public void run() {

                Set<Entity> collectedEntities = new HashSet<>();

                for(Player p : Bukkit.getOnlinePlayers()) {
                    List<Entity> nearbyEntities = p.getNearbyEntities(SEARCH_WIDTH, SEARCH_HEIGHT, SEARCH_WIDTH);
                    for(Entity entity : nearbyEntities) {
                        //add anything that moves and is clickable
                        if(entity instanceof LivingEntity || entity instanceof Vehicle || entity instanceof Fireball) {
                            collectedEntities.add(entity);
                        }
                    }
                }

                for(Entity entity : collectedEntities) {
                    trackedEntities.put(entity, trackedEntities.getOrDefault(entity, new CopyOnWriteArrayList<>()));
                }

                Set<Entity> expiredEntities = new HashSet<>(trackedEntities.keySet());
                expiredEntities.removeAll(collectedEntities);

                for(Entity expired : expiredEntities) {
                    trackedEntities.remove(expired);
                }

            }
        }, POLL_RATE);

        if(ALWAYS_TICK_PLAYERS) {
            hawk.getHawkSyncTaskScheduler().addRepeatingTask(new Runnable() {
                @Override
                public void run() {
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        trackedEntities.put(p, trackedEntities.getOrDefault(p, new CopyOnWriteArrayList<>()));
                    }
                }
            }, 1);
        }

        hawk.getHawkSyncTaskScheduler().addRepeatingTask(new Runnable() {
            @Override
            public void run() {
                for(Entity entity : trackedEntities.keySet()) {
                    processPosition(entity);
                }
                /*
                for(HawkPlayer pp : hawk.getHawkPlayers()) {
                    Player p = pp.getPlayer();
                    p.getActivePotionEffects();
                }*/

                long currTime = System.currentTimeMillis();

                while(pistonPushes.size() > 0 && currTime - pistonPushes.get(0).getTimestamp() > 2000) {
                    pistonPushes.remove(0);
                }
            }
        }, 1);
    }

    //Uses linear interpolation to get the best location
    public Location getHistoryLocation(int rewindMillisecs, Entity entity) {
        List<Pair<Location, Long>> times = trackedEntities.get(entity);
        if (times == null || times.size() == 0) {
            return entity.getLocation();
        }
        long currentTime = System.currentTimeMillis();
        int rewindTime = rewindMillisecs + pingOffset; //player a + avg processing time.
        for (int i = times.size() - 1; i >= 0; i--) { //loop backwards
            int elapsedTime = (int) (currentTime - times.get(i).getValue());
            if (elapsedTime >= rewindTime) {
                if (i == times.size() - 1) {
                    return times.get(i).getKey();
                }
                double nextMoveWeight = (elapsedTime - rewindTime) / (double) (elapsedTime - (currentTime - times.get(i + 1).getValue()));
                Location before = times.get(i).getKey().clone();
                Location after = times.get(i + 1).getKey();
                Vector interpolate = after.toVector().subtract(before.toVector());
                interpolate.multiply(nextMoveWeight);
                before.add(interpolate);
                return before;
            }
        }
        return times.get(0).getKey().clone(); //ran out of historical data; return oldest entry
    }

    public Vector getHistoryVelocity(int rewindMillisecs, Entity entity) {
        List<Pair<Location, Long>> times = trackedEntities.get(entity);
        if (times == null || times.size() == 0) {
            return new Vector(0, 0, 0);
        }
        long currentTime = System.currentTimeMillis();
        int rewindTime = rewindMillisecs + pingOffset; //player a + avg processing time.
        for (int i = times.size() - 1; i >= 0; i--) { //loop backwards
            int elapsedTime = (int) (currentTime - times.get(i).getValue());
            if (elapsedTime >= rewindTime) {
                if (i == times.size() - 1) {
                    return new Vector(0, 0, 0);
                }
                Location before = times.get(i).getKey();
                Location after = times.get(i + 1).getKey();
                return after.toVector().subtract(before.toVector());
            }
        }
        return new Vector(0, 0, 0); //ran out of historical data; return oldest entry
    }

    public Location getHistoryLocationNoLerp(int rewindMillisecs, Entity entity) {
        List<Pair<Location, Long>> times = trackedEntities.get(entity);
        if (times == null || times.size() == 0) {
            return entity.getLocation();
        }
        long currentTime = System.currentTimeMillis();
        int rewindTime = rewindMillisecs + pingOffset; //player a + avg processing time.
        for (int i = times.size() - 1; i >= 0; i--) { //loop backwards
            int elapsedTime = (int) (currentTime - times.get(i).getValue());
            if (elapsedTime >= rewindTime) {
                if (i == times.size() - 1) {
                    return times.get(i).getKey();
                }
                return times.get(i).getKey().clone();
            }
        }
        return times.get(0).getKey().clone(); //ran out of historical data; return oldest entry
    }

    private void processPosition(Entity entity) {
        List<Pair<Location, Long>> times = trackedEntities.getOrDefault(entity, new CopyOnWriteArrayList<>());
        long currTime = System.currentTimeMillis();
        if(DEBUG && entity instanceof Player) {
            Player p = (Player) entity;
            p.sendMessage(ChatColor.GRAY + "[Lag Compensator] Your moves are being recorded: " + currTime);
        }
        times.add(new Pair<>(entity.getLocation(), currTime));
        if (times.size() > historySize) times.remove(0);
        trackedEntities.put(entity, times);
    }

    public int getHistorySize() {
        return historySize;
    }

    public int getPingOffset() {
        return pingOffset;
    }

    public Set<Entity> getPositionTrackedEntities() {
        return trackedEntities.keySet();
    }

    public List<Pair<Location, Long>> getHistoryLocations(Entity entity) {
        return trackedEntities.get(entity);
    }

    public List<Pair<Location, Long>> getHistoryPositionsOfEntity(Entity entity) {
        return trackedEntities.get(entity);
    }

    public boolean testNearPiston(Vector pos, World world, int ping) {

        long rewoundTime = System.currentTimeMillis() - ping;
        AABB playerBox = AABB.playerCollisionBox.clone();
        playerBox.translate(pos);
        playerBox.expand(1, 1, 1);

        for(PistonPush pistonPush : pistonPushes) {
            if(pistonPush.getWorld().equals(world)) {
                if(Math.abs(pistonPush.getTimestamp() - rewoundTime) < 500) {

                    Vector blockMin = pistonPush.getPosition().clone();
                    Vector blockMax = pistonPush.getPosition().clone().add(new Vector(1, 1, 1));
                    AABB basicBounds = new AABB(blockMin, blockMax);

                    if(playerBox.isColliding(basicBounds)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    @EventHandler
    public void pistonExtend(BlockPistonExtendEvent e) {
        World world = e.getBlock().getWorld();
        BlockFace bf = e.getDirection();
        Vector offset = new Vector(0, 0, 0);
        switch (bf) {
            case UP:
                offset.setY(1);
                break;
            case DOWN:
                offset.setY(-1);
                break;
            case EAST:
                offset.setX(1);
                break;
            case WEST:
                offset.setX(-1);
                break;
            case NORTH:
                offset.setZ(-1);
                break;
            case SOUTH:
                offset.setZ(1);
        }

        long currTime = System.currentTimeMillis();

        pistonPushes.add(new PistonPush(world, e.getBlock().getLocation().toVector().add(offset), bf, currTime));

        for(Block b : e.getBlocks()) {
            pistonPushes.add(new PistonPush(world, b.getLocation().toVector().add(offset), bf, currTime));
        }
    }

}
