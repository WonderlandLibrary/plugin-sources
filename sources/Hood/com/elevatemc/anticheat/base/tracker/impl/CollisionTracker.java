package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.block.BlockUtil;
import com.elevatemc.anticheat.util.mcp.AxisAlignedBB;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Getter
public class CollisionTracker extends Tracker {

    private boolean onSlime, lastOnSlime,
            onIce, lastOnIce,
            onFence, lastOnFence, lastLastOnFence,
            inWeb, lastInWeb,
            inWater, lastInWater, lastLastInWater,
            inLava, lastInLava, lastLastInLava,
            onClimbable, lastOnClimbable, lastLastOnClimbable,
            onStairs, lastOnStairs,
            onSlabs, lastOnSlabs,
            onSoulSand, lastOnSoulSand,
            verticallyColliding, lastVerticallyColliding, lastLastVerticallyColliding,
            nearWall, lastNearWall,
            nearPiston, lastNearPiston,
            clientGround, lastClientGround, lastLastClientGround,
            serverGround, lastServerGround, lastLastServerGround,
            inAir, onTrapdoor, onLadder;

    private boolean collided = false;

    private final Predicate<Double> collision = position -> position % 0.015625D < 1.0E-8D;

    private AxisAlignedBB boundingBox, lastBoundingBox, lastlastBoundingBox;
    private float friction, lastFriction ,lastLastFriction;

    private List<Material> nearbyBlocks;
    private List<Material> blocksAbove;
    private List<Material> blocksSide;
    private World world;

    private int clientAirTicks, clientGroundTicks, serverAirTicks, serverGroundTicks;

    public CollisionTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            WrapperPlayClientPlayerFlying playerFlying = new WrapperPlayClientPlayerFlying(event);

            actualize();

            clientGround = playerFlying.isOnGround();

            if (playerFlying.hasPositionChanged()) {
                friction = onIce ? onSlime ?  0.98F : 0.8F : 0.6F;

                boundingBox = PlayerUtil.getBoundingBox(
                                playerData.getPositionTracker().getX(),
                                playerData.getPositionTracker().getY(),
                                playerData.getPositionTracker().getZ()).
                        expand(0.001, 0.001, 0.001);

                if (lastBoundingBox != null) {
                    serverGround = playerData.getPositionTracker().getY() % 0.015625 == 0;
                }

                handleCollisions();
            }
            handleFlyingTicks();
        }
    }

    public synchronized void handleCollisions() {
        this.world = playerData.getPlayer().getWorld();
        this.nearbyBlocks = BlockUtil.getNearbyBlocksAsync(new Location(this.world, this.playerData.getPositionTracker().getX(), this.playerData.getPositionTracker().getY(), this.playerData.getPositionTracker().getZ()), 1);
        this.blocksAbove = BlockUtil.getBlocksAbove(playerData);
        this.blocksSide = BlockUtil.getBlocksSide(playerData);
        this.verticallyColliding = !this.blocksAbove.stream().allMatch(BlockUtil::isAir);
        this.nearWall = !this.blocksSide.stream().allMatch(BlockUtil::isAir);
        if (this.nearbyBlocks != null) {
            final Supplier<Stream<Material>> supplier = (() -> this.nearbyBlocks.stream());
            this.inAir = supplier.get().allMatch(BlockUtil::isAir);
            this.nearPiston = supplier.get().anyMatch(BlockUtil::isPiston);
            this.onSlime = supplier.get().anyMatch(BlockUtil::isSlime);
            this.onSoulSand = supplier.get().anyMatch(BlockUtil::isSoulSand);
            this.onStairs = supplier.get().anyMatch(BlockUtil::isStair);
            this.onSlabs = supplier.get().anyMatch(BlockUtil::isSlab);
            this.onIce = supplier.get().anyMatch(BlockUtil::isIce);
            this.inWeb = supplier.get().anyMatch(BlockUtil::isWeb);
            this.onTrapdoor = supplier.get().anyMatch(BlockUtil::isTrapdoor);
            this.onClimbable = BlockUtil.isClimbable(playerData);
            this.onFence = BlockUtil.isFence(playerData);
            this.onLadder = BlockUtil.isLadder(playerData);
            this.inWater = this.inLava = PlayerUtil.isLiquid(playerData);
        }
    }

    private void handleFlyingTicks() {
        if (this.clientGround) {
            ++this.clientGroundTicks;
        } else {
            this.clientGroundTicks = 0;
        }
        if (!this.clientGround) {
            ++this.clientAirTicks;
        } else {
            this.clientAirTicks = 0;
        }
        if (this.serverGround) {
            ++this.serverGroundTicks;
        } else {
            this.serverGroundTicks = 0;
        }
        if (this.inAir) {
            ++this.serverAirTicks;
        } else {
            this.serverAirTicks = 0;
        }
    }


    private void actualize() {
        lastOnFence = onFence;
        lastOnSlime = onSlime;
        lastOnIce = onIce;
        lastInWeb = inWeb;
        lastLastOnFence = lastOnFence;
        lastLastInWater = lastInWater;
        lastLastFriction = lastFriction;
        lastLastClientGround = lastClientGround;
        lastLastServerGround = lastServerGround;
        lastFriction = friction;
        lastInWater = inWater;
        lastLastInLava = lastInLava;
        lastInLava = inLava;
        lastLastOnClimbable = lastOnClimbable;
        lastOnClimbable = onClimbable;
        lastOnStairs = onStairs;
        lastOnSlabs = onSlabs;
        lastOnSoulSand = onSoulSand;
        lastVerticallyColliding = verticallyColliding;
        lastLastVerticallyColliding = lastVerticallyColliding;
        lastNearWall = nearWall;
        lastNearPiston = nearPiston;
        lastlastBoundingBox = lastBoundingBox;
        lastBoundingBox = boundingBox;
        lastClientGround = clientGround;
        lastServerGround = serverGround;
    }

    public void setBarrierCollided(boolean collided) {
        this.collided = collided;
    }
}