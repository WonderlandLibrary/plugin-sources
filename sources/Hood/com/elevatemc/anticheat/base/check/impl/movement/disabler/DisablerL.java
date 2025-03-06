package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientWindowConfirmation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowConfirmation;

import java.util.Deque;
import java.util.LinkedList;

public class DisablerL extends PacketCheck {

    private boolean sent;
    private long time;
    private long timeExpected, timeStamp;
    private final Deque<Short> expectedIds = new LinkedList<>();

    public DisablerL(PlayerData playerData) {
        super(playerData, "Disabler L", "Transaction Disabler", new ViolationHandler(3, 30000L), Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION) {
            WrapperPlayClientWindowConfirmation trx = new WrapperPlayClientWindowConfirmation(event);
            if (trx.getWindowId() != 0 || !trx.isAccepted()) {
                return;
            }
            if (!this.expectedIds.isEmpty()) {
                short expected;
                while (this.expectedIds.peekFirst() != null && (expected = this.expectedIds.pollFirst()) != trx.getActionId()) {
                    if (System.currentTimeMillis() - playerData.lastJoin < 3000L) continue;
                    handleViolation(new DetailedPlayerViolation(this, "E " + expected + " G " + trx.getActionId()));
                    staffAlert(new PlayerViolation(this));
                }
                this.sent = false;
            }
        } else if (PacketUtil.isFlying(event.getPacketType())) {
            this.time += 50L;
            if (this.sent && !this.expectedIds.isEmpty()) {
                long flagTime = playerData.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9) ? System.currentTimeMillis() : this.time;
                boolean flag = playerData.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9) ? flagTime > this.timeExpected && !Hood.get().isLagging() : flagTime > this.timeExpected;
                if (flag && !playerData.getPlayer().isDead() && System.currentTimeMillis() - playerData.lastJoin > 3000L) {
                    handleViolation(new DetailedPlayerViolation(this, "T-E " + timeExpected + " T-R " + flagTime));
                    staffAlert(new PlayerViolation(this));
                }
            }
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.WINDOW_CONFIRMATION) {
            timeStamp = System.currentTimeMillis();
            WrapperPlayServerWindowConfirmation wrapper = new WrapperPlayServerWindowConfirmation(event);
            if (wrapper.getWindowId() != 0 || wrapper.isAccepted()) {
                return;
            }
            this.expectedIds.add(wrapper.getActionId());
            if (!this.sent) {
                this.sent = true;
                this.time = timeStamp;
                this.timeExpected = timeStamp;
                this.timeExpected += (long) ((int) Math.min(Math.ceil(keepAliveTracker.getKeepAlivePing() * 4), 5000.0));
                this.timeExpected += 60000L;
            }
        }
    }
}
