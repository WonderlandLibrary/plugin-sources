package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;

@CommandInfo(
        name = "broadcast",
        description = "Broadcast a message to all players",
        aliases = {"bc"},
        rank = Rank.FREE,
        blatant = true
)
public class CommandBroadcast extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){


        if(args.length == 0){
            user.sendMessage("Usage: broadcast <message>");
            return;
        }

        Bukkit.broadcastMessage(String.join(" ", args).replace("&", "§"));

    }
}
