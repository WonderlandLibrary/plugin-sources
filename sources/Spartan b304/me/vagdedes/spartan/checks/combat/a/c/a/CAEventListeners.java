package me.vagdedes.spartan.checks.combat.a.c.a;

import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.h.Velocity;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.event.Event;
import me.vagdedes.spartan.checks.combat.a.c.CACombatEvent;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CAEventListeners
{
    private static final long a = 555L;
    private static final long b = 3000L;
    
    public CAEventListeners() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity instanceof Player && g(spartanPlayer) && !CombatUtils.c(spartanPlayer, entity)) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId(), 2);
            final long a2 = TimeBetweenClicks.a(a);
            if (g(a) && ((PlayerData.au(a) && PlayerData.av(a)) || a(spartanPlayer, a) == CAEventListeners.b.b || (a2 > 0L && a2 <= 3000L)) && MathUtils.b(spartanPlayer.a(), a.a()) >= 1.0) {
                Bukkit.getPluginManager().callEvent((Event)new CACombatEvent(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Bukkit.getPlayer(a.getUniqueId())));
            }
            MillisUtils.o(spartanPlayer, "combat-analysis=player-hand-combat-event=" + a.getUniqueId());
        }
    }
    
    public static boolean g(final SpartanPlayer spartanPlayer) {
        final int a = a(spartanPlayer, 6.0);
        return spartanPlayer.getMaximumNoDamageTicks() == 20 && a >= 1 && a <= 5 && !PlayerData.aF(spartanPlayer) && !VL.b(spartanPlayer, CombatAnalysis.a, true) && PlayerData.b(spartanPlayer) <= 0.2 && !PlayerData.aR(spartanPlayer) && !Velocity.E(spartanPlayer) && PlayerData.a(spartanPlayer, PotionEffectType.SPEED) <= 1 && PlayerData.a(spartanPlayer, PotionEffectType.JUMP) <= 1 && !PlayerData.ba(spartanPlayer) && !spartanPlayer.isSleeping();
    }
    
    public static b a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        return (MillisUtils.a(spartanPlayer, "combat-analysis=player-hand-combat-event=" + spartanPlayer2.getUniqueId()) >= 2220L) ? CAEventListeners.b.b : CAEventListeners.b.c;
    }
    
    private static int a(final SpartanPlayer spartanPlayer, final double n) {
        int n2 = 0;
        for (final Entity entity : spartanPlayer.getNearbyEntities(n, n, n)) {
            if (entity instanceof Player && (PlayerData.au(SpartanBukkit.a(entity.getUniqueId())) || PlayerData.av(spartanPlayer))) {
                ++n2;
            }
        }
        return n2;
    }
    
    public enum b
    {
        b, 
        c;
        
        private static final /* synthetic */ b[] a;
        
        public static b[] values() {
            return b.a.clone();
        }
        
        public static b valueOf(final String name) {
            return Enum.<b>valueOf(b.class, name);
        }
        
        static {
            a = new b[] { b.b, b.c };
        }
    }
    
    public enum a
    {
        b, 
        c;
        
        private static final /* synthetic */ a[] a;
        
        public static a[] values() {
            return CAEventListeners.a.a.clone();
        }
        
        public static a valueOf(final String name) {
            return Enum.<a>valueOf(a.class, name);
        }
        
        static {
            a = new a[] { CAEventListeners.a.b, CAEventListeners.a.c };
        }
    }
}
