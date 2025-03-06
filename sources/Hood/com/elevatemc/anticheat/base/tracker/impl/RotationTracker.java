package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.math.client.ClientMath;
import com.elevatemc.anticheat.util.math.client.impl.OptifineMath;
import com.elevatemc.anticheat.util.math.client.impl.Values;
import com.elevatemc.anticheat.util.math.client.impl.VanillaMath;
import com.elevatemc.anticheat.util.mcp.AxisAlignedBB;
import com.elevatemc.anticheat.util.mcp.MovingObjectPosition;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;
import com.elevatemc.anticheat.util.mcp.Vec3;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Getter
public class RotationTracker extends Tracker {
    private static final boolean[] BOOLEANS = {false, true};

    private static final OptifineMath optifineMath = new OptifineMath();

    private static final VanillaMath vanillaMath = new VanillaMath();

    private final Set<Integer> candidates = new HashSet<>();

    private float yaw, pitch, lastYaw, lastPitch, deltaYaw,
            deltaPitch, lastDeltaYaw, lastDeltaPitch, joltYaw, joltPitch,
            lastJoltYaw, lastJoltPitch, yawAccel, pitchAccel,
            lastYawAccel, lastPitchAccel, divisorX, divisorY;

    private float gcdYaw, gcdPitch;

    private int sensitivity, calcSensitivity;

    private int ticksSinceRotation;
    @Setter
    private int zoomTicks, smoothZoomTicks;

    private double angle, lastAngle, lastLastAngle, mcpSensitivity;

    @Setter
    private boolean zooming;
    @Setter
    private long lastZoom;
    public RotationTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            WrapperPlayClientPlayerFlying playerFlying = new WrapperPlayClientPlayerFlying(event);

            lastYaw = yaw;
            lastPitch = pitch;

            lastJoltPitch = joltPitch;
            lastJoltYaw = joltYaw;

            lastDeltaYaw = deltaYaw;
            lastDeltaPitch = deltaPitch;

            lastYawAccel = yawAccel;
            lastPitchAccel = pitchAccel;

            lastLastAngle = lastAngle;
            lastAngle = angle;

            if (playerFlying.hasRotationChanged()) {
                yaw = playerFlying.getLocation().getYaw();
                pitch = playerFlying.getLocation().getPitch();

                deltaYaw = Math.abs(yaw - lastYaw);
                deltaPitch = Math.abs(pitch - lastPitch);

                yawAccel = Math.abs(deltaYaw - lastDeltaYaw);
                pitchAccel = Math.abs(deltaPitch - lastDeltaPitch);

                joltYaw = Math.abs(deltaYaw - lastDeltaYaw);
                joltPitch = Math.abs(deltaPitch - lastDeltaPitch);

                long expandedYaw = (long) (deltaYaw * MathUtil.EXPANDER);
                long previousExpandedYaw = (long) (lastDeltaYaw * MathUtil.EXPANDER);

                gcdYaw = (float) (MathUtil.getGcd(expandedYaw, previousExpandedYaw) / MathUtil.EXPANDER);

                long expandedPitch = (long) (deltaPitch * MathUtil.EXPANDER);
                long previousExpandedPitch = (long) (lastDeltaPitch * MathUtil.EXPANDER);

                gcdPitch = (float) (MathUtil.getGcd(expandedPitch, previousExpandedPitch) / MathUtil.EXPANDER);

                divisorX = MathUtil.getRotationGcd(deltaPitch, lastDeltaPitch);
                divisorY = MathUtil.getRotationGcd(deltaYaw, lastDeltaYaw);

                processSensitivity();
                processOptifine();

                ticksSinceRotation = 0;

                if (playerData.getActionTracker().getLastAttack() < 20) {
                    PlayerReachEntity entity = playerData.getEntityTracker().getTrackedPlayer(playerData.getActionTracker().getEntityId());

                    if (entity == null) {
                        angle = Double.NaN;
                        return;
                    }

                    AtomicReference<Double> minDistance = new AtomicReference<>(Double.MAX_VALUE);

                    Vec3 hitVec = null, center = null;

                    for (boolean fastMath : BOOLEANS) {
                        ClientMath clientMath = fastMath ? optifineMath : vanillaMath;

                        Vec3[] possibleEyeRotation = {
                                MathUtil.getVectorForRotation(playerData.getRotationTracker().getYaw(), playerData.getRotationTracker().getPitch(), clientMath),
                                MathUtil.getVectorForRotation(playerData.getRotationTracker().getLastYaw(), playerData.getRotationTracker().getLastPitch(), clientMath),
                                MathUtil.getVectorForRotation(playerData.getRotationTracker().getLastYaw(), playerData.getRotationTracker().getPitch(), clientMath),
                        };

                        for (boolean sneaking : BOOLEANS) {
                            for (Vec3 eyeRotation : possibleEyeRotation) {
                                Vec3 eyePos = new Vec3(
                                        playerData.getPositionTracker().getLastX(),
                                        playerData.getPositionTracker().getLastY() + MathUtil.getEyeHeight(sneaking),
                                        playerData.getPositionTracker().getLastZ()
                                );

                                Vec3 endReachRay = eyePos.addVector(
                                        eyeRotation.xCoord * 6.0D,
                                        eyeRotation.yCoord * 6.0D,
                                        eyeRotation.zCoord * 6.0D
                                );

                                AxisAlignedBB axisAlignedBB = PlayerUtil.getBoundingBox(
                                            entity.serverPos.getX(),
                                        entity.serverPos.getY(),
                                        entity.serverPos.getZ()
                                    );

                                axisAlignedBB = axisAlignedBB.expand(0.1F, 0.1F, 0.1F);
                                axisAlignedBB = axisAlignedBB.expand(0.005, 0.005, 0.005);

                                if (playerData.getPositionTracker().isPossiblyZeroThree()) {
                                    axisAlignedBB = axisAlignedBB.expand(0.03, 0.03, 0.03);
                                }

                                MovingObjectPosition intercept = axisAlignedBB.calculateIntercept(eyePos, endReachRay);

                                if (intercept != null) {
                                    double range = intercept.hitVec.distanceTo(eyePos);

                                    if (range < minDistance.get()) {
                                        minDistance.set(range);
                                        center = axisAlignedBB.getCenter();
                                        hitVec = intercept.hitVec;
                                    }
                                }
                            }
                        }
                    }

                    if (minDistance.get() == Double.MAX_VALUE || center == null || hitVec == null)
                        return;

                    Vec3 eyePos = new Vec3(
                            playerData.getPositionTracker().getLastX(),
                            playerData.getPositionTracker().getLastY() + MathUtil.getEyeHeight(false),
                            playerData.getPositionTracker().getLastZ()
                    );

                    Vector boundingBoxDiffXZ = new Vector(eyePos.xCoord - center.xCoord, 0, eyePos.zCoord - center.zCoord);
                    Vector hitVecDiffXZ = new Vector(eyePos.xCoord - hitVec.xCoord, 0, eyePos.zCoord - hitVec.zCoord);

                    angle = hitVecDiffXZ.angle(boundingBoxDiffXZ);
                }
            } else {
                ++ticksSinceRotation;
            }
        }
    }

    public void processSensitivity() {
        float pitch = getPitch();
        float lastPitch = getLastPitch();

        float yaw = getYaw();
        float lastYaw = getLastYaw();

        if (Math.abs(pitch) != 90.0f) {
            float distanceY = pitch - lastPitch;

            double error = Math.max(Math.abs(pitch), Math.abs(lastPitch)) * 3.814697265625E-6;

            computeSensitivity(distanceY, error);
        }

        float distanceX = circularDistance(yaw, lastYaw);

        double error = Math.max(Math.abs(yaw), Math.abs(lastYaw)) * 3.814697265625E-6;

        computeSensitivity(distanceX, error);

        if (candidates.size() == 1) {
            calcSensitivity = candidates.iterator().next();
            sensitivity = 200 * calcSensitivity / 143;
        } else {
            sensitivity = -1;

            forEach(candidates::add);
        }

        if (sensitivity != -1) {
            mcpSensitivity = Values.SENSITIVITY_MCP_VALUES.get(sensitivity);
        }
    }

    public void computeSensitivity(double delta, double error) {
        double start = delta - error;
        double end = delta + error;

        forEach(s -> {
            double f0 = ((double) s / 142.0) * 0.6 + 0.2;
            double f = (f0 * f0 * f0 * 8.0) * 0.15;

            int pStart = (int) Math.ceil(start / f);
            int pEnd = (int) Math.floor(end / f);

            if (pStart <= pEnd) {
                for (int p = pStart; p <= pEnd; p++) {
                    double d = p * f;

                    if (!(d >= start && d <= end)) {
                        candidates.remove(s);
                    }
                }
            } else {
                candidates.remove(s);
            }
        });
    }

    public float circularDistance(float a, float b) {
        float d = Math.abs(a % 360.0f - b % 360.0f);
        return d < 180.0f ? d : 360.0f - d;
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int s = 0; s <= 143; s++) {
            consumer.accept(s);
        }
    }
    private void processOptifine() {
        double yawDiff = Math.abs(this.deltaYaw - this.lastDeltaYaw);
        double pitchDiff = Math.abs(this.deltaPitch - this.lastDeltaPitch);
        if ((pitchDiff < 0.009400162506103517 && pitchDiff > 0.0) || (yawDiff < 0.009400162506103517 && yawDiff > 0.0)) {
            ++this.zoomTicks;
        }
        if (this.zoomTicks >= 2) {
            this.setZooming(true);
            this.setLastZoom(System.currentTimeMillis());
            this.setZoomTicks(0);
        }
        else {
            if ((this.zooming && pitchDiff > 0.009400162506103517) || (this.zooming && pitchDiff == 0.0) || (this.zooming && yawDiff == 0.0) || (this.zooming && yawDiff > 0.009400162506103517)) {
                ++this.smoothZoomTicks;
            }
            else if (this.smoothZoomTicks > 0 && this.zooming) {
                --this.smoothZoomTicks;
            }
            if (this.smoothZoomTicks > 4) {
                this.setZooming(false);
                this.smoothZoomTicks = 0;
            }
        }
    }

}