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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

@CommandInfo(
        name = "wand",
        description = "Gives you a wand that can replace blocks.",
        rank = Rank.FREE,
        async = false,
        listener = true,
        blatant = true
)
public class CommandWand extends AbstractCommand implements Listener {

    @EventHandler
    public void onItemRightClick(PlayerInteractEvent e){
        ItemStack item = e.getItem();
        if (item == null)
            return;
        ItemMeta meta = item.getItemMeta();
        Action action = e.getAction();
        if(meta == null || !meta.hasDisplayName() || !meta.hasEnchant(Enchantment.DURABILITY) || !meta.getDisplayName().equals("§7§oectasy §cwand") || !meta.hasLore() || meta.getLore().size() != 2)
            return;
        if(action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK)
            return;


        Material material = Material.valueOf(meta.getLore().get(0));
        int radius = Integer.parseInt(meta.getLore().get(1)) / 2;

        Location hit = e.getPlayer().getTargetBlock(null, 100).getLocation();
        for(int x = -radius; x <= radius; x++){
            for(int y = -radius; y <= radius; y++){
                for(int z = -radius; z <= radius; z++){
                    Location loc = hit.clone().add(x, y, z);
                    loc.getBlock().setType(material);
                }
            }
        }



    }



    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){

        if(args.length < 2){
            user.sendMessage("Usage: wand <block> <radius>");
            return;
        }

        Material material;
        try{
            material = Material.valueOf(args[0].toUpperCase());
        }catch(IllegalArgumentException e){
            user.sendMessage("The block " + user.getMainColor() + args[0] + user.getSecondaryColor() + " does not exist.");
            return;
        }
        int radius;
        try{
            radius = Integer.parseInt(args[1]);
        }catch(NumberFormatException e){
            user.sendMessage("Invalid radius.");
            return;
        }

        ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7§oectasy §cwand");
        meta.setLore(Arrays.asList(material.name(), String.valueOf(radius)));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 69);
        user.getPlayer().getInventory().addItem(item);
        user.sendMessage("You have been given a wand, right click to use it.");
    }

}
