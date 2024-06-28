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

public class DebugCommand
implements CommandExecutor {
    private static final /* synthetic */ String[] I;

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(I["".length()]);
            return "".length() != 0;
        }
        Player player = (Player)sender;
        if (!player.isOp()) {
            player.sendMessage(I[" ".length()]);
            return "".length() != 0;
        }
        PlayerData playerData = PlayerManager.getInstance().getPlayer(player);
        if (playerData != null) {
            boolean bl;
            if (playerData.isDebug()) {
                bl = "".length();
                "".length();
                if (2 != 2) {
                    throw null;
                }
            } else {
                bl = " ".length();
            }
            playerData.setDebug(bl);
            if (playerData.isDebug()) {
                sender.sendMessage((Object)ChatColor.GREEN + I["  ".length()]);
                player.setMetadata(I["   ".length()], (MetadataValue)new FixedMetadataValue((Plugin)Main.getPlugin(), (Object)(" ".length() != 0)));
                "".length();
                if (!true) {
                    throw null;
                }
            } else {
                sender.sendMessage((Object)ChatColor.RED + I[0x19 ^ 0x1D]);
                player.removeMetadata(I[0x2C ^ 0x29], (Plugin)Main.getPlugin());
            }
        }
        return " ".length() != 0;
    }

    private static void I() {
        I = new String[0xAA ^ 0xAC];
        DebugCommand.I["".length()] = DebugCommand.I("\u00018%\u001fm>:(\u001f(<%i\u0005, v<\u0015(n\"!\u000f>n5&\u000b /8-H", "NVIfM");
        DebugCommand.I[" ".length()] = DebugCommand.l("PCuJKwN19phGha7Y18EhrsjijsnXahjCDvdDUdBvjbUGAZLg/mXVWg==", "xOSyg");
        DebugCommand.I["  ".length()] = DebugCommand.lI("zAsJneXogjX58Su0XzCoATlsBr9KIZIPg1JIDXXk2MQ=", "kVRSk");
        DebugCommand.I["   ".length()] = DebugCommand.l("ZTjs3udt80FLnrtUfOYDGQ==", "tBgqC");
        DebugCommand.I[35 ^ 39] = DebugCommand.lI("CC9ibxxkkuegj2v2pTfHN+k/e/KYK3GI/0BLkSuA8KY=", "LNyJU");
        DebugCommand.I[55 ^ 50] = DebugCommand.I("/\b\u0003\u0011\u000f.\u0014\u000f\u0011\f/\f", "zKKTN");
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

    static {
        DebugCommand.I();
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x64 ^ 0x6C), "DES");
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

