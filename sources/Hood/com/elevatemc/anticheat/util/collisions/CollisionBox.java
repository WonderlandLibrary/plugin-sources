package com.elevatemc.anticheat.util.collisions;

import com.elevatemc.anticheat.util.mcp.AxisAlignedBB;

import java.util.List;

public interface CollisionBox {

    CollisionBox copy();

    CollisionBox offset(double x, double y, double z);

    boolean isFullBlock();

    boolean isCollided(AxisAlignedBB boundingBox);

    boolean isCollided(CollisionBox collisionBox);

    List<SimpleCollisionBox> getBoxes();

    double calculateYOffset(AxisAlignedBB bb, double y);
}
