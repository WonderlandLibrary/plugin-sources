package me.vagdedes.spartan.k.d;

import java.util.Iterator;
import java.util.ArrayList;
import java.nio.charset.Charset;

public class StringUtils
{
    private static final Charset UTF_8;
    
    public StringUtils() {
        super();
    }
    
    private static String a(final byte[] bytes) {
        return (bytes == null) ? null : new String(bytes, StringUtils.UTF_8);
    }
    
    public static String newStringUtf8(final byte[] array) {
        return a(array);
    }
    
    public static boolean a(final ArrayList<String> list, final String s) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (s.contains(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public static String getClearColorString(final String s) {
        return s.replaceAll("§[a-z,0-9]", "");
    }
    
    public static String substring(final String s, final int n, final int endIndex) {
        final int length = s.length();
        return (n > length || n == endIndex) ? s : ((endIndex > length) ? s.substring(n, length) : s.substring(n, endIndex));
    }
    
    static {
        UTF_8 = Charset.forName("UTF-8");
    }
}
