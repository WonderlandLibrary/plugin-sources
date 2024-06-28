package me.vagdedes.spartan.checks.b;

import com.comphenix.protocol.events.PacketListener;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.PacketType;
import me.vagdedes.spartan.Register;
import com.comphenix.protocol.ProtocolLibrary;
import me.vagdedes.spartan.system.Enums;

public class PacketOverflow
{
    private static final Enums.HackType a;
    private static final int i = 100;
    private static final int j = 3;
    
    public PacketOverflow() {
        super();
    }
    
    public static void run() {
        try {
            ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new PacketAdapter(Register.plugin, new PacketType[] { PacketType.Play.Client.SET_CREATIVE_SLOT, PacketType.Play.Client.USE_ENTITY, PacketType.Play.Client.CHAT, PacketType.Play.Client.CLIENT_COMMAND, PacketType.Play.Client.HELD_ITEM_SLOT, PacketType.Play.Client.TAB_COMPLETE, PacketType.Play.Client.TRANSACTION, PacketType.Play.Client.ENCHANT_ITEM, PacketType.Play.Client.UPDATE_SIGN, PacketType.Play.Client.BLOCK_PLACE, PacketType.Play.Client.KEEP_ALIVE, PacketType.Play.Client.POSITION_LOOK, PacketType.Play.Client.LOOK, PacketType.Play.Client.ABILITIES, PacketType.Play.Client.BLOCK_DIG, PacketType.Play.Client.ENTITY_ACTION, PacketType.Play.Client.STEER_VEHICLE, PacketType.Play.Client.RESOURCE_PACK_STATUS, PacketType.Play.Client.ARM_ANIMATION, PacketType.Play.Client.SETTINGS }) {
                PacketOverflow$1(final Plugin plugin, final PacketType... array) {
                    super(plugin, array);
                }
                
                public void onPacketReceiving(final PacketEvent packetEvent) {
                    if (Thread.currentThread().getId() <= 29L) {
                        if (packetEvent.isCancelled()) {
                            return;
                        }
                        final Player player = packetEvent.getPlayer();
                        if (NPC.is(player)) {
                            return;
                        }
                        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
                        if (a == null || !VL.b(a, PacketOverflow.a, true) || Teleport.E(a)) {
                            return;
                        }
                        final String string = PacketOverflow.a.toString() + "=packet-overflow";
                        if (AttemptUtils.b(a, string, 20) >= 300) {
                            AttemptUtils.m(a, string);
                            new HackPrevention(a, PacketOverflow.a, "t: packet overflow");
                        }
                    }
                }
            });
        }
        catch (Exception ex) {}
    }
    
    static /* synthetic */ Enums.HackType a() {
        return PacketOverflow.a;
    }
    
    static {
        a = Enums.HackType.Exploits;
    }
}
