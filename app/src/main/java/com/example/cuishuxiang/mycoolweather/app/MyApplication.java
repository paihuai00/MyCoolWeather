package com.example.cuishuxiang.mycoolweather.app;

import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.litepal.LitePalApplication;

/**
 * Created by cuishuxiang on 2017/10/17.
 *
 *
 * 由于使用了Litepal数据库，需要继承 LitePalApplication
 */

public class MyApplication extends LitePalApplication {
    private static final String TAG = MyApplication.class.getSimpleName();

    public static final boolean LOG_CONFIG = true;
    /**
     * 数据查询  key= defbffa06a1846fe8bab0b271a9eca6e
     */
    @Override
    public void onCreate() {
        super.onCreate();

        initLogger();
    }

    /**
     *  初始化，Logger 管理
     */
    private void initLogger() {
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)//是否打印当前线程
                .tag("MyCoolWeather")//修改，默认的TAG前缀
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                //此处为log的打印开关
                return LogUtils.LOG_CONFIG;
            }
        });
    }

}
