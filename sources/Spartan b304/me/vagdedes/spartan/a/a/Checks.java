package me.vagdedes.spartan.a.a;

import org.bukkit.configuration.file.YamlConfiguration;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.Register;
import java.util.HashMap;
import java.io.File;

public class Checks
{
    private static File file;
    private static final HashMap<String, Boolean> a;
    private static final HashMap<String, Integer> b;
    private static final HashMap<String, Double> c;
    
    public Checks() {
        super();
    }
    
    public static void clear() {
        Checks.a.clear();
        Checks.b.clear();
        Checks.c.clear();
    }
    
    public static void create() {
        Checks.file = new File(Register.plugin.getDataFolder() + "/checks.yml");
        clear();
        ConfigUtils.add(Checks.file, "FastPlace.cancel_seconds", Integer.valueOf(1));
        ConfigUtils.add(Checks.file, "Exploits.check_ping_spoof", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Exploits.check_undetected_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastHeal.check_unusual", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastHeal.check_illegal", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastBreak.cancel_seconds", Integer.valueOf(1));
        ConfigUtils.add(Checks.file, "FastBreak.check_surface_blocks", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "EntityMove.check_speed", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "EntityMove.check_vertical", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Clip.check_when_flying", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "Clip.check_instant_movements", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Clip.check_inside_movements", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Clip.check_block_change", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Clip.check_pistons", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "Criticals.check_location", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Criticals.check_position", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Criticals.check_mini_jump", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "BoatMove.check_horizontal", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "BoatMove.check_vertical", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "BoatMove.check_stable", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "BoatMove.check_packets", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "BoatMove.compatibility_protection", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "HitReach.horizontal_distance", Double.valueOf(3.5));
        ConfigUtils.add(Checks.file, "HitReach.vertical_distance", Double.valueOf(4.0));
        ConfigUtils.add(Checks.file, "HitReach.exempt_player_chasing", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "HitReach.account_speed_effect", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "Speed.check_sneaking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Speed.check_walking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_distance", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_vertical", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_horizontal", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_combined", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_extreme", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_opposite", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Velocity.check_direction", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastClicks.cps_limit", Integer.valueOf(15));
        ConfigUtils.add(Checks.file, "FastClicks.check_cps", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastClicks.check_click_time", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastClicks.check_click_consistency", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastClicks.combat_only_mode", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "ImpossibleActions.check_tower", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleActions.check_scaffold", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleActions.check_actions", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleActions.check_cactus", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleActions.check_item_usage", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleActions.cancel_seconds", Integer.valueOf(1));
        ConfigUtils.add(Checks.file, "Nuker.check_delay", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Nuker.check_breaks_per_sec", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Nuker.check_moves_in_between", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Nuker.max_breaks_per_second", Integer.valueOf(40));
        ConfigUtils.add(Checks.file, "Nuker.cancel_seconds", Integer.valueOf(2));
        ConfigUtils.add(Checks.file, "FastBow.check_bow_force", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "FastBow.check_bow_shots", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "NoSwing.check_damage", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "NoSwing.check_breaking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_sneaking", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_sprinting", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_sleeping", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_walking", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_dead", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_sprint_jumping", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ImpossibleInventory.check_walk_jumping", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Sprint.check_food_sprinting", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Sprint.check_omnidirectional_sprinting", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.detection.players", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.detection.entities", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_overall", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_accuracy", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_angle", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_block_raytrace", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_combined", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_direction", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_entity_raytrace", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_hits_per_second", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_hit_time", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_impossible_hits", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_item_usage", Boolean.valueOf(false));
        ConfigUtils.add(Checks.file, "KillAura.check_pitch_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_yaw_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_rapid_hits", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_rotations", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_hitbox", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_aim_consistency", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_aim_accuracy", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_comparison", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_modulo", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_stability", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_precision", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "KillAura.check_targeted_area", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "GhostHand.check_block_breaking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "GhostHand.check_player_interactions", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "IrregularMovements.check_step_hacking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "IrregularMovements.check_bouncing_blocks", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "IrregularMovements.check_block_climbing", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "IrregularMovements.check_hop_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "IrregularMovements.check_jump_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_speed", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_repeated_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_horizontal_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_swimming", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_walking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_upwards_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_liquid_exit", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Jesus.check_y_position", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Liquids.check_block_breaking", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Liquids.check_block_placing", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Fly.check_fly", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "Fly.check_glide", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ElytraMove.check_ratio", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ElytraMove.check_speed", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "ElytraMove.check_fly", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "NoSlowdown.check_item_usage", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "NoSlowdown.check_bow_shots", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "NoSlowdown.check_food_eating", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "NoSlowdown.check_cobweb_movement", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "MorePackets.check_blink", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "MorePackets.check_overall", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "MorePackets.check_difference", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "MorePackets.check_delay", Boolean.valueOf(true));
        ConfigUtils.add(Checks.file, "MorePackets.check_instant", Boolean.valueOf(true));
    }
    
    private static YamlConfiguration a() {
        if (!Checks.file.exists()) {
            create();
        }
        return YamlConfiguration.loadConfiguration(Checks.file);
    }
    
    public static int getInteger(final String key) {
        if (Checks.b.containsKey(key)) {
            return Integer.valueOf(Checks.b.get((Object)key));
        }
        final int int1 = a().getInt(key);
        Checks.b.put(key, Integer.valueOf(int1));
        return int1;
    }
    
    public static boolean getBoolean(final String key) {
        if (Checks.a.containsKey(key)) {
            return Boolean.valueOf(Checks.a.get((Object)key));
        }
        final boolean boolean1 = a().getBoolean(key);
        Checks.a.put(key, Boolean.valueOf(boolean1));
        return boolean1;
    }
    
    public static double getDouble(final String key) {
        if (Checks.c.containsKey(key)) {
            return Double.valueOf(Checks.c.get((Object)key));
        }
        final double double1 = a().getDouble(key);
        Checks.c.put(key, Double.valueOf(double1));
        return double1;
    }
    
    static {
        Checks.file = new File(Register.plugin.getDataFolder() + "/checks.yml");
        a = new HashMap<String, Boolean>(130);
        b = new HashMap<String, Integer>(10);
        c = new HashMap<String, Double>(10);
    }
}
