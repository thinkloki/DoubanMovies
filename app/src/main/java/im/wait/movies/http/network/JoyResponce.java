package im.wait.movies.http.network;

import com.android.volley.VolleyError;

import im.wait.movies.http.task.NetworkTask;


/**
 * Created by Yomine on 2015/4/2.
 */
public interface JoyResponce<MODEL> {
    void onSuccess(MODEL model);

    void onError(VolleyError errorException, MODEL errorModel);

    boolean onFinish(NetworkTask task);
}
