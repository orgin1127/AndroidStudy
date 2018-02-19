package com.study.event3.event3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText input1, input2, resultCalc;
    Button calc, clear;
    int btid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.caleventlayout);

        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        resultCalc = (EditText) findViewById(R.id.resultCalc);
        calc = (Button) findViewById(R.id.calc);
        clear = (Button) findViewById(R.id.clear);



    }

    public void test(View view) {
        /*Log.d("tag라는데요","이건 msg구요");
        Log.i("tag랍니다","msg고");
        Log.e("e tag", "e msg");*/

        /*
        이벤트 소스 구분 방법
        1. 주소 비교
        if (view == calc) {

        }
        //2. ID 비교
        if (view.getId() == R.id.calc) {

        }
        //3. 객체 속성 비교
        Button b = (Button) view;
        if (b.getText().toString().equals("계산")) {

        }
        */

        //계산 버튼 클릭하면 입력란 1,2의 문자열 읽어 숫자로 변환해 계산
        if (view == calc) {
            try{
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());
                int result = num1 + num2;
                resultCalc.setText(Integer.toString(result));
            }
            catch (Exception e) {

                Toast.makeText(this,"숫자만 입력하여 주세요",Toast.LENGTH_SHORT).show();
            }
        }
        //계산 결과 입력란 3에 보여주기
        //지우기 버튼 클릭하면 입력란 1,2,3의 내용을 "0" 또는 "" 로 초기화
        if (view == clear) {
            input1.setText("");
            input2.setText("");
            resultCalc.setText("");
        }
    }
}
