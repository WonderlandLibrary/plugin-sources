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

public class BackTrackB extends PacketCheck {
    /*
        read backtrack A (this is slinky edition)
     */
    private static final int MOVE_SAMPLES = 5;
    private static final int ANALYSE_SAMPLES = 3;
    private int trackedId = -1;
    private long lastAttack = System.currentTimeMillis();
    private final long cooldown = System.currentTimeMillis();
    private final Queue<Long> awayTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Long> closerTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Double> closerSamples = new ArrayDeque<>(ANALYSE_SAMPLES);
    private final Queue<Double> differenceSamples = new ArrayDeque<>(ANALYSE_SAMPLES);

    public BackTrackB(PlayerData playerData) {
        super(playerData, "Back Track B", "slinky edition", new ViolationHandler(25, 3000L), Category.COMBAT, SubCategory.BACK_TRACK, 8);
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
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE) {
            WrapperPlayServerEntityRelativeMove wrapper = new WrapperPlayServerEntityRelativeMove(event);

            if (wrapper.getEntityId() == trackedId) {
                processMove(wrapper.getDeltaX(), wrapper.getDeltaY(), wrapper.getDeltaZ());
            }

        } else if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE_AND_ROTATION) {
            WrapperPlayServerEntityRelativeMoveAndRotation wrapper = new WrapperPlayServerEntityRelativeMoveAndRotation(event);

            if (wrapper.getEntityId() == trackedId) {
                processMove(wrapper.getDeltaX(), wrapper.getDeltaY(), wrapper.getDeltaZ());
            }
        }
    }

    public void processMove(double deltaX, double deltaY, double deltaZ) {

        long send = System.currentTimeMillis();

        playerData.getTransactionTracker().confirmPre(() -> {
            long now = System.currentTimeMillis();
            long receiveTime = now - send;

            PlayerReachEntity tracked = playerData.getEntityTracker().getTrackedPlayer(trackedId);

            if (tracked == null) return;

            Vector3d playerPos = new Vector3d(playerData.getPositionTracker().getX(), playerData.getPositionTracker().getY(), playerData.getPositionTracker().getZ());
            double oldDistance = tracked.serverPos.distanceSquared(playerPos);
            double newDistance = tracked.serverPos.add(deltaX, deltaY, deltaZ).distanceSquared(playerPos);

            boolean away = newDistance > oldDistance;

            if (oldDistance == newDistance) {
                return;
            }

            // Bukkit.broadcastMessage("oldDi")

            if (away) {
                if (now - lastAttack > 4_000) {
                    return;
                }

                if (now - lastAttack < 500) {
                    return;
                }
                awayTimes.add(receiveTime);
                // cooldown = System.currentTimeMillis() + 5_000; // Assume the worst -> 5s cooldown
            } else {
                // Prevent closer filling too much because away fills very slowly
                if (closerTimes.size() > MOVE_SAMPLES * 2) {
                    return;
                }
                closerTimes.add(receiveTime);
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
                    if (differenceSamplesAvg > 30) { // I think we could even use a lower number, but let's be safe. Let's detect > 30 ms backtrack!
                        double stdevCloser = MathUtil.getStandardDeviation(closerSamples);
                        if (stdevCloser > 45) { // Magic number to prevent lag cases, basically the stdev of moving closer should be low with backtrack and should be high with real lag
                            differenceSamples.clear();
                            closerSamples.clear();
                            return;
                        }
                        if (increaseBuffer() > 5) handleViolation(new DetailedPlayerViolation(this,"MS " + format(differenceSamplesAvg) + " DEV" + format(stdevCloser)));
                    }

                    differenceSamples.clear();
                    closerSamples.clear();
                }
            }
        });








/*
        if (squaredNewDistance == squaredOldDistance) {
            return;
        }

        boolean away = squaredNewDistance < squaredOldDistance;
        long send = System.currentTimeMillis();

        Bukkit.broadcastMessage("squaredOldDistance=" + squaredOldDistance);
        Bukkit.broadcastMessage("squaredNewDistance=" + squaredNewDistance);
        Bukkit.broadcastMessage("away=" + away);

        playerData.getTransactionTracker().confirmPre(() -> {
            long receiveTime = System.currentTimeMillis() - send;

            if (away) {
                // Cooldown in progress */
                /*if (cooldown - System.currentTimeMillis() > 0) {
                    Bukkit.broadcastMessage("cooldown in progress");
                    return;
                } */
/*
                if (System.currentTimeMillis() - lastAttack > 4_000) {
                    Bukkit.broadcastMessage("not attacked anymore!");
                    return;
                }

                if (System.currentTimeMillis() - lastAttack < 500) {
                    Bukkit.broadcastMessage("half a second cooldown!");
                    return;
                }


                Bukkit.broadcastMessage("added away!");
                awayTimes.add(receiveTime);
                // cooldown = System.currentTimeMillis() + 5_000; // Assume the worst -> 5s cooldown
            } else {
                // Prevent closer filling too much because away fills very slowly
                if (closerTimes.size() > MOVE_SAMPLES * 2) {
                    return;
                }

                Bukkit.broadcastMessage("added closer!");
                closerTimes.add(receiveTime);
            }

            double averageAway = MathUtil.getAverage(awayTimes);
            double averageClose = MathUtil.getAverage(closerTimes);



            if (awayTimes.size() >= MOVE_SAMPLES && closerTimes.size() >= MOVE_SAMPLES) {
                Bukkit.broadcastMessage("AVG AWAY=" + averageAway);
                Bukkit.broadcastMessage("AVG CLOSE=" + averageClose);
                Bukkit.broadcastMessage("DIFF=" + (averageAway - averageClose));
                Bukkit.broadcastMessage("samples cleared!");
                awayTimes.clear();
                closerTimes.clear();
                closerSamples.add(averageClose);
                differenceSamples.add(averageAway - averageClose);

                if (differenceSamples.size() >= ANALYSE_SAMPLES) {
                    Bukkit.broadcastMessage("avg of diff: " + MathUtil.getAverage(differenceSamples));
                    Bukkit.broadcastMessage("stdev of closer: " + MathUtil.getStandardDeviation(closerSamples));



                    double differenceSamplesAvg = MathUtil.getAverage(differenceSamples);
                    if (differenceSamplesAvg > 30) { // I think we could even use a lower number, but let's be safe. Let's detect > 30 ms backtrack!
                        double stdevCloser = MathUtil.getStandardDeviation(closerSamples);
                        if (stdevCloser > 45) { // Magic number to prevent lag cases, basically the stdev of moving closer should be low with backtrack and should be high with real lag
                            differenceSamples.clear();
                            closerSamples.clear();
                            Bukkit.broadcastMessage("gave leniency away!");
                            return;
                        }

                        handleViolation(new DetailedPlayerViolation(this,"ms=" + differenceSamplesAvg + " stdevCloser=" + stdevCloser));
                    }

                    differenceSamples.clear();
                    closerSamples.clear();
                }
            }
        });  */
    }
}