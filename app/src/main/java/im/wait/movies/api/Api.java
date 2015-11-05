package im.wait.movies.api;

import im.wait.movies.http.network.JoyResponce;
import im.wait.movies.model.BaseModel;
import im.wait.movies.model.SubjectsModel;

/**
 * 作者：thinkloki on 15/11/3 21:50
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public interface Api {
    String BASE_URL="https://api.douban.com/v2/movie/";

    //正在上映
    String ACTION_IN_THEATERS="in_theaters";
    //即将上映
    String ACTION_COMING_SOON="coming_soon";
    //top250
    String ACTION_TOP250="top250";
    //new_movies
    String ACTION_NEW_MOVIES="new_movies";
    //subject
    String ACTION_SUBJECT="subject";
    //PHOTOS
    String ACTION_PHOTOS="photos";





    String PARAM_CITY="city";

    String PARAM_START="start";

    String PARAM_COUNT="count";

    void getTheaters(String city,JoyResponce<SubjectsModel> responce);

    void getComing(int start,int count,JoyResponce<SubjectsModel> responce);

    void getTops(int start,int count,JoyResponce<SubjectsModel> responce);

    void getNewMovices(JoyResponce<SubjectsModel> responce);

    void getPhotos(String id,int start,int count,JoyResponce<BaseModel> responce);

}
