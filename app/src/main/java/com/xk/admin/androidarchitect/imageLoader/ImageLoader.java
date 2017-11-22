package com.xk.admin.androidarchitect.imageLoader;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.xk.admin.androidarchitect.imageLoader.CacheImpl.DoubleCache;
import com.xk.admin.androidarchitect.imageLoader.interfaces.ImageCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private ImageCache imageCache  = new DoubleCache();
    private static  ImageLoader imageLoader ;
ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ImageLoader() {

    }
    public static  ImageLoader getInstance(){
        if (imageLoader ==null){
            synchronized (ImageLoader.class){
                imageLoader = new ImageLoader();
            }
        }
        return imageLoader;
    }
    public void displayImage(String url, ImageView imageView){
        Bitmap bitmap = imageCache.get(url);
        if (bitmap==null){
            submitLoadRequest(url,imageView);
        }else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    private void submitLoadRequest (final String imageUrl, final ImageView imageView){
        imageView.setTag(imageUrl);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imageUrl);
                if (bitmap==null){
                    return;
                }
                if (imageView.getTag() == imageUrl){
                    imageView.setImageBitmap(bitmap);
                }
                imageCache.put(imageUrl,bitmap);
            }
        });
    }
    /**
     * 下载图片
     * @param imageUrl
     * @return
     */
    private Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
