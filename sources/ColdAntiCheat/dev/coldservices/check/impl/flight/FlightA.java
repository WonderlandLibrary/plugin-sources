package dev.coldservices.check.impl.flight;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientPosition;
import com.avaje.ebeaninternal.server.type.reflect.CheckImmutable;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.PositionUpdate;

@CheckManifest(name = "Flight", type = "A", description = "Detects static Y motion")
public class FlightA extends Check implements PositionCheck {

    public FlightA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(PositionUpdate update) {
        boolean exempt = this.isExempt(ExemptType.CHUNK, ExemptType.BOAT, ExemptType.FLIGHT,
                ExemptType.JOIN, ExemptType.TELEPORT, ExemptType.UNDER_BLOCK,
                ExemptType.STEP);

        if(data.getPositionTracker().getLastY() == data.getPositionTracker().getLastLastY() && !exempt && !update.isOnGround()) {
            if(this.buffer.increase() > 5) {
                this.failNoBan("y=" + data.getPositionTracker().getY() + "==" + data.getPositionTracker().getLastLastY());
                this.executeSetback(true, false);
            }
        } else {
            this.buffer.setBuffer(0);
        }
    }
}
