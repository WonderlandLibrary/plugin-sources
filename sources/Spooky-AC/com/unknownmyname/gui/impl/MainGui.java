/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package com.unknownmyname.gui.impl;

import com.unknownmyname.gui.ClickData;
import com.unknownmyname.gui.Gui;
import com.unknownmyname.gui.impl.CheckGui;
import com.unknownmyname.gui.impl.MouvementGui;
import com.unknownmyname.gui.impl.OtherGui;
import com.unknownmyname.manager.GuiManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainGui
extends Gui {
    private final /* synthetic */ ItemStack checkStack;
    private static final /* synthetic */ String[] I;
    private final /* synthetic */ ItemStack checkStack2;
    private final /* synthetic */ ItemStack checkStack1;

    @Override
    public void onClick(ClickData clickData) {
        if (clickData.getSlot().intValue() == "  ".length()) {
            clickData.getPlayer().closeInventory();
            GuiManager.getInstance().getCheckGui().openGui(clickData.getPlayer());
        }
        if (clickData.getSlot() == (0x85 ^ 0x81)) {
            clickData.getPlayer().closeInventory();
            GuiManager.getInstance().getMovGui().openGui(clickData.getPlayer());
        }
        if (clickData.getSlot() == (0x63 ^ 0x65)) {
            clickData.getPlayer().closeInventory();
            GuiManager.getInstance().getOtherGui().openGui(clickData.getPlayer());
        }
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x72 ^ 0x7A), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), "Blowfish");
            Cipher des = Cipher.getInstance("Blowfish");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void I() {
        I = new String[0x18 ^ 0x13];
        MainGui.I["".length()] = MainGui.I("oNfWLMTibV4=", "qfGAb");
        MainGui.I[" ".length()] = MainGui.l("IKidhKj/se0lEKsSVzAHuLIw7OBIqeBx", "bgHmC");
        MainGui.I["  ".length()] = MainGui.lI("\u00fe:", "YYnUw");
        MainGui.I["   ".length()] = MainGui.I("xXlWo68I5953A3wHtw3hPA==", "ONYwm");
        MainGui.I[103 ^ 99] = MainGui.I("mPL7WgS8nbasjTyTauY7tw==", "nyiAI");
        MainGui.I[140 ^ 137] = MainGui.lI("\u00c94", "nWbys");
        MainGui.I[143 ^ 137] = MainGui.lI("\u000b5\r\u00154+?\u0016\u0017q\u00052\u001d\u0000:", "FZxcQ");
        MainGui.I[105 ^ 110] = MainGui.lI("\t\u0006\u0013%\u0002j\u001e\u0015f\u0006:\u000f\u0014", "JjzFi");
        MainGui.I[176 ^ 184] = MainGui.I("Dwq2KE7KSgo=", "oolBo");
        MainGui.I[27 ^ 18] = MainGui.lI("\u0001&?#\u0017n\u0011?#\u0006%", "NRWFe");
        MainGui.I[62 ^ 52] = MainGui.l("sz9X0P7DG5UFJzZMpr7o0w==", "SIrAB");
    }

    public MainGui() {
        super(I["".length()] + I[" ".length()], 0xA9 ^ 0xA0);
        this.checkStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta checkMeta = this.checkStack.getItemMeta();
        checkMeta.setDisplayName(I["  ".length()] + I["   ".length()]);
        String[] arrstring = new String[" ".length()];
        arrstring["".length()] = (Object)ChatColor.GRAY + I[0xA ^ 0xE];
        checkMeta.setLore(Arrays.asList(arrstring));
        this.checkStack.setItemMeta(checkMeta);
        this.inventory.setItem("  ".length(), this.checkStack);
        this.checkStack1 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta checkMeta1 = this.checkStack1.getItemMeta();
        checkMeta1.setDisplayName(I[0x80 ^ 0x85] + I[0xB ^ 0xD]);
        String[] arrstring2 = new String[" ".length()];
        arrstring2["".length()] = (Object)ChatColor.GRAY + I[0xA1 ^ 0xA6];
        checkMeta1.setLore(Arrays.asList(arrstring2));
        this.checkStack1.setItemMeta(checkMeta1);
        this.inventory.setItem(0x88 ^ 0x8C, this.checkStack1);
        this.checkStack2 = new ItemStack(Material.REDSTONE_COMPARATOR);
        ItemMeta checkMeta2 = this.checkStack2.getItemMeta();
        checkMeta2.setDisplayName(I[0x76 ^ 0x7E] + I[0x86 ^ 0x8F]);
        String[] arrstring3 = new String[" ".length()];
        arrstring3["".length()] = (Object)ChatColor.GRAY + I[3 ^ 9];
        checkMeta2.setLore(Arrays.asList(arrstring3));
        this.checkStack2.setItemMeta(checkMeta2);
        this.inventory.setItem(0xB8 ^ 0xBE, this.checkStack2);
    }

    private static String lI(String obj, String key) {
        StringBuilder sb = new StringBuilder();
        char[] keyChars = key.toCharArray();
        int i = "".length();
        char[] arrc = obj.toCharArray();
        int n = arrc.length;
        for (int j = "".length(); j < n; ++j) {
            char c = arrc[j];
            sb.append((char)(c ^ keyChars[i % keyChars.length]));
            ++i;
            "".length();
            if (0 < 4) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        MainGui.I();
    }
}

