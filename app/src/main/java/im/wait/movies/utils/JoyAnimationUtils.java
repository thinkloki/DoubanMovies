package im.wait.movies.utils;

import android.animation.Animator;
import android.view.View;

/**
 * Created on 15/4/9.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class JoyAnimationUtils {

    private static final int ANIMATION_TIME = 300;

    public static void makeProgressDismiss(final View fromView, final View toView) {
        makeProgressDismissWithListener(fromView, toView, null);
    }

    public static void makeProgressDismissWithListener(final View fromView, final View toView, final AnimatorListener animatorListener) {
        if (fromView != null) {
            if (fromView.getVisibility() != View.VISIBLE) {
                fromView.setVisibility(View.VISIBLE);
            }
            fromView.animate().alpha(0f).setListener(new AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    fromView.setVisibility(View.GONE);
                }
            }).setDuration(ANIMATION_TIME).start();
        }
        toView.setAlpha(0f);
        toView.setVisibility(View.GONE);
        toView.animate().alpha(1f).setListener(new AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                toView.setVisibility(View.VISIBLE);
                if (animatorListener != null) {
                    animatorListener.onAnimationStart(animation);
                }
            }
        }).setDuration(ANIMATION_TIME).start();

    }

}
