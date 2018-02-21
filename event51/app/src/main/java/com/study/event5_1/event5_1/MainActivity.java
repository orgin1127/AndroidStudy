package com.study.event5_1.event5_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.layout1);
        et = (EditText) findViewById(R.id.et1);
        tv = (TextView) findViewById(R.id.tv1);

        TouchHandler th = new TouchHandler();
        KeyHandler kh = new KeyHandler();

        tv.setOnTouchListener(th);
        et.setOnKeyListener(kh);

    }

    //터치 이벤트 처리
    class TouchHandler implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            String msg = "[터치 이벤트]\n";
            msg +="x=" + motionEvent.getX();
            msg +="y=" + motionEvent.getY();
            msg += "action=" + motionEvent.getAction();
            msg += motionEvent.toString();

            tv.setText(msg);
            //return false; //최초 이벤트 이후는 무시
            /*if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            }*/
            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                Toast.makeText(MainActivity.this, "Action UP", Toast.LENGTH_SHORT).show();
            }
            return true; //최초 이벤트 이후 계속 액션이 변함
        }
    }

    class KeyHandler implements View.OnKeyListener {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if(keyEvent.getAction() == KeyEvent.ACTION_UP) {
                Toast.makeText(MainActivity.this, "Key UP", Toast.LENGTH_SHORT).show();
            }
            String msg = "";
            msg += keyEvent.toString();
            tv.setText(msg);
            return false;
        }
    }
}
