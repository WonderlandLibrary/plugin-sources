/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package com.unknownmyname.gui.impl;

import com.google.common.collect.Maps;
import com.unknownmyname.check.Check;
import com.unknownmyname.gui.ClickData;
import com.unknownmyname.gui.Gui;
import com.unknownmyname.gui.impl.MainGui;
import com.unknownmyname.manager.CheckManager;
import com.unknownmyname.manager.GuiManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CheckGui
extends Gui {
    private static final /* synthetic */ String[] I;
    private final /* synthetic */ Map<Integer, Check.CheckType> checksById;
    private final /* synthetic */ ItemStack back;

    @Override
    public void onClick(ClickData clickData) {
        Integer slot = clickData.getSlot();
        if (slot <= this.getInventory().getSize() && slot >= 0) {
            ItemStack checkStack = clickData.getItemStack();
            Check.CheckType type = this.checksById.get(slot);
            if (checkStack.getItemMeta().getDisplayName().equalsIgnoreCase(I[0xD7 ^ 0xC2])) {
                clickData.getPlayer().closeInventory();
                GuiManager.getInstance().getMainGui().openGui(clickData.getPlayer());
                "".length();
                if (-1 == 4) {
                    throw null;
                }
            } else if (checkStack.getItemMeta().getDisplayName().equalsIgnoreCase(type.getName())) {
                Material material;
                boolean enabled = CheckManager.getInstance().enabled(type);
                if (enabled) {
                    material = Material.REDSTONE_BLOCK;
                    "".length();
                    if (4 == -1) {
                        throw null;
                    }
                } else {
                    material = Material.EMERALD_BLOCK;
                }
                checkStack.setType(material);
                if (enabled) {
                    CheckManager.getInstance().disableType(type, clickData.getPlayer());
                    "".length();
                    if (4 < 0) {
                        throw null;
                    }
                } else {
                    CheckManager.getInstance().enableType(type, clickData.getPlayer());
                }
                this.inventory.setItem(slot.intValue(), checkStack);
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public CheckGui() {
        super(CheckGui.I["".length()] + CheckGui.I[" ".length()], 73 ^ 100);
        this.checksById = Maps.newConcurrentMap();
        total = "".length();
        this.back = new ItemStack(Material.ARROW);
        checkMeta1 = this.back.getItemMeta();
        checkMeta1.setDisplayName(CheckGui.I["  ".length()] + CheckGui.I["   ".length()]);
        v0 = new String[" ".length()];
        v0["".length()] = (Object)ChatColor.GRAY + CheckGui.I[150 ^ 146];
        checkMeta1.setLore(Arrays.asList(v0));
        this.back.setItemMeta(checkMeta1);
        this.inventory.setItem(79 ^ 99, this.back);
        var6_3 = Check.CheckType.values();
        var5_4 = var6_3.length;
        var4_5 = "".length();
        "".length();
        if (-1 < 2) ** GOTO lbl54
        throw null;
lbl-1000: // 1 sources:
        {
            type = var6_3[var4_5];
            if (type.getSuffix().equals(CheckGui.I[59 ^ 62])) {
                if (CheckManager.getInstance().enabled(type)) {
                    v1 = Material.EMERALD_BLOCK;
                    "".length();
                    if (-1 != -1) {
                        throw null;
                    }
                } else {
                    v1 = Material.REDSTONE_BLOCK;
                }
                checkStack = new ItemStack(v1);
                checkMeta = checkStack.getItemMeta();
                checkMeta.setDisplayName(type.getName());
                if (type.getName().equalsIgnoreCase(CheckGui.I[27 ^ 29]) || type.getName().equalsIgnoreCase(CheckGui.I[58 ^ 61]) || type.getName().equalsIgnoreCase(CheckGui.I[151 ^ 159]) || type.getName().contains(CheckGui.I[7 ^ 14]) || type.getName().contains(CheckGui.I[91 ^ 81]) || type.getName().contains(CheckGui.I[94 ^ 85]) || type.getName().contains(CheckGui.I[204 ^ 192]) || type.getName().contains(CheckGui.I[124 ^ 113]) || type.getName().contains(CheckGui.I[119 ^ 121]) || type.getName().contains(CheckGui.I[84 ^ 91]) || type.getName().contains(CheckGui.I[34 ^ 50])) {
                    v2 = new String["  ".length()];
                    v2["".length()] = CheckGui.I[113 ^ 96];
                    v2[" ".length()] = CheckGui.I[108 ^ 126];
                    checkMeta.setLore(Arrays.asList(v2));
                    "".length();
                    if (2 <= 1) {
                        throw null;
                    }
                } else {
                    v3 = new String["  ".length()];
                    v3["".length()] = CheckGui.I[33 ^ 50];
                    v3[" ".length()] = CheckGui.I[157 ^ 137];
                    checkMeta.setLore(Arrays.asList(v3));
                }
                checkStack.setItemMeta(checkMeta);
                this.inventory.setItem(total, checkStack);
                this.checksById.put(total, type);
            }
            ++total;
            ++var4_5;
lbl54: // 2 sources:
            ** while (var4_5 < var5_4)
        }
lbl55: // 1 sources:
    }

    private static String I(String obj, String key) {
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
            if (-1 < 3) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        CheckGui.I();
    }

    private static void I() {
        I = new String[0x89 ^ 0x9F];
        CheckGui.I["".length()] = CheckGui.I("\u00ed\u0015", "JvjVb");
        CheckGui.I[" ".length()] = CheckGui.l("UecppDjU3zxn0rY5e+FQMQ==", "egJVp");
        CheckGui.I["  ".length()] = CheckGui.lI("+VWHgTOhxRw=", "nxHxU");
        CheckGui.I["   ".length()] = CheckGui.I("7\u000b4\u0006", "ujWmx");
        CheckGui.I[89 ^ 93] = CheckGui.l("nRdY/EiUYg/My23cTL3WOA==", "TOrsM");
        CheckGui.I[157 ^ 152] = CheckGui.lI("x30XvKJ5te8=", "wjHyC");
        CheckGui.I[95 ^ 89] = CheckGui.lI("Jxjaddnsw0R4Y2tS3aJxYQ==", "kjsAB");
        CheckGui.I[178 ^ 181] = CheckGui.l("hy1H4lUbKDA0mwK2vbdniQ==", "pQGtz");
        CheckGui.I[19 ^ 27] = CheckGui.I("*()52\u0018(7\u0000a/", "kADtA");
        CheckGui.I[115 ^ 122] = CheckGui.I("$%&!\b\u001a>+", "oLJMI");
        CheckGui.I[9 ^ 3] = CheckGui.I(")\f\u001b8\u0006\u0010", "gcHTi");
        CheckGui.I[52 ^ 63] = CheckGui.lI("x8enLKCgM9s=", "tacUt");
        CheckGui.I[53 ^ 57] = CheckGui.I("\u0010!\u0010#\u001e02\u0015$", "SSyWw");
        CheckGui.I[5 ^ 8] = CheckGui.lI("RFaU9UXKXhvv38FCtqzXFw==", "SVEPd");
        CheckGui.I[182 ^ 184] = CheckGui.I("7\u0004#\u001b&\u001a\u00184\u001f\u0000\u0004", "vqWte");
        CheckGui.I[54 ^ 57] = CheckGui.l("xXkqxOZ7sng0eY6lZ6/6Q9+4XduEwisq", "txNVB");
        CheckGui.I[149 ^ 133] = CheckGui.I("9\u000f\u001b8\u0016\u0019\u0005$7\u000e\u0015\u0013\f:\u000e", "pamYz");
        CheckGui.I[17 ^ 0] = CheckGui.lI("9awr/5bkGCA=", "WoPMI");
        CheckGui.I[208 ^ 194] = CheckGui.l("6aFSZC+Rx1u1jHF829tRzf6gEPf9PxzS9N5fCRjHmkBGsVaNVjPYQf4xI0Lbaseoi+xAi5l7WKk=", "dJssl");
        CheckGui.I[116 ^ 103] = CheckGui.lI("1gMN7q5nQlU=", "DHRhT");
        CheckGui.I[191 ^ 171] = CheckGui.l("FeSg+KvS9gEDGM9dFnCnEm0AqRux8YHNvc2SBYNPz636EUEZFpA3hzzmZrMhmUUglWFZtTdYCnAeONFG81N9Iw==", "JsasO");
        CheckGui.I[11 ^ 30] = CheckGui.l("XqwWiBuzxIY=", "xzuVq");
    }

    private static String lI(String obj, String key) {
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

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x65 ^ 0x6D), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

