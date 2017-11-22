package com.xk.admin.androidarchitect.imageLoader.CacheImpl;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.xk.admin.androidarchitect.imageLoader.interfaces.ImageCache;

/**
 * Created by admin on 2017/11/22.
 */

public class MemoryCache implements ImageCache {
    private LruCache<String,Bitmap> bitmapLruCache ;

    public MemoryCache() {
        initImageCache();
    }

    private void initImageCache() {
        // 计算可使用的最大内存
        final  int macMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        // 取四分之一可用内存作为缓存
        final  int cacheSize = macMemory/4 ;
        bitmapLruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()* value.getHeight()/1024;
            }
        };
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        bitmapLruCache.put(key,bitmap);
    }

    @Override
    public Bitmap get(String key) {
        return bitmapLruCache.get(key);
    }
}
