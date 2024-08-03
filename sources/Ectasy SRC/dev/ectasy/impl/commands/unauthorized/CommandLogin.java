package dev.ectasy.impl.commands.unauthorized;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "./login",
        description = "Logs you in ectasy",
        aliases = {">"},
        rank = Rank.UNAUTHORIZED
)
public class CommandLogin extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){

        for(Rank rank : Rank.values()){
            user.setRank(rank);
            user.sendMessage("You have been logged in" + user.getRank().getMainColor() + " " + rank.name().toLowerCase() + user.getRank().getSecondaryColor() + " rank.");
        }


    }



}
