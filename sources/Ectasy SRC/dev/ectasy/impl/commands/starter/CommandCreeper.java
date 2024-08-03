package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCamera;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerChangeGameState;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "creeper",
        description = "Loads the creeper shader to a player.",
        rank = Rank.STARTER,
        async = false
)
public class CommandCreeper extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            if(args.length < 1){
                user.sendMessage("Usage: creeper <player>");
                return;
            }

            if(args[0].equals("*")){

                for(Player player : Bukkit.getOnlinePlayers()){
                    Creeper creeper = player.getWorld().spawn(player.getLocation(), Creeper.class);

                    WrapperPlayServerCamera wrapperPlayServerCamera = new WrapperPlayServerCamera(creeper.getEntityId());
                    PacketEvents.getAPI().getPlayerManager().sendPacket(player, wrapperPlayServerCamera);

                }

                user.sendMessage("The creeper shader has been loaded to all players.");
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
                return;
            }

            Creeper creeper = target.getWorld().spawn(target.getLocation(), Creeper.class);

            WrapperPlayServerCamera wrapperPlayServerCamera = new WrapperPlayServerCamera(creeper.getEntityId());
            PacketEvents.getAPI().getPlayerManager().sendPacket(target, wrapperPlayServerCamera);
            user.sendMessage("The creeper shader has been loaded to " + user.getMainColor() + target.getName() + user.getSecondaryColor() + ".");
        }
}
