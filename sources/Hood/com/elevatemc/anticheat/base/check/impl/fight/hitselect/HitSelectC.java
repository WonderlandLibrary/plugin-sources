package com.elevatemc.anticheat.base.check.impl.fight.hitselect;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientEntityAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;

public class HitSelectC extends PacketCheck {
    private EvictingList<Integer> flying = new EvictingList<>(20);
    private int clientTicks = -1;

    public HitSelectC(PlayerData playerData) {
        super(playerData, "HitSelect C", "Hit Select / timed tap ", new ViolationHandler(30, 3000L), Category.COMBAT, SubCategory.BACK_TRACK, 5);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ENTITY_ACTION) {
            WrapperPlayClientEntityAction action = new WrapperPlayClientEntityAction(event);

            if (action.getAction() == WrapperPlayClientEntityAction.Action.START_SPRINTING) {
                if (actionTracker.getLastAttack() > 20 && actionTracker.getLastAttack() < 60 && clientTicks < 10 && !actionTracker.isBlocking()) {
                    flying.add(clientTicks);
                    if (flying.isFull()) {

                        double stDev = MathUtil.getStandardDeviation(flying);

                        debug("select " + stDev);
                        flying.clear();
                    }
                }
            } else if (action.getAction() == WrapperPlayClientEntityAction.Action.STOP_SPRINTING) {
                clientTicks = 0;
            }
        } else if (PacketUtil.isFlying(event.getPacketType())) {
            ++clientTicks;
        }
    }
}
