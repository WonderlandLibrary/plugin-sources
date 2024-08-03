package dev.ectasy.impl.commands.starter.weather;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

@CommandInfo(
        name = "thunder",
        description = "Set the weather to thunder.",
        rank = Rank.STARTER,
        async = false
)
public class CommandThunder extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        user.getPlayer().getWorld().setStorm(true);
        user.getPlayer().getWorld().setThundering(true);
        user.sendMessage("The weather has been set to thunder.");
    }
}
