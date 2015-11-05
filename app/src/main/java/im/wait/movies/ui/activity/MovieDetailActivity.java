package im.wait.movies.ui.activity;

import android.view.View;

import im.wait.movies.R;
import im.wait.movies.ui.base.BaseActivity;

/**
 * 作者：thinkloki on 15/11/5 13:20
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class MovieDetailActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_moviedetail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        if (toolbar != null) {
            title.setText("");
            toolbar.setTitle("电影详情");
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
}
