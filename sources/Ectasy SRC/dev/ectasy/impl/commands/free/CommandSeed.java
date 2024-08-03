package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "seed",
        description = "View the world seed.",
        rank = Rank.FREE
)
public class CommandSeed extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
           user.sendMessage("The world seed is " + user.getMainColor() + user.getPlayer().getWorld().getSeed() + user.getSecondaryColor() + ".");
        }
}
