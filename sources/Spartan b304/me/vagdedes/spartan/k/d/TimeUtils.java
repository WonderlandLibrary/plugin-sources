package me.vagdedes.spartan.k.d;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class TimeUtils
{
    public TimeUtils() {
        super();
    }
    
    public static String a(final Timestamp timestamp) {
        return timestamp.toString().substring(11, 13) + ":" + timestamp.toString().substring(14, 16) + ":" + timestamp.toString().substring(17, 19);
    }
    
    public static String b(final Timestamp timestamp) {
        return timestamp.toString().substring(8, 10) + "/" + timestamp.toString().substring(5, 7) + "/" + timestamp.toString().substring(0, 4);
    }
    
    public static String c(final Timestamp timestamp) {
        return timestamp.toString().substring(5, 7) + "/" + timestamp.toString().substring(8, 10) + "/" + timestamp.toString().substring(0, 4);
    }
    
    public static String d(final Timestamp timestamp) {
        return timestamp.toString().substring(0, 4) + "/" + timestamp.toString().substring(5, 7) + "/" + timestamp.toString().substring(8, 10);
    }
    
    public static Timestamp a(final int n, final int n2, final int n3) {
        final Date time = new Date();
        final Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        instance.add(5, n);
        instance.add(10, n2);
        instance.add(12, n3);
        return new Timestamp(instance.getTime().getTime());
    }
    
    public static long a(final long n) {
        return System.currentTimeMillis() - n;
    }
    
    public static int a(final Timestamp timestamp, final int n) {
        return (int)((System.currentTimeMillis() - timestamp.getTime()) / n);
    }
    
    public static long a(final int n, final char c) {
        final long n2 = n * 1000L;
        switch (c) {
            case 'm': {
                return n2 * 60L;
            }
            case 'h': {
                return n2 * 60L * 60L;
            }
            case 'd': {
                return n2 * 60L * 60L * 24L;
            }
            case 'w': {
                return n2 * 60L * 60L * 24L * 7L;
            }
            case 'y': {
                return n2 * 60L * 60L * 24L * 365L;
            }
            default: {
                return 0L;
            }
        }
    }
}
