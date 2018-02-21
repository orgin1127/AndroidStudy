package com.study.paint1.paint1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class PaintView extends View{

    Bitmap img;
    Typeface font;

    //생성자
    public PaintView(Context context) {
        super(context);

        img = BitmapFactory.decodeResource(
                getResources(), R.drawable.dealwithit3
        );

        font = Typeface.createFromAsset(
                context.getAssets(), "NanumBarunpenR.ttf"
        );
    }

    //새로 그려지는 시점에 자동호출
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawColor(Color.parseColor("#33cccc"));
       /* paint.setColor(Color.RED);

        canvas.drawCircle(200,200,100, paint);

        canvas.drawCircle(300, 300, 150, paint);

        paint.setColor(Color.DKGRAY);
        canvas.drawCircle(600,600,200,paint);


        canvas.drawRect(300,200,400,300,paint);

        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100,350,500,800,paint);*/

        paint.setColor(Color.GREEN);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(0,200,0,200,10,90,true,paint);

        paint.setColor(Color.MAGENTA);
        paint.setTextSize(70);
        paint.setTypeface(font);
        canvas.drawText("draw paint",650,650,paint);


        canvas.drawBitmap(img, 200,500,paint);

    }
}
