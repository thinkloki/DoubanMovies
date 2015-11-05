package im.wait.movies.api;

import im.wait.movies.bean.Subject;
import im.wait.movies.http.network.JoyResponce;
import im.wait.movies.http.network.NetWorkHelper;
import im.wait.movies.http.network.NetworkMiddleHandler;
import im.wait.movies.http.task.NetworkTask;
import im.wait.movies.model.BaseModel;
import im.wait.movies.model.SubjectsModel;

/**
 * 作者：thinkloki on 15/11/3 21:50
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class ApiImpl implements Api {
    protected NetWorkHelper netWorkHelper;


    private static volatile ApiImpl mApiImpl;

    public static ApiImpl getInstance() {
        if (null == mApiImpl) {
            synchronized (ApiImpl.class) {
                if (null == mApiImpl) {
                    mApiImpl = new ApiImpl();
                }
            }
        }
        return mApiImpl;
    }

    private ApiImpl() {
        netWorkHelper = NetWorkHelper.getInstance();
    }

    @Override
    public void getTheaters(String city,JoyResponce<SubjectsModel> responce) {
        NetworkTask networkTask = new NetworkTask(BASE_URL + ACTION_IN_THEATERS, NetworkTask.Method.GET);
        networkTask.addParams(PARAM_CITY, city);
        networkTask.setNetworkNewResponce(new NetworkMiddleHandler<>(SubjectsModel.class, responce));
        netWorkHelper.addTask(networkTask);
    }

    @Override
    public void getComing(int start, int count, JoyResponce<SubjectsModel> responce) {
        NetworkTask networkTask = new NetworkTask(BASE_URL + ACTION_COMING_SOON, NetworkTask.Method.GET);
        networkTask.addParams(PARAM_START, String.valueOf(start));
        networkTask.addParams(PARAM_COUNT, String.valueOf(count));
        networkTask.setNetworkNewResponce(new NetworkMiddleHandler<>(SubjectsModel.class, responce));
        netWorkHelper.addTask(networkTask);
    }

    @Override
    public void getTops(int start, int count, JoyResponce<SubjectsModel> responce) {
        NetworkTask networkTask = new NetworkTask(BASE_URL + ACTION_TOP250, NetworkTask.Method.GET);
        networkTask.addParams(PARAM_START, String.valueOf(start));
        networkTask.addParams(PARAM_COUNT, String.valueOf(count));
        networkTask.setNetworkNewResponce(new NetworkMiddleHandler<>(SubjectsModel.class, responce));
        netWorkHelper.addTask(networkTask);
    }

    @Override
    public void getNewMovices(JoyResponce<SubjectsModel> responce) {
        NetworkTask networkTask = new NetworkTask(BASE_URL + ACTION_NEW_MOVIES, NetworkTask.Method.GET);
        networkTask.setNetworkNewResponce(new NetworkMiddleHandler<>(SubjectsModel.class, responce));
        netWorkHelper.addTask(networkTask);
    }

    @Override
    public void getPhotos(String id,int start, int count, JoyResponce<BaseModel> responce) {
        NetworkTask networkTask = new NetworkTask(BASE_URL + ACTION_SUBJECT+"/"+id+"/"+ACTION_PHOTOS, NetworkTask.Method.GET);
        networkTask.addParams(PARAM_START, String.valueOf(start));
        networkTask.addParams(PARAM_COUNT, String.valueOf(count));
        networkTask.setNetworkNewResponce(new NetworkMiddleHandler<>(BaseModel.class, responce));
        netWorkHelper.addTask(networkTask);
    }

    @Override
    public void getMovieDetail(String id, JoyResponce<Subject> responce) {
        NetworkTask networkTask = new NetworkTask(BASE_URL + ACTION_SUBJECT+"/"+id, NetworkTask.Method.GET);
        networkTask.setNetworkNewResponce(new NetworkMiddleHandler<>(Subject.class, responce));
        netWorkHelper.addTask(networkTask);
    }
}
