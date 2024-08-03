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
        name = "sudo",
        description = "Executes a command as another player.",
        rank = Rank.PREMIUM,
        async = false
)
public class CommandSudo extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {

        if (args.length < 2){
            user.sendMessage("Usage: sudo <player> (command)");
            return;
        }

        String command = String.join(" ", args).replace(args[0] + " ", "");

        if(args[0].equals("*")){
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                player.chat(command);
            }
            user.sendMessage("All players have been sudo'd.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }


        target.chat(command);
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been sudo'd.");
    }
}
