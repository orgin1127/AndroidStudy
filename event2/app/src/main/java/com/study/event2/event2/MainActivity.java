package com.study.event2.event2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.eventlayout_2);
        b1 = (Button) findViewById(R.id.bt1);
        b2 = (Button) findViewById(R.id.bt2);
        b3 = (Button) findViewById(R.id.bt3);
        et1 = (EditText) findViewById(R.id.et1);

        ButtonHandler bh = new ButtonHandler();
        b1.setOnClickListener(bh);
        b2.setOnClickListener(bh);
        b3.setOnClickListener(bh);
    }

    class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            String s = "";
            //
            if(view == b1) {
                s = "初めてのボタン";
            }
            else if (view == b2) {
                s = "ボタンがおされました";
            }
            else if (view == b3) {
                s = "yay!";
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

}
