package me.frep.vulcan.spigot.command.impl;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.reset.ResetManager;
import org.bukkit.util.Vector;
import java.text.DecimalFormat;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.util.ColorUtil;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.check.AbstractCheck;
import me.frep.vulcan.api.check.Check;
import me.frep.vulcan.spigot.gui.impl.MainMenu;
import me.frep.vulcan.spigot.util.ServerUtil;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.Map;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.UUID;
import java.util.HashMap;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import me.frep.vulcan.spigot.command.CommandManager;

public class VulcanCommand extends CommandManager implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 0) {
            if (!sender.hasPermission("vulcan.help")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            Config.HELP_COMMAND.forEach(message -> this.sendMessage(sender, message.replaceAll("%version%", Vulcan.INSTANCE.getPlugin().getDescription().getVersion())));
        }
        else if (args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("vulcan.help")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            Config.HELP_COMMAND.forEach(message -> this.sendMessage(sender, message.replaceAll("%version%", Vulcan.INSTANCE.getPlugin().getDescription().getVersion())));
        }
        else if (args[0].equalsIgnoreCase("top")) {
            if (!sender.hasPermission("vulcan.top")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            final Map<UUID, Integer> topTotalViolations = new HashMap<UUID, Integer>();
            final Map<UUID, Integer> topCombatViolations = new HashMap<UUID, Integer>();
            final Map<UUID, Integer> topMovementViolations = new HashMap<UUID, Integer>();
            final Map<UUID, Integer> topPlayerViolations = new HashMap<UUID, Integer>();
            for (final PlayerData data : Vulcan.INSTANCE.getPlayerDataManager().getAllData()) {
                topTotalViolations.put(data.getPlayer().getUniqueId(), data.getTotalViolations());
                topCombatViolations.put(data.getPlayer().getUniqueId(), data.getCombatViolations());
                topMovementViolations.put(data.getPlayer().getUniqueId(), data.getMovementViolations());
                topPlayerViolations.put(data.getPlayer().getUniqueId(), data.getPlayerViolations());
            }
            Map<UUID, Integer> sortedTotal = topTotalViolations.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new));
            Map<UUID, Integer> sortedCombat = topCombatViolations.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new));
            Map<UUID, Integer> sortedMovement = topMovementViolations.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new));
            Map<UUID, Integer> sortedPlayer = topPlayerViolations.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new));
            final Object[] totalViolationsArray = sortedTotal.keySet().toArray();
            final Object[] combatViolationsArray = sortedCombat.keySet().toArray();
            final Object[] movementViolationsArray = sortedMovement.keySet().toArray();
            final Object[] playerViolationsArray = sortedPlayer.keySet().toArray();
            UUID total1Uuid = null;
            if (totalViolationsArray.length > 0 && totalViolationsArray[0] != null && totalViolationsArray[0] instanceof UUID) {
                total1Uuid = (UUID)totalViolationsArray[0];
            }
            UUID total2Uuid = null;
            if (totalViolationsArray.length > 1 && totalViolationsArray[1] != null && totalViolationsArray[1] instanceof UUID) {
                total2Uuid = (UUID)totalViolationsArray[1];
            }
            UUID total3Uuid = null;
            if (totalViolationsArray.length > 2 && totalViolationsArray[2] != null && totalViolationsArray[2] instanceof UUID) {
                total3Uuid = (UUID)totalViolationsArray[2];
            }
            UUID total4Uuid = null;
            if (totalViolationsArray.length > 3 && totalViolationsArray[3] != null && totalViolationsArray[3] instanceof UUID) {
                total4Uuid = (UUID)totalViolationsArray[3];
            }
            UUID total5Uuid = null;
            if (totalViolationsArray.length > 4 && totalViolationsArray[4] != null && totalViolationsArray[4] instanceof UUID) {
                total5Uuid = (UUID)totalViolationsArray[4];
            }
            Player total1Player = null;
            if (total1Uuid != null) {
                total1Player = Bukkit.getPlayer(total1Uuid);
            }
            Player total2Player = null;
            if (total2Uuid != null) {
                total2Player = Bukkit.getPlayer(total2Uuid);
            }
            Player total3Player = null;
            if (total3Uuid != null) {
                total3Player = Bukkit.getPlayer(total3Uuid);
            }
            Player total4Player = null;
            if (total4Uuid != null) {
                total4Player = Bukkit.getPlayer(total4Uuid);
            }
            Player total5Player = null;
            if (total5Uuid != null) {
                total5Player = Bukkit.getPlayer(total5Uuid);
            }
            UUID combat1Uuid = null;
            if (combatViolationsArray.length > 0 && combatViolationsArray[0] != null && combatViolationsArray[0] instanceof UUID) {
                combat1Uuid = (UUID)combatViolationsArray[0];
            }
            UUID combat2Uuid = null;
            if (combatViolationsArray.length > 1 && combatViolationsArray[1] != null && combatViolationsArray[1] instanceof UUID) {
                combat2Uuid = (UUID)combatViolationsArray[1];
            }
            UUID combat3Uuid = null;
            if (combatViolationsArray.length > 2 && combatViolationsArray[2] != null && combatViolationsArray[2] instanceof UUID) {
                combat3Uuid = (UUID)combatViolationsArray[2];
            }
            UUID combat4Uuid = null;
            if (combatViolationsArray.length > 3 && combatViolationsArray[3] != null && combatViolationsArray[3] instanceof UUID) {
                combat4Uuid = (UUID)combatViolationsArray[3];
            }
            UUID combat5Uuid = null;
            if (combatViolationsArray.length > 4 && combatViolationsArray[4] != null && combatViolationsArray[4] instanceof UUID) {
                combat5Uuid = (UUID)combatViolationsArray[4];
            }
            Player combat1Player = null;
            if (combat1Uuid != null) {
                combat1Player = Bukkit.getPlayer(combat1Uuid);
            }
            Player combat2Player = null;
            if (combat2Uuid != null) {
                combat2Player = Bukkit.getPlayer(combat2Uuid);
            }
            Player combat3Player = null;
            if (combat3Uuid != null) {
                combat3Player = Bukkit.getPlayer(combat3Uuid);
            }
            Player combat4Player = null;
            if (combat4Uuid != null) {
                combat4Player = Bukkit.getPlayer(combat4Uuid);
            }
            Player combat5Player = null;
            if (combat5Uuid != null) {
                combat5Player = Bukkit.getPlayer(combat5Uuid);
            }
            UUID movement1Uuid = null;
            if (movementViolationsArray.length > 0 && movementViolationsArray[0] != null && movementViolationsArray[0] instanceof UUID) {
                movement1Uuid = (UUID)movementViolationsArray[0];
            }
            UUID movement2Uuid = null;
            if (movementViolationsArray.length > 1 && movementViolationsArray[1] != null && movementViolationsArray[1] instanceof UUID) {
                movement2Uuid = (UUID)movementViolationsArray[1];
            }
            UUID movement3Uuid = null;
            if (movementViolationsArray.length > 2 && movementViolationsArray[2] != null && movementViolationsArray[2] instanceof UUID) {
                movement3Uuid = (UUID)movementViolationsArray[2];
            }
            UUID movement4Uuid = null;
            if (movementViolationsArray.length > 3 && movementViolationsArray[3] != null && movementViolationsArray[3] instanceof UUID) {
                movement4Uuid = (UUID)movementViolationsArray[3];
            }
            UUID movement5Uuid = null;
            if (movementViolationsArray.length > 4 && movementViolationsArray[4] != null && movementViolationsArray[4] instanceof UUID) {
                movement5Uuid = (UUID)movementViolationsArray[4];
            }
            Player movement1Player = null;
            if (movement1Uuid != null) {
                movement1Player = Bukkit.getPlayer(movement1Uuid);
            }
            Player movement2Player = null;
            if (movement2Uuid != null) {
                movement2Player = Bukkit.getPlayer(movement2Uuid);
            }
            Player movement3Player = null;
            if (movement3Uuid != null) {
                movement3Player = Bukkit.getPlayer(movement3Uuid);
            }
            Player movement4Player = null;
            if (movement4Uuid != null) {
                movement4Player = Bukkit.getPlayer(movement4Uuid);
            }
            Player movement5Player = null;
            if (movement5Uuid != null) {
                movement5Player = Bukkit.getPlayer(movement5Uuid);
            }
            UUID player1Uuid = null;
            if (playerViolationsArray.length > 0 && playerViolationsArray[0] != null && playerViolationsArray[0] instanceof UUID) {
                player1Uuid = (UUID)playerViolationsArray[0];
            }
            UUID player2Uuid = null;
            if (playerViolationsArray.length > 1 && playerViolationsArray[1] != null && playerViolationsArray[1] instanceof UUID) {
                player2Uuid = (UUID)playerViolationsArray[1];
            }
            UUID player3Uuid = null;
            if (playerViolationsArray.length > 2 && playerViolationsArray[2] != null && playerViolationsArray[2] instanceof UUID) {
                player3Uuid = (UUID)playerViolationsArray[2];
            }
            UUID player4Uuid = null;
            if (playerViolationsArray.length > 3 && playerViolationsArray[3] != null && playerViolationsArray[3] instanceof UUID) {
                player4Uuid = (UUID)playerViolationsArray[3];
            }
            UUID player5Uuid = null;
            if (playerViolationsArray.length > 4 && playerViolationsArray[4] != null && playerViolationsArray[4] instanceof UUID) {
                player5Uuid = (UUID)playerViolationsArray[4];
            }
            Player player1Player = null;
            if (player1Uuid != null) {
                player1Player = Bukkit.getPlayer(player1Uuid);
            }
            Player player2Player = null;
            if (player2Uuid != null) {
                player2Player = Bukkit.getPlayer(player2Uuid);
            }
            Player player3Player = null;
            if (player3Uuid != null) {
                player3Player = Bukkit.getPlayer(player3Uuid);
            }
            Player player4Player = null;
            if (player4Uuid != null) {
                player4Player = Bukkit.getPlayer(player4Uuid);
            }
            Player player5Player = null;
            if (player5Uuid != null) {
                player5Player = Bukkit.getPlayer(player5Uuid);
            }
            final String total1PlayerName = (total1Player == null) ? Config.TOP_COMMAND_UNRESOLVED : total1Player.getName();
            final String total2PlayerName = (total2Player == null) ? Config.TOP_COMMAND_UNRESOLVED : total2Player.getName();
            final String total3PlayerName = (total3Player == null) ? Config.TOP_COMMAND_UNRESOLVED : total3Player.getName();
            final String total4PlayerName = (total4Player == null) ? Config.TOP_COMMAND_UNRESOLVED : total4Player.getName();
            final String total5PlayerName = (total5Player == null) ? Config.TOP_COMMAND_UNRESOLVED : total5Player.getName();
            final String total1Vl = (total1Uuid == null) ? "0" : sortedTotal.get(total1Uuid).toString();
            final String total2Vl = (total2Uuid == null) ? "0" : sortedTotal.get(total2Uuid).toString();
            final String total3Vl = (total3Uuid == null) ? "0" : sortedTotal.get(total3Uuid).toString();
            final String total4Vl = (total4Uuid == null) ? "0" : sortedTotal.get(total4Uuid).toString();
            final String total5Vl = (total5Uuid == null) ? "0" : sortedTotal.get(total5Uuid).toString();
            final String combat1PlayerName = (combat1Player == null) ? Config.TOP_COMMAND_UNRESOLVED : combat1Player.getName();
            final String combat2PlayerName = (combat2Player == null) ? Config.TOP_COMMAND_UNRESOLVED : combat2Player.getName();
            final String combat3PlayerName = (combat3Player == null) ? Config.TOP_COMMAND_UNRESOLVED : combat3Player.getName();
            final String combat4PlayerName = (combat4Player == null) ? Config.TOP_COMMAND_UNRESOLVED : combat4Player.getName();
            final String combat5PlayerName = (combat5Player == null) ? Config.TOP_COMMAND_UNRESOLVED : combat5Player.getName();
            final String combat1Vl = (combat1Uuid == null) ? "0" : sortedCombat.get(combat1Uuid).toString();
            final String combat2Vl = (combat2Uuid == null) ? "0" : sortedCombat.get(combat2Uuid).toString();
            final String combat3Vl = (combat3Uuid == null) ? "0" : sortedCombat.get(combat3Uuid).toString();
            final String combat4Vl = (combat4Uuid == null) ? "0" : sortedCombat.get(combat4Uuid).toString();
            final String combat5Vl = (combat5Uuid == null) ? "0" : sortedCombat.get(combat5Uuid).toString();
            final String movement1PlayerName = (movement1Player == null) ? Config.TOP_COMMAND_UNRESOLVED : movement1Player.getName();
            final String movement2PlayerName = (movement2Player == null) ? Config.TOP_COMMAND_UNRESOLVED : movement2Player.getName();
            final String movement3PlayerName = (movement3Player == null) ? Config.TOP_COMMAND_UNRESOLVED : movement3Player.getName();
            final String movement4PlayerName = (movement4Player == null) ? Config.TOP_COMMAND_UNRESOLVED : movement4Player.getName();
            final String movement5PlayerName = (movement5Player == null) ? Config.TOP_COMMAND_UNRESOLVED : movement5Player.getName();
            final String movement1Vl = (movement1Uuid == null) ? "0" : sortedMovement.get(movement1Uuid).toString();
            final String movement2Vl = (movement2Uuid == null) ? "0" : sortedMovement.get(movement2Uuid).toString();
            final String movement3Vl = (movement3Uuid == null) ? "0" : sortedMovement.get(movement3Uuid).toString();
            final String movement4Vl = (movement4Uuid == null) ? "0" : sortedMovement.get(movement4Uuid).toString();
            final String movement5Vl = (movement5Uuid == null) ? "0" : sortedMovement.get(movement5Uuid).toString();
            final String player1PlayerName = (player1Player == null) ? Config.TOP_COMMAND_UNRESOLVED : player1Player.getName();
            final String player2PlayerName = (player2Player == null) ? Config.TOP_COMMAND_UNRESOLVED : player2Player.getName();
            final String player3PlayerName = (player3Player == null) ? Config.TOP_COMMAND_UNRESOLVED : player3Player.getName();
            final String player4PlayerName = (player4Player == null) ? Config.TOP_COMMAND_UNRESOLVED : player4Player.getName();
            final String player5PlayerName = (player5Player == null) ? Config.TOP_COMMAND_UNRESOLVED : player5Player.getName();
            final String player1Vl = (player1Uuid == null) ? "0" : sortedPlayer.get(player1Uuid).toString();
            final String player2Vl = (player2Uuid == null) ? "0" : sortedPlayer.get(player2Uuid).toString();
            final String player3Vl = (player3Uuid == null) ? "0" : sortedPlayer.get(player3Uuid).toString();
            final String player4Vl = (player4Uuid == null) ? "0" : sortedPlayer.get(player4Uuid).toString();
            final String player5Vl = (player5Uuid == null) ? "0" : sortedPlayer.get(player5Uuid).toString();
            Config.TOP_COMMAND.forEach(message -> this.sendMessage(sender, message.replaceAll("%total-1-name%", total1PlayerName).replaceAll("%total-2-name%", total2PlayerName).replaceAll("%total-3-name%", total3PlayerName).replaceAll("%total-4-name%", total4PlayerName).replaceAll("%total-5-name%", total5PlayerName).replaceAll("%total-1-violations%", total1Vl).replaceAll("%total-2-violations%", total2Vl).replaceAll("%total-3-violations%", total3Vl).replaceAll("%total-4-violations%", total4Vl).replaceAll("%total-5-violations%", total5Vl).replaceAll("%combat-1-name%", combat1PlayerName).replaceAll("%combat-2-name%", combat2PlayerName).replaceAll("%combat-3-name%", combat3PlayerName).replaceAll("%combat-4-name%", combat4PlayerName).replaceAll("%combat-5-name%", combat5PlayerName).replaceAll("%combat-1-violations%", combat1Vl).replaceAll("%combat-2-violations%", combat2Vl).replaceAll("%combat-3-violations%", combat3Vl).replaceAll("%combat-4-violations%", combat4Vl).replaceAll("%combat-5-violations%", combat5Vl).replaceAll("%movement-1-name%", movement1PlayerName).replaceAll("%movement-2-name%", movement2PlayerName).replaceAll("%movement-3-name%", movement3PlayerName).replaceAll("%movement-4-name%", movement4PlayerName).replaceAll("%movement-5-name%", movement5PlayerName).replaceAll("%movement-1-violations%", movement1Vl).replaceAll("%movement-2-violations%", movement2Vl).replaceAll("%movement-3-violations%", movement3Vl).replaceAll("%movement-4-violations%", movement4Vl).replaceAll("%movement-5-violations%", movement5Vl).replaceAll("%player-1-name%", player1PlayerName).replaceAll("%player-2-name%", player2PlayerName).replaceAll("%player-3-name%", player3PlayerName).replaceAll("%player-4-name%", player4PlayerName).replaceAll("%player-5-name%", player5PlayerName).replaceAll("%player-1-violations%", player1Vl).replaceAll("%player-2-violations%", player2Vl).replaceAll("%player-3-violations%", player3Vl).replaceAll("%player-4-violations%", player4Vl).replaceAll("%player-5-violations%", player5Vl)));
        }
        else if (args[0].equalsIgnoreCase("exempts")) {
            if (!sender.hasPermission("vulcan.exempts")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, "must include player parameter");
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final PlayerData data2 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
                if (data2 == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final List<String> exempts = new ArrayList<String>();
                for (final ExemptType exemptType : ExemptType.values()) {
                    if (data2.getExemptProcessor().isExempt(exemptType)) {
                        exempts.add(exemptType.toString());
                    }
                }
                this.sendLine(sender);
                this.sendMessage(sender, "&cExemptions:");
                exempts.forEach(exemption -> this.sendMessage(sender, " &8- &7" + exemption));
                this.sendMessage(sender, "&cBypass Permission: " + player.hasPermission("vulcan.bypass.speeda"));
                this.sendLine(sender);
            }
        }
        else if (args[0].equalsIgnoreCase("menu") || args[0].equalsIgnoreCase("gui")) {
            if (!(sender instanceof Player)) {
                this.sendMessage(sender, Config.CANT_EXECUTE_FROM_CONSOLE);
                return true;
            }
            if (!sender.hasPermission("vulcan.gui") && !sender.hasPermission("vulcan.menu")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (ServerUtil.isLowerThan1_8()) {
                this.sendMessage(sender, "&cGUI unavailable on 1.7 servers!");
            }
            else {
                MainMenu.getInstance().open((Player)sender);
            }
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("vulcan.reload")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            Vulcan.INSTANCE.reload();
            this.sendMessage(sender, Config.RELOAD_SUCCESS);
        }
        else if (args[0].equalsIgnoreCase("testalert")) {
            if (!sender.hasPermission("vulcan.testalert")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (!(sender instanceof Player)) {
                this.sendMessage(sender, Config.CANT_EXECUTE_FROM_CONSOLE);
                return true;
            }
            final Player player2 = (Player)sender;
            final PlayerData data3 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player2);
            if (data3 == null) {
                this.sendMessage(sender, Config.INVALID_TARGET);
                return true;
            }
            if (sender.hasPermission("vulcan.bypass.speeda")) {
                this.sendLine(sender);
                this.sendMessage(sender, "&4&lNOTE! &cThis command emulates a real alert message, so if you are OP or have the bypass permission it will not work! If you use LuckPerms, you can negate the bypass permission by doing:");
                this.sendMessage(sender, " &8- &c/lp user " + sender.getName() + " permission set vulcan.bypass.* false");
                this.sendLine(sender);
                return true;
            }
            for (final Check check : data3.getChecks()) {
                if (check.getDisplayName().toLowerCase().equals("speed") && Character.toString(check.getDisplayType()).toLowerCase().equals("a")) {
                    Bukkit.getScheduler().runTaskAsynchronously(Vulcan.INSTANCE.getPlugin(), () -> {
                        Vulcan.INSTANCE.getAlertManager().handleAlert((AbstractCheck)check, data3, "Test Alert!");
                        Vulcan.INSTANCE.getAlertManager().handleAlert((AbstractCheck)check, data3, "Test Alert!");
                        Vulcan.INSTANCE.getAlertManager().handleAlert((AbstractCheck)check, data3, "Test Alert!");
                        return;
                    });
                }
            }
            this.sendMessage(sender, Config.SENT_TEST_ALERT);
            return true;
        }
        else if (args[0].equalsIgnoreCase("clickalert")) {
            if (!sender.hasPermission("vulcan.clickalert")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (!(sender instanceof Player)) {
                this.sendMessage(sender, Config.CANT_EXECUTE_FROM_CONSOLE);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, "&cInvalid syntax!");
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                }
                else {
                    final Player staff = (Player)sender;
                    Config.CLICKALERT_COMMAND_COMMANDS.forEach(message -> staff.performCommand(message.replaceAll("%player%", player.getName())));
                }
            }
        }
        else if (args[0].equalsIgnoreCase("disablecheck")) {
            if (!sender.hasPermission("vulcan.disablecheck")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.DISABLE_CHECK_COMMAND_SYNTAX);
            }
            else {
                final String checkName = args[1];
                if (!Config.ENABLED_CHECKS.containsKey(checkName) || !Config.ENABLED_CHECKS.get(checkName)) {
                    this.sendMessage(sender, Config.INVALID_CHECK);
                    return true;
                }
                Config.ENABLED_CHECKS.remove(checkName);
                Config.ENABLED_CHECKS.put(checkName, false);
                this.sendMessage(sender, Config.DISABLED_CHECK.replaceAll("%check%", checkName));
            }
        }
        else if (args[0].equalsIgnoreCase("freeze")) {
            if (!sender.hasPermission("vulcan.freeze")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.FREEZE_COMMAND_SYNTAX);
                return true;
            }
            final String name = args[1];
            final Player player = Bukkit.getPlayer(name);
            if (player == null) {
                this.sendMessage(sender, Config.INVALID_TARGET);
                return true;
            }
            final PlayerData data2 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
            if (data2 == null) {
                this.sendMessage(sender, Config.INVALID_TARGET);
                return true;
            }
            final boolean frozen = data2.getPositionProcessor().isFrozen();
            if (frozen) {
                data2.getPositionProcessor().setFrozen(false);
                this.sendMessage(sender, Config.UNFROZE.replaceAll("%player%", player.getName()));
                Vulcan.INSTANCE.getAlertManager().sendMessage(ColorUtil.translate(Config.STAFF_UNFROZE_BROADCAST.replaceAll("%player%", player.getName()).replaceAll("%staff%", sender.getName())));
            }
            else {
                data2.getPositionProcessor().setFrozen(true);
                this.sendMessage(sender, Config.FROZE.replaceAll("%player%", player.getName()));
                Vulcan.INSTANCE.getAlertManager().sendMessage(ColorUtil.translate(Config.STAFF_FROZE_BROADCAST.replaceAll("%player%", player.getName()).replaceAll("%staff%", sender.getName())));
            }
        }
        else if (args[0].equalsIgnoreCase("ban")) {
            if (!sender.hasPermission("vulcan.ban")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.BAN_COMMAND_SYNTAX);
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                for (final String string : Config.BAN_COMMANDS) {
                    if (string.startsWith("Bungee:") || string.startsWith("bungee")) {
                        this.sendPluginMessage(player, ColorUtil.translate(string.replaceAll("Bungee:", "").replaceAll("bungee:", "")).replaceAll("%player%", name));
                    }
                    else {
                        ServerUtil.dispatchCommand(ColorUtil.translate(string.replaceAll("%player%", name)));
                    }
                }
                Config.BAN_COMMAND_BROADCAST.forEach(message -> ServerUtil.broadcast(message.replaceAll("%player%", name)));
            }
        }
        else if (args[0].equalsIgnoreCase("shuffle")) {
            if (sender.hasPermission("vulcan.shuffle")) {
                if (args.length == 1) {
                    this.sendMessage(sender, Config.SHUFFLE_COMMAND_SYNTAX);
                }
                else {
                    final String name = args[1];
                    final Player player = Bukkit.getPlayer(name);
                    if (player == null) {
                        this.sendMessage(sender, Config.INVALID_TARGET);
                        return true;
                    }
                    PlayerUtil.shuffleHotbar(player);
                    this.sendMessage(sender, Config.SHUFFLED_HOTBAR.replaceAll("%player%", name));
                }
            }
        }
        else if (args[0].equalsIgnoreCase("rotate")) {
            if (sender.hasPermission("vulcan.rotate")) {
                if (args.length == 1) {
                    this.sendMessage(sender, Config.ROTATE_COMMAND_SYNTAX);
                }
                else {
                    final String name = args[1];
                    final Player player = Bukkit.getPlayer(name);
                    if (player == null) {
                        this.sendMessage(sender, Config.INVALID_TARGET);
                        return true;
                    }
                    PlayerUtil.rotateRandomly(player);
                    this.sendMessage(sender, Config.RANDOMLY_ROTATED.replaceAll("%player%", name));
                }
            }
        }
        else if (args[0].equalsIgnoreCase("violations")) {
            if (!sender.hasPermission("vulcan.violations")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.VIOLATIONS_COMMAND_SYNTAX);
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final PlayerData data2 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
                if (data2 == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final int totalViolations = data2.getTotalViolations();
                if (totalViolations < 1) {
                    this.sendMessage(sender, Config.NO_LOGS);
                    return true;
                }
                this.sendLine(sender);
                this.sendMessage(sender, "&7Total Violations for &c" + name + " &7(&c" + totalViolations + "&7):");
                for (final AbstractCheck check2 : data2.getChecks()) {
                    if (check2.getVl() > 0) {
                        this.sendMessage(sender, " &8- &b" + check2.getCheckInfo().name() + " &7(&bType " + Character.toUpperCase(check2.getType()) + "&7) VL: " + check2.getVl());
                    }
                }
                this.sendLine(sender);
            }
        }
        else if (args[0].equalsIgnoreCase("cps")) {
            if (!sender.hasPermission("vulcan.cps")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.CPS_COMMAND_SYNTAX);
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final PlayerData data2 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
                if (data2 == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final String cps = new DecimalFormat("##.##").format(data2.getClickProcessor().getCps());
                this.sendMessage(sender, Config.CPS_COMMAND.replaceAll("%cps%", cps).replace("%player%", name));
            }
        }
        else if (args[0].equalsIgnoreCase("kb") || args[0].equalsIgnoreCase("knockback")) {
            if (!sender.hasPermission("vulcan.kb") && !sender.hasPermission("vulcan.knockback")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.KB_COMMAND_SYNTAX);
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final Vector vector = new Vector(0.75, 0.5, 0.75);
                player.setVelocity(vector);
                this.sendMessage(sender, Config.KB_TEST_SUCCESS.replaceAll("%player%", name));
            }
        }
        else if (args[0].equalsIgnoreCase("checks")) {
            if (!sender.hasPermission("vulcan.checks")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            this.sendLine(sender);
            this.sendMessage(sender, "&7Total Enabled Checks: &c" + Config.ENABLED_CHECKS.size());
            final Set<String> checks = Config.ENABLED_CHECKS.keySet();
            final List<String> checkList = new ArrayList<String>();
            checks.forEach(check -> checkList.add(check));
            Collections.sort(checkList);
            for (final String string : checkList) {
                if (Config.ENABLED_CHECKS.get(string)) {
                    this.sendMessage(sender, " &8- &7" + string);
                }
            }
            this.sendLine(sender);
        }
        else if (args[0].equalsIgnoreCase("reset")) {
            if (!sender.hasPermission("vulcan.reset")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                ResetManager.reset();
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final PlayerData data2 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
                if (data2 == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                for (final Check check3 : data2.getChecks()) {
                    check3.setVl(0);
                    check3.setBuffer(0.0);
                }
                data2.setMovementViolations(0);
                data2.setTotalViolations(0);                data2.setCombatViolations(0);
                data2.setAutoClickerViolations(0);
                data2.setScaffoldViolations(0);
                data2.setPlayerViolations(0);
                data2.setTimerViolations(0);
                this.sendMessage(sender, Config.VIOLATIONS_RESET.replaceAll("%player%", name));
            }
        }
        else if (args[0].equalsIgnoreCase("license")) {
            if (sender.getName().equalsIgnoreCase("frep") || sender.getName().equals("bloaaie")) {
                this.sendLine(sender);
                this.sendMessage(sender, "&7This copy is licensed to:");
                this.sendMessage(sender, " &8- &7ID: &c" + Vulcan.INSTANCE.getSpigot());
                this.sendMessage(sender, " &8- &7Nonce: &c" + Vulcan.INSTANCE.getNonce());
                this.sendMessage(sender, " &8- &7Spigot Profile: &chttps://www.spigotmc.org/members/" + Vulcan.INSTANCE.getSpigot() + "/");
                this.sendLine(sender);
            }
            else {
                this.sendMessage(sender, "&cUnknown command!");
            }
        }
        else if (args[0].equalsIgnoreCase("connection")) {
            if (!sender.hasPermission("vulcan.connection")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.CONNECTION_COMMAND_SYNTAX);
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final PlayerData data2 = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
                if (data2 == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final long now = System.currentTimeMillis();
                final String transactionPing = Long.toString(data2.getConnectionProcessor().getTransactionPing());
                final String keepAlivePing = Integer.toString(data2.getConnectionProcessor().getKeepAlivePing());
                final String lastRepliedTransaction = Long.toString(now - data2.getConnectionProcessor().getLastRepliedTransaction());
                final String flyingDelay = Long.toString(data2.getConnectionProcessor().getFlyingDelay());
                final String queuedTransactions = Integer.toString(data2.getPendingTransactions());
                Config.CONNECTION_COMMAND.forEach(message -> this.sendMessage(sender, message.replaceAll("%player%", player.getName()).replaceAll("%transaction-ping%", transactionPing).replaceAll("%keepalive-ping%", keepAlivePing).replaceAll("%queued-transactions%", queuedTransactions).replaceAll("%last-replied-transaction%", lastRepliedTransaction).replaceAll("%flying-delay%", flyingDelay)));
            }
        }
        else if (args[0].equalsIgnoreCase("dump")) {
            if (!sender.hasPermission("vulcan.dump")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            this.sendLine(sender);
            this.sendMessage(sender, "&7Vulcan Version: &c" + Vulcan.INSTANCE.getPlugin().getDescription().getVersion());
            this.sendMessage(sender, "&7Server Version: &c" + ServerUtil.getServerVersion());
            this.sendMessage(sender, "&7Server Software: &c" + Bukkit.getVersion());
            this.sendMessage(sender, "&7TPS: &c" + ServerUtil.getTPS());
            this.sendMessage(sender, "&7ID: &c" + Vulcan.INSTANCE.getSpigot());
            this.sendMessage(sender, "&7Nonce: &c" + Vulcan.INSTANCE.getNonce());
            this.sendLine(sender);
        }
        else {
            if (!args[0].equalsIgnoreCase("profile")) {
                this.sendMessage(sender, Config.UNKNOWN_COMMAND);
                return true;
            }
            if (!sender.hasPermission("vulcan.profile")) {
                this.sendMessage(sender, Config.NO_PERMISSION);
                return true;
            }
            if (args.length == 1) {
                this.sendMessage(sender, Config.PROFILE_COMMAND_SYNTAX);
            }
            else {
                final String name = args[1];
                final Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    this.sendMessage(sender, Config.INVALID_TARGET);
                    return true;
                }
                final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
                if (data != null) {
                    if (ServerUtil.isHigherThan1_13()) {
                        final String clientBrand = (data.getClientBrand() == null) ? "Unresolved" : data.getClientBrand();
                        final String sensitivity = Integer.toString(data.getRotationProcessor().getSensitivity());
                        final String totalViolations = Integer.toString(data.getTotalViolations());
                        final String combatViolations = Integer.toString(data.getCombatViolations());
                        final String movementViolations = Integer.toString(data.getMovementViolations());
                        final String playerViolations = Integer.toString(data.getPlayerViolations());
                        final String clientVersion = PlayerUtil.getClientVersionToString(data.getPlayer());
                        final String cps = MathUtil.trim(data.getClickProcessor().getCps());
                        final String uuid = data.getPlayer().getUniqueId().toString();
                        final String nearbyBlocks = (data.getPositionProcessor().getNearbyBlocksModern() == null) ? "Empty" : data.getPositionProcessor().getNearbyBlocksModern().toString();
                        final String nearbyEntities = (data.getPositionProcessor().getNearbyEntities() == null) ? "Empty" : data.getPositionProcessor().getNearbyEntities().toString();
                        Config.PROFILE_COMMAND.forEach(message -> this.sendMessage(sender, message.replaceAll("%player%", name).replaceAll("%client-brand%", clientBrand).replaceAll("%cps%", cps).replaceAll("%uuid%", uuid).replaceAll("%x%", Integer.toString(data.getPositionProcessor().getBlockX())).replaceAll("%y%", Integer.toString(data.getPositionProcessor().getBlockY())).replaceAll("%z%", Integer.toString(data.getPositionProcessor().getBlockZ())).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%potion-effects%", data.getPlayer().getActivePotionEffects().toString()).replaceAll("%nearby-blocks%", nearbyBlocks).replaceAll("%nearby-entities%", nearbyEntities).replaceAll("%client-version%", clientVersion).replaceAll("%total-violations%", totalViolations).replaceAll("%combat-violations%", combatViolations).replaceAll("%movement-violations%", movementViolations).replaceAll("%player-violations%", playerViolations).replaceAll("%sensitivity%", sensitivity)));
                    }
                    else {
                        final String clientBrand = (data.getClientBrand() == null) ? "Unresolved" : data.getClientBrand();
                        final String sensitivity = Integer.toString(data.getRotationProcessor().getSensitivity());
                        final String totalViolations = Integer.toString(data.getTotalViolations());
                        final String combatViolations = Integer.toString(data.getCombatViolations());
                        final String movementViolations = Integer.toString(data.getMovementViolations());
                        final String playerViolations = Integer.toString(data.getPlayerViolations());
                        final String clientVersion = PlayerUtil.getClientVersionToString(data.getPlayer());
                        final String cps = MathUtil.trim(data.getClickProcessor().getCps());
                        final String uuid = data.getPlayer().getUniqueId().toString();
                        final String nearbyBlocks = (data.getPositionProcessor().getNearbyBlocks() == null) ? "Empty" : data.getPositionProcessor().getNearbyBlocks().toString();
                        final String nearbyEntities = (data.getPositionProcessor().getNearbyEntities() == null) ? "Empty" : data.getPositionProcessor().getNearbyEntities().toString();
                        Config.PROFILE_COMMAND.forEach(message -> this.sendMessage(sender, message.replaceAll("%player%", name).replaceAll("%client-brand%", clientBrand).replaceAll("%cps%", cps).replaceAll("%uuid%", uuid).replaceAll("%x%", Integer.toString(data.getPositionProcessor().getBlockX())).replaceAll("%y%", Integer.toString(data.getPositionProcessor().getBlockY())).replaceAll("%z%", Integer.toString(data.getPositionProcessor().getBlockZ())).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%potion-effects%", data.getPlayer().getActivePotionEffects().toString()).replaceAll("%nearby-blocks%", nearbyBlocks).replaceAll("%nearby-entities%", nearbyEntities).replaceAll("%client-version%", clientVersion).replaceAll("%total-violations%", totalViolations).replaceAll("%combat-violations%", combatViolations).replaceAll("%movement-violations%", movementViolations).replaceAll("%player-violations%", playerViolations).replaceAll("%sensitivity%", sensitivity)));
                    }
                }
            }
        }
        return true;
    }
    
    private void sendPluginMessage(final Player player, final String msg) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("punishment");
        out.writeUTF(msg);
        player.sendPluginMessage(Vulcan.INSTANCE.getPlugin(), "vulcan:bungee", out.toByteArray());
    }
}
