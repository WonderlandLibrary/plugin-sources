/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.apache.commons.lang3.StringEscapeUtils
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package com.unknownmyname.cmd;

import com.unknownmyname.manager.OptionsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class CheckBanCommand
implements CommandExecutor {
    private static final /* synthetic */ String[] I;

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == " ".length()) {
            if (!sender.hasPermission(I["".length()])) {
                sender.sendMessage(I[" ".length()]);
                return "".length() != 0;
            }
            if (OptionsManager.getInstance().getBanConfiguration().get(args["".length()]) == null) {
                sender.sendMessage((Object)ChatColor.RED + I["  ".length()]);
                return "".length() != 0;
            }
            String enabled = (Object)ChatColor.GRAY + I["   ".length()] + StringEscapeUtils.unescapeHtml4((String)I[8 ^ 0xC]) + I[0x31 ^ 0x34];
            sender.sendMessage((Object)ChatColor.BLUE + args["".length()] + I[0x41 ^ 0x47]);
            sender.sendMessage((Object)ChatColor.GRAY + enabled + (Object)ChatColor.BLUE + I[0x3A ^ 0x3D] + (Object)ChatColor.WHITE + OptionsManager.getInstance().getBanConfiguration().get(String.valueOf(args["".length()]) + I[0x84 ^ 0x8C]));
            sender.sendMessage((Object)ChatColor.GRAY + enabled + (Object)ChatColor.BLUE + I[0x2B ^ 0x22] + (Object)ChatColor.WHITE + OptionsManager.getInstance().getBanConfiguration().get(String.valueOf(args["".length()]) + I[0x4C ^ 0x46]));
            sender.sendMessage((Object)ChatColor.GRAY + enabled + (Object)ChatColor.BLUE + I[0xA5 ^ 0xAE] + (Object)ChatColor.WHITE + OptionsManager.getInstance().getBanConfiguration().get(String.valueOf(args["".length()]) + I[0x31 ^ 0x3D]));
            sender.sendMessage((Object)ChatColor.GRAY + enabled + (Object)ChatColor.BLUE + I[0x2E ^ 0x23] + (Object)ChatColor.WHITE + OptionsManager.getInstance().getBanConfiguration().get(String.valueOf(args["".length()]) + I[0xA9 ^ 0xA7]));
            "".length();
            if (3 <= 0) {
                throw null;
            }
        } else {
            sender.sendMessage(I[0xD ^ 2]);
        }
        return " ".length() != 0;
    }

    private static void I() {
        I = new String[0x4D ^ 0x5D];
        CheckBanCommand.I["".length()] = CheckBanCommand.I(" \u0007+I;>\r", "MhOgN");
        CheckBanCommand.I[" ".length()] = CheckBanCommand.I("\u00ed48#x\u001a2\u0004!19$\u001f#6k", "JWvLX");
        CheckBanCommand.I["  ".length()] = CheckBanCommand.l("FZ1I1sYyXOfgDXK7iS890GOmUqBOQFjeThxRHXNvZuhITfKNb4QVTtcPTNslyPMr", "tqPFC");
        CheckBanCommand.I["   ".length()] = CheckBanCommand.I("S", "sPzDA");
        CheckBanCommand.I[167 ^ 163] = CheckBanCommand.l("X/nkzamixlg=", "pqPcn");
        CheckBanCommand.I[70 ^ 67] = CheckBanCommand.lI("b0N2NSP8ZU4=", "HFfme");
        CheckBanCommand.I[20 ^ 18] = CheckBanCommand.lI("LcT50Z10PIq3/XeXHeZyQ19sXtzzGobF", "sNRde");
        CheckBanCommand.I[158 ^ 153] = CheckBanCommand.I("#\u0002\u001f\u001cxV", "vWVXB");
        CheckBanCommand.I[89 ^ 81] = CheckBanCommand.lI("M6ZKFpl08AM=", "hhGtL");
        CheckBanCommand.I[97 ^ 104] = CheckBanCommand.I("\u001f!\u0005\u000fok", "KHhjU");
        CheckBanCommand.I[183 ^ 189] = CheckBanCommand.lI("k2TIAAXR8ZU=", "zhnzx");
        CheckBanCommand.I[98 ^ 105] = CheckBanCommand.lI("HwFd4tKBrCA=", "aossX");
        CheckBanCommand.I[157 ^ 145] = CheckBanCommand.lI("6MSFDeK9hjI=", "znTbY");
        CheckBanCommand.I[130 ^ 143] = CheckBanCommand.lI("NULVEoGzboU=", "iZbPZ");
        CheckBanCommand.I[142 ^ 128] = CheckBanCommand.lI("iiq5+YFH6LQ=", "MefeH");
        CheckBanCommand.I[109 ^ 98] = CheckBanCommand.l("SJ6CkyRUPpnkDz9o2LQIz3MFBFR8UOS+", "DSXFn");
    }

    static {
        CheckBanCommand.I();
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
            if (2 > 0) continue;
            throw null;
        }
        return sb.toString();
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xBE ^ 0xB6), "DES");
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
}

