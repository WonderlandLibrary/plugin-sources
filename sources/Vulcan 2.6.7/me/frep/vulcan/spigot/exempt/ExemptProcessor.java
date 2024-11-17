package me.frep.vulcan.spigot.exempt;

import java.util.function.Function;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.data.PlayerData;

public class ExemptProcessor
{
    private final PlayerData data;
    
    public boolean isExempt(final ExemptType exemptType) {
        return exemptType.getException().apply(this.data);
    }
    
    public boolean isExempt(final ExemptType... exemptTypes) {
        for (final ExemptType exemptType : exemptTypes) {
            if (this.isExempt(exemptType)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isExempt(final Function<PlayerData, Boolean> exception) {
        return exception.apply(this.data);
    }
    
    public ExemptProcessor(final PlayerData data) {
        this.data = data;
    }
}
