package im.wait.movies.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import im.wait.movies.R;
import im.wait.movies.api.ApiImpl;
import im.wait.movies.http.AppManager;
import im.wait.movies.http.network.NetWorkHelper;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * 作者：thinkloki on 15/11/3 19:49
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public abstract class BaseActivity extends AppCompatActivity implements SwipeBackActivityBase {
    protected final int DELAY_TIME=2000;
    private SwipeBackActivityHelper mHelper;
    protected AppCompatActivity mContext;
    protected NetWorkHelper netWorkHelper;
    protected ApiImpl mApi;
    @Optional
    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @Optional
    @InjectView(R.id.title)
    protected TextView title;
    protected ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needSwipeBack()) {
            mHelper = new SwipeBackActivityHelper(this);
            mHelper.onActivityCreate();
        }
        initBase();
        setContentView(getContentView());
        initActionBar(toolbar);
        initView();
        initEvents();
        initData();
        setKeyBoardListener();
    }
    public final void setContentView(int layout) {
        View mainView = mContext.getLayoutInflater().inflate(layout, null);
        super.setContentView(mainView);
        ButterKnife.inject(this, mainView);
    }
    protected abstract boolean needSwipeBack();

    protected abstract int getContentView();

    protected void initActionBar(Toolbar toolbar){
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
    };
    protected void initActionBar() {

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mHelper != null) {
            mHelper.onPostCreate();
            setSwipeBackEnable(true);
        }
    }


    protected abstract void initView();

    protected abstract void initEvents();

    protected abstract void initData();


    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        if (mHelper != null) {
            getSwipeBackLayout().setEnableGesture(enable);
        }
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
    private void setKeyBoardListener() {
        if (getSwipeBackLayout()!=null){
            getSwipeBackLayout().addSwipeListener(new SwipeBackLayout.SwipeListener() {
                @Override
                public void onScrollStateChange(int state, float scrollPercent) {

                }

                @Override
                public void onEdgeTouch(int edgeFlag) {
                    hideKeyBoard();
                }

                @Override
                public void onScrollOverThreshold() {

                }
            });
        }
    }

    /**
     * inis
     * 隐藏键盘
     */
    protected final void hideKeyBoard() {
        InputMethodManager inputManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
    /**
     * 显示键盘
     */
    protected final void showKeyBoard() {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void initBase() {
        mContext = this;
        netWorkHelper = NetWorkHelper.getInstance();
        mApi = ApiImpl.getInstance();
        AppManager.getInstance().addActivity(this);
    }

}
