package dev.ectasy.impl.commands.free.gamemode;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "gmsp",
        description = "Sets a player's gamemode to spectator.",
        rank = Rank.FREE,
        async = false
)
public class CommandGamemodeSpectator extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
       if(args.length == 0){
            user.getPlayer().setGameMode(GameMode.SPECTATOR);
            user.sendMessage("Your gamemode has been set to " + user.getMainColor() + "spectator" + user.getSecondaryColor() + ".");
            return;
        }

        if(args[0].equals("*")){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.setGameMode(GameMode.SPECTATOR);
            }
            user.sendMessage("All players' gamemodes have been set to " + user.getMainColor() + "spectator" + user.getSecondaryColor() + ".");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        target.setGameMode(GameMode.SPECTATOR);
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + "'s gamemode has been set to " + user.getMainColor() + "spectator" + user.getSecondaryColor() + ".");
    }


}
