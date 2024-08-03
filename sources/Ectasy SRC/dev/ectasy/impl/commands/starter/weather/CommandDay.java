package dev.ectasy.impl.commands.starter.weather;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "day",
        description = "Set the time to day.",
        rank = Rank.STARTER,
        async = false
)
public class CommandDay extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        user.getPlayer().getWorld().setTime(1000);
        user.sendMessage("The time has been set to day.");
    }
}
