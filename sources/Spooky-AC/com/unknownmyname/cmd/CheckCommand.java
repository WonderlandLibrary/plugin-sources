/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package com.unknownmyname.cmd;

import com.unknownmyname.main.Main;
import com.unknownmyname.manager.OptionsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class CheckCommand
implements TabCompleter,
CommandExecutor {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Main plugin;

    public CheckCommand(Main plugin) {
        this.plugin = plugin;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        block8 : {
            block7 : {
                if (cmd.getName().equalsIgnoreCase(CheckCommand.I[51 ^ 52]) == false) return null;
                if (args.length != " ".length()) return null;
                playerName = new ArrayList<String>();
                if (args["".length()].equals(CheckCommand.I[68 ^ 76])) break block7;
                var7_6 = Bukkit.getOnlinePlayers().iterator();
                "".length();
                if (4 != 4) {
                    throw null;
                }
                ** GOTO lbl21
            }
            var7_7 = Bukkit.getOnlinePlayers().iterator();
            "".length();
            if (-1 == -1) ** GOTO lbl30
            throw null;
lbl-1000: // 1 sources:
            {
                pl = (Player)var7_6.next();
                if (!pl.getName().toLowerCase().startsWith(args["".length()].toLowerCase())) continue;
                playerName.add(pl.getName());
lbl21: // 3 sources:
                ** while (var7_6.hasNext())
            }
lbl22: // 1 sources:
            "".length();
            if (3 == 3) break block8;
            throw null;
lbl-1000: // 1 sources:
            {
                pl = (Player)var7_7.next();
                if (!pl.getName().toLowerCase().startsWith(args["".length()].toLowerCase())) continue;
                playerName.add(pl.getName());
lbl30: // 3 sources:
                ** while (var7_7.hasNext())
            }
        }
        Collections.sort(playerName);
        return playerName;
    }

    public boolean onCommand(CommandSender sender, Command command, String s, final String[] args) {
        final Player player = (Player)sender;
        if (!player.hasPermission(OptionsManager.getInstance().getModPermission())) {
            player.sendMessage(I["".length()]);
            return " ".length() != 0;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage((Object)ChatColor.RED + I[" ".length()]);
                return " ".length() != 0;
            }
            sender.sendMessage((Object)ChatColor.RED + I["  ".length()]);
            return " ".length() != 0;
        }
        Player toCheck = Bukkit.getPlayer((String)StringUtils.join((Object[])args));
        if (toCheck == null) {
            sender.sendMessage((Object)ChatColor.RED + I["   ".length()] + StringUtils.join((Object[])args) + I[0x1B ^ 0x1F]);
            return " ".length() != 0;
        }
        toCheck = Bukkit.getPlayer((String)StringUtils.join((Object[])args));
        player.sendMessage(I[0x5D ^ 0x58] + toCheck.getName() + I[0x4B ^ 0x4D]);
        new BukkitRunnable(){
            private static final /* synthetic */ String[] I;

            private static String lI(String obj, String key) {
                try {
                    SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x67 ^ 0x6F), "DES");
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
                    if (0 != 4) continue;
                    throw null;
                }
                return sb.toString();
            }

            static {
                1.I();
            }

            private static void I() {
                I = new String[0x7F ^ 0x78];
                1.I["".length()] = 1.I("NLT+dAOpJkwJaQcE9vqW9AlpBwT2+pb0CWkHBPb6lvSxHlkbmESd7Q==", "mtoQb");
                1.I[" ".length()] = 1.l("\u00ceN\n\u000b\u0007\n\u001c,\u0007B\u0019\u001b(\u001a\u0007\u001bMi\u00c4\u0004\u00ce\u0018", "iwIcb");
                1.I["  ".length()] = 1.l("", "eoBKd");
                1.I["   ".length()] = 1.lI("3XGs1bUeY9v5wS2clLs54pXda88NoBOH", "pjwwg");
                1.I[121 ^ 125] = 1.I("NrFGo3oj+WTPkjveTDEc60iJdURreyr9", "qZMHJ");
                1.I[131 ^ 134] = 1.l("\u00cet\u00eb<.\u0001(-$\u0004\u0007*vp\u00ca\n\u0003#", "iMLPm");
                1.I[89 ^ 95] = 1.I("gAqd6p/xyUqpXIg1kCXKN6lciDWQJco3qVyINZAlyjcrOk4BJnmX5Q==", "TEzEs");
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

            public void run() {
                player.sendMessage(I["".length()]);
                player.sendMessage(I[" ".length()] + Bukkit.getPlayer((String)StringUtils.join((Object[])args)).getName());
                player.sendMessage(I["  ".length()]);
                Main.getInstance();
                if (Main.isCheating(Bukkit.getPlayer((String)StringUtils.join((Object[])args)).getPlayer())) {
                    player.sendMessage(I["   ".length()]);
                    Main.getInstance();
                    player.sendMessage(I[0x5F ^ 0x5B] + Main.lastCheck.get(Bukkit.getPlayer((String)StringUtils.join((Object[])args)).getUniqueId()));
                    "".length();
                    if (2 >= 4) {
                        throw null;
                    }
                } else {
                    player.sendMessage(I[0xA1 ^ 0xA4]);
                }
                player.sendMessage(I[0x7E ^ 0x78]);
            }
        }.runTaskLaterAsynchronously((Plugin)this.plugin, 60L);
        return " ".length() != 0;
    }

    static {
        CheckCommand.I();
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
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x26 ^ 0x2E), "DES");
            Cipher des = Cipher.getInstance("DES");
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
            if (3 != 4) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String[0x49 ^ 0x40];
        CheckCommand.I["".length()] = CheckCommand.I("Sb/hip81wdw816pzcYuKkg==", "KDZKT");
        CheckCommand.I[" ".length()] = CheckCommand.I("uCe8Fv/MdfK2ewUrRU186mUng31MHSCt", "DmsVw");
        CheckCommand.I["  ".length()] = CheckCommand.I("elsK5n5/zwMAHRWrM5cZi8v5MecVFEtP", "ivXwU");
        CheckCommand.I["   ".length()] = CheckCommand.l("d2tPo2Rr3YV5TcU6mZ6hzd87iqILKlCT", "HgnbY");
        CheckCommand.I[115 ^ 119] = CheckCommand.lI("Wu(\u0006#\u001e1n\u00068\u001c< \fx", "pUNiV");
        CheckCommand.I[40 ^ 45] = CheckCommand.lI("\u00d06\u000b\u0003\u001e\u0019>7\u0011P\u0014?<\u0015\u001bW87V", "wWYvp");
        CheckCommand.I[121 ^ 127] = CheckCommand.lI("w_e{", "WqKUr");
        CheckCommand.I[126 ^ 121] = CheckCommand.I("vWk5MPUXouE=", "zgXaV");
        CheckCommand.I[58 ^ 50] = CheckCommand.l("o6hqLDATWkc=", "KVfUM");
    }

}

