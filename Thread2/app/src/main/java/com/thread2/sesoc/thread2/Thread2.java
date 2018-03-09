package com.thread2.sesoc.thread2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Thread2 extends AppCompatActivity {

    int mMainValue = 0;
    int mBackvalue = 0;
    TextView mMainText;
    TextView mBackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread2);

        mMainText = (TextView)findViewById(R.id.mainvalue);
        mBackText = (TextView)findViewById(R.id.backvalue);

        BackThread runnable = new BackThread();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }

    public void mOnClick(View v){
        mMainValue++;
        mMainText.setText("MainValue : " + mMainValue);
        mBackText.setText("BackValue : " + mBackvalue);
    }

    class BackThread implements Runnable {
        @Override
        public void run() {
            while (true){
                mBackvalue++;
                Log.i("mBackValue",mBackvalue+"");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
            }
        }
    }
}
