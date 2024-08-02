package fr.body.ectasy.util;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class OtherFile {
   public String fileName;
   public byte[] data;

   public OtherFile(String var1, byte[] var2) {
      this.fileName = var1;
      this.data = var2;
   }

   public OtherFile(String var1, InputStream var2) throws Exception {
      this.fileName = var1;
      this.data = IOUtils.toByteArray(var2);
   }
}
