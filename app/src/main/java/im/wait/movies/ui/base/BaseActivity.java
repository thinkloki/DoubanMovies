package im.wait.movies.ui.base;

import android.support.v7.app.AppCompatActivity;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;

/**
 * 作者：thinkloki on 15/11/3 19:49
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class BaseActivity extends AppCompatActivity implements SwipeBackActivityBase {


    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return null;
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {

    }

    @Override
    public void scrollToFinishActivity() {

    }
}
