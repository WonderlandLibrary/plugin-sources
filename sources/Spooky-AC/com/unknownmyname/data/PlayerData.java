/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 *  io.netty.channel.Channel
 *  io.netty.channel.Channel$Unsafe
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.EnumDirection
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  net.minecraft.server.v1_8_R3.PacketPlayInClientCommand
 *  net.minecraft.server.v1_8_R3.PacketPlayInClientCommand$EnumClientCommand
 *  net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow
 *  net.minecraft.server.v1_8_R3.PacketPlayInEntityAction
 *  net.minecraft.server.v1_8_R3.PacketPlayInEntityAction$EnumPlayerAction
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive
 *  net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity$EnumEntityUseAction
 *  net.minecraft.server.v1_8_R3.PacketPlayInWindowClick
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutRelEntityMove
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutRelEntityMoveLook
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutExplosion
 *  net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive
 *  net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.GameMode
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.player.PlayerRespawnEvent
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package com.unknownmyname.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.unknownmyname.check.AimCheck;
import com.unknownmyname.check.Check;
import com.unknownmyname.check.EventCheck;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.check.ReachCheck;
import com.unknownmyname.check.alert.Alert;
import com.unknownmyname.check.badpackets.BadPacketsC;
import com.unknownmyname.check.misc.MiscB;
import com.unknownmyname.data.ClientVersion;
import com.unknownmyname.data.DistanceData;
import com.unknownmyname.data.GolemEntity;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.data.ReachData;
import com.unknownmyname.data.StaticFakeEntity;
import com.unknownmyname.data.VelocityPacket;
import com.unknownmyname.injector.PacketDecoder;
import com.unknownmyname.injector.PacketEncoder;
import com.unknownmyname.main.Main;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.manager.PlayerManager;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MathUtil;
import com.unknownmyname.util.SafeReflection;
import io.netty.channel.Channel;
import java.lang.invoke.LambdaMetafactory;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class PlayerData {
    private /* synthetic */ double lastVelY;
    private final /* synthetic */ Map<Integer, Long> keepAliveMap;
    private /* synthetic */ int horizontalVelocityTicks;
    private /* synthetic */ boolean debug;
    private /* synthetic */ Queue<Alert> spoofedAlerts;
    private final /* synthetic */ EntityPlayer entityPlayer;
    private /* synthetic */ Integer sendT2;
    private final /* synthetic */ Queue<Integer> connectionFrequency;
    private /* synthetic */ double velX;
    private /* synthetic */ int lastSetSlot;
    private /* synthetic */ boolean inventoryOpen;
    private /* synthetic */ boolean abortedDigging;
    private /* synthetic */ Boolean sneaking;
    private /* synthetic */ int lastAttackTicks;
    private /* synthetic */ boolean receivedKeepAlive;
    private /* synthetic */ Queue<Alert> queuedAlerts;
    private /* synthetic */ int keepAliveTicks;
    private /* synthetic */ int nonMoveTicks;
    private /* synthetic */ EnumDirection diggingBlockFace;
    private final /* synthetic */ List<EventCheck> eventChecks;
    private /* synthetic */ int lastFakeEntityDamageTicks;
    private final /* synthetic */ Player player;
    private /* synthetic */ boolean t2;
    private /* synthetic */ double velZ;
    private /* synthetic */ boolean spoofBan;
    private /* synthetic */ PlayerLocation lastLastLocation;
    private /* synthetic */ PlayerLocation lastLocation;
    private /* synthetic */ int suffocationTicks;
    private /* synthetic */ PacketDecoder packetDecoder;
    private /* synthetic */ double lastVelX;
    private /* synthetic */ ClientVersion clientVersion;
    private final /* synthetic */ List<PacketCheck> packetChecks;
    private /* synthetic */ int packets;
    private /* synthetic */ int horizontalSpeedTicks;
    private /* synthetic */ MiscB pingSpoofCheck;
    private /* synthetic */ Boolean sprinting;
    private final /* synthetic */ Queue<BiConsumer<Integer, Double>> pingQueue;
    private final /* synthetic */ Queue<PlayerLocation> teleportList;
    private /* synthetic */ int teleportTicks;
    private final /* synthetic */ Queue<Double> recentConnectionFrequencies;
    private /* synthetic */ double lastVelZ;
    private final /* synthetic */ Map<Integer, Deque<PlayerLocation>> recentMoveMap;
    private /* synthetic */ int verticalVelocityTicks;
    private final /* synthetic */ List<AimCheck> aimChecks;
    private /* synthetic */ int lastTeleportTicks;
    private /* synthetic */ boolean banned;
    private /* synthetic */ long lastFast;
    private final /* synthetic */ List<Check> checks;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ boolean isBlocking;
    private /* synthetic */ int flyingTicks;
    private /* synthetic */ long lastWindowClick;
    private final /* synthetic */ PlayerLocation location;
    private /* synthetic */ int lastFakeEntityTicks;
    private /* synthetic */ boolean onGround;
    private /* synthetic */ PlayerData lastAttacked;
    private /* synthetic */ boolean enabled;
    private /* synthetic */ int lastAttackedId;
    private /* synthetic */ Check spoofBanCheck;
    private /* synthetic */ BlockPosition diggingBlock;
    private /* synthetic */ double velY;
    private /* synthetic */ int ping;
    private /* synthetic */ int lastInventoryOutTick;
    private /* synthetic */ int steerTicks;
    private /* synthetic */ int totalTicks;
    private /* synthetic */ PacketEncoder packetEncoder;
    private /* synthetic */ int groundTicks;
    private /* synthetic */ boolean checkSpoofing;
    private /* synthetic */ boolean swingDigging;
    @Deprecated
    private final /* synthetic */ Queue<PlayerLocation> recentMoveList;
    private /* synthetic */ boolean spawnedIn;
    private /* synthetic */ String brand;
    private /* synthetic */ int averagePing;
    private /* synthetic */ int lastInventoryTick;
    private final /* synthetic */ List<ReachCheck> reachChecks;
    private final /* synthetic */ List<MovementCheck> movementChecks;
    private /* synthetic */ long lastVapeString;
    private /* synthetic */ int velocityTicks;
    private /* synthetic */ long lastPosition;
    private /* synthetic */ long lastDelayed;
    private /* synthetic */ BadPacketsC keepAliveCheck;
    private /* synthetic */ boolean stoppedDigging;
    private /* synthetic */ boolean digging;
    private /* synthetic */ long lastFlying;
    private /* synthetic */ boolean bypass;
    private /* synthetic */ GolemEntity golemEntity;
    private /* synthetic */ boolean alerts;
    private /* synthetic */ StaticFakeEntity reachEntity;

    public boolean hasFast() {
        return this.hasFast(this.lastFlying);
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    static {
        PlayerData.I();
    }

    public int getVerticalVelocityTicks() {
        return this.verticalVelocityTicks;
    }

    private /* synthetic */ void lambda$23(Alert alert) {
        AlertsManager.getInstance().handleAlert(this, alert.getCheck(), alert.getVl());
    }

    public boolean isSpoofBan() {
        return this.spoofBan;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public long getTimeDifference(PlayerLocation move, long timestamp, int ping) {
        return Math.abs(timestamp - move.getTimestamp() - (long)ping - 50L);
    }

    public Map<Integer, Long> getKeepAliveMap() {
        return this.keepAliveMap;
    }

    public Queue<Double> getRecentConnectionFrequencies() {
        return this.recentConnectionFrequencies;
    }

    public Queue<PlayerLocation> getTeleportList() {
        return this.teleportList;
    }

    public EnumDirection getDiggingBlockFace() {
        return this.diggingBlockFace;
    }

    public Boolean getSprinting() {
        return this.sprinting;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public int getGroundTicks() {
        return this.groundTicks;
    }

    public void setSendT2(Integer sendT2) {
        this.sendT2 = sendT2;
    }

    public boolean isBypass() {
        return this.bypass;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }

    public int getHorizontalVelocityTicks() {
        return this.horizontalVelocityTicks;
    }

    public long getLastDelayed() {
        return this.lastDelayed;
    }

    public int getSuffocationTicks() {
        return this.suffocationTicks;
    }

    public int getMaxPingTicks() {
        int n;
        if (this.receivedKeepAlive) {
            n = (int)Math.ceil((double)Math.max(this.ping, this.averagePing) / 50.0);
            "".length();
            if (3 < 0) {
                throw null;
            }
        } else {
            n = 0x3C ^ 0x28;
        }
        return n + " ".length();
    }

    public double getVelX() {
        return this.velX;
    }

    public EntityPlayer getEntityPlayer() {
        return this.entityPlayer;
    }

    public void setSpoofBan(boolean spoofBan) {
        this.spoofBan = spoofBan;
    }

    private static /* synthetic */ Cuboid lambda$19(Cuboid hitbox) {
        return hitbox.expand(0.0325, 0.0, 0.0325);
    }

    public void setLastFakeEntityTicks(int lastFakeEntityTicks) {
        this.lastFakeEntityTicks = lastFakeEntityTicks;
    }

    public double getLastVelY() {
        return this.lastVelY;
    }

    public PacketDecoder getPacketDecoder() {
        return this.packetDecoder;
    }

    public List<AimCheck> getAimChecks() {
        return this.aimChecks;
    }

    public boolean isInventoryOpen() {
        return this.inventoryOpen;
    }

    public Queue<BiConsumer<Integer, Double>> getPingQueue() {
        return this.pingQueue;
    }

    public boolean hasLag() {
        return this.hasLag(this.lastFlying);
    }

    public int getVelocityTicks() {
        return this.velocityTicks;
    }

    public Map<Integer, Deque<PlayerLocation>> getRecentMoveMap() {
        return this.recentMoveMap;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setReachEntity(StaticFakeEntity reachEntity) {
        this.reachEntity = reachEntity;
    }

    public PlayerLocation getLocation(int ticks) {
        PlayerLocation playerLocation;
        int size = this.recentMoveList.size() - " ".length();
        if (size > ticks) {
            playerLocation = (PlayerLocation)Iterables.get(this.recentMoveList, (int)(size - ticks));
            "".length();
            if (-1 >= 1) {
                throw null;
            }
        } else {
            playerLocation = null;
        }
        return playerLocation;
    }

    public void setLastFakeEntityDamageTicks(int lastFakeEntityDamageTicks) {
        this.lastFakeEntityDamageTicks = lastFakeEntityDamageTicks;
    }

    private /* synthetic */ void lambda$24(Packet packet, long l, PacketCheck check) {
        check.handle(this.player, this, packet, l);
    }

    public void setSpoofBanCheck(Check spoofBanCheck) {
        this.spoofBanCheck = spoofBanCheck;
    }

    public Boolean getSneaking() {
        return this.sneaking;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public int getMoveTicks() {
        return (int)Math.floor((double)Math.min(this.ping, this.averagePing) / 125.0);
    }

    public List<EventCheck> getEventChecks() {
        return this.eventChecks;
    }

    public boolean isStoppedDigging() {
        return this.stoppedDigging;
    }

    public int getLastFakeEntityDamageTicks() {
        return this.lastFakeEntityDamageTicks;
    }

    public int getLastSetSlot() {
        return this.lastSetSlot;
    }

    private static /* synthetic */ DistanceData lambda$20(PlayerLocation playerLocation, PlayerLocation playerLocation2, Cuboid hitbox) {
        double d;
        double d2;
        double d3;
        double distanceNow = hitbox.distanceXZ(playerLocation.getX(), playerLocation.getZ());
        double distanceBefore = hitbox.distanceXZ(playerLocation2.getX(), playerLocation2.getZ());
        double x1 = playerLocation.getX() - hitbox.cX();
        double x2 = playerLocation2.getX() - hitbox.cX();
        double z1 = playerLocation.getZ() - hitbox.cZ();
        double z2 = playerLocation2.getZ() - hitbox.cZ();
        double y1 = playerLocation.getY() - hitbox.cY();
        double y2 = playerLocation2.getY() - hitbox.cY();
        if (Math.abs(x1) < Math.abs(x2)) {
            d = x1;
            "".length();
            if (4 <= 0) {
                throw null;
            }
        } else {
            d = x2;
        }
        if (Math.abs(z1) < Math.abs(z2)) {
            d3 = z1;
            "".length();
            if (2 < -1) {
                throw null;
            }
        } else {
            d3 = z2;
        }
        if (Math.abs(y1) < Math.abs(y2)) {
            d2 = y1;
            "".length();
            if (0 == 2) {
                throw null;
            }
        } else {
            d2 = y2;
        }
        return new DistanceData(hitbox, d, d3, d2, Math.min(distanceBefore, distanceNow));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @EventHandler
    public void handle(Event event) {
        timestamp = System.currentTimeMillis();
        if (event instanceof EntityDamageEvent) {
            entityDamageEvent = (EntityDamageEvent)event;
            if (entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                this.suffocationTicks = "".length();
                "".length();
                if (true < false) {
                    throw null;
                }
            }
        } else if (event instanceof PlayerRespawnEvent) {
            this.inventoryOpen = "".length();
        }
        var5_4 = this.eventChecks.iterator();
        "".length();
        if (3 >= 0) ** GOTO lbl20
        throw null;
lbl-1000: // 1 sources:
        {
            eventCheck = var5_4.next();
            eventCheck.handle(this.player, this, event, timestamp);
lbl20: // 2 sources:
            ** while (var5_4.hasNext())
        }
lbl21: // 1 sources:
    }

    public void setGolemEntity(GolemEntity golemEntity) {
        this.golemEntity = golemEntity;
    }

    public int getPackets() {
        return this.packets;
    }

    public String getBrand() {
        return this.brand;
    }

    public GolemEntity getGolemEntity() {
        return this.golemEntity;
    }

    public PlayerLocation getLastLastLocation() {
        return this.lastLastLocation;
    }

    public int getLastAttackedId() {
        return this.lastAttackedId;
    }

    public void setInventoryOpen(boolean inventoryOpen) {
        this.inventoryOpen = inventoryOpen;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void processPosition(PlayerLocation location, boolean hasMove) {
        if (this.enabled == false) return;
        timestamp = System.currentTimeMillis();
        if (this.recentMoveList.size() > (28 ^ 8)) {
            this.recentMoveList.poll();
        }
        location.setTimestamp(timestamp);
        this.recentMoveList.add((PlayerLocation)location);
        if (hasMove != false) return;
        var6_4 = PlayerManager.getInstance().getPlayers().values().iterator();
        "".length();
        if (4 >= -1) ** GOTO lbl36
        throw null;
lbl-1000: // 1 sources:
        {
            playerData = var6_4.next();
            recentMoveQueue = playerData.getRecentMoveMap().get(this.entityPlayer.getId());
            if (recentMoveQueue == null || recentMoveQueue.isEmpty()) continue;
            location = recentMoveQueue.peekLast();
            if (location == null) {
                "".length();
                if (-1 != 2) continue;
                throw null;
            }
            location = location.clone();
            location.setTickTime(playerData.getTotalTicks());
            location.setTimestamp(timestamp);
            recentMoveQueue.add((PlayerLocation)location);
            if (recentMoveQueue.size() <= (131 ^ 151)) {
                "".length();
                if (3 >= 1) continue;
                throw null;
            }
            recentMoveQueue.poll();
lbl36: // 5 sources:
            ** while (var6_4.hasNext())
        }
lbl37: // 1 sources:
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private /* synthetic */ void lambda$25(int var1_1, Cuboid var2_2, World var3_3) {
        block3 : {
            ticksOffset = var1_1 - this.totalTicks;
            blockAbove = "".length();
            if (!(this.velY > 0.0)) break block3;
            var7_6 = var2_2.getBlocks(var3_3).iterator();
            "".length();
            if (4 >= -1) ** GOTO lbl20
            throw null;
lbl-1000: // 1 sources:
            {
                block = var7_6.next();
                if (block.isEmpty()) {
                    "".length();
                    if (2 != 3) continue;
                    throw null;
                }
                blockAbove = " ".length();
                "".length();
                if (false < true) break;
                throw null;
lbl20: // 2 sources:
                ** while (var7_6.hasNext())
            }
        }
        if (blockAbove == 0 && this.lastVelY == 0.0 && this.ping > 0) {
            this.lastVelY = this.velY;
            this.verticalVelocityTicks = ticksOffset;
            "".length();
            if (true == true) return;
            throw null;
        }
        this.lastVelY = 0.0;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private /* synthetic */ void lambda$15(PlayerLocation var1_1, PlayerLocation var2_2, List var3_3, long var4_4, Integer ping, Double connection) {
        possibleMoves = var3_3.stream().filter((Predicate<PlayerLocation>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$16(long java.lang.Integer java.lang.Double com.unknownmyname.data.PlayerLocation ), (Lcom/unknownmyname/data/PlayerLocation;)Z)((long)var4_4, (Integer)ping, (Double)connection)).collect(Collectors.toList());
        if (possibleMoves.isEmpty() != false) return;
        earliestLocation = possibleMoves.stream().min(Comparator.comparingLong((java.util.function.ToLongFunction<PlayerLocation>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)J, getTimestamp(), (Lcom/unknownmyname/data/PlayerLocation;)J)())).get();
        index = var3_3.indexOf(earliestLocation);
        if (index > 0) {
            possibleMoves.add((PlayerLocation)var3_3.get(index - " ".length()));
        }
        distanceList = possibleMoves.stream().map((Function<PlayerLocation, Cuboid>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, hitbox(), (Lcom/unknownmyname/data/PlayerLocation;)Lcom/unknownmyname/util/Cuboid;)()).map((Function<Cuboid, Cuboid>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$19(com.unknownmyname.util.Cuboid ), (Lcom/unknownmyname/util/Cuboid;)Lcom/unknownmyname/util/Cuboid;)()).map((Function<Cuboid, DistanceData>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$20(com.unknownmyname.data.PlayerLocation com.unknownmyname.data.PlayerLocation com.unknownmyname.util.Cuboid ), (Lcom/unknownmyname/util/Cuboid;)Lcom/unknownmyname/data/DistanceData;)((PlayerLocation)var1_1, (PlayerLocation)var2_2)).collect(Collectors.toList());
        reachList = distanceList.stream().map((Function<DistanceData, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, getDist(), (Lcom/unknownmyname/data/DistanceData;)Ljava/lang/Double;)()).collect(Collectors.toList());
        locationArray = possibleMoves.toArray(new PlayerLocation["".length()]);
        horizontalData = distanceList.stream().min(Comparator.comparingDouble((java.util.function.ToDoubleFunction<DistanceData>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)D, getDist(), (Lcom/unknownmyname/data/DistanceData;)D)())).get();
        horizontal = horizontalData.getDist();
        movement = MathUtil.highest(reachList) - horizontal;
        angle = Math.toRadians(Math.min(MathUtil.getMinimumAngle(var1_1, locationArray), MathUtil.getMinimumAngle(var2_2, locationArray)));
        extra = Math.abs(Math.sin(angle)) * horizontal;
        vertical = Math.abs(Math.sin(Math.toRadians(Math.abs(var1_1.getPitch()))) * horizontal);
        v0 = new double["   ".length()];
        v0["".length()] = horizontal;
        v0[" ".length()] = vertical;
        v0["  ".length()] = extra;
        reach = MathUtil.hypot(v0) - 0.01;
        reachData = new ReachData(this, var1_1, horizontalData, movement, horizontal, extra, vertical, reach);
        var28 = this.reachChecks.iterator();
        "".length();
        if (4 > 1) ** GOTO lbl30
        throw null;
lbl-1000: // 1 sources:
        {
            reachCheck = var28.next();
            reachCheck.handle(this.player, this, reachData, System.currentTimeMillis());
lbl30: // 2 sources:
            ** while (var28.hasNext())
        }
lbl31: // 1 sources:
    }

    public void setPacketEncoder(PacketEncoder packetEncoder) {
        this.packetEncoder = packetEncoder;
    }

    private static String l(String obj, String key) {
        StringBuilder sb = new StringBuilder();
        char[] keyChars = key.toCharArray();
        int i = "".length();
        char[] arrc = obj.toCharArray();
        int n = arrc.length;
        for (int j = "".length(); j < n; ++j) {
            char c = arrc[j];
            sb.append((char)(c ^ keyChars[i % keyChars.length]));
            ++i;
            "".length();
            if (1 < 2) continue;
            throw null;
        }
        return sb.toString();
    }

    public List<Check> getChecks() {
        return this.checks;
    }

    public int getLastInventoryOutTick() {
        return this.lastInventoryOutTick;
    }

    public void setLastVelX(double lastVelX) {
        this.lastVelX = lastVelX;
    }

    public int getLastFakeEntityTicks() {
        return this.lastFakeEntityTicks;
    }

    public StaticFakeEntity getReachEntity() {
        return this.reachEntity;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setAlerts(boolean alerts) {
        this.alerts = alerts;
    }

    public double getVelY() {
        return this.velY;
    }

    public long getLastFlying() {
        return this.lastFlying;
    }

    public BadPacketsC getKeepAliveCheck() {
        return this.keepAliveCheck;
    }

    public void setCheckSpoofing(boolean checkSpoofing) {
        this.checkSpoofing = checkSpoofing;
    }

    public PlayerData getLastAttacked() {
        return this.lastAttacked;
    }

    public boolean hasLag(long timestamp) {
        if ((this.lastFlying == 0L || this.lastDelayed == 0L || timestamp - this.lastDelayed >= 150L) && System.currentTimeMillis() - this.lastFlying <= 90L) {
            return "".length() != 0;
        }
        return " ".length() != 0;
    }

    public MiscB getPingSpoofCheck() {
        return this.pingSpoofCheck;
    }

    public BlockPosition getDiggingBlock() {
        return this.diggingBlock;
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), "Blowfish");
            Cipher des = Cipher.getInstance("Blowfish");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isT2() {
        return this.t2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void handle(Packet packet, boolean serverBound) {
        block138 : {
            block145 : {
                block143 : {
                    block142 : {
                        block136 : {
                            block144 : {
                                block139 : {
                                    block140 : {
                                        block141 : {
                                            block137 : {
                                                if (this.enabled == false) return;
                                                if (!serverBound) break block136;
                                                timestamp = System.currentTimeMillis();
                                                if (!(packet instanceof PacketPlayInFlying)) break block137;
                                                packetPlayInFlying = (PacketPlayInFlying)packet;
                                                this.onGround = packetPlayInFlying.g();
                                                this.location.setTimestamp(timestamp);
                                                if (packetPlayInFlying.g()) {
                                                    this.location.setX(packetPlayInFlying.a());
                                                    this.location.setY(packetPlayInFlying.b());
                                                    this.location.setZ(packetPlayInFlying.c());
                                                }
                                                if (packetPlayInFlying.h()) {
                                                    this.location.setYaw(packetPlayInFlying.d());
                                                    this.location.setPitch(packetPlayInFlying.e());
                                                }
                                                this.location.setOnGround(packetPlayInFlying.f());
                                                clonedLocation = this.location.clone();
                                                new BukkitRunnable((PlayerLocation)clonedLocation, packetPlayInFlying){
                                                    private final /* synthetic */ PacketPlayInFlying val$packetPlayInFlying;
                                                    private final /* synthetic */ PlayerLocation val$clonedLocation;
                                                    {
                                                        this.val$clonedLocation = playerLocation;
                                                        this.val$packetPlayInFlying = packetPlayInFlying;
                                                    }

                                                    public void run() {
                                                        int n;
                                                        if (this.val$packetPlayInFlying.g() && PlayerData.this.packets < (0xA9 ^ 0xBD)) {
                                                            n = " ".length();
                                                            "".length();
                                                            if (true <= false) {
                                                                throw null;
                                                            }
                                                        } else {
                                                            n = "".length();
                                                        }
                                                        PlayerData.this.processPosition(this.val$clonedLocation, n != 0);
                                                    }
                                                }.runTask((Plugin)Main.getPlugin());
                                                if (this.player.getVehicle() == null && this.steerTicks > this.getPingTicks()) {
                                                    if (packetPlayInFlying.g()) {
                                                        v0 = "".length();
                                                        "".length();
                                                        if (-1 >= 1) {
                                                            throw null;
                                                        }
                                                    } else {
                                                        v0 = this.packets = this.packets + " ".length();
                                                        "".length();
                                                        if (!true) {
                                                            throw null;
                                                        }
                                                    }
                                                } else {
                                                    v0 = this.packets = "".length();
                                                }
                                                if (packetPlayInFlying.g()) {
                                                    v1 = "".length();
                                                    "".length();
                                                    if (4 != 4) {
                                                        throw null;
                                                    }
                                                } else {
                                                    v1 = this.nonMoveTicks = (this.nonMoveTicks = this.nonMoveTicks + " ".length());
                                                }
                                                if ((diff = timestamp - this.lastFlying) > 80L) {
                                                    this.lastDelayed = timestamp;
                                                }
                                                if (diff < 25L) {
                                                    this.lastFast = timestamp;
                                                }
                                                this.lastFlying = timestamp;
                                                if (this.isTeleporting()) {
                                                    v2 = "".length();
                                                    "".length();
                                                    if (3 >= 4) {
                                                        throw null;
                                                    }
                                                } else {
                                                    v2 = this.teleportTicks = this.teleportTicks + " ".length();
                                                }
                                                this.teleportTicks = v2;
                                                this.connectionFrequency.add((11 ^ 57) - (int)diff);
                                                if (this.abortedDigging) {
                                                    this.abortedDigging = "".length();
                                                    this.swingDigging = "".length();
                                                    this.digging = "".length();
                                                }
                                                if (this.stoppedDigging) {
                                                    this.stoppedDigging = "".length();
                                                    this.digging = "".length();
                                                }
                                                if ((teleport = this.teleportList.peek()) != null) {
                                                    lastPing = this.totalTicks - teleport.getTickTime();
                                                    if (packetPlayInFlying.g() && lastPing >= this.getMoveTicks() && teleport.sameLocation(this.location)) {
                                                        this.teleportList.poll();
                                                        this.lastVelY = 0.0;
                                                        this.velY = 0.0;
                                                        this.lastVelX = 0.0;
                                                        this.velX = 0.0;
                                                        this.lastVelZ = 0.0;
                                                        this.velZ = 0.0;
                                                        this.lastLocation = this.location.clone();
                                                        this.lastLastLocation = this.location.clone();
                                                        this.lastTeleportTicks = this.totalTicks;
                                                        this.spawnedIn = "".length();
                                                        return;
                                                    }
                                                    if (packetPlayInFlying.g()) {
                                                        v3 = this.getPingTicks() * "  ".length();
                                                        "".length();
                                                        if (3 == 1) {
                                                            throw null;
                                                        }
                                                    } else {
                                                        v3 = this.getPingTicks() * (82 ^ 86);
                                                    }
                                                    if (lastPing > v3) {
                                                        this.teleportList.poll();
                                                    }
                                                }
                                                if (this.lastVelY == 0.0 && this.velY != 0.0) {
                                                    if (packetPlayInFlying.f()) {
                                                        this.velY = 0.0;
                                                        "".length();
                                                        if (-1 != -1) {
                                                            throw null;
                                                        }
                                                    } else {
                                                        this.velY -= 0.08;
                                                        this.velY *= 0.98;
                                                    }
                                                }
                                                if (packetPlayInFlying.f() && this.teleportTicks > this.getPingTicks()) {
                                                    this.spawnedIn = " ".length();
                                                }
                                                this.totalTicks += " ".length();
                                                this.lastAttackTicks += " ".length();
                                                if (this.player.isFlying()) {
                                                    v4 = "".length();
                                                    "".length();
                                                    if (-1 < -1) {
                                                        throw null;
                                                    }
                                                } else {
                                                    v4 = this.flyingTicks = (this.flyingTicks = this.flyingTicks + " ".length());
                                                }
                                                if (packetPlayInFlying.f()) {
                                                    v5 = "".length();
                                                    "".length();
                                                    if (4 <= 1) {
                                                        throw null;
                                                    }
                                                } else {
                                                    v5 = this.groundTicks = this.groundTicks + " ".length();
                                                }
                                                this.groundTicks = v5;
                                                this.steerTicks += " ".length();
                                                this.velocityTicks += " ".length();
                                                this.horizontalSpeedTicks += " ".length();
                                                this.verticalVelocityTicks += " ".length();
                                                this.horizontalVelocityTicks += " ".length();
                                                this.keepAliveTicks += " ".length();
                                                this.suffocationTicks += " ".length();
                                                if (!this.lastLocation.sameLocation(this.location)) {
                                                    this.movementChecks.forEach((Consumer<MovementCheck>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$12(long com.unknownmyname.check.MovementCheck ), (Lcom/unknownmyname/check/MovementCheck;)V)((PlayerData)this, (long)timestamp));
                                                }
                                                if (!this.lastLocation.sameDirection(this.location)) {
                                                    this.aimChecks.forEach((Consumer<AimCheck>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$13(long com.unknownmyname.check.AimCheck ), (Lcom/unknownmyname/check/AimCheck;)V)((PlayerData)this, (long)timestamp));
                                                }
                                                this.lastLastLocation = this.lastLocation.clone();
                                                this.lastLocation = this.location.clone();
                                                lastPing = this.ping;
                                                if (!(this.getLastAttackTicks() > " ".length() || this.lastAttacked == null || this.hasLag() || this.hasFast() || this.teleportTicks <= this.getPingTicks() + "  ".length() || this.lastAttacked.getTeleportTicks() <= this.lastAttacked.getPingTicks() + this.getPingTicks() + "  ".length() || this.lastAttacked.hasLag(timestamp - (long)this.getPing()) || this.lastAttacked.hasFast(timestamp - (long)this.getPing()) || this.player.getGameMode() == GameMode.CREATIVE)) {
                                                    location = this.location.clone();
                                                    lastLocation = this.lastLastLocation.clone();
                                                    recentMovesQueue = this.recentMoveMap.get(this.lastAttackedId);
                                                    if (recentMovesQueue != null && !recentMovesQueue.isEmpty() && recentMovesQueue.size() > (105 ^ 99)) {
                                                        recentMoves = recentMovesQueue.stream().map((Function<PlayerLocation, PlayerLocation>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, clone(), (Lcom/unknownmyname/data/PlayerLocation;)Lcom/unknownmyname/data/PlayerLocation;)()).collect(Collectors.toList());
                                                        this.pingQueue.add((BiConsumer<Integer, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, lambda$15(com.unknownmyname.data.PlayerLocation com.unknownmyname.data.PlayerLocation java.util.List long java.lang.Integer java.lang.Double ), (Ljava/lang/Integer;Ljava/lang/Double;)V)((PlayerData)this, (PlayerLocation)location, (PlayerLocation)lastLocation, recentMoves, (long)timestamp));
                                                        "".length();
                                                        if (3 <= 0) {
                                                            throw null;
                                                        }
                                                    }
                                                }
                                                break block138;
                                            }
                                            if (!(packet instanceof PacketPlayInUseEntity)) break block139;
                                            packetPlayInUseEntity = (PacketPlayInUseEntity)packet;
                                            if (packetPlayInUseEntity.a() != PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) break block138;
                                            if (this.horizontalVelocityTicks > this.getPingTicks() / "  ".length() - "  ".length()) {
                                                this.velX *= 0.6;
                                                this.velZ *= 0.6;
                                            }
                                            this.lastAttackedId = SafeReflection.getAttackedEntity(packetPlayInUseEntity);
                                            entity = packetPlayInUseEntity.a(this.entityPlayer.getWorld());
                                            if (entity == null) break block140;
                                            if (!(entity instanceof EntityPlayer)) break block141;
                                            if (this.spoofBan && ThreadLocalRandom.current().nextDouble() < 0.25) {
                                                AlertsManager.getInstance().handleBan(this, this.spoofBanCheck, (boolean)"".length());
                                            }
                                            if (!this.checkSpoofing || !(ThreadLocalRandom.current().nextDouble() < 0.25)) break block142;
                                            "".length();
                                            if (3 == 2) {
                                                throw null;
                                            }
                                            ** GOTO lbl462
                                        }
                                        this.lastAttacked = null;
                                        "".length();
                                        if (4 != 4) {
                                            throw null;
                                        }
                                        break block143;
                                    }
                                    this.lastAttacked = null;
                                    break block143;
                                }
                                if (packet instanceof PacketPlayInKeepAlive) break block144;
                                if (packet instanceof PacketPlayInEntityAction) {
                                    packetPlayInEntityAction = (PacketPlayInEntityAction)packet;
                                    if (packetPlayInEntityAction.b() == PacketPlayInEntityAction.EnumPlayerAction.START_SPRINTING) {
                                        this.sprinting = (boolean)" ".length();
                                        "".length();
                                        if (2 >= 3) {
                                            throw null;
                                        }
                                    } else if (packetPlayInEntityAction.b() == PacketPlayInEntityAction.EnumPlayerAction.STOP_SPRINTING) {
                                        this.sprinting = (boolean)"".length();
                                        "".length();
                                        if (0 <= -1) {
                                            throw null;
                                        }
                                    } else if (packetPlayInEntityAction.b() == PacketPlayInEntityAction.EnumPlayerAction.START_SNEAKING) {
                                        this.sneaking = (boolean)" ".length();
                                        "".length();
                                        if (4 < 3) {
                                            throw null;
                                        }
                                    } else if (packetPlayInEntityAction.b() == PacketPlayInEntityAction.EnumPlayerAction.STOP_SNEAKING) {
                                        this.sneaking = (boolean)"".length();
                                        "".length();
                                        if (0 >= 2) {
                                            throw null;
                                        }
                                    }
                                } else if (packet instanceof PacketPlayInClientCommand) {
                                    packetPlayInClientCommand = (PacketPlayInClientCommand)packet;
                                    if (packetPlayInClientCommand.a() == PacketPlayInClientCommand.EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT) {
                                        this.inventoryOpen = " ".length();
                                        this.lastInventoryTick = this.totalTicks;
                                        "".length();
                                        if (-1 != -1) {
                                            throw null;
                                        }
                                    }
                                } else if (packet instanceof PacketPlayInCloseWindow) {
                                    this.inventoryOpen = "".length();
                                    "".length();
                                    if (false < false) {
                                        throw null;
                                    }
                                } else if (packet instanceof PacketPlayInWindowClick) {
                                    this.lastWindowClick = timestamp;
                                    "".length();
                                    if (2 <= 0) {
                                        throw null;
                                    }
                                } else if (packet instanceof PacketHandshakingInSetProtocol) {
                                    packetHandshakingInSetProtocol = (PacketHandshakingInSetProtocol)packet;
                                    protocol = SafeReflection.getProtocolVersion(packetHandshakingInSetProtocol);
                                    if (protocol == (22 ^ 57)) {
                                        v6 = ClientVersion.VERSION1_7;
                                        "".length();
                                        if (4 != 4) {
                                            throw null;
                                        }
                                    } else if (protocol == (42 ^ 47)) {
                                        v6 = ClientVersion.VERSION1_8;
                                        "".length();
                                        if (0 == -1) {
                                            throw null;
                                        }
                                    } else {
                                        v6 = ClientVersion.VERSION_UNSUPPORTED;
                                    }
                                    this.clientVersion = v6;
                                    this.player.setMetadata(PlayerData.I[37 ^ 35], (MetadataValue)new FixedMetadataValue((Plugin)Main.getPlugin(), (Object)this.clientVersion));
                                    "".length();
                                    if (0 == 3) {
                                        throw null;
                                    }
                                } else if (packet instanceof PacketPlayInBlockDig) {
                                    packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
                                    if (this.player.getGameMode() == GameMode.CREATIVE) {
                                        this.digging = "".length();
                                        this.swingDigging = "".length();
                                        "".length();
                                        if (4 == 2) {
                                            throw null;
                                        }
                                    } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                                        this.digging = " ".length();
                                        this.diggingBlock = packetPlayInBlockDig.a();
                                        this.diggingBlockFace = packetPlayInBlockDig.b();
                                        this.swingDigging = " ".length();
                                        this.abortedDigging = "".length();
                                        this.stoppedDigging = "".length();
                                        "".length();
                                        if (4 <= 2) {
                                            throw null;
                                        }
                                    } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                                        this.abortedDigging = " ".length();
                                        "".length();
                                        if (4 < 3) {
                                            throw null;
                                        }
                                    } else if (packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK) {
                                        this.stoppedDigging = " ".length();
                                        "".length();
                                        if (0 < -1) {
                                            throw null;
                                        }
                                    }
                                } else if (packet instanceof PacketPlayInSteerVehicle) {
                                    this.steerTicks = "".length();
                                    "".length();
                                    if (true <= false) {
                                        throw null;
                                    }
                                }
                                break block138;
                            }
                            packetPlayInKeepAlive = (PacketPlayInKeepAlive)packet;
                            protocol = packetPlayInKeepAlive.a();
                            this.receivedKeepAlive = " ".length();
                            sent = this.keepAliveMap.remove(protocol);
                            if (sent == null) break block145;
                            this.ping = newPing = (int)(timestamp - sent);
                            this.averagePing = (this.averagePing * "   ".length() + this.ping) / (45 ^ 41);
                            deviation = MathUtil.variance("".length(), this.connectionFrequency);
                            this.recentConnectionFrequencies.add(deviation);
                            if (this.recentConnectionFrequencies.size() > (107 ^ 111)) {
                                this.recentConnectionFrequencies.poll();
                                "".length();
                                if (-1 >= 2) {
                                    throw null;
                                }
                            }
                            ** GOTO lbl485
                        }
                        timestamp = System.currentTimeMillis();
                        if (packet instanceof PacketPlayOutPosition) {
                            timestamp = System.currentTimeMillis();
                            packetPlayOutPosition = (PacketPlayOutPosition)packet;
                            clonedLocation = SafeReflection.getLocation(timestamp, this.totalTicks, packetPlayOutPosition);
                            this.teleportList.add(clonedLocation);
                            this.teleportTicks = "".length();
                            this.lastPosition = timestamp;
                            "".length();
                            if (1 >= 3) {
                                throw null;
                            }
                        } else if (packet instanceof PacketPlayOutKeepAlive) {
                            playerConnection = this.entityPlayer.playerConnection;
                            SafeReflection.setNextKeepAliveTime(playerConnection, SafeReflection.getNextKeepAliveTime(playerConnection) + (178 ^ 150));
                            packetPlayOutKeepAlive = (PacketPlayOutKeepAlive)packet;
                            keepAliveNumber = SafeReflection.getKeepAliveId(packetPlayOutKeepAlive);
                            this.keepAliveMap.put(keepAliveNumber, timestamp);
                            "".length();
                            if (false) {
                                throw null;
                            }
                        } else if (packet instanceof PacketPlayOutEntityVelocity) {
                            packetPlayOutEntityVelocity = (PacketPlayOutEntityVelocity)packet;
                            velocityPacket = SafeReflection.getVelocity(packetPlayOutEntityVelocity);
                            if (velocityPacket.getEntityId() == this.player.getEntityId()) {
                                this.velY = (double)velocityPacket.getY() / 8000.0;
                                this.velX = (double)velocityPacket.getX() / 8000.0;
                                this.velZ = (double)velocityPacket.getZ() / 8000.0;
                                this.horizontalVelocityTicks = "".length();
                                currentTicks = this.totalTicks;
                                cuboid = new Cuboid(this.location).move(0.0, 1.5, 0.0).expand(0.5, 1.0, 0.5);
                                world = this.player.getWorld();
                                runnable = (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$25(int com.unknownmyname.util.Cuboid org.bukkit.World ), ()V)((PlayerData)this, (int)currentTicks, (Cuboid)cuboid, (World)world);
                                new BukkitRunnable(){

                                    public void run() {
                                        runnable.run();
                                    }
                                }.runTask((Plugin)Main.getPlugin());
                                v7 = new double["   ".length()];
                                v7["".length()] = this.velX;
                                v7[" ".length()] = this.velY;
                                v7["  ".length()] = this.velZ;
                                this.velocityTicks = Math.min(this.velocityTicks, "".length()) - (int)Math.ceil(Math.pow(MathUtil.hypotSquared(v7) * 2.0, 1.75) * 4.0);
                                v8 = new double["  ".length()];
                                v8["".length()] = this.velX;
                                v8[" ".length()] = this.velZ;
                                this.horizontalSpeedTicks = Math.min(this.horizontalSpeedTicks, "".length()) - (int)Math.ceil(Math.pow(MathUtil.hypotSquared(v8) * 2.0, 2.0) * 8.0);
                                "".length();
                                if (2 >= 3) {
                                    throw null;
                                }
                            }
                        } else if (packet instanceof PacketPlayOutExplosion) {
                            this.velocityTicks = "".length();
                            this.horizontalSpeedTicks = "".length();
                            "".length();
                            if (0 < -1) {
                                throw null;
                            }
                        } else if (packet instanceof PacketPlayOutOpenWindow) {
                            this.digging = "".length();
                            this.lastInventoryOutTick = this.totalTicks;
                            "".length();
                            if (0 == -1) {
                                throw null;
                            }
                        } else if (packet instanceof PacketPlayOutEntityTeleport) {
                            packetPlayOutEntityTeleport = (PacketPlayOutEntityTeleport)packet;
                            entityId = SafeReflection.getEntityId(packetPlayOutEntityTeleport);
                            recentMoveQueue = this.recentMoveMap.get(entityId);
                            if (recentMoveQueue == null) {
                                recentMoveQueue = new ConcurrentLinkedDeque<PlayerLocation>();
                                this.recentMoveMap.put(entityId, recentMoveQueue);
                            }
                            recentMoveQueue.add(SafeReflection.getLocation(System.currentTimeMillis(), this.totalTicks, packetPlayOutEntityTeleport));
                            if (recentMoveQueue.size() > (103 ^ 115)) {
                                recentMoveQueue.poll();
                                "".length();
                                if (2 != 2) {
                                    throw null;
                                }
                            }
                        } else if (!(packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMove) && !(packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook)) {
                            if (packet instanceof PacketPlayOutEntityDestroy) {
                                packetPlayOutEntityDestroy = (PacketPlayOutEntityDestroy)packet;
                                this.recentMoveMap.keySet().removeAll((Collection)Arrays.stream(SafeReflection.getEntities(packetPlayOutEntityDestroy)).boxed().collect(Collectors.toList()));
                                "".length();
                                if (3 == 0) {
                                    throw null;
                                }
                            } else if (packet instanceof PacketPlayOutSetSlot) {
                                this.lastSetSlot = this.totalTicks;
                                "".length();
                                if (0 == 4) {
                                    throw null;
                                }
                            }
                        } else {
                            packetPlayOutEntity = (PacketPlayOutEntity)packet;
                            entityId = SafeReflection.getEntityId(packetPlayOutEntity);
                            recentMoveQueue = this.recentMoveMap.get(entityId);
                            if (recentMoveQueue != null && !recentMoveQueue.isEmpty()) {
                                movement = SafeReflection.getMovement(packetPlayOutEntity);
                                location = recentMoveQueue.peekLast().clone().add(movement.getX(), movement.getY(), movement.getZ());
                                location.setTickTime(this.totalTicks);
                                location.setTimestamp(System.currentTimeMillis());
                                recentMoveQueue.add(location);
                                if (recentMoveQueue.size() > (89 ^ 77)) {
                                    recentMoveQueue.poll();
                                }
                            }
                        }
                        if (this.golemEntity == null) return;
                        this.golemEntity.handle(this, packet);
                        return;
lbl-1000: // 1 sources:
                        {
                            alert = this.spoofedAlerts.poll();
                            if (alert == null) {
                                this.checkSpoofing = "".length();
                                "".length();
                                if (3 == 3) continue;
                                throw null;
                            }
                            if (timestamp - alert.getTimestamp() >= TimeUnit.SECONDS.toMillis(15L)) {
                                "".length();
                                if (2 > 0) continue;
                                throw null;
                            }
                            AlertsManager.getInstance().getExecutorService().submit((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$23(com.unknownmyname.check.alert.Alert ), ()V)((PlayerData)this, (Alert)alert));
                            "".length();
                            if (3 >= 0) break;
                            throw null;
lbl462: // 3 sources:
                            ** while (this.checkSpoofing)
                        }
                    }
                    entityPlayer = (EntityPlayer)entity;
                    if (entityPlayer.playerConnection != null) {
                        v9 = PlayerManager.getInstance().getPlayers().get(entityPlayer.getBukkitEntity().getPlayer().getUniqueId());
                        "".length();
                        if (4 <= 3) {
                            throw null;
                        }
                    } else {
                        v9 = null;
                    }
                    this.lastAttacked = v9;
                    "".length();
                    if (1 >= 2) {
                        throw null;
                    }
                }
                this.lastAttackTicks = "".length();
                "".length();
                if (-1 != 1) break block138;
                throw null;
lbl-1000: // 1 sources:
                {
                    pingConsumer.accept(this.ping, deviation);
lbl485: // 2 sources:
                    ** while ((pingConsumer = this.pingQueue.poll()) != null)
                }
lbl486: // 1 sources:
                this.connectionFrequency.clear();
            }
            this.keepAliveTicks = "".length();
        }
        this.packetChecks.forEach((Consumer<PacketCheck>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$24(net.minecraft.server.v1_8_R3.Packet long com.unknownmyname.check.PacketCheck ), (Lcom/unknownmyname/check/PacketCheck;)V)((PlayerData)this, (Packet)packet, (long)timestamp));
        "".length();
        if (-1 < 2) return;
        throw null;
    }

    public void setT2(boolean t2) {
        this.t2 = t2;
    }

    public int getSteerTicks() {
        return this.steerTicks;
    }

    public void setPacketDecoder(PacketDecoder packetDecoder) {
        this.packetDecoder = packetDecoder;
    }

    public void setLastVelY(double lastVelY) {
        this.lastVelY = lastVelY;
    }

    public int getPingTicks() {
        int n;
        if (this.receivedKeepAlive) {
            n = (int)Math.ceil((double)this.getPing() / 50.0);
            "".length();
            if (0 == 2) {
                throw null;
            }
        } else {
            n = 0x13 ^ 7;
        }
        return n + " ".length();
    }

    public Queue<Alert> getSpoofedAlerts() {
        return this.spoofedAlerts;
    }

    public void setVelZ(double velZ) {
        this.velZ = velZ;
    }

    private /* synthetic */ void lambda$13(long l, AimCheck aimCheck) {
        aimCheck.handle(this.player, this, this.lastLocation, this.location, l);
    }

    static EntityPlayer access$200(PlayerData x0) {
        return x0.entityPlayer;
    }

    public List<MovementCheck> getMovementChecks() {
        return this.movementChecks;
    }

    public List<PacketCheck> getPacketChecks() {
        return this.packetChecks;
    }

    public Check getSpoofBanCheck() {
        return this.spoofBanCheck;
    }

    public long getLastVapeString() {
        return this.lastVapeString;
    }

    public boolean isCheckSpoofing() {
        return this.checkSpoofing;
    }

    public boolean isTeleporting() {
        boolean bl;
        if (this.teleportList.isEmpty()) {
            bl = "".length();
            "".length();
            if (0 == 3) {
                throw null;
            }
        } else {
            bl = " ".length();
        }
        return bl;
    }

    public boolean isAlerts() {
        return this.alerts;
    }

    public PlayerLocation getLastLocation() {
        return this.lastLocation;
    }

    public void setLastVelZ(double lastVelZ) {
        this.lastVelZ = lastVelZ;
    }

    public long getLastWindowClick() {
        return this.lastWindowClick;
    }

    public double getVelZ() {
        return this.velZ;
    }

    public PlayerLocation getLocation() {
        return this.location;
    }

    public boolean isBlocking() {
        return this.isBlocking;
    }

    public Queue<Integer> getConnectionFrequency() {
        return this.connectionFrequency;
    }

    public void setDigging(boolean digging) {
        this.digging = digging;
    }

    public double getLastVelX() {
        return this.lastVelX;
    }

    public long getLastPosition() {
        return this.lastPosition;
    }

    public boolean isDigging() {
        return this.digging;
    }

    public int getLastTeleportTicks() {
        return this.lastTeleportTicks;
    }

    public int getKeepAliveTicks() {
        return this.keepAliveTicks;
    }

    public int getTeleportTicks() {
        return this.teleportTicks;
    }

    private static /* synthetic */ boolean lambda$16(long l, Integer n, Double d, PlayerLocation move) {
        if ((double)(l - move.getTimestamp() - (long)n.intValue() - 75L) <= 25.0 + d) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public List<ReachCheck> getReachChecks() {
        return this.reachChecks;
    }

    public PlayerData(Player player, List<Check> checks) {
        int n;
        int n2;
        this.recentMoveList = new ConcurrentLinkedQueue<PlayerLocation>();
        this.recentMoveMap = new ConcurrentHashMap<Integer, Deque<PlayerLocation>>();
        this.teleportList = new ConcurrentLinkedQueue<PlayerLocation>();
        this.receivedKeepAlive = "".length();
        this.digging = "".length();
        this.swingDigging = "".length();
        this.abortedDigging = "".length();
        this.stoppedDigging = "".length();
        this.diggingBlock = null;
        this.diggingBlockFace = null;
        this.banned = "".length();
        this.enabled = " ".length();
        this.lastAttackTicks = 32 + 269 - -211 + 88;
        this.packets = "".length();
        this.nonMoveTicks = "".length();
        this.queuedAlerts = new ConcurrentLinkedQueue<Alert>();
        this.spoofedAlerts = new ConcurrentLinkedQueue<Alert>();
        this.checkSpoofing = "".length();
        this.spoofBan = "".length();
        this.spoofBanCheck = null;
        this.lastVapeString = 0L;
        this.keepAliveMap = new ConcurrentHashMap<Integer, Long>();
        this.pingQueue = new ConcurrentLinkedQueue<BiConsumer<Integer, Double>>();
        this.connectionFrequency = new ConcurrentLinkedQueue<Integer>();
        this.recentConnectionFrequencies = new ConcurrentLinkedQueue<Double>();
        this.lastPosition = 0L;
        this.teleportTicks = "".length();
        this.lastTeleportTicks = "".length();
        this.flyingTicks = "".length();
        this.groundTicks = "".length();
        this.velocityTicks = "".length();
        this.verticalVelocityTicks = -(0xD0 ^ 0xC4);
        this.horizontalVelocityTicks = "".length();
        this.totalTicks = "".length();
        this.keepAliveTicks = "".length();
        this.lastInventoryTick = "".length();
        this.lastInventoryOutTick = "".length();
        this.steerTicks = "".length();
        this.suffocationTicks = 0xF4 ^ 0x90;
        this.lastSetSlot = "".length();
        this.isBlocking = "".length();
        this.spawnedIn = "".length();
        this.sprinting = null;
        this.sneaking = null;
        this.lastFakeEntityTicks = "".length();
        this.lastFakeEntityDamageTicks = "".length();
        this.reachEntity = null;
        this.golemEntity = null;
        this.sendT2 = null;
        this.player = player;
        this.entityPlayer = ((CraftPlayer)player).getHandle();
        this.checks = checks;
        this.packetChecks = ImmutableList.copyOf((Collection)checks.stream().filter(check -> check instanceof PacketCheck).map(check -> (PacketCheck)check).collect(Collectors.toList()));
        this.movementChecks = ImmutableList.copyOf((Collection)checks.stream().filter(check -> check instanceof MovementCheck).map(check -> (MovementCheck)check).collect(Collectors.toList()));
        this.aimChecks = ImmutableList.copyOf((Collection)checks.stream().filter(check -> check instanceof AimCheck).map(check -> (AimCheck)check).collect(Collectors.toList()));
        this.eventChecks = ImmutableList.copyOf((Collection)checks.stream().filter(check -> check instanceof EventCheck).map(check -> (EventCheck)check).collect(Collectors.toList()));
        this.reachChecks = ImmutableList.copyOf((Collection)checks.stream().filter(check -> check instanceof ReachCheck).map(check -> (ReachCheck)check).collect(Collectors.toList()));
        this.keepAliveCheck = checks.stream().filter(check -> check instanceof BadPacketsC).findFirst().orElse(null);
        this.pingSpoofCheck = checks.stream().filter(check -> check instanceof MiscB).findFirst().orElse(null);
        this.location = new PlayerLocation(System.currentTimeMillis(), this.totalTicks, this.entityPlayer.locX, this.entityPlayer.locY, this.entityPlayer.locZ, this.entityPlayer.yaw, this.entityPlayer.pitch, this.entityPlayer.onGround);
        this.lastLocation = this.location.clone();
        this.lastLastLocation = this.location.clone();
        if (player.hasMetadata(I["".length()]) && player.hasPermission(I[" ".length()])) {
            n2 = " ".length();
            "".length();
            if (-1 != -1) {
                throw null;
            }
        } else {
            this.alerts = "".length();
            n2 = this.alerts ? 1 : 0;
        }
        if (player.hasMetadata(I["  ".length()]) && player.hasPermission(I["   ".length()])) {
            n = " ".length();
            "".length();
            if (3 <= 0) {
                throw null;
            }
        } else {
            n = "".length();
        }
        this.debug = n;
        int bl = n;
        if (player.hasMetadata(I[0x9C ^ 0x98])) {
            this.clientVersion = (ClientVersion)((Object)((MetadataValue)player.getMetadata(I[0x2A ^ 0x2F]).stream().findFirst().get()).value());
        }
    }

    private static void I() {
        I = new String[0x70 ^ 0x78];
        PlayerData.I["".length()] = PlayerData.I("yTqu4auC3NXOAACTmFbFEA==", "PuiMe");
        PlayerData.I[" ".length()] = PlayerData.I("Bh7Gc124KYk=", "gKFgQ");
        PlayerData.I["  ".length()] = PlayerData.I("Gt7VaSe1nyehlKQogOC/xg==", "WNxvm");
        PlayerData.I["   ".length()] = PlayerData.I("2S2jdtHh/DWYtfmv5bO/WA==", "WNABG");
        PlayerData.I[137 ^ 141] = PlayerData.l("\u000f\r!3)\u0018>>35\u001f\b'8", "laHVG");
        PlayerData.I[38 ^ 35] = PlayerData.l("\u000e8<\n\u0014\u0019\u000b#\n\b\u001e=:\u0001", "mTUoz");
        PlayerData.I[120 ^ 126] = PlayerData.l(":'\u001b'>-\u0014\u0004'\"*\"\u001d,", "YKrBP");
        PlayerData.I[40 ^ 47] = PlayerData.lI("0xOYWYg//1g=", "orpiG");
    }

    public boolean isBanned() {
        return this.banned;
    }

    public int getPing() {
        return this.ping;
    }

    public void fuckOff() {
        Channel channel = (Channel)SafeReflection.getLocalField(this.getEntityPlayer().playerConnection.networkManager.getClass(), (Object)this.getEntityPlayer().playerConnection.networkManager, I[0xA9 ^ 0xAE]);
        channel.unsafe().closeForcibly();
        this.enabled = "".length();
        throw new IllegalArgumentException();
    }

    public int getLastInventoryTick() {
        return this.lastInventoryTick;
    }

    public double getLastVelZ() {
        return this.lastVelZ;
    }

    public boolean isSpawnedIn() {
        return this.spawnedIn;
    }

    public long getLastFast() {
        return this.lastFast;
    }

    public boolean isSwingDigging() {
        return this.swingDigging;
    }

    public boolean isAbortedDigging() {
        return this.abortedDigging;
    }

    private /* synthetic */ void lambda$12(long l, MovementCheck movementCheck) {
        movementCheck.handle(this.player, this, this.lastLocation, this.location, l);
    }

    public int getLastAttackTicks() {
        return this.lastAttackTicks;
    }

    public int getFlyingTicks() {
        return this.flyingTicks;
    }

    public int getTotalTicks() {
        return this.totalTicks;
    }

    public Integer getSendT2() {
        return this.sendT2;
    }

    public int getAveragePing() {
        return this.averagePing;
    }

    public int getNonMoveTicks() {
        return this.nonMoveTicks;
    }

    public int getHorizontalSpeedTicks() {
        return this.horizontalSpeedTicks;
    }

    public boolean isReceivedKeepAlive() {
        return this.receivedKeepAlive;
    }

    public Queue<Alert> getQueuedAlerts() {
        return this.queuedAlerts;
    }

    public ClientVersion getClientVersion() {
        return this.clientVersion;
    }

    public boolean hasFast(long timestamp) {
        if (this.lastFlying != 0L && this.lastFast != 0L && timestamp - this.lastFast < 110L) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public PacketEncoder getPacketEncoder() {
        return this.packetEncoder;
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xBD ^ 0xB5), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public Queue<PlayerLocation> getRecentMoveList() {
        return this.recentMoveList;
    }

}

