package com.elevatemc.anticheat.database;

import com.elevatemc.anticheat.Hood;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.Getter;
import org.bson.Document;

import java.util.UUID;

public class LogHandler {

    private static final String MONGO_COLLECTION = "PlayerViolations";
    private static MongoDatabase db;
    private static MongoClient mongoPool = new MongoClient();

    @Getter
    private static final boolean loading = false;

    public LogHandler() {
        String database = "admin";
        String host = "127.0.0.1";
        int port = 27017;
        String password = "";
        String username = "";

        if (password.isEmpty()) {
            mongoPool = new MongoClient(host, port);
        } else {
            mongoPool = new MongoClient(
                    new ServerAddress(host, port),
                    MongoCredential.createCredential(username, database, password.toCharArray()),
                    MongoClientOptions.builder().build()
            );
        }

        if (!database.isEmpty()) {
            db = mongoPool.getDatabase(database);
        }
    }

    public void closeMongo() {
        mongoPool.close();
    }

    public Document getPlayer(UUID uuid) {
        return getCollection(MONGO_COLLECTION).find(Filters.eq("uuid", uuid.toString())).first();
    }

    public MongoCollection<Document> getCollection(String coll) {
        if (db == null) {
            db = mongoPool.getDatabase(Hood.get().getName());
        }
        return db.getCollection(coll);
    }

}