package com.study.multiactivity.multiactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.layout1);
    }

    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                //Intent 객체에게 찾아갈 곳을 객체 생상하며 알려준다
                Intent it = new Intent(this, SubActivity.class);
                //특정 activity 명시적 호출
                startActivity(it);
                break;
            case R.id.bt2:
                Intent it2 = new Intent(this, SubActivity.class);
                it2.putExtra("param", "메인에서 put 한 문자열");
                //startActivity는 값을 보낼순 있지만 값을 받을 순 없다
                startActivityForResult(it2,1);
                break;
            //묵시적 intent
            case R.id.bt3:
                Uri uri = Uri.parse("http://www.google.com/");
                Intent it3 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it3);
                break;
            case R.id.bt4:
                Uri uri2 = Uri.parse("mailto:godfo21@naver.com");
                Intent it4 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(it4);
                break;
        }
    }

    //startActivityForResult로 값을 보내면 이 method로 받는다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, "다녀오기 성공", Toast.LENGTH_SHORT).show();
                String s = data.getStringExtra("return Data");
                Log.d("result", s);
            }
        }
    }
}
