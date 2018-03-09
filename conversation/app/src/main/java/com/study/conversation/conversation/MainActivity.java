package com.study.conversation.conversation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] strs = {"aaaa","bbbb","cccc","dddd"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.dialoglayout);
    }

    public void viewDialog(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                Dialog d1 = new Dialog(this);
                TextView t = new TextView(this);
                t.setText("대화상자 내용");
                d1.setContentView(t);
                d1.setTitle("대화상자 제목");
                d1.show();
                break;
            case R.id.bt2:
                AlertDialog.Builder d2 = new AlertDialog.Builder(this);
                d2.setTitle("대화상자 제목");
                d2.setMessage("대화상자 내용");
                d2.setIcon(R.mipmap.meaticon_1);
                d2.setCancelable(false);

                d2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("check", "확인 clicked "+i );
                        //i = -1
                    }
                });

                d2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("check", "취소 clicked " +i);
                        // i = -2
                    }
                });
                d2.setNeutralButton("무시", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("check", "무시 clicked "+ i);
                        //i = -3
                    }
                });


                d2.show();
                break;
            case R.id.bt3:
                Toast.makeText(this,"dddd",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder d3 = new AlertDialog.Builder(this);
                d3.setTitle("다음 목록에서 선택하세요.");
                d3.setItems(strs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Log.d("check i", strs[i]+"");
                                break;
                            case 1:
                                Log.d("check i", strs[i]+"");
                                break;
                            case 2:
                                Log.d("check i", strs[i]+"");
                                break;
                            case 3:
                                Log.d("check i", strs[i]+"");
                                break;
                        }
                    }
                });
                d3.setNegativeButton("취소", null);
                d3.show();
                break;

            case R.id.bt4:
                AlertDialog.Builder d4 = new AlertDialog.Builder(this);
                d4.setTitle("연습연습");
                d4.setCancelable(false);
                final LinearLayout layout = (LinearLayout) View.inflate(this, R.layout.dialoglayout2, null);
                d4.setView(layout);
                d4.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText et = (EditText) layout.findViewById(R.id.edit);
                        CheckBox cb = (CheckBox) layout.findViewById(R.id.check);
                        String msg = et.getText().toString();
                        boolean result = cb.isChecked();
                        Toast.makeText(MainActivity.this,msg + ", " + result, Toast.LENGTH_SHORT).show();
                    }
                });
                d4.show();
                break;
        }
    }
}
