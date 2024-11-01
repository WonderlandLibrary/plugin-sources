package com.elevatemc.anticheat.base.listener.server;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BanBroadcaster extends BukkitRunnable {
    private int lastCount;
    @Override
    public void run() {
        File banFile = new File(Hood.get().getDataFolder(), "banlogs.txt");
        int banCount = countLines(banFile);
        if(this.lastCount == banCount) {
            return;
        }
        this.lastCount = banCount;
        Bukkit.getScheduler().runTask(Hood.get(), () -> {
            broadcast("&7&m----&cAnti-Cheat Announcement&7&m---- \n" +
                    "&r   \n" +
                    "&eSince the start of &cSeason XII \n" +
                    "&eA Total of &c" + banCount + "&eCheaters have been caught \n" +
                    "&r    \n" +
                    "&oStay legit... \n" +
                    "&r    \n" +
                    "&7&m----&cAnti-Cheat Announcement&7&m----");
        });

    }

    private int countLines(File file) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(CC.translate(message));
    }
}
