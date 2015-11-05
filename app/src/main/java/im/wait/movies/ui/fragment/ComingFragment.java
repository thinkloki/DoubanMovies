package im.wait.movies.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.GridView;

import com.android.volley.VolleyError;

import java.util.List;

import butterknife.InjectView;
import im.wait.movies.R;
import im.wait.movies.bean.Subject;
import im.wait.movies.http.network.SimpleJoyResponce;
import im.wait.movies.http.task.NetworkTask;
import im.wait.movies.model.SubjectsModel;
import im.wait.movies.ui.adapter.TheatersAdapter;
import im.wait.movies.ui.base.BaseFragment;
import im.wait.movies.utils.OnDelayedClickListener;
import im.wait.movies.views.JoyProgressFramelayout;

/**
 * 作者：thinkloki on 15/11/4 18:13
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class ComingFragment extends BaseFragment {

    @InjectView(R.id.gridView)
    GridView gridView;
    @InjectView(R.id.joy_progress_layout)
    JoyProgressFramelayout joyProgressLayout;

    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    private long oldTime;

    public static ComingFragment newInstance(Bundle bundle) {
        ComingFragment fragment = new ComingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_theaters;
    }

    @Override
    protected void initEvents() {
        joyProgressLayout.setErrorClickListener(new OnDelayedClickListener() {
            @Override
            public void onDelayClick(View view) {
                initDatas();
            }
        });
        swipeLayout.setColorSchemeColors(Color.parseColor("#FFB600"));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDatas();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {
        oldTime=System.currentTimeMillis();
        mApi.getComing(0, 20, new SimpleJoyResponce<SubjectsModel>() {
            @Override
            public void onSuccess(SubjectsModel response) {
                List<Subject> subjects = response.getSubjects();
                if (null != subjects && subjects.size() > 0) {
                    gridView.setAdapter(new TheatersAdapter(subjects, mContext));
                    joyProgressLayout.toMain();
                } else {
                    joyProgressLayout.toNoData();
                }
            }

            @Override
            public void onError(VolleyError errorException, SubjectsModel errorModel) {
                joyProgressLayout.toNoData();
            }
            @Override
            public boolean onFinish(NetworkTask task) {
                toLoadSuccess(oldTime);
                return super.onFinish(task);
            }
        });
    }
    public void toLoadSuccess(long oldTime) {
        long interTime = System.currentTimeMillis() - oldTime;
        if (interTime < DELAY_TIME) {
            if (swipeLayout != null) {
                swipeLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeLayout != null) {
                            swipeLayout.setRefreshing(false);
                        }
                    }
                }, DELAY_TIME - interTime);
            }
        } else {
            if (swipeLayout != null) {
                swipeLayout.setRefreshing(false);
            }
        }
    }

}
