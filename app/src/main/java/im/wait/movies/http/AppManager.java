package im.wait.movies.http;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 15/4/16.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public final class AppManager {


    private final static Object lockObj = new Object();
    private static volatile AppManager manager;
    private List<Activity> activityList = new LinkedList<Activity>();
    private Application application;


    public static AppManager getInstance() {
        if (manager == null) {
            synchronized (lockObj) {
                if (manager == null) {
                    manager = new AppManager();
                }
            }
        }
        return manager;
    }


    public synchronized void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    public synchronized void removeActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
        }
    }

    public List<Activity> getActivitys() {
        return activityList;
    }


    public void attachApplication(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    // 遍历所有Activity并finish
    public void exitApp() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
        System.exit(0);
    }

}
