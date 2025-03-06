package me.frep.vulcan.spigot.util.material;

import org.bukkit.Bukkit;
import java.util.List;
import java.util.HashSet;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;
import com.google.common.cache.CacheLoader;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.apache.commons.lang.WordUtils;
import java.util.Iterator;
import java.util.Locale;
import java.util.Collection;
import org.bukkit.inventory.ItemStack;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import java.util.Optional;
import javax.annotation.Nullable;
import org.bukkit.Material;
import javax.annotation.Nonnull;
import java.util.Set;
import java.util.regex.Pattern;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Cache;
import java.util.Map;

public enum XMaterial
{
    ACACIA_BOAT(new String[] { "BOAT_ACACIA" }), 
    ACACIA_BUTTON(new String[] { "WOOD_BUTTON" }), 
    ACACIA_DOOR(new String[] { "ACACIA_DOOR", "ACACIA_DOOR_ITEM" }), 
    ACACIA_FENCE, 
    ACACIA_FENCE_GATE, 
    ACACIA_LEAVES(new String[] { "LEAVES_2" }), 
    ACACIA_LOG(new String[] { "LOG_2" }), 
    ACACIA_PLANKS(4, new String[] { "WOOD" }), 
    ACACIA_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), 
    ACACIA_SAPLING(4, new String[] { "SAPLING" }), 
    ACACIA_SIGN(new String[] { "SIGN_POST", "SIGN" }), 
    ACACIA_SLAB(4, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), 
    ACACIA_STAIRS, 
    ACACIA_TRAPDOOR(new String[] { "TRAP_DOOR" }), 
    ACACIA_WALL_SIGN(new String[] { "WALL_SIGN" }), 
    ACACIA_WOOD(new String[] { "LOG_2" }), 
    ACTIVATOR_RAIL, 
    AIR, 
    ALLIUM(2, new String[] { "RED_ROSE" }), 
    ANCIENT_DEBRIS(16), 
    ANDESITE(5, new String[] { "STONE" }), 
    ANDESITE_SLAB, 
    ANDESITE_STAIRS, 
    ANDESITE_WALL, 
    ANVIL, 
    APPLE, 
    ARMOR_STAND, 
    ARROW, 
    ATTACHED_MELON_STEM(7, new String[] { "MELON_STEM" }), 
    ATTACHED_PUMPKIN_STEM(7, new String[] { "PUMPKIN_STEM" }), 
    AZURE_BLUET(3, new String[] { "RED_ROSE" }), 
    BAKED_POTATO, 
    BAMBOO(0, 14, new String[0]), 
    BAMBOO_SAPLING(14), 
    BARREL(0, 14, new String[0]), 
    BARRIER, 
    BASALT(16), 
    BAT_SPAWN_EGG(65, new String[] { "MONSTER_EGG" }), 
    BEACON, 
    BEDROCK, 
    BEEF(new String[] { "RAW_BEEF" }), 
    BEEHIVE(15), 
    BEETROOT(new String[] { "BEETROOT_BLOCK" }), 
    BEETROOTS(new String[] { "BEETROOT" }), 
    BEETROOT_SEEDS, 
    BEETROOT_SOUP, 
    BEE_NEST(15), 
    BEE_SPAWN_EGG(15), 
    BELL(14), 
    BIRCH_BOAT(new String[] { "BOAT_BIRCH" }), 
    BIRCH_BUTTON(new String[] { "WOOD_BUTTON" }), 
    BIRCH_DOOR(new String[] { "BIRCH_DOOR", "BIRCH_DOOR_ITEM" }), 
    BIRCH_FENCE, 
    BIRCH_FENCE_GATE, 
    BIRCH_LEAVES(2, new String[] { "LEAVES" }), 
    BIRCH_LOG(2, new String[] { "LOG" }), 
    BIRCH_PLANKS(2, new String[] { "WOOD" }), 
    BIRCH_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), 
    BIRCH_SAPLING(2, new String[] { "SAPLING" }), 
    BIRCH_SIGN(new String[] { "SIGN_POST", "SIGN" }), 
    BIRCH_SLAB(2, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), 
    BIRCH_STAIRS(new String[] { "BIRCH_WOOD_STAIRS" }), 
    BIRCH_TRAPDOOR(new String[] { "TRAP_DOOR" }), 
    BIRCH_WALL_SIGN(new String[] { "WALL_SIGN" }), 
    BIRCH_WOOD(2, new String[] { "LOG" }), 
    BLACKSTONE(16), 
    BLACKSTONE_SLAB(16), 
    BLACKSTONE_STAIRS(16), 
    BLACKSTONE_WALL(16), 
    BLACK_BANNER(new String[] { "STANDING_BANNER", "BANNER" }), 
    BLACK_BED(15, new String[] { "BED_BLOCK", "BED" }), 
    BLACK_CARPET(15, new String[] { "CARPET" }), 
    BLACK_CONCRETE(15, new String[] { "CONCRETE" }), 
    BLACK_CONCRETE_POWDER(15, new String[] { "CONCRETE_POWDER" }), 
    BLACK_DYE(0, 14, new String[] { "INK_SACK", "INK_SAC" }), 
    BLACK_GLAZED_TERRACOTTA(15, 12, new String[0]), 
    BLACK_SHULKER_BOX, 
    BLACK_STAINED_GLASS(15, new String[] { "STAINED_GLASS" }), 
    BLACK_STAINED_GLASS_PANE(15, new String[] { "STAINED_GLASS_PANE" }), 
    BLACK_TERRACOTTA(15, new String[] { "STAINED_CLAY" }), 
    BLACK_WALL_BANNER(new String[] { "WALL_BANNER" }), 
    BLACK_WOOL(15, new String[] { "WOOL" }), 
    BLAST_FURNACE(0, 14, new String[0]), 
    BLAZE_POWDER, 
    BLAZE_ROD, 
    BLAZE_SPAWN_EGG(61, new String[] { "MONSTER_EGG" }), 
    BLUE_BANNER(4, new String[] { "STANDING_BANNER", "BANNER" }), 
    BLUE_BED(11, new String[] { "BED_BLOCK", "BED" }), 
    BLUE_CARPET(11, new String[] { "CARPET" }), 
    BLUE_CONCRETE(11, new String[] { "CONCRETE" }), 
    BLUE_CONCRETE_POWDER(11, new String[] { "CONCRETE_POWDER" }), 
    BLUE_DYE(4, new String[] { "INK_SACK", "LAPIS_LAZULI" }), 
    BLUE_GLAZED_TERRACOTTA(11, 12, new String[0]), 
    BLUE_ICE(0, 13, new String[0]), 
    BLUE_ORCHID(1, new String[] { "RED_ROSE" }), 
    BLUE_SHULKER_BOX, 
    BLUE_STAINED_GLASS(11, new String[] { "STAINED_GLASS" }), 
    BLUE_STAINED_GLASS_PANE(11, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    BLUE_TERRACOTTA(11, new String[] { "STAINED_CLAY" }), 
    BLUE_WALL_BANNER(4, new String[] { "WALL_BANNER" }), 
    BLUE_WOOL(11, new String[] { "WOOL" }), 
    BONE, 
    BONE_BLOCK, 
    BONE_MEAL(15, new String[] { "INK_SACK" }), 
    BOOK, 
    BOOKSHELF, 
    BOW, 
    BOWL, 
    BRAIN_CORAL(13), 
    BRAIN_CORAL_BLOCK(13), 
    BRAIN_CORAL_FAN(13), 
    BRAIN_CORAL_WALL_FAN, 
    BREAD, 
    BREWING_STAND(new String[] { "BREWING_STAND", "BREWING_STAND_ITEM" }), 
    BRICK(new String[] { "CLAY_BRICK" }), 
    BRICKS(new String[] { "BRICK" }), 
    BRICK_SLAB(4, new String[] { "STEP" }), 
    BRICK_STAIRS, 
    BRICK_WALL, 
    BROWN_BANNER(3, new String[] { "STANDING_BANNER", "BANNER" }), 
    BROWN_BED(12, new String[] { "BED_BLOCK", "BED" }), 
    BROWN_CARPET(12, new String[] { "CARPET" }), 
    BROWN_CONCRETE(12, new String[] { "CONCRETE" }), 
    BROWN_CONCRETE_POWDER(12, new String[] { "CONCRETE_POWDER" }), 
    BROWN_DYE(3, new String[] { "INK_SACK", "DYE", "COCOA_BEANS" }), 
    BROWN_GLAZED_TERRACOTTA(12, 12, new String[0]), 
    BROWN_MUSHROOM, 
    BROWN_MUSHROOM_BLOCK(new String[] { "BROWN_MUSHROOM", "HUGE_MUSHROOM_1" }), 
    BROWN_SHULKER_BOX, 
    BROWN_STAINED_GLASS(12, new String[] { "STAINED_GLASS" }), 
    BROWN_STAINED_GLASS_PANE(12, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    BROWN_TERRACOTTA(12, new String[] { "STAINED_CLAY" }), 
    BROWN_WALL_BANNER(3, new String[] { "WALL_BANNER" }), 
    BROWN_WOOL(12, new String[] { "WOOL" }), 
    BUBBLE_COLUMN(13), 
    BUBBLE_CORAL(13), 
    BUBBLE_CORAL_BLOCK(13), 
    BUBBLE_CORAL_FAN(13), 
    BUBBLE_CORAL_WALL_FAN, 
    BUCKET, 
    CACTUS, 
    CAKE(new String[] { "CAKE_BLOCK" }), 
    CAMPFIRE(14), 
    CARROT(new String[] { "CARROT_ITEM" }), 
    CARROTS(new String[] { "CARROT" }), 
    CARROT_ON_A_STICK(new String[] { "CARROT_STICK" }), 
    CARTOGRAPHY_TABLE(0, 14, new String[0]), 
    CARVED_PUMPKIN(1, 13, new String[0]), 
    CAT_SPAWN_EGG, 
    CAULDRON(new String[] { "CAULDRON", "CAULDRON_ITEM" }), 
    CAVE_AIR(new String[] { "AIR" }), 
    CAVE_SPIDER_SPAWN_EGG(59, new String[] { "MONSTER_EGG" }), 
    CHAIN(16), 
    CHAINMAIL_BOOTS, 
    CHAINMAIL_CHESTPLATE, 
    CHAINMAIL_HELMET, 
    CHAINMAIL_LEGGINGS, 
    CHAIN_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_CHAIN" }), 
    CHARCOAL(1, new String[] { "COAL" }), 
    CHEST(new String[] { "LOCKED_CHEST" }), 
    CHEST_MINECART(new String[] { "STORAGE_MINECART" }), 
    CHICKEN(new String[] { "RAW_CHICKEN" }), 
    CHICKEN_SPAWN_EGG(93, new String[] { "MONSTER_EGG" }), 
    CHIPPED_ANVIL(1, new String[] { "ANVIL" }), 
    CHISELED_NETHER_BRICKS(1, new String[] { "NETHER_BRICKS" }), 
    CHISELED_POLISHED_BLACKSTONE(0, 16, new String[] { "POLISHED_BLACKSTONE" }), 
    CHISELED_QUARTZ_BLOCK(1, new String[] { "QUARTZ_BLOCK" }), 
    CHISELED_RED_SANDSTONE(1, new String[] { "RED_SANDSTONE" }), 
    CHISELED_SANDSTONE(1, new String[] { "SANDSTONE" }), 
    CHISELED_STONE_BRICKS(3, new String[] { "SMOOTH_BRICK" }), 
    CHORUS_FLOWER(0, 9, new String[0]), 
    CHORUS_FRUIT(0, 9, new String[0]), 
    CHORUS_PLANT(0, 9, new String[0]), 
    CLAY(new String[] { "HARD_CLAY" }), 
    CLAY_BALL, 
    CLOCK(new String[] { "WATCH" }), 
    COAL, 
    COAL_BLOCK, 
    COAL_ORE, 
    COARSE_DIRT(1, new String[] { "DIRT" }), 
    COBBLESTONE, 
    COBBLESTONE_SLAB(3, new String[] { "STEP" }), 
    COBBLESTONE_STAIRS, 
    COBBLESTONE_WALL(new String[] { "COBBLE_WALL" }), 
    COBWEB(new String[] { "WEB" }), 
    COCOA(15), 
    COCOA_BEANS(3, new String[] { "INK_SACK" }), 
    COD(new String[] { "RAW_FISH" }), 
    COD_BUCKET(0, 13, new String[0]), 
    COD_SPAWN_EGG(0, 13, new String[0]), 
    COMMAND_BLOCK(new String[] { "COMMAND" }), 
    COMMAND_BLOCK_MINECART(new String[] { "COMMAND_MINECART" }), 
    COMPARATOR(new String[] { "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "REDSTONE_COMPARATOR" }), 
    COMPASS, 
    COMPOSTER(0, 14, new String[0]), 
    CONDUIT(0, 13, new String[] { "BEACON" }), 
    COOKED_BEEF, 
    COOKED_CHICKEN, 
    COOKED_COD(new String[] { "COOKED_FISH" }), 
    COOKED_MUTTON, 
    COOKED_PORKCHOP(new String[] { "PORK", "GRILLED_PORK" }), 
    COOKED_RABBIT, 
    COOKED_SALMON(1, new String[] { "COOKED_FISH" }), 
    COOKIE, 
    CORNFLOWER(4, 14, new String[0]), 
    COW_SPAWN_EGG(92, new String[] { "MONSTER_EGG" }), 
    CRACKED_NETHER_BRICKS(2, new String[] { "NETHER_BRICKS" }), 
    CRACKED_POLISHED_BLACKSTONE_BRICKS(0, 16, new String[] { "POLISHED_BLACKSTONE_BRICKS" }), 
    CRACKED_STONE_BRICKS(2, new String[] { "SMOOTH_BRICK" }), 
    CRAFTING_TABLE(new String[] { "WORKBENCH" }), 
    CREEPER_BANNER_PATTERN, 
    CREEPER_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }), 
    CREEPER_SPAWN_EGG(50, new String[] { "MONSTER_EGG" }), 
    CREEPER_WALL_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }), 
    CRIMSON_BUTTON(16), 
    CRIMSON_DOOR(16), 
    CRIMSON_FENCE(16), 
    CRIMSON_FENCE_GATE(16), 
    CRIMSON_FUNGUS(16), 
    CRIMSON_HYPHAE(16), 
    CRIMSON_NYLIUM(16), 
    CRIMSON_PLANKS(16), 
    CRIMSON_PRESSURE_PLATE(16), 
    CRIMSON_ROOTS(16), 
    CRIMSON_SIGN(0, 16, new String[] { "SIGN_POST" }), 
    CRIMSON_SLAB(16), 
    CRIMSON_STAIRS(16), 
    CRIMSON_STEM(16), 
    CRIMSON_TRAPDOOR(16), 
    CRIMSON_WALL_SIGN(0, 16, new String[] { "WALL_SIGN" }), 
    CROSSBOW, 
    CRYING_OBSIDIAN(16), 
    CUT_RED_SANDSTONE(13), 
    CUT_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }), 
    CUT_SANDSTONE(13), 
    CUT_SANDSTONE_SLAB(new String[] { "STEP" }), 
    CYAN_BANNER(6, new String[] { "STANDING_BANNER", "BANNER" }), 
    CYAN_BED(9, new String[] { "BED_BLOCK", "BED" }), 
    CYAN_CARPET(9, new String[] { "CARPET" }), 
    CYAN_CONCRETE(9, new String[] { "CONCRETE" }), 
    CYAN_CONCRETE_POWDER(9, new String[] { "CONCRETE_POWDER" }), 
    CYAN_DYE(6, new String[] { "INK_SACK" }), 
    CYAN_GLAZED_TERRACOTTA(9, 12, new String[0]), 
    CYAN_SHULKER_BOX, 
    CYAN_STAINED_GLASS(9, new String[] { "STAINED_GLASS" }), 
    CYAN_STAINED_GLASS_PANE(9, new String[] { "STAINED_GLASS_PANE" }), 
    CYAN_TERRACOTTA(9, new String[] { "STAINED_CLAY" }), 
    CYAN_WALL_BANNER(6, new String[] { "WALL_BANNER" }), 
    CYAN_WOOL(9, new String[] { "WOOL" }), 
    DAMAGED_ANVIL(2, new String[] { "ANVIL" }), 
    DANDELION(new String[] { "YELLOW_FLOWER" }), 
    DARK_OAK_BOAT(new String[] { "BOAT_DARK_OAK" }), 
    DARK_OAK_BUTTON(new String[] { "WOOD_BUTTON" }), 
    DARK_OAK_DOOR(new String[] { "DARK_OAK_DOOR", "DARK_OAK_DOOR_ITEM" }), 
    DARK_OAK_FENCE, 
    DARK_OAK_FENCE_GATE, 
    DARK_OAK_LEAVES(4, new String[] { "LEAVES", "LEAVES_2" }), 
    DARK_OAK_LOG(1, new String[] { "LOG", "LOG_2" }), 
    DARK_OAK_PLANKS(5, new String[] { "WOOD" }), 
    DARK_OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), 
    DARK_OAK_SAPLING(5, new String[] { "SAPLING" }), 
    DARK_OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }), 
    DARK_OAK_SLAB(5, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), 
    DARK_OAK_STAIRS, 
    DARK_OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }), 
    DARK_OAK_WALL_SIGN(new String[] { "WALL_SIGN" }), 
    DARK_OAK_WOOD(1, new String[] { "LOG", "LOG_2" }), 
    DARK_PRISMARINE(1, new String[] { "PRISMARINE" }), 
    DARK_PRISMARINE_SLAB(13), 
    DARK_PRISMARINE_STAIRS(13), 
    DAYLIGHT_DETECTOR(new String[] { "DAYLIGHT_DETECTOR_INVERTED" }), 
    DEAD_BRAIN_CORAL(13), 
    DEAD_BRAIN_CORAL_BLOCK(13), 
    DEAD_BRAIN_CORAL_FAN(13), 
    DEAD_BRAIN_CORAL_WALL_FAN(13), 
    DEAD_BUBBLE_CORAL(13), 
    DEAD_BUBBLE_CORAL_BLOCK(13), 
    DEAD_BUBBLE_CORAL_FAN(13), 
    DEAD_BUBBLE_CORAL_WALL_FAN(13), 
    DEAD_BUSH, 
    DEAD_FIRE_CORAL(13), 
    DEAD_FIRE_CORAL_BLOCK(13), 
    DEAD_FIRE_CORAL_FAN(13), 
    DEAD_FIRE_CORAL_WALL_FAN(13), 
    DEAD_HORN_CORAL(13), 
    DEAD_HORN_CORAL_BLOCK(13), 
    DEAD_HORN_CORAL_FAN(13), 
    DEAD_HORN_CORAL_WALL_FAN(13), 
    DEAD_TUBE_CORAL(13), 
    DEAD_TUBE_CORAL_BLOCK(13), 
    DEAD_TUBE_CORAL_FAN(13), 
    DEAD_TUBE_CORAL_WALL_FAN(13), 
    DEBUG_STICK(0, 13, new String[0]), 
    DETECTOR_RAIL, 
    DIAMOND, 
    DIAMOND_AXE, 
    DIAMOND_BLOCK, 
    DIAMOND_BOOTS, 
    DIAMOND_CHESTPLATE, 
    DIAMOND_HELMET, 
    DIAMOND_HOE, 
    DIAMOND_HORSE_ARMOR(new String[] { "DIAMOND_BARDING" }), 
    DIAMOND_LEGGINGS, 
    DIAMOND_ORE, 
    DIAMOND_PICKAXE, 
    DIAMOND_SHOVEL(new String[] { "DIAMOND_SPADE" }), 
    DIAMOND_SWORD, 
    DIORITE(3, new String[] { "STONE" }), 
    DIORITE_SLAB, 
    DIORITE_STAIRS, 
    DIORITE_WALL, 
    DIRT, 
    DISPENSER, 
    DOLPHIN_SPAWN_EGG(0, 13, new String[0]), 
    DONKEY_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }), 
    DRAGON_BREATH(new String[] { "DRAGONS_BREATH" }), 
    DRAGON_EGG, 
    DRAGON_HEAD(5, 9, new String[] { "SKULL", "SKULL_ITEM" }), 
    DRAGON_WALL_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }), 
    DRIED_KELP(13), 
    DRIED_KELP_BLOCK(13), 
    DROPPER, 
    DROWNED_SPAWN_EGG(0, 13, new String[0]), 
    EGG, 
    ELDER_GUARDIAN_SPAWN_EGG(4, new String[] { "MONSTER_EGG" }), 
    ELYTRA, 
    EMERALD, 
    EMERALD_BLOCK, 
    EMERALD_ORE, 
    ENCHANTED_BOOK, 
    ENCHANTED_GOLDEN_APPLE(1, new String[] { "GOLDEN_APPLE" }), 
    ENCHANTING_TABLE(new String[] { "ENCHANTMENT_TABLE" }), 
    ENDERMAN_SPAWN_EGG(58, new String[] { "MONSTER_EGG" }), 
    ENDERMITE_SPAWN_EGG(67, new String[] { "MONSTER_EGG" }), 
    ENDER_CHEST, 
    ENDER_EYE(new String[] { "EYE_OF_ENDER" }), 
    ENDER_PEARL, 
    END_CRYSTAL, 
    END_GATEWAY(0, 9, new String[0]), 
    END_PORTAL(new String[] { "ENDER_PORTAL" }), 
    END_PORTAL_FRAME(new String[] { "ENDER_PORTAL_FRAME" }), 
    END_ROD(0, 9, new String[0]), 
    END_STONE(new String[] { "ENDER_STONE" }), 
    END_STONE_BRICKS(new String[] { "END_BRICKS" }), 
    END_STONE_BRICK_SLAB(6, new String[] { "STEP" }), 
    END_STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }), 
    END_STONE_BRICK_WALL, 
    EVOKER_SPAWN_EGG(34, new String[] { "MONSTER_EGG" }), 
    EXPERIENCE_BOTTLE(new String[] { "EXP_BOTTLE" }), 
    FARMLAND(new String[] { "SOIL" }), 
    FEATHER, 
    FERMENTED_SPIDER_EYE, 
    FERN(2, new String[] { "LONG_GRASS" }), 
    FILLED_MAP(new String[] { "MAP" }), 
    FIRE, 
    FIREWORK_ROCKET(new String[] { "FIREWORK" }), 
    FIREWORK_STAR(new String[] { "FIREWORK_CHARGE" }), 
    FIRE_CHARGE(new String[] { "FIREBALL" }), 
    FIRE_CORAL(13), 
    FIRE_CORAL_BLOCK(13), 
    FIRE_CORAL_FAN(13), 
    FIRE_CORAL_WALL_FAN, 
    FISHING_ROD, 
    FLETCHING_TABLE(0, 14, new String[0]), 
    FLINT, 
    FLINT_AND_STEEL, 
    FLOWER_BANNER_PATTERN, 
    FLOWER_POT(new String[] { "FLOWER_POT", "FLOWER_POT_ITEM" }), 
    FOX_SPAWN_EGG(14), 
    FROSTED_ICE(0, 9, new String[0]), 
    FURNACE(new String[] { "BURNING_FURNACE" }), 
    FURNACE_MINECART(new String[] { "POWERED_MINECART" }), 
    GHAST_SPAWN_EGG(56, new String[] { "MONSTER_EGG" }), 
    GHAST_TEAR, 
    GILDED_BLACKSTONE(16), 
    GLASS, 
    GLASS_BOTTLE, 
    GLASS_PANE(new String[] { "THIN_GLASS" }), 
    GLISTERING_MELON_SLICE(new String[] { "SPECKLED_MELON" }), 
    GLOBE_BANNER_PATTERN, 
    GLOWSTONE, 
    GLOWSTONE_DUST, 
    GOLDEN_APPLE, 
    GOLDEN_AXE(new String[] { "GOLD_AXE" }), 
    GOLDEN_BOOTS(new String[] { "GOLD_BOOTS" }), 
    GOLDEN_CARROT, 
    GOLDEN_CHESTPLATE(new String[] { "GOLD_CHESTPLATE" }), 
    GOLDEN_HELMET(new String[] { "GOLD_HELMET" }), 
    GOLDEN_HOE(new String[] { "GOLD_HOE" }), 
    GOLDEN_HORSE_ARMOR(new String[] { "GOLD_BARDING" }), 
    GOLDEN_LEGGINGS(new String[] { "GOLD_LEGGINGS" }), 
    GOLDEN_PICKAXE(new String[] { "GOLD_PICKAXE" }), 
    GOLDEN_SHOVEL(new String[] { "GOLD_SPADE" }), 
    GOLDEN_SWORD(new String[] { "GOLD_SWORD" }), 
    GOLD_BLOCK, 
    GOLD_INGOT, 
    GOLD_NUGGET, 
    GOLD_ORE, 
    GRANITE(1, new String[] { "STONE" }), 
    GRANITE_SLAB, 
    GRANITE_STAIRS, 
    GRANITE_WALL, 
    GRASS(1, new String[] { "LONG_GRASS" }), 
    GRASS_BLOCK(new String[] { "GRASS" }), 
    GRASS_PATH, 
    GRAVEL, 
    GRAY_BANNER(8, new String[] { "STANDING_BANNER", "BANNER" }), 
    GRAY_BED(7, new String[] { "BED_BLOCK", "BED" }), 
    GRAY_CARPET(7, new String[] { "CARPET" }), 
    GRAY_CONCRETE(7, new String[] { "CONCRETE" }), 
    GRAY_CONCRETE_POWDER(7, new String[] { "CONCRETE_POWDER" }), 
    GRAY_DYE(8, new String[] { "INK_SACK" }), 
    GRAY_GLAZED_TERRACOTTA(7, 12, new String[0]), 
    GRAY_SHULKER_BOX, 
    GRAY_STAINED_GLASS(7, new String[] { "STAINED_GLASS" }), 
    GRAY_STAINED_GLASS_PANE(7, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    GRAY_TERRACOTTA(7, new String[] { "STAINED_CLAY" }), 
    GRAY_WALL_BANNER(8, new String[] { "WALL_BANNER" }), 
    GRAY_WOOL(7, new String[] { "WOOL" }), 
    GREEN_BANNER(2, new String[] { "STANDING_BANNER", "BANNER" }), 
    GREEN_BED(13, new String[] { "BED_BLOCK", "BED" }), 
    GREEN_CARPET(13, new String[] { "CARPET" }), 
    GREEN_CONCRETE(13, new String[] { "CONCRETE" }), 
    GREEN_CONCRETE_POWDER(13, new String[] { "CONCRETE_POWDER" }), 
    GREEN_DYE(2, new String[] { "INK_SACK", "CACTUS_GREEN" }), 
    GREEN_GLAZED_TERRACOTTA(13, 12, new String[0]), 
    GREEN_SHULKER_BOX, 
    GREEN_STAINED_GLASS(13, new String[] { "STAINED_GLASS" }), 
    GREEN_STAINED_GLASS_PANE(13, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    GREEN_TERRACOTTA(13, new String[] { "STAINED_CLAY" }), 
    GREEN_WALL_BANNER(2, new String[] { "WALL_BANNER" }), 
    GREEN_WOOL(13, new String[] { "WOOL" }), 
    GRINDSTONE(0, 14, new String[0]), 
    GUARDIAN_SPAWN_EGG(68, new String[] { "MONSTER_EGG" }), 
    GUNPOWDER(new String[] { "SULPHUR" }), 
    HAY_BLOCK, 
    HEART_OF_THE_SEA(13), 
    HEAVY_WEIGHTED_PRESSURE_PLATE(new String[] { "IRON_PLATE" }), 
    HOGLIN_SPAWN_EGG(0, 16, new String[] { "MONSTER_EGG" }), 
    HONEYCOMB(15), 
    HONEYCOMB_BLOCK(15), 
    HONEY_BLOCK(0, 15, new String[0]), 
    HONEY_BOTTLE(0, 15, new String[0]), 
    HOPPER, 
    HOPPER_MINECART, 
    HORN_CORAL(13), 
    HORN_CORAL_BLOCK(13), 
    HORN_CORAL_FAN(13), 
    HORN_CORAL_WALL_FAN, 
    HORSE_SPAWN_EGG(100, new String[] { "MONSTER_EGG" }), 
    HUSK_SPAWN_EGG(23, new String[] { "MONSTER_EGG" }), 
    ICE, 
    INFESTED_CHISELED_STONE_BRICKS(5, new String[] { "MONSTER_EGGS", "SMOOTH_BRICK" }), 
    INFESTED_COBBLESTONE(1, new String[] { "MONSTER_EGGS" }), 
    INFESTED_CRACKED_STONE_BRICKS(4, new String[] { "MONSTER_EGGS", "SMOOTH_BRICK" }), 
    INFESTED_MOSSY_STONE_BRICKS(3, new String[] { "MONSTER_EGGS" }), 
    INFESTED_STONE(new String[] { "MONSTER_EGGS" }), 
    INFESTED_STONE_BRICKS(2, new String[] { "MONSTER_EGGS", "SMOOTH_BRICK" }), 
    INK_SAC(new String[] { "INK_SACK" }), 
    IRON_AXE, 
    IRON_BARS(new String[] { "IRON_FENCE" }), 
    IRON_BLOCK, 
    IRON_BOOTS, 
    IRON_CHESTPLATE, 
    IRON_DOOR(new String[] { "IRON_DOOR_BLOCK" }), 
    IRON_HELMET, 
    IRON_HOE, 
    IRON_HORSE_ARMOR(new String[] { "IRON_BARDING" }), 
    IRON_INGOT, 
    IRON_LEGGINGS, 
    IRON_NUGGET, 
    IRON_ORE, 
    IRON_PICKAXE, 
    IRON_SHOVEL(new String[] { "IRON_SPADE" }), 
    IRON_SWORD, 
    IRON_TRAPDOOR, 
    ITEM_FRAME, 
    JACK_O_LANTERN, 
    JIGSAW(0, 14, new String[0]), 
    JUKEBOX, 
    JUNGLE_BOAT(new String[] { "BOAT_JUNGLE" }), 
    JUNGLE_BUTTON(new String[] { "WOOD_BUTTON" }), 
    JUNGLE_DOOR(new String[] { "JUNGLE_DOOR", "JUNGLE_DOOR_ITEM" }), 
    JUNGLE_FENCE, 
    JUNGLE_FENCE_GATE, 
    JUNGLE_LEAVES(3, new String[] { "LEAVES" }), 
    JUNGLE_LOG(3, new String[] { "LOG" }), 
    JUNGLE_PLANKS(3, new String[] { "WOOD" }), 
    JUNGLE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), 
    JUNGLE_SAPLING(3, new String[] { "SAPLING" }), 
    JUNGLE_SIGN(new String[] { "SIGN_POST", "SIGN" }), 
    JUNGLE_SLAB(3, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), 
    JUNGLE_STAIRS(new String[] { "JUNGLE_WOOD_STAIRS" }), 
    JUNGLE_TRAPDOOR(new String[] { "TRAP_DOOR" }), 
    JUNGLE_WALL_SIGN(new String[] { "WALL_SIGN" }), 
    JUNGLE_WOOD(3, new String[] { "LOG" }), 
    KELP(13), 
    KELP_PLANT(13), 
    KNOWLEDGE_BOOK(0, 12, new String[] { "BOOK" }), 
    LADDER, 
    LANTERN(0, 14, new String[0]), 
    LAPIS_BLOCK, 
    LAPIS_LAZULI(4, new String[] { "INK_SACK" }), 
    LAPIS_ORE, 
    LARGE_FERN(3, new String[] { "DOUBLE_PLANT" }), 
    LAVA(new String[] { "STATIONARY_LAVA" }), 
    LAVA_BUCKET, 
    LEAD(new String[] { "LEASH" }), 
    LEATHER, 
    LEATHER_BOOTS, 
    LEATHER_CHESTPLATE, 
    LEATHER_HELMET, 
    LEATHER_HORSE_ARMOR(0, 14, new String[] { "IRON_HORSE_ARMOR" }), 
    LEATHER_LEGGINGS, 
    LECTERN(0, 14, new String[0]), 
    LEVER, 
    LIGHT_BLUE_BANNER(12, new String[] { "STANDING_BANNER", "BANNER" }), 
    LIGHT_BLUE_BED(3, new String[] { "BED_BLOCK", "BED" }), 
    LIGHT_BLUE_CARPET(3, new String[] { "CARPET" }), 
    LIGHT_BLUE_CONCRETE(3, new String[] { "CONCRETE" }), 
    LIGHT_BLUE_CONCRETE_POWDER(3, new String[] { "CONCRETE_POWDER" }), 
    LIGHT_BLUE_DYE(12, new String[] { "INK_SACK" }), 
    LIGHT_BLUE_GLAZED_TERRACOTTA(3, 12, new String[0]), 
    LIGHT_BLUE_SHULKER_BOX, 
    LIGHT_BLUE_STAINED_GLASS(3, new String[] { "STAINED_GLASS" }), 
    LIGHT_BLUE_STAINED_GLASS_PANE(3, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    LIGHT_BLUE_TERRACOTTA(3, new String[] { "STAINED_CLAY" }), 
    LIGHT_BLUE_WALL_BANNER(12, new String[] { "WALL_BANNER", "STANDING_BANNER", "BANNER" }), 
    LIGHT_BLUE_WOOL(3, new String[] { "WOOL" }), 
    LIGHT_GRAY_BANNER(7, new String[] { "STANDING_BANNER", "BANNER" }), 
    LIGHT_GRAY_BED(8, new String[] { "BED_BLOCK", "BED" }), 
    LIGHT_GRAY_CARPET(8, new String[] { "CARPET" }), 
    LIGHT_GRAY_CONCRETE(8, new String[] { "CONCRETE" }), 
    LIGHT_GRAY_CONCRETE_POWDER(8, new String[] { "CONCRETE_POWDER" }), 
    LIGHT_GRAY_DYE(7, new String[] { "INK_SACK" }), 
    LIGHT_GRAY_GLAZED_TERRACOTTA(0, 12, new String[] { "STAINED_CLAY", "LIGHT_GRAY_TERRACOTTA", "SILVER_GLAZED_TERRACOTTA" }), 
    LIGHT_GRAY_SHULKER_BOX(new String[] { "SILVER_SHULKER_BOX" }), 
    LIGHT_GRAY_STAINED_GLASS(8, new String[] { "STAINED_GLASS" }), 
    LIGHT_GRAY_STAINED_GLASS_PANE(8, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    LIGHT_GRAY_TERRACOTTA(8, new String[] { "STAINED_CLAY" }), 
    LIGHT_GRAY_WALL_BANNER(7, new String[] { "WALL_BANNER" }), 
    LIGHT_GRAY_WOOL(8, new String[] { "WOOL" }), 
    LIGHT_WEIGHTED_PRESSURE_PLATE(new String[] { "GOLD_PLATE" }), 
    LILAC(1, new String[] { "DOUBLE_PLANT" }), 
    LILY_OF_THE_VALLEY(15, 14, new String[0]), 
    LILY_PAD(new String[] { "WATER_LILY" }), 
    LIME_BANNER(10, new String[] { "STANDING_BANNER", "BANNER" }), 
    LIME_BED(5, new String[] { "BED_BLOCK", "BED" }), 
    LIME_CARPET(5, new String[] { "CARPET" }), 
    LIME_CONCRETE(5, new String[] { "CONCRETE" }), 
    LIME_CONCRETE_POWDER(5, new String[] { "CONCRETE_POWDER" }), 
    LIME_DYE(10, new String[] { "INK_SACK" }), 
    LIME_GLAZED_TERRACOTTA(5, 12, new String[0]), 
    LIME_SHULKER_BOX, 
    LIME_STAINED_GLASS(5, new String[] { "STAINED_GLASS" }), 
    LIME_STAINED_GLASS_PANE(5, new String[] { "STAINED_GLASS_PANE" }), 
    LIME_TERRACOTTA(5, new String[] { "STAINED_CLAY" }), 
    LIME_WALL_BANNER(10, new String[] { "WALL_BANNER" }), 
    LIME_WOOL(5, new String[] { "WOOL" }), 
    LINGERING_POTION, 
    LLAMA_SPAWN_EGG(103, new String[] { "MONSTER_EGG" }), 
    LODESTONE(16), 
    LOOM(14), 
    MAGENTA_BANNER(13, new String[] { "STANDING_BANNER", "BANNER" }), 
    MAGENTA_BED(2, new String[] { "BED_BLOCK", "BED" }), 
    MAGENTA_CARPET(2, new String[] { "CARPET" }), 
    MAGENTA_CONCRETE(2, new String[] { "CONCRETE" }), 
    MAGENTA_CONCRETE_POWDER(2, new String[] { "CONCRETE_POWDER" }), 
    MAGENTA_DYE(13, new String[] { "INK_SACK" }), 
    MAGENTA_GLAZED_TERRACOTTA(2, 12, new String[0]), 
    MAGENTA_SHULKER_BOX, 
    MAGENTA_STAINED_GLASS(2, new String[] { "STAINED_GLASS" }), 
    MAGENTA_STAINED_GLASS_PANE(2, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    MAGENTA_TERRACOTTA(2, new String[] { "STAINED_CLAY" }), 
    MAGENTA_WALL_BANNER(13, new String[] { "WALL_BANNER" }), 
    MAGENTA_WOOL(2, new String[] { "WOOL" }), 
    MAGMA_BLOCK(0, 10, new String[] { "MAGMA" }), 
    MAGMA_CREAM, 
    MAGMA_CUBE_SPAWN_EGG(62, new String[] { "MONSTER_EGG" }), 
    MAP(new String[] { "EMPTY_MAP" }), 
    MELON(new String[] { "MELON_BLOCK" }), 
    MELON_SEEDS, 
    MELON_SLICE(new String[] { "MELON" }), 
    MELON_STEM, 
    MILK_BUCKET, 
    MINECART, 
    MOJANG_BANNER_PATTERN, 
    MOOSHROOM_SPAWN_EGG(96, new String[] { "MONSTER_EGG" }), 
    MOSSY_COBBLESTONE, 
    MOSSY_COBBLESTONE_SLAB(3, new String[] { "STEP" }), 
    MOSSY_COBBLESTONE_STAIRS, 
    MOSSY_COBBLESTONE_WALL(1, new String[] { "COBBLE_WALL", "COBBLESTONE_WALL" }), 
    MOSSY_STONE_BRICKS(1, new String[] { "SMOOTH_BRICK" }), 
    MOSSY_STONE_BRICK_SLAB(5, new String[] { "STEP" }), 
    MOSSY_STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }), 
    MOSSY_STONE_BRICK_WALL, 
    MOVING_PISTON(new String[] { "PISTON_BASE", "PISTON_MOVING_PIECE" }), 
    MULE_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }), 
    MUSHROOM_STEM(new String[] { "BROWN_MUSHROOM" }), 
    MUSHROOM_STEW(new String[] { "MUSHROOM_SOUP" }), 
    MUSIC_DISC_11(new String[] { "GOLD_RECORD" }), 
    MUSIC_DISC_13(new String[] { "GREEN_RECORD" }), 
    MUSIC_DISC_BLOCKS(new String[] { "RECORD_3" }), 
    MUSIC_DISC_CAT(new String[] { "RECORD_4" }), 
    MUSIC_DISC_CHIRP(new String[] { "RECORD_5" }), 
    MUSIC_DISC_FAR(new String[] { "RECORD_6" }), 
    MUSIC_DISC_MALL(new String[] { "RECORD_7" }), 
    MUSIC_DISC_MELLOHI(new String[] { "RECORD_8" }), 
    MUSIC_DISC_PIGSTEP(16), 
    MUSIC_DISC_STAL(new String[] { "RECORD_9" }), 
    MUSIC_DISC_STRAD(new String[] { "RECORD_10" }), 
    MUSIC_DISC_WAIT(new String[] { "RECORD_11" }), 
    MUSIC_DISC_WARD(new String[] { "RECORD_12" }), 
    MUTTON, 
    MYCELIUM(new String[] { "MYCEL" }), 
    NAME_TAG, 
    NAUTILUS_SHELL(13), 
    NETHERITE_AXE(16), 
    NETHERITE_BLOCK(16), 
    NETHERITE_BOOTS(16), 
    NETHERITE_CHESTPLATE(16), 
    NETHERITE_HELMET(16), 
    NETHERITE_HOE(16), 
    NETHERITE_INGOT(16), 
    NETHERITE_LEGGINGS(16), 
    NETHERITE_PICKAXE(16), 
    NETHERITE_SCRAP(16), 
    NETHERITE_SHOVEL(16), 
    NETHERITE_SWORD(16), 
    NETHERRACK, 
    NETHER_BRICK(new String[] { "NETHER_BRICK_ITEM" }), 
    NETHER_BRICKS(new String[] { "NETHER_BRICK" }), 
    NETHER_BRICK_FENCE(new String[] { "NETHER_FENCE" }), 
    NETHER_BRICK_SLAB(6, new String[] { "STEP" }), 
    NETHER_BRICK_STAIRS, 
    NETHER_BRICK_WALL, 
    NETHER_GOLD_ORE(16), 
    NETHER_PORTAL(new String[] { "PORTAL" }), 
    NETHER_QUARTZ_ORE(new String[] { "QUARTZ_ORE" }), 
    NETHER_SPROUTS(16), 
    NETHER_STAR, 
    NETHER_WART(new String[] { "NETHER_WARTS", "NETHER_STALK" }), 
    NETHER_WART_BLOCK, 
    NOTE_BLOCK, 
    OAK_BOAT(new String[] { "BOAT" }), 
    OAK_BUTTON(new String[] { "WOOD_BUTTON" }), 
    OAK_DOOR(new String[] { "WOODEN_DOOR", "WOOD_DOOR" }), 
    OAK_FENCE(new String[] { "FENCE" }), 
    OAK_FENCE_GATE(new String[] { "FENCE_GATE" }), 
    OAK_LEAVES(new String[] { "LEAVES" }), 
    OAK_LOG(new String[] { "LOG" }), 
    OAK_PLANKS(new String[] { "WOOD" }), 
    OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), 
    OAK_SAPLING(new String[] { "SAPLING" }), 
    OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }), 
    OAK_SLAB(new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), 
    OAK_STAIRS(new String[] { "WOOD_STAIRS" }), 
    OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }), 
    OAK_WALL_SIGN(new String[] { "WALL_SIGN" }), 
    OAK_WOOD(new String[] { "LOG" }), 
    OBSERVER, 
    OBSIDIAN, 
    OCELOT_SPAWN_EGG(98, new String[] { "MONSTER_EGG" }), 
    ORANGE_BANNER(14, new String[] { "STANDING_BANNER", "BANNER" }), 
    ORANGE_BED(1, new String[] { "BED_BLOCK", "BED" }), 
    ORANGE_CARPET(1, new String[] { "CARPET" }), 
    ORANGE_CONCRETE(1, new String[] { "CONCRETE" }), 
    ORANGE_CONCRETE_POWDER(1, new String[] { "CONCRETE_POWDER" }), 
    ORANGE_DYE(14, new String[] { "INK_SACK" }), 
    ORANGE_GLAZED_TERRACOTTA(1, 12, new String[0]), 
    ORANGE_SHULKER_BOX, 
    ORANGE_STAINED_GLASS(1, new String[] { "STAINED_GLASS" }), 
    ORANGE_STAINED_GLASS_PANE(1, new String[] { "STAINED_GLASS_PANE" }), 
    ORANGE_TERRACOTTA(1, new String[] { "STAINED_CLAY" }), 
    ORANGE_TULIP(5, new String[] { "RED_ROSE" }), 
    ORANGE_WALL_BANNER(14, new String[] { "WALL_BANNER" }), 
    ORANGE_WOOL(1, new String[] { "WOOL" }), 
    OXEYE_DAISY(8, new String[] { "RED_ROSE" }), 
    PACKED_ICE, 
    PAINTING, 
    PANDA_SPAWN_EGG(14), 
    PAPER, 
    PARROT_SPAWN_EGG(105, new String[] { "MONSTER_EGG" }), 
    PEONY(5, new String[] { "DOUBLE_PLANT" }), 
    PETRIFIED_OAK_SLAB(new String[] { "WOOD_STEP" }), 
    PHANTOM_MEMBRANE(13), 
    PHANTOM_SPAWN_EGG(0, 13, new String[0]), 
    PIGLIN_BANNER_PATTERN(16), 
    PIGLIN_BRUTE_SPAWN_EGG(16), 
    PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG" }), 
    PIG_SPAWN_EGG(90, new String[] { "MONSTER_EGG" }), 
    PILLAGER_SPAWN_EGG(14), 
    PINK_BANNER(9, new String[] { "STANDING_BANNER", "BANNER" }), 
    PINK_BED(6, new String[] { "BED_BLOCK", "BED" }), 
    PINK_CARPET(6, new String[] { "CARPET" }), 
    PINK_CONCRETE(6, new String[] { "CONCRETE" }), 
    PINK_CONCRETE_POWDER(6, new String[] { "CONCRETE_POWDER" }), 
    PINK_DYE(9, new String[] { "INK_SACK" }), 
    PINK_GLAZED_TERRACOTTA(6, 12, new String[0]), 
    PINK_SHULKER_BOX, 
    PINK_STAINED_GLASS(6, new String[] { "STAINED_GLASS" }), 
    PINK_STAINED_GLASS_PANE(6, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    PINK_TERRACOTTA(6, new String[] { "STAINED_CLAY" }), 
    PINK_TULIP(7, new String[] { "RED_ROSE" }), 
    PINK_WALL_BANNER(9, new String[] { "WALL_BANNER" }), 
    PINK_WOOL(6, new String[] { "WOOL" }), 
    PISTON(new String[] { "PISTON_BASE" }), 
    PISTON_HEAD(new String[] { "PISTON_EXTENSION" }), 
    PLAYER_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }), 
    PLAYER_WALL_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }), 
    PODZOL(2, new String[] { "DIRT" }), 
    POISONOUS_POTATO, 
    POLAR_BEAR_SPAWN_EGG(102, new String[] { "MONSTER_EGG" }), 
    POLISHED_ANDESITE(6, new String[] { "STONE" }), 
    POLISHED_ANDESITE_SLAB, 
    POLISHED_ANDESITE_STAIRS, 
    POLISHED_BASALT(16), 
    POLISHED_BLACKSTONE(16), 
    POLISHED_BLACKSTONE_BRICKS(16), 
    POLISHED_BLACKSTONE_BRICK_SLAB(16), 
    POLISHED_BLACKSTONE_BRICK_STAIRS(16), 
    POLISHED_BLACKSTONE_BRICK_WALL(16), 
    POLISHED_BLACKSTONE_BUTTON(16), 
    POLISHED_BLACKSTONE_PRESSURE_PLATE(16), 
    POLISHED_BLACKSTONE_SLAB(16), 
    POLISHED_BLACKSTONE_STAIRS(16), 
    POLISHED_BLACKSTONE_WALL(16), 
    POLISHED_DIORITE(4, new String[] { "STONE" }), 
    POLISHED_DIORITE_SLAB, 
    POLISHED_DIORITE_STAIRS, 
    POLISHED_GRANITE(2, new String[] { "STONE" }), 
    POLISHED_GRANITE_SLAB, 
    POLISHED_GRANITE_STAIRS, 
    POPPED_CHORUS_FRUIT(new String[] { "CHORUS_FRUIT_POPPED" }), 
    POPPY(new String[] { "RED_ROSE" }), 
    PORKCHOP(new String[] { "PORK" }), 
    POTATO(new String[] { "POTATO_ITEM" }), 
    POTATOES(new String[] { "POTATO" }), 
    POTION, 
    POTTED_ACACIA_SAPLING(4, new String[] { "SAPLING", "FLOWER_POT" }), 
    POTTED_ALLIUM(2, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_AZURE_BLUET(3, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_BAMBOO, 
    POTTED_BIRCH_SAPLING(2, new String[] { "SAPLING", "FLOWER_POT" }), 
    POTTED_BLUE_ORCHID(1, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_BROWN_MUSHROOM(new String[] { "FLOWER_POT" }), 
    POTTED_CACTUS(new String[] { "FLOWER_POT" }), 
    POTTED_CORNFLOWER, 
    POTTED_CRIMSON_FUNGUS(16), 
    POTTED_CRIMSON_ROOTS(16), 
    POTTED_DANDELION(new String[] { "YELLOW_FLOWER", "FLOWER_POT" }), 
    POTTED_DARK_OAK_SAPLING(5, new String[] { "SAPLING", "FLOWER_POT" }), 
    POTTED_DEAD_BUSH(new String[] { "FLOWER_POT" }), 
    POTTED_FERN(2, new String[] { "LONG_GRASS", "FLOWER_POT" }), 
    POTTED_JUNGLE_SAPLING(3, new String[] { "SAPLING", "FLOWER_POT" }), 
    POTTED_LILY_OF_THE_VALLEY, 
    POTTED_OAK_SAPLING(new String[] { "SAPLING", "FLOWER_POT" }), 
    POTTED_ORANGE_TULIP(5, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_OXEYE_DAISY(8, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_PINK_TULIP(7, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_POPPY(new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_RED_MUSHROOM(new String[] { "FLOWER_POT" }), 
    POTTED_RED_TULIP(4, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_SPRUCE_SAPLING(1, new String[] { "SAPLING", "FLOWER_POT" }), 
    POTTED_WARPED_FUNGUS(16), 
    POTTED_WARPED_ROOTS(16), 
    POTTED_WHITE_TULIP(6, new String[] { "RED_ROSE", "FLOWER_POT" }), 
    POTTED_WITHER_ROSE, 
    POWERED_RAIL, 
    PRISMARINE, 
    PRISMARINE_BRICKS(2, new String[] { "PRISMARINE" }), 
    PRISMARINE_BRICK_SLAB(4, new String[] { "STEP" }), 
    PRISMARINE_BRICK_STAIRS(13), 
    PRISMARINE_CRYSTALS, 
    PRISMARINE_SHARD, 
    PRISMARINE_SLAB(13), 
    PRISMARINE_STAIRS(13), 
    PRISMARINE_WALL, 
    PUFFERFISH(3, new String[] { "RAW_FISH" }), 
    PUFFERFISH_BUCKET(0, 13, new String[0]), 
    PUFFERFISH_SPAWN_EGG(0, 13, new String[0]), 
    PUMPKIN, 
    PUMPKIN_PIE, 
    PUMPKIN_SEEDS, 
    PUMPKIN_STEM, 
    PURPLE_BANNER(5, new String[] { "STANDING_BANNER", "BANNER" }), 
    PURPLE_BED(10, new String[] { "BED_BLOCK", "BED" }), 
    PURPLE_CARPET(10, new String[] { "CARPET" }), 
    PURPLE_CONCRETE(10, new String[] { "CONCRETE" }), 
    PURPLE_CONCRETE_POWDER(10, new String[] { "CONCRETE_POWDER" }), 
    PURPLE_DYE(5, new String[] { "INK_SACK" }), 
    PURPLE_GLAZED_TERRACOTTA(10, 12, new String[0]), 
    PURPLE_SHULKER_BOX, 
    PURPLE_STAINED_GLASS(10, new String[] { "STAINED_GLASS" }), 
    PURPLE_STAINED_GLASS_PANE(10, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    PURPLE_TERRACOTTA(10, new String[] { "STAINED_CLAY" }), 
    PURPLE_WALL_BANNER(5, new String[] { "WALL_BANNER" }), 
    PURPLE_WOOL(10, new String[] { "WOOL" }), 
    PURPUR_BLOCK, 
    PURPUR_PILLAR, 
    PURPUR_SLAB(new String[] { "PURPUR_DOUBLE_SLAB" }), 
    PURPUR_STAIRS, 
    QUARTZ, 
    QUARTZ_BLOCK, 
    QUARTZ_BRICKS(16), 
    QUARTZ_PILLAR(2, new String[] { "QUARTZ_BLOCK" }), 
    QUARTZ_SLAB(7, new String[] { "STEP" }), 
    QUARTZ_STAIRS, 
    RABBIT, 
    RABBIT_FOOT, 
    RABBIT_HIDE, 
    RABBIT_SPAWN_EGG(101, new String[] { "MONSTER_EGG" }), 
    RABBIT_STEW, 
    RAIL(new String[] { "RAILS" }), 
    RAVAGER_SPAWN_EGG(14), 
    REDSTONE, 
    REDSTONE_BLOCK, 
    REDSTONE_LAMP(new String[] { "REDSTONE_LAMP_ON", "REDSTONE_LAMP_OFF" }), 
    REDSTONE_ORE(new String[] { "GLOWING_REDSTONE_ORE" }), 
    REDSTONE_TORCH(new String[] { "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON" }), 
    REDSTONE_WALL_TORCH, 
    REDSTONE_WIRE, 
    RED_BANNER(1, new String[] { "STANDING_BANNER", "BANNER" }), 
    RED_BED(0, new String[] { "BED_BLOCK", "BED" }), 
    RED_CARPET(14, new String[] { "CARPET" }), 
    RED_CONCRETE(14, new String[] { "CONCRETE" }), 
    RED_CONCRETE_POWDER(14, new String[] { "CONCRETE_POWDER" }), 
    RED_DYE(1, new String[] { "INK_SACK", "ROSE_RED" }), 
    RED_GLAZED_TERRACOTTA(14, 12, new String[0]), 
    RED_MUSHROOM, 
    RED_MUSHROOM_BLOCK(new String[] { "RED_MUSHROOM", "HUGE_MUSHROOM_2" }), 
    RED_NETHER_BRICKS(new String[] { "RED_NETHER_BRICK" }), 
    RED_NETHER_BRICK_SLAB(4, new String[] { "STEP" }), 
    RED_NETHER_BRICK_STAIRS, 
    RED_NETHER_BRICK_WALL, 
    RED_SAND(1, new String[] { "SAND" }), 
    RED_SANDSTONE, 
    RED_SANDSTONE_SLAB(new String[] { "DOUBLE_STONE_SLAB2", "STONE_SLAB2" }), 
    RED_SANDSTONE_STAIRS, 
    RED_SANDSTONE_WALL, 
    RED_SHULKER_BOX, 
    RED_STAINED_GLASS(14, new String[] { "STAINED_GLASS" }), 
    RED_STAINED_GLASS_PANE(14, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    RED_TERRACOTTA(14, new String[] { "STAINED_CLAY" }), 
    RED_TULIP(4, new String[] { "RED_ROSE" }), 
    RED_WALL_BANNER(1, new String[] { "WALL_BANNER" }), 
    RED_WOOL(14, new String[] { "WOOL" }), 
    REPEATER(new String[] { "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF", "DIODE" }), 
    REPEATING_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_REPEATING" }), 
    RESPAWN_ANCHOR(16), 
    ROSE_BUSH(4, new String[] { "DOUBLE_PLANT" }), 
    ROTTEN_FLESH, 
    SADDLE, 
    SALMON(1, new String[] { "RAW_FISH" }), 
    SALMON_BUCKET(0, 13, new String[0]), 
    SALMON_SPAWN_EGG(0, 13, new String[0]), 
    SAND, 
    SANDSTONE, 
    SANDSTONE_SLAB(1, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }), 
    SANDSTONE_STAIRS, 
    SANDSTONE_WALL, 
    SCAFFOLDING(0, 14, new String[0]), 
    SCUTE(13), 
    SEAGRASS(0, 13, new String[0]), 
    SEA_LANTERN, 
    SEA_PICKLE(13), 
    SHEARS, 
    SHEEP_SPAWN_EGG(91, new String[] { "MONSTER_EGG" }), 
    SHIELD, 
    SHROOMLIGHT(16), 
    SHULKER_BOX(new String[] { "PURPLE_SHULKER_BOX" }), 
    SHULKER_SHELL, 
    SHULKER_SPAWN_EGG(69, new String[] { "MONSTER_EGG" }), 
    SILVERFISH_SPAWN_EGG(60, new String[] { "MONSTER_EGG" }), 
    SKELETON_HORSE_SPAWN_EGG(28, new String[] { "MONSTER_EGG" }), 
    SKELETON_SKULL(new String[] { "SKULL", "SKULL_ITEM" }), 
    SKELETON_SPAWN_EGG(51, new String[] { "MONSTER_EGG" }), 
    SKELETON_WALL_SKULL(new String[] { "SKULL", "SKULL_ITEM" }), 
    SKULL_BANNER_PATTERN, 
    SLIME_BALL, 
    SLIME_BLOCK, 
    SLIME_SPAWN_EGG(55, new String[] { "MONSTER_EGG" }), 
    SMITHING_TABLE, 
    SMOKER(0, 14, new String[0]), 
    SMOOTH_QUARTZ(0, 13, new String[0]), 
    SMOOTH_QUARTZ_SLAB(7, new String[] { "STEP" }), 
    SMOOTH_QUARTZ_STAIRS, 
    SMOOTH_RED_SANDSTONE(2, new String[] { "RED_SANDSTONE" }), 
    SMOOTH_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }), 
    SMOOTH_RED_SANDSTONE_STAIRS, 
    SMOOTH_SANDSTONE(2, new String[] { "SANDSTONE" }), 
    SMOOTH_SANDSTONE_SLAB(new String[] { "STEP" }), 
    SMOOTH_SANDSTONE_STAIRS, 
    SMOOTH_STONE(new String[] { "STEP" }), 
    SMOOTH_STONE_SLAB(new String[] { "STEP" }), 
    SNOW, 
    SNOWBALL(new String[] { "SNOW_BALL" }), 
    SNOW_BLOCK, 
    SOUL_CAMPFIRE(16), 
    SOUL_FIRE(16), 
    SOUL_LANTERN(16), 
    SOUL_SAND, 
    SOUL_SOIL(16), 
    SOUL_TORCH(16), 
    SOUL_WALL_TORCH(16), 
    SPAWNER(new String[] { "MOB_SPAWNER" }), 
    SPECTRAL_ARROW(0, 9, new String[0]), 
    SPIDER_EYE, 
    SPIDER_SPAWN_EGG(52, new String[] { "MONSTER_EGG" }), 
    SPLASH_POTION, 
    SPONGE, 
    SPRUCE_BOAT(new String[] { "BOAT_SPRUCE" }), 
    SPRUCE_BUTTON(new String[] { "WOOD_BUTTON" }), 
    SPRUCE_DOOR(new String[] { "SPRUCE_DOOR", "SPRUCE_DOOR_ITEM" }), 
    SPRUCE_FENCE, 
    SPRUCE_FENCE_GATE, 
    SPRUCE_LEAVES(1, new String[] { "LEAVES", "LEAVES_2" }), 
    SPRUCE_LOG(1, new String[] { "LOG" }), 
    SPRUCE_PLANKS(1, new String[] { "WOOD" }), 
    SPRUCE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), 
    SPRUCE_SAPLING(1, new String[] { "SAPLING" }), 
    SPRUCE_SIGN(new String[] { "SIGN_POST", "SIGN" }), 
    SPRUCE_SLAB(1, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), 
    SPRUCE_STAIRS(new String[] { "SPRUCE_WOOD_STAIRS" }), 
    SPRUCE_TRAPDOOR(new String[] { "TRAP_DOOR" }), 
    SPRUCE_WALL_SIGN(new String[] { "WALL_SIGN" }), 
    SPRUCE_WOOD(1, new String[] { "LOG" }), 
    SQUID_SPAWN_EGG(94, new String[] { "MONSTER_EGG" }), 
    STICK, 
    STICKY_PISTON(new String[] { "PISTON_BASE", "PISTON_STICKY_BASE" }), 
    STONE, 
    STONECUTTER(14), 
    STONE_AXE, 
    STONE_BRICKS(new String[] { "SMOOTH_BRICK" }), 
    STONE_BRICK_SLAB(4, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }), 
    STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }), 
    STONE_BRICK_WALL, 
    STONE_BUTTON, 
    STONE_HOE, 
    STONE_PICKAXE, 
    STONE_PRESSURE_PLATE(new String[] { "STONE_PLATE" }), 
    STONE_SHOVEL(new String[] { "STONE_SPADE" }), 
    STONE_SLAB(new String[] { "DOUBLE_STEP", "STEP" }), 
    STONE_STAIRS, 
    STONE_SWORD, 
    STRAY_SPAWN_EGG(6, new String[] { "MONSTER_EGG" }), 
    STRIDER_SPAWN_EGG(16), 
    STRING, 
    STRIPPED_ACACIA_LOG(new String[] { "LOG_2" }), 
    STRIPPED_ACACIA_WOOD(new String[] { "LOG_2" }), 
    STRIPPED_BIRCH_LOG(2, new String[] { "LOG" }), 
    STRIPPED_BIRCH_WOOD(2, new String[] { "LOG" }), 
    STRIPPED_CRIMSON_HYPHAE(16), 
    STRIPPED_CRIMSON_STEM(16), 
    STRIPPED_DARK_OAK_LOG(new String[] { "LOG" }), 
    STRIPPED_DARK_OAK_WOOD(new String[] { "LOG" }), 
    STRIPPED_JUNGLE_LOG(3, new String[] { "LOG" }), 
    STRIPPED_JUNGLE_WOOD(3, new String[] { "LOG" }), 
    STRIPPED_OAK_LOG(new String[] { "LOG" }), 
    STRIPPED_OAK_WOOD(new String[] { "LOG" }), 
    STRIPPED_SPRUCE_LOG(1, new String[] { "LOG" }), 
    STRIPPED_SPRUCE_WOOD(1, new String[] { "LOG" }), 
    STRIPPED_WARPED_HYPHAE(16), 
    STRIPPED_WARPED_STEM(16), 
    STRUCTURE_BLOCK, 
    STRUCTURE_VOID(10, new String[] { "BARRIER" }), 
    SUGAR, 
    SUGAR_CANE(new String[] { "SUGAR_CANE_BLOCK" }), 
    SUNFLOWER(new String[] { "DOUBLE_PLANT" }), 
    SUSPICIOUS_STEW(0, 14, new String[0]), 
    SWEET_BERRIES(14), 
    SWEET_BERRY_BUSH(0, 14, new String[0]), 
    TALL_GRASS(2, new String[] { "DOUBLE_PLANT" }), 
    TALL_SEAGRASS(2, 13, new String[0]), 
    TARGET(16), 
    TERRACOTTA(new String[] { "STAINED_CLAY" }), 
    TIPPED_ARROW(0, 9, new String[0]), 
    TNT, 
    TNT_MINECART(new String[] { "EXPLOSIVE_MINECART" }), 
    TORCH, 
    TOTEM_OF_UNDYING(new String[] { "TOTEM" }), 
    TRADER_LLAMA_SPAWN_EGG(103, 14, new String[0]), 
    TRAPPED_CHEST, 
    TRIDENT(13), 
    TRIPWIRE, 
    TRIPWIRE_HOOK, 
    TROPICAL_FISH(2, new String[] { "RAW_FISH" }), 
    TROPICAL_FISH_BUCKET(0, 13, new String[] { "BUCKET", "WATER_BUCKET" }), 
    TROPICAL_FISH_SPAWN_EGG(0, 13, new String[] { "MONSTER_EGG" }), 
    TUBE_CORAL(13), 
    TUBE_CORAL_BLOCK(13), 
    TUBE_CORAL_FAN(13), 
    TUBE_CORAL_WALL_FAN, 
    TURTLE_EGG(0, 13, new String[0]), 
    TURTLE_HELMET(0, 13, new String[0]), 
    TURTLE_SPAWN_EGG(0, 13, new String[0]), 
    TWISTING_VINES(16), 
    TWISTING_VINES_PLANT(16), 
    VEX_SPAWN_EGG(35, new String[] { "MONSTER_EGG" }), 
    VILLAGER_SPAWN_EGG(120, new String[] { "MONSTER_EGG" }), 
    VINDICATOR_SPAWN_EGG(36, new String[] { "MONSTER_EGG" }), 
    VINE, 
    VOID_AIR(new String[] { "AIR" }), 
    WALL_TORCH(new String[] { "TORCH" }), 
    WANDERING_TRADER_SPAWN_EGG(0, 14, new String[0]), 
    WARPED_BUTTON(16), 
    WARPED_DOOR(16), 
    WARPED_FENCE(16), 
    WARPED_FENCE_GATE(16), 
    WARPED_FUNGUS(16), 
    WARPED_FUNGUS_ON_A_STICK(16), 
    WARPED_HYPHAE(16), 
    WARPED_NYLIUM(16), 
    WARPED_PLANKS(16), 
    WARPED_PRESSURE_PLATE(16), 
    WARPED_ROOTS(16), 
    WARPED_SIGN(0, 16, new String[] { "SIGN_POST" }), 
    WARPED_SLAB(16), 
    WARPED_STAIRS(16), 
    WARPED_STEM(16), 
    WARPED_TRAPDOOR(16), 
    WARPED_WALL_SIGN(0, 16, new String[] { "WALL_SIGN" }), 
    WARPED_WART_BLOCK(16), 
    WATER(new String[] { "STATIONARY_WATER" }), 
    WATER_BUCKET, 
    WEEPING_VINES(16), 
    WEEPING_VINES_PLANT(16), 
    WET_SPONGE(1, new String[] { "SPONGE" }), 
    WHEAT(new String[] { "CROPS" }), 
    WHEAT_SEEDS(new String[] { "SEEDS" }), 
    WHITE_BANNER(15, new String[] { "STANDING_BANNER", "BANNER" }), 
    WHITE_BED(new String[] { "BED_BLOCK", "BED" }), 
    WHITE_CARPET(new String[] { "CARPET" }), 
    WHITE_CONCRETE(new String[] { "CONCRETE" }), 
    WHITE_CONCRETE_POWDER(new String[] { "CONCRETE_POWDER" }), 
    WHITE_DYE(15, 14, new String[] { "INK_SACK", "BONE_MEAL" }), 
    WHITE_GLAZED_TERRACOTTA(0, 12, new String[] { "STAINED_CLAY" }), 
    WHITE_SHULKER_BOX, 
    WHITE_STAINED_GLASS(new String[] { "STAINED_GLASS" }), 
    WHITE_STAINED_GLASS_PANE(new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    WHITE_TERRACOTTA(new String[] { "STAINED_CLAY", "TERRACOTTA" }), 
    WHITE_TULIP(6, new String[] { "RED_ROSE" }), 
    WHITE_WALL_BANNER(15, new String[] { "WALL_BANNER" }), 
    WHITE_WOOL(new String[] { "WOOL" }), 
    WITCH_SPAWN_EGG(66, new String[] { "MONSTER_EGG" }), 
    WITHER_ROSE(0, 14, new String[0]), 
    WITHER_SKELETON_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }), 
    WITHER_SKELETON_SPAWN_EGG(5, new String[] { "MONSTER_EGG" }), 
    WITHER_SKELETON_WALL_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }), 
    WOLF_SPAWN_EGG(95, new String[] { "MONSTER_EGG" }), 
    WOODEN_AXE(new String[] { "WOOD_AXE" }), 
    WOODEN_HOE(new String[] { "WOOD_HOE" }), 
    WOODEN_PICKAXE(new String[] { "WOOD_PICKAXE" }), 
    WOODEN_SHOVEL(new String[] { "WOOD_SPADE" }), 
    WOODEN_SWORD(new String[] { "WOOD_SWORD" }), 
    WRITABLE_BOOK(new String[] { "BOOK_AND_QUILL" }), 
    WRITTEN_BOOK, 
    YELLOW_BANNER(11, new String[] { "STANDING_BANNER", "BANNER" }), 
    YELLOW_BED(4, new String[] { "BED_BLOCK", "BED" }), 
    YELLOW_CARPET(4, new String[] { "CARPET" }), 
    YELLOW_CONCRETE(4, new String[] { "CONCRETE" }), 
    YELLOW_CONCRETE_POWDER(4, new String[] { "CONCRETE_POWDER" }), 
    YELLOW_DYE(11, new String[] { "INK_SACK", "DANDELION_YELLOW" }), 
    YELLOW_GLAZED_TERRACOTTA(4, 12, new String[] { "STAINED_CLAY", "YELLOW_TERRACOTTA" }), 
    YELLOW_SHULKER_BOX, 
    YELLOW_STAINED_GLASS(4, new String[] { "STAINED_GLASS" }), 
    YELLOW_STAINED_GLASS_PANE(4, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), 
    YELLOW_TERRACOTTA(4, new String[] { "STAINED_CLAY" }), 
    YELLOW_WALL_BANNER(11, new String[] { "WALL_BANNER" }), 
    YELLOW_WOOL(4, new String[] { "WOOL" }), 
    ZOGLIN_SPAWN_EGG(16), 
    ZOMBIE_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }), 
    ZOMBIE_HORSE_SPAWN_EGG(29, new String[] { "MONSTER_EGG" }), 
    ZOMBIE_SPAWN_EGG(54, new String[] { "MONSTER_EGG" }), 
    ZOMBIE_VILLAGER_SPAWN_EGG(27, new String[] { "MONSTER_EGG" }), 
    ZOMBIE_WALL_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }), 
    ZOMBIFIED_PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG", "ZOMBIE_PIGMAN_SPAWN_EGG" });
    
    public static final XMaterial[] VALUES;
    private static final Map<String, XMaterial> NAMES;
    private static final Cache<String, XMaterial> NAME_CACHE;
    private static final LoadingCache<String, Pattern> CACHED_REGEX;
    private static final byte MAX_DATA_VALUE = 120;
    private static final byte UNKNOWN_DATA_VALUE = -1;
    private static final short MAX_ID = 2267;
    private static final Set<String> DUPLICATED;
    private final byte data;
    private final byte version;
    @Nonnull
    private final String[] legacy;
    @Nullable
    private final Material material;
    
    private XMaterial(@Nonnull final int data, final int version, final String[] legacy) {
        this.data = (byte)data;
        this.version = (byte)version;
        this.legacy = legacy;
        Material mat = null;
        if ((!Data.ISFLAT && this.isDuplicated()) || (mat = Material.getMaterial(this.name())) == null) {
            for (int i = legacy.length - 1; i >= 0; --i) {
                mat = Material.getMaterial(legacy[i]);
                if (mat != null) {
                    break;
                }
            }
        }
        this.material = mat;
    }
    
    private XMaterial(final int data, final String[] legacy) {
        this(data, 0, legacy);
    }
    
    private XMaterial(final int version) {
        this(0, version, new String[0]);
    }
    
    private XMaterial() {
        this(0, 0, new String[0]);
    }
    
    private XMaterial(final String[] legacy) {
        this(0, 0, legacy);
    }
    
    public static boolean isNewVersion() {
        return Data.ISFLAT;
    }
    
    public static boolean isOneEight() {
        return !supports(9);
    }
    
    @Nonnull
    private static Optional<XMaterial> getIfPresent(@Nonnull final String name) {
        return Optional.ofNullable(XMaterial.NAMES.get(name));
    }
    
    public static int getVersion() {
        return Data.VERSION;
    }
    
    @Nullable
    private static XMaterial requestOldXMaterial(@Nonnull final String name, final byte data) {
        final String holder = name + data;
        final XMaterial cache = XMaterial.NAME_CACHE.getIfPresent(holder);
        if (cache != null) {
            return cache;
        }
        for (final XMaterial material : XMaterial.VALUES) {
            if ((data == -1 || data == material.data) && material.anyMatchLegacy(name)) {
                XMaterial.NAME_CACHE.put(holder, material);
                return material;
            }
        }
        return null;
    }
    
    @Nonnull
    public static Optional<XMaterial> matchXMaterial(@Nonnull final String name) {
        Validate.notEmpty(name, "Cannot match a material with null or empty material name");
        final Optional<XMaterial> oldMatch = matchXMaterialWithData(name);
        return oldMatch.isPresent() ? oldMatch : matchDefinedXMaterial(format(name), (byte)(-1));
    }
    
    @Nonnull
    private static Optional<XMaterial> matchXMaterialWithData(@Nonnull final String name) {
        final int index = name.indexOf(58);
        if (index != -1) {
            final String mat = format(name.substring(0, index));
            try {
                final byte data = (byte)Integer.parseInt(StringUtils.deleteWhitespace(name.substring(index + 1)));
                return (data >= 0 && data < 120) ? matchDefinedXMaterial(mat, data) : matchDefinedXMaterial(mat, (byte)(-1));
            }
            catch (final NumberFormatException ignored) {
                return matchDefinedXMaterial(mat, (byte)(-1));
            }
        }
        return Optional.empty();
    }
    
    @Nonnull
    public static XMaterial matchXMaterial(@Nonnull final Material material) {
        Objects.requireNonNull(material, "Cannot match null material");
        return matchDefinedXMaterial(material.name(), (byte)(-1)).get();
    }
    
    @Nonnull
    public static XMaterial matchXMaterial(@Nonnull final ItemStack item) {
        Objects.requireNonNull(item, "Cannot match null ItemStack");
        final String material = item.getType().name();
        final byte data = (byte)((Data.ISFLAT || item.getType().getMaxDurability() > 0) ? 0 : item.getDurability());
        return matchDefinedXMaterial(material, data).get();
    }
    
    @Nonnull
    private static Optional<XMaterial> matchDefinedXMaterial(@Nonnull final String name, final byte data) {
        Boolean duplicated = null;
        if (data <= 0 && (Data.ISFLAT || !(duplicated = isDuplicated(name)))) {
            final Optional<XMaterial> xMaterial = getIfPresent(name);
            if (xMaterial.isPresent()) {
                return xMaterial;
            }
        }
        final XMaterial oldXMaterial = requestOldXMaterial(name, data);
        if (oldXMaterial == null) {
            return (data > 0 && name.endsWith("MAP")) ? Optional.of(XMaterial.FILLED_MAP) : Optional.empty();
        }
        if (!Data.ISFLAT && oldXMaterial.isPlural()) {
            if (duplicated == null) {
                if (!isDuplicated(name)) {
                    return Optional.of(oldXMaterial);
                }
            }
            else if (!duplicated) {
                return Optional.of(oldXMaterial);
            }
            return getIfPresent(name);
        }
        return Optional.of(oldXMaterial);
    }
    
    private static boolean isDuplicated(@Nonnull final String name) {
        return XMaterial.DUPLICATED.contains(name);
    }
    
    @Nonnull
    @Deprecated
    public static Optional<XMaterial> matchXMaterial(final int id, final byte data) {
        if (id < 0 || id > 2267 || data < 0) {
            return Optional.empty();
        }
        for (final XMaterial materials : XMaterial.VALUES) {
            if (materials.data == data && materials.getId() == id) {
                return Optional.of(materials);
            }
        }
        return Optional.empty();
    }
    
    @Nonnull
    protected static String format(@Nonnull final String name) {
        final int len = name.length();
        final char[] chs = new char[len];
        int count = 0;
        boolean appendUnderline = false;
        for (int i = 0; i < len; ++i) {
            final char ch = name.charAt(i);
            if (!appendUnderline && count != 0 && (ch == '-' || ch == ' ' || ch == '_') && chs[count] != '_') {
                appendUnderline = true;
            }
            else {
                boolean number = false;
                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (number = (ch >= '0' && ch <= '9'))) {
                    if (appendUnderline) {
                        chs[count++] = '_';
                        appendUnderline = false;
                    }
                    if (number) {
                        chs[count++] = ch;
                    }
                    else {
                        chs[count++] = (char)(ch & '_');
                    }
                }
            }
        }
        return new String(chs, 0, count);
    }
    
    public static boolean supports(final int version) {
        return Data.VERSION >= version;
    }
    
    @Nonnull
    public static String getMajorVersion(@Nonnull String version) {
        Validate.notEmpty(version, "Cannot get major Minecraft version from null or empty string");
        int index = version.lastIndexOf("MC:");
        if (index != -1) {
            version = version.substring(index + 4, version.length() - 1);
        }
        else if (version.endsWith("SNAPSHOT")) {
            index = version.indexOf(45);
            version = version.substring(0, index);
        }
        final int lastDot = version.lastIndexOf(46);
        if (version.indexOf(46) != lastDot) {
            version = version.substring(0, lastDot);
        }
        return version;
    }
    
    public String[] getLegacy() {
        return this.legacy;
    }
    
    private boolean isPlural() {
        return this.name().charAt(this.name().length() - 1) == 'S';
    }
    
    public boolean isOneOf(@Nullable final Collection<String> materials) {
        if (materials == null || materials.isEmpty()) {
            return false;
        }
        final String name = this.name();
        for (String comp : materials) {
            final String checker = comp.toUpperCase(Locale.ENGLISH);
            if (checker.startsWith("CONTAINS:")) {
                comp = format(checker.substring(9));
                if (name.contains(comp)) {
                    return true;
                }
                continue;
            }
            else if (checker.startsWith("REGEX:")) {
                comp = comp.substring(6);
                final Pattern pattern = XMaterial.CACHED_REGEX.getUnchecked(comp);
                if (pattern != null && pattern.matcher(name).matches()) {
                    return true;
                }
                continue;
            }
            else {
                final Optional<XMaterial> xMat = matchXMaterial(comp);
                if (xMat.isPresent() && xMat.get() == this) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    @Nonnull
    public ItemStack setType(@Nonnull final ItemStack item) {
        Objects.requireNonNull(item, "Cannot set material for null ItemStack");
        final Material material = this.parseMaterial();
        Objects.requireNonNull(material, () -> "Unsupported material: " + this.name());
        item.setType(material);
        if (!Data.ISFLAT && material.getMaxDurability() <= 0) {
            item.setDurability(this.data);
        }
        return item;
    }
    
    private boolean anyMatchLegacy(@Nonnull final String name) {
        for (int i = this.legacy.length - 1; i >= 0; --i) {
            final String legacy = this.legacy[i];
            if (name.equals(legacy)) {
                return true;
            }
        }
        return false;
    }
    
    @Nonnull
    @Override
    public String toString() {
        return WordUtils.capitalize(this.name().replace('_', ' ').toLowerCase(Locale.ENGLISH));
    }
    
    public int getId() {
        if (this.data != 0 || this.version >= 13) {
            return -1;
        }
        final Material material = this.parseMaterial();
        if (material == null) {
            return -1;
        }
        if (Data.ISFLAT && !material.isLegacy()) {
            return -1;
        }
        return material.getId();
    }
    
    public byte getData() {
        return this.data;
    }
    
    @NotNull
    public ItemStack parseItem() {
        final Material material = this.parseMaterial();
        if (material == null) {
            return null;
        }
        return Data.ISFLAT ? new ItemStack(material) : new ItemStack(material, 1, this.data);
    }
    
    public Material parseMaterial() {
        return this.material;
    }
    
    public boolean isSimilar(@Nonnull final ItemStack item) {
        Objects.requireNonNull(item, "Cannot compare with null ItemStack");
        return item.getType() == this.parseMaterial() && (Data.ISFLAT || item.getDurability() == this.data || item.getType().getMaxDurability() <= 0);
    }
    
    public boolean isSupported() {
        return this.material != null;
    }
    
    public byte getMaterialVersion() {
        return this.version;
    }
    
    private boolean isDuplicated() {
        final String name = this.name();
        return name.equals("MELON") || name.equals("CARROT") || name.equals("POTATO") || name.equals("BEETROOT") || name.equals("GRASS") || name.equals("BROWN_MUSHROOM") || name.equals("BRICK") || name.equals("NETHER_BRICK") || name.equals("DARK_OAK_DOOR") || name.equals("ACACIA_DOOR") || name.equals("BIRCH_DOOR") || name.equals("JUNGLE_DOOR") || name.equals("SPRUCE_DOOR") || name.equals("CAULDRON") || name.equals("BREWING_STAND") || name.equals("FLOWER_POT");
    }
    
    static {
        VALUES = values();
        NAMES = new HashMap<String, XMaterial>();
        NAME_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.HOURS).build();
        CACHED_REGEX = CacheBuilder.newBuilder().expireAfterAccess(3L, TimeUnit.HOURS).build((CacheLoader<? super String, Pattern>)new CacheLoader<String, Pattern>() {
            @Override
            public Pattern load(@Nonnull final String str) {
                try {
                    return Pattern.compile(str);
                }
                catch (final PatternSyntaxException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        });
        for (final XMaterial material : XMaterial.VALUES) {
            XMaterial.NAMES.put(material.name(), material);
        }
        if (Data.ISFLAT) {
            DUPLICATED = null;
        }
        else {
            final List<XMaterial> duplications = Arrays.asList(XMaterial.MELON, XMaterial.CARROT, XMaterial.POTATO, XMaterial.BEETROOT, XMaterial.GRASS, XMaterial.BROWN_MUSHROOM, XMaterial.BRICK, XMaterial.NETHER_BRICK, XMaterial.DARK_OAK_DOOR, XMaterial.ACACIA_DOOR, XMaterial.BIRCH_DOOR, XMaterial.JUNGLE_DOOR, XMaterial.SPRUCE_DOOR, XMaterial.CAULDRON, XMaterial.BREWING_STAND, XMaterial.FLOWER_POT);
            final Set<String> duplicatedNames = new HashSet<String>(50);
            for (final XMaterial dupe : duplications) {
                duplicatedNames.add(dupe.name());
                duplicatedNames.addAll(Arrays.asList(dupe.legacy));
            }
            DUPLICATED = duplicatedNames;
        }
    }
    
    private static final class Data
    {
        private static final int VERSION;
        private static final boolean ISFLAT;
        
        static {
            VERSION = Integer.parseInt(XMaterial.getMajorVersion(Bukkit.getVersion()).substring(2));
            ISFLAT = XMaterial.supports(13);
        }
    }
}
