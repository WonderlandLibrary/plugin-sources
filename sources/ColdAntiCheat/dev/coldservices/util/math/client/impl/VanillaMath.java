package dev.coldservices.util.math.client.impl;

import dev.coldservices.util.math.client.ClientMath;
import dev.coldservices.util.mcp.MathHelper;

public class VanillaMath implements ClientMath {
    @Override
    public float sin(float value) {
        return MathHelper.sin(value);
    }

    @Override
    public float cos(float value) {
        return MathHelper.cos(value);
    }

    public static float sqrt(float f) {
        return (float) Math.sqrt(f);
    }
}