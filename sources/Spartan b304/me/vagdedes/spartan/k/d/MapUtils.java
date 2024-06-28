package me.vagdedes.spartan.k.d;

import java.util.HashMap;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.ConcurrentHashMap;

public class MapUtils
{
    public MapUtils() {
        super();
    }
    
    public static int a(final ConcurrentHashMap obj) {
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            return out.size() / 1024;
        }
        catch (IOException ex) {
            return 0;
        }
    }
    
    public static int a(final HashMap obj) {
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            return out.size() / 1024;
        }
        catch (IOException ex) {
            return 0;
        }
    }
}
