package me.vagdedes.spartan.checks.b;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.Chunk;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class ChunkUpdates
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, Chunk> q;
    
    public ChunkUpdates() {
        super();
    }
    
    public static void clear() {
        ChunkUpdates.q.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        ChunkUpdates.q.remove(spartanPlayer.getUniqueId());
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        final Chunk chunk = spartanLocation.getChunk();
        if (spartanLocation.getChunk() != spartanLocation2.getChunk()) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            final int b = AttemptUtils.b(spartanPlayer, ChunkUpdates.a.toString() + "=chunk-updates", 20);
            if (ChunkUpdates.q.containsKey(uniqueId) && ChunkUpdates.q.get(uniqueId) != chunk && b > 16) {
                new HackPrevention(spartanPlayer, ChunkUpdates.a, "t: chunk updates, a: " + b, spartanLocation2);
            }
            ChunkUpdates.q.put(uniqueId, chunk);
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
        q = new HashMap<UUID, Chunk>();
    }
}
