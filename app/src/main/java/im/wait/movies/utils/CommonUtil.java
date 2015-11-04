package im.wait.movies.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.WindowManager;

/**
 * 作者：thinkloki on 15/11/4 00:00
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class CommonUtil {

    /**
     * 适配图片的宽高
     * @param w         计算出来的图片的宽度
     * @param picWidth  图片的元始像素宽度
     * @param picHeight 图片的元始像素高度
     * @return 计算出来的图片的高度
     */
    public static float getHeight(int w, int picWidth, int picHeight) {
        return (int) ((float) w / picWidth * picHeight);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Activity context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(FragmentActivity context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(FragmentActivity context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 窗口管理器
     *
     * @param mContext 上下文对象
     */
    public static Display getWindowManager(Context mContext) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay();
    }
}
