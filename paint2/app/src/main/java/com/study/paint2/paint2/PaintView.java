package com.study.paint2.paint2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PaintView extends View implements View.OnTouchListener {
    Context context;
    float x;
    float y;
    ArrayList<Point> pointList = new ArrayList<>();

    public PaintView(Context context) {
        super(context);
        //자기 자신이 event 발생자 = event listener
        this.setOnTouchListener(this);
        this.context = context;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawColor(Color.parseColor("#cce6ff"));
        if(pointList != null && !pointList.isEmpty()) {
            /*for (Point p:pointList) {
                canvas.drawLine(p.getX(),p.getY(),p.getX(),p.getY(),paint);
                canvas.drawCircle(p.getX(), p.getY(), 5, paint);
            }*/

            for (int i =1; i < pointList.size(); i++) {
                /*if(i== 0) {
                    canvas.drawLine(pointList.get(i).getX(),pointList.get(i).getY(),
                            pointList.get(i).getX(),pointList.get(i).getY(),paint);
                }*/
                canvas.drawLine(pointList.get(i-1).getX(),pointList.get(i-1).getY(),
                        pointList.get(i).getX(),pointList.get(i).getY(),paint);
                /*else {
                    canvas.drawLine(pointList.get(i-1).getX(),pointList.get(i-1).getY(),
                            pointList.get(i).getX(),pointList.get(i).getY(),paint);
                }*/
            }
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //뷰 객체를 새로 그리게 함
        invalidate();
        x = motionEvent.getX();
        y = motionEvent.getY();
        Point p = new Point(x, y);
        pointList.add(p);
        //Toast.makeText(context,"touched",Toast.LENGTH_SHORT).show();
        return true;
    }

    class Point {
        float x;
        float y;
        public Point(Float x, Float y){
            this.x = x;
            this.y = y;
        }

        public float getY() {
            return y;
        }
        public void setY(float y) {
            this.y = y;
        }
        public float getX() {
            return x;
        }
        public void setX(float x) {
            this.x = x;
        }
    }
}
