package me.frep.vulcan.spigot.packet;

import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.NMSPacket;

public class Packet
{
    private final Direction direction;
    private final NMSPacket rawPacket;
    private final byte packetId;
    
    public Packet(final Direction direction, final NMSPacket rawPacket, final byte packetId) {
        this.direction = direction;
        this.rawPacket = rawPacket;
        this.packetId = packetId;
    }
    
    public boolean isReceiving() {
        return this.direction == Direction.RECEIVE;
    }
    
    public boolean isSending() {
        return this.direction == Direction.SEND;
    }
    
    public boolean isFlying() {
        return this.isReceiving() && PacketType.Play.Client.Util.isInstanceOfFlying(this.packetId);
    }
    
    public boolean isUseEntity() {
        return this.isReceiving() && this.packetId == -100;
    }
    
    public boolean isSpectate() {
        return this.isReceiving() && this.packetId == -70;
    }
    
    public boolean isPong() {
        return this.isReceiving() && this.packetId == 28;
    }
    
    public boolean isRotation() {
        return this.isReceiving() && (this.packetId == -94 || this.packetId == -95);
    }
    
    public boolean isPosition() {
        return this.isReceiving() && (this.packetId == -96 || this.packetId == -95);
    }
    
    public boolean isExplosion() {
        return this.isSending() && this.packetId == -38;
    }
    
    public boolean isArmAnimation() {
        return this.isReceiving() && this.packetId == -71;
    }
    
    public boolean isCreativeSlot() {
        return this.isReceiving() && this.packetId == -75;
    }
    
    public boolean isMetaData() {
        return this.isSending() && this.packetId == 1;
    }
    
    public boolean isAbilities() {
        return this.isReceiving() && this.packetId == -88;
    }
    
    public boolean isAbilitiesOut() {
        return this.isSending() && this.packetId == -17;
    }
    
    public boolean isHeldItemSlotOut() {
        return this.isSending() && this.packetId == -3;
    }
    
    public boolean isBlockPlaceEvent() {
        return this.isReceiving() && this.packetId == 120;
    }
    
    public boolean isCustomPayload() {
        return this.isReceiving() && this.packetId == -103;
    }
    
    public boolean isProjectileLaunchEvent() {
        return this.isReceiving() && this.packetId == 98;
    }
    
    public boolean isChat() {
        return this.isReceiving() && this.packetId == -111;
    }
    
    public boolean isItemConsumeEvent() {
        return this.isReceiving() && this.packetId == 97;
    }
    
    public boolean isTabComplete() {
        return this.isReceiving() && this.packetId == -108;
    }
    
    public boolean isInteractEvent() {
        return this.isReceiving() && this.packetId == 96;
    }
    
    public boolean isBlockPlace() {
        return (this.isReceiving() && PacketType.Play.Client.Util.isBlockPlace(this.packetId)) || (this.isReceiving() && this.packetId == -68);
    }
    
    public boolean isBlockDig() {
        return this.isReceiving() && this.packetId == -87;
    }
    
    public boolean isWindowClick() {
        return this.isReceiving() && this.packetId == -105;
    }
    
    public boolean isEntityAction() {
        return this.isReceiving() && this.packetId == -86;
    }
    
    public boolean isCloseWindow() {
        return this.isReceiving() && this.packetId == -104;
    }
    
    public boolean isUpdateAttributes() {
        return this.isSending() && this.packetId == 22;
    }
    
    public boolean isKeepAlive() {
        return this.isReceiving() && this.packetId == -98;
    }
    
    public boolean isSteerVehicle() {
        return this.isReceiving() && this.packetId == -85;
    }
    
    public boolean isHeldItemSlot() {
        return this.isReceiving() && this.packetId == -78;
    }
    
    public boolean isClientCommand() {
        return this.isReceiving() && this.packetId == -110;
    }
    
    public boolean isTransaction() {
        return this.isReceiving() && this.packetId == -107;
    }
    
    public boolean isSendTransaction() {
        return this.isSending() && this.packetId == -48;
    }
    
    public boolean isTeleport() {
        return this.isSending() && this.packetId == -13;
    }
    
    public boolean isVehicleMove() {
        return this.isReceiving() && this.packetId == -92;
    }
    
    public boolean isVelocity() {
        return this.isSending() && this.packetId == 3;
    }
    
    public boolean isPositionLook() {
        return this.isReceiving() && this.packetId == -95;
    }
    
    public boolean isRelEntityMove() {
        return this.isSending() && this.packetId == -26;
    }
    
    public boolean isRelEntityMoveLook() {
        return this.isSending() && this.packetId == -25;
    }
    
    public boolean isCreateEntity() {
        return this.isSending() && this.packetId == 27;
    }
    
    public boolean isDeleteEntity() {
        return this.isSending() && this.packetId == -11;
    }
    
    public boolean isBlockBreakEvent() {
        return this.isReceiving() && this.packetId == 100;
    }
    
    public boolean isEntityEffect() {
        return this.isSending() && this.packetId == 23;
    }
    
    public boolean isRemoveEntityEffect() {
        return this.isSending() && this.packetId == -10;
    }
    
    public boolean isGameStateChange() {
        return this.isSending() && this.packetId == -36;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public NMSPacket getRawPacket() {
        return this.rawPacket;
    }
    
    public byte getPacketId() {
        return this.packetId;
    }
    
    public enum Direction
    {
        SEND, 
        RECEIVE;
    }
}
