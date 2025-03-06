package me.frep.vulcan.spigot.check.impl.movement.entityflight;

import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Horse;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Entity Flight", type = 'B', complexType = "Hover", description = "Hovering while riding an entity.")
public class EntityFlightB extends AbstractCheck
{
    public EntityFlightB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isVehicleMove()) {
            if (ServerUtil.isLowerThan1_9()) {
                return;
            }
            if (this.data.getPositionProcessor().getVehicleY() < 0.0) {
                return;
            }
            final boolean vehicleHorse = this.data.getPlayer().getVehicle() instanceof Horse;
            final boolean pig = this.data.getPlayer().getVehicle() instanceof Pig;
            final boolean donkey = ServerUtil.isHigherThan1_9() && this.data.getPlayer().getVehicle() instanceof Donkey;
            final boolean liquid = this.data.getPositionProcessor().getSinceVehicleNearLiquidTicks() < 100;
            final boolean slime = this.data.getPositionProcessor().getSinceVehicleNearSlimeTicks() < 100;
            final boolean bubbleColumn = this.data.getPositionProcessor().getSinceVehicleNearBubbleColumnTicks() < 100;
            final int vehicleTicks = this.data.getPositionProcessor().getVehicleTicks();
            final int airTicks = this.data.getPositionProcessor().getVehicleAirTicks();
            final double deltaY = this.data.getPositionProcessor().getVehicleDeltaY();
            final double lastDeltaY = this.data.getPositionProcessor().getLastVehicleDeltaY();
            final double acceleration = Math.abs(deltaY - lastDeltaY);
            final boolean invalid = airTicks > 30 && Math.abs(deltaY) < 5.0 && acceleration < 0.05 && !liquid && !slime && !bubbleColumn && vehicleTicks > 5 && deltaY > -1.5;
            if (vehicleHorse) {
                final Horse horse = (Horse)this.data.getPlayer().getVehicle();
                if (ServerUtil.isHigherThan1_13() && horse.hasPotionEffect(PotionEffectType.LEVITATION)) {
                    return;
                }
                if (horse.isLeashed()) {
                    return;
                }
                if (invalid) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Horse] deltaY=" + deltaY + " acceleration=" + acceleration + " ticks=" + vehicleTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (pig) {
                final Pig pigg = (Pig)this.data.getPlayer().getVehicle();
                if (ServerUtil.isHigherThan1_13() && pigg.hasPotionEffect(PotionEffectType.LEVITATION)) {
                    return;
                }
                if (pigg.isLeashed()) {
                    return;
                }
                if (invalid) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Pig] deltaY=" + deltaY + " acceleration=" + acceleration + " ticks=" + vehicleTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (donkey) {
                final Donkey donkeyy = (Donkey)this.data.getPlayer().getVehicle();
                if (ServerUtil.isHigherThan1_13() && donkeyy.hasPotionEffect(PotionEffectType.LEVITATION)) {
                    return;
                }
                if (donkeyy.isLeashed()) {
                    return;
                }
                if (invalid) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Pig] deltaY=" + deltaY + " acceleration=" + acceleration + " ticks=" + vehicleTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
