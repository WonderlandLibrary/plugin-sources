package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerChangeGameState;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "demo",
        description = "Displays the credit screen to a player.",
        rank = Rank.STARTER,
        async = false
)
public class CommandDemo extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        if(args.length < 1){
            user.sendMessage("Usage: demo <player>");
            return;
        }

        if(args[0].equals("*")){

            for(Player player : Bukkit.getOnlinePlayers()){
                WrapperPlayServerChangeGameState wrapperPlayServerChangeGameState = new WrapperPlayServerChangeGameState(WrapperPlayServerChangeGameState.Reason.WIN_GAME, 1);
                PacketEvents.getAPI().getPlayerManager().sendPacket(player, wrapperPlayServerChangeGameState);


            }

            user.sendMessage("The demo screen has been displayed to all players.");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
            return;
        }

        WrapperPlayServerChangeGameState wrapperPlayServerChangeGameState = new WrapperPlayServerChangeGameState(WrapperPlayServerChangeGameState.Reason.DEMO_EVENT, 0);
        PacketEvents.getAPI().getPlayerManager().sendPacket(target, wrapperPlayServerChangeGameState);
        user.sendMessage("The demo screen has been displayed to " + user.getMainColor() + target.getName() + user.getSecondaryColor() + ".");
        
    }


}
