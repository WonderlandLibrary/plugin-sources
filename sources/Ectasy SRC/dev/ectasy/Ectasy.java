package dev.ectasy;

import dev.ectasy.api.commands.CommandManager;
import dev.ectasy.api.data.User;
import dev.ectasy.api.packets.backend.BackendPacketManager;
import dev.ectasy.api.packets.minecraft.MinecraftPacketManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class Ectasy {

    private static Ectasy instance;
    private final Plugin plugin;
    private MinecraftPacketManager minecraftPacketManager;
    private CommandManager commandManager;
    private BackendPacketManager backendPacketManager;
    private final HashMap<UUID, User> users = new HashMap<>();
    private boolean exiting = false;
    public HashMap<UUID, User> getUsers(){
        return users;
    }

    public User getUser(Player player){
        if(!users.containsKey(player.getUniqueId())){
            users.put(player.getUniqueId(), new User(player.getUniqueId()));
        }
        return users.get(player.getUniqueId());
    }

    public Ectasy(Plugin plugin){
        if(plugin == null)
            throw new RuntimeException("Plugin cannot be null.");
        instance = this;
        this.plugin = plugin;
    }

    public void onEnable(){
        commandManager = new CommandManager();
        minecraftPacketManager = new MinecraftPacketManager();
        BackendPacketManager.open();
    }

    public void onDisable(){
        exiting = true;
    }

    public CommandManager getCommandManager(){
        return commandManager;
    }

    public boolean isExiting(){
        return exiting;
    }

    public MinecraftPacketManager getPacketManager(){
        return minecraftPacketManager;
    }

    public BackendPacketManager getBackendPacketManager(){
        return backendPacketManager;
    }

    public void setBackendPacketManager(BackendPacketManager backendPacketManager){
        this.backendPacketManager = backendPacketManager;
    }

    public static Ectasy getInstance(){
        return instance;
    }

    public Plugin getPlugin(){
        return plugin;
    }

}
