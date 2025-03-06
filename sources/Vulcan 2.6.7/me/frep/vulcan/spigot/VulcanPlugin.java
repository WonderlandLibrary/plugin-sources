package me.frep.vulcan.spigot;

import me.frep.vulcan.api.VulcanAPI;
import me.frep.vulcan.spigot.api.VulcanSpigotAPI;
import org.bukkit.plugin.Plugin;
import io.github.retrooper.packetevents.PacketEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class VulcanPlugin extends JavaPlugin
{
    public void onLoad() {
        PacketEvents.create(this);
        PacketEvents.get().getSettings().compatInjector(!this.getConfig().getBoolean("settings.inject-early"));
        PacketEvents.get().getSettings().checkForUpdates(false);
        PacketEvents.get().load();
    }
    
    public void onEnable() {
        PacketEvents.get().init();
        VulcanAPI.Factory.setApi(new VulcanSpigotAPI());
        Vulcan.INSTANCE.start(this);
    }
    
    public void onDisable() {
        PacketEvents.get().terminate();
        Vulcan.INSTANCE.stop(this);
    }
}
