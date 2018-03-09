package com.thread1.sesoc.thread1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mMainValue = 0;
    int mBackvalue = 0;
    TextView mMainText;
    TextView mBackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
            }
        }
    }
}
