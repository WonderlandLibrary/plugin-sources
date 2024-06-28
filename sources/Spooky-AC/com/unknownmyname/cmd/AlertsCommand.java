/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package com.unknownmyname.cmd;

import com.unknownmyname.data.PlayerData;
import com.unknownmyname.main.Main;
import com.unknownmyname.manager.PlayerManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class AlertsCommand
implements CommandExecutor {
    private static final /* synthetic */ String[] I;

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
            if (3 == 3) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        AlertsCommand.I();
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

    private static void I() {
        I = new String[0x9C ^ 0x99];
        AlertsCommand.I["".length()] = AlertsCommand.I("\u0001 \u00018e>\"\f8 <=M\"$ n\u00182 n:\u0005(6n-\u0002,(/ \to", "NNmAE");
        AlertsCommand.I[" ".length()] = AlertsCommand.l("DDVwXdr/wS7ZT9vmxe3ihQMUG/0R2SXu4+pq8a2xaEI=", "LAvTw");
        AlertsCommand.I["  ".length()] = AlertsCommand.lI("cjCHkRqckdwMoUH+Bw+Sug==", "yXyXq");
        AlertsCommand.I["   ".length()] = AlertsCommand.I("0\u001a9c&\b\u0003)c*\u0000\u0006-!\"\fU5,;\u001bU-/+\u001b\u0001?", "iuLCN");
        AlertsCommand.I[76 ^ 72] = AlertsCommand.lI("g/lkGIXLst5+YuV0YqUy7A==", "HCoJS");
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x68 ^ 0x60), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(I["".length()]);
            return "".length() != 0;
        }
        Player player = (Player)sender;
        PlayerData playerData = PlayerManager.getInstance().getPlayer(player);
        if (playerData != null) {
            boolean bl;
            if (playerData.isAlerts()) {
                bl = "".length();
                "".length();
                if (3 == 1) {
                    throw null;
                }
            } else {
                bl = " ".length();
            }
            playerData.setAlerts(bl);
            if (playerData.isAlerts()) {
                sender.sendMessage((Object)ChatColor.GREEN + I[" ".length()]);
                player.setMetadata(I["  ".length()], (MetadataValue)new FixedMetadataValue((Plugin)Main.getPlugin(), (Object)(" ".length() != 0)));
                "".length();
                if (2 <= 1) {
                    throw null;
                }
            } else {
                sender.sendMessage((Object)ChatColor.RED + I["   ".length()]);
                player.removeMetadata(I[0x6D ^ 0x69], (Plugin)Main.getPlugin());
            }
        }
        return " ".length() != 0;
    }
}

