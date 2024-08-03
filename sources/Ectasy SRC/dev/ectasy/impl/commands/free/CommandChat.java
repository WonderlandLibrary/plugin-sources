package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "chat",
        description = "Allows you to send a message in chat while being logged in.",
        aliases = {"c"},
        rank = Rank.FREE
)
public class CommandChat extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
       event.setCancelled(false);
       packet.setMessage(String.join(" ", args));
    }

}
