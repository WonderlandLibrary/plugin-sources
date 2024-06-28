/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.mongodb.client.MongoCollection
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.manager;

import com.mongodb.client.MongoCollection;
import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.DatabaseManager;
import com.unknownmyname.manager.OptionsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunishmentManager {
    private static final /* synthetic */ String[] I;
    private static /* synthetic */ PunishmentManager instance;

    static {
        PunishmentManager.I();
    }

    public void enable() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void insertBan(PlayerData playerData, Check check) {
        playerData.setBanned((boolean)" ".length());
        name = playerData.getPlayer().getName();
        var5_4 = OptionsManager.getInstance().getBanCommands().iterator();
        "".length();
        if (1 != -1) ** GOTO lbl13
        throw null;
lbl-1000: // 1 sources:
        {
            banCommand = var5_4.next();
            v0 = new Object[" ".length()];
            v0["".length()] = name;
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)String.format(banCommand, v0).replace(PunishmentManager.I["".length()], check.getFriendlyName()));
lbl13: // 2 sources:
            ** while (var5_4.hasNext())
        }
lbl14: // 1 sources:
        if (OptionsManager.getInstance().isBanAnnouncement() == false) return;
        Bukkit.broadcastMessage((String)ChatColor.translateAlternateColorCodes((char)(96 ^ 70), (String)String.format(OptionsManager.getInstance().getSm(), new Object["".length()])));
        v1 = new Object[" ".length()];
        v1["".length()] = playerData.getPlayer().getName();
        Bukkit.broadcastMessage((String)ChatColor.translateAlternateColorCodes((char)(160 ^ 134), (String)String.format(OptionsManager.getInstance().getBanMessage().replace(PunishmentManager.I[" ".length()], check.getFriendlyName()), v1)));
        Bukkit.broadcastMessage((String)ChatColor.translateAlternateColorCodes((char)(75 ^ 109), (String)String.format(OptionsManager.getInstance().getSm(), new Object["".length()])));
    }

    public static PunishmentManager getInstance() {
        PunishmentManager punishmentManager;
        if (instance == null) {
            punishmentManager = instance = new PunishmentManager();
            "".length();
            if (!true) {
                throw null;
            }
        } else {
            punishmentManager = instance;
        }
        PunishmentManager punishmentManager2 = punishmentManager;
        return punishmentManager2;
    }

    public void disable() {
    }

    private static void I() {
        I = new String["  ".length()];
        PunishmentManager.I["".length()] = PunishmentManager.I("s6aVSX2rc1w=", "OGecO");
        PunishmentManager.I[" ".length()] = PunishmentManager.l("mi6pjaZCwc0=", "VzTrh");
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

    public long getTotalBans() {
        return DatabaseManager.getInstance().getBanCollection().count();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x25 ^ 0x2D), "DES");
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

