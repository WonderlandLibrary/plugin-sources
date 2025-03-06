package com.elevatemc.anticheat.base.listener.packetevents;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.*;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import org.bukkit.entity.Player;

public class PacketEventsPacketListener extends PacketListenerAbstract {
    public PacketEventsPacketListener(PacketListenerPriority priority) {
        super(priority);
    }
    @Override
    public void onUserConnect(UserConnectEvent event) {
        Hood.getPlayerDataManager().registerData(event.getUser());
    }

    @Override
    public void onUserLogin(UserLoginEvent event) {
        Hood.getPlayerDataManager().registerData(event.getUser());
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        Player player = (Player) event.getPlayer();

        if (player == null) {
            return;
        }

        PlayerData playerData = Hood.getPlayerDataManager().getData(player.getUniqueId());

        if (playerData == null) {
            return;
        }

        if (playerData.getPlayer() == null) {
            playerData.initialize(player);
        }
        for (Tracker tracker : Hood.getPlayerDataManager().getData(player.getUniqueId()).getTrackers()) {
            tracker.registerIncomingPreHandler(event);
        }

        for (PacketCheck check : Hood.getPlayerDataManager().getData(player.getUniqueId()).getCheckData().getPacketChecks()) {
            check.handle(event);
        }

        if (PacketUtil.isFlying(event.getPacketType())) {
            WrapperPlayClientPlayerFlying playerFlying = new WrapperPlayClientPlayerFlying(event);

            if (playerFlying.hasPositionChanged() && !playerData.getTeleportTracker().isTeleport()) {
                for (PositionCheck check : playerData.getCheckData().getPositionChecks()) {
                    check.handle();
                }
            }

            if (playerFlying.hasRotationChanged() && !playerData.getTeleportTracker().isTeleport()) {
                for (RotationCheck check : playerData.getCheckData().getRotationChecks()) {
                    check.handle();
                }
            }
        }

        for (Tracker tracker : Hood.getPlayerDataManager().getData(player.getUniqueId()).getTrackers()) {
            tracker.registerIncomingPostHandler(event);

        }
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        Player player = (Player) event.getPlayer();

        if (player == null) {
            return;
        }

        PlayerData playerData = Hood.getPlayerDataManager().getData(player.getUniqueId());

        if (playerData == null) {
            return;
        }

        if (event.getPacketType() == PacketType.Login.Server.LOGIN_SUCCESS) {
            Hood.getPlayerDataManager().registerData(event.getUser());
            playerData.initialize(player);
        }


        // Run before prediction for backtrack rel
        for (PacketCheck check : Hood.getPlayerDataManager().getData(player.getUniqueId()).getCheckData().getPacketChecks()) {
            check.handle(event);
        }

        for (Tracker tracker : playerData.getTrackers()) {
            tracker.registerOutgoingPreHandler(event);
        }

        for (Tracker tracker : playerData.getTrackers()) {
            tracker.registerOutgoingPostHandler(event);
        }
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        Hood.getPlayerDataManager().removeData(event.getUser());
    }
}