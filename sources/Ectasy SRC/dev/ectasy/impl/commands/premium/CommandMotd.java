package dev.ectasy.impl.commands.premium;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

@CommandInfo(
        name = "motd",
        description = "Sets the message of the day.",
        rank = Rank.PREMIUM,
        blatant = true,
        listener = true
)
public class CommandMotd extends AbstractCommand implements Listener {

    private String motd = "";

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        if(args.length == 0){
            user.sendMessage("Usage: motd <message> or motd clear");
            return;
        }

        if(args[0].equalsIgnoreCase("clear")){
            motd = "";
            user.sendMessage("Message of the day has been cleared.");
            return;
        }

        motd = String.join(" ", args);
        user.sendMessage("Message of the day has been set to: " + user.getMainColor() + motd + user.getSecondaryColor() + ".");
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e){
        if(!motd.isEmpty())
            e.setMotd(motd.replace("&", "§"));
    }

}
