package com.xk.admin.androidarchitect.imageLoader.interfaces;

import android.graphics.Bitmap;

/**
 * Created by admin on 2017/11/22.
 */

public interface ImageCache {
    void put(String key, Bitmap bitmap);
    Bitmap get(String key);
}
