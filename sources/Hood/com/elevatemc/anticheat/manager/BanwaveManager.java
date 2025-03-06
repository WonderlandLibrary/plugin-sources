package com.elevatemc.anticheat.manager;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.util.chat.CC;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class BanwaveManager extends BukkitRunnable {

    int countdown = 6;
    int cheatersAmount = 0;
    Deque<UUID> cheaters = new LinkedList<>();
    AtomicInteger num = new AtomicInteger();

    String[] reasons = {"moving heat",
            "using oxy",
            "blowing down the ally",
            "dealing",
            "counterfeiting",
            "committing arson",
            "committing assault",
            "doing insurance fraud",
            "kidnapping",
            "doing homicide",
            "robbing",
            "doing wire fraud",
            "doing white collar crimes",
            "shoplifting",
            "abusing medical marijuana",
            "trafficking people",
            "setting fire to aircrafts",
            "stalking",
            "abducting elderly",
            "allowing a dog to be a nuisance",
            "planning a terror attack",
            "avoiding court",
            "edating",
            "sellings strikers"
    };
    @Override
    public void run() {
        if (--this.countdown > 0) {
            broadcastMessage(CC.GRAY + "Cops gonna roll up in " + CC.RED + this.countdown + " second(s)...");
        } else if (this.countdown == 0) {
            broadcastMessage(CC.DARK_RED + "Cops have arrived on the scene...");
            this.countdown = -1;
        } else if (!cheaters.isEmpty()) {
            UUID id = loadNextCheater();
            String name = Bukkit.getOfflinePlayer(id).getName();

            Bukkit.getScheduler().runTask(Hood.get(),() -> Hood.get().getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + name + " perm [HOOD] Judgement Day."));
            broadcastMessage("&c" + name + " &7was caught &c" + reasons[num.getAndIncrement()]);
        } else {
            broadcastMessage("&m--------------------------------------------------");
            broadcastMessage("&c✘ &7The &cHood &7is no longer getting &cPatrolled.");
            broadcastMessage("&7A total of &c" + cheatersAmount + " &7gangstas were caught");
            broadcastMessage("&m--------------------------------------------------\n");
            deleteDocument();
            this.cancel();
        }
    }

    public void load() {
        MongoCollection<Document> collection = Hood.get().getLogHandler().getCollection("HoodBanwave");
        for (Document document : collection.find()) {
            UUID uuid = UUID.fromString(document.getString("uuid"));
            this.cheaters.add(uuid);
        }
        this.cheatersAmount = cheaters.size();
    }
    public UUID loadNextCheater() {
        return this.cheaters.poll();
    }

    public void deleteDocument() {
        Bukkit.getScheduler().runTaskAsynchronously(Hood.get(), () -> Hood.get().getLogHandler().getCollection("HoodBanwave").deleteMany(new Document()));
    }

    public void broadcastMessage(String message) {
        Bukkit.broadcastMessage(CC.translate(message));
    }
}
