package com.study.wordmemorize.mywordmemorize;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    URL url;
    HttpURLConnection con;

    Button regiBT, loginBT;
    public static String loginID;
    public static String loginName;
    public static JSONObject loginObject;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.mainlayout);
        getPreferences("loginID");
        if(!loginID.isEmpty()){
            Intent it = new Intent(this, AfterLoginActivity.class);

            startActivity(it);
        }
        backPressCloseHandler = new BackPressCloseHandler(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        regiBT = (Button) findViewById(R.id.btRegister);
        loginBT = (Button) findViewById(R.id.btLogin);

    }

    public void regi_Login_Phase (View v) {
        if (v.getId() == regiBT.getId()) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle(" [ 회 원 가 입 ] ");
            ad.setCancelable(false);
            final LinearLayout register_Layout = (LinearLayout) View.inflate(this, R.layout.register_layout, null);
            ad.setView(register_Layout);
            ad.setPositiveButton("가입", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText inputID = (EditText) register_Layout.findViewById(R.id.inputID);
                    EditText inputPassword = (EditText) register_Layout.findViewById(R.id.inputPassword);
                    EditText inputName = (EditText) register_Layout.findViewById(R.id.inputName);
                    EditText inputPassword2 = (EditText) register_Layout.findViewById(R.id.inputPassword2);

                    HashMap<String, String> userInfo = new HashMap<>();
                    userInfo.put("memberID", inputID.getText().toString());
                    userInfo.put("password", inputPassword.getText().toString());
                    userInfo.put("memberName", inputName.getText().toString());
                    userInfo.put("password2", inputPassword2.getText().toString());
                    String param = makeParams(userInfo);

                    try{
                        //서버의 IP주소, PORT번호, Context root, Request Mapping경로
                        url = new URL("http://203.233.199.96:8888/www/insertMember");
                    } catch (MalformedURLException e){
                        Toast.makeText(MainActivity.this,"잘못된 url입니다.",Toast.LENGTH_SHORT).show();
                    }
                    try{
                        con = (HttpURLConnection) url.openConnection();

                        if(con != null){

                            con.setConnectTimeout(10000);
                            con.setUseCaches(false);
                            con.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                            con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                            con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");

                            OutputStream os = con.getOutputStream();

                            os.write(param.getBytes("UTF-8"));
                            os.flush();
                            os.close();

                            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){

                                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                                String line;
                                String page = "";

                                while ((line = reader.readLine()) != null){
                                    page += line;
                                }

                                Toast.makeText(MainActivity.this, page, Toast.LENGTH_LONG).show();

                            }

                        }
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_LONG).show();
                    } finally {
                        if(con != null){
                            con.disconnect();
                        }
                    }
                }
            });
            ad.setNegativeButton("취소", null);
            ad.show();
        }

        else if(v.getId() == loginBT.getId()) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle(" [ L O G - I N ] ");
            ad.setCancelable(false);
            final LinearLayout login_Layout = (LinearLayout) View.inflate(this, R.layout.login_layout, null);
            ad.setView(login_Layout);
            ad.setPositiveButton("Log in", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText inputID = (EditText) login_Layout.findViewById(R.id.loginPhaseID);
                    EditText inputPassword = (EditText) login_Layout.findViewById(R.id.loginPhasePassword);
                    String params = "memberID="+inputID.getText()+"&password="+inputPassword.getText();
                    try{
                        //서버의 IP주소, PORT번호, Context root, Request Mapping경로
                        url = new URL("http://203.233.199.96:8888/www/login");
                    } catch (MalformedURLException e){
                        Toast.makeText(MainActivity.this,"잘못된 url입니다.",Toast.LENGTH_SHORT).show();
                    }
                    try {
                        con = (HttpURLConnection) url.openConnection();

                        if (con != null) {

                            con.setConnectTimeout(10000);    //연결제한시간. 0은 무한대기.
                            con.setUseCaches(false);        //캐쉬 사용여부
                            con.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                            con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                            con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                            OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream(), "UTF-8");

                            os.write(params);
                            os.flush();
                            os.close();

                            if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                                String page = reader.readLine();
                                if (page != null) {
                                    jsonParse(page);
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        Log.d("exc",e.toString());
                    }
                    finally {
                        if(con != null){
                            con.disconnect();
                        }
                    }
                }
            });
            ad.setNegativeButton("취소", null);
            ad.show();
        }

    }

    public String makeParams(HashMap<String,String> params){
        StringBuffer sbParam = new StringBuffer();
        String key = "";
        String value = "";
        boolean isAnd = false;

        for(Map.Entry<String,String> elem : params.entrySet()){
            key = elem.getKey();
            value = elem.getValue();

            if(isAnd){
                sbParam.append("&");
            }

            sbParam.append(key).append("=").append(value);

            if(!isAnd){
                if(params.size() >= 3){
                    isAnd = true;
                }
            }
        }
        Log.d("check", sbParam.toString());
        return sbParam.toString();
    }

    //For Login JsonParse
    public void jsonParse(String page) {
        JSONObject jItem = null;

        try {
            jItem = new JSONObject(page);
            loginObject = jItem;
            loginID = jItem.getString("memberID");
            loginName = jItem.getString("memberName");
            savePreferences("loginID", jItem.getString("memberID"));
            savePreferences("loginName", jItem.getString("memberName"));
            Toast.makeText(this, "환영합니다. "+loginID+" 님",Toast.LENGTH_LONG).show();
            Intent it = new Intent(this, AfterLoginActivity.class);

            startActivity(it);


        }
        catch (Exception e) {
            Log.d("exc", e.toString());
        }
    }

    // 값 불러오기
    private void getPreferences(String key){
        SharedPreferences pref = getSharedPreferences("loginID", MODE_PRIVATE);
        loginID = pref.getString(key, "can't not find login ID");
    }

    // 값 저장하기
    private void savePreferences(String key, String value){
        SharedPreferences pref = getSharedPreferences("loginID", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    private void removePreferences(String key){
        SharedPreferences pref = getSharedPreferences("loginID", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    private void removeAllPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
