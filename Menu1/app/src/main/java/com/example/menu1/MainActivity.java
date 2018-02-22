package com.example.menu1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    int color = Color.BLUE;
    int size = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.text);
        text.setTextSize(size);
        text.setTextColor(color);
        text.setText("최초 문자열");
    }

    //메뉴 버튼 누를 때 메뉴 보이기. 최초로 메뉴 버튼 누를 때 호출됨.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //메뉴 버튼 누를 때마다 호출됨.
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //첫번째 체크박스 : 큰 글자일때 체크
        if (size == 30) {
            menu.findItem(R.id.check1).setChecked(true);
        }
        else {
            menu.findItem(R.id.check1).setChecked(false);
        }

        //현재 글자색에 따라 라디오 버튼 체크
        if (color == Color.RED) menu.findItem(R.id.option1).setChecked(true);
        else if (color == Color.BLUE) menu.findItem(R.id.option2).setChecked(true);

        return true;
    }

    //메뉴 선택시 처리
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1 : text.setText("첫번째 메뉴 선택"); break;
            case R.id.menu2 : text.setText("두번째 메뉴 선택"); break;
            case R.id.menu3 : text.setText("세번째 메뉴 선택"); break;
            case R.id.menu4 : text.setText("네번째 메뉴 선택"); break;

            case R.id.check1 :
                if (item.isChecked()) {
                    size = 10;
                    text.setTextSize(size);
                    text.setText("작은 글자 선택");
                }
                else {
                    size = 30;
                    text.setTextSize(size);
                    text.setText("큰 글자 선택");
                }
                break;
            case R.id.option1 : text.setText("빨강글자 선택");
                color = Color.RED;
                text.setTextColor(color); break;
            case R.id.option2 : text.setText("파랑글자 선택");
                color = Color.BLUE;
                text.setTextColor(color); break;

        }
        return false;
    }
}
