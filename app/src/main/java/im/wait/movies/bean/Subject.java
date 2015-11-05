package im.wait.movies.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import im.wait.movies.model.BaseModel;

/**
 * 作者：thinkloki on 15/11/3 22:34
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class Subject extends BaseModel implements Serializable{
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
    public String summary;              //简介

    public String comments_count;    //评论人数
    public String ratings_count;        //评分人数


    public List<People> directors;

    public List<People> casts;

    public int reviews_count;           //看过
    public int wish_count;              //想看
    public int collect_count;           //收藏
    public String douban_site;          //豆瓣地址
    public String mobile_url;


    public String do_count;
    public String seasons_count;
    public String schedule_url;
    public String episodes_count;
    public String current_season;


    public String [] countries;         //国别

    public String [] aka;               //推荐

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
