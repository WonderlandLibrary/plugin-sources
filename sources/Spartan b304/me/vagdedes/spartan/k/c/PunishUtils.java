package me.vagdedes.spartan.k.c;

import me.vagdedes.spartan.checks.f.XRay;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.k.e.ClickableMessage;
import org.bukkit.Material;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.features.d.FalsePositiveDetection;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import me.vagdedes.spartan.features.b.FileLogs;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class PunishUtils
{
    private static final int Y = 2;
    
    public PunishUtils() {
        super();
    }
    
    public static boolean bg(final SpartanPlayer spartanPlayer) {
        return CooldownUtils.a(spartanPlayer, "detection") == 2;
    }
    
    public static boolean bh(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "detection");
    }
    
    public static void P(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, "detection", 2);
    }
    
    public static void a(final CommandSender commandSender, final SpartanPlayer spartanPlayer, final String s) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        player.sendMessage(ConfigUtils.a(player, Language.getMessage("warning_message"), (Enums.HackType)null).replace((CharSequence)"{reason}", (CharSequence)s));
        if (commandSender != null) {
            commandSender.sendMessage(ConfigUtils.a(player, Language.getMessage("warning_feedback_message"), (Enums.HackType)null).replace((CharSequence)"{reason}", (CharSequence)s));
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2, final String s) {
        final Player player = Bukkit.getPlayer(spartanPlayer2.getUniqueId());
        final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (player2 == player) {
            player2.sendMessage(ConfigUtils.a(player2, Language.getMessage("self_report_message"), (Enums.HackType)null).replace("{reason}", s).replace((CharSequence)"{reported}", (CharSequence)player.getName()));
            return;
        }
        final String replace = ConfigUtils.a(player2, Language.getMessage("report_message"), (Enums.HackType)null).replace("{reason}", s).replace("{reported}", player.getName());
        boolean b = false;
        for (final SpartanPlayer spartanPlayer3 : NPC.a()) {
            if (PermissionUtils.a(Bukkit.getPlayer(spartanPlayer3.getUniqueId()), Enums.Permission.report)) {
                if (spartanPlayer3 == player2) {
                    b = true;
                }
                Bukkit.getPlayer(spartanPlayer3.getUniqueId()).sendMessage(replace);
            }
        }
        if (!b) {
            player2.sendMessage(replace);
        }
    }
    
    public static void l(final SpartanPlayer spartanPlayer, final String str) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        final String version = Register.plugin.getDescription().getVersion();
        final String a = ConfigUtils.a(player, Language.getMessage("kick_reason").replace((CharSequence)"{reason}", (CharSequence)str), (Enums.HackType)null);
        final String a2 = ConfigUtils.a(player, Language.getMessage("kick_broadcast_message").replace((CharSequence)"{reason}", (CharSequence)str), (Enums.HackType)null);
        if (Settings.canDo("broadcast_on_kick")) {
            Bukkit.broadcastMessage(a2);
        }
        else {
            for (final SpartanPlayer spartanPlayer2 : NPC.a()) {
                if (PermissionUtils.a(Bukkit.getPlayer(spartanPlayer2.getUniqueId()), Enums.Permission.kick_message)) {
                    Bukkit.getPlayer(spartanPlayer2.getUniqueId()).sendMessage(a2);
                }
            }
        }
        FileLogs.a("[Spartan " + version + "] " + player.getName() + " was kicked for " + str, true);
        player.kickPlayer(a);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, String s, final boolean b) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        P(spartanPlayer);
        if (VerboseNotifications.g()) {
            return;
        }
        s = ((s == null) ? "None" : s);
        final String value = String.valueOf(TPS.get());
        final String str = (value.length() > 5) ? value.substring(0, 5) : value;
        final String version = Register.plugin.getDescription().getVersion();
        final String replace = VersionUtils.a().toString().substring(1).replace("_", ".");
        final int a = VL.a(spartanPlayer, hackType);
        final int d = Config.d(hackType);
        final boolean b2 = FalsePositiveDetection.d(spartanPlayer, hackType) || FalsePositiveDetection.a(spartanPlayer, hackType, s);
        final boolean b3 = Settings.canDo("Detections.run_asynchronously") && Threads.enabled && ChunkManager.f();
        if (b && VL.a(spartanPlayer, hackType) < VL.m()) {
            FileLogs.a(player, (b2 ? "(False Positive) " : "") + "[Spartan " + version + "-" + IDs.spigot() + "/" + IDs.nonce() + "] " + player.getName() + " failed " + hackType.toString() + " (VL: " + a + ") [(Version: " + replace + "), (C-V: " + Config.c(hackType) + ") (Silent: " + Config.isSilent(hackType, player.getWorld().getName()) + "), (Ping: " + LatencyUtils.c(player) + "ms), (TPS: " + str + "), (Plib: " + Register.arePlibPacketsEnabled() + "), (Async: " + b3 + "), (Online: " + NPC.a().length + "), (" + s + ")]", true, null, hackType, b2, s);
        }
        if (((a > 0 && d > 0 && a % d == 0) || (a == 0 && d == 1) || (d < 0 && a >= Math.abs(d))) && (Settings.canDo("output_silent_check_verbose") || Config.b(spartanPlayer, hackType))) {
            final String a2 = ConfigUtils.a((OfflinePlayer)player, ((b2 ? "§4(False Positive)§f " : "") + Language.getMessage("verbose_message")).replace((CharSequence)"{info}", (CharSequence)s), hackType);
            final boolean canDo = Settings.canDo("public_verbose");
            final Player[] a3 = VerboseNotifications.a();
            try {
                if (canDo) {
                    final Player[] array = a3;
                    for (int length = array.length, i = 0; i < length; ++i) {
                        ClickableMessage.a(array[i], a2, "Teleport to " + player.getName(), "/tp " + player.getName());
                    }
                }
                else if (VerboseNotifications.d(player)) {
                    ClickableMessage.a(player, a2, "Teleport to " + player.getName(), "/tp " + player.getName());
                }
            }
            catch (Exception ex) {
                if (canDo) {
                    final Player[] array2 = a3;
                    for (int length2 = array2.length, j = 0; j < length2; ++j) {
                        array2[j].sendMessage(a2);
                    }
                }
                else if (VerboseNotifications.d(player)) {
                    player.sendMessage(a2);
                }
            }
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final String replacement, final String replacement2) {
        VerboseNotifications.j();
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        Enums.HackType value = null;
        try {
            value = Enums.HackType.valueOf(replacement);
        }
        catch (Exception ex) {}
        final String a = ConfigUtils.a(player, Language.getMessage("verbose_message").replace("{info}", replacement2).replace("{detection}", (value == null) ? replacement : Config.a(value)).replace("{detection:real}", replacement).replace("{silent:detection}", (value == null) ? "Unknown" : String.valueOf(Config.isSilent(value, player.getWorld().getName()))).replace((CharSequence)"{vls:detection}", (CharSequence)((value == null) ? "Unknown" : String.valueOf(VL.a(spartanPlayer, value)))), (Enums.HackType)null);
        final boolean canDo = Settings.canDo("public_verbose");
        final Player[] a2 = VerboseNotifications.a();
        try {
            if (canDo) {
                final Player[] array = a2;
                for (int length = array.length, i = 0; i < length; ++i) {
                    ClickableMessage.a(array[i], a, "Teleport to " + player.getName(), "/tp " + player.getName());
                }
            }
            else if (VerboseNotifications.d(player)) {
                ClickableMessage.a(player, a, "Teleport to " + player.getName(), "/tp " + player.getName());
            }
        }
        catch (Exception ex2) {
            if (canDo) {
                final Player[] array2 = a2;
                for (int length2 = array2.length, j = 0; j < length2; ++j) {
                    array2[j].sendMessage(a);
                }
            }
            else if (VerboseNotifications.d(player)) {
                player.sendMessage(a);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Material material, final String s, final int n) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        final int blockX = player.getLocation().getBlockX();
        final int blockY = player.getLocation().getBlockY();
        final int blockZ = player.getLocation().getBlockZ();
        final Player[] a = MiningNotifications.a();
        for (int length = a.length, i = 0; i < length; ++i) {
            a[i].sendMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " found " + ChatColor.DARK_RED + n + " " + s + " §7on §8" + blockX + "§7, §8" + blockY + "§7, §8" + blockZ);
        }
        FileLogs.a(player, player.getName() + " found " + n + " " + s + " on " + blockX + ", " + blockY + ", " + blockZ, true, material, null, false, null);
        XRay.a(spartanPlayer, material);
    }
}
