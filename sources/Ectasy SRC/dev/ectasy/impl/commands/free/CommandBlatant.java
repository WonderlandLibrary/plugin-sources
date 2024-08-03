package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "blatant",
        description = "Toggles blatant mode.",
        rank = Rank.FREE
)
public class CommandBlatant extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            user.setBlatant(!user.isBlatant());
            user.sendMessage("Blatant mode is now " + user.getMainColor() + (user.isBlatant() ? "enabled" : "disabled") + user.getSecondaryColor() + ".");
        }
}
