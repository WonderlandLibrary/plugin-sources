package com.elevatemc.anticheat.base.check.impl.fight.backtrack;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
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

public class BackTrackA extends PacketCheck {
    /*
        Beanes Check ~ Yay!
        So basically what this does is make samples of how long it takes the client to see the relative move towards the client and how long it takes the client to see the relative move away from the client
        I would recommend reading this code to understand it
        To make the check more accurate BUT being more lenient & takes longer to flag,
        u can increase the MOVE_SAMPLES and ANALYSE_SAMPLES

        Now you might wonder, it might false flag on real lag! Well not really (its still theoretically possible but hey its way less likely)
        We also keep track of the average time it takes for the user to receive the relative move towards the client
        Now this number shouldn't really be messed up because backtrack still wants people moving towards the client seen as fast as possible
        So if we take the stdev of this and this number is pretty big it means the client is probably really lagging so thats when we exempt them from backtrack!

        maybe I explained it a bit dumb but it works
     */
    private static final int MOVE_SAMPLES = 25;
    private static final int ANALYSE_SAMPLES = 5;
    private long lastAway = System.currentTimeMillis();
    private int trackedId = -1;
    private boolean isCrazy = false; // If a player is crazy lagged
    private final Queue<Long> awayTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Long> closerTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Double> closerSamples = new ArrayDeque<>(ANALYSE_SAMPLES);
    private final Queue<Double> differenceSamples = new ArrayDeque<>(ANALYSE_SAMPLES);

    public BackTrackA(PlayerData playerData) {
        super(playerData, "Back Track A", "hahaha beanes funny code ez", new ViolationHandler(20, 3000L), Category.COMBAT, SubCategory.BACK_TRACK, 5);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                trackedId = wrapper.getEntityId();
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

            if (away) {
                awayTimes.add(receiveTime);

                lastAway = System.currentTimeMillis();
            } else {
                // The closer packet could be with the last away
                if (System.currentTimeMillis() - lastAway <= (isCrazy ? 300 : 50)) { // TODO: make this use trans id oto
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
                double diff = averageAway - averageClose;

                isCrazy = diff >= 350;

                differenceSamples.add(diff);

                if (differenceSamples.size() >= ANALYSE_SAMPLES) {

                    double differenceSamplesAvg = MathUtil.getAverage(differenceSamples);
                    if (differenceSamplesAvg > 30) { // I think we could even use a lower number, but let's be safe. Let's detect > 30 ms backtrack!
                        double stdevCloser = MathUtil.getStandardDeviation(closerSamples);
                        if (stdevCloser > (isCrazy ? 80 : 45)) { // Magic number to prevent lag cases, basically the stdev of moving closer should be low with backtrack and should be high with real lag
                            differenceSamples.clear();
                            closerSamples.clear();
                            return;
                        }
                        handleViolation(new DetailedPlayerViolation(this,"MS " + format(differenceSamplesAvg) + " DEV " + format(stdevCloser)));
                        staffAlert(new PlayerViolation(this));
                    }

                    differenceSamples.clear();
                    closerSamples.clear();
                    isCrazy = false;
                }
            }
        });
    }
}