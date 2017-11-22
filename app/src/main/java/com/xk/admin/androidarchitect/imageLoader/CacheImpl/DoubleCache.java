package com.xk.admin.androidarchitect.imageLoader.CacheImpl;

import android.graphics.Bitmap;

import com.xk.admin.androidarchitect.imageLoader.interfaces.ImageCache;


public class DoubleCache implements ImageCache {
    private MemoryCache memoryCache ;
    private DiskCache diskCache ;

    public DoubleCache() {
        memoryCache = new MemoryCache();
        diskCache =new DiskCache();
    }

    /**
     * 内存和SD中都缓存图片
     * @param key
     * @param bitmap
     */
    @Override
    public void put(String key, Bitmap bitmap) {
        diskCache.put(key,bitmap);
        memoryCache.put(key,bitmap);
    }

    /**
     * 先从内存缓存中获取图片，如果不存在，则从SD卡中获取图片
     * @param key
     * @return
     */
    @Override
    public Bitmap get(String key) {
        Bitmap bitmap = memoryCache.get(key);
        if (bitmap ==null){
             bitmap = memoryCache.get(key);
        }
        return bitmap;
    }
}
