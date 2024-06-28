package me.vagdedes.spartan.k.b;

public class SiteUtils
{
    public SiteUtils() {
        super();
    }
    
    public static String c(String replace) {
        if (replace != null) {
            replace = replace.replace("http://", "");
            for (int i = 0; i <= replace.length(); ++i) {
                if (replace.substring(i).startsWith("/")) {
                    return replace.substring(0, i);
                }
            }
        }
        return null;
    }
}
