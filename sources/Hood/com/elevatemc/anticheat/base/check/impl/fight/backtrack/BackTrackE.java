package com.elevatemc.anticheat.base.check.impl.fight.backtrack;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityRelativeMove;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityRelativeMoveAndRotation;

import java.util.ArrayDeque;
import java.util.Queue;

public class BackTrackE extends PacketCheck {
    private static final int MOVE_SAMPLES = 5;
    private static final int ANALYSE_SAMPLES = 3;
    private int trackedId = -1;
    private long lastAttack = System.currentTimeMillis();
    private final Queue<Long> awayTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Long> closerTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Double> closerSamples = new ArrayDeque<>(ANALYSE_SAMPLES);
    private final Queue<Double> differenceSamples = new ArrayDeque<>(ANALYSE_SAMPLES);

    // EWMA Variables
    private double ewma = 0.0;
    private final double alpha = 0.3; // Smoothing factor

        /*
        okay skidding beanes whole check but just changing the average calculation to test smth

        okay so EWMA -> a * currentSample + (1 - a) * previousEMWA
        A in this stance, represents the smoothing factor that controls the weight given
        to the most recent sample, meaning a higher alpha gives more weight to recent samples
        resulting in making the check more sensitive in a sudden change

        how does this help us out in Back Track?? basically this helps reduce the impact
        of outliers and focuses on consistent patterns.
        It averages the differences samples and makes the check more accurate,
        as the whole point of EWMA is to smooth out the difference between the average times it takes
        for packets to indicate movement away from and towards the player.
        shit explanations but it's not hard to understand
     */


    public BackTrackE(PlayerData playerData) {
        super(playerData, "Back Track E", "ultimate edition", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.BACK_TRACK, 8);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                trackedId = wrapper.getEntityId();
                lastAttack = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE ||
                event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE_AND_ROTATION) {

            int entityId = event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE
                    ? new WrapperPlayServerEntityRelativeMove(event).getEntityId()
                    : new WrapperPlayServerEntityRelativeMoveAndRotation(event).getEntityId();

            if (entityId == trackedId) {
                double deltaX = event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE
                        ? new WrapperPlayServerEntityRelativeMove(event).getDeltaX()
                        : new WrapperPlayServerEntityRelativeMoveAndRotation(event).getDeltaX();

                double deltaY = event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE
                        ? new WrapperPlayServerEntityRelativeMove(event).getDeltaY()
                        : new WrapperPlayServerEntityRelativeMoveAndRotation(event).getDeltaY();

                double deltaZ = event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE
                        ? new WrapperPlayServerEntityRelativeMove(event).getDeltaZ()
                        : new WrapperPlayServerEntityRelativeMoveAndRotation(event).getDeltaZ();

                processMove(deltaX, deltaY, deltaZ);
            }
        }
    }

    public void processMove(double deltaX, double deltaY, double deltaZ) {
        long sendTime = System.currentTimeMillis();

        playerData.getTransactionTracker().confirmPre(() -> {
            long now = System.currentTimeMillis();
            long receiveTime = now - sendTime;

            PlayerReachEntity tracked = playerData.getEntityTracker().getTrackedPlayer(trackedId);
            if (tracked == null) return;

            Vector3d playerPos = new Vector3d(playerData.getPositionTracker().getX(), playerData.getPositionTracker().getY(), playerData.getPositionTracker().getZ());
            double oldDistance = tracked.serverPos.distanceSquared(playerPos);
            double newDistance = tracked.serverPos.add(deltaX, deltaY, deltaZ).distanceSquared(playerPos);

            boolean away = newDistance > oldDistance;
            if (oldDistance == newDistance) return;

            // Update EWMA for latency tracking
            ewma = ewma == 0 ? receiveTime : alpha * receiveTime + (1 - alpha) * ewma;
            long smoothedTime = Math.round(ewma); // Convert EWMA value to long for storage

            if (away) {
                if (now - lastAttack > 4000 || now - lastAttack < 500) return;
                awayTimes.add(smoothedTime);  // Add smoothed EWMA value to the queue
            } else {
                if (closerTimes.size() > MOVE_SAMPLES * 2) return;
                closerTimes.add(smoothedTime);  // Add smoothed EWMA value to the queue
            }

            if (awayTimes.size() >= MOVE_SAMPLES && closerTimes.size() >= MOVE_SAMPLES) {
                double averageAway = MathUtil.getAverage(awayTimes);
                double averageClose = MathUtil.getAverage(closerTimes);

                awayTimes.clear();
                closerTimes.clear();

                closerSamples.add(averageClose);
                differenceSamples.add(averageAway - averageClose);

                if (differenceSamples.size() >= ANALYSE_SAMPLES) {
                    double differenceSamplesAvg = MathUtil.getAverage(differenceSamples);
                    double stdevCloser = MathUtil.getStandardDeviation(closerSamples);

                    debug("DIFF " + format(differenceSamplesAvg) + " Closer " + format(stdevCloser));

                    if (differenceSamplesAvg > 25 && stdevCloser < 45) {
                        if (increaseBuffer() > 1) {
                            handleViolation(new DetailedPlayerViolation(this, "MS " + format(differenceSamplesAvg) + " DEV" + format(stdevCloser)));
                        }
                    }

                    differenceSamples.clear();
                    closerSamples.clear();
                }
            }
        });
    }
}
