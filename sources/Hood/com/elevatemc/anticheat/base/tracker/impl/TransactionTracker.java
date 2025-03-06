package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.task.ScheduledTask;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientWindowConfirmation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowConfirmation;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TransactionTracker extends Tracker {

    public TransactionTracker(PlayerData data) {
        super(data);
    }

    private final AtomicInteger transSent = new AtomicInteger(0);
    private final AtomicInteger transReceived = new AtomicInteger(0);
    private final List<ScheduledTask> transTasks = new ArrayList<>();

    private long lastSent, lastReceived;

    @Getter
    private final Deque<Long> transSentTimes = new ArrayDeque<>();

    public void sendTransaction(boolean runInEventLoop) {
        if (playerData.getUser().getDecoderState() != ConnectionState.PLAY) return;
        if (runInEventLoop) {
            runInEventLoop(() -> playerData.getUser().writePacket(new WrapperPlayServerWindowConfirmation(0, (short) -1, false)));
        } else {
            playerData.getUser().writePacket(new WrapperPlayServerWindowConfirmation(0, (short) -1, false));
        }
    }


    public void runInEventLoop(Runnable runnable) {
        getNMSPlayer().playerConnection.networkManager.channel.eventLoop().execute(runnable);
    }

    private EntityPlayer getNMSPlayer() {
        return ((CraftPlayer) playerData.getPlayer()).getHandle();
    }


    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.WINDOW_CONFIRMATION) {
            handleServerTransaction(new WrapperPlayServerWindowConfirmation(event));
            lastSent = System.currentTimeMillis();
        }
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            Long lastSentTime = transSentTimes.peek();

            if (lastSentTime != null) {
                if (lastSentTime > 30_000) {
                    Bukkit.getScheduler().runTask(Hood.get(), () -> {
                        playerData.getPlayer().kickPlayer("Your connection is unreliable");
                    });
                }
            }
        }

        if (event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION) {
            handleClientTransaction(new WrapperPlayClientWindowConfirmation(event));
            lastReceived = System.currentTimeMillis();
        }
    }

    public void sendTransaction() {
        this.sendTransaction(false);
    }

    public void handleServerTransaction(WrapperPlayServerWindowConfirmation wrapper) {
        if (wrapper.getActionId() != -1) {
            return;
        }

        transSent.incrementAndGet();
        transSentTimes.add(System.currentTimeMillis());
    }

    public void handleClientTransaction(WrapperPlayClientWindowConfirmation wrapper) {
        if (wrapper.getActionId() != -1) {
            return;
        }

        Long sentTime = transSentTimes.poll();
        if (sentTime != null) {
            long responseTime = System.currentTimeMillis() - sentTime;

            if (responseTime > 30_000) {
                Bukkit.getScheduler().runTask(Hood.get(), () -> {
                    playerData.getPlayer().kickPlayer("Your connection is unreliable");
                });
            }
        }


        int currentTrans = transReceived.incrementAndGet();

        Iterator<ScheduledTask> iterator = transTasks.iterator();
        while (iterator.hasNext()) {
            ScheduledTask scheduledTask = iterator.next();
            if (scheduledTask.getTransaction() == currentTrans) {
                scheduledTask.getTask().run();
                iterator.remove();
            }
        }
    }

    public void scheduleTrans(int offset, Runnable runnable) {
        int scheduledTrans = transSent.get() + offset;

        if (transReceived.get() >= scheduledTrans) {
            runnable.run();
            return;
        }

        transTasks.add(new ScheduledTask(scheduledTrans, runnable));
    }

    public void confirmPre(Runnable runnable) {
        this.scheduleTrans(0, runnable);
    }

    public int getLastTransactionSent() {
        return transSent.get();
    }
}
