package com.terraceshouses.terracehouse;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_FROM_ALBUM = 1;
    private final int REQ_CODE_SELECT_IMAGE = 100;
    private Uri uri;
    private Button imgBtn;
    private EditText tv1;
    private ImageView imgv1;
    private String img_path = new String();
    //private String serverURL = "https://terraceshouses.com/imgForScan";
    private String serverURL = "http://203.233.199.82:8889/imgForScan";
    private Bitmap image_bitmap_copy = null;
    private Bitmap image_bitmap = null;
    private String imageName = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgBtn = (Button) findViewById(R.id.imgBtn);
        tv1 = (EditText) findViewById(R.id.tv1);
        imgv1 = (ImageView) findViewById(R.id.imgv1);

        ButtonHandler bh = new ButtonHandler();
        imgBtn.setOnClickListener(bh);
    }

    class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick (View v){
            if (R.id.imgBtn == v.getId()){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            try{
                img_path = getImagePathToUri(data.getData());
                Bitmap bitImg = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imgv1.setImageBitmap(bitImg);
                doFileUpload(serverURL,img_path);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getImagePathToUri(Uri data){

        String imgPath = null;

        String[] proj = {MediaStore.Images.Media.DATA};

        Cursor cursor = this.getContentResolver().query(data, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            imgPath = cursor.getString(column_index);
        }

        if (imgPath != null ){
            int cut = imgPath.lastIndexOf('/');
            imageName = imgPath.substring(cut+1);
        }

        Log.e("img path", imgPath);
        Log.e("img name", imageName);

        cursor.close();

        return imgPath;
    }
    public void doFileUpload(String apiUrl, String absolutePath) {
        httpFileUpload(apiUrl, "", absolutePath);
    }

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";

    public String httpFileUpload(String urlString, String params, String fileName) {

        try{
            File sourceFile = new File(fileName);
            if (!sourceFile.isFile()) {
                Log.e("uploadFile", "Source File not exist :" + fileName);
            }
            else {

                FileInputStream mFileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestMethod("POST");
                con.setRequestProperty("Connection", "Keep-Alive");
                con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                DataOutputStream dos = new DataOutputStream(con.getOutputStream());



                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);


                int bytesAvailable = mFileInputStream.available();
                int maxBufferSize =  1024 * 1024;
                int bufferSize = Math.min(bytesAvailable, maxBufferSize);

                byte[] buffer = new byte[bufferSize];
                int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

                Log.d("Test", "image byte is " + bytesRead);

                // read image
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = mFileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
                }

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // close streams
                Log.e("Test", "File is written");
                mFileInputStream.close();
                dos.flush();

                if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                    String line;
                    String page = "";

                    while ((line = reader.readLine()) != null){
                        page += line;
                    }

                    //Toast.makeText(MainActivity.this, page, Toast.LENGTH_LONG).show();
                    tv1.setText(page);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
