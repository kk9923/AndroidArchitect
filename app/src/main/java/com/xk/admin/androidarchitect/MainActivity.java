package com.xk.admin.androidarchitect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.xk.admin.androidarchitect.imageLoader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv =findViewById(R.id.iv);
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage("http://img3.imgtn.bdimg.com/it/u=625175783,2044150402&fm=27&gp=0.jpg",iv);
    }
}
