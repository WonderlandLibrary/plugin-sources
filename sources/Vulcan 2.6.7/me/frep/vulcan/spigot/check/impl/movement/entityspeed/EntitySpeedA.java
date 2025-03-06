package me.frep.vulcan.spigot.check.impl.movement.entityspeed;

import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Strider;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Mule;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.entity.LivingEntity;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Horse;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Entity Speed", type = 'A', complexType = "Limit", description = "Riding an entity too quickly.")
public class EntitySpeedA extends AbstractCheck
{
    public EntitySpeedA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isVehicleMove()) {
            if (ServerUtil.isLowerThan1_9()) {
                return;
            }
            final boolean vehicleHorse = this.data.getPlayer().getVehicle() instanceof Horse;
            final boolean vehicleBoat = this.data.getPlayer().getVehicle() instanceof Boat;
            final boolean vehiclePig = this.data.getPlayer().getVehicle() instanceof Pig;
            final boolean liquid = this.data.getPositionProcessor().getSinceVehicleNearLiquidTicks() < 100;
            final boolean slime = this.data.getPositionProcessor().getSinceVehicleNearSlimeTicks() < 100;
            final boolean bubbleColumn = this.data.getPositionProcessor().getSinceVehicleNearBubbleColumnTicks() < 100;
            final boolean ice = this.data.getPositionProcessor().getSinceVehicleNearIceTicks() < 100;
            final boolean piston = this.data.getPositionProcessor().getSinceVehicleNearPistonTicks() < 40;
            if (piston) {
                return;
            }
            final int vehicleTicks = this.data.getPositionProcessor().getVehicleTicks();
            final double deltaXZ = this.data.getPositionProcessor().getVehicleDeltaXZ();
            final boolean ground = this.data.getPositionProcessor().getVehicleY() % 0.015625 == 0.0;
            if (vehicleHorse) {
                final Horse horse = (Horse)this.data.getPlayer().getVehicle();
                if (horse == null || horse.hasMetadata("PROCOSMETICS_ENTITY") || horse.isLeashed()) {
                    return;
                }
                double maxSpeed = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (horse.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(horse) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Horse] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_12() && this.data.getPlayer().getVehicle() instanceof Mule) {
                final Mule mule = (Mule)this.data.getPlayer().getVehicle();
                if (mule == null || mule.isLeashed()) {
                    return;
                }
                double maxSpeed = mule.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (mule.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(mule) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Mule] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_12() && this.data.getPlayer().getVehicle() instanceof Donkey) {
                final Donkey donkey = (Donkey)this.data.getPlayer().getVehicle();
                if (donkey == null || donkey.isLeashed()) {
                    return;
                }
                double maxSpeed = donkey.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (donkey.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(donkey) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Donkey] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_12() && this.data.getPlayer().getVehicle() instanceof ZombieHorse) {
                final ZombieHorse zombieHorse = (ZombieHorse)this.data.getPlayer().getVehicle();
                if (zombieHorse == null || zombieHorse.isLeashed()) {
                    return;
                }
                double maxSpeed = zombieHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (zombieHorse.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(zombieHorse) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Zombie Horse] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_12() && this.data.getPlayer().getVehicle() instanceof SkeletonHorse) {
                final SkeletonHorse skeletonHorse = (SkeletonHorse)this.data.getPlayer().getVehicle();
                if (skeletonHorse == null || skeletonHorse.isLeashed()) {
                    return;
                }
                double maxSpeed = skeletonHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (skeletonHorse.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(skeletonHorse) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Skeleton Horse] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (vehicleBoat) {
                double max = 0.65;
                if (liquid) {
                    max += 0.15;
                }
                if (deltaXZ > max && !ice && !bubbleColumn && !slime && vehicleTicks > 5) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Boat] deltaXZ=" + deltaXZ);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (vehiclePig) {
                final Pig pig = (Pig)this.data.getPlayer().getVehicle();
                if (pig == null || pig.isLeashed()) {
                    return;
                }
                final double maxSpeed = pig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (deltaXZ > maxSpeed && ground && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Pig] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_16() && this.data.getPlayer().getVehicle() instanceof Strider) {
                final Strider strider = (Strider)this.data.getPlayer().getVehicle();
                if (strider == null || strider.isLeashed()) {
                    return;
                }
                double maxSpeed = strider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (strider.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(strider) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Strider] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_11() && this.data.getPlayer().getVehicle() instanceof Llama) {
                final Llama llama = (Llama)this.data.getPlayer().getVehicle();
                if (llama == null || llama.isLeashed()) {
                    return;
                }
                double maxSpeed = llama.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (llama.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(llama) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[Llama] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (ServerUtil.isHigherThan1_11() && this.data.getPlayer().getVehicle() instanceof TraderLlama) {
                final TraderLlama traderLlama = (TraderLlama)this.data.getPlayer().getVehicle();
                if (traderLlama == null || traderLlama.isLeashed()) {
                    return;
                }
                double maxSpeed = traderLlama.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 2.25 + 0.1;
                if (traderLlama.hasPotionEffect(PotionEffectType.SPEED)) {
                    maxSpeed += PlayerUtil.getSpeed(traderLlama) * 0.15;
                }
                if (this.data.getActionProcessor().isHasSpeed()) {
                    maxSpeed += this.data.getActionProcessor().getSpeedAmplifier() * 0.065;
                }
                if (!ground) {
                    maxSpeed += 0.17499999701976776;
                }
                if (deltaXZ > maxSpeed && !ice && vehicleTicks > 10) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("[TraderLlama] speed=" + deltaXZ + " maxSpeed=" + maxSpeed);
                        if (Config.ENTITY_SPEED_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
