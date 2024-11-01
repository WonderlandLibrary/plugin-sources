package com.elevatemc.anticheat.util.reach.box;

public class GetBoundingBox
{
    public static SimpleCollisionBox getBoundingBoxFromPosAndSize(double centerX, double minY, double centerZ, float width, float height) {
        double minX = centerX - (width / 2f);
        double maxX = centerX + (width / 2f);
        double maxY = minY + height;
        double minZ = centerZ - (width / 2f);
        double maxZ = centerZ + (width / 2f);

        return new SimpleCollisionBox(minX, minY, minZ, maxX, maxY, maxZ, false);
    }
}
