package phonebook.example.com.phonebook;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textShow;
    EditText edit_num , edit_name, edit_phone;

    URL url;
    HttpURLConnection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Main Thread에서 네트워크 접속 가능하도록 설정
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        textShow = findViewById(R.id.textShow);
        edit_num = findViewById(R.id.edit_num);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);

    }

    public void btnOnClick(View view){
        switch (view.getId()){
            case R.id.btnSend:
                String num = edit_num.getText().toString();
                String name = edit_name.getText().toString();
                String phone = edit_phone.getText().toString();

                //사용자가 입력한 데이터 (서버로 보낼 데이터)를 Map에 저장
                HashMap<String, String> params = new HashMap<>();
                params.put("num", num);
                params.put("name", name);
                params.put("phone", phone);

                //요청시 보낼 쿼리스트림으로 변환
                String param = makeParams(params);

                try{
                    //서버의 IP주소, PORT번호, Context root, Request Mapping경로
                    url = new URL("http://203.233.199.96:8888/www/insertMember");
                } catch (MalformedURLException e){
                    Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
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

                            Toast.makeText(this, page, Toast.LENGTH_SHORT).show();

                        }

                    }
                }catch (Exception e){
                    Toast.makeText(this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
                break;
            case R.id.btnParse:

                try{
                    url = new URL("http://203.233.199.96:8888/www/selectMember");
                } catch (MalformedURLException e){
                    Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
                }

                try{
                    con = (HttpURLConnection) url.openConnection();

                    if(con != null){
                        con.setConnectTimeout(10000);	//연결제한시간. 0은 무한대기.
                        con.setUseCaches(false);		//캐쉬 사용여부
                        con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                        con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");

                        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){

                            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                            String line;
                            String page = "";

                            while ((line = reader.readLine()) != null){
                                page += line;
                            }
                            jsonParse(page);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
                break;
        }
    }

    //서버로 보낼 데이터를 쿼리 스트링으로 변환 ("?이름=값&이름2=값2" 형식)
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
                if(params.size() >= 2){
                    isAnd = true;
                }
            }
        }

        return sbParam.toString();
    }

    public void jsonParse(String page){
        JSONArray jarray = null;
        JSONObject item = null;

        try {
            jarray = new JSONArray(page);


            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < jarray.length(); i++) {
                item = jarray.getJSONObject(i);
                sb2.append("번호:");
                sb2.append(item.getInt("num"));
                sb2.append(" 이름:");
                sb2.append(item.getString("name"));
                sb2.append(" 전화:");
                sb2.append(item.getString("phone"));
                sb2.append("\n");
            }
            textShow.setText(sb2.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

















}
