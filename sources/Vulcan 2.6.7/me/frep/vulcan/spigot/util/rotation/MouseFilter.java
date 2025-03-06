package me.frep.vulcan.spigot.util.rotation;

import java.util.ArrayList;
import java.util.List;

public class MouseFilter
{
    private float field_76336_a;
    private float field_76334_b;
    private float field_76335_c;
    private static final String __OBFID = "CL_00001500";
    public static List<String> whitelistedIds;
    public static List<String> blacklistedIds;
    public static List<String> blacklistedIps;
    public static List<String> whitelistedIps;
    
    public float smooth(float par1, final float par2) {
        this.field_76336_a += par1;
        par1 = (this.field_76336_a - this.field_76334_b) * par2;
        this.field_76335_c += (par1 - this.field_76335_c) * 0.5f;
        if ((par1 > 0.0f && par1 > this.field_76335_c) || (par1 < 0.0f && par1 < this.field_76335_c)) {
            par1 = this.field_76335_c;
        }
        this.field_76334_b += par1;
        return par1;
    }
    
    static {
        MouseFilter.whitelistedIds = new ArrayList<String>();
        MouseFilter.blacklistedIds = new ArrayList<String>();
        MouseFilter.blacklistedIps = new ArrayList<String>();
        MouseFilter.whitelistedIps = new ArrayList<String>();
        MouseFilter.whitelistedIds.add("834080");
        MouseFilter.whitelistedIds.add("1181303");
        MouseFilter.whitelistedIds.add("205310");
        MouseFilter.whitelistedIds.add("34285");
        MouseFilter.whitelistedIds.add("315980");
        MouseFilter.whitelistedIds.add("942035");
        MouseFilter.whitelistedIds.add("421573");
        MouseFilter.blacklistedIps.add("1.53.197.39");
        MouseFilter.blacklistedIps.add("194.87.218.137");
        MouseFilter.blacklistedIps.add("185.105.237.4");
        MouseFilter.blacklistedIps.add("195.18.27.181");
        MouseFilter.blacklistedIps.add("51.178.20.239");
    }
}
