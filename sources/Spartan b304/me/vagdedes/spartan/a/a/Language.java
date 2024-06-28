package me.vagdedes.spartan.a.a;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.Register;
import java.util.HashMap;
import java.io.File;

public class Language
{
    private static File file;
    private static final HashMap<String, String> d;
    
    public Language() {
        super();
    }
    
    public static void clear() {
        Language.d.clear();
    }
    
    public static void create() {
        Language.file = new File(Register.plugin.getDataFolder() + "/language.yml");
        clear();
        ConfigUtils.add(Language.file, "server_name", "&4Another Minecraft Server");
        ConfigUtils.add(Language.file, "verbose_enable", "&8[&2Spartan&8] &aYou enabled verbose.");
        ConfigUtils.add(Language.file, "verbose_disable", "&8[&2Spartan&8] &cYou disabled verbose.");
        ConfigUtils.add(Language.file, "violations_reset", "&8[&2Spartan&8] &cVLs &7have been reset&8!");
        ConfigUtils.add(Language.file, "config_reload", "&8[&2Spartan&8]&e Config successfully reloaded.");
        ConfigUtils.add(Language.file, "kick_reason", "&8[&2Spartan&8]&c {reason}");
        ConfigUtils.add(Language.file, "kick_broadcast_message", "&8[&2Spartan&8]&c {player}&7 was kicked for&4 {reason}");
        ConfigUtils.add(Language.file, "command_no_access", "&cYou do not have access to this command.");
        ConfigUtils.add(Language.file, "menu_gui_no_access", "&cYou don't have permission to do this.");
        ConfigUtils.add(Language.file, "ping_command_message", "&2{player}&7's latency&8:&a {ping}ms");
        ConfigUtils.add(Language.file, "player_not_found_message", "&cPlayer not found.");
        ConfigUtils.add(Language.file, "non_console_command", "&cThis command can only be used by a player.");
        ConfigUtils.add(Language.file, "player_violation_reset_message", "&8[&2Spartan&8]&a Violations successfully reset for player&8: &2{player}");
        ConfigUtils.add(Language.file, "blocked_command_message", "&cYou are not allowed to dispatch that command.");
        ConfigUtils.add(Language.file, "blocked_word_message", "&cYou are not allowed to type that.");
        ConfigUtils.add(Language.file, "verbose_message", "&8[&2Spartan&8] &c{player} failed {detection} (VL: {vls:detection}) &8[&7(&fVersion: {server:version}&7)&8, &7(&fSilent: {silent:detection}&7)&8, (&fPing: {ping}ms&7)&8, &7(&fTPS: {tps}&7)&8, &7(&fPlib: {plib}&7)&8, &7(&fAsync: {async}&7)&8, &7(&fOnline: {online}&7)&8, &7(&f{info}&7)&8]");
        ConfigUtils.add(Language.file, "non_existing_check", "&8[&2Spartan&8] &cThis check doesn't exist.");
        ConfigUtils.add(Language.file, "massive_command_reason", "&cThe length of the reason is too big.");
        ConfigUtils.add(Language.file, "check_enable_message", "&8[&2Spartan&8] &aYou enabled the check&8:&7 {detection}");
        ConfigUtils.add(Language.file, "check_disable_message", "&8[&2Spartan&8] &cYou disabled the check&8:&7 {detection}");
        ConfigUtils.add(Language.file, "warning_message", "&8[&2Spartan&8]&c {reason}");
        ConfigUtils.add(Language.file, "warning_feedback_message", "&8[&2Spartan&8]&7 You warned &c{player} &7for&8: &4{reason}");
        ConfigUtils.add(Language.file, "bypass_message", "&8[&2Spartan&8] &c{player} &7is now bypassing the &4{detection} &7check for &e{time} &7seconds.");
        ConfigUtils.add(Language.file, "ban_message", "&8[&2Spartan&8]&7 You banned &c{player} &7for &4{reason}");
        ConfigUtils.add(Language.file, "unban_message", "&8[&2Spartan&8]&7 You unbanned &c{player}");
        ConfigUtils.add(Language.file, "player_not_banned", "&8[&2Spartan&8]&c This player is not banned.");
        ConfigUtils.add(Language.file, "ban_broadcast_message", "&8[&2Spartan&8]&c {player}&7 was banned for&4 {reason}");
        ConfigUtils.add(Language.file, "ban_reason", "&8[&2Spartan&8]&c {reason}");
        ConfigUtils.add(Language.file, "chat_cooldown_message", "&cPlease wait {time} second(s) until typing again.");
        ConfigUtils.add(Language.file, "mining_notifications_enable", "&8[&2Spartan&8] &aYou enabled mining notifications.");
        ConfigUtils.add(Language.file, "mining_notifications_disable", "&8[&2Spartan&8] &cYou disabled mining notifications.");
        ConfigUtils.add(Language.file, "reconnect_kick_message", "&8[&2Spartan&8]&c Please wait a few seconds before logging in back.");
        ConfigUtils.add(Language.file, "empty_ban_list", "&8[&2Spartan&8]&c There are currently no banned moderation.");
        ConfigUtils.add(Language.file, "command_cooldown_message", "&cPlease wait {time} second(s) until dispatching a command again.");
        ConfigUtils.add(Language.file, "wave_start_message", "&8[&2Spartan&8]&c The wave is starting.");
        ConfigUtils.add(Language.file, "wave_end_message", "&8[&2Spartan&8]&c The wave has ended with a total of {total} action(s).");
        ConfigUtils.add(Language.file, "wave_clear_message", "&8[&2Spartan&8]&c The wave was cleared.");
        ConfigUtils.add(Language.file, "wave_add_message", "&8[&2Spartan&8]&a {player} was added to the wave.");
        ConfigUtils.add(Language.file, "wave_remove_message", "&8[&2Spartan&8]&c {player} was removed from the wave.");
        ConfigUtils.add(Language.file, "full_wave_list", "&8[&2Spartan&8]&c The wave list is full.");
        ConfigUtils.add(Language.file, "empty_wave_list", "&8[&2Spartan&8]&c The wave list is empty.");
        ConfigUtils.add(Language.file, "wave_not_added_message", "&8[&2Spartan&8]&c {player} is not added to the wave.");
        ConfigUtils.add(Language.file, "staff_chat_message", "&8[&4Staff Chat&8]&c {player} &e{message}");
        ConfigUtils.add(Language.file, "staff_chat_character", "@");
        ConfigUtils.add(Language.file, "report_message", "&a{player} &7reported &c{reported} &7for&8: &4{reason}");
        ConfigUtils.add(Language.file, "self_report_message", "&cYou can't report yourself.");
        ConfigUtils.add(Language.file, "debug_player_message", "&8[&2Spartan&8]&7 Debugging &6{player}&7's &e{type}&8: &f{info}");
        ConfigUtils.add(Language.file, "debug_enable_message", "&8[&2Spartan&8]&7 Enabled debugging for &6{player}&7's &e{type}");
        ConfigUtils.add(Language.file, "debug_disable_message", "&8[&2Spartan&8]&7 Disabled debugging for &6{player}&7's &e{type}");
        ConfigUtils.add(Language.file, "debug_disable_all_message", "&8[&2Spartan&8]&7 Disabled debugging for &6{player}");
        ConfigUtils.add(Language.file, "same_message_warning", "&8[&2Spartan&8]&c Please avoid sending the same message again.");
        ConfigUtils.add(Language.file, "player_ip_limit_kick_message", "&8[&2Spartan&8]&c The player limit of your IP has been reached.");
        ConfigUtils.add(Language.file, "disabled_log_saving", "&8[&2Spartan&8]&c All log saving options are disabled.");
        ConfigUtils.add(Language.file, "not_enough_saved_logs", "&8[&2Spartan&8]&c Not enough saved logs. Must be at least {amount} rows.");
        ConfigUtils.add(Language.file, "unknown_command", "&fUnknown command. Please type \"/help\" for help.");
        ConfigUtils.add(Language.file, "failed_command", "&8[&2Spartan&8]&c Command failed. Please check your arguments and try again.");
    }
    
    public static String getMessage(final String key) {
        if (Language.d.containsKey(key)) {
            return Language.d.get(key);
        }
        if (!Language.file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Language.file);
        if (loadConfiguration == null) {
            return key;
        }
        final String string = loadConfiguration.getString(key);
        if (string == null) {
            return key;
        }
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', string);
        Language.d.put(key, translateAlternateColorCodes);
        return translateAlternateColorCodes;
    }
    
    static {
        Language.file = new File(Register.plugin.getDataFolder() + "/language.yml");
        d = new HashMap<String, String>(60);
    }
}
