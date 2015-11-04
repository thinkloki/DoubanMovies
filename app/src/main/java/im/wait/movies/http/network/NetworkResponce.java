package im.wait.movies.http.network;

import com.android.volley.VolleyError;

import im.wait.movies.http.task.JoyTask;

/**
 *
 * @param <TASK>  必须继承JoyTask，是 业务接口组装的 Task(网络为 NewNetworkTask)，方便做业务容错后的接口重试与分发。
 * @param <MODEL> 为 经过 NetworkMiddleHandler 做 中间层处理后的 类型，一般为各业务Model。
 */
public interface NetworkResponce<TASK extends JoyTask,ResponseString, MODEL> extends Cloneable {

    void onSuccessResponse(TASK task, ResponseString s);

    void onError(TASK task, VolleyError error, MODEL model);

    boolean networkFinish(TASK task);


}
