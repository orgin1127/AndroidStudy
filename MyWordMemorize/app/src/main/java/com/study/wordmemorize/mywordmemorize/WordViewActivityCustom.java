package com.study.wordmemorize.mywordmemorize;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

public class WordViewActivityCustom extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String wordLevel;
    private URL url;
    private HttpURLConnection con;
    private static ArrayList<JSONObject> wordList;
    private static ViewPager mViewPager;
    private static int pageNum;
    private String key, searchString, word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_view_custom);


        Intent it = getIntent();
        Bundle extras = it.getExtras();

        for (String s:extras.keySet()) {
            Object ob = extras.get(s);
            Log.d("ob", ob.toString()+", "+s);
            if(s.equals("level")){
                key = s;
            }
            else if(s.equals("searchType")){
                key = "searchType="+ob.toString();
            }
            else if(s.equals("searchWord")){
                word = ob.toString();
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
                Log.d("search Check", wordLevel +", " + searchString);

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
                }
            }
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        @Override
        public boolean getUserVisibleHint() {
            return super.getUserVisibleHint();
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            if(isVisibleToUser){
                pageNum = getArguments().getInt(ARG_SECTION_NUMBER)-1;
                Log.d("visible", "yay");
            }
            else {
                Log.d("invisible", "eeee");
            }
            super.setUserVisibleHint(isVisibleToUser);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static WordViewActivityCustom.PlaceholderFragment newInstance(int sectionNumber) {
            WordViewActivityCustom.PlaceholderFragment fragment = new WordViewActivityCustom.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            Log.d("sectionNum", sectionNumber+"");
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_word_view_activity_custom, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            TextView yomi = (TextView) rootView.findViewById(R.id.viewWordYomiTV);
            TextView mean = (TextView) rootView.findViewById(R.id.viewWordMeanTV);
            // pageNum = getArguments().getInt(ARG_SECTION_NUMBER)-1;

            Log.d("idk",""+(getArguments().getInt(ARG_SECTION_NUMBER)));
            Log.d("pageNumber","pageNum: "+pageNum+", getArg: "+getArguments().getInt(ARG_SECTION_NUMBER)+", getuser:");
            try{
                textView.setText(wordList.get(getArguments().getInt(ARG_SECTION_NUMBER)).getString("word").toString());
                yomi.setText(wordList.get(getArguments().getInt(ARG_SECTION_NUMBER)).getString("yomigana").toString());
                mean.setText(wordList.get(getArguments().getInt(ARG_SECTION_NUMBER)).getString("meaning").toString());

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Log.d("position",position+"");
            return WordViewActivityCustom.PlaceholderFragment.newInstance(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            if(wordList == null) {
                return 0;
            }
            return wordList.size();
        }
    }

    public void viewWordButtonHandler(View v) {
        if(v.getId() == R.id.viewWordMoreInfoBT) {
            try {
                String str = wordList.get(pageNum+1).getString("linkAddress").split("href=")[1];
                String rex = "\"";
                String str2 = "";
                str = str.replaceAll(rex, str2);
                TextView s = (TextView) findViewById(R.id.section_label);
                //Toast.makeText(this, wordList.get(pageNum).getString("word")+", "+mSectionsPagerAdapter.getItemId(0), Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://jpdic.naver.com/"+str);
                Intent it3 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it3);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(v.getId() == R.id.viewWordDeleteBT) {
            Log.d("DELETE CUSTOM WORD", "ACTIVATED");
            try{
                StringBuilder sb = new StringBuilder();

                sb.append("word="+wordList.get(pageNum+1).getString("word"));
                sb.append("&addedMemberID="+MainActivity.loginID);
                String deleteWord = sb.toString();

                Log.d("check delete word", deleteWord);
                try{
                    //서버의 IP주소, PORT번호, Context root, Request Mapping경로
                    url = new URL("http://203.233.199.96:8888/www/deleteCustomWord");
                } catch (MalformedURLException e){
                    Toast.makeText(this,"잘못된 URL입니다.", Toast.LENGTH_SHORT).show();
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
                        os.write(deleteWord.getBytes("UTF-8"));
                        os.flush();
                        os.close();
                        if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                            String line;
                            String page = "";

                            while ((line = reader.readLine()) != null) {
                                page += line;
                            }
                            Toast.makeText(this, page, Toast.LENGTH_SHORT).show();

                            recreate();
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
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
}
