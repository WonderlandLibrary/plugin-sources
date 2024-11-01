package com.elevatemc.anticheat.base.check.violation.log;

import lombok.Getter;
import org.bson.Document;

import java.util.UUID;

@Getter
public class BanwaveLog {

    private final UUID uuid;
    private final String check;


    public BanwaveLog(UUID uuid, String check) {

        this.uuid = uuid;
        this.check = check;
    }


    public static Document toDocument(BanwaveLog log) {
        Document document = new Document();

        document.put("uuid", log.getUuid().toString());
        document.put("check", log.getCheck());

        return document;
    }
}