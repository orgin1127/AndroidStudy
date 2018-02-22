package com.study.paint3.paint3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class PaintView extends View implements View.OnTouchListener {
    Context context;

    ArrayList<Vertex> vertex = new ArrayList<>();
    ArrayList<Vertex> eraserVertex = new ArrayList<>();

    int color = Color.BLACK;
    int eraseColor;

    int backgroundColor = Color.WHITE;

    String drawType = "draw";

    Paint paint = new Paint();

    //java에서 생성할때 쓰이는 생성자
    public PaintView(Context context) {
        super(context);
        this.context = context;
        this.setOnTouchListener(this);
    }

    //xml에서 생성할때 쓰이는 생성자
    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("touch check", "touched");
        //뷰 객체 새로그리기
        invalidate();
        float ex = 0;
        float ey = 0;

        if (drawType.equals("eraser")) {
             ex = motionEvent.getX();
             ey = motionEvent.getY();
        }

        float x = motionEvent.getX();
        float y = motionEvent.getY();

        boolean drawable = true;
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            drawable = false;
        }
        if (drawType.equals("eraser")) {
            vertex.add(new Vertex(ex, ey, color, drawable, drawType));
        }
        else {
            vertex.add(new Vertex(x, y, color, drawable, drawType));
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       paint.setStrokeWidth(15);
       canvas.drawColor(backgroundColor);
           //그리기
               if (vertex != null && !vertex.isEmpty()) {
                    for (int i = 1; i < vertex.size(); i++) {
                        if (vertex.get(i).isDrawable()) {
                            if(vertex.get(i).getDrawType().equals("draw")) {
                                paint.setColor(vertex.get(i).getColor());

                            }
                            else {
                                paint.setColor(backgroundColor);
                            }
                            canvas.drawLine(
                                    vertex.get(i-1).getX(),
                                    vertex.get(i-1).getY(),
                                    vertex.get(i).getX(),
                                    vertex.get(i).getY(),paint);
                        }
                    }
                }
           //지우기
          /* case "eraser" :
                Log.d("erase check", "erasing");
                if (eraserVertex != null) {
                    for (int i = 1; i < vertex.size(); i++) {
                        if (vertex.get(i).isDrawable()) {
                            paint.setColor(vertex.get(i).getColor());
                            canvas.drawLine(
                                    vertex.get(i - 1).getX(),
                                    vertex.get(i - 1).getY(),
                                    vertex.get(i).getX(),
                                    vertex.get(i).getY(), paint);
                        }
                    }
                    for (int i=1; i < eraserVertex.size(); i++) {
                        if (eraserVertex.get(i).isDrawable()) {
                            paint.setColor(eraseColor);
                            canvas.drawLine(
                                    eraserVertex.get(i-1).getX(),
                                    eraserVertex.get(i-1).getY(),
                                    eraserVertex.get(i).getX(),
                                    eraserVertex.get(i).getY(),
                                    paint);
                        }
                    }
                }
                break;*/


    }

}
