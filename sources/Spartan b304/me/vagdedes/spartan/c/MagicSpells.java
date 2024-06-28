package me.vagdedes.spartan.c;

import com.nisovin.magicspells.events.SpellEvent;
import com.nisovin.magicspells.events.SpellCastedEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.SpartanPlayer;
import com.nisovin.magicspells.events.SpellCastEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class MagicSpells implements Listener
{
    private static boolean enabled;
    
    public MagicSpells() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("MagicSpells");
        if (!MagicSpells.enabled && compatibility.isEnabled() && (PluginUtils.exists("magicspells") || compatibility.c())) {
            Register.enable(new MagicSpells(), (Class)MagicSpells.class);
            MagicSpells.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final SpellCastEvent spellCastEvent) {
        CheckProtection.h(new SpartanPlayer(spellCastEvent.getCaster()), 40);
    }
    
    @EventHandler
    private void a(final SpellCastedEvent spellCastedEvent) {
        CheckProtection.h(new SpartanPlayer(spellCastedEvent.getCaster()), 40);
    }
    
    @EventHandler
    private void a(final SpellEvent spellEvent) {
        CheckProtection.h(new SpartanPlayer(spellEvent.getCaster()), 40);
    }
    
    static {
        MagicSpells.enabled = false;
    }
}
