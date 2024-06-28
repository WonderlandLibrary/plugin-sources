package me.vagdedes.spartan.features.b;

import me.vagdedes.spartan.k.d.MapUtils;
import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.k.d.MathUtils;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Location;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.k.f.TPS;
import java.sql.Timestamp;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import me.vagdedes.spartan.a.b.SQLConfig;
import me.vagdedes.spartan.Register;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.sql.Connection;

public class SQLLogs
{
    private static Connection a;
    private static boolean i;
    private static boolean j;
    
    public SQLLogs() {
        super();
    }
    
    private static boolean a(final boolean b, final boolean b2) {
        if (b) {
            b(true);
        }
        if (SQLLogs.a != null) {
            try {
                return !SQLLogs.a.isClosed();
            }
            catch (Exception ex) {
                if (b2) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL Connection:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + ex.getMessage());
                }
            }
        }
        return false;
    }
    
    public static boolean isConnected() {
        return a(true, true);
    }
    
    public static void disconnect(final boolean b) {
        if (b) {
            SQLLogs.j = false;
        }
        SQLLogs.i = true;
        if (a(false, false)) {
            try {
                SQLLogs.a.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public static void b(final boolean b) {
        final String version = Register.plugin.getDescription().getVersion();
        final String host = SQLConfig.getHost();
        final String user = SQLConfig.getUser();
        final String password = SQLConfig.getPassword();
        final String database = SQLConfig.getDatabase();
        final String port = SQLConfig.getPort();
        if (host.equalsIgnoreCase("")) {
            SQLLogs.i = false;
            SQLLogs.j = false;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Spartan " + version + "] Database Configuration Error: Host is blank");
        }
        else if (user.equalsIgnoreCase("")) {
            SQLLogs.i = false;
            SQLLogs.j = false;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Spartan " + version + "] Database Configuration Error: User is blank");
        }
        else if (password.equalsIgnoreCase("")) {
            SQLLogs.i = false;
            SQLLogs.j = false;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Spartan " + version + "] Database Configuration Error: Password is blank");
        }
        else if (database.equalsIgnoreCase("")) {
            SQLLogs.i = false;
            SQLLogs.j = false;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Spartan " + version + "] Database Configuration Error: Database is blank");
        }
        else if (!a(false, true)) {
            a(host, user, password, database, port, b);
        }
    }
    
    private static void a(final String p0, final String p1, final String p2, final String p3, final String p4, final boolean p5) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:267)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:426)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataHelper.isRawType(MetadataHelper.java:1581)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2361)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2322)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:577)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visit(MetadataHelper.java:2336)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSameType(MetadataHelper.java:1411)
        //     at com.strobel.assembler.metadata.TypeReference.equals(TypeReference.java:118)
        //     at com.strobel.core.Comparer.equals(Comparer.java:31)
        //     at com.strobel.assembler.ir.FrameValue.equals(FrameValue.java:72)
        //     at com.strobel.core.Comparer.equals(Comparer.java:31)
        //     at com.strobel.assembler.ir.Frame.merge(Frame.java:338)
        //     at com.strobel.assembler.ir.Frame.merge(Frame.java:273)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2206)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.base/java.lang.Thread.run(Thread.java:834)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void update(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       5
        //     4: return         
        //     5: iconst_0       
        //     6: invokestatic    me/vagdedes/spartan/features/b/SQLLogs.b:(Z)V
        //     9: getstatic       me/vagdedes/spartan/features/b/SQLLogs.a:Ljava/sql/Connection;
        //    12: invokeinterface java/sql/Connection.createStatement:()invokeinterface!!! ERROR
        //    17: astore_1       
        //    18: aload_1        
        //    19: aload_0        
        //    20: invokeinterface invokeinterface!!! ERROR
        //    25: pop            
        //    26: aload_1        
        //    27: invokeinterface invokeinterface!!! ERROR
        //    32: goto            180
        //    35: astore_1       
        //    36: aload_1        
        //    37: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //    40: astore_2       
        //    41: aload_2        
        //    42: ifnull          85
        //    45: aload_2        
        //    46: new             Ljava/lang/StringBuilder;
        //    49: dup            
        //    50: invokespecial   java/lang/StringBuilder.<init>:()V
        //    53: ldc             "Table '"
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: invokestatic    me/vagdedes/spartan/a/b/SQLConfig.getTable:()Ljava/lang/String;
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    64: ldc             "' already exists"
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    75: ifeq            85
        //    78: iconst_1       
        //    79: putstatic       me/vagdedes/spartan/features/b/SQLLogs.j:Z
        //    82: goto            180
        //    85: invokestatic    org/bukkit/Bukkit.getConsoleSender:()Lorg/bukkit/command/ConsoleCommandSender;
        //    88: new             Ljava/lang/StringBuilder;
        //    91: dup            
        //    92: invokespecial   java/lang/StringBuilder.<init>:()V
        //    95: getstatic       org/bukkit/ChatColor.RED:Lorg/bukkit/ChatColor;
        //    98: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   101: ldc             "MySQL Update:"
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   109: invokeinterface org/bukkit/command/ConsoleCommandSender.sendMessage:(Ljava/lang/String;)V
        //   114: invokestatic    org/bukkit/Bukkit.getConsoleSender:()Lorg/bukkit/command/ConsoleCommandSender;
        //   117: new             Ljava/lang/StringBuilder;
        //   120: dup            
        //   121: invokespecial   java/lang/StringBuilder.<init>:()V
        //   124: getstatic       org/bukkit/ChatColor.RED:Lorg/bukkit/ChatColor;
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   130: ldc             "Command: "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: aload_0        
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   142: invokeinterface org/bukkit/command/ConsoleCommandSender.sendMessage:(Ljava/lang/String;)V
        //   147: invokestatic    org/bukkit/Bukkit.getConsoleSender:()Lorg/bukkit/command/ConsoleCommandSender;
        //   150: new             Ljava/lang/StringBuilder;
        //   153: dup            
        //   154: invokespecial   java/lang/StringBuilder.<init>:()V
        //   157: getstatic       org/bukkit/ChatColor.RED:Lorg/bukkit/ChatColor;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   163: ldc             "Error: "
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload_2        
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   175: invokeinterface org/bukkit/command/ConsoleCommandSender.sendMessage:(Ljava/lang/String;)V
        //   180: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      32     35     180    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:267)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:426)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataHelper.isRawType(MetadataHelper.java:1581)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2369)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2322)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:577)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visit(MetadataHelper.java:2336)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSameType(MetadataHelper.java:1411)
        //     at com.strobel.assembler.metadata.TypeReference.equals(TypeReference.java:118)
        //     at com.strobel.core.Comparer.equals(Comparer.java:31)
        //     at com.strobel.assembler.ir.FrameValue.equals(FrameValue.java:72)
        //     at com.strobel.core.Comparer.equals(Comparer.java:31)
        //     at com.strobel.assembler.ir.Frame.merge(Frame.java:338)
        //     at com.strobel.assembler.ir.Frame.merge(Frame.java:273)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2206)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.base/java.lang.Thread.run(Thread.java:834)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static ResultSet query(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: iconst_0       
        //     7: invokestatic    me/vagdedes/spartan/features/b/SQLLogs.b:(Z)V
        //    10: aconst_null    
        //    11: astore_1       
        //    12: getstatic       me/vagdedes/spartan/features/b/SQLLogs.a:Ljava/sql/Connection;
        //    15: invokeinterface java/sql/Connection.createStatement:()invokeinterface!!! ERROR
        //    20: astore_2       
        //    21: aload_2        
        //    22: aload_0        
        //    23: invokeinterface invokeinterface!!! ERROR
        //    28: astore_1       
        //    29: goto            131
        //    32: astore_2       
        //    33: invokestatic    org/bukkit/Bukkit.getConsoleSender:()Lorg/bukkit/command/ConsoleCommandSender;
        //    36: new             Ljava/lang/StringBuilder;
        //    39: dup            
        //    40: invokespecial   java/lang/StringBuilder.<init>:()V
        //    43: getstatic       org/bukkit/ChatColor.RED:Lorg/bukkit/ChatColor;
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    49: ldc             "MySQL Query:"
        //    51: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    54: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    57: invokeinterface org/bukkit/command/ConsoleCommandSender.sendMessage:(Ljava/lang/String;)V
        //    62: invokestatic    org/bukkit/Bukkit.getConsoleSender:()Lorg/bukkit/command/ConsoleCommandSender;
        //    65: new             Ljava/lang/StringBuilder;
        //    68: dup            
        //    69: invokespecial   java/lang/StringBuilder.<init>:()V
        //    72: getstatic       org/bukkit/ChatColor.RED:Lorg/bukkit/ChatColor;
        //    75: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    78: ldc             "Command: "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload_0        
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    90: invokeinterface org/bukkit/command/ConsoleCommandSender.sendMessage:(Ljava/lang/String;)V
        //    95: invokestatic    org/bukkit/Bukkit.getConsoleSender:()Lorg/bukkit/command/ConsoleCommandSender;
        //    98: new             Ljava/lang/StringBuilder;
        //   101: dup            
        //   102: invokespecial   java/lang/StringBuilder.<init>:()V
        //   105: getstatic       org/bukkit/ChatColor.RED:Lorg/bukkit/ChatColor;
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   111: ldc             "Error: "
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: aload_2        
        //   117: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   126: invokeinterface org/bukkit/command/ConsoleCommandSender.sendMessage:(Ljava/lang/String;)V
        //   131: aload_1        
        //   132: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     29     32     131    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:267)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:426)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataHelper.isRawType(MetadataHelper.java:1581)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2369)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visitClassType(MetadataHelper.java:2322)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.accept(CoreMetadataFactory.java:577)
        //     at com.strobel.assembler.metadata.MetadataHelper$SameTypeVisitor.visit(MetadataHelper.java:2336)
        //     at com.strobel.assembler.metadata.MetadataHelper.isSameType(MetadataHelper.java:1411)
        //     at com.strobel.assembler.metadata.TypeReference.equals(TypeReference.java:118)
        //     at com.strobel.core.Comparer.equals(Comparer.java:31)
        //     at com.strobel.assembler.ir.FrameValue.equals(FrameValue.java:72)
        //     at com.strobel.core.Comparer.equals(Comparer.java:31)
        //     at com.strobel.assembler.ir.Frame.merge(Frame.java:338)
        //     at com.strobel.assembler.ir.Frame.merge(Frame.java:273)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2206)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.base/java.lang.Thread.run(Thread.java:834)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean tableExists(final String s) {
        if (SQLLogs.j) {
            return true;
        }
        try {
            final Connection a = SQLLogs.a;
            if (a == null) {
                return false;
            }
            final DatabaseMetaData metaData = a.getMetaData();
            return metaData != null && metaData.getTables(null, null, s, null).next();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static void a(final Player player, final String str, final Material material, final Enums.HackType hackType, final boolean i, final String str2) {
        if (str != null && SQLLogs.i) {
            if (!tableExists(SQLConfig.getTable())) {
                final String s;
                Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, () -> update("CREATE TABLE " + s + " (id INT(11) NOT NULL AUTO_INCREMENT, date TIMESTAMP, spartan_build VARCHAR(4), server_version VARCHAR(7), server_tps DOUBLE, online_players INT, type VARCHAR(32), info VARCHAR(512), player_uuid VARCHAR(36), player_x INT(11), player_y INT(11), player_z INT(11), hack_type VARCHAR(32), false_positive TINYINT(1), violation_level INT(3), cancel_violation INT(3), silent_check TINYINT(1), detection_information VARCHAR(384), mined_item VARCHAR(32), primary key (id));"));
            }
            else {
                SQLLogs.j = true;
                VersionUtils.a().toString().substring(1).replace("_", ".");
                Register.plugin.getDescription().getVersion().replace("Build ", "");
                final String str3 = (hackType != null) ? "violation" : ((material != null) ? "mining" : "other");
                final String s2 = (player == null) ? "null" : ("'" + player.getUniqueId() + "'");
                final Location location = (player == null) ? null : player.getLocation();
                final String s3 = (location == null) ? "null" : ("'" + location.getBlockX() + "'");
                final String s4 = (location == null) ? "null" : ("'" + location.getBlockY() + "'");
                final String s5 = (location == null) ? "null" : ("'" + location.getBlockZ() + "'");
                final String s6 = (hackType == null) ? "null" : ("'" + hackType.toString() + "'");
                final String s7 = (hackType == null) ? "null" : ("'" + (i ? 1 : 0) + "'");
                final String s8 = (hackType == null) ? "null" : ("'" + ((player == null) ? -1 : VL.a(SpartanBukkit.a(player.getUniqueId()), hackType)) + "'");
                final String s9 = (hackType == null) ? "null" : ("'" + Config.c(hackType) + "'");
                final String s10 = (hackType == null) ? "null" : ("'" + (Config.isSilent(hackType, (player == null) ? null : player.getWorld().getName()) ? 1 : 0) + "'");
                final String s11 = (hackType == null) ? "null" : ((str2 == null) ? "null" : ("'" + str2 + "'"));
                if (material != null) {
                    new StringBuilder().append("'").append(material.toString()).append("'").toString();
                }
                final String s;
                final String str4;
                final String str5;
                final String str6;
                final String str7;
                final String str8;
                final String str9;
                final String str10;
                final String str11;
                final String str12;
                final String str13;
                final String str14;
                final String str15;
                final String str16;
                Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, () -> update("INSERT INTO " + s + " (date, spartan_build, server_version, server_tps, online_players, type, info, player_uuid, player_x, player_y, player_z, hack_type, false_positive, violation_level, cancel_violation, silent_check, detection_information, mined_item) VALUES ('" + new Timestamp(System.currentTimeMillis()) + "', '" + str4 + "', '" + str5 + "', '" + TPS.get() + "', '" + NPC.a().length + "', '" + str3 + "', '" + str + "', " + str6 + ", " + str7 + ", " + str8 + ", " + str9 + ", " + str10 + ", " + str11 + ", " + str12 + ", " + str13 + ", " + str14 + ", " + str15 + ", " + str16 + ")"));
            }
        }
    }
    
    public static ConcurrentHashMap<String, String> a(final int n) {
        final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        try {
            int n2 = 0;
            final ResultSet query = query("SELECT * FROM " + SQLConfig.getTable() + " ORDER BY spartan_build DESC;");
            while (query.next()) {
                ++n2;
                final Timestamp timestamp = query.getTimestamp("date");
                SearchEngine.a("(" + MathUtils.a(0, 99) + ")[" + TimeUtils.d(timestamp) + " " + TimeUtils.a(timestamp) + "]", query.getString("info"), concurrentHashMap);
                if (n2 % 5000 == 0 && MapUtils.a(concurrentHashMap) >= n) {
                    SearchEngine.g();
                    return concurrentHashMap;
                }
            }
        }
        catch (Exception ex) {}
        return concurrentHashMap;
    }
    
    private static /* synthetic */ void a(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12, final String str13, final String str14, final String str15, final String str16) {
        update("INSERT INTO " + str + " (date, spartan_build, server_version, server_tps, online_players, type, info, player_uuid, player_x, player_y, player_z, hack_type, false_positive, violation_level, cancel_violation, silent_check, detection_information, mined_item) VALUES ('" + new Timestamp(System.currentTimeMillis()) + "', '" + str2 + "', '" + str3 + "', '" + TPS.get() + "', '" + NPC.a().length + "', '" + str4 + "', '" + str5 + "', " + str6 + ", " + str7 + ", " + str8 + ", " + str9 + ", " + str10 + ", " + str11 + ", " + str12 + ", " + str13 + ", " + str14 + ", " + str15 + ", " + str16 + ")");
    }
    
    private static /* synthetic */ void b(final String str) {
        update("CREATE TABLE " + str + " (id INT(11) NOT NULL AUTO_INCREMENT, date TIMESTAMP, spartan_build VARCHAR(4), server_version VARCHAR(7), server_tps DOUBLE, online_players INT, type VARCHAR(32), info VARCHAR(512), player_uuid VARCHAR(36), player_x INT(11), player_y INT(11), player_z INT(11), hack_type VARCHAR(32), false_positive TINYINT(1), violation_level INT(3), cancel_violation INT(3), silent_check TINYINT(1), detection_information VARCHAR(384), mined_item VARCHAR(32), primary key (id));");
    }
    
    static {
        SQLLogs.a = null;
        SQLLogs.i = true;
        SQLLogs.j = false;
    }
}
