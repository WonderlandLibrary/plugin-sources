package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "serverchat",
        description = "Allows you to send a message to all logged in ectasy users.",
        aliases = {"sc"},
        rank = Rank.FREE
)
public class CommandServerChat extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
       String message = String.join(" ", args);
       for(Player player : Bukkit.getServer().getOnlinePlayers()){
           User target = Ectasy.getInstance().getUser(player);
           if(target.getRank() == Rank.UNAUTHORIZED) continue;
           target.sendMessage(user.getMainColor() + user.getPlayer().getName() + target.getSecondaryColor() + ": " + message);

       }
    }

}
