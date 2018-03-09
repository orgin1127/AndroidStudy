package kr.co.momtalk.momtalk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /**
     * android.support.v4.view.PagerAdapter 는  전체 section 에 대한 fragment 액티비티 화면을 포함 합니다.
     * 즉 아래, FragmentPagerAdapter 상속받은 사용자생성 SectionsPagerAdapter 클래스를 사용해서 전체 fragment 액티비티화면을 메모리에 올려 놓습니다.
     * 만약 이러한 사용이 메모리에 부하를 주게 된다면,, android.support.v4.app.FragmentStatePagerAdapter 라이브러리를 사용하는 것이 좋습니다.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * 아래 ViewPager 개체는 각 section 콘텐츠를 화면에 올리게 됩니다.
     */
    static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //현재 액티비티에서만 액션바 숨기기
        getSupportActionBar().hide();

        // 현재 액티비티에 전체(3개)의 섹션을 Fragment 액티비티화면에 리턴받고, 어댑터를 만들게 됩니다.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // 위에서 생성된 섹션 어댑터에서 화면표시 섹션을 메인 액티비티의 뷰페이저 ID로 바인딩 시킵니다.(화면출력)
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴를 화면 액션 바에 표시
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 액션 바에 각 Item 메뉴를 선택시 작동
        int id = item.getItemId();

        //기본값=무작동
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 아래 FragmentPagerAdapter 에서 상속받은 SectionsPagerAdapter 는 섹션의 페이지중 하나에 대응하는 Fragment 액티비티화면을 반환 합니다.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem 매서드는 주어진 페이지의 Fragment 액티비티 화면을 인스턴스화 합니다.
            // PlaceholderFragment 액티비티 화면을 반환 (아래에서 정적 내부 클래스로 정의).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // 전체 페이지를 지정합니다. 여기서는 3페이지.
            return 3;
        }
        // 전체 화면으로 처리 될 예정 이기 때문에 페이지 섹션 타이틀은 사용하지 않을 것입니다.
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * placeholder fragment 액티비티 화면은 간단한 뷰 위젯을 포함하고 있음.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * 현재 fragment 액티비티화면의 섹션 페이지 번호를 나타내는 변수 선언.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /** PlaceholderFragment 클래스 생성자
         * 주어진 섹션페이지 번호에 대해 현재 Fragment 액티비티 화면의 인스턴스를 리턴.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        //PlaceholderFragment 클래스 초기 생성자
        public PlaceholderFragment() {
        }
        // PlaceholderFragment 클래스가 호출될때 현재 섹션넘버에 대응하는 fragment_main 레이아웃을 root 화면에 표시 합니다.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_main2, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_main3, container, false);
            }
            //이동버튼 클릭 이벤트
            rootView.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(0);
                    //Toast.makeText(getActivity(), "btn1", Toast.LENGTH_SHORT).show();
                }
            });
            rootView.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(1);
                    //Toast.makeText(getActivity(), "btn2", Toast.LENGTH_SHORT).show();
                }
            });
            rootView.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(2);
                    //Toast.makeText(getActivity(), "btn3", Toast.LENGTH_SHORT).show();
                }
            });
            return rootView;
        }
    }

}
