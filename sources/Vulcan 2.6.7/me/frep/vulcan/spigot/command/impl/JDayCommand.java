package me.frep.vulcan.spigot.command.impl;

import java.util.UUID;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bukkit.OfflinePlayer;
import me.frep.vulcan.spigot.jday.JDay;
import java.util.ArrayList;
import java.util.Calendar;
import me.frep.vulcan.spigot.jday.config.JDayConfig;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import me.frep.vulcan.spigot.command.CommandManager;

public class JDayCommand extends CommandManager implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (!sender.hasPermission("vulcan.jday")) {
            this.sendMessage(sender, Config.NO_PERMISSION);
            return true;
        }
        if (args.length == 0) {
            this.sendMessage(sender, Config.JDAY_COMMAND_SYNTAX);
            return true;
        }
        if (!args[0].equalsIgnoreCase("execute")) {
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length < 2) {
                    this.sendMessage(sender, Config.JDAY_COMMAND_SYNTAX);
                    return true;
                }
                final OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                final JDayConfig pending = new JDayConfig("jday-pending");
                final StringBuilder str = new StringBuilder();
                for (int i = 2; i < args.length; ++i) {
                    str.append(args[i]).append(" ");
                }
                String reason = str.toString();
                if (reason.isEmpty() || reason.equals(" ")) {
                    reason = "No Reason Specified.";
                }
                else {
                    reason = str.toString();
                }
                final int reasonLength = reason.length();
                reason = reason.substring(0, reasonLength - 1);
                pending.getConfigFile().set("PendingUsers." + target.getUniqueId() + ".Name", target.getName());
                pending.getConfigFile().set("PendingUsers." + target.getUniqueId() + ".UUID", target.getUniqueId().toString());
                pending.getConfigFile().set("PendingUsers." + target.getUniqueId() + ".Date", Calendar.getInstance().getTime().toString());
                pending.getConfigFile().set("PendingUsers." + target.getUniqueId() + ".Reason", reason);
                pending.getConfigFile().set("PendingUsers." + target.getUniqueId() + ".ExecutedBy", sender.getName());
                pending.saveConfigFile();
                final String name = args[1];
                this.sendMessage(sender, Config.JDAY_ADDED_TO_LIST.replaceAll("%player%", name));
            }
            else if (args[0].equalsIgnoreCase("list")) {
                final JDayConfig pending2 = new JDayConfig("jday-pending");
                if (pending2.getConfigFile() == null || pending2.getConfigFile().getConfigurationSection("PendingUsers") == null) {
                    this.sendMessage(sender, Config.JDAY_NO_PENDING_BANS);
                    return true;
                }
                final Set<String> queued = pending2.getConfigFile().getConfigurationSection("PendingUsers").getKeys(false);
                if (queued.isEmpty()) {
                    this.sendMessage(sender, Config.JDAY_NO_PENDING_BANS);
                    return true;
                }
                final List<String> names = new ArrayList<String>();
                for (final String string : queued) {
                    final String name = pending2.getConfigFile().getString("PendingUsers." + string + ".Name");
                    names.add(name);
                }
                this.sendMessage(sender, Config.JDAY_LIST_HEADER_FOOTER);
                for (final String name2 : names) {
                    this.sendMessage(sender, Config.JDAY_LIST_FORMAT.replaceAll("%name%", name2));
                }
                this.sendMessage(sender, Config.JDAY_LIST_HEADER_FOOTER);
            }
            else {
                if (!args[0].equalsIgnoreCase("remove")) {
                    this.sendMessage(sender, Config.JDAY_COMMAND_SYNTAX);
                    return true;
                }
                if (args.length == 1) {
                    this.sendMessage(sender, Config.JDAY_REMOVE_COMMAND_SYNTAX);
                    return true;
                }
                final String name3 = args[1];
                final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name3);
                final UUID uuid = offlinePlayer.getUniqueId();
                final JDayConfig pending3 = new JDayConfig("jday-pending");
                if (pending3.getConfigFile() == null || pending3.getConfigFile().getConfigurationSection("PendingUsers") == null) {
                    this.sendMessage(sender, Config.JDAY_NO_PENDING_BANS);
                    return true;
                }
                final Set<String> queued2 = pending3.getConfigFile().getConfigurationSection("PendingUsers").getKeys(false);
                final String string2 = uuid.toString();
                if (!queued2.contains(string2)) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                pending3.getConfigFile().set("PendingUsers." + string2 + ".Name", null);
                pending3.getConfigFile().set("PendingUsers." + string2 + ".UUID", null);
                pending3.getConfigFile().set("PendingUsers." + string2 + ".Reason", null);
                pending3.getConfigFile().set("PendingUsers." + string2 + ".ExecutedBy", null);
                pending3.getConfigFile().set("PendingUsers." + string2 + ".wasOnline", null);
                pending3.getConfigFile().set("PendingUsers." + string2 + ".Date", null);
                pending3.getConfigFile().getConfigurationSection("PendingUsers").set(string2, null);
                pending3.saveConfigFile();
                this.sendMessage(sender, Config.REMOVED_FROM_JDAY.replaceAll("%player%", name3));
            }
            return true;
        }
        if (JDay.getAmountToBan() == 0) {
            this.sendMessage(sender, Config.JDAY_NO_PENDING_BANS);
            return true;
        }
        JDay.executeBanWave();
        return true;
    }
}
