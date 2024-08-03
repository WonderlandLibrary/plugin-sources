package fr.body.ectasy.util;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class JarUtil {
   public String inputPath;
   public String outputPath;
   public List<ClassNode> classes = new ArrayList<>();
   public List<ClassNode> newClasses = new ArrayList<>();
   public List<OtherFile> files = new ArrayList<>();

   public void read() throws Exception {
      File v1 = new File(this.inputPath);
      JarFile v2 = new JarFile(v1);
      ZipInputStream v3 = new ZipInputStream(Files.newInputStream(v1.toPath()));

      ZipEntry v5;
      while ((v5 = v3.getNextEntry()) != null) {
         if (v5.getName().endsWith(".class")) {
            try {
               ClassReader v6 = new ClassReader(v3);
               ClassNode v7 = new ClassNode();
               v6.accept(v7, 0);
               this.classes.add(v7);
            } catch (Exception var9) {
               this.files.add(new OtherFile(v5.getName(), v2.getInputStream(v5)));
            }
         } else {
            this.files.add(new OtherFile(v5.getName(), v2.getInputStream(v5)));
         }
      }

      v3.close();
   }

   public void write() throws Exception {
      File v1 = new File(this.outputPath);
      JarOutputStream v2 = new JarOutputStream(Files.newOutputStream(v1.toPath()));

       for(ClassNode v5 : this.classes) {
         ClassWriter v6 = new ClassWriter(1);
         v5.accept(v6);
         v2.putNextEntry(new JarEntry(v5.name + ".class"));
         v2.write(v6.toByteArray());
      }

      for(ClassNode var11 : this.newClasses) {
         ClassWriter var13 = new ClassWriter(1);
         var11.accept(var13);
         v2.putNextEntry(new JarEntry(var11.name + ".class"));
         v2.write(var13.toByteArray());
      }

       for(OtherFile var12 : this.files) {
          v2.putNextEntry(new JarEntry(var12.fileName));
          v2.write(var12.data);
       }

       v2.close();
   }

   public JarUtil(String string1, String string2) {
      this.inputPath = string1;
      this.outputPath = string2;
   }

   public void addClass(ClassNode classNode1) {
      this.classes.add(classNode1);
   }
}
