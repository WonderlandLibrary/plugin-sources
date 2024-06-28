/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Server
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 */
package com.unknownmyname.cmd;

import com.unknownmyname.gui.impl.MainGui;
import com.unknownmyname.main.Main;
import com.unknownmyname.manager.GuiManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class AntiCheatCommand
implements CommandExecutor {
    private /* synthetic */ Plugin plugin;
    private static final /* synthetic */ String[] I;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        int passed = "".length();
        if (args.length == " ".length()) {
            if (!(commandSender instanceof Player)) {
                this.sendSpookyACMessage(commandSender);
                passed = " ".length();
            }
            Player player = (Player)commandSender;
            if (args["".length()].equalsIgnoreCase(I["".length()])) {
                this.sendSpookyACMessage(commandSender);
                passed = " ".length();
                "".length();
                if (-1 >= 4) {
                    throw null;
                }
            } else if (args["".length()].equalsIgnoreCase(I[" ".length()])) {
                if (player.isOp()) {
                    GuiManager.getInstance().getMainGui().openGui(player);
                }
                passed = " ".length();
            }
        }
        if (passed == 0) {
            this.sendHelpMessage(commandSender);
        }
        return " ".length() != 0;
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
            if (2 != 1) continue;
            throw null;
        }
        return sb.toString();
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

    static {
        AntiCheatCommand.I();
    }

    public AntiCheatCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    private void sendHelpMessage(CommandSender commandSender) {
        commandSender.sendMessage(I["  ".length()]);
        commandSender.sendMessage((Object)ChatColor.GRAY + I["   ".length()] + I[0x27 ^ 0x23] + I[0x8A ^ 0x8F]);
        commandSender.sendMessage((Object)ChatColor.GRAY + I[0x2D ^ 0x2B] + I[0xC1 ^ 0xC6] + I[0x88 ^ 0x80]);
        if (Main.getInstance().getServer().getPluginManager().getPlugin(I[0xCB ^ 0xC2]) != null) {
            commandSender.sendMessage((Object)ChatColor.GRAY + I[0x8A ^ 0x80] + I[0xB4 ^ 0xBF] + I[0x92 ^ 0x9E]);
        }
    }

    private void sendSpookyACMessage(CommandSender commandSender) {
        commandSender.sendMessage(I[0x22 ^ 0x2F]);
    }

    private static void I() {
        I = new String[0x67 ^ 0x69];
        AntiCheatCommand.I["".length()] = AntiCheatCommand.I("7L/JsgNpwq8=", "LBcqs");
        AntiCheatCommand.I[" ".length()] = AntiCheatCommand.l("e/yQh/w+Vb0=", "jHNdp");
        AntiCheatCommand.I["  ".length()] = AntiCheatCommand.l("j9Xqfcw3r56WTsgPbH6mbO9CR4Ndyt5e4uqJYRaRL+Kknk/8PRG3JYF2TcIzq2TIIxJgZd7G5sEi3pn8prApotyLR4+iacC6LTTFj3EIMaSX0FM29j45jw==", "fzCTl");
        AntiCheatCommand.I["   ".length()] = AntiCheatCommand.I("+GE09vlb3No=", "vXqmz");
        AntiCheatCommand.I[25 ^ 29] = AntiCheatCommand.lI("\u00dfZ", "xcsjK");
        AntiCheatCommand.I[81 ^ 84] = AntiCheatCommand.l("/xGL1tDV5jNOhjB40wYW6sYJj7IY1Hj5/ykcVuYEjoLPSb7DGO3BwFQPbT4nzSkf", "gXlJL");
        AntiCheatCommand.I[131 ^ 133] = AntiCheatCommand.l("m+r7mdsMSEk=", "cuCqw");
        AntiCheatCommand.I[113 ^ 118] = AntiCheatCommand.lI("\u00f3X", "TaMWt");
        AntiCheatCommand.I[96 ^ 104] = AntiCheatCommand.I("N8Hrx2KRz2EEqObjIkeF5q0e4cq1qTOSI6Qu5lF+28hLS2JD8/afVg==", "Swlzb");
        AntiCheatCommand.I[152 ^ 145] = AntiCheatCommand.l("jAzY05ateIk=", "ECtDK");
        AntiCheatCommand.I[31 ^ 21] = AntiCheatCommand.I("TmvQjiQdOmE=", "azPcO");
        AntiCheatCommand.I[114 ^ 121] = AntiCheatCommand.l("YElH7Du+1jQ=", "ogHvV");
        AntiCheatCommand.I[154 ^ 150] = AntiCheatCommand.l("SQk3pt3suS1Ku5DwRFn7c2gmhiBp7JJrh6eHuTlDowEezPTcTWLftp81sDIz7C8P", "zMeOp");
        AntiCheatCommand.I[29 ^ 16] = AntiCheatCommand.l("CsETj/AQ1VNeLJL1/nruG30OCUIuMcz4PCKQiJJaKogM8q/8WZcT1P8vyG6Jjk/Y5FKCn9J6EgZ9L3g6Rl4w60l2FpJ9O610jXQxt8MijrGTuEq2R/2laQ==", "Gmzsd");
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xBF ^ 0xB7), "DES");
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

