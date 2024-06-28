package me.vagdedes.spartan.k.b;

import java.util.Arrays;
import java.io.InputStream;

public class IOUtils
{
    public IOUtils() {
        super();
    }
    
    public static byte[] readFully(final InputStream inputStream) {
        byte[] original = new byte[0];
        final int n = Integer.MAX_VALUE;
        int i = 0;
        while (i < n) {
            int min;
            if (i >= original.length) {
                min = Math.min(n - i, original.length + 1024);
                if (original.length < i + min) {
                    original = Arrays.copyOf(original, i + min);
                }
            }
            else {
                min = original.length - i;
            }
            final int read = inputStream.read(original, i, min);
            if (read < 0) {
                if (original.length != i) {
                    original = Arrays.copyOf(original, i);
                    break;
                }
                break;
            }
            else {
                i += read;
            }
        }
        return original;
    }
    
    public static InputStream inputStream(final Class clazz) {
        return IOUtils.class.getResourceAsStream("/" + replace(clazz.getName()) + b(true));
    }
    
    private static String replace(final String s) {
        return s.replaceAll("\\.", "/");
    }
    
    private static String b(final boolean b) {
        return b ? ".class" : ".java";
    }
}
