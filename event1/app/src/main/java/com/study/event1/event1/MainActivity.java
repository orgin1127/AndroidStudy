package com.study.event1.event1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    Button b3;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.eventlayout_1);

        //변수를 선언해서 버튼의 주소값을 받아놓는다.
        //원하는 타입으로 casting 해서 받는다.
        b1 = (Button) findViewById(R.id.bt1);
        b2 = (Button) findViewById(R.id.bt2);
        b3 = (Button) findViewById(R.id.bt3);
        et1 = (EditText) findViewById(R.id.et1);


        //View.OnClickListener 에는 많은게 생략되어 있음
        //View 안에 OnClickListener라는 interface가 구현되어 있고 그걸 class 이름 없이 곧바로 interface만을 구현한것
        //그리고 그 이름없는 class를 객체 생성하는 문장
        //생략 안할시에는
        //class Test implements View.OnClickListener {
        //
        //}
        //가 되어야 한다.

        b1.setOnClickListener(new View.OnClickListener() {
            //해당 listener가 앱이 실행되는 동안 감시할 event를 정하기 위해 overriding으로 정의한다.
            @Override
            public void onClick(View view) {
                //클릭 이벤트가 발생되면 실행될 부분
                //내부 클레스에서 바깥쪽을 호출할때는 해당class명.this
                Toast.makeText(MainActivity.this, R.string.bt1_st, Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"두번째 버튼이요", Toast.LENGTH_LONG).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String s = et1.getText().toString();
                Toast.makeText(MainActivity.this, et1.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
