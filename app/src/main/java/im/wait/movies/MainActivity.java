package im.wait.movies;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.ButterKnife;
import butterknife.InjectView;
import im.wait.movies.ui.fragment.ComingFragment;
import im.wait.movies.ui.fragment.NewMovicesFragment;
import im.wait.movies.ui.fragment.TheatersFragment;
import im.wait.movies.ui.fragment.TopFragment;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.title)
    TextView title;



    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    protected ActionBar actionBar;
    @InjectView(R.id.titles)
    PagerSlidingTabStrip titles;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initActionBar(toolbar);

        viewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));

        titles.setViewPager(viewPager);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);

        int parseColor = Color.parseColor("#FFAA4D");
        titles.setIndicatorColor(parseColor);
        titles.setViewPager(viewPager);
    }

    private class HomePagerAdapter extends FragmentStatePagerAdapter {
        private final String [] titlesStr={"热映","即将上映","Top250","新片榜"};
        private Fragment theatersFragment;
        private Fragment comingFragment;
        private Fragment topFragment;
        private Fragment newFragment;
        private Fragment currentFragment;
        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
            viewPager.setOffscreenPageLimit(titlesStr.length);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if(theatersFragment==null){
                        theatersFragment= TheatersFragment.newInstance(null);
                    }
                    currentFragment=theatersFragment;
                    break;
                case 1:
                    if(comingFragment==null){
                        comingFragment= ComingFragment.newInstance(null);
                    }
                    currentFragment=comingFragment;
                    break;
                case 2:
                    if(topFragment==null){
                        topFragment= TopFragment.newInstance(null);
                    }
                    currentFragment=topFragment;
                    break;
                case 3:
                    if(newFragment==null){
                        newFragment= NewMovicesFragment.newInstance(null);
                    }
                    currentFragment=newFragment;
                    break;
            }
            return currentFragment;
        }

        @Override
        public int getCount() {
            return titlesStr.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return titlesStr[position];
        }
    }

    protected final void initActionBar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.getBackground().setAlpha(255);
            actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("");
            }
            toolbar.setTitle("");
            initActionBar();
        }
    }

    protected void initActionBar() {
        title.setText("豆瓣电影");
    }



}
