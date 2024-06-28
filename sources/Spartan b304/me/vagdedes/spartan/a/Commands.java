package me.vagdedes.spartan.a;

import java.util.UUID;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.features.e.DeveloperReport;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.a.a.Settings;
import org.apache.commons.lang.StringUtils;
import java.sql.Timestamp;
import me.vagdedes.spartan.checks.combat.a.a.CATraining;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.b.Wave;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.features.a.ConfigurationDiagnostics;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import me.vagdedes.spartan.d.DebugMenu;
import me.vagdedes.spartan.d.PlayerInfo;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.a.b.BanManagement;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.d.MainMenu;
import me.vagdedes.spartan.d.SynMenu;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.Command;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.g.Patreon;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.c.SynManager;
import me.vagdedes.spartan.k.c.PermissionUtils;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor
{
    public Commands() {
        super();
    }
    
    public static boolean a(final CommandSender commandSender) {
        if (!(commandSender instanceof Player) || PermissionUtils.has((Player)commandSender) || !SynManager.s()) {
            final String version = Register.plugin.getDescription().getVersion();
            commandSender.sendMessage("");
            commandSender.sendMessage("§4Spartan Anti-Cheat §8[§7(§fVersion: " + version + "§7)§8, §7(§fID: " + IDs.spigot() + "/" + IDs.nonce() + "§7)§8]");
            return true;
        }
        commandSender.sendMessage(Language.getMessage("unknown_command"));
        return false;
    }
    
    public static void a(final CommandSender commandSender) {
        if (Patreon.o()) {
            a(commandSender);
            if (commandSender instanceof Player) {
                final Player player = (Player)commandSender;
                final boolean a = PermissionUtils.a((Player)commandSender, Enums.Permission.verbose);
                if (PermissionUtils.a(player, Enums.Permission.manage)) {
                    player.sendMessage(ChatColor.RED + "/spartan menu");
                }
                if (PermissionUtils.a(player, Enums.Permission.reload)) {
                    player.sendMessage(ChatColor.RED + "/spartan reload/rl");
                }
                if (a || PermissionUtils.a(player, Enums.Permission.mining)) {
                    player.sendMessage(ChatColor.RED + "/spartan notifications <verbose/mining>");
                }
                if (a) {
                    player.sendMessage(ChatColor.RED + "/spartan verbose <player> <module> <info>");
                }
                if (PermissionUtils.a(player, Enums.Permission.info)) {
                    player.sendMessage(ChatColor.RED + "/spartan info [player]");
                }
                if (PermissionUtils.a(player, Enums.Permission.debug)) {
                    player.sendMessage(ChatColor.RED + "/spartan debug [player]");
                }
                if (PermissionUtils.a(player, Enums.Permission.ping)) {
                    player.sendMessage(ChatColor.RED + "/spartan ping [player]");
                }
                if (PermissionUtils.a(player, Enums.Permission.kick)) {
                    player.sendMessage(ChatColor.RED + "/spartan kick <player> <reason>");
                }
                if (PermissionUtils.a(player, Enums.Permission.toggle)) {
                    player.sendMessage(ChatColor.RED + "/spartan toggle <check>");
                }
                if (PermissionUtils.a(player, Enums.Permission.warn)) {
                    player.sendMessage(ChatColor.RED + "/spartan warn <player> <reason>");
                }
                if (PermissionUtils.a(player, Enums.Permission.use_bypass)) {
                    player.sendMessage(ChatColor.RED + "/spartan bypass <player> <check> <seconds>");
                }
                if (PermissionUtils.a(player, Enums.Permission.ban)) {
                    player.sendMessage(ChatColor.RED + "/spartan ban <player> <reason>");
                    player.sendMessage(ChatColor.RED + "/spartan tempban <player> <(time)m/h/d/w/y> <reason>");
                }
                if (PermissionUtils.a(player, Enums.Permission.unban)) {
                    player.sendMessage(ChatColor.RED + "/spartan unban <player>");
                }
                if (PermissionUtils.a(player, Enums.Permission.ban_info)) {
                    player.sendMessage(ChatColor.RED + "/spartan ban-info <player>");
                    player.sendMessage(ChatColor.RED + "/spartan ban-list");
                }
                if (PermissionUtils.a(player, Enums.Permission.wave)) {
                    player.sendMessage(ChatColor.RED + "/spartan wave <add/remove/clear/run/list> [player] [command]");
                }
                if (PermissionUtils.a(player, Enums.Permission.use_report)) {
                    player.sendMessage(ChatColor.RED + "/spartan report <player> <reason>");
                }
                if (PermissionUtils.a(player, Enums.Permission.admin)) {
                    player.sendMessage(ChatColor.DARK_RED + "/spartan developer-report [check] [player]");
                }
                final Enums.Permission[] values = Enums.Permission.values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    if (PermissionUtils.a(player, values[i])) {
                        player.sendMessage(ChatColor.GOLD + "/spartan syn");
                        break;
                    }
                }
                if (PermissionUtils.a(player, Enums.Permission.condition)) {
                    player.sendMessage(ChatColor.YELLOW + "/spartan <player> if <condition> equals <result> do <command>");
                    player.sendMessage(ChatColor.YELLOW + "/spartan <player> if <condition> contains <result> do <command>");
                    player.sendMessage(ChatColor.YELLOW + "/spartan <player> if <number> is-less-than <result> do <command>");
                    player.sendMessage(ChatColor.YELLOW + "/spartan <player> if <number> is-greater-than <result> do <command>");
                }
            }
            else {
                commandSender.sendMessage(ChatColor.RED + "/spartan reload/rl");
                commandSender.sendMessage(ChatColor.RED + "/spartan verbose <player> <module> <info>");
                commandSender.sendMessage(ChatColor.RED + "/spartan ping <player>");
                commandSender.sendMessage(ChatColor.RED + "/spartan kick <player> <reason>");
                commandSender.sendMessage(ChatColor.RED + "/spartan toggle <check>");
                commandSender.sendMessage(ChatColor.RED + "/spartan warn <player> <reason>");
                commandSender.sendMessage(ChatColor.RED + "/spartan bypass <player> <check> <seconds>");
                commandSender.sendMessage(ChatColor.RED + "/spartan ban <player> <reason>");
                commandSender.sendMessage(ChatColor.RED + "/spartan tempban <player> <(time)m/h/d/w/y> <reason>");
                commandSender.sendMessage(ChatColor.RED + "/spartan unban <player>");
                commandSender.sendMessage(ChatColor.RED + "/spartan ban-info <player>");
                commandSender.sendMessage(ChatColor.RED + "/spartan ban-list");
                commandSender.sendMessage(ChatColor.RED + "/spartan wave <add/remove/clear/run/list> [player] [command]");
                commandSender.sendMessage(ChatColor.YELLOW + "/spartan <player> if <condition> equals <result> do <command>");
                commandSender.sendMessage(ChatColor.YELLOW + "/spartan <player> if <condition> contains <result> do <command>");
                commandSender.sendMessage(ChatColor.YELLOW + "/spartan <player> if <number> is-less-than <result> do <command>");
                commandSender.sendMessage(ChatColor.YELLOW + "/spartan <player> if <number> is-greater-than <result> do <command>");
            }
        }
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] array) {
        final boolean b = commandSender instanceof Player;
        if (s.equalsIgnoreCase("Spartan") && (commandSender instanceof ConsoleCommandSender || b)) {
            final SpartanPlayer spartanPlayer = b ? SpartanBukkit.a(((Player)commandSender).getUniqueId()) : null;
            Patreon.a(commandSender, array);
            if (array.length == 1) {
                if (array[0].equalsIgnoreCase("Syn")) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    SynMenu.A(spartanPlayer);
                }
                else if (array[0].equalsIgnoreCase("Menu")) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.manage)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    MainMenu.b(spartanPlayer, false);
                }
                else if (array[0].equalsIgnoreCase("Ping")) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.ping)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    commandSender.sendMessage(ConfigUtils.a((OfflinePlayer)commandSender, Language.getMessage("ping_command_message"), (Enums.HackType)null));
                }
                else if (array[0].equalsIgnoreCase("Ban-list")) {
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.ban_info)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    commandSender.sendMessage(ChatColor.GRAY + "Banned Players" + ChatColor.DARK_GRAY + ":");
                    commandSender.sendMessage(BanManagement.a());
                }
                else if (array[0].equalsIgnoreCase("Reload") || array[0].equalsIgnoreCase("Rl")) {
                    if (commandSender instanceof Player) {
                        if (!PermissionUtils.a((Player)commandSender, Enums.Permission.reload)) {
                            commandSender.sendMessage(Language.getMessage("command_no_access"));
                            return true;
                        }
                        Config.a((Player)commandSender, false);
                    }
                    else {
                        Config.a((Player)null, true);
                    }
                }
                else if (array[0].equalsIgnoreCase("Info")) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.info)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    PlayerInfo.a(spartanPlayer, (Player)commandSender);
                }
                else if (array[0].equalsIgnoreCase("Debug")) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.debug)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    DebugMenu.a((Player)commandSender, (Player)commandSender);
                }
                else if (array[0].equalsIgnoreCase("Notifications")) {
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.verbose)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    VerboseNotifications.a((Player)commandSender, 0);
                }
                else if (array[0].equalsIgnoreCase("Diagnostics")) {
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.manage)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    ConfigurationDiagnostics.b(commandSender);
                }
                else {
                    a(commandSender);
                }
            }
            else if (array.length == 2) {
                if (array[0].equalsIgnoreCase("Notifications")) {
                    final String s2 = array[1];
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (s2.equalsIgnoreCase("Verbose")) {
                        if (!PermissionUtils.a((Player)commandSender, Enums.Permission.verbose)) {
                            commandSender.sendMessage(Language.getMessage("command_no_access"));
                            return true;
                        }
                        VerboseNotifications.a((Player)commandSender, 0);
                    }
                    else if (s2.equalsIgnoreCase("Mining")) {
                        final Player player = (Player)commandSender;
                        if (!PermissionUtils.a(player, Enums.Permission.mining)) {
                            commandSender.sendMessage(Language.getMessage("command_no_access"));
                            return true;
                        }
                        MiningNotifications.d(player);
                    }
                }
                else if (array[0].equalsIgnoreCase("Wave")) {
                    final String s3 = array[1];
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.wave)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (s3.equalsIgnoreCase("run")) {
                        if (Wave.getSize() == 0) {
                            commandSender.sendMessage(Language.getMessage("empty_wave_list"));
                            return true;
                        }
                        Wave.run();
                    }
                    else if (s3.equalsIgnoreCase("Clear")) {
                        Wave.clear();
                        commandSender.sendMessage(Language.getMessage("wave_clear_message"));
                    }
                    else if (s3.equalsIgnoreCase("List")) {
                        commandSender.sendMessage(ChatColor.GRAY + "Wave Queued Players" + ChatColor.DARK_GRAY + ":");
                        commandSender.sendMessage(Wave.b());
                    }
                }
                else if (array[0].equalsIgnoreCase("Info")) {
                    final Player player2 = Bukkit.getPlayer(array[1]);
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.info)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (player2 == null || !player2.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    PlayerInfo.a(spartanPlayer, player2);
                }
                else if (array[0].equalsIgnoreCase("Debug")) {
                    final Player player3 = Bukkit.getPlayer(array[1]);
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.debug)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (player3 == null || !player3.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    DebugMenu.a((Player)commandSender, player3);
                }
                else if (array[0].equalsIgnoreCase("Ping")) {
                    final Player player4 = Bukkit.getPlayer(array[1]);
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (player4 == null || !player4.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.ping)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    commandSender.sendMessage(ConfigUtils.a((OfflinePlayer)player4, Language.getMessage("ping_command_message"), (Enums.HackType)null));
                }
                else if (array[0].equalsIgnoreCase("Train")) {
                    final Player player5 = Bukkit.getPlayer(array[1]);
                    if (player5 == null || !player5.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.admin)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    CATraining.b(spartanPlayer, SpartanBukkit.a(player5.getUniqueId()));
                }
                else if (array[0].equalsIgnoreCase("Toggle")) {
                    String string = array[1];
                    if (b && !PermissionUtils.a((Player)commandSender, Enums.Permission.toggle)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    boolean b2 = false;
                    for (final Enums.HackType hackType : Enums.HackType.values()) {
                        if (hackType.toString().equalsIgnoreCase(string)) {
                            string = hackType.toString();
                            b2 = true;
                            break;
                        }
                    }
                    if (b2) {
                        final Enums.HackType value = Enums.HackType.valueOf(string);
                        if (Config.isEnabled(value)) {
                            Config.b(value, true);
                            commandSender.sendMessage(ConfigUtils.a((OfflinePlayer)(b ? ((Player)commandSender) : null), Language.getMessage("check_disable_message"), value));
                        }
                        else {
                            Config.a(value, true);
                            commandSender.sendMessage(ConfigUtils.a((OfflinePlayer)(b ? ((Player)commandSender) : null), Language.getMessage("check_enable_message"), value));
                        }
                    }
                    else {
                        commandSender.sendMessage(Language.getMessage("non_existing_check"));
                    }
                }
                else if (array[0].equalsIgnoreCase("Unban")) {
                    final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(array[1]);
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.unban)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (!BanManagement.isBanned(offlinePlayer.getUniqueId())) {
                        commandSender.sendMessage(Language.getMessage("player_not_banned"));
                        return true;
                    }
                    BanManagement.a(offlinePlayer.getUniqueId());
                    commandSender.sendMessage(ConfigUtils.a(offlinePlayer, Language.getMessage("unban_message"), (Enums.HackType)null));
                }
                else if (array[0].equalsIgnoreCase("Ban-info")) {
                    final OfflinePlayer offlinePlayer2 = Bukkit.getOfflinePlayer(array[1]);
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.ban_info)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (!BanManagement.isBanned(offlinePlayer2.getUniqueId())) {
                        commandSender.sendMessage(Language.getMessage("player_not_banned"));
                        return true;
                    }
                    final UUID uniqueId = offlinePlayer2.getUniqueId();
                    commandSender.sendMessage(ChatColor.GRAY + "Ban Information" + ChatColor.DARK_GRAY + ":");
                    commandSender.sendMessage(ChatColor.GRAY + "Player" + ChatColor.DARK_GRAY + ": " + ChatColor.RED + offlinePlayer2.getName());
                    commandSender.sendMessage(ChatColor.GRAY + "Punisher" + ChatColor.DARK_GRAY + ": " + ChatColor.GREEN + BanManagement.a(uniqueId, "punisher"));
                    commandSender.sendMessage(ChatColor.GRAY + "Reason" + ChatColor.DARK_GRAY + ": " + ChatColor.YELLOW + BanManagement.a(uniqueId, "reason"));
                    final long a = BanManagement.a(uniqueId);
                    if (a != 0L) {
                        commandSender.sendMessage(ChatColor.GRAY + "Expiration" + ChatColor.DARK_GRAY + ": " + ChatColor.AQUA + new Timestamp(a).toString().substring(0, 10));
                    }
                    else {
                        commandSender.sendMessage(ChatColor.GRAY + "Expiration" + ChatColor.DARK_GRAY + ": " + ChatColor.AQUA + "Never");
                    }
                }
                else if (array[0].equalsIgnoreCase("Syn")) {
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.admin)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    final String s4 = array[1];
                    if (s4.length() <= 10 && StringUtils.isNumeric(s4)) {
                        final int cooldown = SynManager.getCooldown();
                        if (cooldown > 0) {
                            commandSender.sendMessage(ChatColor.RED + "Please wait " + cooldown + " second(s) before doing this again.");
                            return true;
                        }
                        try {
                            ConfigUtils.set(Settings.getFile(), "syn_id", Integer.valueOf(s4));
                            Settings.create();
                            commandSender.sendMessage(ChatColor.GREEN + "The Syn ID was successfully saved.");
                            SynManager.verify();
                        }
                        catch (Exception ex) {
                            commandSender.sendMessage(ChatColor.RED + "An error occurred: " + ex.getMessage());
                        }
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + "The Syn ID must be numeric.");
                    }
                }
                else if (array[0].equalsIgnoreCase("Developer-report")) {
                    String string2 = array[1];
                    if (b && !PermissionUtils.a((Player)commandSender, Enums.Permission.admin)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (!SearchEngine.d()) {
                        commandSender.sendMessage(Language.getMessage("disabled_log_saving"));
                        return true;
                    }
                    boolean b3 = false;
                    for (final Enums.HackType hackType2 : Enums.HackType.values()) {
                        if (hackType2.toString().equalsIgnoreCase(string2)) {
                            string2 = hackType2.toString();
                            b3 = true;
                            break;
                        }
                    }
                    if (b3) {
                        final Enums.HackType value2 = Enums.HackType.valueOf(string2);
                        SearchEngine.d(true);
                        commandSender.sendMessage(DeveloperReport.a(DeveloperReport.a(commandSender, SearchEngine.a(null, value2), value2, (String)null)));
                    }
                    else {
                        SearchEngine.d(true);
                        commandSender.sendMessage(DeveloperReport.a(DeveloperReport.a(commandSender, SearchEngine.a(string2, null), (Enums.HackType)null, string2)));
                    }
                }
                else {
                    a(commandSender);
                }
            }
            else if (array.length > 2) {
                final StringBuilder sb = new StringBuilder();
                for (int k = 2; k < array.length; ++k) {
                    sb.append(array[k] + " ");
                }
                final String substring = sb.toString().substring(0, sb.length() - 1);
                if (substring.length() > 256) {
                    commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                    return true;
                }
                if (array[0].equalsIgnoreCase("Kick")) {
                    final Player player6 = Bukkit.getPlayer(array[1]);
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.kick)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (player6 == null || !player6.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    if ((commandSender instanceof Player && substring.length() > 256) || substring.length() > 4096) {
                        commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                        return true;
                    }
                    PunishUtils.l(SpartanBukkit.a(player6.getUniqueId()), substring);
                }
                else if (array[0].equalsIgnoreCase("Warn")) {
                    final Player player7 = Bukkit.getPlayer(array[1]);
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.warn)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (player7 == null || !player7.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    if (substring.length() > 256) {
                        commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                        return true;
                    }
                    PunishUtils.a(commandSender, SpartanBukkit.a(player7.getUniqueId()), substring);
                }
                else if (array[0].equalsIgnoreCase("Ban")) {
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.ban)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (substring.length() > 256) {
                        commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                        return true;
                    }
                    final OfflinePlayer offlinePlayer3 = Bukkit.getOfflinePlayer(array[1]);
                    BanManagement.a(offlinePlayer3.getUniqueId(), commandSender.getName(), substring, 0L);
                    commandSender.sendMessage(ConfigUtils.a(offlinePlayer3, Language.getMessage("ban_message").replace("{reason}", substring).replace("{punisher}", commandSender.getName()).replace("{expiration}", "Permanently"), (Enums.HackType)null));
                }
                else if (array[0].equalsIgnoreCase("Report")) {
                    final Player player8 = Bukkit.getPlayer(array[1]);
                    if (!(commandSender instanceof Player)) {
                        commandSender.sendMessage(Language.getMessage("non_console_command"));
                        return true;
                    }
                    if (!PermissionUtils.a((Player)commandSender, Enums.Permission.use_report)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (player8 == null || !player8.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    if (substring.length() > 256) {
                        commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                        return true;
                    }
                    PunishUtils.b(spartanPlayer, SpartanBukkit.a(player8.getUniqueId()), substring);
                }
                else if (array[0].equalsIgnoreCase("Bypass")) {
                    if (array.length == 4) {
                        final Player player9 = Bukkit.getPlayer(array[1]);
                        final String s5 = array[2];
                        final String s6 = array[3];
                        if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.use_bypass)) {
                            commandSender.sendMessage(Language.getMessage("command_no_access"));
                            return true;
                        }
                        if (player9 == null || !player9.isOnline()) {
                            commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                            return true;
                        }
                        try {
                            final Enums.HackType value3 = Enums.HackType.valueOf(s5);
                            try {
                                final int int1 = Integer.parseInt(s6);
                                if (int1 < 1 || int1 > 3600) {
                                    commandSender.sendMessage(ChatColor.RED + "Seconds must be between 1 and 3600.");
                                    return true;
                                }
                                VL.a(SpartanBukkit.a(player9.getUniqueId()), value3, int1 * 20);
                                commandSender.sendMessage(ConfigUtils.a((OfflinePlayer)player9, Language.getMessage("bypass_message"), value3).replace((CharSequence)"{time}", (CharSequence)String.valueOf(int1)));
                            }
                            catch (Exception ex2) {
                                commandSender.sendMessage(ChatColor.RED + s6 + " is not a valid number.");
                            }
                        }
                        catch (Exception ex3) {
                            commandSender.sendMessage(Language.getMessage("non_existing_check"));
                        }
                    }
                    else {
                        a(commandSender);
                    }
                }
                else if (array[0].equalsIgnoreCase("Wave")) {
                    final String s7 = array[1];
                    final OfflinePlayer offlinePlayer4 = Bukkit.getOfflinePlayer(array[2]);
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.wave)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (s7.equalsIgnoreCase("add") && array.length >= 4) {
                        if (Wave.getSize() >= 100) {
                            commandSender.sendMessage(Language.getMessage("full_wave_list"));
                            return true;
                        }
                        final StringBuilder sb2 = new StringBuilder();
                        for (int l = 3; l < array.length; ++l) {
                            sb2.append(array[l] + " ");
                        }
                        final String substring2 = sb2.toString().substring(0, sb2.length() - 1);
                        if ((commandSender instanceof Player && substring2.length() > 256) || substring2.length() > 4096) {
                            commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                            return true;
                        }
                        Wave.a(offlinePlayer4.getUniqueId(), substring2);
                        commandSender.sendMessage(ConfigUtils.a(offlinePlayer4, Language.getMessage("wave_add_message"), (Enums.HackType)null));
                    }
                    else if (s7.equalsIgnoreCase("remove") && array.length >= 3) {
                        if (Wave.getSize() == 0) {
                            commandSender.sendMessage(Language.getMessage("empty_wave_list"));
                            return true;
                        }
                        final UUID uniqueId2 = offlinePlayer4.getUniqueId();
                        if (!Wave.b(uniqueId2)) {
                            commandSender.sendMessage(ConfigUtils.a(offlinePlayer4, Language.getMessage("wave_not_added_message"), (Enums.HackType)null));
                            return true;
                        }
                        Wave.b(uniqueId2);
                        commandSender.sendMessage(ConfigUtils.a(offlinePlayer4, Language.getMessage("wave_remove_message"), (Enums.HackType)null));
                    }
                    else {
                        a(commandSender);
                    }
                }
                else if (array.length >= 4 && array[0].equalsIgnoreCase("Verbose")) {
                    final Player player10 = Bukkit.getPlayer(array[1]);
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.verbose)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (player10 == null || !player10.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    final StringBuilder sb3 = new StringBuilder();
                    for (int n = 3; n < array.length; ++n) {
                        sb3.append(array[n] + " ");
                    }
                    final String substring3 = sb3.toString().substring(0, sb3.length() - 1);
                    if (substring3.length() > 256) {
                        commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                        return true;
                    }
                    PunishUtils.b(SpartanBukkit.a(player10.getUniqueId()), array[2], substring3);
                }
                else if (array.length >= 7) {
                    if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.condition)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    final Player player11 = Bukkit.getPlayer(array[0]);
                    if (player11 == null || !player11.isOnline()) {
                        commandSender.sendMessage(Language.getMessage("player_not_found_message"));
                        return true;
                    }
                    if (array[1].toLowerCase().equals("if") && array[5].toLowerCase().equals("do")) {
                        final String a2 = ConfigUtils.a(player11, array[2], (Enums.HackType)null);
                        final String a3 = ConfigUtils.a(player11, array[4], (Enums.HackType)null);
                        final StringBuilder sb4 = new StringBuilder();
                        for (int n2 = 6; n2 < array.length; ++n2) {
                            sb4.append(array[n2] + " ");
                        }
                        final String a4 = ConfigUtils.a(player11, sb4.toString().substring(0, sb4.length() - 1), (Enums.HackType)null);
                        final String lowerCase = array[3].toLowerCase();
                        int n3 = -1;
                        switch (lowerCase.hashCode()) {
                            case -1295482945: {
                                if (lowerCase.equals("equals")) {
                                    n3 = 0;
                                    break;
                                }
                                break;
                            }
                            case 61: {
                                if (lowerCase.equals("=")) {
                                    n3 = 1;
                                    break;
                                }
                                break;
                            }
                            case -567445985: {
                                if (lowerCase.equals("contains")) {
                                    n3 = 2;
                                    break;
                                }
                                break;
                            }
                            case 244443250: {
                                if (lowerCase.equals("is-less-than")) {
                                    n3 = 3;
                                    break;
                                }
                                break;
                            }
                            case 60: {
                                if (lowerCase.equals("<")) {
                                    n3 = 4;
                                    break;
                                }
                                break;
                            }
                            case -369640617: {
                                if (lowerCase.equals("is-greater-than")) {
                                    n3 = 5;
                                    break;
                                }
                                break;
                            }
                            case 62: {
                                if (lowerCase.equals(">")) {
                                    n3 = 6;
                                    break;
                                }
                                break;
                            }
                        }
                        switch (n3) {
                            case 0: {
                                if (a2.equalsIgnoreCase(a3)) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                if (a2.equalsIgnoreCase(a3)) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                            case 2: {
                                if (a2.contains(a3)) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                if ((MathUtils.validNum(a2) && MathUtils.validNum(a3) && a(a2) < a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validDouble(a3) && a(a2) < a(a3)) || (MathUtils.validNum(a2) && MathUtils.validDouble(a3) && a(a2) < a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validNum(a3) && a(a2) < a(a3))) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                if ((MathUtils.validNum(a2) && MathUtils.validNum(a3) && a(a2) < a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validDouble(a3) && a(a2) < a(a3)) || (MathUtils.validNum(a2) && MathUtils.validDouble(a3) && a(a2) < a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validNum(a3) && a(a2) < a(a3))) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                            case 5: {
                                if ((MathUtils.validNum(a2) && MathUtils.validNum(a3) && a(a2) > a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validDouble(a3) && a(a2) > a(a3)) || (MathUtils.validNum(a2) && MathUtils.validDouble(a3) && a(a2) > a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validNum(a3) && a(a2) > a(a3))) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                            case 6: {
                                if ((MathUtils.validNum(a2) && MathUtils.validNum(a3) && a(a2) > a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validDouble(a3) && a(a2) > a(a3)) || (MathUtils.validNum(a2) && MathUtils.validDouble(a3) && a(a2) > a(a3)) || (MathUtils.validDouble(a2) && MathUtils.validNum(a3) && a(a2) > a(a3))) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a4);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
                else if (array.length == 3 && array[0].equalsIgnoreCase("Developer-report")) {
                    String string3 = array[1];
                    if (b && !PermissionUtils.a((Player)commandSender, Enums.Permission.admin)) {
                        commandSender.sendMessage(Language.getMessage("command_no_access"));
                        return true;
                    }
                    if (!SearchEngine.d()) {
                        commandSender.sendMessage(Language.getMessage("disabled_log_saving"));
                        return true;
                    }
                    boolean b4 = false;
                    for (final Enums.HackType hackType3 : Enums.HackType.values()) {
                        if (hackType3.toString().equalsIgnoreCase(string3)) {
                            string3 = hackType3.toString();
                            b4 = true;
                            break;
                        }
                    }
                    if (b4) {
                        final Enums.HackType value4 = Enums.HackType.valueOf(string3);
                        final String s8 = array[2];
                        if (s8.length() <= 16) {
                            SearchEngine.d(true);
                            commandSender.sendMessage(DeveloperReport.a(DeveloperReport.a(commandSender, SearchEngine.a(s8, value4), value4, s8)));
                        }
                    }
                    else {
                        commandSender.sendMessage(Language.getMessage("non_existing_check"));
                    }
                }
                else if (array.length > 3) {
                    final StringBuilder sb5 = new StringBuilder();
                    for (int n5 = 3; n5 < array.length; ++n5) {
                        sb5.append(array[n5] + " ");
                    }
                    final String substring4 = sb5.toString().substring(0, sb5.length() - 1);
                    if (array[0].equalsIgnoreCase("Tempban")) {
                        if (commandSender instanceof Player && !PermissionUtils.a((Player)commandSender, Enums.Permission.ban)) {
                            commandSender.sendMessage(Language.getMessage("command_no_access"));
                            return true;
                        }
                        if (substring4.length() > 256) {
                            commandSender.sendMessage(Language.getMessage("massive_command_reason"));
                            return true;
                        }
                        final OfflinePlayer offlinePlayer5 = Bukkit.getOfflinePlayer(array[1]);
                        final String s9 = array[2];
                        final String[] array2 = { "m", "h", "d", "w", "y" };
                        char char1 = 'a';
                        for (final String suffix : array2) {
                            if (s9.endsWith(suffix)) {
                                char1 = suffix.charAt(0);
                                break;
                            }
                        }
                        if (char1 == 'a') {
                            commandSender.sendMessage(Language.getMessage("failed_command"));
                            return true;
                        }
                        final String substring5 = s9.substring(0, s9.length() - 1);
                        if (!MathUtils.validNum(substring5)) {
                            commandSender.sendMessage(Language.getMessage("failed_command"));
                            return true;
                        }
                        final long a5 = TimeUtils.a(Integer.valueOf(substring5), char1);
                        if (a5 == 0L) {
                            commandSender.sendMessage(Language.getMessage("failed_command"));
                            return true;
                        }
                        final long time = a5 + System.currentTimeMillis();
                        BanManagement.a(offlinePlayer5.getUniqueId(), commandSender.getName(), substring4, time);
                        commandSender.sendMessage(ConfigUtils.a(offlinePlayer5, Language.getMessage("ban_message").replace("{reason}", substring4).replace("{punisher}", commandSender.getName()).replace("{expiration}", new Timestamp(time).toString().substring(0, 10)), (Enums.HackType)null));
                    }
                }
                else {
                    a(commandSender);
                }
            }
            else {
                a(commandSender);
            }
        }
        return false;
    }
    
    public static int a(final String s) {
        return Integer.parseInt(s);
    }
    
    public static double a(final String s) {
        return Double.parseDouble(s);
    }
}
