package im.wait.movies.http.network;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;

import im.wait.movies.http.task.NetworkTask;
import im.wait.movies.model.BaseModel;


/**
 * 作者：thinkloki on 15/8/28 09:29
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 * <p/>
 * 接口封装类
 */
public class NetworkMiddleHandler<MODEL extends BaseModel> implements NetworkResponce<NetworkTask, String,MODEL> {

    private JoyResponce<MODEL> joyResponce;
    private Class<MODEL> clazz;

    public NetworkMiddleHandler(Class<MODEL> clazz, JoyResponce<MODEL> joyResponce) {
        this.joyResponce = joyResponce;
        this.clazz = clazz;
    }


    @Override
    public void onError(NetworkTask task, VolleyError error, MODEL errorModel) {
        joyResponce.onError(error,errorModel);
    }

    @Override
    public void onSuccessResponse(NetworkTask task, String responseData) {
            MODEL data = null;
            boolean isJsonParseExceptionFlag = true;
            try {
                data= JSONObject.parseObject(responseData,clazz);
            } catch (Exception e) {
                e.printStackTrace();
                if (responseData != null && !responseData.startsWith("{")) {
                    isJsonParseExceptionFlag = false;
                }
            }
            if (data == null) {
                if (isJsonParseExceptionFlag) {
                    joyResponce.onError(new VolleyError(), null);
                } else {
                    joyResponce.onError(new VolleyError(), null);
                }
                return;
            }
        joyResponce.onSuccess(data);
//            switch (data.code) {
//                //TODO 统一处理
//                case 1000:
//                    joyResponce.onSuccess(data);
//                    break;
//                default:
//                    //默认 抛出 未知异常，// 后续应该保持 未知异常的数据于 XML,根据事件（每次APP启动）将未知的异常信息传输到服务端
//                    //，便于日志分析
////                joyResponce.onError(new JoyUnKnownException(), data);
//                    break;
//            }
    }
    @Override
    public boolean networkFinish(NetworkTask task) {
        return joyResponce != null && joyResponce.onFinish(task);
    }
}
