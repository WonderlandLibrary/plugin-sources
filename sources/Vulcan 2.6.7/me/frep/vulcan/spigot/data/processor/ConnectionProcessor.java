package me.frep.vulcan.spigot.data.processor;

import me.frep.vulcan.spigot.util.ServerUtil;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.config.Config;
import io.github.retrooper.packetevents.packetwrappers.play.in.pong.WrappedPacketInPong;
import io.github.retrooper.packetevents.packetwrappers.play.in.transaction.WrappedPacketInTransaction;
import me.frep.vulcan.spigot.util.PlayerUtil;
import java.util.HashMap;
import java.util.Map;
import me.frep.vulcan.spigot.data.PlayerData;

public class ConnectionProcessor
{
    private final PlayerData data;
    private int keepAlivePing;
    private int pingId;
    private int flightDesyncTicks;
    private short transactionId;
    private final Map<Short, Long> transactionQueue;
    private final Map<Integer, Long> pingQueue;
    private long lastFlying;
    private long flyingDelay;
    private long lastRepliedTransaction;
    private long transactionPing;
    private long lastKeepAlive;
    private long lastFast;
    private boolean fast;
    private double transactionBuffer;
    private double keepAliveBuffer;
    private boolean transactionKicked;
    private boolean keepAliveKicked;
    private int maxPingBuffer;
    private boolean maxPingKicked;
    
    public ConnectionProcessor(final PlayerData data) {
        this.pingId = -25768;
        this.flightDesyncTicks = 0;
        this.transactionId = -25768;
        this.transactionQueue = new HashMap<Short, Long>();
        this.pingQueue = new HashMap<Integer, Long>();
        this.lastRepliedTransaction = System.currentTimeMillis();
        this.lastKeepAlive = System.currentTimeMillis();
        this.lastFast = System.currentTimeMillis();
        this.data = data;
    }
    
    public void handleFlying() {
        final long now = System.currentTimeMillis();
        this.flyingDelay = now - this.lastFlying;
        this.fast = (this.flyingDelay <= 3L);
        if (this.fast) {
            this.lastFast = System.currentTimeMillis();
        }
        this.keepAlivePing = PlayerUtil.getPing(this.data.getPlayer());
        this.lastFlying = now;
        this.confirmTransactionConnection();
        this.confirmKeepAliveConnection();
        this.handleFlightDesync();
        this.handleMaxPingKick();
    }
    
    public void handleKeepAlive() {
        this.lastKeepAlive = System.currentTimeMillis();
    }
    
    public void sendPing() {
        ++this.pingId;
        if (this.pingId > -24769) {
            this.pingId = -25768;
        }
        this.data.setPendingTransactions(this.data.getPendingTransactions() + 1);
        this.data.sendPing(this.pingId);
        this.pingQueue.put(this.pingId, System.currentTimeMillis());
    }
    
    public void sendTransaction() {
        ++this.transactionId;
        if (this.transactionId > -24769) {
            this.transactionId = -25768;
        }
        this.data.setPendingTransactions(this.data.getPendingTransactions() + 1);
        this.data.sendTransaction(this.transactionId);
        this.transactionQueue.put(this.transactionId, System.currentTimeMillis());
    }
    
    public void handleTransaction(final WrappedPacketInTransaction wrapper) {
        if (this.transactionQueue.containsKey(wrapper.getActionNumber())) {
            final long sent = this.transactionQueue.get(wrapper.getActionNumber());
            this.lastRepliedTransaction = System.currentTimeMillis();
            this.transactionPing = System.currentTimeMillis() - sent;
            this.data.setPendingTransactions(this.data.getPendingTransactions() - 1);
            this.transactionQueue.remove(wrapper.getActionNumber());
        }
    }
    
    public void handlePong(final WrappedPacketInPong wrapper) {
        if (this.pingQueue.containsKey(wrapper.getId())) {
            final long sent = this.pingQueue.get(wrapper.getId());
            this.lastRepliedTransaction = System.currentTimeMillis();
            this.transactionPing = System.currentTimeMillis() - sent;
            this.data.setPendingTransactions(this.data.getPendingTransactions() - 1);
            this.pingQueue.remove(wrapper.getId());
        }
    }
    
    public void handleFlightDesync() {
        if (!Config.DESYNC_KICK_ENABLED) {
            return;
        }
        final boolean confirmed = this.data.getPositionProcessor().isAllowFlight();
        final boolean unconfirmed = this.data.getPlayer().getAllowFlight();
        final boolean joined = this.data.getExemptProcessor().isExempt(ExemptType.JOINED);
        if (confirmed && !unconfirmed && !joined) {
            if (++this.flightDesyncTicks > Config.DESYNC_MAX_TICKS) {
                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().kickPlayer(ColorUtil.translate(Config.DESYNC_KICK_MESSAGE)));
                if (!Config.DESYNC_STAFF_KICK_MESSAGE.equals("")) {
                    Vulcan.INSTANCE.getAlertManager().sendMessage(Config.DESYNC_STAFF_KICK_MESSAGE.replaceAll("%ticks%", Integer.toString(this.flightDesyncTicks)).replaceAll("%player%", this.data.getPlayer().getName()));
                }
                if (!Config.DESYNC_CONSOLE_MESSAGE.equals("")) {
                    ServerUtil.log(ColorUtil.translate(Config.DESYNC_CONSOLE_MESSAGE.replaceAll("%player%", this.data.getPlayer().getName()).replaceAll("%ticks%", Integer.toString(this.flightDesyncTicks))));
                }
            }
        }
        else if (this.flightDesyncTicks > 0) {
            this.flightDesyncTicks -= (int)0.05;
        }
    }
    
    private void confirmTransactionConnection() {
        if (!Config.TRANSACTION_KICK_ENABLED || this.data.getPlayer().hasPermission("vulcan.bypass.connection.transaction")) {
            return;
        }
        final long delay = System.currentTimeMillis() - this.lastRepliedTransaction;
        if (delay > Config.TRANSACTION_MAX_DELAY && !this.transactionKicked) {
            final double transactionBuffer = this.transactionBuffer + 1.0;
            this.transactionBuffer = transactionBuffer;
            if (transactionBuffer > 5.0) {
                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().kickPlayer(ColorUtil.translate(Config.TRANSACTION_KICK_MESSAGE)));
                if (!Config.TRANSACTION_STAFF_ALERT_MESSAGE.equals("")) {
                    Vulcan.INSTANCE.getAlertManager().sendMessage(Config.TRANSACTION_STAFF_ALERT_MESSAGE.replaceAll("%delay%", Long.toString(delay)).replaceAll("%player%", this.data.getPlayer().getName()));
                }
                if (!Config.TRANSACTION_KICK_CONSOLE_MESSAGE.equals("")) {
                    ServerUtil.log(ColorUtil.translate(Config.TRANSACTION_KICK_CONSOLE_MESSAGE.replaceAll("%player%", this.data.getPlayer().getName()).replaceAll("%delay%", Long.toString(delay))));
                }
                this.transactionKicked = true;
            }
            else if (this.transactionBuffer > 0.0) {
                this.transactionBuffer -= 0.1;
            }
        }
    }
    
    private void confirmKeepAliveConnection() {
        if (!Config.KEEPALIVE_KICK_ENABLED || this.data.getPlayer().hasPermission("vulcan.bypass.connection.keepalive")) {
            return;
        }
        final long delay = System.currentTimeMillis() - this.lastKeepAlive;
        if (delay > Config.KEEPALIVE_MAX_DELAY && !this.keepAliveKicked) {
            final double keepAliveBuffer = this.keepAliveBuffer + 1.0;
            this.keepAliveBuffer = keepAliveBuffer;
            if (keepAliveBuffer > 5.0) {
                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().kickPlayer(ColorUtil.translate(Config.TRANSACTION_KICK_MESSAGE)));
                if (!Config.KEEPALIVE_STAFF_ALERT_MESSAGE.equals("")) {
                    Vulcan.INSTANCE.getAlertManager().sendMessage(Config.KEEPALIVE_STAFF_ALERT_MESSAGE.replaceAll("%delay%", Long.toString(delay)).replaceAll("%player%", this.data.getPlayer().getName()));
                }
                if (!Config.KEEPALIVE_KICK_CONSOLE_MESSAGE.equals("")) {
                    ServerUtil.log(ColorUtil.translate(Config.KEEPALIVE_KICK_CONSOLE_MESSAGE.replaceAll("%player%", this.data.getPlayer().getName()).replaceAll("%delay%", Long.toString(delay))));
                }
                this.keepAliveKicked = true;
            }
            else if (this.keepAliveBuffer > 0.0) {
                this.keepAliveBuffer -= 0.1;
            }
        }
    }
    
    private void handleMaxPingKick() {
        if (!Config.MAX_PING_KICK_ENABLED || this.data.getPlayer().hasPermission("vulcan.bypass.connection.max-ping")) {
            return;
        }
        if (this.keepAlivePing > Config.MAX_PING && !this.maxPingKicked) {
            if (++this.maxPingBuffer > Config.MAX_PING_KICK_TICKS) {
                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().kickPlayer(ColorUtil.translate(Config.MAX_PING_KICK_MESSAGE)));
                if (!Config.MAX_PING_KICK_STAFF_ALERT_MESSAGE.equals("")) {
                    Vulcan.INSTANCE.getAlertManager().sendMessage(Config.MAX_PING_KICK_STAFF_ALERT_MESSAGE.replaceAll("%ping%", Long.toString(this.keepAlivePing)).replaceAll("%player%", this.data.getPlayer().getName()));
                }
                if (!Config.MAX_PING_KICK_CONSOLE_MESSAGE.equals("")) {
                    ServerUtil.log(ColorUtil.translate(Config.MAX_PING_KICK_CONSOLE_MESSAGE.replaceAll("%player%", this.data.getPlayer().getName()).replaceAll("%ping%", Long.toString(this.keepAlivePing))));
                }
                this.maxPingKicked = true;
            }
        }
        else if (this.maxPingBuffer > 0) {
            --this.maxPingBuffer;
        }
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    public int getKeepAlivePing() {
        return this.keepAlivePing;
    }
    
    public int getPingId() {
        return this.pingId;
    }
    
    public int getFlightDesyncTicks() {
        return this.flightDesyncTicks;
    }
    
    public short getTransactionId() {
        return this.transactionId;
    }
    
    public Map<Short, Long> getTransactionQueue() {
        return this.transactionQueue;
    }
    
    public Map<Integer, Long> getPingQueue() {
        return this.pingQueue;
    }
    
    public long getLastFlying() {
        return this.lastFlying;
    }
    
    public long getFlyingDelay() {
        return this.flyingDelay;
    }
    
    public long getLastRepliedTransaction() {
        return this.lastRepliedTransaction;
    }
    
    public long getTransactionPing() {
        return this.transactionPing;
    }
    
    public long getLastKeepAlive() {
        return this.lastKeepAlive;
    }
    
    public long getLastFast() {
        return this.lastFast;
    }
    
    public boolean isFast() {
        return this.fast;
    }
    
    public double getTransactionBuffer() {
        return this.transactionBuffer;
    }
    
    public double getKeepAliveBuffer() {
        return this.keepAliveBuffer;
    }
    
    public boolean isTransactionKicked() {
        return this.transactionKicked;
    }
    
    public boolean isKeepAliveKicked() {
        return this.keepAliveKicked;
    }
    
    public int getMaxPingBuffer() {
        return this.maxPingBuffer;
    }
    
    public boolean isMaxPingKicked() {
        return this.maxPingKicked;
    }
}
