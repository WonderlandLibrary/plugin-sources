package com.elevatemc.anticheat;

import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import com.elevatemc.anticheat.base.command.impl.*;
import com.elevatemc.anticheat.base.listener.packetevents.PacketEventsPacketListener;
import com.elevatemc.anticheat.base.listener.player.ClientBrandListener;
import com.elevatemc.anticheat.base.listener.player.PlayerListener;
import com.elevatemc.anticheat.base.listener.server.BanBroadcaster;
import com.elevatemc.anticheat.database.task.ExportLogsTask;
import com.elevatemc.anticheat.manager.*;
import com.elevatemc.anticheat.util.config.Configuration;
import com.elevatemc.anticheat.util.config.ConfigurationService;
import com.elevatemc.anticheat.util.task.ServerTickTask;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public enum HoodPlugin {

    INSTANCE;

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Getter
    private Hood plugin;
    private Configuration configuration = new Configuration();
    private ServerTickTask tickManager;
    private BanwaveManager banwaveManager;
    private PlayerDataManager playerDataManager;
    private AlertManager alertManager;
    private PluginManager pluginManager;
    private PaperCommandManager paperCommandManager;
    private GuiManager guiManager;
    private ExecutorService packetExecutor;
    private CheckManager checkManager;
    private ConfigurationService configurationService;
    private ai.rippedthisofsomeguy.gui.GuiManager guiManager2;
    private ExportLogsTask exportLogsTask;
    private BanBroadcaster banBroadcaster;

    public void start(final Hood plugin) {
        this.plugin = plugin;

        assert plugin != null : "Error while starting Hood!";

        getPlugin().saveDefaultConfig();
        registerConfig();
        this.tickManager = new ServerTickTask();
        this.banwaveManager = new BanwaveManager();
        this.playerDataManager = new PlayerDataManager();
        this.alertManager = new AlertManager();
        this.pluginManager = Bukkit.getPluginManager();
        this.paperCommandManager = new PaperCommandManager(plugin);
        this.guiManager = new GuiManager();
        this.packetExecutor = Executors.newSingleThreadExecutor();
        this.checkManager = new CheckManager();
        this.configurationService = new ConfigurationService();
        this.guiManager2 = new ai.rippedthisofsomeguy.gui.GuiManager(plugin);
        this.exportLogsTask = new ExportLogsTask();
        this.banBroadcaster = new BanBroadcaster();

        // Register Commands
        this.paperCommandManager.enableUnstableAPI("help");

        this.paperCommandManager.setFormat(MessageType.ERROR, ChatColor.DARK_RED, ChatColor.DARK_RED, ChatColor.RED);
        this.paperCommandManager.setFormat(MessageType.INFO, ChatColor.RED, ChatColor.RED, ChatColor.RED);
        this.paperCommandManager.setFormat(MessageType.HELP, ChatColor.RED, ChatColor.RED, ChatColor.GRAY);
        this.paperCommandManager.setFormat(MessageType.SYNTAX, ChatColor.RED, ChatColor.RED, ChatColor.RED);

        getPlugin().getCommand("logs").setExecutor(new LogsCommand());
        getPlugin().getCommand("banwave").setExecutor(new BanwaveCommand());
        getPlugin().getCommand("retard").setExecutor(new RetardCommand());
        getPlugin().getCommand("checkban").setExecutor(new CheckBanCommand());

        Collections.singletonList(new HoodCommands()).forEach(wardenCommands -> {
            this.paperCommandManager.registerCommand(wardenCommands);
        });

        tickManager.runTaskTimer(plugin, 0L, 1L);
        banBroadcaster.runTaskTimerAsynchronously(plugin, 0L, 20 * 60 * 60);
        exportLogsTask.start();


        Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "MC|Brand", new ClientBrandListener());

        pluginManager.registerEvents(new PlayerListener(), plugin);

        PacketEvents.getAPI().getEventManager().registerListener(new PacketEventsPacketListener(PacketListenerPriority.LOWEST));
        PacketEvents.getAPI().init();
    }

    public void stop(final Hood plugin) {
        this.plugin = plugin;

        assert plugin != null : "Error while shutting down Hood!";

        tickManager.cancel();
        exportLogsTask.stop();
        Hood.get().getLogHandler().closeMongo();
    }

    public void reload() {
        HoodPlugin.INSTANCE.getPlugin().reloadConfig();
    }

    public void registerConfig() {
        // Register Config.
        File configFile = new File(getPlugin().getDataFolder(), "config.json");

        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();

                try (FileWriter writer = new FileWriter(configFile)) {
                    GSON.toJson(configuration = new Configuration(), writer);
                }
            }

            try (FileReader reader = new FileReader(configFile)) {
                configuration = GSON.fromJson(reader, Configuration.class);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
