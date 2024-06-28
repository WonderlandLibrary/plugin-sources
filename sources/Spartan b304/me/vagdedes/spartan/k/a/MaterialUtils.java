package me.vagdedes.spartan.k.a;

import me.vagdedes.spartan.Register;
import org.bukkit.Material;
import java.util.HashMap;

public class MaterialUtils
{
    private static final HashMap<String, Material> q;
    
    public MaterialUtils() {
        super();
    }
    
    public static Material a(final String s) {
        return MaterialUtils.q.get(s.toLowerCase());
    }
    
    public static Material a(final String s, final String s2) {
        return Material.getMaterial(Register.v1_13 ? s.toUpperCase() : s2.toUpperCase());
    }
    
    static {
        q = new HashMap<String, Material>(60);
        if (Register.v1_13) {
            MaterialUtils.q.put("water", Material.WATER);
            MaterialUtils.q.put("lava", Material.LAVA);
            MaterialUtils.q.put("web", Material.COBWEB);
            MaterialUtils.q.put("gold_axe", Material.GOLDEN_AXE);
            MaterialUtils.q.put("wood_axe", Material.WOODEN_AXE);
            MaterialUtils.q.put("gold_pickaxe", Material.GOLDEN_PICKAXE);
            MaterialUtils.q.put("wood_pickaxe", Material.WOODEN_PICKAXE);
            MaterialUtils.q.put("gold_sword", Material.GOLDEN_SWORD);
            MaterialUtils.q.put("wood_sword", Material.WOODEN_SWORD);
            MaterialUtils.q.put("watch", Material.CLOCK);
            MaterialUtils.q.put("exp_bottle", Material.EXPERIENCE_BOTTLE);
            MaterialUtils.q.put("redstone_comparator", Material.COMPARATOR);
            MaterialUtils.q.put("cake", Material.CAKE);
            MaterialUtils.q.put("gold_pickaxe", Material.GOLDEN_PICKAXE);
            MaterialUtils.q.put("wood_pickaxe", Material.WOODEN_PICKAXE);
            MaterialUtils.q.put("diamond_spade", Material.DIAMOND_HOE);
            MaterialUtils.q.put("iron_spade", Material.IRON_HOE);
            MaterialUtils.q.put("gold_spade", Material.GOLDEN_HOE);
            MaterialUtils.q.put("stone_spade", Material.STONE_HOE);
            MaterialUtils.q.put("wood_spade", Material.WOODEN_HOE);
            MaterialUtils.q.put("beetroot_block", Material.BEETROOT);
            MaterialUtils.q.put("magma", Material.MAGMA_BLOCK);
            MaterialUtils.q.put("firework", Material.FIREWORK_ROCKET);
            MaterialUtils.q.put("nether_portal", Material.NETHER_PORTAL);
            MaterialUtils.q.put("cobblestone_wall", Material.COBBLESTONE_WALL);
            MaterialUtils.q.put("end_portal_frame", Material.END_PORTAL_FRAME);
            MaterialUtils.q.put("iron_bars", Material.IRON_BARS);
            MaterialUtils.q.put("enchanting_table", Material.ENCHANTING_TABLE);
            MaterialUtils.q.put("piston_extension", Material.PISTON_HEAD);
            MaterialUtils.q.put("piston_moving", Material.MOVING_PISTON);
            MaterialUtils.q.put("piston", Material.PISTON);
            MaterialUtils.q.put("sticky_piston", Material.STICKY_PISTON);
            MaterialUtils.q.put("lily_pad", Material.LILY_PAD);
            MaterialUtils.q.put("repeater_on", Material.REPEATER);
            MaterialUtils.q.put("repeater_off", Material.REPEATER);
            MaterialUtils.q.put("comparator_on", Material.COMPARATOR);
            MaterialUtils.q.put("comparator_off", Material.COMPARATOR);
            MaterialUtils.q.put("soil", Material.FARMLAND);
            MaterialUtils.q.put("gold_boots", Material.GOLDEN_BOOTS);
            MaterialUtils.q.put("grass_block", Material.GRASS_BLOCK);
            MaterialUtils.q.put("diamond_shovel", Material.DIAMOND_SHOVEL);
            MaterialUtils.q.put("gold_shovel", Material.GOLDEN_SHOVEL);
            MaterialUtils.q.put("iron_shovel", Material.IRON_SHOVEL);
            MaterialUtils.q.put("stone_shovel", Material.STONE_SHOVEL);
            MaterialUtils.q.put("wood_shovel", Material.WOODEN_SHOVEL);
            MaterialUtils.q.put("redstone_torch", Material.REDSTONE_TORCH);
            MaterialUtils.q.put("daylight_detector", Material.DAYLIGHT_DETECTOR);
            MaterialUtils.q.put("crafting_table", Material.CRAFTING_TABLE);
            MaterialUtils.q.put("furnace", Material.FURNACE);
        }
        else {
            MaterialUtils.q.put("water", Material.getMaterial("STATIONARY_WATER"));
            MaterialUtils.q.put("lava", Material.getMaterial("STATIONARY_LAVA"));
            MaterialUtils.q.put("web", Material.getMaterial("WEB"));
            MaterialUtils.q.put("gold_axe", Material.getMaterial("GOLD_AXE"));
            MaterialUtils.q.put("wood_axe", Material.getMaterial("WOOD_AXE"));
            MaterialUtils.q.put("gold_pickaxe", Material.getMaterial("GOLD_PICKAXE"));
            MaterialUtils.q.put("wood_pickaxe", Material.getMaterial("WOOD_PICKAXE"));
            MaterialUtils.q.put("gold_sword", Material.getMaterial("GOLD_SWORD"));
            MaterialUtils.q.put("wood_sword", Material.getMaterial("WOOD_SWORD"));
            MaterialUtils.q.put("watch", Material.getMaterial("WATCH"));
            MaterialUtils.q.put("exp_bottle", Material.getMaterial("EXP_BOTTLE"));
            MaterialUtils.q.put("redstone_comparator", Material.getMaterial("REDSTONE_COMPARATOR"));
            MaterialUtils.q.put("cake", Material.getMaterial("CAKE_BLOCK"));
            MaterialUtils.q.put("gold_pickaxe", Material.getMaterial("GOLD_PICKAXE"));
            MaterialUtils.q.put("wood_pickaxe", Material.getMaterial("WOOD_PICKAXE"));
            MaterialUtils.q.put("diamond_spade", Material.getMaterial("DIAMOND_SPADE"));
            MaterialUtils.q.put("iron_spade", Material.getMaterial("IRON_SPADE"));
            MaterialUtils.q.put("gold_spade", Material.getMaterial("GOLD_SPADE"));
            MaterialUtils.q.put("stone_spade", Material.getMaterial("STONE_SPADE"));
            MaterialUtils.q.put("wood_spade", Material.getMaterial("WOOD_SPADE"));
            MaterialUtils.q.put("beetroot_block", Material.getMaterial("BEETROOT_BLOCK"));
            MaterialUtils.q.put("magma", Material.getMaterial("MAGMA"));
            MaterialUtils.q.put("firework", Material.getMaterial("FIREWORK"));
            MaterialUtils.q.put("nether_portal", Material.getMaterial("PORTAL"));
            MaterialUtils.q.put("cobblestone_wall", Material.getMaterial("COBBLE_WALL"));
            MaterialUtils.q.put("end_portal_frame", Material.getMaterial("ENDER_PORTAL_FRAME"));
            MaterialUtils.q.put("iron_bars", Material.getMaterial("IRON_FENCE"));
            MaterialUtils.q.put("enchanting_table", Material.getMaterial("ENCHANTMENT_TABLE"));
            MaterialUtils.q.put("piston_extension", Material.getMaterial("PISTON_EXTENSION"));
            MaterialUtils.q.put("piston_moving", Material.getMaterial("PISTON_MOVING_PIECE"));
            MaterialUtils.q.put("piston", Material.getMaterial("PISTON_BASE"));
            MaterialUtils.q.put("sticky_piston", Material.getMaterial("PISTON_STICKY_BASE"));
            MaterialUtils.q.put("lily_pad", Material.getMaterial("WATER_LILY"));
            MaterialUtils.q.put("repeater_on", Material.getMaterial("DIODE_BLOCK_ON"));
            MaterialUtils.q.put("repeater_off", Material.getMaterial("DIODE_BLOCK_OFF"));
            MaterialUtils.q.put("comparator_on", Material.getMaterial("REDSTONE_COMPARATOR_ON"));
            MaterialUtils.q.put("comparator_off", Material.getMaterial("REDSTONE_COMPARATOR_OFF"));
            MaterialUtils.q.put("soil", Material.getMaterial("SOIL"));
            MaterialUtils.q.put("gold_boots", Material.getMaterial("GOLD_BOOTS"));
            MaterialUtils.q.put("grass_block", Material.getMaterial("GRASS"));
            MaterialUtils.q.put("diamond_shovel", Material.getMaterial("DIAMOND_SPADE"));
            MaterialUtils.q.put("gold_shovel", Material.getMaterial("GOLD_SPADE"));
            MaterialUtils.q.put("iron_shovel", Material.getMaterial("IRON_SPADE"));
            MaterialUtils.q.put("stone_shovel", Material.getMaterial("STONE_SPADE"));
            MaterialUtils.q.put("wood_shovel", Material.getMaterial("WOOD_SPADE"));
            MaterialUtils.q.put("redstone_torch", Material.getMaterial("REDSTONE_TORCH_ON"));
            MaterialUtils.q.put("daylight_detector", Material.getMaterial("DAYLIGHT_DETECTOR_INVERTED"));
            MaterialUtils.q.put("crafting_table", Material.getMaterial("WORKBENCH"));
            MaterialUtils.q.put("furnace", Material.getMaterial("BURNING_FURNACE"));
        }
    }
}
