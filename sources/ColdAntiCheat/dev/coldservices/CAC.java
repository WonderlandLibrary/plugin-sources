package dev.coldservices;

import cc.ghast.packet.PacketAPI;
import cc.ghast.packet.PacketManager;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import dev.coldservices.alert.AlertManager;
import dev.coldservices.check.api.CheckManager;
import dev.coldservices.command.impl.AlertsCommand;
import dev.coldservices.command.impl.ChecksCommand;
import dev.coldservices.command.impl.DefaultCommand;
import dev.coldservices.command.impl.InfoCommand;
import dev.coldservices.config.ConfigManager;
import dev.coldservices.data.PlayerDataManager;
import dev.coldservices.listener.bukkit.PlayerDataListener;
import dev.coldservices.listener.bukkit.RegistrationListener;
import dev.coldservices.listener.network.NetworkListener;
import dev.coldservices.util.registry.ServiceRegistry;
import dev.coldservices.util.registry.ServiceRegistryImpl;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

@Getter
public enum CAC {

    INSTANCE;

    public static final boolean TEST_MODE = false;

    private final Logger logger = Logger.getLogger("coldac");

    private final ServiceRegistry serviceRegistry = new ServiceRegistryImpl();

    @Getter
    private JavaPlugin plugin;

    private PlayerDataManager dataManager;
    private CheckManager checkManager;
    private ConfigManager configManager;
    private AlertManager alertManager;

    public void start(final JavaPlugin plugin) {
        this.plugin = plugin;

        if (!Bukkit.getVersion().contains("1.8.8")) {
            Bukkit.getPluginManager().disablePlugin(this.plugin);
            return;
        }

        registerMetrics();
        registerManagers();
        registerConfiguration();
        registerListeners();
        registerPacketAPI();
        registerCommands();
    }

    public void end() {
        terminateManagers();
    }


    private void registerMetrics() {
        System.setProperty("bstats.relocatecheck", "false");
        new Metrics(this.plugin, 11350);
    }

    private void registerManagers() {
        install(CheckManager.class, new CheckManager());
        install(ConfigManager.class, new ConfigManager());
        install(PlayerDataManager.class, new PlayerDataManager());
        install(AlertManager.class, new AlertManager());
        install(PaperCommandManager.class, new PaperCommandManager(this.plugin));
    }

    private void registerConfiguration() {
        get(ConfigManager.class).generate();
        get(ConfigManager.class).load();
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new RegistrationListener(), this.plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDataListener(), this.plugin);
    }

    private void registerPacketAPI() {
        PacketManager.INSTANCE.init(this.plugin);
        PacketAPI.addListener(new NetworkListener());
    }

    private void registerCommands() {
        get(PaperCommandManager.class).enableUnstableAPI("help");

        Arrays.asList(
                new DefaultCommand(),
                new AlertsCommand(),
                new InfoCommand(),
                new ChecksCommand()
        ).forEach(niceCommand -> {
            get(PaperCommandManager.class).registerCommand(niceCommand);
        });
    }

    private void terminateManagers() {
        this.dataManager = null;
        this.checkManager = null;
        this.alertManager = null;
        this.configManager = null;
    }

    public static CAC get() {
        return INSTANCE;
    }

    public static <T> T install(final Class<T> key, final T service) {
        return INSTANCE.serviceRegistry.put(key, service);
    }

    public static <T> T get(final Class<T> tClass) {
        return INSTANCE.serviceRegistry.get(tClass);
    }
}