package dev.ectasy.api.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {

    public static void overwrite(File target) throws Exception {
        FileOutputStream out = new FileOutputStream(target);
        out.flush();
        out.close();
    }

}
