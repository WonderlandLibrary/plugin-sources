package dev.ectasy.impl.commands.premium;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandInfo(
        name = "alone",
        description = "Hides a player from all of the other players.",
        rank = Rank.PREMIUM,
        async = false,
        listener = true
)
public class CommandAlone extends AbstractCommand implements Listener {

    public List<UUID> hidden = new ArrayList<>();

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        if(args.length == 0){
            user.sendMessage("Usage: alone <player>");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " is not online.");
            return;
        }

        if(hidden.contains(target.getUniqueId())){
            hidden.remove(target.getUniqueId());
            user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " is no longer alone.");
            for(Player p : Bukkit.getOnlinePlayers()){
                p.showPlayer(Ectasy.getInstance().getPlugin(), target);
            }
            return;
        }

        hidden.add(target.getUniqueId());
        for(Player p : Bukkit.getOnlinePlayers()){
            p.hidePlayer(Ectasy.getInstance().getPlugin(), target);
        }
        user.sendMessage("The player " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " is now alone.");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        for(UUID uuid : hidden){
            if(Bukkit.getPlayer(uuid) != null)
                e.getPlayer().hidePlayer(Ectasy.getInstance().getPlugin(), Bukkit.getPlayer(uuid));

        }
    }
}
