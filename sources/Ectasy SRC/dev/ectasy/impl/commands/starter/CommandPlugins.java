package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.luaj.vm2.ast.Str;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(
        name = "plugins",
        description = "Lists all the plugins.",
        rank = Rank.STARTER
)
public class CommandPlugins extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        List<String> plugins = new ArrayList<>();
        for(Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()){
            if(plugin.isEnabled()){
                plugins.add("§a" + plugin.getName() + user.getSecondaryColor());
            }else{
                plugins.add("§c" + plugin.getName() + user.getSecondaryColor());
            }
        }

        user.sendMessage(String.join(", ", plugins));
    }
}
