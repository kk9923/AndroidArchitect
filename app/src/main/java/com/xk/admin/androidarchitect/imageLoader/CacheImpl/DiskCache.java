package com.xk.admin.androidarchitect.imageLoader.CacheImpl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xk.admin.androidarchitect.imageLoader.interfaces.ImageCache;
import com.xk.admin.androidarchitect.imageLoader.utilts.CloseUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by admin on 2017/11/22.
 */

public class DiskCache implements ImageCache {

    private static String cacheDir = "sdcard/cache";
    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null ;
        try {
            fileOutputStream = new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            CloseUtils.closeStream(fileOutputStream);
        }
    }

    @Override
    public Bitmap get(String key) {
        return BitmapFactory.decodeFile(cacheDir+key);
    }
}
