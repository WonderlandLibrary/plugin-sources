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
        name = "coords",
        description = "View the coordinates of a player.",
        rank = Rank.PREMIUM
)
public class CommandCoords extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {


        if (args.length < 1){
            user.sendMessage("Usage: coords <player>");
            return;
        }


        if(args[0].equals("*")){
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                user.sendMessage(player.getName() + " ;  X: " + player.getLocation().getBlockX() + " Y: " + player.getLocation().getBlockY() + " Z: " + player.getLocation().getBlockZ());
            }
            return;

        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        user.sendMessage(target.getName() + " ;  X: " + target.getLocation().getBlockX() + " Y: " + target.getLocation().getBlockY() + " Z: " + target.getLocation().getBlockZ());



    }
}
