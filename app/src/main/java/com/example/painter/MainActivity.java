package com.example.painter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.drawer.DrawerHolder;

public class MainActivity extends AppCompatActivity {

    private DrawerHolder drawerHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerHolder = findViewById(R.id.drawHolder);
        drawerHolder.register(bitmap -> {

        });
    }
}