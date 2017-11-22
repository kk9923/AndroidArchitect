package com.xk.admin.androidarchitect.imageLoader.utilts;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by admin on 2017/11/22.
 */

public class CloseUtils {
    public CloseUtils() {
    }
    public static void closeStream(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
