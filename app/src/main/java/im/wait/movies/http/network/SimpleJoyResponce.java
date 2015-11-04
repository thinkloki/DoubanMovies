package im.wait.movies.http.network;

import com.android.volley.VolleyError;

import im.wait.movies.http.task.NetworkTask;

/**
 * Created by xicheng on 15/5/13.
 */
public class SimpleJoyResponce<MODEL> implements JoyResponce<MODEL> {
    @Override
    public void onSuccess(MODEL response) {

    }

    @Override
    public void onError(VolleyError errorException, MODEL errorModel) {

    }

    @Override
    public boolean onFinish(NetworkTask task) {
        return task != null;
    }
}
