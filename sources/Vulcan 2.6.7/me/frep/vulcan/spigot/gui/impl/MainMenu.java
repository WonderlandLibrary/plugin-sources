package me.frep.vulcan.spigot.gui.impl;

import org.bukkit.entity.Player;
import java.util.List;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.util.material.XMaterial;
import me.frep.vulcan.spigot.util.ColorUtil;
import java.util.ArrayList;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.inventory.Inventory;
import me.frep.vulcan.spigot.gui.VulcanGUI;

public class MainMenu extends VulcanGUI
{
    public static final MainMenu instance;
    private final String inventoryName;
    private Inventory inventory;
    
    public MainMenu() {
        this.inventoryName = Config.GUI_TITLE;
    }
    
    private void initializeInventory() {
        this.inventory = this.createInventory(27, this.inventoryName);
        for (int i = 0; i < 27; ++i) {
            this.inventory.setItem(i, this.glassPane());
        }
        final List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("");
        comingSoon.add(ColorUtil.translate("&cComing Soon.."));
        final List<String> disclaimer = new ArrayList<String>();
        disclaimer.add(ColorUtil.translate(""));
        disclaimer.add(ColorUtil.translate("&4&lWarning: &cEditing checks via the GUI can result"));
        disclaimer.add(ColorUtil.translate("&cin config corruption. Editing from the config file"));
        disclaimer.add(ColorUtil.translate("&cis recommended."));
        this.inventory.setItem(10, this.itemStack(XMaterial.CHEST.parseItem(), "&cManage Checks", disclaimer));
        final List<String> topViolationsLore = new ArrayList<String>();
        topViolationsLore.add(ColorUtil.translate(""));
        topViolationsLore.add(ColorUtil.translate("&7Click to view top violations."));
        this.inventory.setItem(11, this.itemStack(XMaterial.DIAMOND.parseItem(), "&cTop Violations", topViolationsLore));
        final ArrayList<String> bookString = new ArrayList<String>();
        bookString.add(ColorUtil.translate(""));
        bookString.add(ColorUtil.translate("&7Discord: &c5170#data"));
        bookString.add(ColorUtil.translate("&7Support: &chttps://discord.isnow.dev/"));
        bookString.add(ColorUtil.translate(""));
        bookString.add(ColorUtil.translate("&7" + Vulcan.INSTANCE.getNonce()));
        final String version = Vulcan.INSTANCE.getPlugin().getDescription().getVersion();
        this.inventory.setItem(13, this.itemStack(XMaterial.BOOK.parseItem(), "&cVulcan't v" + version + " by fap (data better) &7(" + Vulcan.INSTANCE.getSpigot() + "&7)", bookString));
        this.inventory.setItem(15, this.itemStack(XMaterial.OAK_SIGN.parseItem(), "&cSearch Player", comingSoon));
        this.inventory.setItem(16, this.itemStack(XMaterial.PAPER.parseItem(), "&cReload", this.noLore));
    }
    
    public void open(final Player player) {
        if (this.inventory == null) {
            this.initializeInventory();
        }
        player.openInventory(this.inventory);
    }
    
    public String getInventoryName() {
        return this.inventoryName;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public static MainMenu getInstance() {
        return MainMenu.instance;
    }
    
    static {
        instance = new MainMenu();
    }
}
