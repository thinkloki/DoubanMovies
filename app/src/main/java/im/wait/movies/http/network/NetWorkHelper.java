package im.wait.movies.http.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import im.wait.movies.MovieApp;
import im.wait.movies.http.task.NetworkTask;

/**
 * 作者：thinkloki on 15/8/28 09:29
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 * <p/>
 * 接口封装类
 */
public class NetWorkHelper {


    private RequestQueue mRequestQueue;
    private static final String TAG = NetWorkHelper.class.getSimpleName();
    private final static Object lock = new Object();
    private static volatile NetWorkHelper networkHelper;

    public static NetWorkHelper getInstance() {
        if (networkHelper == null) {
            synchronized (lock) {
                if (networkHelper == null) {
                    networkHelper = new NetWorkHelper(MovieApp.getAppContext());
                }
            }
        }
        return networkHelper;
    }

    private NetWorkHelper(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }


    private void setNetworkUrl(NetworkTask networkTask) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(networkTask.getHttpUrl());
        urlBuilder.append("?");
        int mapSize = networkTask.getParams().size();
        int position = 0;
        for (Map.Entry<String, String> entry : networkTask.getParams().entrySet()) {
            urlBuilder.append(entry.getKey());
            urlBuilder.append("=");
            urlBuilder.append(entry.getValue());
            if (position != mapSize - 1) {
                urlBuilder.append("&");
            }
            position++;
        }
        networkTask.setHttpUrl(urlBuilder.toString());
    }

    public void addTask(final NetworkTask networkTask) {
        final NetworkResponce networkResponce = networkTask.getNetworkResponce();
//            boolean checkNetState = NetworkUtils.checkNetState(AppManager.getInstance().getApplication().getApplicationContext());
//            if (!checkNetState) {
//                if (networkResponce != null && networkResponce.networkFinish(networkTask)) {
//                    networkResponce.onError(networkTask, new JoyNoConnectionException(), null);
//                }
//                return;
//            }
        final long oldTime = System.currentTimeMillis();
        if (networkTask.getMethod() == NetworkTask.Method.GET) {
            setNetworkUrl(networkTask);
        }
        StringBaseRequest stringRequest = new StringBaseRequest(networkTask.getMethod(), networkTask.getHttpUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (networkResponce != null) {
                    //TODO 做成 钩子，实现性能优化
                    if (networkResponce.networkFinish(networkTask)) {
                        networkResponce.onSuccessResponse(networkTask, response);
                    }
                }
                //测试的事后 调用接口调试
                long persistTime = System.currentTimeMillis() - oldTime;
                String responce = "接口调用时间:" + persistTime + " ms ," + networkTask.toString() + "  , reponse:" + response;
                if (persistTime > 1500) {
                    Log.e(TAG, responce);
                } else {
                    Log.d(TAG, responce);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                if (networkResponce != null) {
                    if (networkResponce.networkFinish(networkTask)) {
                        networkResponce.onError(networkTask, error, null);
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (networkTask.getMethod() != NetworkTask.Method.GET) {
                    return networkTask.getParams();
                } else {
                    return super.getParams();
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return networkTask.getHeader();
            }
        };

        stringRequest.setTag(networkTask.getTag());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
//            stringRequest.setCacheKey(makeCacheKey(networkTask.getHttpUrl(), networkTask.getParams()));
        stringRequest.setShouldCache(networkTask.isShouldCacheFlag());
        mRequestQueue.add(stringRequest);

    }


}
