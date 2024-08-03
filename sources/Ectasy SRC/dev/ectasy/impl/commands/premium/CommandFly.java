package dev.ectasy.impl.commands.premium;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "fly",
        description = "Enables or disables fly mode.",
        rank = Rank.PREMIUM
)
public class CommandFly extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        if(args.length == 0){
            user.getPlayer().setAllowFlight(!user.getPlayer().getAllowFlight());
            user.sendMessage("You are " + user.getMainColor() + (user.getPlayer().getAllowFlight() ? "now" : "no longer") + user.getSecondaryColor() + " flying.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
            return;
        }

        target.setFoodLevel(20);
        target.setAllowFlight(!target.getAllowFlight());
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " is " + user.getMainColor() + (target.getAllowFlight() ? "now" : "no longer") + user.getSecondaryColor() + " flying.");
    }

}
