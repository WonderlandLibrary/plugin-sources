package me.vagdedes.spartan.features.a;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import me.vagdedes.spartan.features.syn.ViolationHistory;
import me.vagdedes.spartan.a.a.Config;
import java.util.ArrayList;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.k.f.ErrorUtils;
import org.bukkit.command.CommandSender;

public class ConfigurationDiagnostics
{
    private static final int max = 1200;
    private static int time;
    
    public ConfigurationDiagnostics() {
        super();
    }
    
    public static void run() {
        if (ConfigurationDiagnostics.time > 0) {
            --ConfigurationDiagnostics.time;
        }
    }
    
    public static void b(final CommandSender commandSender) {
        final String str = "[Configuration Diagnostics] ";
        final boolean b = commandSender != null;
        if (!b) {
            ErrorUtils.send("Running automated Configuration Diagnostics.");
        }
        else {
            ErrorUtils.send("Running manual Configuration Diagnostics. (" + commandSender.getName() + ")");
        }
        if (ConfigurationDiagnostics.time == 0) {
            ConfigurationDiagnostics.time = 1200;
            if (!SearchEngine.d()) {
                if (b) {
                    commandSender.sendMessage(Language.getMessage("disabled_log_saving"));
                }
            }
            else if (SearchEngine.e()) {
                if (b) {
                    commandSender.sendMessage(Language.getMessage("not_enough_saved_logs").replace((CharSequence)"{amount}", (CharSequence)String.valueOf(100)));
                }
            }
            else {
                final ViolationHistory a = SearchEngine.a();
                final ConcurrentHashMap<Enums.HackType, Integer> violations = a.getViolations();
                final ConcurrentHashMap<Enums.HackType, ArrayList<String>> days = a.getDays();
                boolean b2 = false;
                if (b) {
                    commandSender.sendMessage(ChatColor.DARK_GREEN + str.substring(0, str.length() - 1) + ":");
                }
                for (final Enums.HackType hackType : violations.keySet()) {
                    if (days.containsKey(hackType)) {
                        final double min = Math.min(5.0, Integer.valueOf(violations.get((Object)hackType)) / (double)((ArrayList<String>)days.get(hackType)).size() + (Config.isSilent(hackType, null) ? 1.0 : 2.0));
                        if (!Config.a(hackType, (int)Math.round(min))) {
                            continue;
                        }
                        b2 = true;
                        if (!b) {
                            continue;
                        }
                        commandSender.sendMessage(ChatColor.GREEN + "Changed " + hackType + "'s cancel-after-violation to " + min);
                    }
                }
                if (b) {
                    if (!b2) {
                        commandSender.sendMessage(ChatColor.GREEN + "No changes needed.");
                    }
                    commandSender.sendMessage("");
                }
            }
        }
        else if (b) {
            commandSender.sendMessage(ChatColor.RED + str + "Under cooldown. Please wait " + ConfigurationDiagnostics.time / 20 + " second(s).");
        }
    }
    
    static {
        ConfigurationDiagnostics.time = 0;
    }
}
