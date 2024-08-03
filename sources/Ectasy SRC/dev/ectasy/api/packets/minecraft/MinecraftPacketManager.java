package dev.ectasy.api.packets.minecraft;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.CommandManager;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;

public class MinecraftPacketManager {

    public MinecraftPacketManager() {
        this.onEnable();
    }

    public void onEnable() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(Ectasy.getInstance().getPlugin()));
        PacketEvents.getAPI().getSettings()
                .reEncodeByDefault(true)
                .checkForUpdates(false)
                .debug(false)
                .bStats(false);
        PacketEvents.getAPI().load();

        PacketEvents.getAPI().getEventManager().registerListener(Ectasy.getInstance().getCommandManager(), PacketListenerPriority.HIGHEST);

        PacketEvents.getAPI().init();
    }

    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }
}
