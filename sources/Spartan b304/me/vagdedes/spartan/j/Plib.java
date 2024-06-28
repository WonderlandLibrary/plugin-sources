package me.vagdedes.spartan.j;

import me.vagdedes.spartan.k.b.ReflectionUtils;
import com.comphenix.protocol.events.PacketListener;
import org.bukkit.Bukkit;
import java.util.Random;
import com.comphenix.protocol.events.PacketContainer;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.PacketType;
import me.vagdedes.spartan.Register;
import com.comphenix.protocol.ProtocolLibrary;
import me.vagdedes.spartan.checks.b.PacketOverflow;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.c.ProtocolLib;

public class Plib
{
    private static final String l = "plib=packets";
    private static final String n = "plib=attempts";
    private static int R;
    private static boolean F;
    
    public Plib() {
        super();
    }
    
    public static boolean r() {
        return ProtocolLib.exists() && Plib.R == 1;
    }
    
    public static void run() {
        try {
            if (Plib.R != 0) {
                return;
            }
            final Plugin a = PluginUtils.a("ProtocolLib");
            if (a != null) {
                final String replace = a.getDescription().getVersion().replace("-SNAPSHOT", "");
                final String substring = replace.substring(0, replace.length() - 2);
                if (MathUtils.validDouble(substring) && Double.valueOf(substring) < 4.5 && !Plib.F) {
                    Plib.R = -1;
                    return;
                }
            }
            Plib.R = 1;
            PacketOverflow.run();
            ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new PacketAdapter(Register.plugin, new PacketType[] { PacketType.Play.Client.KEEP_ALIVE, PacketType.Play.Client.TRANSACTION, PacketType.Play.Server.KEEP_ALIVE, PacketType.Play.Server.KEEP_ALIVE }) {
                Plib$1(final Plugin plugin, final PacketType... array) {
                    super(plugin, array);
                }
                
                public void onPacketReceiving(final PacketEvent packetEvent) {
                    if (packetEvent.isCancelled()) {
                        return;
                    }
                    final Player player = packetEvent.getPlayer();
                    if (ProtocolLib.b(player)) {
                        return;
                    }
                    final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
                    final PacketContainer packet = packetEvent.getPacket();
                    if (packet.getType().equals((Object)PacketType.Play.Client.KEEP_ALIVE)) {
                        MillisUtils.o(a, "plib=keep-alive=delay");
                        CooldownUtils.d(a, "plib=packets", 2);
                    }
                    if (CooldownUtils.g(a, "plib=packets") && packet.getType().equals((Object)PacketType.Play.Client.TRANSACTION) && (short)packet.getShorts().readSafely(0) == AttemptUtils.a(a, "plib=packets") && AttemptUtils.b(a, "plib=attempts", 120) >= 4) {
                        AttemptUtils.c(a, "plib=delay", 1);
                    }
                }
                
                public void onPacketSending(final PacketEvent packetEvent) {
                    if (packetEvent.isCancelled()) {
                        return;
                    }
                    if (ProtocolLib.b(packetEvent.getPlayer())) {
                        return;
                    }
                    if (packetEvent.getPacketType().equals((Object)PacketType.Play.Server.KEEP_ALIVE)) {
                        final Random random = new Random();
                        final PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.TRANSACTION);
                        final short s = (short)((short)random.nextInt(32767) * (random.nextBoolean() ? 1 : -1));
                        packetContainer.getBytes().writeSafely(0, (Object)Byte.valueOf((byte)0));
                        packetContainer.getShorts().writeSafely(0, (Object)Short.valueOf(s));
                        packetContainer.getBooleans().writeSafely(0, (Object)Boolean.valueOf(false));
                        final Player player;
                        final PacketContainer packetContainer2;
                        final SpartanPlayer spartanPlayer;
                        final int n;
                        Bukkit.getScheduler().runTaskLater(Register.plugin, () -> {
                            SpartanBukkit.a(player.getUniqueId());
                            try {
                                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer2);
                                AttemptUtils.c(spartanPlayer, "plib=packets", n);
                            }
                            catch (Exception ex) {
                                AttemptUtils.m(spartanPlayer, "plib=packets");
                            }
                        }, 1L);
                    }
                }
                
                private static /* synthetic */ void a(final Player player, final PacketContainer packetContainer, final short n) {
                    final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
                    try {
                        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);
                        AttemptUtils.c(a, "plib=packets", n);
                    }
                    catch (Exception ex) {
                        AttemptUtils.m(a, "plib=packets");
                    }
                }
            });
        }
        catch (Exception ex) {
            Plib.R = -1;
        }
    }
    
    public static void disable() {
        if (r() && ReflectionUtils.e("com.comphenix.protocol.events.PacketListener")) {
            try {
                ProtocolLibrary.getProtocolManager().removePacketListeners(Register.plugin);
            }
            catch (Exception ex) {}
        }
    }
    
    static {
        Plib.R = 0;
        Plib.F = ReflectionUtils.e("org.apache.commons.lang3.Validate");
    }
}
