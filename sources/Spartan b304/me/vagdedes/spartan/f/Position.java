package me.vagdedes.spartan.f;

import me.vagdedes.spartan.k.e.ServerWorld;
import org.bukkit.Location;
import java.util.Objects;

public class Position
{
    private int id;
    private double x;
    private double y;
    private double z;
    
    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id), Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z));
    }
    
    public Position(final Location location) {
        super();
        this.id = ServerWorld.a(location.getWorld());
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }
    
    public Position(final SpartanLocation spartanLocation) {
        super();
        this.id = ServerWorld.a(spartanLocation.getWorld());
        this.x = spartanLocation.getBlockX();
        this.y = spartanLocation.getBlockY();
        this.z = spartanLocation.getBlockZ();
    }
    
    public Position(final int id, final double x, final double y, final double z) {
        super();
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Position(final double x, final double y, final double z) {
        super();
        this.id = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public int getID() {
        return this.id;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
}
