package me.frep.vulcan.spigot.gui.impl;

import java.util.Objects;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.ChatColor;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.config.Config;
import org.apache.commons.lang.StringUtils;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.util.material.XMaterial;
import java.util.Collection;
import java.util.Arrays;
import me.frep.vulcan.spigot.check.manager.CheckManager;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import me.frep.vulcan.spigot.gui.VulcanGUI;

public class MovementChecks extends VulcanGUI
{
    public static final MovementChecks instance;
    private final List<Class> movementChecks;
    private final String inventoryName = "Manage Movement Checks";
    private int maxPages;
    private final HashMap<UUID, Integer> currentPage;
    private Inventory inventory;
    private final List<Inventory> inventoryList;
    private ItemStack enableAllItem;
    private ItemStack disableAllItem;
    private ItemStack enableAllPunishmentsItem;
    private ItemStack disableAllPunishmentsItem;
    
    public MovementChecks() {
        this.movementChecks = new ArrayList<Class>();
        this.currentPage = new HashMap<UUID, Integer>();
        this.inventoryList = new ArrayList<Inventory>();
    }
    
    public void initializeInventory() {
        this.inventoryList.clear();
        this.movementChecks.clear();
        this.inventory = this.createInventory(54, "Manage Movement Checks");
        final List<Class> classList = new ArrayList<Class>(Arrays.asList(CheckManager.CHECKS));
        for (final Class clazz : classList) {
            if (clazz.getName().contains("movement")) {
                this.movementChecks.add(clazz);
            }
        }
        this.maxPages = 0;
        this.maxPages = (int)Math.ceil(this.movementChecks.size() / 45.0);
        int offset = 0;
        int currentPage = 1;
        for (int i = 0; i <= this.maxPages; ++i) {
            final Inventory temporaryInventory = this.createInventory(54, "Manage Movement Checks [" + currentPage + "/" + this.maxPages + "]");
            for (int bottomRow = 46; bottomRow < 54; ++bottomRow) {
                temporaryInventory.setItem(bottomRow, this.glassPane());
            }
            this.enableAllItem = this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aEnable All Checks", this.noLore);
            this.disableAllItem = this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cDisable All Checks", this.noLore);
            this.disableAllPunishmentsItem = this.itemStack(XMaterial.RED_TERRACOTTA.parseItem(), "&cDisable All Punishments", this.noLore);
            this.enableAllPunishmentsItem = this.itemStack(XMaterial.GREEN_TERRACOTTA.parseItem(), "&aEnable All Punishments", this.noLore);
            temporaryInventory.setItem(45, this.arrow(false));
            temporaryInventory.setItem(46, this.enableAllItem);
            temporaryInventory.setItem(47, this.disableAllItem);
            temporaryInventory.setItem(51, this.disableAllPunishmentsItem);
            temporaryInventory.setItem(52, this.enableAllPunishmentsItem);
            temporaryInventory.setItem(53, this.arrow(true));
            temporaryInventory.setItem(49, this.itemStack(XMaterial.REDSTONE_BLOCK.parseItem(), "&cGo Back", this.noLore));
            for (int checkSlot = 0; checkSlot < 45; ++checkSlot) {
                if (offset <= this.movementChecks.size() - 1) {
                    final String checkClassName = this.movementChecks.get(offset).getSimpleName();
                    final CheckInfo checkInfo = (CheckInfo)this.movementChecks.get(offset).getAnnotation(CheckInfo.class);
                    final String checkName = checkInfo.name();
                    final char checkType = checkInfo.type();
                    final boolean experimental = checkInfo.experimental();
                    final String description = checkInfo.description();
                    String parsedName;
                    if (experimental) {
                        parsedName = checkName + " *(Type " + checkType + ")*";
                    }
                    else {
                        parsedName = checkName + " (Type " + checkType + ")";
                    }
                    final String checkNameToLowercase = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
                    final char checkTypeToLowercase = Character.toLowerCase(checkInfo.type());
                    final String path = "checks.movement." + checkNameToLowercase + "." + checkTypeToLowercase + ".";
                    final boolean enabled = Config.ENABLED_CHECKS.get(checkClassName);
                    final boolean punishable = Config.PUNISHABLE.get(checkClassName);
                    final boolean broadcastPunishment = Config.BROADCAST_PUNISHMENT.get(checkClassName);
                    final int maxViolations = Config.MAX_VIOLATIONS.get(checkClassName);
                    final int maximumPing = Config.MAXIMUM_PING.get(checkClassName);
                    final double minimumTps = Config.MINIMUM_TPS.get(checkClassName);
                    final int alertEvery = Config.ALERT_INTERVAL.get(checkClassName);
                    final int alertMinimum = Config.MINIMUM_VL_TO_NOTIFY.get(checkClassName);
                    final List<String> punishmentCommands = Config.PUNISHMENT_COMMANDS.get(checkClassName);
                    final int maxBuffer = Config.MAX_BUFFERS.get(checkClassName);
                    final double bufferMultiple = Config.BUFFER_MULTIPLES.get(checkClassName);
                    final double bufferDecay = Config.BUFFER_DECAYS.get(checkClassName);
                    final boolean hasBuffer = Vulcan.INSTANCE.getPlugin().getConfig().isConfigurationSection(path + "buffer");
                    final List<String> lore = new ArrayList<String>();
                    lore.add(ColorUtil.translate("&7&m---»--*---------------------------*--«---"));
                    lore.add(ColorUtil.translate("&7" + description));
                    lore.add(ColorUtil.translate("&7&m---»--*---------------------------*--«---"));
                    lore.add(ColorUtil.translate(this.getColor(enabled) + "Settings:"));
                    lore.add(ColorUtil.translate(" &8» &7Enabled: " + this.getColor(enabled) + enabled));
                    lore.add(ColorUtil.translate(" &8» &7Punishable: " + this.getColor(punishable) + punishable));
                    lore.add(ColorUtil.translate(" &8» &7Broadcast Punishment: " + this.getColor(enabled) + broadcastPunishment));
                    lore.add(ColorUtil.translate(" &8» &7Max Violations: &c" + this.getColor(enabled) + maxViolations));
                    lore.add(ColorUtil.translate(" &8» &7Alert Interval: &c" + this.getColor(enabled) + alertEvery));
                    lore.add(ColorUtil.translate(" &8» &7Minimum To Alert: &c" + this.getColor(enabled) + alertMinimum));
                    lore.add(ColorUtil.translate(" &8» &7Maximum Ping: &c" + this.getColor(enabled) + maximumPing));
                    lore.add(ColorUtil.translate(" &8» &7Minimum TPS: &c" + this.getColor(enabled) + minimumTps));
                    lore.add(ColorUtil.translate("&7&m---»--*---------------------------*--«---"));
                    lore.add(ColorUtil.translate(this.getColor(enabled) + "Punishment Commands:"));
                    for (String punishmentCommand : punishmentCommands) {
                        punishmentCommand = ColorUtil.translate(punishmentCommand);
                        if (punishmentCommand.length() > 40) {
                            lore.add(ColorUtil.translate(" &8» &7" + ChatColor.stripColor("Command too long to display.")));
                        }
                        else {
                            lore.add(ColorUtil.translate(" &8» &7" + ChatColor.stripColor(punishmentCommand)));
                        }
                    }
                    if (!hasBuffer) {
                        lore.add(ColorUtil.translate("&7&m---»--*---------------------------*--«---"));
                    }
                    if (hasBuffer) {
                        lore.add(ColorUtil.translate("&7&m---»--*---------------------------*--«---"));
                        lore.add(ColorUtil.translate(this.getColor(enabled) + "Buffer:"));
                        lore.add(ColorUtil.translate(" &8» &7Max: &c" + this.getColor(enabled) + maxBuffer));
                        lore.add(ColorUtil.translate(" &8» &7Multiple on Flag: &c" + this.getColor(enabled) + bufferMultiple));
                        lore.add(ColorUtil.translate(" &8» &7Decay: &c" + this.getColor(enabled) + bufferDecay));
                        lore.add(ColorUtil.translate("&7&m---»--*---------------------------*--«---"));
                    }
                    if (Config.ENABLED_CHECKS.get(checkClassName)) {
                        temporaryInventory.setItem(checkSlot, this.itemStack(XMaterial.GREEN_STAINED_GLASS_PANE.parseItem(), "&a" + parsedName, lore));
                    }
                    else {
                        temporaryInventory.setItem(checkSlot, this.itemStack(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), "&c" + parsedName, lore));
                    }
                }
                ++offset;
            }
            ++currentPage;
            this.inventoryList.add(temporaryInventory);
        }
    }
    
    public void open(final Player player, final int page) {
        this.initializeInventory();
        this.currentPage.put(player.getUniqueId(), page);
        player.openInventory(this.inventoryList.get(this.currentPage.get(player.getUniqueId()) - 1));
    }
    
    private String getColor(final boolean value) {
        return value ? ChatColor.GREEN.toString() : ChatColor.RED.toString();
    }
    
    public List<Class> getMovementChecks() {
        return this.movementChecks;
    }
    
    public String getInventoryName() {
        Objects.requireNonNull(this);
        return "Manage Movement Checks";
    }
    
    public int getMaxPages() {
        return this.maxPages;
    }
    
    public HashMap<UUID, Integer> getCurrentPage() {
        return this.currentPage;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public List<Inventory> getInventoryList() {
        return this.inventoryList;
    }
    
    public ItemStack getEnableAllItem() {
        return this.enableAllItem;
    }
    
    public ItemStack getDisableAllItem() {
        return this.disableAllItem;
    }
    
    public ItemStack getEnableAllPunishmentsItem() {
        return this.enableAllPunishmentsItem;
    }
    
    public ItemStack getDisableAllPunishmentsItem() {
        return this.disableAllPunishmentsItem;
    }
    
    public static MovementChecks getInstance() {
        return MovementChecks.instance;
    }
    
    static {
        instance = new MovementChecks();
    }
}
