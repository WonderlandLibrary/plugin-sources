package fr.body.ectasy.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
   public static void flushFile(File file) throws Exception {
      FileOutputStream fos = new FileOutputStream(file);
      fos.flush();
      fos.close();
   }
}
