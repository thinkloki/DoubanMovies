package im.wait.movies.api;

import im.wait.movies.http.network.JoyResponce;
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






    String PARAM_CITY="city";

    void getTheaters(String city,JoyResponce<SubjectsModel> responce);

}
