package me.frep.vulcan.spigot.gui.impl;

import java.util.Objects;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.ChatColor;
import me.frep.vulcan.spigot.util.material.XMaterial;
import me.frep.vulcan.spigot.util.ColorUtil;
import java.util.ArrayList;
import me.frep.vulcan.spigot.Vulcan;
import java.util.List;
import me.frep.vulcan.spigot.config.Config;
import org.apache.commons.lang.StringUtils;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import me.frep.vulcan.spigot.gui.VulcanGUI;

public class ManageCheck extends VulcanGUI
{
    public static final ManageCheck instance;
    private final String inventoryName = "Manage Check: ";
    private Inventory inventory;
    private ItemStack enabledItem;
    private ItemStack punishableItem;
    private ItemStack broadcastPunishmentItem;
    private ItemStack punishmentCommandItem;
    private ItemStack maxViolationsItem;
    private ItemStack minimumViolationsToAlertItem;
    private ItemStack alertIntervalItem;
    private ItemStack goBackItem;
    private ItemStack maxBufferItem;
    private ItemStack bufferMultipleItem;
    private ItemStack bufferDecayItem;
    private ItemStack hotbarShuffleItem;
    private ItemStack hotbarShuffleMinimumItem;
    private ItemStack hotbarShuffleIntervalItem;
    private ItemStack randomRotationItem;
    private ItemStack randomRotationMinimumItem;
    private ItemStack randomRotationIntervalItem;
    private ItemStack maximumPingItem;
    private ItemStack minimumTPSItem;
    private String path;
    private Class check;
    
    private void initializeInventory(final Class check) {
        this.check = check;
        final String checkClassName = check.getSimpleName();
        this.inventory = this.createInventory(54, "Manage Check: " + check.getSimpleName());
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
        final CheckInfo checkInfo = (CheckInfo) check.getAnnotation(CheckInfo.class);
        final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
        final char checkType = Character.toLowerCase(checkInfo.type());
        this.path = "checks." + checkCategory + "." + checkName + "." + checkType + ".";
        final boolean enabled = Config.ENABLED_CHECKS.get(checkClassName);
        final boolean punishable = Config.PUNISHABLE.get(checkClassName);
        final boolean broadcastPunishment = Config.BROADCAST_PUNISHMENT.get(checkClassName);
        final int maxViolations = Config.MAX_VIOLATIONS.get(checkClassName);
        final int alertInterval = Config.ALERT_INTERVAL.get(checkClassName);
        final int minimumViolationsToAlert = Config.MINIMUM_VL_TO_NOTIFY.get(checkClassName);
        final int maximumPing = Config.MAXIMUM_PING.get(checkClassName);
        final double minimumTPS = Config.MINIMUM_TPS.get(checkClassName);
        final List<String> punishmentCommands = Config.PUNISHMENT_COMMANDS.get(checkClassName);
        final int maxBuffer = Config.MAX_BUFFERS.get(checkClassName);
        final double bufferMultiple = Config.BUFFER_MULTIPLES.get(checkClassName);
        final double bufferDecay = Config.BUFFER_DECAYS.get(checkClassName);
        final boolean hotbarShuffle = Config.HOTBAR_SHUFFLE.get(checkClassName);
        final int minVlToShuffle = Config.HOTBAR_SHUFFLE_MINIMUM.get(checkClassName);
        final int shuffleEvery = Config.HOTBAR_SHUFFLE_EVERY.get(checkClassName);
        final boolean randomRotation = Config.RANDOM_ROTATION.get(checkClassName);
        final int minToRotate = Config.RANDOM_ROTATION_MINIMUM.get(checkClassName);
        final int rotateEvery = Config.RANDOM_ROTATION_EVERY.get(checkClassName);
        final boolean hasBuffer = Vulcan.INSTANCE.getPlugin().getConfig().isConfigurationSection(this.path + "buffer");
        final boolean hasHotbarShuffle = Vulcan.INSTANCE.getPlugin().getConfig().isConfigurationSection(this.path + "hotbar-shuffle");
        final boolean hasRandomRotation = Vulcan.INSTANCE.getPlugin().getConfig().isConfigurationSection(this.path + "random-rotation");
        final List<String> maxViolationsLore = new ArrayList<String>();
        maxViolationsLore.add(ColorUtil.translate("&7Current Max Violations: " + this.getColor(enabled) + maxViolations));
        maxViolationsLore.add("");
        maxViolationsLore.add(ColorUtil.translate("&7Click to Edit."));
        this.maxViolationsItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Max Violations", maxViolationsLore);
        final List<String> minimumViolationsToAlertLore = new ArrayList<String>();
        minimumViolationsToAlertLore.add(ColorUtil.translate("&7Current Minimum Violations to Alert: " + this.getColor(enabled) + minimumViolationsToAlert));
        minimumViolationsToAlertLore.add("");
        minimumViolationsToAlertLore.add(ColorUtil.translate("&7Click to Edit."));
        this.minimumViolationsToAlertItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Minimum Violations to Alert", minimumViolationsToAlertLore);
        final List<String> enabledEnabledLore = new ArrayList<String>();
        enabledEnabledLore.add(ColorUtil.translate("&7Click to &cdisable&7."));
        final List<String> enabledDisabledLore = new ArrayList<String>();
        enabledDisabledLore.add(ColorUtil.translate("&7Click to &aenable&7."));
        this.enabledItem = (enabled ? this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aEnabled", enabledEnabledLore) : this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cDisabled", enabledDisabledLore));
        final List<String> punishableEnabledLore = new ArrayList<String>();
        punishableEnabledLore.add(ColorUtil.translate("&7Click to &cdisable&7."));
        final List<String> punishableDisabledLore = new ArrayList<String>();
        punishableDisabledLore.add(ColorUtil.translate("&7Click to &aenable&7."));
        this.punishableItem = (punishable ? this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aPunishable", punishableEnabledLore) : this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cPunishable", punishableDisabledLore));
        final List<String> broadcastPunishmentEnabledLore = new ArrayList<String>();
        broadcastPunishmentEnabledLore.add(ColorUtil.translate("&7Click to &cdisable&7."));
        final List<String> broadcastPunishmentDisabledLore = new ArrayList<String>();
        broadcastPunishmentDisabledLore.add(ColorUtil.translate("&7Click to &aenable&7."));
        this.broadcastPunishmentItem = (broadcastPunishment ? this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aBroadcast Punishment", broadcastPunishmentEnabledLore) : this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cBroadcast Punishment", broadcastPunishmentDisabledLore));
        final List<String> alertIntervalLore = new ArrayList<String>();
        alertIntervalLore.add(ColorUtil.translate("&7Current Alert Interval: " + this.getColor(enabled) + alertInterval));
        alertIntervalLore.add("");
        alertIntervalLore.add(ColorUtil.translate("&7Click to Edit."));
        this.alertIntervalItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Alert Interval", alertIntervalLore);
        final List<String> punishmentCommandsLore = new ArrayList<String>();
        punishmentCommandsLore.add("");
        punishmentCommandsLore.add(ColorUtil.translate(this.getColor(enabled) + "Punishment Commands:"));
        for (String punishmentCommand : punishmentCommands) {
            punishmentCommand = ColorUtil.translate(punishmentCommand);
            if (punishmentCommand.length() > 40) {
                punishmentCommandsLore.add(ColorUtil.translate(" &8» &7" + ChatColor.stripColor("Command too long to display.")));
            }
            else {
                punishmentCommandsLore.add(ColorUtil.translate(" &8» &7" + ChatColor.stripColor(punishmentCommand)));
            }
        }
        punishmentCommandsLore.add("");
        punishmentCommandsLore.add(ColorUtil.translate("&7Left Click to &aadd &7a command."));
        punishmentCommandsLore.add(ColorUtil.translate("&7Right Click to &cremove &7a command."));
        this.punishmentCommandItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Punishment Commands", punishmentCommandsLore);
        final List<String> maximumPingLore = new ArrayList<String>();
        maximumPingLore.add(ColorUtil.translate("&7Current Maximum Ping: " + this.getColor(enabled) + maximumPing));
        maximumPingLore.add("");
        maximumPingLore.add(ColorUtil.translate("&7Click to Edit."));
        this.maximumPingItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Maximum Ping", maximumPingLore);
        final List<String> minimumTpsLore = new ArrayList<String>();
        minimumTpsLore.add(ColorUtil.translate("&7Current Minimum TPS: " + this.getColor(enabled) + minimumTPS));
        minimumTpsLore.add("");
        minimumTpsLore.add(ColorUtil.translate("&7Click to Edit."));
        this.minimumTPSItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Minimum TPS", minimumTpsLore);
        this.goBackItem = this.itemStack(XMaterial.REDSTONE_BLOCK.parseItem(), "&cGo Back", this.noLore);
        if (hasBuffer) {
            final List<String> maxBufferLore = new ArrayList<String>();
            maxBufferLore.add(ColorUtil.translate("&7Current Max Buffer: " + this.getColor(enabled) + maxBuffer));
            maxBufferLore.add("");
            maxBufferLore.add(ColorUtil.translate("&7Click to Edit."));
            this.maxBufferItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Max Buffer", maxBufferLore);
            final List<String> bufferMultipleLore = new ArrayList<String>();
            bufferMultipleLore.add(ColorUtil.translate("&7Current Buffer Multiple: " + this.getColor(enabled) + bufferMultiple));
            bufferMultipleLore.add("");
            bufferMultipleLore.add(ColorUtil.translate("&7Click to Edit."));
            this.bufferMultipleItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Buffer Multiple", bufferMultipleLore);
            final List<String> bufferDecayLore = new ArrayList<String>();
            bufferDecayLore.add(ColorUtil.translate("&7Current Buffer Decay: " + this.getColor(enabled) + bufferDecay));
            bufferDecayLore.add("");
            bufferDecayLore.add(ColorUtil.translate("&7Click to Edit."));
            this.bufferDecayItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(enabled) + "Set Buffer Decay", bufferDecayLore);
        }
        if (hasHotbarShuffle) {
            final List<String> hotbarShuffleEnabledLore = new ArrayList<String>();
            hotbarShuffleEnabledLore.add(ColorUtil.translate("&7Click to &cdisable&7."));
            final List<String> hotbarShuffleDisabledLore = new ArrayList<String>();
            hotbarShuffleDisabledLore.add(ColorUtil.translate("&7Click to &aenable&7."));
            this.hotbarShuffleItem = (hotbarShuffle ? this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aHotbar Shuffle", hotbarShuffleEnabledLore) : this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cHotbar Shuffle", hotbarShuffleDisabledLore));
            final List<String> hotbarShuffleMinimumLore = new ArrayList<String>();
            hotbarShuffleMinimumLore.add(ColorUtil.translate("&7Current Minimum Violations to Shuffle Hotbar: " + this.getColor(hotbarShuffle) + minVlToShuffle));
            hotbarShuffleMinimumLore.add("");
            hotbarShuffleMinimumLore.add(ColorUtil.translate("&7Click to Edit."));
            this.hotbarShuffleMinimumItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(hotbarShuffle) + "Set Hotbar Shuffle Minimum Violations", hotbarShuffleMinimumLore);
            final List<String> hotbarShuffleIntervalLore = new ArrayList<String>();
            hotbarShuffleIntervalLore.add(ColorUtil.translate("&7Current Hotbar Shuffle Interval: " + this.getColor(hotbarShuffle) + shuffleEvery));
            hotbarShuffleIntervalLore.add("");
            hotbarShuffleIntervalLore.add(ColorUtil.translate("&7Click to Edit."));
            this.hotbarShuffleIntervalItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(hotbarShuffle) + "Set Hotbar Shuffle Interval", hotbarShuffleIntervalLore);
        }
        if (hasRandomRotation) {
            final List<String> randomRotationEnabledLore = new ArrayList<String>();
            randomRotationEnabledLore.add(ColorUtil.translate("&7Click to &cdisable&7."));
            final List<String> randomRotationDisabledLore = new ArrayList<String>();
            randomRotationDisabledLore.add(ColorUtil.translate("&7Click to &aenable&7."));
            this.randomRotationItem = (randomRotation ? this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aRandom Rotation", randomRotationEnabledLore) : this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cRandom Rotation", randomRotationDisabledLore));
            final List<String> randomRotationMinimumLore = new ArrayList<String>();
            randomRotationMinimumLore.add(ColorUtil.translate("&7Current Minimum Violations to Rotate Randomly: " + this.getColor(randomRotation) + minToRotate));
            randomRotationMinimumLore.add("");
            randomRotationMinimumLore.add(ColorUtil.translate("&7Click to Edit."));
            this.randomRotationMinimumItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(randomRotation) + "Set Random Rotation Minimum Violations", randomRotationMinimumLore);
            final List<String> randomRotationIntervalLore = new ArrayList<String>();
            randomRotationIntervalLore.add(ColorUtil.translate("&7Current Random Rotation Interval: " + this.getColor(randomRotation) + rotateEvery));
            randomRotationIntervalLore.add("");
            randomRotationIntervalLore.add(ColorUtil.translate("&7Click to Edit."));
            this.randomRotationIntervalItem = this.itemStack(XMaterial.WRITABLE_BOOK.parseItem(), this.getColor(randomRotation) + "Set Random Rotation Interval", randomRotationIntervalLore);
        }
        for (int i = 0; i < 54; ++i) {
            this.inventory.setItem(i, this.glassPane());
        }
        this.inventory.setItem(9, this.maximumPingItem);
        this.inventory.setItem(10, this.maxViolationsItem);
        this.inventory.setItem(11, this.minimumViolationsToAlertItem);
        this.inventory.setItem(12, this.enabledItem);
        this.inventory.setItem(13, this.punishableItem);
        this.inventory.setItem(14, this.broadcastPunishmentItem);
        this.inventory.setItem(15, this.alertIntervalItem);
        this.inventory.setItem(16, this.punishmentCommandItem);
        this.inventory.setItem(17, this.minimumTPSItem);
        this.inventory.setItem(28, this.goBackItem);
        if (hasBuffer) {
            this.inventory.setItem(30, this.maxBufferItem);
            this.inventory.setItem(31, this.bufferMultipleItem);
            this.inventory.setItem(32, this.bufferDecayItem);
        }
        this.inventory.setItem(34, this.goBackItem);
        if (hasHotbarShuffle) {
            this.inventory.setItem(46, this.hotbarShuffleItem);
            this.inventory.setItem(47, this.hotbarShuffleMinimumItem);
            this.inventory.setItem(48, this.hotbarShuffleIntervalItem);
        }
        if (hasRandomRotation) {
            this.inventory.setItem(50, this.randomRotationIntervalItem);
            this.inventory.setItem(51, this.randomRotationMinimumItem);
            this.inventory.setItem(52, this.randomRotationItem);
        }
    }
    
    public void open(final Player player, final Class check) {
        this.initializeInventory(check);
        player.openInventory(this.inventory);
    }
    
    private String getColor(final boolean value) {
        return value ? ChatColor.GREEN.toString() : ChatColor.RED.toString();
    }
    
    public String getInventoryName() {
        Objects.requireNonNull(this);
        return "Manage Check: ";
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public ItemStack getEnabledItem() {
        return this.enabledItem;
    }
    
    public ItemStack getPunishableItem() {
        return this.punishableItem;
    }
    
    public ItemStack getBroadcastPunishmentItem() {
        return this.broadcastPunishmentItem;
    }
    
    public ItemStack getPunishmentCommandItem() {
        return this.punishmentCommandItem;
    }
    
    public ItemStack getMaxViolationsItem() {
        return this.maxViolationsItem;
    }
    
    public ItemStack getMinimumViolationsToAlertItem() {
        return this.minimumViolationsToAlertItem;
    }
    
    public ItemStack getAlertIntervalItem() {
        return this.alertIntervalItem;
    }
    
    public ItemStack getGoBackItem() {
        return this.goBackItem;
    }
    
    public ItemStack getMaxBufferItem() {
        return this.maxBufferItem;
    }
    
    public ItemStack getBufferMultipleItem() {
        return this.bufferMultipleItem;
    }
    
    public ItemStack getBufferDecayItem() {
        return this.bufferDecayItem;
    }
    
    public ItemStack getHotbarShuffleItem() {
        return this.hotbarShuffleItem;
    }
    
    public ItemStack getHotbarShuffleMinimumItem() {
        return this.hotbarShuffleMinimumItem;
    }
    
    public ItemStack getHotbarShuffleIntervalItem() {
        return this.hotbarShuffleIntervalItem;
    }
    
    public ItemStack getRandomRotationItem() {
        return this.randomRotationItem;
    }
    
    public ItemStack getRandomRotationMinimumItem() {
        return this.randomRotationMinimumItem;
    }
    
    public ItemStack getRandomRotationIntervalItem() {
        return this.randomRotationIntervalItem;
    }
    
    public ItemStack getMaximumPingItem() {
        return this.maximumPingItem;
    }
    
    public ItemStack getMinimumTPSItem() {
        return this.minimumTPSItem;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public Class getCheck() {
        return this.check;
    }
    
    public static ManageCheck getInstance() {
        return ManageCheck.instance;
    }
    
    static {
        instance = new ManageCheck();
    }
}
