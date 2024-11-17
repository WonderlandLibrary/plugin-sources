package me.frep.vulcan.spigot.gui.manager;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.check.AbstractCheck;
import java.util.Iterator;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.apache.commons.lang.math.NumberUtils;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.gui.impl.ManageCheck;
import me.frep.vulcan.spigot.check.manager.CheckManager;
import org.apache.commons.lang.StringUtils;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import me.frep.vulcan.spigot.gui.impl.PlayerChecks;
import me.frep.vulcan.spigot.gui.impl.MovementChecks;
import me.frep.vulcan.spigot.gui.impl.CombatChecks;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.gui.impl.CheckTypes;
import me.frep.vulcan.spigot.util.material.XMaterial;
import me.frep.vulcan.spigot.gui.impl.MainMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
import org.bukkit.event.Listener;
import me.frep.vulcan.spigot.gui.VulcanGUI;

public class GUIManager extends VulcanGUI implements Listener
{
    private final Map<UUID, String> addingPunishmentCommands;
    private final Map<UUID, String> removingPunishmentCommands;
    private final Map<UUID, String> configPath;
    
    public GUIManager() {
        this.addingPunishmentCommands = new HashMap<UUID, String>();
        this.removingPunishmentCommands = new HashMap<UUID, String>();
        this.configPath = new HashMap<UUID, String>();
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();
        if (event.getCurrentItem() == null) {
            return;
        }
        final String title = event.getView().getTitle();
        final ItemStack item = event.getCurrentItem();
        final Material material = item.getType();
        if (title.equals(MainMenu.getInstance().getInventoryName())) {
            event.setCancelled(true);
            if (material.equals(XMaterial.CHEST.parseMaterial())) {
                CheckTypes.getInstance().open(player);
            }
            if (material.equals(XMaterial.PAPER.parseMaterial())) {
                Vulcan.INSTANCE.reload();
                player.sendMessage(ColorUtil.translate(Config.RELOAD_SUCCESS));
            }
            if (material.equals(XMaterial.DIAMOND.parseMaterial())) {
                player.performCommand("vulcan top");
                player.closeInventory();
            }
        }
        if (title.equals(CheckTypes.getInstance().getInventoryName())) {
            event.setCancelled(true);
            if (material.equals(XMaterial.REDSTONE_BLOCK.parseMaterial())) {
                MainMenu.getInstance().open(player);
            }
            else if (material.equals(XMaterial.DIAMOND_SWORD.parseMaterial())) {
                CombatChecks.getInstance().open(player, 1);
            }
            else if (material.equals(XMaterial.FEATHER.parseMaterial())) {
                MovementChecks.getInstance().open(player, 1);
            }
            else if (material.equals(XMaterial.APPLE.parseMaterial())) {
                PlayerChecks.getInstance().open(player, 1);
            }
        }
        if (title.startsWith(CombatChecks.getInstance().getInventoryName())) {
            event.setCancelled(true);
            final int page = CombatChecks.getInstance().getCurrentPage().get(player.getUniqueId());
            if (material == Material.AIR) {
                return;
            }
            final String className = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().replaceAll(" ", "").replace("*", "").replaceAll("Type", "").replaceAll("\\(", "").replaceAll("\\)", ""));
            if (material.equals(XMaterial.REDSTONE_BLOCK.parseMaterial())) {
                CheckTypes.getInstance().open(player);
            }
            else if (item.equals(this.arrow(false))) {
                if (page <= 1) {
                    player.sendMessage(ColorUtil.translate(Config.NO_PREVIOUS_PAGE));
                    return;
                }
                CombatChecks.getInstance().open(player, page - 1);
            }
            else if (item.equals(this.arrow(true))) {
                if (page == CombatChecks.getInstance().getMaxPages()) {
                    player.sendMessage(ColorUtil.translate(Config.NO_NEXT_PAGE));
                    return;
                }
                CombatChecks.getInstance().open(player, page + 1);
                CombatChecks.getInstance().open(player, page + 1);
            }
            else if (item.equals(CombatChecks.getInstance().getEnableAllItem())) {
                final List<Class> combatChecks = CombatChecks.getInstance().getCombatChecks();
                for (final Class clazz : combatChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    String path = "checks.combat." + checkName + "." + checkType + ".";
                    Config.ENABLED_CHECKS.remove(checkClassName);
                    Config.ENABLED_CHECKS.put(checkClassName, true);
                    Config.setValue(path + "enabled", true);
                }
                CombatChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.ENABLED_ALL_CHECKS));
            }
            else if (item.equals(CombatChecks.getInstance().getDisableAllItem())) {
                final List<Class> combatChecks = CombatChecks.getInstance().getCombatChecks();
                for (final Class clazz : combatChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    String path = "checks.combat." + checkName + "." + checkType + ".";
                    Config.ENABLED_CHECKS.remove(checkClassName);
                    Config.ENABLED_CHECKS.put(checkClassName, false);
                    Config.setValue(path + "enabled", false);
                }
                CombatChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.DISABLED_ALL_CHECKS));
            }
            else if (item.equals(CombatChecks.getInstance().getEnableAllPunishmentsItem())) {
                final List<Class> combatChecks = CombatChecks.getInstance().getCombatChecks();
                for (final Class clazz : combatChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    String path = "checks.combat." + checkName + "." + checkType + ".";
                    Config.PUNISHABLE.remove(checkClassName);
                    Config.PUNISHABLE.put(checkClassName, true);
                    Config.setValue(path + "punishable", true);
                }
                CombatChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.ENABLED_ALL_PUNISHMENTS));
            }
            else if (item.equals(CombatChecks.getInstance().getDisableAllPunishmentsItem())) {
                final List<Class> combatChecks = CombatChecks.getInstance().getCombatChecks();
                for (final Class clazz : combatChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    String path = "checks.combat." + checkName + "." + checkType + ".";
                    Config.PUNISHABLE.remove(checkClassName);
                    Config.PUNISHABLE.put(checkClassName, false);
                    Config.setValue(path + "punishable", false);
                }
                CombatChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.DISABLED_ALL_PUNISHMENTS));
            }
            else {
                for(Class check : CheckManager.CHECKS) {
                    if (check.getSimpleName().equals(className)) {
                        ManageCheck.getInstance().open(player, check);
                    }
                }
            }
        }
        if (title.startsWith(MovementChecks.getInstance().getInventoryName())) {
            event.setCancelled(true);
            final int page = MovementChecks.getInstance().getCurrentPage().get(player.getUniqueId());
            if (material == Material.AIR) {
                return;
            }
            final String className = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().replaceAll(" ", "").replace("*", "").replaceAll("Type", "").replaceAll("\\(", "").replaceAll("\\)", ""));
            if (material.equals(XMaterial.REDSTONE_BLOCK.parseMaterial())) {
                CheckTypes.getInstance().open(player);
            }
            else if (item.equals(this.arrow(false))) {
                if (page <= 1) {
                    player.sendMessage(ColorUtil.translate(Config.NO_PREVIOUS_PAGE));
                    return;
                }
                MovementChecks.getInstance().open(player, page - 1);
            }
            else if (item.equals(this.arrow(true))) {
                if (page == MovementChecks.getInstance().getMaxPages()) {
                    player.sendMessage(ColorUtil.translate(Config.NO_NEXT_PAGE));
                    return;
                }
                MovementChecks.getInstance().open(player, page + 1);
                MovementChecks.getInstance().open(player, page + 1);
            }
            else if (item.equals(MovementChecks.getInstance().getEnableAllItem())) {
                final List<Class> movementChecks = MovementChecks.getInstance().getMovementChecks();
                for (final Class clazz : movementChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.movement." + checkName + "." + checkType + ".";
                    Config.ENABLED_CHECKS.remove(checkClassName);
                    Config.ENABLED_CHECKS.put(checkClassName, true);
                    Config.setValue(path + "enabled", true);
                }
                MovementChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.ENABLED_ALL_CHECKS));
            }
            else if (item.equals(MovementChecks.getInstance().getDisableAllItem())) {
                final List<Class> movementChecks = MovementChecks.getInstance().getMovementChecks();
                for (final Class clazz : movementChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.movement." + checkName + "." + checkType + ".";
                    Config.ENABLED_CHECKS.remove(checkClassName);
                    Config.ENABLED_CHECKS.put(checkClassName, false);
                    Config.setValue(path + "enabled", false);
                }
                MovementChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.DISABLED_ALL_CHECKS));
            }
            else if (item.equals(MovementChecks.getInstance().getEnableAllPunishmentsItem())) {
                final List<Class> movementChecks = MovementChecks.getInstance().getMovementChecks();
                for (final Class clazz : movementChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.movement." + checkName + "." + checkType + ".";
                    Config.PUNISHABLE.remove(checkClassName);
                    Config.PUNISHABLE.put(checkClassName, true);
                    Config.setValue(path + "punishable", true);
                }
                MovementChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.ENABLED_ALL_PUNISHMENTS));
            }
            else if (item.equals(MovementChecks.getInstance().getDisableAllPunishmentsItem())) {
                final List<Class> movementChecks = MovementChecks.getInstance().getMovementChecks();
                for (final Class clazz : movementChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.movement." + checkName + "." + checkType + ".";
                    Config.PUNISHABLE.remove(checkClassName);
                    Config.PUNISHABLE.put(checkClassName, false);
                    Config.setValue(path + "punishable", false);
                }
                MovementChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.DISABLED_ALL_PUNISHMENTS));
            }
            else {
                for(Class check : CheckManager.CHECKS) {
                    if (check.getSimpleName().equals(className)) {
                        ManageCheck.getInstance().open(player, check);
                    }
                }
            }
        }
        if (title.startsWith(PlayerChecks.getInstance().getInventoryName())) {
            event.setCancelled(true);
            final int page = PlayerChecks.getInstance().getCurrentPage().get(player.getUniqueId());
            if (material == Material.AIR) {
                return;
            }
            final String className = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().replaceAll(" ", "").replace("*", "").replaceAll("Type", "").replaceAll("\\(", "").replaceAll("\\)", ""));
            if (material.equals(XMaterial.REDSTONE_BLOCK.parseMaterial())) {
                CheckTypes.getInstance().open(player);
            }
            else if (item.equals(this.arrow(false))) {
                if (page <= 1) {
                    player.sendMessage(ColorUtil.translate(Config.NO_PREVIOUS_PAGE));
                    return;
                }
                PlayerChecks.getInstance().open(player, page - 1);
            }
            else if (item.equals(this.arrow(true))) {
                if (page == PlayerChecks.getInstance().getMaxPages()) {
                    player.sendMessage(ColorUtil.translate(Config.NO_NEXT_PAGE));
                    return;
                }
                PlayerChecks.getInstance().open(player, page + 1);
                PlayerChecks.getInstance().open(player, page + 1);
            }
            else if (item.equals(PlayerChecks.getInstance().getEnableAllItem())) {
                final List<Class> playerChecks = PlayerChecks.getInstance().getPlayerChecks();
                for (final Class clazz : playerChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.player." + checkName + "." + checkType + ".";
                    Config.ENABLED_CHECKS.remove(checkClassName);
                    Config.ENABLED_CHECKS.put(checkClassName, true);
                    Config.setValue(path + "enabled", true);
                }
                PlayerChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.ENABLED_ALL_CHECKS));
            }
            else if (item.equals(PlayerChecks.getInstance().getDisableAllItem())) {
                final List<Class> playerChecks = PlayerChecks.getInstance().getPlayerChecks();
                for (final Class clazz : playerChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.player." + checkName + "." + checkType + ".";
                    Config.ENABLED_CHECKS.remove(checkClassName);
                    Config.ENABLED_CHECKS.put(checkClassName, false);
                    Config.setValue(path + "enabled", false);
                }
                PlayerChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.DISABLED_ALL_CHECKS));
            }
            else if (item.equals(PlayerChecks.getInstance().getEnableAllPunishmentsItem())) {
                final List<Class> playerChecks = PlayerChecks.getInstance().getPlayerChecks();
                for (final Class clazz : playerChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.player." + checkName + "." + checkType + ".";
                    Config.PUNISHABLE.remove(checkClassName);
                    Config.PUNISHABLE.put(checkClassName, true);
                    Config.setValue(path + "punishable", true);
                }
                PlayerChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.ENABLED_ALL_PUNISHMENTS));
            }
            else if (item.equals(PlayerChecks.getInstance().getDisableAllPunishmentsItem())) {
                final List<Class> playerChecks = PlayerChecks.getInstance().getPlayerChecks();
                for (final Class clazz : playerChecks) {
                    final String checkClassName = clazz.getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
                    final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkType = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.player." + checkName + "." + checkType + ".";
                    Config.PUNISHABLE.remove(checkClassName);
                    Config.PUNISHABLE.put(checkClassName, false);
                    Config.setValue(path + "punishable", false);
                }
                PlayerChecks.getInstance().open(player, page);
                player.sendMessage(ColorUtil.translate(Config.DISABLED_ALL_PUNISHMENTS));
            }
            else {
                for(Class check : CheckManager.CHECKS) {
                    if (check.getSimpleName().equals(className)) {
                        ManageCheck.getInstance().open(player, check);
                    }
                }
            }
        }
        if (title.startsWith(ManageCheck.getInstance().getInventoryName())) {
            event.setCancelled(true);
            final String checkName = title.split(": ")[1];
            final String path = ManageCheck.getInstance().getPath();
            final Class check = ManageCheck.getInstance().getCheck();
            String checkCategory = "";
            if (check.getName().contains("combat")) {
                checkCategory = "combat";
            }
            else if (check.getName().contains("movement")) {
                checkCategory = "movement";
            }
            else if (check.getName().contains("player")) {
                checkCategory = "player";
            }
            if (item.equals(ManageCheck.getInstance().getEnabledItem())) {
                if (Config.ENABLED_CHECKS.get(checkName)) {
                    Config.ENABLED_CHECKS.remove(checkName);
                    Config.ENABLED_CHECKS.put(checkName, false);
                    Config.setValue(path + "enabled", false);
                    player.sendMessage(ColorUtil.translate(Config.DISABLED_CHECK.replaceAll("%check%", checkName)));
                }
                else {
                    Config.ENABLED_CHECKS.remove(checkName);
                    Config.ENABLED_CHECKS.put(checkName, true);
                    Config.setValue(path + "enabled", true);
                    player.sendMessage(ColorUtil.translate(Config.ENABLED_CHECK.replaceAll("%check%", checkName)));
                }
                ManageCheck.getInstance().open(player, ManageCheck.getInstance().getCheck());
            }
            else if (item.equals(ManageCheck.getInstance().getPunishableItem())) {
                if (Config.PUNISHABLE.get(checkName)) {
                    Config.PUNISHABLE.remove(checkName);
                    Config.PUNISHABLE.put(checkName, false);
                    Config.setValue(path + "punishable", false);
                    player.sendMessage(ColorUtil.translate(Config.DISABLED_PUNISHMENT.replaceAll("%check%", checkName)));
                }
                else {
                    Config.PUNISHABLE.remove(checkName);
                    Config.PUNISHABLE.put(checkName, true);
                    Config.setValue(path + "punishable", true);
                    player.sendMessage(ColorUtil.translate(Config.ENABLED_PUNISHMENT.replaceAll("%check%", checkName)));
                }
                ManageCheck.getInstance().open(player, ManageCheck.getInstance().getCheck());
            }
            else if (item.equals(ManageCheck.getInstance().getHotbarShuffleItem())) {
                if (Config.HOTBAR_SHUFFLE.get(checkName)) {
                    Config.HOTBAR_SHUFFLE.remove(checkName);
                    Config.HOTBAR_SHUFFLE.put(checkName, false);
                    Config.setValue(path + "hotbar-shuffle.enabled", false);
                    player.sendMessage(ColorUtil.translate(Config.DISABLED_HOTBAR_SHUFFLE.replaceAll("%check%", checkName)));
                }
                else {
                    Config.HOTBAR_SHUFFLE.remove(checkName);
                    Config.HOTBAR_SHUFFLE.put(checkName, true);
                    Config.setValue(path + "hotbar-shuffle.enabled", true);
                    player.sendMessage(ColorUtil.translate(Config.ENABLED_HOTBAR_SHUFFLE.replaceAll("%check%", checkName)));
                }
                ManageCheck.getInstance().open(player, ManageCheck.getInstance().getCheck());
            }
            else if (item.equals(ManageCheck.getInstance().getRandomRotationItem())) {
                if (Config.RANDOM_ROTATION.get(checkName)) {
                    Config.RANDOM_ROTATION.remove(checkName);
                    Config.RANDOM_ROTATION.put(checkName, false);
                    Config.setValue(path + "random-rotation.enabled", false);
                    player.sendMessage(ColorUtil.translate(Config.DISABLED_RANDOM_ROTATION.replaceAll("%check%", checkName)));
                }
                else {
                    Config.RANDOM_ROTATION.remove(checkName);
                    Config.RANDOM_ROTATION.put(checkName, true);
                    Config.setValue(path + "random-rotation.enabled", true);
                    player.sendMessage(ColorUtil.translate(Config.ENABLED_RANDOM_ROTATION.replaceAll("%check%", checkName)));
                }
                ManageCheck.getInstance().open(player, ManageCheck.getInstance().getCheck());
            }
            else if (item.equals(ManageCheck.getInstance().getBroadcastPunishmentItem())) {
                if (Config.BROADCAST_PUNISHMENT.get(checkName)) {
                    Config.BROADCAST_PUNISHMENT.remove(checkName);
                    Config.BROADCAST_PUNISHMENT.put(checkName, false);
                    Config.setValue(path + "broadcast-punishment", false);
                    player.sendMessage(ColorUtil.translate(Config.DISABLED_BROADCAST_PUNISHMENT.replaceAll("%check%", checkName)));
                }
                else {
                    Config.BROADCAST_PUNISHMENT.remove(checkName);
                    Config.BROADCAST_PUNISHMENT.put(checkName, true);
                    player.sendMessage(ColorUtil.translate(Config.ENABLED_BROADCAST_PUNISHMENT.replaceAll("%check%", checkName)));
                }
                ManageCheck.getInstance().open(player, ManageCheck.getInstance().getCheck());
            }
            else if (item.equals(ManageCheck.getInstance().getMaxBufferItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultMaxBuffer = Config.MAX_BUFFERS.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int maxBuffer = Integer.parseInt(s);
                        if (maxBuffer < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.MAX_BUFFERS.remove(checkName);
                            Config.MAX_BUFFERS.put(checkName, maxBuffer);
                            Config.setValue(path + "buffer.max", maxBuffer);
                            player.sendMessage(ColorUtil.translate(Config.SET_MAX_BUFFER.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(maxBuffer))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultMaxBuffer).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getHotbarShuffleMinimumItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultMinToHotbarShuffleViolations = Config.HOTBAR_SHUFFLE_MINIMUM.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int minVlToShuffleHotbar = Integer.parseInt(s);
                        if (minVlToShuffleHotbar < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.HOTBAR_SHUFFLE_MINIMUM.remove(checkName);
                            Config.HOTBAR_SHUFFLE_MINIMUM.put(checkName, minVlToShuffleHotbar);
                            Config.setValue(path + "hotbar-shuffle.minimum-vl-to-shuffle", minVlToShuffleHotbar);
                            player.sendMessage(ColorUtil.translate(Config.SET_HOTBAR_SHUFFLE_MINIMUM_VIOLATIONS.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(minVlToShuffleHotbar))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultMinToHotbarShuffleViolations).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getHotbarShuffleIntervalItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultHotbarShuffleInterval = Config.HOTBAR_SHUFFLE_EVERY.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int hotbarShuffleInterval = Integer.parseInt(s);
                        if (hotbarShuffleInterval < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.HOTBAR_SHUFFLE_EVERY.remove(checkName);
                            Config.HOTBAR_SHUFFLE_EVERY.put(checkName, hotbarShuffleInterval);
                            Config.setValue(path + "hotbar-shuffle.shuffle-every", hotbarShuffleInterval);
                            player.sendMessage(ColorUtil.translate(Config.SET_HOTBAR_SHUFFLE_INTERVAL.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(hotbarShuffleInterval))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultHotbarShuffleInterval).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getRandomRotationMinimumItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultRandomRotationMinimum = Config.RANDOM_ROTATION_MINIMUM.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int randomRotationMinimum = Integer.parseInt(s);
                        if (randomRotationMinimum < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.RANDOM_ROTATION_MINIMUM.remove(checkName);
                            Config.RANDOM_ROTATION_MINIMUM.put(checkName, randomRotationMinimum);
                            Config.setValue(path + "random-rotation.minimum-vl-to-randomly-rotate", randomRotationMinimum);
                            player.sendMessage(ColorUtil.translate(Config.SET_RANDOM_ROTATION_MINIMUM_VIOLATIONS.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(randomRotationMinimum))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultRandomRotationMinimum).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getMaximumPingItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultMaxPing = Config.MAXIMUM_PING.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int maximumPing = Integer.parseInt(s);
                        if (maximumPing < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.MAXIMUM_PING.remove(checkName);
                            Config.MAXIMUM_PING.put(checkName, maximumPing);
                            Config.setValue(path + "maximum-ping", maximumPing);
                            player.sendMessage(ColorUtil.translate(Config.SET_MAXIMUM_PING.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(maximumPing))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultMaxPing).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getMinimumTPSItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final double defaultMinimumTPS = Config.MINIMUM_TPS.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s)) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_NUMBER));
                    }
                    else {
                        final double minimumTPS = Double.parseDouble(s);
                        if (minimumTPS < 0.0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.MINIMUM_TPS.remove(checkName);
                            Config.MINIMUM_TPS.put(checkName, minimumTPS);
                            Config.setValue(path + "minimum-tps", minimumTPS);
                            player.sendMessage(ColorUtil.translate(Config.SET_MINIMUM_TPS.replaceAll("%check%", checkName).replaceAll("%value%", Double.toString(minimumTPS))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultMinimumTPS).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getRandomRotationIntervalItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultRotationInterval = Config.RANDOM_ROTATION_EVERY.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int randomRotationInterval = Integer.parseInt(s);
                        if (randomRotationInterval < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.RANDOM_ROTATION_EVERY.remove(checkName);
                            Config.RANDOM_ROTATION_EVERY.put(checkName, randomRotationInterval);
                            Config.setValue(path + "random-rotation.rotate-every", randomRotationInterval);
                            player.sendMessage(ColorUtil.translate(Config.SET_RANDOM_ROTATION_INTERVAL.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(randomRotationInterval))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultRotationInterval).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getAlertIntervalItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultAlertInterval = Config.ALERT_INTERVAL.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".") || s.contains(",")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int alertInterval = Integer.parseInt(s);
                        if (alertInterval < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.ALERT_INTERVAL.remove(checkName);
                            Config.ALERT_INTERVAL.put(checkName, alertInterval);
                            Config.setValue(path + "alert-interval", alertInterval);
                            player.sendMessage(ColorUtil.translate(Config.SET_ALERT_INTERVAL.replaceAll("%check%", checkName).replaceAll("%value%", Integer.toString(alertInterval))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultAlertInterval).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getBufferMultipleItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final double defaultBufferMultiple = Config.BUFFER_MULTIPLES.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s)) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_NUMBER));
                    }
                    else {
                        final double bufferMultiple = Double.parseDouble(s);
                        if (bufferMultiple < 0.0) {
                            return AnvilGUI.Response.text("Must be positive!");
                        }
                        else {
                            Config.BUFFER_MULTIPLES.remove(checkName);
                            Config.BUFFER_MULTIPLES.put(checkName, bufferMultiple);
                            Config.setValue(path + "buffer.multiple", bufferMultiple);
                            player.sendMessage(ColorUtil.translate(Config.SET_BUFFER_MULTIPLE.replaceAll("%check%", checkName).replaceAll("%value%", Double.toString(bufferMultiple))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultBufferMultiple).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getBufferDecayItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final double defaultBufferDecay = Config.BUFFER_DECAYS.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s)) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_NUMBER));
                    }
                    else {
                        final double bufferDecay = Double.parseDouble(s);
                        if (bufferDecay < 0.0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_NUMBER));
                        }
                        else {
                            Config.BUFFER_DECAYS.remove(checkName);
                            Config.BUFFER_DECAYS.put(checkName, bufferDecay);
                            Config.setValue(path + "buffer.decay", bufferDecay);
                            player.sendMessage(ColorUtil.translate(Config.SET_BUFFER_DECAY.replaceAll("%check%", checkName).replaceAll("%value%", Double.toString(bufferDecay))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultBufferDecay).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getMaxViolationsItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultMaxViolations = Config.MAX_VIOLATIONS.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int maxViolations = Integer.parseInt(s);
                        if (maxViolations < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_POSITIVE));
                        }
                        else {
                            Config.MAX_VIOLATIONS.remove(checkName);
                            Config.MAX_VIOLATIONS.put(checkName, maxViolations);
                            Config.setValue(path + "max-violations", maxViolations);
                            player.sendMessage(ColorUtil.translate(Config.SET_MAX_VIOLATIONS.replaceAll("%check%", checkName).replaceAll("%value%", Double.toString(maxViolations))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultMaxViolations).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getMinimumViolationsToAlertItem())) {
                if (ServerUtil.isHigherThan1_17()) {
                    player.sendMessage(ColorUtil.translate("&cThis is unavailable on 1.17 servers!"));
                    return;
                }
                final int defaultMinVlToNotify = Config.MINIMUM_VL_TO_NOTIFY.get(checkName);
                new AnvilGUI.Builder().onComplete((player1, s) -> {
                    if (!NumberUtils.isNumber(s) || s.contains(".")) {
                        return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                    }
                    else {
                        final int minVlToAlert = Integer.parseInt(s);
                        if (minVlToAlert < 0) {
                            return AnvilGUI.Response.text(ColorUtil.translate(Config.MUST_BE_AN_INTEGER));
                        }
                        else {
                            Config.MINIMUM_VL_TO_NOTIFY.remove(checkName);
                            Config.MINIMUM_VL_TO_NOTIFY.put(checkName, minVlToAlert);
                            Config.setValue(path + "dont-alert-until", minVlToAlert);
                            player.sendMessage(ColorUtil.translate(Config.SET_MINIMUM_VIOLATIONS_TO_ALERT.replaceAll("%check%", checkName).replaceAll("%value%", Double.toString(minVlToAlert))));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check), 2L);
                            return AnvilGUI.Response.close();
                        }
                    }
                }).text("Current Value: " + defaultMinVlToNotify).item(XMaterial.PAPER.parseItem()).plugin(Vulcan.INSTANCE.getPlugin()).open(player);
            }
            else if (item.equals(ManageCheck.getInstance().getPunishmentCommandItem())) {
                final List<String> punishmentCommands = Config.PUNISHMENT_COMMANDS.get(checkName);
                switch (event.getClick()) {
                    case LEFT: {
                        player.closeInventory();
                        player.sendMessage(ColorUtil.translate(Config.ENTER_PUNISHMENT_COMMAND));
                        this.addingPunishmentCommands.put(player.getUniqueId(), checkName);
                        this.configPath.put(player.getUniqueId(), path);
                        break;
                    }
                    case RIGHT: {
                        player.closeInventory();
                        player.sendMessage(ColorUtil.translate("&7&m---»--*---------------------------------*--«---"));
                        player.sendMessage(ColorUtil.translate(Config.REMOVE_PUNISHMENT_COMMAND));
                        player.sendMessage(ColorUtil.translate("&r"));
                        this.removingPunishmentCommands.put(player.getUniqueId(), checkName);
                        this.configPath.put(player.getUniqueId(), path);
                        for (int i = 0; i < punishmentCommands.size(); ++i) {
                            String command = punishmentCommands.get(i);
                            command = ColorUtil.translate(command);
                            player.sendMessage(ColorUtil.translate(" &8» &c" + i + " &8- &7" + ChatColor.stripColor(command)));
                        }
                        player.sendMessage(ColorUtil.translate("&7&m---»--*---------------------------------*--«---"));
                        break;
                    }
                }
            }
            else if (item.equals(ManageCheck.getInstance().getGoBackItem())) {
                final String s = checkCategory;
                switch (s) {
                    case "combat": {
                        CombatChecks.getInstance().open(player, CombatChecks.getInstance().getCurrentPage().get(player.getUniqueId()));
                        break;
                    }
                    case "movement": {
                        MovementChecks.getInstance().open(player, MovementChecks.getInstance().getCurrentPage().get(player.getUniqueId()));
                        break;
                    }
                    case "player": {
                        PlayerChecks.getInstance().open(player, PlayerChecks.getInstance().getCurrentPage().get(player.getUniqueId()));
                        break;
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();
        Class check = null;
        if (this.addingPunishmentCommands.containsKey(player.getUniqueId())) {
            final String checkName = this.addingPunishmentCommands.get(player.getUniqueId());
            boolean stopped = false;
            if (message.equals("CANCEL") || message.equals("STOP")) {
                player.sendMessage(ColorUtil.translate(Config.STOPPED_EDITING_PUNISHMENT_COMMANDS));
                stopped = true;
            }
            else {
                final List<String> punishmentCommands = Config.PUNISHMENT_COMMANDS.get(checkName);
                punishmentCommands.add(message);
                Config.PUNISHMENT_COMMANDS.remove(checkName);
                Config.PUNISHMENT_COMMANDS.put(checkName, punishmentCommands);
                Config.setValue(this.configPath.get(player.getUniqueId()) + "punishment-commands", punishmentCommands);
            }
            for (int length = CheckManager.CHECKS.length, i = 0; i < length; ++i) {
                if (check.getSimpleName().equals(checkName)) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check));
                }
            }
            if (!stopped) {
                player.sendMessage(ColorUtil.translate(Config.ADDED_PUNISHMENT_COMMAND.replaceAll("%command%", message).replaceAll("%check%", checkName)));
            }
            event.setCancelled(true);
            this.removeFromLists(player);
        }
        if (this.removingPunishmentCommands.containsKey(player.getUniqueId())) {
            final String checkName = this.removingPunishmentCommands.get(player.getUniqueId());
            if (message.equals("CANCEL") || message.equals("STOP")) {
                player.sendMessage(ColorUtil.translate(Config.STOPPED_EDITING_PUNISHMENT_COMMANDS));
            }
            else if (!this.isNumeric(message)) {
                player.sendMessage(ColorUtil.translate(Config.MUST_BE_NUMBER));
            }
            else {
                final List<String> punishmentCommands2 = Config.PUNISHMENT_COMMANDS.get(checkName);
                final int index = Integer.parseInt(message);
                if (index < 0 || index > punishmentCommands2.size()) {
                    player.sendMessage(ColorUtil.translate(Config.INVALID_INDEX_NUMBER));
                }
                else {
                    final String removed = punishmentCommands2.get(index);
                    player.sendMessage(ColorUtil.translate(Config.REMOVED_PUNISHMENT_COMMAND.replaceAll("%command%", removed).replaceAll("%check%", checkName)));
                    punishmentCommands2.remove(index);
                    Config.PUNISHMENT_COMMANDS.remove(checkName);
                    Config.PUNISHMENT_COMMANDS.put(checkName, punishmentCommands2);
                    event.setCancelled(true);
                    Config.setValue(this.configPath.get(player.getUniqueId()) + "punishment-commands", punishmentCommands2);
                    for (final Class check2 : CheckManager.CHECKS) {
                        if (check2.getSimpleName().equals(checkName)) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> ManageCheck.getInstance().open(player, check2));
                        }
                    }
                }
            }
            this.removeFromLists(player);
        }
    }
    
    public void removeFromLists(final Player player) {
        this.removingPunishmentCommands.remove(player.getUniqueId());
        this.addingPunishmentCommands.remove(player.getUniqueId());
        this.configPath.remove(player.getUniqueId());
    }
    
    private boolean isNumeric(final String s) {
        for (final char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
