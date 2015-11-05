package im.wait.movies.bean;

import java.io.Serializable;

/**
 * 作者：thinkloki on 15/11/3 22:29
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class Rating implements Serializable{
    public int max;
    public int min;
    public int value;
    public int stars;
    public String average;


    @Override
    public String toString() {
        return "Rating{" +
                "max=" + max +
                ", min=" + min +
                ", value=" + value +
                ", stars=" + stars +
                ", average='" + average + '\'' +
                '}';
    }
}
