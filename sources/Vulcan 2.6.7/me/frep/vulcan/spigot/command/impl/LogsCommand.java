package me.frep.vulcan.spigot.command.impl;

import java.io.FileNotFoundException;
import me.frep.vulcan.spigot.util.ColorUtil;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import org.bukkit.command.CommandExecutor;
import me.frep.vulcan.spigot.command.CommandManager;

public class LogsCommand extends CommandManager implements CommandExecutor
{
    private final ExecutorService logsExecutor;
    
    public LogsCommand() {
        this.logsExecutor = Executors.newSingleThreadExecutor();
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (!sender.hasPermission("vulcan.logs")) {
            this.sendMessage(sender, Config.NO_PERMISSION);
            return true;
        }
        if (args.length != 2) {
            switch (args.length) {
                case 0: {
                    this.sendMessage(sender, Config.LOGS_COMMAND_SYNTAX);
                    break;
                }
                case 1: {
                    if (sender instanceof Player) {
                        final Player player = (Player)sender;
                        player.performCommand("logs " + args[0] + " 1");
                        break;
                    }
                    this.sendMessage(sender, Config.LOGS_COMMAND_SYNTAX);
                    break;
                }
            }
            return true;
        }
        this.logsExecutor.execute(() -> {
            final String playerName = args[0];
            final int page = Integer.parseInt(args[1]);
            final String path = Vulcan.INSTANCE.getPlugin().getDataFolder() + "/violations.txt";
            final File file = new File(path);
            if (!file.exists()) {
                this.sendMessage(sender, Config.NO_LOGS_FILE);
                return;
            }
            else {
                try {
                    final ArrayList lines = new ArrayList<String>();
                    final Scanner scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        final String line = scanner.nextLine();
                        if (line.toLowerCase().contains(playerName.toLowerCase())) {
                            lines.add(line);
                        }
                    }
                    Collections.reverse(lines);
                    if (lines.isEmpty()) {
                        this.sendMessage(sender, Config.LOGS_COMMAND_NO_LOGS.replaceAll("%player%", playerName));
                    }
                    else {
                        final int maxPages = (int)Math.ceil(lines.size() / 10.0);
                        if (page > maxPages) {
                            this.sendMessage(sender, Config.NO_PAGE.replaceAll("%page%", Integer.toString(page)).replaceAll("%player%", playerName));
                        }
                        this.sendMessage(sender, Config.LOGS_COMMAND_HEADER_FOOTER.replaceAll("%player%", playerName).replaceAll("%page%", Integer.toString(page)).replaceAll("%max-pages%", Integer.toString(maxPages)));
                        int blank = 0;
                        for (int i = (page - 1) * 10; i < page * 10; ++i) {
                            if (i < lines.size()) {
                                if (blank > 0) {
                                    sender.sendMessage(ColorUtil.translate("&r"));
                                }
                                sender.sendMessage(ColorUtil.translate(Config.LOGS_COMMAND_COLOR + (String)lines.get(i)));
                                ++blank;
                            }
                        }
                        this.sendMessage(sender, Config.LOGS_COMMAND_HEADER_FOOTER.replaceAll("%player%", playerName).replaceAll("%page%", Integer.toString(page)).replaceAll("%max-pages%", Integer.toString(maxPages)));
                    }
                }
                catch (final FileNotFoundException exception) {
                    exception.printStackTrace();
                }
                return;
            }
        });
        return true;
    }
    
    public static String spigot() {
        return "%%__USER__%%";
    }
    
    public static String nonce() {
        return "%%__NONCE__%%";
    }
    
    public static String resource() {
        return "%%__RESOURCE__%%";
    }
}
