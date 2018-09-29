package com.onzhou.utils;

import android.content.Context;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @anchor: Andy
 * @date: 2018-09-20
 * @description:
 */
public class AssetsUtils {

    public static boolean extractAssets(Context context, String targetDir, String fileName) {
        if (context != null) {
            InputStream inputStream = null;
            FileOutputStream fos = null;
            try {
                inputStream = context.getAssets().open(fileName);
                File targetFile = new File(targetDir, fileName);
                fos = new FileOutputStream(targetFile);
                int length = 0;
                byte[] buffer = new byte[8 * 1024];
                while ((length = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }
                fos.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(fos);
                close(inputStream);
            }
        }
        return false;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
