package im.wait.movies.model;

import java.util.List;

import im.wait.movies.bean.Subject;

/**
 * 作者：thinkloki on 15/11/3 23:01
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class SubjectsModel extends BaseModel{
    public String title;
    private List<Subject> subjects;

    @Override
    public String toString() {
        return "SubjectsModel{" +
                "subjects=" + getSubjects() +
                '}';
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
