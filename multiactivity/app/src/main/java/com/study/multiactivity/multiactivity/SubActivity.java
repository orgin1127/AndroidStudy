package com.study.multiactivity.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;


public class SubActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        Intent it = getIntent();
        String s = it.getStringExtra("param");

        if (s != null) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public void subButtonClick(View v) {
        Intent it = new Intent();
        it.putExtra("return Data", "서브에서 Put된 값");
        setResult(RESULT_OK, it);

        finish();
    }
}
