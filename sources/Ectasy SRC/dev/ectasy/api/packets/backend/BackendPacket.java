package dev.ectasy.api.packets.backend;

import org.json.JSONObject;

public class BackendPacket {
    private final BackendPacketInfo info;

    public BackendPacket(){
        if(!getClass().isAnnotationPresent(BackendPacketInfo.class))
            throw new RuntimeException("BackendPacketInfo annotation not found in class: " + getClass().getName());
        info = getClass().getAnnotation(BackendPacketInfo.class);
    }

    public JSONObject write(){
        return new JSONObject();
    }

    public int getId(){
        return info.id();
    }
}
