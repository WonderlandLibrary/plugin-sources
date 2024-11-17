package me.frep.vulcan.spigot.util.value;

public final class PingValues
{
    public static final short VELOCITY_MIN = -31768;
    public static final short VELOCITY_MAX = -30769;
    public static final short ABILITIES_MIN = -30768;
    public static final short ABILITIES_MAX = -29769;
    public static final short POSITION_MIN = -29768;
    public static final short POSITION_MAX = -28769;
    public static final short ENTITY_EFFECT_MIN = -28768;
    public static final short ENTITY_EFFECT_MAX = -27769;
    public static final short REMOVE_ENTITY_EFFECT_MIN = -27768;
    public static final short REMOVE_ENTITY_EFFECT_MAX = -26769;
    public static final short GAME_STATE_CHANGE_MIN = -26768;
    public static final short GAME_STATE_CHANGE_MAX = -25769;
    public static final short SCHEDULER_MIN = -25768;
    public static final short SCHEDULER_MAX = -24769;
    
    private PingValues() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
