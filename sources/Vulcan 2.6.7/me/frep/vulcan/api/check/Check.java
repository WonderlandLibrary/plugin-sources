package me.frep.vulcan.api.check;

public interface Check
{
    String getCategory();
    
    String getName();
    
    char getType();
    
    char getDisplayType();
    
    int getMaxVl();
    
    int getVl();
    
    double getMaxBuffer();
    
    boolean isExperimental();
    
    double getBufferDecay();
    
    double getBufferMultiple();
    
    int getMinimumVlToNotify();
    
    int getAlertInterval();
    
    double getBuffer();
    
    void setVl(final int p0);
    
    boolean isPunishable();
    
    void setBuffer(final double p0);
    
    String getDescription();
    
    String getComplexType();
    
    String getDisplayName();
}
