package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
    Credit goes out to whomever made this, if I'm not mistaken it was FlyCode.
 */
public class KillAuraK extends PacketCheck {
    PREDICTION prediction = PREDICTION.LEGIT;

    public KillAuraK(PlayerData playerData) {
        super(playerData, "Aura K", "Forced bot", new ViolationHandler(30, 30000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle(PacketReceiveEvent packet) {
        if (WrapperPlayClientPlayerFlying.isFlying(packet.getPacketType()) && predictionTracker.entityPlayer != null) {

            if ((System.currentTimeMillis() - predictionTracker.lastEntityBotHit) > 500L) {
                if (predictionTracker.getEntityHitTime() > 0) predictionTracker.entityHitTime--;
            }

            if (predictionTracker.getEntityHitTime() > 7 && (System.currentTimeMillis() - predictionTracker.lastEntityBotHit) < 320L) {
                if (predictionTracker.getForcedUser() == null) {
                    handleViolation(new DetailedPlayerViolation(this,"t=" + predictionTracker.getEntityHitTime()));
                } else {
                    predictionTracker.entityAReportedFlags++;
                }
            }

            long seconds = (System.currentTimeMillis() - predictionTracker.lastEntitySpawn) / 1000;

            if (seconds > 5L) {
                if (predictionTracker.getForcedUser() != null && predictionTracker.getForcedUser().getPlayer().isOnline()) {
                    if (predictionTracker.entityAReportedFlags > 3) {
                        prediction = PREDICTION.CHEATING;
                    } else if (predictionTracker.getEntityAReportedFlags() > 5) {
                        prediction = PREDICTION.POSSIBLY;
                    } else {
                        prediction = PREDICTION.LEGIT;
                    }
                    predictionTracker.getForcedUser().getPlayer().sendMessage(ChatColor.GRAY + " - " + (predictionTracker.entityAReportedFlags > 3 ? ChatColor.RED + "Cheating" : (predictionTracker.getEntityAReportedFlags() > 5 ? ChatColor.YELLOW + "Possibly Legit" : ChatColor.GREEN + "Legit" + " Hit Time " + predictionTracker.getEntityHitTime() / 1000)));
                }

                BotUtils.removeBotEntity(playerData);
                predictionTracker.setForcedUser(null);
                predictionTracker.entityATotalAttacks = 0;
                predictionTracker.entityAReportedFlags = 0;
                predictionTracker.setWaitingForBot(false);
                return;
            }
            Location playerLoc = new Location(playerData.getPlayer().getLocation().getWorld(), positionTracker.getX(), positionTracker.getY(), positionTracker.getZ());
            Location loc = BotUtils.getBehind(playerData.getPlayer(), (predictionTracker.moveBot ? (!(playerLoc.getPitch() < -21.00f) ? -0.10 : -2.90) : -2.90));

            if (predictionTracker.getBotType() == PredictionTracker.BotTypes.WATCHDOG) loc = playerData.getPlayer().getLocation();

            boolean random = ThreadLocalRandom.current().nextBoolean();
            double offset = (random ? BotUtils.getRandomDouble(0.20, 0.55) : 0.0);
            boolean hit = ((System.currentTimeMillis() - predictionTracker.lastEntityBotHit) < 122L);

            if (predictionTracker.botTicks > 50) {
                if (!predictionTracker.moveBot) {
                    predictionTracker.moveBot = true;
                }
                predictionTracker.botTicks = 0;
            }

            if (predictionTracker.moveBot && predictionTracker.movedBotTicks > 20) {
                predictionTracker.movedBotTicks = 0;
                predictionTracker.moveBot = false;
            }

            if (predictionTracker.getBotType() == PredictionTracker.BotTypes.NORMAL) {
                Random r = new Random();
                int low = 0;
                int high = 20;
                int result = r.nextInt(high-low) + low;
                if(rotationTracker.getPitch() <= -80) {
                    predictionTracker.entityPlayer.setLocation(loc.getX() + offset, loc.getY() - BotUtils.getRandomDouble(0.5f, 1f), loc.getZ() - offset, (float) (loc.getYaw() + BotUtils.getRandomDouble(0.10f, 0.50f)), (float) BotUtils.getRandomDouble(-90.0f, 90.0f));
                }
                else {
                    predictionTracker.entityPlayer.setLocation(loc.getX() + offset, ((hit || predictionTracker.moveBot) && !(playerLoc.getPitch() < -6.00f) ? loc.getY() + 3.42 : loc.getY() + (random && result < 15 ? BotUtils.getRandomDouble(0.10, 0.99) : 0.0)), loc.getZ() - offset, (float) (loc.getYaw() + BotUtils.getRandomDouble(0.10f, 0.50f)), (float) BotUtils.getRandomDouble(-90.0f, 90.0f));
                }
            } else if (predictionTracker.getBotType() == PredictionTracker.BotTypes.WATCHDOG) {
                double increment = BotUtils.getRandomDouble(0.95, 1.40);
                predictionTracker.entityPlayer.setLocation(loc.getX() + Math.sin(Math.toRadians(-(predictionTracker.getEntityAStartYaw() + predictionTracker.getEntityAMovementOffset()))) * increment, loc.getY() + 1 + (ThreadLocalRandom.current().nextBoolean() ? (ThreadLocalRandom.current().nextBoolean() ? BotUtils.getRandomDouble(0.35f, 0.42f) : 0.42f) : 0.0f), loc.getZ() + Math.cos(Math.toRadians(-(predictionTracker.getEntityAStartYaw() + predictionTracker.getEntityAMovementOffset()))) * increment, (float) (loc.getYaw() + BotUtils.getRandomDouble(0.10f, 0.50f)), (float) BotUtils.getRandomDouble(-90.0f, 90.0f));
            } else if (predictionTracker.getBotType() == PredictionTracker.BotTypes.FOLLOW) {
                if (Math.abs(predictionTracker.getEntityAFollowDistance()) > 1.20) {
                    predictionTracker.setEntityAFollowDistance(predictionTracker.getEntityAFollowDistance() + 0.05f);
                }

                double yaw = 0.0f, amount = -predictionTracker.getEntityAFollowDistance();
                yaw = Math.toRadians(yaw);
                double dX = -Math.sin(yaw) * amount;
                double dZ = Math.cos(yaw) * amount;

                predictionTracker.entityPlayer.setLocation(loc.getX() + dX, playerLoc.getY(), loc.getZ() + dZ, playerLoc.getYaw(), playerLoc.getPitch());
            }

            BotUtils.sendPacket(playerData, new PacketPlayOutEntityTeleport(predictionTracker.entityPlayer), predictionTracker.getForcedUser());

            if (!predictionTracker.moveBot) predictionTracker.botTicks++;
            else predictionTracker.movedBotTicks++;
            predictionTracker.randomBotSwingTicks++;
            predictionTracker.randomBotDamageTicks++;
            predictionTracker.setEntityAMovementOffset(predictionTracker.getEntityAMovementOffset() + 20.0f);
        }

        if (packet.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrappedInUseEntityPacket = new WrapperPlayClientInteractEntity(packet);
            if (predictionTracker.hasBot && wrappedInUseEntityPacket.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK && wrappedInUseEntityPacket.getEntityId() == predictionTracker.botID) {
                if (predictionTracker.entityHitTime < 20) predictionTracker.entityHitTime++;
                predictionTracker.lastEntityBotHit = System.currentTimeMillis();
                predictionTracker.entityATotalAttacks++;
            }
        }
    }

    enum PREDICTION {
        LEGIT,
        POSSIBLY,
        CHEATING
    }
}

