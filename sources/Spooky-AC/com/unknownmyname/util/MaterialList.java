/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  org.bukkit.Material
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.Potion
 */
package com.unknownmyname.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import net.minecraft.server.v1_8_R3.ItemStack;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.potion.Potion;

public class MaterialList {
    public static final /* synthetic */ List<Object> STAIRS;
    public static final /* synthetic */ List<Object> PLACEABLE;
    public static final /* synthetic */ Set<Object> BAD_VELOCITY;
    public static final /* synthetic */ Set<Object> GATES;
    public static final /* synthetic */ List<Object> SLABS;
    public static final /* synthetic */ List<Object> INPASSABLE;
    public static final /* synthetic */ Set<Object> INVALID_SHAPE;
    public static final /* synthetic */ Set<Object> FENCES;
    public static final /* synthetic */ List<Object> ICE;

    static {
        Object[] arrobject = new Material[0x77 ^ 0x6E];
        arrobject["".length()] = Material.NETHER_BRICK_STAIRS;
        arrobject[" ".length()] = Material.QUARTZ_STAIRS;
        arrobject["  ".length()] = Material.SANDSTONE_STAIRS;
        arrobject["   ".length()] = Material.RED_SANDSTONE_STAIRS;
        arrobject[148 ^ 144] = Material.SMOOTH_STAIRS;
        arrobject[43 ^ 46] = Material.SPRUCE_WOOD_STAIRS;
        arrobject[4 ^ 2] = Material.WOOD_STAIRS;
        arrobject[75 ^ 76] = Material.SNOW;
        arrobject[54 ^ 62] = Material.STONE_SLAB2;
        arrobject[144 ^ 153] = Material.STEP;
        arrobject[167 ^ 173] = Material.WOOD_STEP;
        arrobject[159 ^ 148] = Material.CARPET;
        arrobject[135 ^ 139] = Material.CHEST;
        arrobject[117 ^ 120] = Material.ENDER_CHEST;
        arrobject[107 ^ 101] = Material.TRAPPED_CHEST;
        arrobject[129 ^ 142] = Material.ENDER_PORTAL_FRAME;
        arrobject[119 ^ 103] = Material.TRAP_DOOR;
        arrobject[102 ^ 119] = Material.SLIME_BLOCK;
        arrobject[184 ^ 170] = Material.WATER_LILY;
        arrobject[1 ^ 18] = Material.REDSTONE_COMPARATOR;
        arrobject[92 ^ 72] = Material.TRAP_DOOR;
        arrobject[95 ^ 74] = Material.CAULDRON;
        arrobject[176 ^ 166] = Material.STATIONARY_WATER;
        arrobject[6 ^ 17] = Material.FENCE;
        arrobject[41 ^ 49] = Material.HOPPER;
        INVALID_SHAPE = ImmutableSet.of((Object)Material.ACACIA_STAIRS, (Object)Material.BIRCH_WOOD_STAIRS, (Object)Material.BRICK_STAIRS, (Object)Material.COBBLESTONE_STAIRS, (Object)Material.DARK_OAK_STAIRS, (Object)Material.JUNGLE_WOOD_STAIRS, (Object[])arrobject);
        Object[] arrobject2 = new Material[0x68 ^ 0x6D];
        arrobject2["".length()] = Material.JUNGLE_FENCE;
        arrobject2[" ".length()] = Material.NETHER_FENCE;
        arrobject2["  ".length()] = Material.SPRUCE_FENCE;
        arrobject2["   ".length()] = Material.STAINED_GLASS_PANE;
        arrobject2[58 ^ 62] = Material.THIN_GLASS;
        FENCES = ImmutableSet.of((Object)Material.ACACIA_FENCE, (Object)Material.BIRCH_FENCE, (Object)Material.COBBLE_WALL, (Object)Material.DARK_OAK_FENCE, (Object)Material.FENCE, (Object)Material.IRON_FENCE, (Object[])arrobject2);
        GATES = ImmutableSet.of((Object)Material.ACACIA_FENCE_GATE, (Object)Material.BIRCH_FENCE_GATE, (Object)Material.DARK_OAK_FENCE_GATE, (Object)Material.FENCE_GATE, (Object)Material.JUNGLE_FENCE_GATE, (Object)Material.SPRUCE_FENCE_GATE, (Object[])new Material["".length()]);
        Object[] arrobject3 = new Material[0x2A ^ 0x23];
        arrobject3["".length()] = Material.LADDER;
        arrobject3[" ".length()] = Material.VINE;
        arrobject3["  ".length()] = Material.PISTON_EXTENSION;
        arrobject3["   ".length()] = Material.PISTON_MOVING_PIECE;
        arrobject3[41 ^ 45] = Material.SNOW;
        arrobject3[107 ^ 110] = Material.FENCE;
        arrobject3[106 ^ 108] = Material.STONE_SLAB2;
        arrobject3[17 ^ 22] = Material.SOUL_SAND;
        arrobject3[41 ^ 33] = Material.CHEST;
        BAD_VELOCITY = ImmutableSet.of((Object)Material.WATER, (Object)Material.STATIONARY_WATER, (Object)Material.LAVA, (Object)Material.STATIONARY_LAVA, (Object)Material.WEB, (Object)Material.SLIME_BLOCK, (Object[])arrobject3);
        ICE = ImmutableList.of((Object)Material.PACKED_ICE, (Object)Material.ICE);
        SLABS = ImmutableList.of((Object)Material.STONE_SLAB2, (Object)Material.STEP, (Object)Material.WOOD_STEP);
        Object[] arrobject4 = new Material[" ".length()];
        arrobject4["".length()] = Material.WOOD_STAIRS;
        STAIRS = ImmutableList.of((Object)Material.ACACIA_STAIRS, (Object)Material.BIRCH_WOOD_STAIRS, (Object)Material.BRICK_STAIRS, (Object)Material.COBBLESTONE_STAIRS, (Object)Material.DARK_OAK_STAIRS, (Object)Material.JUNGLE_WOOD_STAIRS, (Object)Material.NETHER_BRICK_STAIRS, (Object)Material.QUARTZ_STAIRS, (Object)Material.SANDSTONE_STAIRS, (Object)Material.RED_SANDSTONE_STAIRS, (Object)Material.SMOOTH_STAIRS, (Object)Material.SPRUCE_WOOD_STAIRS, (Object[])arrobject4);
        Object[] arrobject5 = new Material[0x17 ^ 0xB];
        arrobject5["".length()] = Material.SMOOTH_BRICK;
        arrobject5[" ".length()] = Material.COAL_BLOCK;
        arrobject5["  ".length()] = Material.IRON_BLOCK;
        arrobject5["   ".length()] = Material.GOLD_BLOCK;
        arrobject5[118 ^ 114] = Material.DIAMOND_BLOCK;
        arrobject5[74 ^ 79] = Material.LAPIS_BLOCK;
        arrobject5[134 ^ 128] = Material.GLASS;
        arrobject5[81 ^ 86] = Material.STAINED_GLASS;
        arrobject5[169 ^ 161] = Material.ENDER_STONE;
        arrobject5[32 ^ 41] = Material.GLOWSTONE;
        arrobject5[90 ^ 80] = Material.SANDSTONE;
        arrobject5[65 ^ 74] = Material.RED_SANDSTONE;
        arrobject5[179 ^ 191] = Material.BOOKSHELF;
        arrobject5[114 ^ 127] = Material.NETHERRACK;
        arrobject5[31 ^ 17] = Material.CLAY;
        arrobject5[71 ^ 72] = Material.SNOW_BLOCK;
        arrobject5[151 ^ 135] = Material.MELON_BLOCK;
        arrobject5[14 ^ 31] = Material.EMERALD_BLOCK;
        arrobject5[164 ^ 182] = Material.QUARTZ_BLOCK;
        arrobject5[88 ^ 75] = Material.QUARTZ_ORE;
        arrobject5[174 ^ 186] = Material.COAL_ORE;
        arrobject5[67 ^ 86] = Material.DIAMOND_ORE;
        arrobject5[146 ^ 132] = Material.REDSTONE_ORE;
        arrobject5[107 ^ 124] = Material.EMERALD_ORE;
        arrobject5[76 ^ 84] = Material.GLOWING_REDSTONE_ORE;
        arrobject5[43 ^ 50] = Material.GOLD_ORE;
        arrobject5[101 ^ 127] = Material.IRON_ORE;
        arrobject5[25 ^ 2] = Material.LAPIS_ORE;
        INPASSABLE = ImmutableList.of((Object)Material.STONE, (Object)Material.GRASS, (Object)Material.DIRT, (Object)Material.COBBLESTONE, (Object)Material.MOSSY_COBBLESTONE, (Object)Material.WOOD, (Object)Material.BEDROCK, (Object)Material.WOOL, (Object)Material.LOG, (Object)Material.LOG_2, (Object)Material.CLAY_BRICK, (Object)Material.NETHER_BRICK, (Object[])arrobject5);
        PLACEABLE = ImmutableList.of((Object)Material.DIAMOND_SWORD, (Object)Material.GOLD_SWORD, (Object)Material.IRON_SWORD, (Object)Material.STONE_SWORD, (Object)Material.WOOD_SWORD, (Object)Material.GOLDEN_APPLE);
    }

    public static boolean canPlace(ItemStack nmsStack) {
        org.bukkit.inventory.ItemStack itemStack = CraftItemStack.asBukkitCopy((ItemStack)nmsStack);
        Material type = itemStack.getType();
        if (type == Material.POTION) {
            boolean bl;
            Potion potion = Potion.fromItemStack((org.bukkit.inventory.ItemStack)itemStack);
            if (potion.isSplash()) {
                bl = "".length();
                "".length();
                if (3 != 3) {
                    throw null;
                }
            } else {
                bl = " ".length();
            }
            return bl;
        }
        return PLACEABLE.contains((Object)type);
    }
}

