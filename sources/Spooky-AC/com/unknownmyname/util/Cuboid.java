/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 */
package com.unknownmyname.util;

import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.util.MathHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Cuboid {
    private /* synthetic */ double z1;
    private /* synthetic */ double y1;
    private /* synthetic */ double x1;
    private /* synthetic */ double z2;
    private /* synthetic */ double x2;
    private /* synthetic */ double y2;

    public boolean phase(Cuboid cuboid) {
        if (!(this.x1 <= cuboid.x1 && this.x2 >= cuboid.x2 || this.z1 <= cuboid.z1 && this.z2 >= cuboid.z2)) {
            return "".length() != 0;
        }
        return " ".length() != 0;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX1() {
        return this.x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setZ2(double z2) {
        this.z2 = z2;
    }

    public boolean containsXZ(double x, double z) {
        if (this.x1 <= x && this.x2 >= x && this.z1 <= z && this.z2 >= z) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public boolean contains(PlayerLocation location) {
        if (this.x1 <= location.getX() && this.x2 >= location.getX() && this.y1 <= location.getY() && this.y2 >= location.getY() && this.z1 <= location.getZ() && this.z2 >= location.getZ()) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public Cuboid(double x1, double x2, double y1, double y2, double z1, double z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
    }

    public double getX2() {
        return this.x2;
    }

    public double cY() {
        return (this.y1 + this.y2) * 0.5;
    }

    public Cuboid combine(Cuboid other) {
        return new Cuboid(Math.min(this.x1, other.x1), Math.max(this.x2, other.x2), Math.min(this.y1, other.y1), Math.max(this.y2, other.y2), Math.min(this.z1, other.z1), Math.max(this.z2, other.z2));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public List<Block> getBlocks(World world) {
        x1 = (int)Math.floor(this.x1);
        x2 = (int)Math.ceil(this.x2);
        y1 = (int)Math.floor(this.y1);
        y2 = (int)Math.ceil(this.y2);
        z1 = (int)Math.floor(this.z1);
        z2 = (int)Math.ceil(this.z2);
        blocks = new ArrayList<Block>();
        blocks.add(world.getBlockAt(x1, y1, z1));
        x = x1;
        "".length();
        if (true) ** GOTO lbl32
        throw null;
lbl-1000: // 1 sources:
        {
            y = y1;
            "".length();
            if (-1 == -1) ** GOTO lbl30
            throw null;
lbl-1000: // 1 sources:
            {
                z = z1;
                "".length();
                if (0 > -1) ** GOTO lbl28
                throw null;
lbl-1000: // 1 sources:
                {
                    blocks.add(world.getBlockAt(x, y, z));
                    ++z;
lbl28: // 2 sources:
                    ** while (z < z2)
                }
lbl29: // 1 sources:
                ++y;
lbl30: // 2 sources:
                ** while (y < y2)
            }
lbl31: // 1 sources:
            ++x;
lbl32: // 2 sources:
            ** while (x < x2)
        }
lbl33: // 1 sources:
        return blocks;
    }

    public double getZ1() {
        return this.z1;
    }

    public double getY1() {
        return this.y1;
    }

    public double cZ() {
        return (this.z1 + this.z2) * 0.5;
    }

    public void setZ1(double z1) {
        this.z1 = z1;
    }

    public Cuboid expand(double x, double y, double z) {
        this.x1 -= x;
        this.x2 += x;
        this.y1 -= y;
        this.y2 += y;
        this.z1 -= z;
        this.z2 += z;
        return this;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double distanceXZ(double x, double z) {
        double dx = Math.min(Math.pow(x - this.x1, 2.0), Math.pow(x - this.x2, 2.0));
        double dz = Math.min(Math.pow(z - this.z1, 2.0), Math.pow(z - this.z2, 2.0));
        return MathHelper.sqrt_double(dx + dz);
    }

    public Cuboid shrink(double x, double y, double z) {
        this.x1 += x;
        this.x2 -= x;
        this.y1 += y;
        this.y2 -= y;
        this.z1 += z;
        this.z2 -= z;
        return this;
    }

    public double getY2() {
        return this.y2;
    }

    public static boolean checkBlocks(Collection<Block> blocks, Predicate<Material> predicate) {
        Iterator<Block> var2 = blocks.iterator();
        do {
            if (var2.hasNext()) {
                "".length();
                if (false < true) continue;
                throw null;
            }
            return " ".length() != 0;
        } while (predicate.test(var2.next().getType()));
        return "".length() != 0;
    }

    public boolean checkBlocks(World world, Predicate<Material> predicate) {
        return Cuboid.checkBlocks(this.getBlocks(world), predicate);
    }

    public Cuboid add(Cuboid other) {
        this.x1 += other.x1;
        this.x2 += other.x2;
        this.y1 += other.y1;
        this.y2 += other.y2;
        this.z1 += other.z1;
        this.z2 += other.z2;
        return this;
    }

    public double getZ2() {
        return this.z2;
    }

    public Cuboid(double x, double y, double z) {
        this(x, x, y, y, z, z);
    }

    public double cX() {
        return (this.x1 + this.x2) * 0.5;
    }

    public Cuboid(PlayerLocation location) {
        this(location.getX(), location.getY(), location.getZ());
    }

    public Cuboid move(double x, double y, double z) {
        this.x1 += x;
        this.x2 += x;
        this.y1 += y;
        this.y2 += y;
        this.z1 += z;
        this.z2 += z;
        return this;
    }
}

