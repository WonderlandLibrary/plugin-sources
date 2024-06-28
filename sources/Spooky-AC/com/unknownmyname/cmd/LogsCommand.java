/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitScheduler
 */
package com.unknownmyname.cmd;

import com.unknownmyname.main.Main;
import com.unknownmyname.manager.OptionsManager;
import com.unknownmyname.util.BukkitUtils;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class LogsCommand
implements TabCompleter,
CommandExecutor {
    private static final /* synthetic */ String[] I;

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 9 ^ 1), "DES");
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

    private static void I() {
        I = new String[0x96 ^ 0x91];
        LogsCommand.I["".length()] = LogsCommand.I("pkH0Mef461i0vpUyBCdJJ574gMrZdwEkoYsex6N+31cHHiHrTSxTCw==", "djUxf");
        LogsCommand.I[" ".length()] = LogsCommand.l("Go3v+D13SJmUbEG01Hu+enivjWsVG/c3", "VUoiL");
        LogsCommand.I["  ".length()] = LogsCommand.lI("Y\b\u0016\u001d", "wdyzu");
        LogsCommand.I["   ".length()] = LogsCommand.l("jdHDK3ZSLCQ=", "CqhIV");
        LogsCommand.I[116 ^ 112] = LogsCommand.I("s1otBdjBG82b85OTy5ihg/jYBf3R72QyWy2wksgDM2vCJHr9KBqMgA==", "TwQYJ");
        LogsCommand.I[43 ^ 46] = LogsCommand.lI("\u0019?\u0014+", "uPsXm");
        LogsCommand.I[14 ^ 8] = LogsCommand.lI("", "LpQEw");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private HashMap<String, Integer> getHighestEntry(HashMap<String, Integer> entries, String exclusions) {
        returnEntry = new HashMap<String, Integer>();
        highestEntry = "".length();
        entry = null;
        var7_6 = entries.keySet().iterator();
        "".length();
        if (-1 < 2) ** GOTO lbl13
        throw null;
lbl-1000: // 1 sources:
        {
            s = var7_6.next();
            if (exclusions.contains(s) || entries.get(s) <= highestEntry) continue;
            highestEntry = entries.get(s);
            entry = s;
lbl13: // 3 sources:
            ** while (var7_6.hasNext())
        }
lbl14: // 1 sources:
        if (entry == null) return null;
        if (highestEntry == 0) {
            return null;
        }
        returnEntry.put(entry, highestEntry);
        return returnEntry;
    }

    static {
        LogsCommand.I();
    }

    public boolean onCommand(final CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(I["".length()]);
            return "".length() != 0;
        }
        if (args.length == " ".length()) {
            final String playerCheck = args["".length()].toLowerCase();
            final String latestLogs = BukkitUtils.getFromFile(I[" ".length()] + playerCheck + I["  ".length()]);
            if (latestLogs.equals(I["   ".length()])) {
                sender.sendMessage(I[0xB2 ^ 0xB6]);
                "".length();
                if (3 <= -1) {
                    throw null;
                }
            } else {
                Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask((Plugin)Main.getInstance(), new Runnable(){
                    private static final /* synthetic */ String[] I;

                    static {
                        1.I();
                    }

                    /*
                     * Unable to fully structure code
                     * Enabled aggressive block sorting
                     * Lifted jumps to return sites
                     */
                    @Override
                    public void run() {
                        sender.sendMessage(OptionsManager.getInstance().getLogsSepearted().replace((char)(8 ^ 46), 72 + 5 - 25 + 115));
                        sender.sendMessage(OptionsManager.getInstance().getPlayerLogs().replace((char)(118 ^ 80), 161 + 81 - 81 + 6).replace(1.I["".length()], Bukkit.getPlayer((String)playerCheck).getName()));
                        flags = new HashMap<String, Integer>();
                        split = latestLogs.split(1.I[" ".length()]);
                        length = split.length;
                        i = "".length();
                        "".length();
                        if (-1 != 4) ** GOTO lbl24
                        throw null;
lbl-1000: // 1 sources:
                        {
                            s = split[i];
                            hackName = s.split(1.I["  ".length()])[" ".length()];
                            if (flags.containsKey(hackName)) {
                                flags.replace((String)hackName, (Integer)flags.get(hackName) + " ".length());
                                "".length();
                                if (false) {
                                    throw null;
                                }
                            } else {
                                flags.put((String)hackName, " ".length());
                            }
                            ++i;
lbl24: // 2 sources:
                            ** while (i < length)
                        }
lbl25: // 1 sources:
                        displayed = 1.I["   ".length()];
                        highest = LogsCommand.access$0(LogsCommand.this, flags, displayed);
                        "".length();
                        if (4 > 3) ** GOTO lbl41
                        throw null;
lbl-1000: // 1 sources:
                        {
                            hackName = highest.keySet().iterator();
                            "".length();
                            if (-1 == -1) ** GOTO lbl39
                            throw null;
lbl-1000: // 1 sources:
                            {
                                s2 = (String)hackName.next();
                                sender.sendMessage(OptionsManager.getInstance().getPlayerLogsResult().replace((char)(152 ^ 190), 123 + 141 - 236 + 139).replace(1.I[155 ^ 159], s2).replace(1.I[151 ^ 146], ((Integer)highest.get(s2)).toString()));
                                displayed = String.valueOf(String.valueOf(String.valueOf(String.valueOf(displayed)))) + s2;
lbl39: // 2 sources:
                                ** while (hackName.hasNext())
                            }
lbl40: // 1 sources:
                            highest = LogsCommand.access$0(LogsCommand.this, flags, displayed);
lbl41: // 2 sources:
                            ** while (highest != null)
                        }
lbl42: // 1 sources:
                        sender.sendMessage(OptionsManager.getInstance().getLogsSepearted().replace((char)(86 ^ 112), 100 + 155 - 107 + 19));
                    }

                    private static String l(String obj, String key) {
                        try {
                            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xCB ^ 0xC3), "DES");
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
                            if (-1 == -1) continue;
                            throw null;
                        }
                        return sb.toString();
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

                    private static void I() {
                        I = new String[0x5B ^ 0x5D];
                        1.I["".length()] = 1.I("\u0001=/&4\u001f?>", "zMCGM");
                        1.I[" ".length()] = 1.l("s2beu0SlK+U=", "slGnS");
                        1.I["  ".length()] = 1.lI("OUQ5oJQP0gs=", "ohVWn");
                        1.I["   ".length()] = 1.l("y8P0uhTeivU=", "GCGuC");
                        1.I[175 ^ 171] = 1.I(")\u000f*\":9\u0011", "RlBGY");
                        1.I[114 ^ 119] = 1.l("c7iUXk83THU=", "ZFajz");
                    }
                });
            }
        }
        return "".length() != 0;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        block8 : {
            block7 : {
                if (cmd.getName().equalsIgnoreCase(LogsCommand.I[4 ^ 1]) == false) return null;
                if (args.length != " ".length()) return null;
                playerName = new ArrayList<String>();
                if (args["".length()].equals(LogsCommand.I[169 ^ 175])) break block7;
                var7_6 = Bukkit.getOnlinePlayers().iterator();
                "".length();
                if (3 >= 4) {
                    throw null;
                }
                ** GOTO lbl21
            }
            var7_7 = Bukkit.getOnlinePlayers().iterator();
            "".length();
            if (-1 < 0) ** GOTO lbl30
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
            if (1 >= -1) break block8;
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

    static /* synthetic */ HashMap access$0(LogsCommand logsCommand, HashMap hashMap, String string) {
        return logsCommand.getHighestEntry(hashMap, string);
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
            if (4 > 1) continue;
            throw null;
        }
        return sb.toString();
    }

}

