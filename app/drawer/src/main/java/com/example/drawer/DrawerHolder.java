package com.example.drawer;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DrawerHolder extends LinearLayout {
    private ImageButton styleButton;
    private ImageButton saveButton;
    private ImageButton colorButton;
    private Button clearButton;
    private ImageButton buttonEraser;
    private boolean isFilledPressed;
    private ImageSavedCallback imageSavedCallback;
    private Drawer drawer;

    public DrawerHolder(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.drawer_layout, this);
        styleButton = findViewById(R.id.styleButton);
        colorButton = findViewById(R.id.colorButton);
        saveButton = findViewById(R.id.saveButton);
        clearButton = findViewById(R.id.clearButton);
        buttonEraser = findViewById(R.id.buttonEraser);
        drawer = findViewById(R.id.my_drawer);
        setButtons();
        onClick();
    }

    public void setButtons(){
        colorButton.setBackgroundColor(Color.BLUE);
        styleButton.setBackgroundColor(Color.LTGRAY);
        saveButton.setBackgroundColor(Color.LTGRAY);
        buttonEraser.setBackgroundColor(Color.LTGRAY);
    }

    public void register(ImageSavedCallback callback){
        this.imageSavedCallback = callback;
    }

     public void onClick(){
         colorButton.setOnClickListener(v -> {
            int currentBackgroundColor = Color.BLUE;
            ColorPickerDialogBuilder
                    .with(getContext())
                    .setTitle("Choose brush color")
                    .initialColor(currentBackgroundColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {
                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                            drawer.setColor(selectedColor);
                            colorButton.setBackgroundColor(selectedColor);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .build()
                    .show();

        });

         styleButton.setOnClickListener(v -> {
            isFilledPressed = !isFilledPressed;
            drawer.setStyle(isFilledPressed);
            if(isFilledPressed){
                styleButton.setBackgroundColor(Color.GRAY);
            }
            else{
                styleButton.setBackgroundColor(Color.LTGRAY);

            }
         });

         saveButton.setOnClickListener(v -> {
             //Drawable d = new BitmapDrawable(getResources(), drawer.getDrawing());
             if(imageSavedCallback != null){
                 imageSavedCallback.onImageSaved(drawer.getDrawing());
             }
             //imageView.setImageDrawable(d);
             Toast.makeText(getContext(), "Your Draw Saved", Toast.LENGTH_SHORT).show();
         });

         buttonEraser.setOnClickListener(v -> {
             drawer.setColor(Color.WHITE);
         });



         clearButton.setOnClickListener(v -> {
             drawer.clearBoard();

         });
     }

     public interface ImageSavedCallback{
        void onImageSaved(Bitmap bitmap);
     }

}
