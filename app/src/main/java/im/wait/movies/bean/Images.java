package im.wait.movies.bean;

import java.io.Serializable;

/**
 * 作者：thinkloki on 15/11/3 22:31
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class Images implements Serializable{
    public  String small;
    public  String large;
    public  String medium;

    @Override
    public String toString() {
        return "Images{" +
                "small='" + small + '\'' +
                ", large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}
