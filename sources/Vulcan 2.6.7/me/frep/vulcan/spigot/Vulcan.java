package me.frep.vulcan.spigot;

import java.util.Objects;
import java.util.logging.Level;
import me.frep.vulcan.spigot.util.CacheUtil;
import me.frep.vulcan.spigot.hook.placeholderapi.PlaceholderAPIExtension;
import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import me.frep.vulcan.spigot.network.NetworkManager;
import io.github.retrooper.packetevents.event.priority.PacketEventPriority;
import io.github.retrooper.packetevents.PacketEvents;
import me.frep.vulcan.spigot.event.EventProcessor1_13;
import me.frep.vulcan.spigot.event.EventProcessor1_11;
import me.frep.vulcan.spigot.hook.brewery.BreweryHook;
import me.frep.vulcan.spigot.hook.gsit.GSitListener;
import me.frep.vulcan.spigot.hook.mythicmobs.MythicMobsHook;
import me.frep.vulcan.spigot.hook.crackshot.CrackShotHook;
import me.frep.vulcan.spigot.hook.mcmmo.McMMOHook;
import me.frep.vulcan.spigot.event.EventProcessor;
import org.bukkit.event.Listener;
import me.frep.vulcan.spigot.listener.PlayerListener;
import org.bukkit.plugin.messaging.PluginMessageListener;
import me.frep.vulcan.spigot.brand.ClientBrandListener;
import me.frep.vulcan.spigot.bukkit.Metrics;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.command.impl.LogsCommand;
import me.frep.vulcan.spigot.command.impl.JDayCommand;
import org.bukkit.command.TabCompleter;
import me.frep.vulcan.spigot.command.tab.VulcanTabCompleter;
import me.frep.vulcan.spigot.command.impl.VulcanCommand;
import me.frep.vulcan.spigot.command.impl.AlertsCommand;
import me.frep.vulcan.spigot.command.impl.VerboseCommand;
import org.bukkit.command.CommandExecutor;
import me.frep.vulcan.spigot.command.impl.PunishLogs;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.check.manager.CheckManager;
import me.frep.vulcan.spigot.config.Stats;
import me.frep.vulcan.spigot.config.Config;
import java.util.concurrent.Executors;
import java.util.HashMap;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.id.ID;
import me.frep.vulcan.spigot.util.reflection.manager.ReflectionManager;
import java.util.concurrent.ExecutorService;
import me.frep.vulcan.spigot.gui.manager.GUIManager;
import me.frep.vulcan.spigot.punishment.PunishmentManager;
import me.frep.vulcan.spigot.alert.AlertManager;
import me.frep.vulcan.spigot.data.manager.PlayerDataManager;
import me.frep.vulcan.spigot.packet.processor.SendingPacketProcessor;
import me.frep.vulcan.spigot.packet.processor.ReceivingPacketProcessor;
import org.bukkit.plugin.PluginManager;
import me.frep.vulcan.spigot.reset.ResetManager;
import me.frep.vulcan.spigot.brand.ClientBrandManager;
import me.frep.vulcan.spigot.tick.TickManager;
import java.util.Map;
import java.util.logging.Logger;
import me.frep.vulcan.spigot.hook.discord.DiscordHelper;

public enum Vulcan
{
    INSTANCE;
    
    private VulcanPlugin plugin;
    private DiscordHelper discordHelper;
    private long startTime;
    private final String spigot;
    private final String nonce;
    private final String resource;
    private final Logger logger;
    private boolean floodgate1;
    private boolean floodgate2;
    private boolean placeHolderAPI;
    private boolean mcMMO;
    private boolean crackShot;
    private boolean libsDisguises;
    private boolean mythicMobs;
    private boolean gSit;
    private boolean brewery;
    private boolean testServer;
    private boolean mythicMobsLatest;
    private final Map<Integer, Integer> fishingRodPulledBoats;
    private final TickManager tickManager;
    private final ClientBrandManager clientBrandManager;
    private final ResetManager resetManager;
    private final PluginManager pluginManager;
    private final ReceivingPacketProcessor receivingPacketProcessor;
    private final SendingPacketProcessor sendingPacketProcessor;
    private final PlayerDataManager playerDataManager;
    private AlertManager alertManager;
    private final PunishmentManager punishmentManager;
    private final GUIManager guiManager;
    private final ExecutorService judgementExecutor;
    private final ExecutorService alertExecutor;
    private final ExecutorService packetExecutor;
    private final ExecutorService logExecutor;
    private final ReflectionManager reflectionManager;
    private final boolean bypass = true;
    
    private Vulcan() {
        this.spigot = ID.spigot();
        this.nonce = ID.nonce();
        this.resource = ID.resource();
        this.logger = Bukkit.getLogger();
        this.fishingRodPulledBoats = new HashMap<Integer, Integer>();
        this.tickManager = new TickManager();
        this.clientBrandManager = new ClientBrandManager();
        this.resetManager = new ResetManager();
        this.pluginManager = Bukkit.getServer().getPluginManager();
        this.receivingPacketProcessor = new ReceivingPacketProcessor();
        this.sendingPacketProcessor = new SendingPacketProcessor();
        this.playerDataManager = new PlayerDataManager();
        this.punishmentManager = new PunishmentManager();
        this.guiManager = new GUIManager();
        this.judgementExecutor = Executors.newSingleThreadExecutor();
        this.alertExecutor = Executors.newSingleThreadExecutor();
        this.packetExecutor = Executors.newSingleThreadExecutor();
        this.logExecutor = Executors.newSingleThreadExecutor();
        this.reflectionManager = new ReflectionManager();
    }
    
    public void start(final VulcanPlugin plugin) {
        this.plugin = plugin;
        assert plugin != null : "Error while starting Vulcan!";
        this.alertManager = new AlertManager();
        this.getPlugin().saveDefaultConfig();
        Config.initialize();
        Config.updateConfig();
        Stats.initialize();
        CheckManager.setup();
        this.log("Server Version: " + ServerUtil.getServerVersion().toString().replaceAll("_", ".").replaceAll("v", "").replaceFirst(".", "") + " detected!");
        if (this.pluginManager.getPlugin("floodgate-bukkit") != null) {
            this.floodgate1 = true;
            this.log("Floodgate 1.0 found. Enabling hook!");
        }
        if (this.pluginManager.getPlugin("floodgate") != null) {
            this.floodgate2 = true;
            this.log("Floodgate 2.0 found. Enabling hook!");
        }
        if (this.pluginManager.getPlugin("mcMMO") != null) {
            this.mcMMO = true;
            this.log("mcMMO found. Enabling hook!");
        }
        if (this.pluginManager.getPlugin("Crackshot") != null) {
            this.crackShot = true;
        }
        if (this.pluginManager.getPlugin("LibsDisguises") != null) {
            this.libsDisguises = true;
            this.log("LibsDisguises found. Enabling hook!");
        }
        if (this.pluginManager.getPlugin("MythicMobs") != null) {
            final Plugin mythicMobsPlugin = Bukkit.getPluginManager().getPlugin("MythicMobs");
            this.mythicMobsLatest = mythicMobsPlugin.getDescription().getVersion().startsWith("5");
            this.mythicMobs = true;
            this.log("MythicMobs found. Enabling hook!");
        }
        if (this.pluginManager.getPlugin("GSit") != null) {
            this.gSit = true;
            this.log("GSit found. Enabling hook!");
        }
        if (this.pluginManager.getPlugin("Brewery") != null) {
            this.brewery = true;
            this.log("Brewery found. Enabling hook!");
        }
        this.getPlugin().getCommand("punishlogs").setExecutor(new PunishLogs());
        this.getPlugin().getCommand("verbose").setExecutor(new VerboseCommand());
        this.getPlugin().getCommand("alerts").setExecutor(new AlertsCommand());
        this.getPlugin().getCommand("vulcan").setExecutor(new VulcanCommand());
        this.getPlugin().getCommand("vulcan").setTabCompleter(new VulcanTabCompleter());
        this.getPlugin().getCommand("jday").setExecutor(new JDayCommand());
        this.getPlugin().getCommand("logs").setExecutor(new LogsCommand());
        this.getPlugin().getServer().getMessenger().registerOutgoingPluginChannel(plugin, "vulcan:bungee");
        if (Config.BSTATS) {
            final Metrics metrics = new Metrics(plugin, 10297);
            this.log("BStats enabled!");
        }
        this.tickManager.start();
        this.resetManager.start();
        if (Config.CLIENT_BRAND_RESOLVE) {
            if (ServerUtil.isHigherThan1_13()) {
                Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "minecraft:brand", new ClientBrandListener());
            }
            else {
                Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "MC|Brand", new ClientBrandListener());
            }
        }
        this.startTime = System.currentTimeMillis();
        this.pluginManager.registerEvents(new PlayerListener(), plugin);
        this.pluginManager.registerEvents(new EventProcessor(), plugin);
        this.pluginManager.registerEvents(new GUIManager(), plugin);
        if (this.mcMMO && Config.HOOK_MCMMO) {
            this.pluginManager.registerEvents(new McMMOHook(), plugin);
            this.log("Registered mcMMO listener!");
        }
        if (this.crackShot) {
            this.pluginManager.registerEvents(new CrackShotHook(), plugin);
            this.log("Registered CrackShot listener!");
        }
        if (this.mythicMobs && Config.HOOK_MYTHICMOBS) {
            this.pluginManager.registerEvents(new MythicMobsHook(), plugin);
            this.log("Registered MythicMobs listener!");
        }
        if (this.gSit && Config.HOOK_GSIT) {
            this.pluginManager.registerEvents(new GSitListener(), plugin);
            this.log("Registered GSit Listener!");
        }
        if (this.brewery && Config.HOOK_BREWERY) {
            this.pluginManager.registerEvents(new BreweryHook(), plugin);
            this.log("Registered Brewery Listener");
        }
        if (ServerUtil.isHigherThan1_11()) {
            Bukkit.getPluginManager().registerEvents(new EventProcessor1_11(), plugin);
        }
        if (ServerUtil.isHigherThan1_13()) {
            Bukkit.getPluginManager().registerEvents(new EventProcessor1_13(), plugin);
        }
        System.setProperty("com.viaversion.handlePingsAsInvAcknowledgements", "true");
        PacketEvents.get().registerListener(new NetworkManager(PacketEventPriority.LOWEST));
        if (this.getPluginManager().getPlugin("DiscordBotAPI") != null && Config.getBoolean("hooks.discord.enable-hook")) {
            this.discordHelper = new DiscordHelper();
            this.log("DiscordBotAPI found. Enabling hook!");
        }
        if (this.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.placeHolderAPI = true;
            new PlaceholderAPIExtension().register();
            this.log("PlaceholderAPI found. Enabling hook!");
        }
    }
    
    public void stop(final VulcanPlugin plugin) {
        this.plugin = plugin;
        assert plugin != null : "Error while shutting down Vulcan.";
        this.tickManager.stop();
        this.resetManager.stop();
        this.judgementExecutor.shutdownNow();
        this.packetExecutor.shutdownNow();
        this.logExecutor.shutdownNow();
        this.alertExecutor.shutdownNow();
        Bukkit.getScheduler().cancelTasks(plugin);
    }
    
    public void reload() {
        Config.reload();
        Config.updateConfig();
        CacheUtil.resetConfigValues();
        CacheUtil.updateCheckValues();
        if (Vulcan.INSTANCE.getDiscordHelper() != null) {
            DiscordHelper.getInstance().updateConfig();
        }
        Vulcan.INSTANCE.getPlugin().reloadConfig();
    }
    
    public void log(final String string) {
        Bukkit.getLogger().log(Level.INFO, "[Vulcan] " + string);
    }
    
    public DiscordHelper getDiscordHelper() {
        return this.discordHelper;
    }
    
    public long getStartTime() {
        return this.startTime;
    }
    
    public String getSpigot() {
        return this.spigot;
    }
    
    public String getNonce() {
        return this.nonce;
    }
    
    public String getResource() {
        return this.resource;
    }
    
    public Logger getLogger() {
        return this.logger;
    }
    
    public boolean isFloodgate1() {
        return this.floodgate1;
    }
    
    public boolean isFloodgate2() {
        return this.floodgate2;
    }
    
    public boolean isPlaceHolderAPI() {
        return this.placeHolderAPI;
    }
    
    public boolean isMcMMO() {
        return this.mcMMO;
    }
    
    public boolean isCrackShot() {
        return this.crackShot;
    }
    
    public boolean isLibsDisguises() {
        return this.libsDisguises;
    }
    
    public boolean isMythicMobs() {
        return this.mythicMobs;
    }
    
    public boolean isGSit() {
        return this.gSit;
    }
    
    public boolean isBrewery() {
        return this.brewery;
    }
    
    public boolean isTestServer() {
        return this.testServer;
    }
    
    public boolean isMythicMobsLatest() {
        return this.mythicMobsLatest;
    }
    
    public Map<Integer, Integer> getFishingRodPulledBoats() {
        return this.fishingRodPulledBoats;
    }
    
    public TickManager getTickManager() {
        return this.tickManager;
    }
    
    public ClientBrandManager getClientBrandManager() {
        return this.clientBrandManager;
    }
    
    public ResetManager getResetManager() {
        return this.resetManager;
    }
    
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }
    
    public ReceivingPacketProcessor getReceivingPacketProcessor() {
        return this.receivingPacketProcessor;
    }
    
    public SendingPacketProcessor getSendingPacketProcessor() {
        return this.sendingPacketProcessor;
    }
    
    public PlayerDataManager getPlayerDataManager() {
        return this.playerDataManager;
    }
    
    public AlertManager getAlertManager() {
        return this.alertManager;
    }
    
    public PunishmentManager getPunishmentManager() {
        return this.punishmentManager;
    }
    
    public GUIManager getGuiManager() {
        return this.guiManager;
    }
    
    public ExecutorService getJudgementExecutor() {
        return this.judgementExecutor;
    }
    
    public ExecutorService getAlertExecutor() {
        return this.alertExecutor;
    }
    
    public ExecutorService getPacketExecutor() {
        return this.packetExecutor;
    }
    
    public ExecutorService getLogExecutor() {
        return this.logExecutor;
    }
    
    public ReflectionManager getReflectionManager() {
        return this.reflectionManager;
    }
    
    public boolean isBypass() {
        Objects.requireNonNull(this);
        return true;
    }
    
    public VulcanPlugin getPlugin() {
        return this.plugin;
    }
    
    public void setFloodgate1(final boolean floodgate1) {
        this.floodgate1 = floodgate1;
    }
    
    public void setFloodgate2(final boolean floodgate2) {
        this.floodgate2 = floodgate2;
    }
    
    public void setPlaceHolderAPI(final boolean placeHolderAPI) {
        this.placeHolderAPI = placeHolderAPI;
    }
    
    public void setMcMMO(final boolean mcMMO) {
        this.mcMMO = mcMMO;
    }
    
    public void setCrackShot(final boolean crackShot) {
        this.crackShot = crackShot;
    }
    
    public void setLibsDisguises(final boolean libsDisguises) {
        this.libsDisguises = libsDisguises;
    }
    
    public void setMythicMobs(final boolean mythicMobs) {
        this.mythicMobs = mythicMobs;
    }
    
    public void setGSit(final boolean gSit) {
        this.gSit = gSit;
    }
    
    public void setBrewery(final boolean brewery) {
        this.brewery = brewery;
    }
    
    public void setTestServer(final boolean testServer) {
        this.testServer = testServer;
    }
    
    public void setMythicMobsLatest(final boolean mythicMobsLatest) {
        this.mythicMobsLatest = mythicMobsLatest;
    }
}
