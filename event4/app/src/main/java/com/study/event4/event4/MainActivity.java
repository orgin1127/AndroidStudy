package com.study.event4.event4;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;
    Button[] bts = new Button[3];
    ImageView iv1, iv2, iv3;
    int btid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.event4layout);
        ButtonHandler bh = new ButtonHandler();

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);

        for (int i = 0; i <= bts.length-1; i++) {
            btid = getResources().getIdentifier("bt"+i, "id","com.study.event4.event4");
            bts[i] = (Button) findViewById(btid);
            bts[i].setOnClickListener(bh);
        }
        iv1.setVisibility(View.INVISIBLE);
        iv2.setVisibility(View.INVISIBLE);
        iv3.setVisibility(View.INVISIBLE);
    }

    class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            for (int i = 0; i <= bts.length-1; i++) {
                if(view.getId() == bts[i].getId() && i+1 ==1) {
                    iv1.setVisibility(View.VISIBLE);
                }
                else if(view.getId() == bts[i].getId() && i+1 ==2) {
                    iv1.setVisibility(View.INVISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    iv3.setVisibility(View.INVISIBLE);
                }
                else if(view.getId() == bts[i].getId() && i+1 ==3) {
                    iv1.setVisibility(View.INVISIBLE);
                    iv2.setVisibility(View.INVISIBLE);
                    iv3.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
