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

public class OtherGui
extends Gui {
    private final /* synthetic */ Map<Integer, Check.CheckType> checksById;
    private final /* synthetic */ ItemStack back;
    private static final /* synthetic */ String[] I;

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 4 ^ 0xC), "DES");
            Cipher des = Cipher.getInstance("DES");
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
            if (true > false) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String[0x59 ^ 0x48];
        OtherGui.I["".length()] = OtherGui.I("\u00e33", "DPJpc");
        OtherGui.I[" ".length()] = OtherGui.l("OTyIf7ti2grFBi+4amhIdQ==", "vqkIw");
        OtherGui.I["  ".length()] = OtherGui.I("\u00df9", "xZVjA");
        OtherGui.I["   ".length()] = OtherGui.I("\u000e2\n\n", "LSiaD");
        OtherGui.I[125 ^ 121] = OtherGui.lI("nuD7nl61gDl91jHJnpD1Aw==", "yWRnJ");
        OtherGui.I[42 ^ 47] = OtherGui.lI("CF6O9kF2Kzs=", "KWrnn");
        OtherGui.I[152 ^ 158] = OtherGui.lI("qSTWyj5joxY=", "ymOsx");
        OtherGui.I[109 ^ 106] = OtherGui.I("\u0016\u00172/<(R\u0015", "DrTFP");
        OtherGui.I[60 ^ 52] = OtherGui.l("qByyNGsjotuQlnYbF2+V6w==", "gpEeJ");
        OtherGui.I[162 ^ 171] = OtherGui.I("\u00114\u0017'&", "CQpBH");
        OtherGui.I[49 ^ 59] = OtherGui.lI("52tWtV6GmYU6Qj/hR13wbQ==", "JKgkk");
        OtherGui.I[157 ^ 150] = OtherGui.lI("FxbloutThH0=", "XPwDO");
        OtherGui.I[181 ^ 185] = OtherGui.l("DxKLSs9+XBU=", "ktAPp");
        OtherGui.I[13 ^ 0] = OtherGui.I("\u00e3\u0006\r! 7G:!,'\fy :d\u00148/,hG &<d\u00048'i1\u0014<i 0G. =,\b,=i\"\u00065:,&\u00067", "DgYII");
        OtherGui.I[80 ^ 94] = OtherGui.l("8r01HUcskWk=", "dlBhv");
        OtherGui.I[161 ^ 174] = OtherGui.lI("ZkrVAh0Q69e1ydYOfyH6t2U/D9kQRoFiJZ7wCaOJj8In+AaRnaLTPAZm9Ug6asDl2hJ/hgndAqCGcVUgQv4gTA==", "HIgMc");
        OtherGui.I[67 ^ 83] = OtherGui.I("\u00cf\u0007\u0017\u0004$\u0003", "hdUeG");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public OtherGui() {
        super(OtherGui.I["".length()] + OtherGui.I[" ".length()], 21 ^ 14);
        this.checksById = Maps.newConcurrentMap();
        total = "".length();
        this.back = new ItemStack(Material.ARROW);
        checkMeta1 = this.back.getItemMeta();
        checkMeta1.setDisplayName(OtherGui.I["  ".length()] + OtherGui.I["   ".length()]);
        v0 = new String[" ".length()];
        v0["".length()] = (Object)ChatColor.GRAY + OtherGui.I[34 ^ 38];
        checkMeta1.setLore(Arrays.asList(v0));
        this.back.setItemMeta(checkMeta1);
        this.inventory.setItem(14 ^ 20, this.back);
        var6_3 = Check.CheckType.values();
        var5_4 = var6_3.length;
        var4_5 = "".length();
        "".length();
        if (-1 < 3) ** GOTO lbl54
        throw null;
lbl-1000: // 1 sources:
        {
            type = var6_3[var4_5];
            if (type.getSuffix().equals(OtherGui.I[190 ^ 187])) {
                if (CheckManager.getInstance().enabled(type)) {
                    v1 = Material.EMERALD_BLOCK;
                    "".length();
                    if (4 < 0) {
                        throw null;
                    }
                } else {
                    v1 = Material.REDSTONE_BLOCK;
                }
                checkStack = new ItemStack(v1);
                checkMeta = checkStack.getItemMeta();
                checkMeta.setDisplayName(type.getName());
                if (type.getName().equalsIgnoreCase(OtherGui.I[174 ^ 168]) || type.getName().equalsIgnoreCase(OtherGui.I[43 ^ 44]) || type.getName().equalsIgnoreCase(OtherGui.I[0 ^ 8]) || type.getName().contains(OtherGui.I[7 ^ 14]) || type.getName().contains(OtherGui.I[157 ^ 151]) || type.getName().contains(OtherGui.I[9 ^ 2])) {
                    v2 = new String["  ".length()];
                    v2["".length()] = OtherGui.I[157 ^ 145];
                    v2[" ".length()] = OtherGui.I[185 ^ 180];
                    checkMeta.setLore(Arrays.asList(v2));
                    "".length();
                    if (4 < 1) {
                        throw null;
                    }
                } else {
                    v3 = new String["  ".length()];
                    v3["".length()] = OtherGui.I[45 ^ 35];
                    v3[" ".length()] = OtherGui.I[142 ^ 129];
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

    @Override
    public void onClick(ClickData clickData) {
        Integer slot = clickData.getSlot();
        if (slot <= this.getInventory().getSize() && slot >= 0) {
            ItemStack checkStack = clickData.getItemStack();
            Check.CheckType type = this.checksById.get(slot);
            if (checkStack.getItemMeta().getDisplayName().equalsIgnoreCase(I[0x68 ^ 0x78])) {
                clickData.getPlayer().closeInventory();
                GuiManager.getInstance().getMainGui().openGui(clickData.getPlayer());
                "".length();
                if (3 == 4) {
                    throw null;
                }
            } else if (checkStack.getItemMeta().getDisplayName().equalsIgnoreCase(type.getName())) {
                Material material;
                boolean enabled = CheckManager.getInstance().enabled(type);
                if (enabled) {
                    material = Material.REDSTONE_BLOCK;
                    "".length();
                    if (4 <= 0) {
                        throw null;
                    }
                } else {
                    material = Material.EMERALD_BLOCK;
                }
                checkStack.setType(material);
                if (enabled) {
                    CheckManager.getInstance().disableType(type, clickData.getPlayer());
                    "".length();
                    if (0 == 2) {
                        throw null;
                    }
                } else {
                    CheckManager.getInstance().enableType(type, clickData.getPlayer());
                }
                this.inventory.setItem(slot.intValue(), checkStack);
            }
        }
    }

    static {
        OtherGui.I();
    }
}

