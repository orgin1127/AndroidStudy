package com.study.wordmemorize.mywordmemorize;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackPressCloseHandler extends Activity {

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    URL url;
    HttpURLConnection con;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed(Activity context) {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide(context);
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            if(context.getClass() == AfterLoginActivity.class){
                MainActivity ma = new MainActivity();
                ma.loginID = "";
                ma.loginName = "";

            }
            activity.finish();
            toast.cancel();
        }
    }


    private void showGuide(Activity context) {
        if (context.getClass() == AfterLoginActivity.class) {
            toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 터치하면 로그아웃 합니다.", Toast.LENGTH_SHORT);

        }
        else if(context.getClass() == MainActivity.class){
            toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 터치하시면 종료됩니다.",
                    Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
