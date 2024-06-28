package me.vagdedes.spartan.a.b;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.Register;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class ChatProtection
{
    private static File file;
    private static final HashMap<String, ArrayList<String>> d;
    private static final HashMap<UUID, String> m;
    
    public ChatProtection() {
        super();
    }
    
    public static void clear() {
        ChatProtection.d.clear();
        ChatProtection.m.clear();
    }
    
    public static void create() {
        ChatProtection.file = new File(Register.plugin.getDataFolder() + "/chat_protection.yml");
        clear();
        if (!ChatProtection.file.exists()) {
            ConfigUtils.add(ChatProtection.file, "blocked_commands.1", "blocked_commands_here");
            ConfigUtils.add(ChatProtection.file, "blocked_words.1", "blocked_words_here");
        }
    }
    
    private static boolean a(final String s, final String s2, final String s3) {
        return (s2.equalsIgnoreCase("blocked_words") && (s.toLowerCase().contains(s3.toLowerCase() + " ") || s.toLowerCase().contains(" " + s3.toLowerCase()) || s.equalsIgnoreCase(s3))) || (s2.equalsIgnoreCase("blocked_commands") && (s.equalsIgnoreCase(s3) || s.toLowerCase().startsWith(s3.toLowerCase() + " ")));
    }
    
    public static boolean a(final String s, final String s2) {
        if (ChatProtection.d.containsKey(s2)) {
            for (final String s3 : (ArrayList<String>)ChatProtection.d.get(s2)) {
                if (s3 != null && a(s, s2, s3)) {
                    return true;
                }
            }
        }
        if (!ChatProtection.file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(ChatProtection.file);
        if (loadConfiguration == null) {
            return false;
        }
        boolean b = false;
        final ArrayList<String> value = new ArrayList<String>();
        try {
            for (final String str : loadConfiguration.getConfigurationSection(s2).getKeys(true)) {
                if (str != null && StringUtils.isNumeric(str)) {
                    final String string = loadConfiguration.getString(s2 + "." + str);
                    if (string == null) {
                        continue;
                    }
                    value.add(string);
                    if (!a(s, s2, string)) {
                        continue;
                    }
                    b = true;
                }
            }
            ChatProtection.d.put(s2, value);
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final String s) {
        if (PermissionUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Enums.Permission.chat_protection)) {
            return false;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        String string = "";
        for (String substring : s.split(" ")) {
            if (substring.startsWith("-")) {
                substring = substring.substring(1);
            }
            if (!MathUtils.validDouble(substring)) {
                string += substring;
            }
        }
        if (!CooldownUtils.g(spartanPlayer, "chat=cooldown=delay")) {
            final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
            player.sendMessage(ConfigUtils.a((OfflinePlayer)player, Language.getMessage("chat_cooldown_message").replace("{time}", String.valueOf((double)Double.valueOf(CooldownUtils.a(spartanPlayer, "chat=cooldown=delay")) / 20.0)), (Enums.HackType)null));
            return true;
        }
        if (a(s, "blocked_words")) {
            Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendMessage(Language.getMessage("blocked_word_message"));
            return true;
        }
        final int b = Settings.b("chat_cooldown");
        final boolean b2 = b > 0;
        if (b2 && ChatProtection.m.containsKey(uniqueId) && ((String)ChatProtection.m.get(uniqueId)).equalsIgnoreCase(string)) {
            final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
            player2.sendMessage(ConfigUtils.a((OfflinePlayer)player2, Language.getMessage("same_message_warning"), (Enums.HackType)null));
            return true;
        }
        if (b2) {
            CooldownUtils.d(spartanPlayer, "chat=cooldown=delay", ((b > 60) ? 60 : b) * 20);
        }
        ChatProtection.m.put(uniqueId, string);
        return false;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final String s, final boolean b) {
        if (PermissionUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Enums.Permission.chat_protection)) {
            return false;
        }
        if (!CooldownUtils.g(spartanPlayer, "command=cooldown=delay") && !b) {
            final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
            player.sendMessage(ConfigUtils.a((OfflinePlayer)player, Language.getMessage("command_cooldown_message").replace("{time}", String.valueOf((double)Double.valueOf(CooldownUtils.a(spartanPlayer, "command=cooldown=delay")) / 20.0)), (Enums.HackType)null));
            return true;
        }
        if (!b) {
            final int b2 = Settings.b("command_cooldown");
            if (b2 > 0) {
                CooldownUtils.d(spartanPlayer, "command=cooldown=delay", ((b2 > 60) ? 60 : b2) * 20);
            }
        }
        if (a(me.vagdedes.spartan.k.d.StringUtils.substring(s, 1, s.length()), "blocked_commands")) {
            if (!b) {
                Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendMessage(Language.getMessage("blocked_command_message"));
            }
            return true;
        }
        return false;
    }
    
    public static boolean a(final CommandSender commandSender, final String s) {
        if (a(me.vagdedes.spartan.k.d.StringUtils.substring(s, 1, s.length()), "blocked_commands")) {
            commandSender.sendMessage(Language.getMessage("blocked_command_message"));
            return true;
        }
        return false;
    }
    
    static {
        ChatProtection.file = new File(Register.plugin.getDataFolder() + "/chat_protection.yml");
        d = new HashMap<String, ArrayList<String>>();
        m = new HashMap<UUID, String>();
    }
}
