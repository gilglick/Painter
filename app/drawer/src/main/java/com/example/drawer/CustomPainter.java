package com.example.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class CustomPainter {

    private final Paint paint;
    private final Path path;

    public CustomPainter(int color, float width){
        paint = new Paint();
        path = new Path();
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void moveTo(float x, float y){
        path.moveTo(x, y);
    }

    public void lineTo(float x, float y){
        path.lineTo(x, y);
    }

    public void draw(Canvas canvas){
        canvas.drawPath(path, paint);
    }

    public Paint getPaint() {
        return paint;
    }

    public void clearPath(){
        path.rewind();
    }

}
