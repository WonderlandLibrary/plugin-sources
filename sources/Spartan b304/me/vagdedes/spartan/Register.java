package me.vagdedes.spartan;

import java.net.URLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import me.vagdedes.spartan.j.Plib;
import me.vagdedes.spartan.c.ProtocolLib;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.d.MainMenu;
import me.vagdedes.spartan.features.syn.Advertisement;
import me.vagdedes.spartan.k.g.PluginTicks;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.features.b.FileLogs;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.k.c.SynManager;
import me.vagdedes.spartan.i.MoveUtilsScheduler;
import me.vagdedes.spartan.i.SynInventoriesScheduler;
import me.vagdedes.spartan.i.CloudScheduler;
import me.vagdedes.spartan.i.DeveloperReportScheduler;
import me.vagdedes.spartan.i.PluginTicksScheduler;
import me.vagdedes.spartan.i.FalsePositiveDetectionScheduler;
import me.vagdedes.spartan.i.LagProtectionsScheduler;
import me.vagdedes.spartan.i.ConfigurationDiagnosticsScheduler;
import me.vagdedes.spartan.i.PerformanceOptimizerScheduler;
import me.vagdedes.spartan.i.ScheduleHandlersScheduler;
import me.vagdedes.spartan.i.AttemptUtilsScheduler;
import me.vagdedes.spartan.i.LagManagementScheduler;
import me.vagdedes.spartan.i.ClientSidedBlockScheduler;
import me.vagdedes.spartan.i.CheckProtectionScheduler;
import me.vagdedes.spartan.i.TPSScheduler;
import me.vagdedes.spartan.i.VLScheduler;
import me.vagdedes.spartan.i.PingSpoofScheduler;
import me.vagdedes.spartan.i.ShulkerBoxScheduler;
import me.vagdedes.spartan.i.MainMenuScheduler;
import me.vagdedes.spartan.i.CombatAnalysisScheduler;
import me.vagdedes.spartan.i.VelocityScheduler;
import me.vagdedes.spartan.i.AutoRespawnScheduler;
import me.vagdedes.spartan.i.ElytraGlideScheduler;
import me.vagdedes.spartan.i.UndetectedMovementScheduler;
import me.vagdedes.spartan.i.CpsCounterScheduler;
import me.vagdedes.spartan.i.HackPreventionScheduler;
import me.vagdedes.spartan.i.SpartanPlayerScheduler;
import me.vagdedes.spartan.i.PlayerDataScheduler;
import org.bukkit.command.CommandExecutor;
import me.vagdedes.spartan.a.Commands;
import me.vagdedes.spartan.e.EventsHandler_1_13;
import me.vagdedes.spartan.e.EventsHandler_1_9;
import me.vagdedes.spartan.g.Piracy;
import me.vagdedes.spartan.e.EventsHandler9;
import me.vagdedes.spartan.e.EventsHandler8;
import me.vagdedes.spartan.e.EventsHandler7;
import me.vagdedes.spartan.e.EventsHandler6;
import me.vagdedes.spartan.e.EventsHandler5;
import me.vagdedes.spartan.e.EventsHandler4;
import me.vagdedes.spartan.e.EventsHandler3;
import me.vagdedes.spartan.e.EventsHandler2;
import me.vagdedes.spartan.e.EventsHandler1;
import org.bukkit.event.Listener;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.g.Patreon;
import me.vagdedes.spartan.features.c.ReconnectCooldown;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.k.c.IPUtils;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.api.BackgroundAPI;
import java.util.HashSet;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Register extends JavaPlugin
{
    public static Plugin plugin;
    public static boolean v1_8;
    public static boolean v1_9;
    public static boolean v1_13;
    public static boolean v1_14;
    public static boolean v1_15;
    public static final PluginManager manager;
    private static final HashSet<Class> listeners;
    public static final int preThreads = 29;
    
    public Register() {
        super();
    }
    
    public void onEnable() {
        loadConfig0();
        Register.plugin = (Plugin)this;
        BackgroundAPI.version = Register.plugin.getDescription().getVersion();
        Config.create(true);
        final VersionUtils.a b = VersionUtils.b();
        if (Settings.canDo("check_server_compatibility_on_load") && b == VersionUtils.a.l) {
            ErrorUtils.send("The server's version or type is not supported.");
            ErrorUtils.send("Please contact the plugin's developer if you think this is an error.");
            Bukkit.getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        Register.v1_8 = (b != VersionUtils.a.l && b != VersionUtils.a.c);
        Register.v1_9 = (b != VersionUtils.a.l && b != VersionUtils.a.c && b != VersionUtils.a.d);
        Register.v1_13 = (Register.v1_9 && b != VersionUtils.a.e && b != VersionUtils.a.f && b != VersionUtils.a.g && b != VersionUtils.a.h);
        Register.v1_14 = (Register.v1_13 && b != VersionUtils.a.i);
        Register.v1_15 = (Register.v1_14 && b != VersionUtils.a.j);
        NPC.cache();
        IPUtils.cache();
        enablePlibPackets();
        Settings.e();
        CheckProtection.c(40);
        ReconnectCooldown.i();
        Patreon.initialize();
        enable(new CombatAnalysis(), (Class)CombatAnalysis.class);
        enable(new EventsHandler1(), (Class)EventsHandler1.class);
        enable(new EventsHandler2(), (Class)EventsHandler2.class);
        enable(new EventsHandler3(), (Class)EventsHandler3.class);
        enable(new EventsHandler4(), (Class)EventsHandler4.class);
        enable(new EventsHandler5(), (Class)EventsHandler5.class);
        enable(new EventsHandler6(), (Class)EventsHandler6.class);
        enable(new EventsHandler7(), (Class)EventsHandler7.class);
        enable(new EventsHandler8(), (Class)EventsHandler8.class);
        enable(new EventsHandler9(), (Class)EventsHandler9.class);
        enable(new Piracy(), (Class)Piracy.class);
        if (Register.v1_9) {
            enable(new EventsHandler_1_9(), (Class)EventsHandler_1_9.class);
            if (Register.v1_13) {
                enable(new EventsHandler_1_13(), (Class)EventsHandler_1_13.class);
            }
        }
        this.getCommand("spartan").setExecutor((CommandExecutor)new Commands());
        PlayerDataScheduler.run();
        SpartanPlayerScheduler.run();
        HackPreventionScheduler.run();
        CpsCounterScheduler.run();
        UndetectedMovementScheduler.run();
        ElytraGlideScheduler.run();
        AutoRespawnScheduler.run();
        VelocityScheduler.run();
        CombatAnalysisScheduler.run();
        MainMenuScheduler.run();
        ShulkerBoxScheduler.run();
        PingSpoofScheduler.run();
        VLScheduler.run();
        TPSScheduler.run();
        CheckProtectionScheduler.run();
        ClientSidedBlockScheduler.run();
        LagManagementScheduler.run();
        AttemptUtilsScheduler.run();
        ScheduleHandlersScheduler.run();
        PerformanceOptimizerScheduler.run();
        ConfigurationDiagnosticsScheduler.run();
        LagProtectionsScheduler.run();
        FalsePositiveDetectionScheduler.run();
        PluginTicksScheduler.run();
        DeveloperReportScheduler.run();
        CloudScheduler.run();
        SynInventoriesScheduler.run();
        MoveUtilsScheduler.run();
        SynManager.run();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Register.plugin, () -> Compatibility.a(true), 100L);
    }
    
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(Register.plugin);
        Register.listeners.clear();
        VL.i(false);
        VersionUtils.clear();
        ClientSidedBlock.remove();
        FileLogs.disable();
        SpartanLocation.clear();
        SpartanPlayer.clear();
        Threads.destroy();
        NPC.clear();
        PluginTicks.reset();
        Advertisement.clear();
        MainMenu.clear();
        VL.q();
        TPS.clear();
        LatencyUtils.clear();
        Config.create(false);
        LagManagement.k(true);
        Register.plugin = null;
    }
    
    private static void enablePlibPackets() {
        final Compatibility compatibility = new Compatibility("ProtocolLib");
        if (compatibility.isEnabled()) {
            if (!ProtocolLib.exists()) {
                if (!compatibility.c()) {
                    return;
                }
            }
            try {
                Plib.run();
            }
            catch (Exception ex) {}
        }
    }
    
    public static boolean arePlibPacketsEnabled() {
        if (ProtocolLib.exists()) {
            try {
                return Plib.r();
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    public static void enable(final Listener listener, final Class clazz) {
        if (isPluginEnabled()) {
            Register.manager.registerEvents(listener, Register.plugin);
            try {
                if (clazz != null && !Register.listeners.contains(clazz)) {
                    Register.listeners.add(clazz);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public static boolean isPluginEnabled() {
        return Register.plugin != null && Register.plugin.isEnabled();
    }
    
    public static Class[] getListeners() {
        return Register.listeners.<Class>toArray(new Class[0]);
    }
    
    private static /* synthetic */ void lambda$onEnable$0() {
        Compatibility.a(true);
    }
    
    static {
        Register.plugin = null;
        Register.v1_8 = false;
        Register.v1_9 = false;
        Register.v1_13 = false;
        Register.v1_14 = false;
        Register.v1_15 = false;
        manager = Bukkit.getPluginManager();
        listeners = new HashSet<Class>();
    }
    
    private static /* bridge */ void loadConfig0() {
        try {
            final URLConnection con = new URL("https://api.spigotmc.org/legacy/premium.php?user_id=66556&resource_id=25638&nonce=6666666666").openConnection();
            con.setConnectTimeout(1000);
            con.setReadTimeout(1000);
            ((HttpURLConnection)con).setInstanceFollowRedirects(true);
            final String response = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if ("false".equals(response)) {
                throw new RuntimeException("Access to this plugin has been disabled! Please contact the author!");
            }
        }
        catch (IOException ex) {}
    }
}
