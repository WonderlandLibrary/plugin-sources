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
        name = "kick",
        description = "Kicks a player.",
        rank = Rank.STARTER,
        async = false
)
public class CommandKick extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {


        if (args.length == 0){
            user.sendMessage("Usage: kick <player> (reason)");
            return;
        }

        String reason = null;
        if(args.length > 1){
            reason = String.join(" ", args).replace(args[0] + " ", "");
        }

        if(args[0].equals("*")){
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                player.kickPlayer(reason);
            }
            user.sendMessage("All players have been kicked.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        target.kickPlayer(reason);
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been kicked.");



    }

}
