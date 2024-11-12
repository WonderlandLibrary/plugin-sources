package dev.coldservices.check.impl.simulation.invmove;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import ac.artemis.packet.wrapper.client.PacketPlayClientWindowButton;
import ac.artemis.packet.wrapper.client.PacketPlayClientWindowClick;
import ac.artemis.packet.wrapper.client.PacketPlayClientWindowClose;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientWindowButton;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.ActionTracker;
import dev.coldservices.data.tracker.impl.AttributeTracker;
import dev.coldservices.data.tracker.impl.EmulationTracker;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.PositionUpdate;

@CheckManifest(name = "Inventory Move", type = "A", description = "Checks if the player is sprinting while the inv is open")
public class InvMoveA extends Check implements PositionCheck {

    private boolean open;

    public InvMoveA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(PositionUpdate update) {
        AttributeTracker tracker = data.getAttributeTracker();

        if(tracker.isInventoryOpen()) {
            boolean exempt = this.isExempt(ExemptType.JOIN, ExemptType.TELEPORTED_RECENTLY, ExemptType.TELEPORT,
                    ExemptType.ICE, ExemptType.CHUNK, ExemptType.CLIMBABLE, ExemptType.VELOCITY);

            if(exempt) return;

            ActionTracker emulationTracker = data.getActionTracker();

            if(emulationTracker.isSprinting() && this.buffer.increase() > 4) {
                this.failNoBan("xd");
                this.executeSetback(false, false);
            } else {
                this.buffer.decrease();
            }
        }
    }
}
