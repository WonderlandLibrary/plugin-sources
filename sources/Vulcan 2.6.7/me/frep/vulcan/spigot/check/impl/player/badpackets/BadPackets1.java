package me.frep.vulcan.spigot.check.impl.player.badpackets;

import org.bukkit.inventory.ItemStack;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import org.bukkit.enchantments.Enchantment;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = '1', complexType = "Nuker", description = "Sent too many Start Dig packets.")
public class BadPackets1 extends AbstractCheck
{
    private long lastStart;
    
    public BadPackets1(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockDig()) {
            final WrappedPacketInBlockDig wrapper = this.data.getBlockDigWrapper();
            if (wrapper.getDigType() == WrappedPacketInBlockDig.PlayerDigType.START_DESTROY_BLOCK) {
                final long now = System.currentTimeMillis();
                final long delay = now - this.lastStart;
                final ItemStack tool = this.data.getPlayer().getItemInHand();
                if (tool.containsEnchantment(Enchantment.DIG_SPEED) && tool.getEnchantmentLevel(Enchantment.DIG_SPEED) > 5) {
                    return;
                }
                final boolean exempt = this.isExempt(ExemptType.FAST, ExemptType.SWIMMING, ExemptType.LIQUID, ExemptType.KELP);
                final boolean invalid = delay < 3L;
                final boolean netherrack = this.data.getActionProcessor().getSinceNetherrackBreakTicks() < 20;
                if (invalid && !exempt && !netherrack) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("delay=" + delay);
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.lastStart = System.currentTimeMillis();
            }
        }
    }
}
