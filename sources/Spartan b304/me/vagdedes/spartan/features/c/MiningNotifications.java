package me.vagdedes.spartan.features.c;

import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.c.PunishUtils;
import org.bukkit.entity.Item;
import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.GameMode;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.OfflinePlayer;
import java.util.Iterator;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import java.util.UUID;
import java.util.HashSet;

public class MiningNotifications
{
    private static final HashSet<UUID> l;
    private static final int q = 100;
    public static final Material[] a;
    
    public MiningNotifications() {
        super();
    }
    
    public static Player[] a() {
        final ArrayList<Player> list = new ArrayList<Player>();
        final Iterator<UUID> iterator = MiningNotifications.l.iterator();
        while (iterator.hasNext()) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer((UUID)iterator.next());
            if (offlinePlayer != null && offlinePlayer.isOnline()) {
                list.add((Player)offlinePlayer);
            }
        }
        return list.<Player>toArray(new Player[0]);
    }
    
    public static boolean c(final Player player) {
        return MiningNotifications.l.contains(player.getUniqueId());
    }
    
    public static void d(final Player player) {
        if (!c(player)) {
            MiningNotifications.l.add(player.getUniqueId());
            player.sendMessage(Language.getMessage("mining_notifications_enable"));
        }
        else {
            MiningNotifications.l.remove(player.getUniqueId());
            player.sendMessage(Language.getMessage("mining_notifications_disable"));
        }
    }
    
    public static void b(final Player player, final boolean b) {
        if (b) {
            if (!c(player)) {
                MiningNotifications.l.add(player.getUniqueId());
                player.sendMessage(Language.getMessage("mining_notifications_enable"));
            }
        }
        else if (c(player)) {
            MiningNotifications.l.remove(player.getUniqueId());
            player.sendMessage(Language.getMessage("mining_notifications_disable"));
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (spartanPlayer.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        final Material type = spartanBlock.getType();
        if (type == Material.DIAMOND_ORE || type == Material.EMERALD_ORE || type == Material.GOLD_ORE) {
            CooldownUtils.d(spartanPlayer, "mining=allow", 100);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Item item) {
        final int ticksLived = item.getTicksLived();
        final int entityId = item.getEntityId();
        if (CooldownUtils.g(spartanPlayer, "mining=allow") || spartanPlayer.getGameMode() != GameMode.SURVIVAL || ticksLived > 100 || !CooldownUtils.g(spartanPlayer, "mining=drop") || !CooldownUtils.g(spartanPlayer, "mining=drop=" + entityId)) {
            CooldownUtils.j(spartanPlayer, "mining=drop=" + entityId);
            return;
        }
        final Material type = item.getItemStack().getType();
        final int amount = item.getItemStack().getAmount();
        if (type == Material.DIAMOND || type == Material.DIAMOND_ORE || type == Material.EMERALD || type == Material.EMERALD_ORE || type == Material.GOLD_ORE) {
            final String replace = type.toString().toLowerCase().replace("_", "-");
            String string;
            if (amount > 1) {
                string = replace + "s";
            }
            else {
                string = replace;
            }
            PunishUtils.a(spartanPlayer, type, string, amount);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Item item) {
        final Material type = item.getItemStack().getType();
        final int entityId = item.getEntityId();
        if (type == Material.DIAMOND || type == Material.DIAMOND_ORE || type == Material.EMERALD || type == Material.EMERALD_ORE || type == Material.GOLD_ORE) {
            CooldownUtils.d(spartanPlayer, "mining=drop", 100);
            CooldownUtils.d(spartanPlayer, "mining=drop=" + entityId, 1200);
            for (final Entity entity : spartanPlayer.getNearbyEntities(20.0, 20.0, 20.0)) {
                if (entity instanceof Player) {
                    CooldownUtils.d(SpartanBukkit.a(entity.getUniqueId()), "mining=drop=" + entityId, 1200);
                }
            }
        }
    }
    
    static {
        l = new HashSet<UUID>();
        a = new Material[] { Material.DIAMOND, Material.EMERALD, Material.GOLD_ORE };
    }
}
