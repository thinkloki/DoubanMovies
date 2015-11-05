package im.wait.movies.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.InjectView;
import im.wait.movies.R;
import im.wait.movies.bean.Subject;
import im.wait.movies.http.network.SimpleJoyResponce;
import im.wait.movies.http.task.NetworkTask;
import im.wait.movies.ui.base.BaseActivity;
import im.wait.movies.utils.OnDelayedClickListener;
import im.wait.movies.views.JoyProgressFramelayout;

/**
 * 作者：thinkloki on 15/11/5 13:20
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class MovieDetailActivity extends BaseActivity {

    @InjectView(R.id.cover)
    ImageView cover;
    @InjectView(R.id.scrollView)
    ScrollView scrollView;
    @InjectView(R.id.joy_progress_layout)
    JoyProgressFramelayout joyProgressLayout;
    @InjectView(R.id.tv_rating)
    TextView tvRating;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_leixing)
    TextView tvLeixing;
    @InjectView(R.id.tv_country)
    TextView tvCountry;

    private Subject subject;
    private long oldTime;

    @Override
    protected int getContentView() {
        return R.layout.activity_moviedetail;
    }

    @Override
    protected void initView() {
        subject = (Subject) getIntent().getSerializableExtra("subject");

        toolbar.setTitle(subject.title);

        ImageLoader.getInstance().displayImage(subject.images.large, cover);

        tvRating.setText(subject.rating.average);

        String ss="";
        for (String s:subject.genres) {
            ss=ss+s+"/";
        }
        tvTime.setText(ss.substring(0,ss.length()-1));




    }

    @Override
    protected void initEvents() {
        joyProgressLayout.setErrorClickListener(new OnDelayedClickListener() {
            @Override
            public void onDelayClick(View view) {
                initData();
            }
        });

    }

    @Override
    protected void initData() {
        oldTime=System.currentTimeMillis();
        mApi.getMovieDetail(subject.id, new SimpleJoyResponce<Subject>() {
            @Override
            public void onSuccess(Subject response) {
                subject = response;
                toMain(oldTime);
                Log.e("2222", subject.toString());

            }

            @Override
            public void onError(VolleyError errorException, Subject errorModel) {
                super.onError(errorException, errorModel);
                joyProgressLayout.toError();
            }

            @Override
            public boolean onFinish(NetworkTask task) {
                return super.onFinish(task);
            }
        });
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        if (toolbar != null) {
            title.setText("");
            toolbar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected boolean needSwipeBack() {
        return true;
    }
    public void toMain(long oldTime) {
        long interTime = System.currentTimeMillis() - oldTime;
        if (interTime < DELAY_TIME) {
            if (joyProgressLayout != null) {
                joyProgressLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (joyProgressLayout != null) {
                            joyProgressLayout.toMain();
                        }
                    }
                }, DELAY_TIME - interTime);
            }
        } else {
            if (joyProgressLayout != null) {
                joyProgressLayout.toMain();
            }
        }
    }


}
