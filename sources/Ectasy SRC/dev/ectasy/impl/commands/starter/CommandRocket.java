package dev.ectasy.impl.commands.starter;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCamera;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import dev.ectasy.api.utils.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

@CommandInfo(
        name = "rocket",
        description = "Turns you into a cool rocket",
        rank = Rank.STARTER,
        async = false
)
public class CommandRocket extends AbstractCommand {

        public void launchPlayer(Player player){
            // add levitation for 10s
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
            BukkitTask task = Bukkit.getScheduler().runTaskTimer(Ectasy.getInstance().getPlugin(), () -> {

                player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation().add(0, -0.5, 0), 4, 0.4, 0.4, 0.4, 0);
                player.getWorld().spawnParticle(org.bukkit.Particle.FLAME, player.getLocation().add(0, -0.5, 0), 10, 0.1, 0.1, 0.1, 0);
            }, 0, 1);
            Bukkit.getScheduler().runTaskLater(Ectasy.getInstance().getPlugin(), task::cancel, 200);
        }

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){

            if(!VersionUtils.isAbove(9)){
                user.sendMessage("This command is only available for 1.9 and above.");
                return;
            }

            if(args.length < 1){
                user.sendMessage("Usage: rocket <player>");
                return;
            }

            if(args[0].equals("*")){

                for(Player player : Bukkit.getOnlinePlayers()){
                    launchPlayer(player);

                }

                user.sendMessage("Turned all players into rockets.");
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                user.sendMessage("The player " + user.getMainColor() + args[0] + user.getSecondaryColor() + " has not been found.");
                return;
            }


            launchPlayer(target);
            user.sendMessage("Turned " + user.getMainColor() + target.getName() + user.getSecondaryColor() + " into a rocket.");
        }
}
