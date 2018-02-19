package com.study.view2.view2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    EditText et;
    Button bt;
    Button bt2, bt3, bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bt = new Button(this);
        bt2 = new Button(this);
        bt3 = new Button(this);
        bt4 = new Button(this);
        layout = new LinearLayout(this);
        et = new EditText(this);

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        layout.setBackgroundColor(Color.parseColor("#66ff99"));
        bt.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        bt2.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        bt3.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        bt4.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        bt.setText("수평으로");
        bt2.setText("수직으로");
        bt3.setText("빨강 20");
        bt4.setText("파랑 30");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //레이아웃 속성을 수평방향으로 변경
                layout.setOrientation(LinearLayout.HORIZONTAL);
            }
        });
        //버튼2 - 레아이웃 배치를 수직방향으로 변경
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setOrientation(LinearLayout.VERTICAL);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setTextColor(Color.parseColor("#ff0000"));
                et.setTextSize(20);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setTextColor(Color.parseColor("#000099"));
                et.setTextSize(30);
            }
        });
        //버튼3 - EditText의 글자 색을 빨강, 크기를 20으로
        //버튼4 - EditText의 글자 색을 파랑, 크기를 30으로

        et.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        layout.addView(bt);
        layout.addView(bt2);
        layout.addView(bt3);
        layout.addView(bt4);

        layout.addView(et);
        setContentView(layout);
    }
}
