package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "logout",
        description = "Logs you out of ectasy",
        rank = Rank.FREE
)
public class CommandLogout extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            user.sendMessage("You have been logged out.");
            user.setRank(Rank.UNAUTHORIZED);
        }
}
