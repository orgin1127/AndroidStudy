package com.study.texteditor.texteditor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText textContent, fileName;
    Button btSave, bt2, bt3;
    FileOutputStream fos;
    FileInputStream fis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textContent = (EditText) findViewById(R.id.textContent);
        fileName = (EditText) findViewById(R.id.fileName);

    }

    public void buttonClick(View v) {
        //저장 버튼 눌렀을 때
        if (v.getId() == R.id.btSave) {
            //사용자가 입력한 문서 내용과 파일명 읽기
            String content = textContent.getText().toString();
            String name = fileName.getText().toString();
            //하나라도 빈칸이면 리턴
            if (content.isEmpty() || name.isEmpty()) {
                Log.d("null check", "null check");
                return;
            }

            //출력 스트림 생성
            try {
                fos = openFileOutput(name+".txt", Context.MODE_PRIVATE);
                //파일에 쓰기
                fos.write(content.getBytes());
                Log.d("file write", "success");
            }
            catch (Exception e) {
                Log.d("file write", "fail");
            }

            //스트림 닫기
            finally {
                try{
                    if (fos != null) fos.close();
                }
                catch (Exception e){

                }
            }
        }

        //출력 버튼 눌렀을때
        if (v.getId() == R.id.btRead) {
            String name = fileName.getText().toString();
            if ( name.isEmpty()) {
                return;
            }
            try{
                fis = openFileInput(name + ".txt");
                byte[] buf = new byte[fis.available()];
                fis.read(buf);
                String str = new String(buf);
                textContent.setText(str);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try{

                    if (fis != null) fis.close();
                }
                catch (Exception e) {

                }
            }
        }

        //파일 목록 보기
        if(v.getId() == R.id.bt3) {
            final String names[] = fileList();
            //{"a.txt", "b.txt", "c.txt"}

            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("다음 목록에서 선택하여 주세요");

            ab.setItems(names, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int i) {
                    for(int y =0; y < names.length; y++) {
                        if(y == i) {
                            String filename1 = names[i].toString();
                            try {
                                //Toast.makeText(MainActivity.this, filename1, Toast.LENGTH_SHORT).show();
                                fis = openFileInput(filename1);
                                byte[] buf = new byte[fis.available()];
                                fis.read(buf);
                                String str = new String(buf);
                                fileName.setText(filename1);
                                textContent.setText(str);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            ab.setNegativeButton("취소",null);
            ab.show();
        }
    }
}
