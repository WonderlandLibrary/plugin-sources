package me.frep.vulcan.spigot.command.impl;

import java.util.Iterator;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import org.bukkit.command.CommandExecutor;
import me.frep.vulcan.spigot.command.CommandManager;

public class PunishLogs extends CommandManager implements CommandExecutor
{
    private final ExecutorService logsExecutor;
    
    public PunishLogs() {
        this.logsExecutor = Executors.newSingleThreadExecutor();
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (!sender.hasPermission("vulcan.punishlogs")) {
            this.sendMessage(sender, Config.NO_PERMISSION);
            return true;
        }
        if (args.length < 1) {
            this.sendMessage(sender, Config.PUNISH_LOGS_SYNTAX);
            return true;
        }
        this.logsExecutor.execute(() -> {
            final String playerName = args[0];
            final String path = Vulcan.INSTANCE.getPlugin().getDataFolder() + "/punishments.txt";
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
                        if (line.toLowerCase().contains(playerName.toLowerCase()) && line.toLowerCase().contains("was punished")) {
                            lines.add(line);
                        }
                    }
                    final ArrayList<String> formatted = new ArrayList<String>();
                    final Iterator<String> iterator = lines.iterator();
                    while (iterator.hasNext()) {
                        final String string = iterator.next();
                        final String replacement = string.replaceAll("-", "").replaceAll("\\[", "").replaceAll("\\]", "");
                        formatted.add(replacement);
                    }
                    Collections.reverse(formatted);
                    int i = 1;
                    if (lines.isEmpty()) {
                        this.sendMessage(sender, Config.NO_LOGS);
                    }
                    else {
                        this.sendMessage(sender, Config.PUNISH_LOGS_HEADER_FOOTER.replaceAll("%player%", args[0]));
                        final Iterator<String> iterator2 = formatted.iterator();
                        while (iterator2.hasNext()) {
                            final String string2 = iterator2.next();
                            this.sendMessage(sender, " &8- &7" + i + ".)" + string2);
                            ++i;
                        }
                        this.sendMessage(sender, Config.PUNISH_LOGS_HEADER_FOOTER.replaceAll("%player%", args[0]));
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
}
