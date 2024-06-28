/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.mongodb.MongoClient
 *  com.mongodb.MongoClientOptions
 *  com.mongodb.ServerAddress
 *  com.mongodb.client.MongoCollection
 *  com.mongodb.client.MongoDatabase
 */
package com.unknownmyname.manager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.unknownmyname.main.Main;
import com.unknownmyname.manager.OptionsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DatabaseManager {
    private static final /* synthetic */ String[] I;
    private static /* synthetic */ DatabaseManager instance;
    private /* synthetic */ boolean connected;
    private /* synthetic */ MongoCollection banCollection;
    private /* synthetic */ MongoClient mongoClient;
    private /* synthetic */ MongoDatabase mongoDatabase;
    private /* synthetic */ MongoCollection logsCollection;

    public MongoDatabase getMongoDatabase() {
        return this.mongoDatabase;
    }

    public MongoClient getMongoClient() {
        return this.mongoClient;
    }

    public static DatabaseManager getInstance() {
        DatabaseManager databaseManager;
        if (instance == null) {
            databaseManager = instance = new DatabaseManager();
            "".length();
            if (4 <= 0) {
                throw null;
            }
        } else {
            databaseManager = instance;
        }
        return databaseManager;
    }

    public MongoCollection getBanCollection() {
        return this.banCollection;
    }

    public DatabaseManager() {
        this.connected = "".length();
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), "Blowfish");
            Cipher des = Cipher.getInstance("Blowfish");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void I() {
        I = new String["   ".length()];
        DatabaseManager.I["".length()] = DatabaseManager.I("Pysgh5/VOrFjqcFXqcstjGwUQfwfyjuBg1hAA3TmcRI=", "uhpIK");
        DatabaseManager.I[" ".length()] = DatabaseManager.I("T/uhMUaoO38=", "Ddqia");
        DatabaseManager.I["  ".length()] = DatabaseManager.I("KVwzYCePrc4=", "eYABC");
    }

    static {
        DatabaseManager.I();
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void enable() {
        if (OptionsManager.getInstance().isMongoEnabled()) {
            try {
                this.connect();
                "".length();
            }
            catch (Throwable var2) {
                Main.getPlugin().getLogger().log(Level.SEVERE, I["".length()], var2.getCause());
            }
            if (2 >= 4) {
                throw null;
            }
        }
    }

    public void disable() {
        block3 : {
            if (!this.connected) break block3;
            try {
                this.mongoClient.close();
                "".length();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            if (3 < 0) {
                throw null;
            }
            this.connected = "".length();
        }
    }

    public MongoCollection getLogsCollection() {
        return this.logsCollection;
    }

    private void connect() {
        ServerAddress address = new ServerAddress(OptionsManager.getInstance().getMongoHost(), OptionsManager.getInstance().getMongoPort());
        MongoClientOptions options = MongoClientOptions.builder().build();
        if (OptionsManager.getInstance().isMongoAuthEnabled()) {
            this.mongoClient = new MongoClient(address, options);
            "".length();
            if (3 != 3) {
                throw null;
            }
        } else {
            this.mongoClient = new MongoClient(address, options);
        }
        this.mongoDatabase = this.mongoClient.getDatabase(OptionsManager.getInstance().getMongoDatabase());
        this.logsCollection = this.mongoDatabase.getCollection(I[" ".length()]);
        this.banCollection = this.mongoDatabase.getCollection(I["  ".length()]);
        this.connected = " ".length();
    }
}

