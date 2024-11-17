package me.frep.vulcan.spigot.util.value;

import me.frep.vulcan.spigot.util.ServerUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import org.bukkit.Material;
import java.util.Map;
import java.util.List;

public final class Values
{
    public static final double MAX_JUMP_HEIGHT = 0.41999998688697815;
    public static final List<Double> WATER_VALUES;
    public static final Map<Integer, Double> JUMP_MOTION;
    public static final Map<Integer, Double> JUMP_MOTION_DOWN;
    public static final Map<Integer, Double> SENSITIVITY_MCP_VALUES;
    public static final Map<Material, Float> BLOCK_HARDNESS_VALUES;
    
    private Values() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    static {
        WATER_VALUES = new ArrayList<Double>();
        JUMP_MOTION = new HashMap<Integer, Double>();
        JUMP_MOTION_DOWN = new HashMap<Integer, Double>();
        SENSITIVITY_MCP_VALUES = new HashMap<Integer, Double>();
        BLOCK_HARDNESS_VALUES = new HashMap<Material, Float>();
        final Material[] materials = Arrays.stream(Material.values()).filter(Material::isBlock).filter(Material::isSolid).toArray(Material[]::new);
        if (ServerUtil.isHigherThan1_13()) {
            final List<String> ignoredBlocks = new ArrayList<String>();
            ignoredBlocks.add("BED");
            ignoredBlocks.add("ICE");
            ignoredBlocks.add("CAKE");
            ignoredBlocks.add("PACKED_ICE");
            ignoredBlocks.add("FIRE");
            ignoredBlocks.add("CHEST");
            ignoredBlocks.add("IRON_DOOR");
            ignoredBlocks.add("WET_SPONGE");
            ignoredBlocks.add("BLUE_ICE");
            ignoredBlocks.add("BAMBOO_SAPLING");
            ignoredBlocks.add("SPONGE");
            ignoredBlocks.add("INFESTED_DEEPSLATE");
            ignoredBlocks.add("JACK_O_LANTERN");
            ignoredBlocks.add("CARVED_PUMPKIN");
            ignoredBlocks.add("OAK_DOOR");
            ignoredBlocks.add("DEEPSLATE");
            ignoredBlocks.add("WEB");
            ignoredBlocks.add("COBWEB");
            ignoredBlocks.add("HAY_BLOCK");
            ignoredBlocks.add("WET_SPONGE");
            ignoredBlocks.add("LECTERN");
            ignoredBlocks.add("SPRUCE_DOOR");
            ignoredBlocks.add("PUMPKIN");
            ignoredBlocks.add("INFESTED_STONE");
            ignoredBlocks.add("SPRUCE_TRAPDOOR");
            ignoredBlocks.add("OAK_DOOR");
            ignoredBlocks.add("SMOOTH_BRICKw");
            ignoredBlocks.add("NETHER_WART_BLOCK");
            ignoredBlocks.add("HAY_BALE");
            ignoredBlocks.add("WARPED_WART_BLOCK");
            ignoredBlocks.add("MELON_BLOCK");
            ignoredBlocks.add("SHROOMLIGHT");
            ignoredBlocks.add("BAMBOO");
            ignoredBlocks.add("GLASS");
            ignoredBlocks.add("MELON");
            ignoredBlocks.add("TURTLE_EGG");
            ignoredBlocks.add("FROSTED_ICE");
            ignoredBlocks.add("SPAWNER");
            ignoredBlocks.add("DRIED_KELP_BLOCK");
            ignoredBlocks.add("FIRE");
            for (final Material mat : materials) {
                if (mat.getHardness() >= 0.5) {
                    if (!ignoredBlocks.contains(mat.name())) {
                        Values.BLOCK_HARDNESS_VALUES.put(mat, mat.getHardness());
                    }
                }
            }
            ignoredBlocks.clear();
        }
        else {
            for (final Material mat2 : materials) {
                final String name = mat2.name();
                switch (name) {
                    case "DIRT":
                    case "HAY_BLOCK":
                    case "MAGMA":
                    case "PISTON_BASE":
                    case "PISTON_EXTENSION":
                    case "PISTON_MOVING_PIECE":
                    case "PISTON_STICKY_BASE":
                    case "SAND":
                    case "SOUL_SAND":
                    case "CONCRETE_POWDER": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 0.5f);
                        break;
                    }
                    case "TRAP_DOOR":
                    case "ACACIA_DOOR":
                    case "BIRCH_DOOR":
                    case "DARK_OAK_DOOR":
                    case "JUNGLE_DOOR":
                    case "OAK_DOOR":
                    case "SPRUCE_DOOR":
                    case "COAL_ORE":
                    case "REDSTONE_ORE":
                    case "QUARTZ_ORE":
                    case "LAPIS_ORE":
                    case "IRON_ORE":
                    case "GOLD_ORE":
                    case "EMERALD_ORE":
                    case "DIAMOND_ORE":
                    case "BEACON":
                    case "GOLD_BLOCK":
                    case "HOPPER":
                    case "LAPIS_BLOCK":
                    case "OBSERVER": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 3.0f);
                        break;
                    }
                    case "IRON_TRAPDOOR":
                    case "IRON_DOOR":
                    case "IRON_DOOR_BLOCK":
                    case "ANVIL":
                    case "COAL_BLOCK":
                    case "DIAMOND_BLOCK":
                    case "EMERALD_BLOCK":
                    case "ENCHANTMENT_TABLE":
                    case "IRON_BARDING":
                    case "IRON_BLOCK":
                    case "REDSTONE_BLOCK": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 5.0f);
                        break;
                    }
                    case "ACACIA_FENCE":
                    case "ACACIA_FENCE_GATE":
                    case "BIRCH_FENCE":
                    case "BIRCH_FENCE_GATE":
                    case "DARK_OAK_FENCE":
                    case "DARK_OAK_FENCE_GATE":
                    case "JUNGLE_FENCE":
                    case "JUNGLE_FENCE_GATE":
                    case "FENCE":
                    case "FENCE_GATE":
                    case "SPRUCE_FENCE":
                    case "SPRUCE_FENCE_GATE":
                    case "LOG":
                    case "LOG_2":
                    case "WOOD":
                    case "ACACIA_STAIRS":
                    case "SMOOTH_STAIRS":
                    case "BIRCH_WOOD_STAIRS":
                    case "BRICK_STAIRS":
                    case "COBBLESTONE_STAIRS":
                    case "DARK_OAK_STAIRS":
                    case "JUNGLE_WOOD_STAIRS":
                    case "NETHER_BRICK_STAIRS":
                    case "WOOD_STAIRS":
                    case "SPRUCE_WOOD_STAIRS":
                    case "ACACIA_SLAB":
                    case "PURPUR_SLAB":
                    case "STONE_SLAB2":
                    case "BRICK":
                    case "CAULDRON":
                    case "COBBLESTONE":
                    case "COBBLE_WALL":
                    case "JUKEBOX":
                    case "MOSSY_COBBLESTONE":
                    case "NETHER_BRICK":
                    case "RED_NETHER_BRICK":
                    case "BLACK_SHULKER_BOX":
                    case "BLUE_SHULKER_BOX":
                    case "BROWN_SHULKER_BOX":
                    case "CYAN_SHULKER_BOX":
                    case "GRAY_SHULKER_BOX":
                    case "GREEN_SHULKER_BOX":
                    case "LIGHT_BLUE_SHULKER_BOX":
                    case "LIGHT_GRAY_SHULKER_BOX":
                    case "LIME_SHULKER_BOX":
                    case "MAGENTA_SHULKER_BOX":
                    case "ORANGE_SHULKER_BOX":
                    case "PINK_SHULKER_BOX":
                    case "PURPLE_SHULKER_BOX":
                    case "RED_SHULKER_BOX":
                    case "SHULKER_BOX":
                    case "WHITE_SHULKER_BOX":
                    case "YELLOW_SHULKER_BOX": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 2.0f);
                        break;
                    }
                    case "PURPUR_STAIRS":
                    case "BOOKSHELF":
                    case "PURPUR_BLOCK":
                    case "PURPUR_PILLAR":
                    case "STONE": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 1.5f);
                        break;
                    }
                    case "QUARTZ_STAIRS":
                    case "RED_SANDSTONE_STAIRS":
                    case "SANDSTONE_STAIRS":
                    case "RED_SANDSTONE":
                    case "SANDSTONE":
                    case "WOOL":
                    case "END_BRICKS":
                    case "NOTE_BLOCK":
                    case "QUARTZ_BLOCK": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 0.8f);
                        break;
                    }
                    case "ACTIVATOR_RAIL":
                    case "RAILS":
                    case "POWERED_RAIL":
                    case "DETECTOR_RAIL": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 0.7f);
                        break;
                    }
                    case "NETHER_WART_BLOCK":
                    case "SIGN":
                    case "WALL_SIGN": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 1.0f);
                        break;
                    }
                    case "ENDER_CHEST": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 22.5f);
                        break;
                    }
                    case "OBSIDIAN": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 50.0f);
                        break;
                    }
                    case "DISPENSER":
                    case "DROPPER":
                    case "FURNACE": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 3.5f);
                        break;
                    }
                    case "SPONGE":
                    case "CLAY":
                    case "GRASS":
                    case "GRAVEL":
                    case "MYCEL": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 0.6f);
                        break;
                    }
                    case "GRASS_PATH": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 0.65f);
                        break;
                    }
                    case "BLACK_GLAZED_TERRACOTTA":
                    case "BLUE_GLAZED_TERRACOTTA":
                    case "BROWN_GLAZED_TERRACOTTA":
                    case "CYAN_GLAZED_TERRACOTTA":
                    case "GRAY_GLAZED_TERRACOTTA":
                    case "GREEN_GLAZED_TERRACOTTA":
                    case "LIGHT_BLUE_GLAZED_TERRACOTTA":
                    case "LIGHT_GRAY_GLAZED_TERRACOTTA":
                    case "LIME_GLAZED_TERRACOTTA":
                    case "MAGENTA_GLAZED_TERRACOTTA":
                    case "ORANGE_GLAZED_TERRACOTTA":
                    case "PINK_GLAZED_TERRACOTTA":
                    case "PURPLE_GLAZED_TERRACOTTA":
                    case "RED_GLAZED_TERRACOTTA":
                    case "WHITE_GLAZED_TERRACOTTA":
                    case "YELLOW_GLAZED_TERRACOTTA": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 1.4f);
                        break;
                    }
                    case "CONCRETE": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 1.8f);
                        break;
                    }
                    case "WORKBENCH":
                    case "TRAPPED_CHEST": {
                        Values.BLOCK_HARDNESS_VALUES.put(mat2, 2.5f);
                        break;
                    }
                }
            }
        }
        Values.JUMP_MOTION.put(1, 0.41999998688697815);
        Values.JUMP_MOTION.put(2, 0.33319999363422426);
        Values.JUMP_MOTION.put(3, 0.24813599859094637);
        Values.JUMP_MOTION.put(4, 0.164773281826065);
        Values.JUMP_MOTION.put(5, 0.08307781780646906);
        Values.JUMP_MOTION.put(6, -0.07840000152587834);
        Values.JUMP_MOTION.put(7, -0.15523200451659847);
        Values.JUMP_MOTION.put(8, -0.23052736891296632);
        Values.JUMP_MOTION.put(9, -0.30431682745754074);
        Values.JUMP_MOTION.put(10, -0.37663049823865435);
        Values.JUMP_MOTION.put(11, -0.1040803780930446);
        Values.JUMP_MOTION_DOWN.put(1, -0.07840000152587834);
        Values.JUMP_MOTION_DOWN.put(2, -0.15523200451659847);
        Values.JUMP_MOTION_DOWN.put(3, -0.23052736891296632);
        Values.JUMP_MOTION_DOWN.put(4, -0.30431682745754074);
        Values.JUMP_MOTION_DOWN.put(5, -0.37663049823865435);
        Values.JUMP_MOTION_DOWN.put(6, -0.44749789698342113);
        Values.JUMP_MOTION_DOWN.put(7, -0.5169479491049742);
        Values.SENSITIVITY_MCP_VALUES.put(0, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(1, 0.0070422534);
        Values.SENSITIVITY_MCP_VALUES.put(2, 0.014084507);
        Values.SENSITIVITY_MCP_VALUES.put(3, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(4, 0.02112676);
        Values.SENSITIVITY_MCP_VALUES.put(5, 0.028169014);
        Values.SENSITIVITY_MCP_VALUES.put(6, 0.0281690166);
        Values.SENSITIVITY_MCP_VALUES.put(7, 0.03521127);
        Values.SENSITIVITY_MCP_VALUES.put(8, 0.04225352);
        Values.SENSITIVITY_MCP_VALUES.put(9, 0.049295776);
        Values.SENSITIVITY_MCP_VALUES.put(10, 0.0492957736);
        Values.SENSITIVITY_MCP_VALUES.put(11, 0.056338027);
        Values.SENSITIVITY_MCP_VALUES.put(12, 0.06338028);
        Values.SENSITIVITY_MCP_VALUES.put(13, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(14, 0.07042254);
        Values.SENSITIVITY_MCP_VALUES.put(15, 0.07746479);
        Values.SENSITIVITY_MCP_VALUES.put(16, 0.08450704);
        Values.SENSITIVITY_MCP_VALUES.put(17, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(18, 0.09154929);
        Values.SENSITIVITY_MCP_VALUES.put(19, 0.09859155);
        Values.SENSITIVITY_MCP_VALUES.put(20, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(21, 0.1056338);
        Values.SENSITIVITY_MCP_VALUES.put(22, 0.112676054);
        Values.SENSITIVITY_MCP_VALUES.put(23, 0.11971831);
        Values.SENSITIVITY_MCP_VALUES.put(24, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(25, 0.12676056);
        Values.SENSITIVITY_MCP_VALUES.put(26, 0.13380282);
        Values.SENSITIVITY_MCP_VALUES.put(27, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(28, 0.14084508);
        Values.SENSITIVITY_MCP_VALUES.put(29, 0.14788732);
        Values.SENSITIVITY_MCP_VALUES.put(30, 0.15492958);
        Values.SENSITIVITY_MCP_VALUES.put(31, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(32, 0.16197184);
        Values.SENSITIVITY_MCP_VALUES.put(33, 0.16901408);
        Values.SENSITIVITY_MCP_VALUES.put(34, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(35, 0.17605634);
        Values.SENSITIVITY_MCP_VALUES.put(36, 0.18309858);
        Values.SENSITIVITY_MCP_VALUES.put(37, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(38, 0.19014084);
        Values.SENSITIVITY_MCP_VALUES.put(39, 0.1971831);
        Values.SENSITIVITY_MCP_VALUES.put(40, 0.20422535);
        Values.SENSITIVITY_MCP_VALUES.put(41, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(42, 0.2112676);
        Values.SENSITIVITY_MCP_VALUES.put(43, 0.21830986);
        Values.SENSITIVITY_MCP_VALUES.put(44, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(45, 0.22535211);
        Values.SENSITIVITY_MCP_VALUES.put(46, 0.23239437);
        Values.SENSITIVITY_MCP_VALUES.put(47, 0.23943663);
        Values.SENSITIVITY_MCP_VALUES.put(48, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(49, 0.24647887);
        Values.SENSITIVITY_MCP_VALUES.put(50, 0.2535211);
        Values.SENSITIVITY_MCP_VALUES.put(51, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(52, 0.26056337);
        Values.SENSITIVITY_MCP_VALUES.put(53, 0.26760563);
        Values.SENSITIVITY_MCP_VALUES.put(54, 0.2746479);
        Values.SENSITIVITY_MCP_VALUES.put(55, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(56, 0.28169015);
        Values.SENSITIVITY_MCP_VALUES.put(57, 0.28873238);
        Values.SENSITIVITY_MCP_VALUES.put(58, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(59, 0.29577464);
        Values.SENSITIVITY_MCP_VALUES.put(60, 0.3028169);
        Values.SENSITIVITY_MCP_VALUES.put(61, 0.30985916);
        Values.SENSITIVITY_MCP_VALUES.put(62, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(63, 0.31690142);
        Values.SENSITIVITY_MCP_VALUES.put(64, 0.32394367);
        Values.SENSITIVITY_MCP_VALUES.put(65, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(66, 0.3309859);
        Values.SENSITIVITY_MCP_VALUES.put(67, 0.33802816);
        Values.SENSITIVITY_MCP_VALUES.put(68, 0.34507042);
        Values.SENSITIVITY_MCP_VALUES.put(69, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(70, 0.35211268);
        Values.SENSITIVITY_MCP_VALUES.put(71, 0.35915494);
        Values.SENSITIVITY_MCP_VALUES.put(72, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(73, 0.36619717);
        Values.SENSITIVITY_MCP_VALUES.put(74, 0.37323943);
        Values.SENSITIVITY_MCP_VALUES.put(75, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(76, 0.3802817);
        Values.SENSITIVITY_MCP_VALUES.put(77, 0.38732395);
        Values.SENSITIVITY_MCP_VALUES.put(78, 0.3943662);
        Values.SENSITIVITY_MCP_VALUES.put(79, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(80, 0.40140846);
        Values.SENSITIVITY_MCP_VALUES.put(81, 0.4084507);
        Values.SENSITIVITY_MCP_VALUES.put(82, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(83, 0.41549295);
        Values.SENSITIVITY_MCP_VALUES.put(84, 0.4225352);
        Values.SENSITIVITY_MCP_VALUES.put(85, 0.42957747);
        Values.SENSITIVITY_MCP_VALUES.put(86, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(87, 0.43661973);
        Values.SENSITIVITY_MCP_VALUES.put(88, 0.44366196);
        Values.SENSITIVITY_MCP_VALUES.put(89, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(90, 0.45070422);
        Values.SENSITIVITY_MCP_VALUES.put(91, 0.45774648);
        Values.SENSITIVITY_MCP_VALUES.put(92, 0.46478873);
        Values.SENSITIVITY_MCP_VALUES.put(93, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(94, 0.471831);
        Values.SENSITIVITY_MCP_VALUES.put(95, 0.47887325);
        Values.SENSITIVITY_MCP_VALUES.put(96, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(97, 0.48591548);
        Values.SENSITIVITY_MCP_VALUES.put(98, 0.49295774);
        Values.SENSITIVITY_MCP_VALUES.put(99, 0.5);
        Values.SENSITIVITY_MCP_VALUES.put(100, 0.5);
        Values.SENSITIVITY_MCP_VALUES.put(101, 0.5070422);
        Values.SENSITIVITY_MCP_VALUES.put(102, 0.5140845);
        Values.SENSITIVITY_MCP_VALUES.put(103, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(104, 0.52112675);
        Values.SENSITIVITY_MCP_VALUES.put(105, 0.52816904);
        Values.SENSITIVITY_MCP_VALUES.put(106, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(107, 0.53521127);
        Values.SENSITIVITY_MCP_VALUES.put(108, 0.5422535);
        Values.SENSITIVITY_MCP_VALUES.put(109, 0.5492958);
        Values.SENSITIVITY_MCP_VALUES.put(110, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(111, 0.556338);
        Values.SENSITIVITY_MCP_VALUES.put(112, 0.5633803);
        Values.SENSITIVITY_MCP_VALUES.put(113, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(114, 0.57042253);
        Values.SENSITIVITY_MCP_VALUES.put(115, 0.57746476);
        Values.SENSITIVITY_MCP_VALUES.put(116, 0.58450705);
        Values.SENSITIVITY_MCP_VALUES.put(117, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(118, 0.5915493);
        Values.SENSITIVITY_MCP_VALUES.put(119, 0.59859157);
        Values.SENSITIVITY_MCP_VALUES.put(120, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(121, 0.6056338);
        Values.SENSITIVITY_MCP_VALUES.put(122, 0.6126761);
        Values.SENSITIVITY_MCP_VALUES.put(123, 0.6197183);
        Values.SENSITIVITY_MCP_VALUES.put(124, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(125, 0.62676054);
        Values.SENSITIVITY_MCP_VALUES.put(126, 0.63380283);
        Values.SENSITIVITY_MCP_VALUES.put(127, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(128, 0.64084506);
        Values.SENSITIVITY_MCP_VALUES.put(129, 0.64788735);
        Values.SENSITIVITY_MCP_VALUES.put(130, 0.6549296);
        Values.SENSITIVITY_MCP_VALUES.put(131, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(132, 0.6619718);
        Values.SENSITIVITY_MCP_VALUES.put(133, 0.6690141);
        Values.SENSITIVITY_MCP_VALUES.put(134, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(135, 0.6760563);
        Values.SENSITIVITY_MCP_VALUES.put(136, 0.6830986);
        Values.SENSITIVITY_MCP_VALUES.put(137, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(138, 0.69014084);
        Values.SENSITIVITY_MCP_VALUES.put(139, 0.6971831);
        Values.SENSITIVITY_MCP_VALUES.put(140, 0.70422536);
        Values.SENSITIVITY_MCP_VALUES.put(141, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(142, 0.7112676);
        Values.SENSITIVITY_MCP_VALUES.put(143, 0.7183099);
        Values.SENSITIVITY_MCP_VALUES.put(144, 0.7253521);
        Values.SENSITIVITY_MCP_VALUES.put(145, 0.7253521);
        Values.SENSITIVITY_MCP_VALUES.put(146, 0.73239434);
        Values.SENSITIVITY_MCP_VALUES.put(147, 0.7394366);
        Values.SENSITIVITY_MCP_VALUES.put(148, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(149, 0.74647886);
        Values.SENSITIVITY_MCP_VALUES.put(150, 0.75352114);
        Values.SENSITIVITY_MCP_VALUES.put(151, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(152, 0.7605634);
        Values.SENSITIVITY_MCP_VALUES.put(153, 0.76760566);
        Values.SENSITIVITY_MCP_VALUES.put(154, 0.7746479);
        Values.SENSITIVITY_MCP_VALUES.put(155, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(156, 0.7816901);
        Values.SENSITIVITY_MCP_VALUES.put(157, 0.7887324);
        Values.SENSITIVITY_MCP_VALUES.put(158, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(159, 0.79577464);
        Values.SENSITIVITY_MCP_VALUES.put(160, 0.8028169);
        Values.SENSITIVITY_MCP_VALUES.put(161, 0.80985916);
        Values.SENSITIVITY_MCP_VALUES.put(162, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(163, 0.8169014);
        Values.SENSITIVITY_MCP_VALUES.put(164, 0.8239437);
        Values.SENSITIVITY_MCP_VALUES.put(165, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(166, 0.8309859);
        Values.SENSITIVITY_MCP_VALUES.put(167, 0.8380282);
        Values.SENSITIVITY_MCP_VALUES.put(168, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(169, 0.8450704);
        Values.SENSITIVITY_MCP_VALUES.put(170, 0.85211265);
        Values.SENSITIVITY_MCP_VALUES.put(171, 0.85915494);
        Values.SENSITIVITY_MCP_VALUES.put(172, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(173, 0.86619717);
        Values.SENSITIVITY_MCP_VALUES.put(174, 0.87323946);
        Values.SENSITIVITY_MCP_VALUES.put(175, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(176, 0.8802817);
        Values.SENSITIVITY_MCP_VALUES.put(177, 0.8873239);
        Values.SENSITIVITY_MCP_VALUES.put(178, 0.8943662);
        Values.SENSITIVITY_MCP_VALUES.put(179, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(180, 0.90140843);
        Values.SENSITIVITY_MCP_VALUES.put(181, 0.9084507);
        Values.SENSITIVITY_MCP_VALUES.put(182, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(183, 0.91549295);
        Values.SENSITIVITY_MCP_VALUES.put(184, 0.92253524);
        Values.SENSITIVITY_MCP_VALUES.put(185, 0.92957747);
        Values.SENSITIVITY_MCP_VALUES.put(186, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(187, 0.9366197);
        Values.SENSITIVITY_MCP_VALUES.put(188, 0.943662);
        Values.SENSITIVITY_MCP_VALUES.put(189, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(190, 0.9507042);
        Values.SENSITIVITY_MCP_VALUES.put(191, 0.9577465);
        Values.SENSITIVITY_MCP_VALUES.put(192, 0.96478873);
        Values.SENSITIVITY_MCP_VALUES.put(193, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(194, 0.97183096);
        Values.SENSITIVITY_MCP_VALUES.put(195, 0.97887325);
        Values.SENSITIVITY_MCP_VALUES.put(196, 0.0);
        Values.SENSITIVITY_MCP_VALUES.put(197, 0.9859155);
        Values.SENSITIVITY_MCP_VALUES.put(198, 0.9929578);
        Values.SENSITIVITY_MCP_VALUES.put(199, 1.0);
        Values.SENSITIVITY_MCP_VALUES.put(200, 1.0);
        Values.WATER_VALUES.add(0.05999999821185753);
        Values.WATER_VALUES.add(0.051999998867515274);
        Values.WATER_VALUES.add(0.06159999881982969);
        Values.WATER_VALUES.add(0.06927999889612124);
        Values.WATER_VALUES.add(0.07542399904870933);
        Values.WATER_VALUES.add(0.08033919924402255);
        Values.WATER_VALUES.add(0.08427135945886732);
        Values.WATER_VALUES.add(0.0874170876776148);
        Values.WATER_VALUES.add(0.08993367029011523);
        Values.WATER_VALUES.add(0.0519469373041872);
        Values.WATER_VALUES.add(-0.05647059355944606);
        Values.WATER_VALUES.add(0.03812980539822064);
        Values.WATER_VALUES.add(-0.035014067535591664);
        Values.WATER_VALUES.add(-0.04453032983624894);
        Values.WATER_VALUES.add(0.019999999105927202);
        Values.WATER_VALUES.add(-0.07159953051526458);
        Values.WATER_VALUES.add(0.020820931761605266);
        Values.WATER_VALUES.add(0.0010261658043049238);
        Values.WATER_VALUES.add(-0.023717291273619878);
        Values.WATER_VALUES.add(-0.010724939925282229);
    }
}
