package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;

@CommandInfo(
        name = "bomb",
        description = "Gives you a bomb you can throw.",
        rank = Rank.FREE,
        aliases = {"bombs"},
        async = false,
        listener = true,
        blatant = true
)
public class CommandBomb extends AbstractCommand implements Listener {

    @EventHandler
    public void onItemRightClick(PlayerInteractEvent e){
        ItemStack item = e.getItem();
        if (item == null)
            return;
        ItemMeta meta = item.getItemMeta();
        Action action = e.getAction();
        if(meta == null || !meta.hasDisplayName() || !meta.hasEnchant(Enchantment.DURABILITY) || !meta.getDisplayName().equals("§7§oectasy §cbomb"))
            return;
        if(action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK)
            return;

        Location loc = e.getPlayer().getLocation().add(0, 1.5, 0);


        e.setCancelled(true);
        Item bomb = loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.TNT, 1));
        bomb.setVelocity(e.getPlayer().getLocation().getDirection().multiply(2));
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(Ectasy.getInstance().getPlugin(), () -> {
            if(bomb.isDead())
                return;
            if (bomb.isOnGround() || !bomb.getLocation().add(0, 1, 0).getBlock().isEmpty() || !bomb.getLocation().add(1, 0, 0).getBlock().isEmpty() || !bomb.getLocation().add(0, 0, 1).getBlock().isEmpty() || !bomb.getLocation().add(-1, 0, 0).getBlock().isEmpty() || !bomb.getLocation().add(0, 0, -1).getBlock().isEmpty()) {
                bomb.getWorld().createExplosion(bomb.getLocation(), 10F, true);
                bomb.remove();
            }
        }, 5, 5);

        Bukkit.getScheduler().runTaskLater(Ectasy.getInstance().getPlugin(), task::cancel, 20 * 5);
    }

    public ItemStack getBomb(){
        ItemStack item = new ItemStack(Material.TNT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7§oectasy §cbomb");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 69);
        return item;
    }


    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){

        user.getPlayer().getInventory().addItem(getBomb());
        user.sendMessage("You have been given a bomb, right click to throw it.");
    }

}
