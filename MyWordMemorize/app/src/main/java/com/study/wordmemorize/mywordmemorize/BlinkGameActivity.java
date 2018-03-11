package com.study.wordmemorize.mywordmemorize;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class BlinkGameActivity extends AppCompatActivity {
    private static ArrayList<JSONObject> wordList;
    private String wordLevel;
    private URL url;
    private HttpURLConnection con;
    private String key;
    private int combo, score;
    private int count = 0;
    private int maxCombo, highScore ;
    private int pageNum = 0;
    private int life = 5;
    private int gameCount = 0;
    private int gameNum = 0;
    TextView wordView, meanView;
    TextView infoView;
    EditText wordInputEditText;
    ImageView life1, life2, life3;
    ImageView life4, life5;
    boolean flag = true;
    boolean blinkFlag = true;
    private JSONObject blinkGameInfo;
    private Vibrator mVibrator;


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "실행됨");
        if(pageNum>0) {
            pageNum++;
        }
        startBlinkGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink_game);

        Intent it = getIntent();
        Bundle extras = it.getExtras();
        wordView = (TextView) findViewById(R.id.blinkGameWordViewTV);
        infoView = (TextView) findViewById(R.id.blinkGameInfoTV);
        meanView = (TextView) findViewById(R.id.blinkGameMeanViewTV);
        wordInputEditText = (EditText) findViewById(R.id.inputBlinkGameWordET);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        life1 = (ImageView) findViewById(R.id.blinkLife1);
        life2 = (ImageView) findViewById(R.id.blinkLife2);
        life3 = (ImageView) findViewById(R.id.blinkLife3);
        life4 = (ImageView) findViewById(R.id.blinkLife4);
        life5 = (ImageView) findViewById(R.id.blinkLife5);
        String param = "memberID="+MainActivity.loginID;
        try{
            url = new URL("http://203.233.199.96:8888/www/insertBlinkGame");
        } catch (MalformedURLException e){
            Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
            Log.d("error", e.toString());
        }
        try {
            con = (HttpURLConnection) url.openConnection();

            if (con != null) {
                con.setConnectTimeout(10000);    //연결제한시간. 0은 무한대기.
                con.setUseCaches(false);        //캐쉬 사용여부
                con.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                OutputStream os = con.getOutputStream();
                os.write(param.getBytes("UTF-8"));
                os.flush();
                os.close();
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                    String line;
                    String page = "";

                    while ((line = reader.readLine()) != null) {
                        page += line;
                    }
                    Log.d("asdasdasd", page);
                    blinkGameInfo = jsonParseForStartBG(page);
                    highScore = Integer.parseInt(blinkGameInfo.getString("highScore"));
                    maxCombo = Integer.parseInt(blinkGameInfo.getString("maxCombo"));
                    gameNum = blinkGameInfo.getInt("blinkGameNumber");
                    gameCount = blinkGameInfo.getInt("playCount");
                    gameCount++;
                }
            }
        }
        catch (Exception e) {
            Log.d("error",e.toString());
        }
        finally {
            if(con != null){
                con.disconnect();
            }
        }


        for (String s:extras.keySet()) {
            Object ob = extras.get(s);
            Log.d("ob", ob.toString()+", "+s);
            if(s.equals("level")){
                key = s;
            }
        }
        if (key.equals("level")){
            if(it.getStringExtra("level").equals("customWord")){
                wordLevel = "wordLevel="+it.getStringExtra("level")+"&memberID="+MainActivity.loginID;
            }
            else {
                wordLevel = "wordLevel="+it.getStringExtra("level");
            }
            try{
                url = new URL("http://203.233.199.96:8888/www/viewWord");
            } catch (MalformedURLException e){
                Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
                Log.d("error", e.toString());
            }
        }
        try{
            con = (HttpURLConnection) url.openConnection();

            if(con != null){
                con.setConnectTimeout(10000);	//연결제한시간. 0은 무한대기.
                con.setUseCaches(false);		//캐쉬 사용여부
                con.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                OutputStream os = con.getOutputStream();


                if (key.equals("level")) {
                    os.write(wordLevel.getBytes("UTF-8"));
                }
                os.flush();
                os.close();
                if(con.getResponseCode() == HttpURLConnection.HTTP_OK){

                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                    String line;
                    String page = "";

                    while ((line = reader.readLine()) != null){
                        page += line;
                    }
                    Log.d("asdasdasd",page);
                    if(page.equals("FAIL")) {
                        finish();
                    }
                    wordList = jsonParse(page);
                    Log.d("size", wordList.size()+"");
                    Log.d("list check", wordList.get(0).getString("word"));
                    Log.d("list check", wordList.get(0).getString("meaning"));
                    Collections.shuffle(wordList);

                }
            }

            /*wordInputEditText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if(i == KeyEvent.KEYCODE_ENTER&&keyEvent.getAction() == KeyEvent.ACTION_UP){
                        Log.d("check log", "enter log");
                        checkWord();
                    }
                    return false;
                }
            });*/

        }
        catch (Exception e) {
            Toast.makeText(this, "찾으시는 단어가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
            Log.d("error",e.toString());
        }
        finally {
            if(con != null){
                con.disconnect();
            }
        }
    }


    //jsonParse for getting wordList
    public JSONObject jsonParseForStartBG(String page){
        JSONObject item = null;
        try {
            item = new JSONObject(page);
            String str1 = item.getString("memberID");
            Log.d("plz no break", str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
    //jsonParse for getting wordList
    public ArrayList<JSONObject> jsonParse(String page){
        JSONArray jarray = null;
        JSONObject item = null;
        wordList = new ArrayList<>();
        try {
            jarray = new JSONArray(page);

            for (int i = 0; i < jarray.length(); i++) {
                item = jarray.getJSONObject(i);
                wordList.add(item);
            }
            item = jarray.getJSONObject(0);
            String str1 = item.getString("word");
            Log.d("plz no break", str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public void startBlinkGame () {
        MAsyncer ma = new MAsyncer();
        switch (life){
            case 4:
                life1.setVisibility(View.GONE);
                break;
            case 3:
                life2.setVisibility(View.GONE);
                break;
            case 2:
                life3.setVisibility(View.GONE);
                break;
            case 1:
                life4.setVisibility(View.GONE);
                break;
        }
        if(life > 0) {
            try {

                if (highScore <= score) {
                    highScore = score;
                }
                if (maxCombo <= combo) {
                    maxCombo = combo;
                }
                infoView.setText("HighScore: "+highScore+" Score: "+score
                        +"\n"+" Max Combo: "+maxCombo+" Combo: "+combo);
                wordView.setText(wordList.get(pageNum).getString("word"));
                meanView.setText(wordList.get(pageNum).getString("meaning"));
                //count++;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(life <= 0){
            //checkWord();
            ma.isCancell = true;
        }


        ma.execute();
    }

    public void checkWord() {
        try{
            Log.d("ccc", Thread.currentThread().getState()+"");
            blinkFlag = true;
            count = 0;
            String yomi = wordList.get(pageNum).getString("yomigana");
            String input = wordInputEditText.getText().toString();
            Log.d("answer check", yomi+", "+input);
            if(life >= 1) {

                if (input.equals(yomi)){
                    score = score + 5 + (combo*2);
                    combo++;
                    Log.d("result", "정답");
                }
                else if(!input.equals(yomi)){
                    life--;
                    combo = 0;
                    Log.d("result", "오답");
                    incollectWordUpdate();
                    mVibrator.vibrate(500);
                    Toast.makeText(BlinkGameActivity.this,"오답입니다.",Toast.LENGTH_SHORT);
                }
                else if(input == null) {
                    life--;
                    combo = 0;
                    Log.d("result", "오답");
                    incollectWordUpdate();
                    mVibrator.vibrate(500);
                    Toast.makeText(BlinkGameActivity.this,"오답입니다.",Toast.LENGTH_SHORT);
                }
                pageNum++;
                Log.d("values", "page: "+pageNum+"life: "+life);
                Thread.currentThread().interrupt();
                startBlinkGame();
            }
            if(life == 0){
                life5.setVisibility(View.GONE);
                Log.d("end game", "end");
                afterBlinkGame();

                finish();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void afterBlinkGame() {
        String param = "memberID="+MainActivity.loginID+"&highScore="+highScore+"&maxCombo="+maxCombo+"&playCount="+gameCount+"&blinkGameNumber="+gameNum;
        try{
            url = new URL("http://203.233.199.96:8888/www/updateBlinkGame");
        } catch (MalformedURLException e){
            Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
            Log.d("error", e.toString());
        }
        try {
            con = (HttpURLConnection) url.openConnection();

            if (con != null) {
                con.setConnectTimeout(10000);    //연결제한시간. 0은 무한대기.
                con.setUseCaches(false);        //캐쉬 사용여부
                con.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                OutputStream os = con.getOutputStream();
                os.write(param.getBytes("UTF-8"));
                os.flush();
                os.close();
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    String line;
                    String page = "";

                    while ((line = reader.readLine()) != null) {
                        page += line;
                    }
                    Log.d("result", page);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(con != null){
                con.disconnect();
            }
        }
    }
    public void incollectWordUpdate(){

        try{
            url = new URL("http://203.233.199.96:8888/www/insertAndUpdateIncollectBlinkGameWord");
        } catch (MalformedURLException e){
            Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
            Log.d("error", e.toString());
        }
        try {
            String param = "memberID="+MainActivity.loginID+"&incollectBGameWord="+wordView.getText().toString()
                    +"&incorrectMeaning="+meanView.getText().toString()+"&incorrectYomigana="+wordList.get(pageNum).getString("yomigana").toString();

            con = (HttpURLConnection) url.openConnection();

            if (con != null) {
                con.setConnectTimeout(10000);    //연결제한시간. 0은 무한대기.
                con.setUseCaches(false);        //캐쉬 사용여부
                con.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                OutputStream os = con.getOutputStream();
                os.write(param.getBytes("UTF-8"));
                os.flush();
                os.close();
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    String line;
                    String page = "";

                    while ((line = reader.readLine()) != null) {
                        page += line;
                    }
                    Log.d("result", page);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(con != null){
                con.disconnect();
            }
        }
    }
    class MAsyncer extends AsyncTask<Void, Integer, Void> {
        private boolean isCancell = false;
        @Override
        protected void onPreExecute() {
            publishProgress(0);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <=4 && !isCancell; i++) {
                if(count <5) {
                    if (blinkFlag){
                        blinkFlag = false;
                        count++;
                        Log.d("i and count", i+", "+count);
                        publishProgress(0);
                    }
                    else if(!blinkFlag){
                        if(count == 5) {
                            count = 0;
                            flag = false;
                        }
                        blinkFlag=true;
                        count++;
                        Log.d("i and count", i+", "+count);
                        publishProgress(4);
                    }
                    Log.d("bFlag", blinkFlag+"");
                }
                if (count > 5) {
                    publishProgress(0);
                }
                try{
                    Thread.sleep(2800);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... p) {
            if(p[0] == 0){
                wordView.setVisibility(View.VISIBLE);
            }
            else if (p[0] == 4){
                wordView.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("작업완료", true+"");
            isCancell = true;
            //cancel(true);
            checkWord();
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(BlinkGameActivity.this, "취소됨", Toast.LENGTH_SHORT).show();
            isCancell = true;
        }

    }
}
