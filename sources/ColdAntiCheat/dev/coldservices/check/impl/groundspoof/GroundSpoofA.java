package dev.coldservices.check.impl.groundspoof;

import cc.ghast.packet.wrapper.packet.play.server.GPacketPlayServerUpdateAttributes;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.update.PositionUpdate;

@CheckManifest(name = "GroundSpoof", type = "A", description = "Detects ground spoofs (some of em)")
public class GroundSpoofA extends Check implements PositionCheck {

    public GroundSpoofA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(PositionUpdate update) {
        double posY = update.getY();
        boolean grounded = update.isOnGround();

        boolean serverGrounded = data.getPositionTracker().isServerGround();

        if(grounded && !serverGrounded) {
            if(this.buffer.increase() > 2) {
                this.failNoBan("claimed to be on ground");
            }
        } else {
            this.buffer.decrease();
        }
    }
}
