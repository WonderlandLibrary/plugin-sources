package com.elevatemc.anticheat;

import com.elevatemc.anticheat.base.command.impl.HoodCommands;
import com.elevatemc.anticheat.database.LogHandler;
import com.elevatemc.anticheat.database.LogManager;
import com.elevatemc.anticheat.manager.PlayerDataManager;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

@Getter
public final class Hood extends JavaPlugin {

    public static Hood instance;

    private LogHandler logHandler;
    private LogManager logManager;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().reEncodeByDefault(false);
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        instance = this;

        this.logHandler = new LogHandler();
        this.logManager = new LogManager();

        HoodPlugin.INSTANCE.start(this);


        Collections.singletonList(new HoodCommands()).forEach(wardenCommands -> {
            HoodPlugin.INSTANCE.getPaperCommandManager().registerCommand(wardenCommands);
        });
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
        HoodPlugin.INSTANCE.stop(this);
    }

    public boolean isLagging() {
        return HoodPlugin.INSTANCE.getTickManager().isLagging();
    }

    public static Hood get() {
        return instance;
    }

    public static PlayerDataManager getPlayerDataManager() {
        return HoodPlugin.INSTANCE.getPlayerDataManager();
    }

}