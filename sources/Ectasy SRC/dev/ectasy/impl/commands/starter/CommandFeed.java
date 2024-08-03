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
        name = "feed",
        description = "Feed yourself.",
        rank = Rank.STARTER,
        async = false
)
public class CommandFeed extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            if(args.length == 0){
                user.getPlayer().setFoodLevel(20);
                user.sendMessage("You have been fed.");
                return;
            }
            if(args[0].equals("*")){
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.setFoodLevel(20);
                }
                user.sendMessage("All players have been fed.");
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
                return;
            }

            target.setFoodLevel(20);
            user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been fed.");

        }
}
