package com.example.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Drawer extends View {
    List<CustomPainter> list = new ArrayList<>();
    CustomPainter customPainter;

    public Drawer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        setColor(Color.BLUE);
    }

//    private void setCustomPainter() {
//        setColor(Color.BLUE);
//        customPainter = new CustomPainter(Color.BLUE, 5);
//        customPainter.getPaint().setStyle(Paint.Style.STROKE);
//        list.add(customPainter);
//
//        //map.put(Color.BLUE, customPainter);
//    }

    public void setColor(int color) {
        customPainter = new CustomPainter(color, 5);
        customPainter.getPaint().setStyle(Paint.Style.STROKE);
        list.add(customPainter);

//        if (map.containsKey(color)){
//            customPainter = map.get(color);
//        } else {
//            initColor(color);
//        }
    }

//    public void setStrokeWidth(int stroke){
//        list.get(list.size()-1).getPaint().setStrokeWidth(stroke);
//    }

    public void setStyle(boolean isStrokePressed){
        CustomPainter strokedPaint = new CustomPainter(list.get(list.size() - 1).getPaint().getColor(), 5);
        if(!isStrokePressed){
            strokedPaint.getPaint().setStyle(Paint.Style.STROKE);
        }else{
            strokedPaint.getPaint().setStyle(Paint.Style.FILL);
        }
        list.add(strokedPaint);
        customPainter = list.get(list.size() - 1);
    }

    private void initColor(int color) {
        customPainter = new CustomPainter(color, 5);
        //map.put(color,customPainter);
    }

    public void clearBoard(){
        for (CustomPainter customPainter : list) {
            customPainter.clearPath();
        }
        invalidate();

        }

    @Override
    protected void onDraw(Canvas canvas) {
        for (CustomPainter customPainter : list){
            if(customPainter.getPaint().getColor() == Color.WHITE){
                customPainter.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            }
            else{
                customPainter.getPaint().setXfermode(null);
            }
            customPainter.draw(canvas);

        }
    }

    public Bitmap getDrawing(){
        setDrawingCacheEnabled(true);
        Log.i("TAG", "getDrawing: " +  getDrawingCache());
        Bitmap bitmap = Bitmap.createBitmap(getDrawingCache());
        setDrawingCacheEnabled(false);
        Log.i("TAG", "byteCount: " +  bitmap.getByteCount());
        return bitmap;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                customPainter.moveTo(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                customPainter.lineTo(event.getX(), event.getY());
                invalidate();
                return true;

        }
        return false;
    }


}
