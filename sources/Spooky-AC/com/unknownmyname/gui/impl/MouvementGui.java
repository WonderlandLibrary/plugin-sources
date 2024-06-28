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

public class MouvementGui
extends Gui {
    private static final /* synthetic */ String[] I;
    private final /* synthetic */ Map<Integer, Check.CheckType> checksById;
    private final /* synthetic */ ItemStack back;

    static {
        MouvementGui.I();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x5B ^ 0x53), "DES");
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
            if (true >= true) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String[0x6E ^ 0x61];
        MouvementGui.I["".length()] = MouvementGui.I("B3hcTfn23bM=", "HOdUH");
        MouvementGui.I[" ".length()] = MouvementGui.l("2ubVW/Jjlwj7HliJIhnRQ+9wiCLadExU", "NTull");
        MouvementGui.I["  ".length()] = MouvementGui.I("A+795bxu9tA=", "dUcYU");
        MouvementGui.I["   ".length()] = MouvementGui.I("ZMtKPMrXx6I=", "MJxrH");
        MouvementGui.I[77 ^ 73] = MouvementGui.l("VnkCXIUocUPcv+DSgnai4A==", "ctpiJ");
        MouvementGui.I[191 ^ 186] = MouvementGui.l("uvC9m8t/3oFqYfaultDjGA==", "fXMeL");
        MouvementGui.I[19 ^ 21] = MouvementGui.I("do0KSsTfCoY=", "XIeew");
        MouvementGui.I[12 ^ 11] = MouvementGui.l("wXKN4lWByMc=", "InEeS");
        MouvementGui.I[140 ^ 132] = MouvementGui.lI("2\"\u0005\u0015\u0006\r3\u0010Z$", "dGize");
        MouvementGui.I[40 ^ 33] = MouvementGui.l("+E9FaFhBfplt/mEyVIBkPQ==", "KfWPy");
        MouvementGui.I[128 ^ 138] = MouvementGui.lI("", "UWdLK");
        MouvementGui.I[150 ^ 157] = MouvementGui.I("uDDa+xwiCzf9m1w2dwtyoCJeXFoQIScNyzyTBtubK8GO/XTiCvAqZxBF1ZQ0ihP4wns0kdqRoqA=", "ISXXs");
        MouvementGui.I[123 ^ 119] = MouvementGui.l("2q3MZZY0i4w=", "GOZHh");
        MouvementGui.I[180 ^ 185] = MouvementGui.l("rGed1zgW1imVnivGLI7mZKt3Spg+PT2DR3mqblYaUNuubjsUikfE6x2L1e965/oF4Nd6vadApWb6E03/Iocaeg==", "oRXjw");
        MouvementGui.I[159 ^ 145] = MouvementGui.lI("\u00ef\u0019,2$#", "HznSG");
    }

    @Override
    public void onClick(ClickData clickData) {
        Integer slot = clickData.getSlot();
        if (slot <= this.getInventory().getSize() && slot >= 0) {
            ItemStack checkStack = clickData.getItemStack();
            Check.CheckType type = this.checksById.get(slot);
            if (checkStack.getItemMeta().getDisplayName().equalsIgnoreCase(I[0x58 ^ 0x56])) {
                clickData.getPlayer().closeInventory();
                GuiManager.getInstance().getMainGui().openGui(clickData.getPlayer());
                "".length();
                if (0 >= 4) {
                    throw null;
                }
            } else if (checkStack.getItemMeta().getDisplayName().equalsIgnoreCase(type.getName())) {
                Material material;
                boolean enabled = CheckManager.getInstance().enabled(type);
                if (enabled) {
                    material = Material.REDSTONE_BLOCK;
                    "".length();
                    if (1 == 4) {
                        throw null;
                    }
                } else {
                    material = Material.EMERALD_BLOCK;
                }
                checkStack.setType(material);
                if (enabled) {
                    CheckManager.getInstance().disableType(type, clickData.getPlayer());
                    "".length();
                    if (-1 == 0) {
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
    public MouvementGui() {
        super(MouvementGui.I["".length()] + MouvementGui.I[" ".length()], 26 ^ 62);
        this.checksById = Maps.newConcurrentMap();
        total = "".length();
        this.back = new ItemStack(Material.ARROW);
        checkMeta1 = this.back.getItemMeta();
        checkMeta1.setDisplayName(MouvementGui.I["  ".length()] + MouvementGui.I["   ".length()]);
        v0 = new String[" ".length()];
        v0["".length()] = (Object)ChatColor.GRAY + MouvementGui.I[112 ^ 116];
        checkMeta1.setLore(Arrays.asList(v0));
        this.back.setItemMeta(checkMeta1);
        this.inventory.setItem(188 ^ 159, this.back);
        var6_3 = Check.CheckType.values();
        var5_4 = var6_3.length;
        var4_5 = "".length();
        "".length();
        if (2 == 2) ** GOTO lbl54
        throw null;
lbl-1000: // 1 sources:
        {
            type = var6_3[var4_5];
            if (type.getSuffix().equals(MouvementGui.I[86 ^ 83])) {
                if (CheckManager.getInstance().enabled(type)) {
                    v1 = Material.EMERALD_BLOCK;
                    "".length();
                    if (1 <= -1) {
                        throw null;
                    }
                } else {
                    v1 = Material.REDSTONE_BLOCK;
                }
                checkStack = new ItemStack(v1);
                checkMeta = checkStack.getItemMeta();
                checkMeta.setDisplayName(type.getName());
                if (type.getName().contains(MouvementGui.I[45 ^ 43]) || type.getName().contains(MouvementGui.I[75 ^ 76]) || type.getName().equals(MouvementGui.I[133 ^ 141]) || type.getName().contains(MouvementGui.I[77 ^ 68])) {
                    v2 = new String["  ".length()];
                    v2["".length()] = MouvementGui.I[62 ^ 52];
                    v2[" ".length()] = MouvementGui.I[93 ^ 86];
                    checkMeta.setLore(Arrays.asList(v2));
                    "".length();
                    if (2 == 4) {
                        throw null;
                    }
                } else {
                    v3 = new String["  ".length()];
                    v3["".length()] = MouvementGui.I[160 ^ 172];
                    v3[" ".length()] = MouvementGui.I[24 ^ 21];
                    checkMeta.setLore(Arrays.asList(v3));
                }
                checkStack.setItemMeta(checkMeta);
                this.inventory.setItem(total, checkStack);
                this.checksById.put(total, type);
                ++total;
            }
            ++var4_5;
lbl54: // 2 sources:
            ** while (var4_5 < var5_4)
        }
lbl55: // 1 sources:
    }
}

