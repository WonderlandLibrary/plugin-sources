/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Server
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandMap
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.command.SimpleCommandMap
 *  org.bukkit.entity.Player
 *  org.bukkit.event.HandlerList
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.java.JavaPlugin
 */
package com.unknownmyname.main;

import com.unknownmyname.check.Check;
import com.unknownmyname.cmd.AlertsCommand;
import com.unknownmyname.cmd.AntiCheatCommand;
import com.unknownmyname.cmd.CheckBanCommand;
import com.unknownmyname.cmd.CheckCommand;
import com.unknownmyname.cmd.DebugCommand;
import com.unknownmyname.cmd.LogsCommand;
import com.unknownmyname.listener.DataListener;
import com.unknownmyname.manager.CheckManager;
import com.unknownmyname.manager.DatabaseManager;
import com.unknownmyname.manager.GuiManager;
import com.unknownmyname.manager.OptionsManager;
import com.unknownmyname.manager.PlayerManager;
import com.unknownmyname.manager.PunishmentManager;
import com.unknownmyname.type.LoaderCheck;
import com.unknownmyname.util.SafeReflection;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
extends JavaPlugin
implements Listener {
    private static final /* synthetic */ String[] I;
    private static /* synthetic */ ArrayList<Player> cheating;
    private static /* synthetic */ Main plugin;
    private /* synthetic */ DataListener dataListener;
    private /* synthetic */ LoaderCheck typeLoader;
    private /* synthetic */ List<Command> commands;
    private /* synthetic */ SimpleCommandMap commandMap;
    public static /* synthetic */ Main instance;
    public static /* synthetic */ HashMap<UUID, String> lastCheck;

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

    public SimpleCommandMap getCommandMap() {
        return this.commandMap;
    }

    public static Main getInstance() {
        return instance;
    }

    private static void I() {
        I = new String[0x80 ^ 0x90];
        Main.I["".length()] = Main.I("aWVDNkJq03I=", "Omnmm");
        Main.I[" ".length()] = Main.I("/NOswiCAC8p+ybZbN4ye/8oGGg4zT9vJ9kM5QioPOp8SVChomKS1SPAxfdrHRPYliIcKaZUYxpM=", "QgAhO");
        Main.I["  ".length()] = Main.l("  \u0017\u001d\u000f)+\u0002\u0000", "ANctl");
        Main.I["   ".length()] = Main.I("qNM8MyrdBlw=", "EdTEi");
        Main.I[192 ^ 196] = Main.I("KUdDy3TANH8=", "IxWip");
        Main.I[137 ^ 140] = Main.lI("PYvWLGvhLqBdqvzVC9W+ug==", "rEwNv");
        Main.I[138 ^ 140] = Main.lI("/IOR/TzcrKU=", "fDESR");
        Main.I[47 ^ 40] = Main.I("E0hcMBa89F8=", "Zsokc");
        Main.I[123 ^ 115] = Main.l("\u00f7+- F -\u0011\"\u000f#;\n \bq", "PHcOf");
        Main.I[190 ^ 183] = Main.I("zxJLNizDCv7vVyeVkZo6wAq3GT5hStVWQQzcS7S9rooxmh6v7zqOA6LxPQjovpuvMWBsKtYeZm4qAtNpwhjgcA==", "ANVCi");
        Main.I[50 ^ 56] = Main.lI("kte3ITp0r2SRCfp00ZeY/Q==", "vxqPV");
        Main.I[98 ^ 105] = Main.I("IhGiQHh8nJlp/X4M+p1byw==", "rjpmZ");
        Main.I[159 ^ 147] = Main.lI("OknXVa7jKL5e3mBXUfMvAw==", "QCaER");
        Main.I[13 ^ 0] = Main.l("#\u000f'&?#\u0005", "FaFDS");
        Main.I[29 ^ 19] = Main.lI("x5U+/H/Fxyk=", "LkWAl");
        Main.I[130 ^ 141] = Main.I("8rBY5h9hwMT8COLGJArYSsTXBvFBqWrFXIiqXwAlochsR7jV/9bc9oejYv+zZHIAfGrgmAcNkhYTVHHq/FbRAw==", "WTZpf");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public void shutdown() {
        PlayerManager.disable();
        OptionsManager.getInstance().disable();
        CheckManager.getInstance().disable();
        DatabaseManager.getInstance().disable();
        PunishmentManager.getInstance().disable();
        this.unregisterCommands();
        this.unregisterListeners();
        plugin.getLogger().info(I[0x32 ^ 0x3D]);
        this.typeLoader.setCheckClasses(null);
    }

    public static boolean isCheating(Player p) {
        return Main.listCheat().contains((Object)p);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void unregisterCommands() {
        knownCommands = SafeReflection.getKnownCommands(this.commandMap);
        knownCommands.values().removeAll(this.commands);
        var3_2 = this.commands.iterator();
        "".length();
        if (1 < 4) ** GOTO lbl12
        throw null;
lbl-1000: // 1 sources:
        {
            command = var3_2.next();
            command.unregister((CommandMap)this.commandMap);
lbl12: // 2 sources:
            ** while (var3_2.hasNext())
        }
lbl13: // 1 sources:
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
            if (!false) continue;
            throw null;
        }
        return sb.toString();
    }

    public Main() {
        lastCheck = new HashMap();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onEnable() {
        Main.plugin = this;
        this.typeLoader = new LoaderCheck();
        Main.instance = this;
        PlayerManager.enable(this);
        OptionsManager.getInstance().enable();
        CheckManager.getInstance().enable();
        GuiManager.getInstance().enable();
        if (this.getServer().getPluginManager().getPlugin(Main.I["".length()]) != null) {
            DatabaseManager.getInstance().enable();
            "".length();
            if (4 == 2) {
                throw null;
            }
        } else {
            Main.plugin.getLogger().info(Main.I[" ".length()]);
        }
        PunishmentManager.getInstance().enable();
        this.registerListeners();
        this.getCommand(Main.I["  ".length()]).setExecutor((CommandExecutor)new AntiCheatCommand((Plugin)this));
        this.getCommand(Main.I["   ".length()]).setExecutor((CommandExecutor)new AlertsCommand());
        this.getCommand(Main.I[88 ^ 92]).setExecutor((CommandExecutor)new LogsCommand());
        this.getCommand(Main.I[100 ^ 97]).setExecutor((CommandExecutor)new DebugCommand());
        this.getCommand(Main.I[106 ^ 108]).setExecutor((CommandExecutor)new CheckBanCommand());
        this.getCommand(Main.I[29 ^ 26]).setExecutor((CommandExecutor)new CheckCommand(this));
        map = this.getDescription().getCommands();
        var3_2 = map.entrySet().iterator();
        "".length();
        if (4 == 4) ** GOTO lbl35
        throw null;
lbl-1000: // 1 sources:
        {
            entry = var3_2.next();
            command = this.getCommand((String)entry.getKey());
            command.setPermission(OptionsManager.getInstance().getModPermission());
            command.setPermissionMessage(Main.I[0 ^ 8]);
lbl35: // 2 sources:
            ** while (var3_2.hasNext())
        }
lbl36: // 1 sources:
        Main.plugin.getLogger().info(Main.I[201 ^ 192]);
        var5_7 = Check.CheckType.values();
        command = var5_7.length;
        var3_3 = "".length();
        "".length();
        if (-1 < 2) ** GOTO lbl56
        throw null;
lbl-1000: // 1 sources:
        {
            type = var5_7[var3_3];
            enabled = CheckManager.getInstance().enabled(type);
            if (!enabled) {
                v0 = Main.I[21 ^ 25];
                "".length();
                if (3 <= 1) {
                    throw null;
                }
            } else {
                v0 = Main.I[14 ^ 3];
            }
            Main.plugin.getLogger().info(Main.I[84 ^ 94] + type.getName() + Main.I[128 ^ 139] + v0 + Main.I[153 ^ 151]);
            ++var3_3;
lbl56: // 2 sources:
            ** while (var3_3 < command)
        }
lbl57: // 1 sources:
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    static {
        Main.I();
        cheating = new ArrayList();
    }

    public LoaderCheck getTypeLoader() {
        return this.typeLoader;
    }

    public void registerListeners() {
        this.dataListener = new DataListener();
        this.getServer().getPluginManager().registerEvents((Listener)this.dataListener, (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    public DataListener getDataListener() {
        return this.dataListener;
    }

    public static ArrayList<Player> listCheat() {
        return cheating;
    }

    public void unregisterListeners() {
        HandlerList.unregisterAll((Listener)this.dataListener);
        HandlerList.unregisterAll((Plugin)Main.getPlugin());
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x95 ^ 0x9D), "DES");
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

