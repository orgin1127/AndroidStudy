package com.study.paint3.paint3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    PaintView pv;
    RadioButton black, red, blue;
    Button deleteAll;
    Button eraser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pv = findViewById(R.id.paintView);

        RadioButtonColorHandler rgh = new RadioButtonColorHandler();
        ButtonHandler bh = new ButtonHandler();

        red = (RadioButton) findViewById(R.id.rbRed);
        blue = (RadioButton) findViewById(R.id.rbBlue);
        black = (RadioButton) findViewById(R.id.rbBlack);

        deleteAll = (Button) findViewById(R.id.deleteAll);


        deleteAll.setOnClickListener(bh);


        red.setOnCheckedChangeListener(rgh);
        blue.setOnCheckedChangeListener(rgh);
        black.setOnCheckedChangeListener(rgh);
    }

    class RadioButtonColorHandler implements RadioButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b) {
                switch (compoundButton.getId()) {
                    case R.id.rbRed:
                        pv.color = Color.RED;
                        pv.drawType = "draw";
                        Log.d("check","red");
                        break;
                    case R.id.rbBlue:
                        pv.color = Color.BLUE;
                        pv.drawType = "draw";
                        Log.d("check","blue");
                        break;
                    case R.id.rbBlack:
                        pv.color = Color.BLACK;
                        pv.drawType = "draw";
                        Log.d("check","black");
                        break;
                }
            }
        }
    }

    class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.deleteAll:
                    Log.d("check", "deleteAll");
                    pv.vertex.clear();
                    pv.eraserVertex.clear();
                    pv.invalidate();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (pv.backgroundColor == Color.RED) {
            menu.findItem(R.id.bRed).setChecked(true);
        }
        else if (pv.backgroundColor == Color.BLUE) {
            menu.findItem(R.id.bBlue).setChecked(true);
        }
        else if (pv.backgroundColor == Color.BLACK) {
            menu.findItem(R.id.bBlack).setChecked(true);
        }
        else if(pv.backgroundColor == Color.WHITE) {
            menu.findItem(R.id.bWhite).setChecked(true);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eraser:
                pv.eraseColor = pv.backgroundColor;
                pv.drawType = "eraser";
                break;
            case R.id.bRed:
                pv.backgroundColor = Color.RED;
                pv.eraseColor = pv.backgroundColor;
                pv.invalidate();
                break;
            case R.id.bBlack:
                pv.backgroundColor = Color.BLACK;
                pv.eraseColor = pv.backgroundColor;
                pv.invalidate();
                break;
            case R.id.bBlue:
                pv.backgroundColor = Color.BLUE;
                pv.eraseColor = pv.backgroundColor;
                pv.invalidate();
                break;
            case R.id.bWhite:
                pv.backgroundColor = Color.WHITE;
                pv.eraseColor = pv.backgroundColor;
                pv.invalidate();
                break;
        }

        return false;
    }
}
