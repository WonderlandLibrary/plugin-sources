package com.elevatemc.anticheat.base.command.impl;

import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckBanCommand implements CommandExecutor {
    private final String headerFooter = CC.translate("&7&m-------------------------------");
    private final String logsColor = CC.translate("&d");
    private final String prefix = CC.translate("&c[&e⚠&c]");

    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("hood.admin")) {
            sender.sendMessage(CC.translate(this.prefix + "&cNo permission."));
            return true;
        }
        if (args.length != 2) {
            sender.sendMessage(CC.translate(this.prefix + " &cUsage: /checkban (name) (page)"));
            return true;
        }
        String player = args[0];
        int page = Integer.parseInt(args[1]);
        String path = HoodPlugin.INSTANCE.getPlugin().getDataFolder() + "/banlogs.txt";
        File file = new File(path);
        if (!file.exists()) {
            sender.sendMessage(CC.translate(this.prefix + " &cThere is no banlogs file!"));
            return true;
        }
        try {
            ArrayList<String> lines = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.contains(player)) continue;
                lines.add(line);
            }
            if (lines.isEmpty()) {
                sender.sendMessage(CC.translate(this.prefix + " &cThere were no logs for " + player + "!"));
                return true;
            }
            sender.sendMessage(CC.translate(this.headerFooter.replaceAll("%page%", Integer.toString(page))));
            for (int i = (page - 1) * 10; i < page * 10; ++i) {
                if (i >= lines.size()) continue;
                String[] spl = lines.get(i).split("banned for");
                sender.sendMessage(CC.translate("&r"));
                sender.sendMessage(CC.translate(this.logsColor + spl[1]));
            }
            sender.sendMessage(CC.translate(this.headerFooter.replaceAll("%page%", Integer.toString(page))));
            if (lines.size() / (page * 10) < 1) {
                sender.sendMessage(CC.translate(this.prefix + " &cThere is no page " + page + " for " + player + "!"));
                return true;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
}