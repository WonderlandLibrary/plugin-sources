package com.elevatemc.anticheat.util.math.client.impl;


import com.elevatemc.anticheat.util.math.MathHelper;
import com.elevatemc.anticheat.util.math.client.ClientMath;

public class VanillaMath implements ClientMath {

    public static float sqrt(float f) {
        return (float) Math.sqrt(f);
    }

    private static final float[] SIN_TABLE = new float[65536];

    static {
        for (int i = 0; i < SIN_TABLE.length; ++i) {
            SIN_TABLE[i] = (float) Math.sin((double) i * Math.PI * 2.0D / 65536.0D);
        }
    }

    @Override
    public float sin(float value) {
        return MathHelper.sin(value);
    }
    @Override
    public float cos(float value) {
        return MathHelper.cos(value);
    }

}