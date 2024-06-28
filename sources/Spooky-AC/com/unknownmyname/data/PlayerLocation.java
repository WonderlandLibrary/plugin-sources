/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 */
package com.unknownmyname.data;

import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MathHelper;
import com.unknownmyname.util.MathUtil;
import org.bukkit.Location;
import org.bukkit.World;

public class PlayerLocation {
    private /* synthetic */ double z;
    private /* synthetic */ float yaw;
    private /* synthetic */ double x;
    private /* synthetic */ int tickTime;
    private /* synthetic */ double y;
    private /* synthetic */ long timestamp;
    private /* synthetic */ float pitch;
    private /* synthetic */ Boolean onGround;

    public float getPitch() {
        return this.pitch;
    }

    public double distanceXZ(PlayerLocation location) {
        return MathHelper.sqrt_double(this.distanceXZSquared(location));
    }

    public double getX() {
        return this.x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Cuboid hitbox() {
        return new Cuboid(this.x, this.y, this.z).add(new Cuboid(-0.3, 0.3, 0.0, 1.8, -0.3, 0.3)).expand(0.1, 0.1, 0.1);
    }

    public PlayerLocation spectator() {
        return new PlayerLocation(this.timestamp, this.tickTime, MathUtil.relEntityRoundPos(this.x), MathUtil.relEntityRoundPos(this.y), MathUtil.relEntityRoundPos(this.z), MathUtil.relEntityRoundLook(this.yaw), MathUtil.relEntityRoundLook(this.pitch), this.onGround);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return " ".length() != 0;
        }
        if (o != null && this.getClass() == o.getClass()) {
            PlayerLocation location = (PlayerLocation)o;
            if (this.timestamp != location.timestamp) {
                return "".length() != 0;
            }
            if (this.tickTime != location.tickTime) {
                return "".length() != 0;
            }
            if (Double.compare(location.x, this.x) != 0) {
                return "".length() != 0;
            }
            if (Double.compare(location.y, this.y) != 0) {
                return "".length() != 0;
            }
            if (Double.compare(location.z, this.z) != 0) {
                return "".length() != 0;
            }
            if (Float.compare(location.yaw, this.yaw) != 0) {
                return "".length() != 0;
            }
            if (Float.compare(location.pitch, this.pitch) == 0) {
                return " ".length() != 0;
            }
            return "".length() != 0;
        }
        return "".length() != 0;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public PlayerLocation add(double x, double y, double z) {
        return new PlayerLocation(this.timestamp, this.tickTime, this.x + x, this.y + y, this.z + z, this.yaw, this.pitch, this.onGround);
    }

    public boolean sameDirection(PlayerLocation playerLocation) {
        if (this.yaw == playerLocation.yaw && this.pitch == playerLocation.pitch) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public Boolean getOnGround() {
        return this.onGround;
    }

    public double distanceXZSquared(PlayerLocation location) {
        return Math.pow(this.x - location.x, 2.0) + Math.pow(this.z - location.z, 2.0);
    }

    public void setX(double x) {
        this.x = x;
    }

    public boolean sameBlock(PlayerLocation other) {
        if ((int)Math.floor(this.x) == (int)Math.floor(other.x) && (int)Math.floor(this.y) == (int)Math.floor(other.y) && (int)Math.floor(this.z) == (int)Math.floor(other.z)) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public Cuboid to(PlayerLocation playerLocation) {
        return new Cuboid(Math.min(this.x, playerLocation.x), Math.max(this.x, playerLocation.x), Math.min(this.y, playerLocation.y), Math.max(this.y, playerLocation.y), Math.min(this.z, playerLocation.z), Math.max(this.z, playerLocation.z));
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean sameLocationAndDirection(PlayerLocation playerLocation) {
        if (this.x == playerLocation.x && this.y == playerLocation.y && this.z == playerLocation.z && this.yaw == playerLocation.yaw && this.pitch == playerLocation.pitch) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public PlayerLocation(long timestamp, int tickTime, double x, double y, double z, float yaw, float pitch, Boolean onGround) {
        this.timestamp = timestamp;
        this.tickTime = tickTime;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public double distanceSquared(PlayerLocation other) {
        double[] arrd = new double["   ".length()];
        arrd["".length()] = this.x - other.x;
        arrd[" ".length()] = this.y - other.y;
        arrd["  ".length()] = this.z - other.z;
        return MathUtil.hypotSquared(arrd);
    }

    public boolean sameLocation(PlayerLocation playerLocation) {
        if (this.x == playerLocation.x && this.y == playerLocation.y && this.z == playerLocation.z) {
            return " ".length() != 0;
        }
        return "".length() != 0;
    }

    public void setTickTime(int tickTime) {
        this.tickTime = tickTime;
    }

    public int getTickTime() {
        return this.tickTime;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int n;
        int n2;
        int result = (int)(this.timestamp ^ this.timestamp >>> (0x2A ^ 0xA));
        result = (0x66 ^ 0x79) * result + this.tickTime;
        long temp = Double.doubleToLongBits(this.x);
        result = (0xA3 ^ 0xBC) * result + (int)(temp ^ temp >>> (0x97 ^ 0xB7));
        temp = Double.doubleToLongBits(this.y);
        result = (0xBE ^ 0xA1) * result + (int)(temp ^ temp >>> (0x68 ^ 0x48));
        temp = Double.doubleToLongBits(this.z);
        result = (0x30 ^ 0x2F) * result + (int)(temp ^ temp >>> (0xE0 ^ 0xC0));
        if (this.yaw != 0.0f) {
            n2 = Float.floatToIntBits(this.yaw);
            "".length();
            if (!true) {
                throw null;
            }
        } else {
            n2 = "".length();
        }
        result = (0x71 ^ 0x6E) * result + n2;
        if (this.pitch != 0.0f) {
            n = Float.floatToIntBits(this.pitch);
            "".length();
            if (4 <= 0) {
                throw null;
            }
        } else {
            n = "".length();
        }
        result = (0x4B ^ 0x54) * result + n;
        return result;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setOnGround(Boolean onGround) {
        this.onGround = onGround;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public PlayerLocation clone() {
        return new PlayerLocation(this.timestamp, this.tickTime, this.x, this.y, this.z, this.yaw, this.pitch, this.onGround);
    }

    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public float getYaw() {
        return this.yaw;
    }
}

