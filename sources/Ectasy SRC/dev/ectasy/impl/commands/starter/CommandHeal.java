package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "heal",
        description = "Heal yourself.",
        rank = Rank.STARTER,
        async = false
)
public class CommandHeal extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            if(args.length == 0){
                user.getPlayer().setHealth(user.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                user.sendMessage("You have been healed.");
                return;
            }
            if(args[0].equals("*")){
                for(Player target : Bukkit.getOnlinePlayers()){
                    target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                }
                user.sendMessage("All players have been healed.");
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
                return;
            }

            target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " has been healed.");

        }
}
