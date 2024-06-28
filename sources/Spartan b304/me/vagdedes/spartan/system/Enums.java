package me.vagdedes.spartan.system;

public class Enums
{
    public Enums() {
        super();
    }
    
    public enum HackType
    {
        XRay, 
        CombatAnalysis, 
        Exploits, 
        EntityMove, 
        NoSwing, 
        IrregularMovements, 
        Clip, 
        ImpossibleActions, 
        ItemDrops, 
        AutoRespawn, 
        InventoryClicks, 
        Sprint, 
        Jesus, 
        NoSlowdown, 
        Criticals, 
        Nuker, 
        GhostHand, 
        Liquids, 
        BlockReach, 
        ElytraMove, 
        BoatMove, 
        FastBow, 
        FastClicks, 
        FastHeal, 
        Fly, 
        ImpossibleInventory, 
        HitReach, 
        FastBreak, 
        Speed, 
        FastPlace, 
        MorePackets, 
        NoFall, 
        IllegalPosition, 
        FastEat, 
        Velocity, 
        KillAura;
        
        private static final /* synthetic */ HackType[] $VALUES;
        
        public static HackType[] values() {
            return HackType.$VALUES.clone();
        }
        
        public static HackType valueOf(final String name) {
            return Enum.<HackType>valueOf(HackType.class, name);
        }
        
        static {
            $VALUES = new HackType[] { HackType.XRay, HackType.CombatAnalysis, HackType.Exploits, HackType.EntityMove, HackType.NoSwing, HackType.IrregularMovements, HackType.Clip, HackType.ImpossibleActions, HackType.ItemDrops, HackType.AutoRespawn, HackType.InventoryClicks, HackType.Sprint, HackType.Jesus, HackType.NoSlowdown, HackType.Criticals, HackType.Nuker, HackType.GhostHand, HackType.Liquids, HackType.BlockReach, HackType.ElytraMove, HackType.BoatMove, HackType.FastBow, HackType.FastClicks, HackType.FastHeal, HackType.Fly, HackType.ImpossibleInventory, HackType.HitReach, HackType.FastBreak, HackType.Speed, HackType.FastPlace, HackType.MorePackets, HackType.NoFall, HackType.IllegalPosition, HackType.FastEat, HackType.Velocity, HackType.KillAura };
        }
    }
    
    public enum Permission
    {
        condition, 
        use_report, 
        report, 
        staff_chat, 
        wave, 
        reconnect, 
        admin, 
        reload, 
        kick, 
        verbose, 
        bypass, 
        manage, 
        info, 
        kick_message, 
        ping, 
        chat_protection, 
        toggle, 
        warn, 
        use_bypass, 
        ban, 
        unban, 
        ban_info, 
        ban_message, 
        mining, 
        debug, 
        ip_limit;
        
        private static final /* synthetic */ Permission[] $VALUES;
        
        public static Permission[] values() {
            return Permission.$VALUES.clone();
        }
        
        public static Permission valueOf(final String name) {
            return Enum.<Permission>valueOf(Permission.class, name);
        }
        
        static {
            $VALUES = new Permission[] { Permission.condition, Permission.use_report, Permission.report, Permission.staff_chat, Permission.wave, Permission.reconnect, Permission.admin, Permission.reload, Permission.kick, Permission.verbose, Permission.bypass, Permission.manage, Permission.info, Permission.kick_message, Permission.ping, Permission.chat_protection, Permission.toggle, Permission.warn, Permission.use_bypass, Permission.ban, Permission.unban, Permission.ban_info, Permission.ban_message, Permission.mining, Permission.debug, Permission.ip_limit };
        }
    }
    
    public enum ToggleAction
    {
        Enable, 
        Disable;
        
        private static final /* synthetic */ ToggleAction[] $VALUES;
        
        public static ToggleAction[] values() {
            return ToggleAction.$VALUES.clone();
        }
        
        public static ToggleAction valueOf(final String name) {
            return Enum.<ToggleAction>valueOf(ToggleAction.class, name);
        }
        
        static {
            $VALUES = new ToggleAction[] { ToggleAction.Enable, ToggleAction.Disable };
        }
    }
}
