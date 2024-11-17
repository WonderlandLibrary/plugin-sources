package me.frep.vulcan.spigot.data.processor;

import org.bukkit.util.Vector;
import me.frep.vulcan.spigot.util.MathUtil;
import org.bukkit.World;
import io.github.retrooper.packetevents.packetwrappers.play.out.entity.WrappedPacketOutEntity;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.ItemFrame;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import org.bukkit.Location;
import me.frep.vulcan.spigot.util.type.Pair;
import me.frep.vulcan.spigot.util.type.EvictingList;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.frep.vulcan.spigot.data.PlayerData;

public class CombatProcessor
{
    private final PlayerData data;
    private int hitTicks;
    private int lastTargetAttackTick;
    private int lastPlayerAttackDamage;
    private int lastItemFrame;
    private Entity target;
    private Player trackedPlayer;
    private Player lastTrackedPlayer;
    private double distance;
    private double complexDistance;
    private final EvictingList<Pair<Location, Integer>> targetLocations;
    
    public CombatProcessor(final PlayerData data) {
        this.hitTicks = 100;
        this.targetLocations = new EvictingList<Pair<Location, Integer>>(20);
        this.data = data;
    }
    
    public void handleUseEntity(final WrappedPacketInUseEntity wrapper) {
        if (wrapper.getEntity() instanceof ItemFrame) {
            this.lastItemFrame = Vulcan.INSTANCE.getTickManager().getTicks();
        }
        if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
            return;
        }
        this.target = wrapper.getEntity();
        if (this.target != null) {
            this.distance = this.data.getPlayer().getLocation().toVector().setY(0).distance(this.target.getLocation().toVector().setY(0)) - 0.42;
        }
        if (wrapper.getEntity() instanceof Player) {
            this.lastTrackedPlayer = (Player)((this.trackedPlayer == null) ? wrapper.getEntity() : this.trackedPlayer);
            this.trackedPlayer = (Player)wrapper.getEntity();
            if (this.trackedPlayer != this.lastTrackedPlayer) {
                this.targetLocations.clear();
            }
        }
        this.hitTicks = 0;
    }
    
    public void handleRelEntityMove(final WrappedPacketOutEntity wrapper) {
        if (this.trackedPlayer != null && this.trackedPlayer.getEntityId() == wrapper.getEntityId()) {
            final PlayerData targetData = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(this.trackedPlayer);
            if (targetData == null) {
                return;
            }
            final int ticks = Vulcan.INSTANCE.getTickManager().getTicks();
            final World world = targetData.getPlayer().getWorld();
            final double x = targetData.getPositionProcessor().getX() - wrapper.getDeltaX();
            final double y = targetData.getPositionProcessor().getY() - wrapper.getDeltaY();
            final double z = targetData.getPositionProcessor().getZ() - wrapper.getDeltaZ();
            final Location targetLocation = new Location(world, x, y, z);
            this.targetLocations.add(new Pair<Location, Integer>(targetLocation, ticks));
        }
    }
    
    public void handleServerPosition() {
        this.targetLocations.clear();
    }
    
    public void handleBukkitAttack() {
        this.lastTargetAttackTick = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleBukkitAttackDamage() {
        this.lastPlayerAttackDamage = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public int getTicksSinceAttack() {
        return Vulcan.INSTANCE.getTickManager().getTicks() - this.lastTargetAttackTick;
    }
    
    public int getTicksSinceAttackDamage() {
        return Vulcan.INSTANCE.getTickManager().getTicks() - this.lastPlayerAttackDamage;
    }
    
    public void handleFlying() {
        ++this.hitTicks;
        if (!this.targetLocations.isEmpty() && this.trackedPlayer != null) {
            final int ticks = Vulcan.INSTANCE.getTickManager().getTicks();
            final int pingTicks = MathUtil.getPingInTicks(this.data);
            final double x = this.data.getPositionProcessor().getX();
            final double z = this.data.getPositionProcessor().getZ();
            final Vector origin = new Vector(x, 0.0, z);
            this.complexDistance = this.data.getCombatProcessor().getTargetLocations().stream().filter(pair -> Math.abs(ticks - pair.getY() - pingTicks) < 3).mapToDouble(pair -> {
                final Vector victimVector = pair.getX().toVector().setY(0);
                return origin.distance(victimVector) - 0.52;
            }).min().orElse(-1.0);
        }
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    public int getHitTicks() {
        return this.hitTicks;
    }
    
    public int getLastTargetAttackTick() {
        return this.lastTargetAttackTick;
    }
    
    public int getLastPlayerAttackDamage() {
        return this.lastPlayerAttackDamage;
    }
    
    public int getLastItemFrame() {
        return this.lastItemFrame;
    }
    
    public Entity getTarget() {
        return this.target;
    }
    
    public Player getTrackedPlayer() {
        return this.trackedPlayer;
    }
    
    public Player getLastTrackedPlayer() {
        return this.lastTrackedPlayer;
    }
    
    public double getDistance() {
        return this.distance;
    }
    
    public double getComplexDistance() {
        return this.complexDistance;
    }
    
    public EvictingList<Pair<Location, Integer>> getTargetLocations() {
        return this.targetLocations;
    }
}
