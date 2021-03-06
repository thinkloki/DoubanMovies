package im.wait.movies.bean;

import java.io.Serializable;

/**
 * 作者：thinkloki on 15/11/3 22:56
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class People implements Serializable{
    public String name;
    public String id;
    public String alt;
    public Images avatars;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                ", avatars=" + avatars +
                '}';
    }
}
