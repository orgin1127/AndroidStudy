package com.thread3.sesoc.thread3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Thread3 extends AppCompatActivity {

    int mMainValue = 0;
    int mBackvalue = 0;
    TextView mMainText;
    TextView mBackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread3);

        mMainText = (TextView)findViewById(R.id.mainvalue);
        mBackText = (TextView)findViewById(R.id.backvalue);

        BackThread thread = new BackThread();
        thread.setDaemon(true);
        thread.start();
    }

    public void mOnClick(View v){
        mMainValue++;
        mMainText.setText("MainValue : " + mMainValue);
        mBackText.setText("BackValue : " + mBackvalue);
    }

    class BackThread extends Thread {
        @Override
        public void run() {
            while (true){
                mBackvalue++;
                Log.i("mBackValue",mBackvalue+"");
                mHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
            }
        }
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                mBackText.setText("BackValue : "+ mBackvalue);
            }
        }
    };
}
