package com.study.paint3.paint3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PaintView extends View implements View.OnTouchListener {
    Context context;
    ArrayList<Vertex> vertex = new ArrayList<>();
    int color = Color.BLACK;

    //java에서 생성할때 쓰이는 생성자
    public PaintView(Context context) {
        super(context);
        this.context = context;
        this.setOnTouchListener(this);
    }

    //xml에서 생성할때 쓰이는 생성자
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("touch check", "touched");
        //뷰 객체 새로그리기
        invalidate();

        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean drawable = true;
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            drawable = false;
        }
        vertex.add(new Vertex(x, y, color, drawable));
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawColor(Color.LTGRAY);

        if (vertex != null && !vertex.isEmpty()) {
            for (int i = 1; i < vertex.size(); i++) {
                if (vertex.get(i).isDrawable()) {

                    paint.setColor(vertex.get(i).getColor());
                    canvas.drawLine(
                            vertex.get(i-1).getX(),
                            vertex.get(i-1).getY(),
                            vertex.get(i).getX(),
                            vertex.get(i).getY(),paint);
                }
            }
        }
    }


}
