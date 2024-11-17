package me.frep.vulcan.spigot.data.processor;

import io.github.retrooper.packetevents.packetwrappers.play.in.pong.WrappedPacketInPong;
import io.github.retrooper.packetevents.packetwrappers.play.in.transaction.WrappedPacketInTransaction;
import org.bukkit.util.Vector;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.util.MathUtil;
import java.util.HashMap;
import java.util.Map;
import me.frep.vulcan.spigot.data.PlayerData;

public class VelocityProcessor
{
    private final PlayerData data;
    private double velocityX;
    private double velocityY;
    private double velocityZ;
    private double velocityXZ;
    private long lastRepliedVelocityConfirmation;
    private long lastSentVelocityTransaction;
    private short transactionId;
    private short pingId;
    private VelocitySnapshot snapshot;
    private final Map<Short, VelocitySnapshot> velocities;
    private int tick;
    private int transactionFlyingTicks;
    private int sinceHighVelocityTicks;
    
    public VelocityProcessor(final PlayerData data) {
        this.lastRepliedVelocityConfirmation = System.currentTimeMillis();
        this.lastSentVelocityTransaction = System.currentTimeMillis();
        this.transactionId = -31768;
        this.pingId = -31768;
        this.velocities = new HashMap<Short, VelocitySnapshot>();
        this.transactionFlyingTicks = 100;
        this.sinceHighVelocityTicks = 100;
        this.data = data;
    }
    
    public void handle(final double velocityX, final double velocityY, final double velocityZ) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.velocityXZ = MathUtil.hypot(velocityX, velocityZ);
        if (this.velocityXZ > 1.0) {
            this.sinceHighVelocityTicks = 0;
        }
        this.tick = Vulcan.INSTANCE.getTickManager().getTicks();
        ++this.transactionId;
        ++this.pingId;
        if (this.transactionId > -30769) {
            this.transactionId = -31768;
        }
        if (this.pingId > -30769) {
            this.pingId = -31768;
        }
        if (ServerUtil.isHigherThan1_17()) {
            this.data.sendPing(this.pingId);
        }
        else {
            PlayerUtil.sendTransaction(this.data.getPlayer(), this.transactionId);
        }
        this.lastSentVelocityTransaction = System.currentTimeMillis();
        this.velocities.put(this.transactionId, new VelocitySnapshot(new Vector(velocityX, velocityY, velocityZ), this.transactionId));
        final int lastDamage = this.data.getActionProcessor().getLastDamage();
        if (this.tick - lastDamage >= 3) {
            this.data.getActionProcessor().handleBukkitVelocity();
        }
    }
    
    public void handleFlying() {
        ++this.transactionFlyingTicks;
        ++this.sinceHighVelocityTicks;
    }
    
    public void handleTransaction(final WrappedPacketInTransaction wrapper) {
        if (this.velocities.containsKey(wrapper.getActionNumber())) {
            this.transactionFlyingTicks = 0;
            this.snapshot = this.velocities.get(wrapper.getActionNumber());
            this.velocities.remove(wrapper.getActionNumber());
            this.lastRepliedVelocityConfirmation = System.currentTimeMillis();
        }
    }
    
    public void handlePong(final WrappedPacketInPong wrapper) {
        final short id = (short)wrapper.getId();
        if (this.velocities.containsKey(id)) {
            this.transactionFlyingTicks = 0;
            this.snapshot = this.velocities.get(id);
            this.velocities.remove(id);
            this.lastRepliedVelocityConfirmation = System.currentTimeMillis();
        }
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    public double getVelocityX() {
        return this.velocityX;
    }
    
    public double getVelocityY() {
        return this.velocityY;
    }
    
    public double getVelocityZ() {
        return this.velocityZ;
    }
    
    public double getVelocityXZ() {
        return this.velocityXZ;
    }
    
    public long getLastRepliedVelocityConfirmation() {
        return this.lastRepliedVelocityConfirmation;
    }
    
    public long getLastSentVelocityTransaction() {
        return this.lastSentVelocityTransaction;
    }
    
    public short getTransactionId() {
        return this.transactionId;
    }
    
    public short getPingId() {
        return this.pingId;
    }
    
    public VelocitySnapshot getSnapshot() {
        return this.snapshot;
    }
    
    public Map<Short, VelocitySnapshot> getVelocities() {
        return this.velocities;
    }
    
    public int getTick() {
        return this.tick;
    }
    
    public int getTransactionFlyingTicks() {
        return this.transactionFlyingTicks;
    }
    
    public int getSinceHighVelocityTicks() {
        return this.sinceHighVelocityTicks;
    }
    
    public class VelocitySnapshot
    {
        private final Vector velocity;
        private final short transactionId;
        
        public VelocitySnapshot(final Vector velocity, final short transactionId) {
            this.velocity = velocity;
            this.transactionId = transactionId;
        }
        
        public Vector getVelocity() {
            return this.velocity;
        }
        
        public short getTransactionId() {
            return this.transactionId;
        }
    }
}
