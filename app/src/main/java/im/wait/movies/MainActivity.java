package im.wait.movies;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import im.wait.movies.api.ApiImpl;
import im.wait.movies.bean.Subject;
import im.wait.movies.http.network.SimpleJoyResponce;
import im.wait.movies.model.SubjectsModel;
import im.wait.movies.utils.CommonUtil;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.gridView)
    GridView gridView;


    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    protected ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiImpl.getInstance().getTheaters("北京", new SimpleJoyResponce<SubjectsModel>() {
                    @Override
                    public void onSuccess(SubjectsModel response) {
                        Log.e("response", response.getSubjects().get(0).toString());
                    }
                });
            }
        });

        initActionBar(toolbar);

        initDatas();
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

    //分别提供288px x 465px(大)
    private void initDatas() {
        ApiImpl.getInstance().getTheaters("北京", new SimpleJoyResponce<SubjectsModel>() {
            @Override
            public void onSuccess(SubjectsModel response) {
                List<Subject> subjects = response.getSubjects();
                if (null != subjects && subjects.size() > 0) {
                    gridView.setAdapter(new TheatersAdapter(subjects));
                }
            }
        });
    }

    private class TheatersAdapter extends BaseAdapter {
        private List<Subject> subjects;
        private int SCREENWIDTH, ITEM_WIDTH;
        private float ITEM_HEIGHT;

        public TheatersAdapter(List<Subject> subjects) {
            this.subjects = subjects;
            SCREENWIDTH = CommonUtil.getWindowManager(MainActivity.this).getWidth();
            ITEM_WIDTH = (SCREENWIDTH - 64) / 3;
            ITEM_HEIGHT = CommonUtil.getHeight(ITEM_WIDTH, 300, 428);
        }

        @Override
        public int getCount() {
            return subjects == null ? 0 : subjects.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view==null){
                view= View.inflate(MainActivity.this, R.layout.item_theaters, null);
                holder=new ViewHolder(view);
                view.setTag(holder);
            }else{
                holder= (ViewHolder) view.getTag();
            }
            Log.e("ivCover",subjects.get(i).images.large+"---"+subjects.get(i).images.medium+"---"+subjects.get(i).images.small);
            holder.ivCover.setLayoutParams(new LinearLayout.LayoutParams(ITEM_WIDTH, (int) ITEM_HEIGHT));
            holder.ivCover.setTag(subjects.get(i).images.large);
            if(holder.ivCover.getTag()==subjects.get(i).images.large){
                ImageLoader.getInstance().displayImage((String)holder.ivCover.getTag(),holder.ivCover);
            }
            holder.ivName.setText(subjects.get(i).title);
            return view;
        }


    }
    static class ViewHolder {
        @InjectView(R.id.iv_cover)
        ImageView ivCover;
        @InjectView(R.id.iv_name)
        TextView ivName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
