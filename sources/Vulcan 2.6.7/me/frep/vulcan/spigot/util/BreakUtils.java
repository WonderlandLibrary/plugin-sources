package me.frep.vulcan.spigot.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.enchantments.Enchantment;
import me.frep.vulcan.spigot.util.value.Values;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

public final class BreakUtils
{
    public static long getDigTime(final Block block, final Player player) {
        if (!Values.BLOCK_HARDNESS_VALUES.containsKey(block.getType())) {
            return 0L;
        }
        final ItemStack tool = player.getItemInHand();
        if (tool.getType().toString().equalsIgnoreCase("SHEARS")) {
            return 0L;
        }
        final boolean canHarvest = !block.getDrops(tool).isEmpty();
        double seconds = Values.BLOCK_HARDNESS_VALUES.get(block.getType()) * (canHarvest ? 1.5 : 5.0);
        double multiplier = getToolMultiplier(tool);
        if (tool.containsEnchantment(Enchantment.DIG_SPEED)) {
            final int level = tool.getEnchantmentLevel(Enchantment.DIG_SPEED);
            multiplier += (float)(level * level + 1);
        }
        if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
            multiplier *= 1.0 + 0.2 * PlayerUtil.getPotionLevel(player, PotionEffectType.FAST_DIGGING);
        }
        if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
            multiplier /= (0x3 ^ PlayerUtil.getPotionLevel(player, PotionEffectType.SLOW_DIGGING));
        }
        seconds /= multiplier;
        return (long)(seconds * 1000.0);
    }
    
    private static int getToolMultiplier(final ItemStack tool) {
        final String name = tool.getType().name();
        int multiplier = 0;
        switch (name) {
            case "IRON_PICKAXE":
            case "IRON_AXE":
            case "IRON_SHOVEL":
            case "IRON_SPADE": {
                multiplier = ToolMaterials.IRON.getMultiplier();
                break;
            }
            case "STONE_PICKAXE":
            case "STONE_SHOVEL":
            case "STONE_SPADE":
            case "STONE_AXE": {
                multiplier = ToolMaterials.STONE.getMultiplier();
                break;
            }
            case "WOODEN_PICKAXE":
            case "WOODEN_AXE":
            case "WOODEN_SHOVEL":
            case "WOODEN_SPADE":
            case "WOOD_PICKAXE":
            case "WOOD_AXE":
            case "WOOD_SHOVEL":
            case "WOOD_SPADE": {
                multiplier = ToolMaterials.WOOD.getMultiplier();
                break;
            }
            case "NETHERITE_AXE":
            case "NETHERITE_SHOVEL":
            case "NETHERITE_PICKAXE": {
                multiplier = ToolMaterials.NETHERITE.getMultiplier();
                break;
            }
            case "DIAMOND_PICKAXE":
            case "DIAMOND_AXE":
            case "DIAMOND_SHOVEL":
            case "DIAMOND_SPADE": {
                multiplier = ToolMaterials.DIAMOND.getMultiplier();
                break;
            }
            case "GOLDEN_AXE":
            case "GOLD_AXE":
            case "GOLDEN_SHOVEL":
            case "GOLDEN_SPADE":
            case "GOLD_SHOVEL":
            case "GOLD_SPADE":
            case "GOLDEN_PICKAXE":
            case "GOLD_PICKAXE": {
                multiplier = ToolMaterials.GOLD.getMultiplier();
                break;
            }
            default: {
                multiplier = 1;
                break;
            }
        }
        return multiplier;
    }
    
    private BreakUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    private enum ToolMaterials
    {
        NETHERITE(9), 
        DIAMOND(8), 
        GOLD(12), 
        IRON(6), 
        STONE(4), 
        WOOD(2);
        
        private final int multiplier;
        
        private ToolMaterials(final int multiplier) {
            this.multiplier = multiplier;
        }
        
        private int getMultiplier() {
            return this.multiplier;
        }
    }
}
