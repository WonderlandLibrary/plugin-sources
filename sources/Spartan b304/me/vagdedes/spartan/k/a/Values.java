package me.vagdedes.spartan.k.a;

public class Values
{
    public Values() {
        super();
    }
    
    public static double b(double abs, int n) {
        boolean b = false;
        if (abs < 0.0) {
            abs = Math.abs(abs);
            b = true;
        }
        n = (int)Math.pow(10.0, n);
        abs *= n;
        abs = Math.floor(abs) / n;
        return b ? (-abs) : abs;
    }
    
    public static int a(final double n, final int n2) {
        return (int)(b(n, n2) * Math.pow(10.0, n2));
    }
}
