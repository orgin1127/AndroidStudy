package com.study.wordmemorize.mywordmemorize;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AfterLoginActivity extends Activity {

    private BackPressCloseHandler backPressCloseHandler;
    private RadioButton n1, n2, n3;
    private RadioButton n4, n5, customWord;
    private RadioButton searchTypeWord, searchTypeYomi, searchTypeMean;
    private RadioButton searchTypeWordAndMean;
    private EditText searchWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);


        backPressCloseHandler = new BackPressCloseHandler(this);

    }

    public void buttonHandler (View v) {
        switch (v.getId()) {
            case R.id.viewWordBT:
                AlertDialog.Builder ab = new AlertDialog.Builder(this);
                ab.setTitle("단어 레벨 선택");
                ab.setCancelable(false);
                final LinearLayout wordViewSelect_Layout = (LinearLayout) View.inflate(this, R.layout.word_level_select_layout, null);
                ab.setView(wordViewSelect_Layout);
                ab.setNegativeButton("취소",null);
                ab.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("positive onClick check", "worked");
                        n1 = (RadioButton) wordViewSelect_Layout.findViewById(R.id.wordLevelN1);
                        n2 = (RadioButton) wordViewSelect_Layout.findViewById(R.id.wordLevelN2);
                        n3 = (RadioButton) wordViewSelect_Layout.findViewById(R.id.wordLevelN3);
                        n4 = (RadioButton) wordViewSelect_Layout.findViewById(R.id.wordLevelN4);
                        n5 = (RadioButton) wordViewSelect_Layout.findViewById(R.id.wordLevelN5);
                        customWord = (RadioButton) wordViewSelect_Layout.findViewById(R.id.customWord);
                        if(n1.isChecked()){
                            Log.d("n1","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N1");
                            startActivity(it2);
                        }
                        else if(n2.isChecked()){
                            Log.d("n2","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N2");
                            startActivity(it2);
                        }
                        else if(n3.isChecked()){
                            Log.d("n3","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N3");
                            startActivity(it2);
                        }
                        else if(n4.isChecked()){
                            Log.d("n4","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N4");
                            startActivity(it2);
                        }
                        else if(n5.isChecked()){
                            Log.d("n5","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N5");
                            startActivity(it2);
                        }
                        else if(customWord.isChecked()){
                            Log.d("customWord","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActivityCustom.class);
                            it2.putExtra("level", "customWord");
                            startActivity(it2);
                        }
                    }
                });
                ab.show();
                break;

            case R.id.searchWordBT:
                Log.d("단어검색","switch 작동");
                AlertDialog.Builder ab2 = new AlertDialog.Builder(this);
                ab2.setTitle("검색 방법 선택");
                ab2.setCancelable(false);
                final LinearLayout wordSearchTypeSelect_Layout = (LinearLayout) View.inflate(this, R.layout.select_word_search_type_layout, null);
                ab2.setView(wordSearchTypeSelect_Layout);

                ab2.setNegativeButton("취소",null);
                ab2.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        searchTypeWord = (RadioButton) wordSearchTypeSelect_Layout.findViewById(R.id.searchTypeWord);
                        searchTypeYomi = (RadioButton) wordSearchTypeSelect_Layout.findViewById(R.id.searchTypeYomi);
                        searchTypeMean = (RadioButton) wordSearchTypeSelect_Layout.findViewById(R.id.searchTypeMeaning);
                        searchTypeWordAndMean = (RadioButton) wordSearchTypeSelect_Layout.findViewById(R.id.searchTypeWordAndMean);
                        searchWord = (EditText) wordSearchTypeSelect_Layout.findViewById(R.id.searchWordET);
                        if(searchTypeWord.isChecked()) {
                            Log.d("check searchType","Word");
                            Intent it = new Intent(AfterLoginActivity.this, );
                            it.putExtra("searchType", "word");
                            it.putExtra("searchWord", searchWord.getText());
                            startActivity(it);
                        }
                        else if(searchTypeYomi.isChecked()) {
                            Log.d("check searchType","Yomi");
                            Intent it = new Intent(AfterLoginActivity.this, );
                            it.putExtra("searchType", "yomigana");
                            it.putExtra("searchWord", searchWord.getText());
                            startActivity(it);
                        }
                        else if(searchTypeMean.isChecked()) {
                            Log.d("check searchType","Mean");
                            Intent it = new Intent(AfterLoginActivity.this, );
                            it.putExtra("searchType", "meaning");
                            it.putExtra("searchWord", searchWord.getText());
                            startActivity(it);
                        }
                        else if(searchTypeWordAndMean.isChecked()) {
                            Log.d("check searchType","Word&Mean");
                            Intent it = new Intent(AfterLoginActivity.this, );
                            it.putExtra("searchType", "wordAndYomi");
                            it.putExtra("searchWord", searchWord.getText());
                            startActivity(it);
                        }
                    }
                });
                ab2.show();
                break;
            case R.id.blinkGameBT:
                Log.d("Blink game Button", "ACTIVATED");
                AlertDialog.Builder ab3 = new AlertDialog.Builder(this);
                ab3.setTitle("단어 레벨 선택");
                ab3.setCancelable(false);
                final LinearLayout wordViewSelect_LayoutForBlinkGame = (LinearLayout) View.inflate(this, R.layout.word_level_select_layoutForBlinkGame, null);
                ab3.setView(wordViewSelect_LayoutForBlinkGame);
                ab3.setNegativeButton("취소",null);
                ab3.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        n1 = (RadioButton) wordViewSelect_LayoutForBlinkGame.findViewById(R.id.wordLevelN1);
                        n2 = (RadioButton) wordViewSelect_LayoutForBlinkGame.findViewById(R.id.wordLevelN2);
                        n3 = (RadioButton) wordViewSelect_LayoutForBlinkGame.findViewById(R.id.wordLevelN3);
                        n4 = (RadioButton) wordViewSelect_LayoutForBlinkGame.findViewById(R.id.wordLevelN4);
                        n5 = (RadioButton) wordViewSelect_LayoutForBlinkGame.findViewById(R.id.wordLevelN5);
                        customWord = (RadioButton) wordViewSelect_LayoutForBlinkGame.findViewById(R.id.customWord);
                        if(n1.isChecked()){
                            Log.d("n1","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N1");
                            startActivity(it2);
                        }
                        else if(n2.isChecked()){
                            Log.d("n2","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N2");
                            startActivity(it2);
                        }
                        else if(n3.isChecked()){
                            Log.d("n3","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N3");
                            startActivity(it2);
                        }
                        else if(n4.isChecked()){
                            Log.d("n4","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N4");
                            startActivity(it2);
                        }
                        else if(n5.isChecked()){
                            Log.d("n5","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActiviy.class);
                            it2.putExtra("level", "N5");
                            startActivity(it2);
                        }
                        else if(customWord.isChecked()){
                            Log.d("customWord","checked");
                            Intent it2 = new Intent(AfterLoginActivity.this,WordViewActivityCustom.class);
                            it2.putExtra("level", "customWord");
                            startActivity(it2);
                        }
                    }
                });
                ab3.show();
                break;
        }
    }

    //뒤로가기 종료
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed(this);
    }


}
