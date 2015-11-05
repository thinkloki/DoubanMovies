package im.wait.movies.http.network;

import com.android.volley.VolleyError;

import im.wait.movies.http.task.NetworkTask;


/**
 * 作者：thinkloki on 15/8/28 09:29
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 * <p/>
 * 接口封装类
 */
public interface JoyResponce<MODEL> {
    void onSuccess(MODEL model);

    void onError(VolleyError errorException, MODEL errorModel);

    boolean onFinish(NetworkTask task);
}
