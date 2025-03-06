package com.elevatemc.anticheat.base.check.impl.fight.backtrack;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityRelativeMove;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityRelativeMoveAndRotation;

public class BackTrackD extends PacketCheck {

    /*
        So, backtrack....it's due time that I've given this a shot, but I've literally had 0 time whatsoever.
        as we know, how backtrack works is it stops incoming packets when the attacked entity is moving away,
        ping spikes, and its overall an advantage.
        the point of this check if the following, we're gonna check the average ping of when the player is attacking
        the average ping when the player is NOT attacking, check for delayed flying packets whilst the players'
        connection is what to be considered "stable"
        I don't know how to explain the check but reading it should make you understand pretty easily.
     */

    private int trackedId = -1;
    private final EvictingList<Long> combatPing = new EvictingList<>(50), nonCombatPing = new EvictingList<>(50);

    private final EvictingList<Integer> closeDelay = new EvictingList<>(50), awayDelay = new EvictingList<>(50);


    public BackTrackD(PlayerData playerData) {
        super(playerData, "Back Track D", "BackTrack", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.BACK_TRACK, 10);
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
        playerData.getTransactionTracker().confirmPre(() -> {

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
                nonCombatPing.add(keepAliveTracker.getKeepAlivePing());
                awayDelay.add(positionTracker.getDelayedFlyingTicks());
            } else {
                combatPing.add(keepAliveTracker.getKeepAlivePing());
                closeDelay.add(positionTracker.getDelayedFlyingTicks());
            }

            boolean everyoneIsFull = nonCombatPing.isFull() && combatPing.isFull() && closeDelay.isFull() && awayDelay.isFull();

            if (everyoneIsFull) {
                double averageAway = MathUtil.getAverage(nonCombatPing);
                double averageClose = MathUtil.getAverage(combatPing);

                int difference = (int) (averageClose - averageAway);

                int awayTicks = (int) MathUtil.getAverage(awayDelay);
                int closeTicks = (int) MathUtil.getAverage(closeDelay);

                debug("Ticks A|C (" + awayTicks + " | " + closeTicks +") Average A|C (" + averageAway + " | " + averageClose + ") difference " + difference);

                if (difference > 25 && (closeTicks > 0 || awayTicks > 0)) {
                    handleViolation(new DetailedPlayerViolation(this, "A|C (" + awayTicks + " | " + closeTicks +") AVG A|C (" + format(averageAway) + " | " + format(averageClose) + ") DIFF " + difference));
                }
                nonCombatPing.clear();
                combatPing.clear();
                awayDelay.clear();
                closeDelay.clear();
            }
        });
    }
}