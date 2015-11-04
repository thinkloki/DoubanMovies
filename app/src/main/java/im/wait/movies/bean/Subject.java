package im.wait.movies.bean;

import java.util.Arrays;
import java.util.List;

/**
 * 作者：thinkloki on 15/11/3 22:34
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class Subject{
    public String id;                  //条目id
    public String title;                //中文名
    public String original_title;       //原名
    public String alt;                  //条目URL
    public Images images;               //电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸
    public Rating rating;               //评分
    public String pubdates;             //如果条目类型是电影则为上映日期，如果是电视剧则为首播日期
    public String year;                 //年代
    public String subtype;              //条目分类, movie或者tv
    public String [] genres;

    public int collect_count;

    public List<People> directors;

    public List<People> casts;

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", alt='" + alt + '\'' +
                ", images=" + images +
                ", rating=" + rating +
                ", pubdates='" + pubdates + '\'' +
                ", year='" + year + '\'' +
                ", subtype='" + subtype + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", collect_count=" + collect_count +
                ", directors=" + directors +
                ", casts=" + casts +
                '}';
    }
}
