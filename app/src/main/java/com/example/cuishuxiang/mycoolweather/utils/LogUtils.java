package com.example.cuishuxiang.mycoolweather.utils;

import com.orhanobut.logger.AndroidLogAdapter;

import java.util.logging.Logger;

/**
 *
 * @author cuishuxiang
 * @date 2017/10/28
 *
 * 封装 Logger
 */

public class LogUtils {

    public static final boolean LOG_CONFIG = true;

    public static void d(String tag, String msg) {
        com.orhanobut.logger.Logger.t(tag).d(msg);
    }

    public static void e(String tag, String msg) {
        com.orhanobut.logger.Logger.t(tag).e(tag, msg);
    }

    public static void i(String tag, String msg) {
        com.orhanobut.logger.Logger.t(tag).i(msg);

    }

    public static void json(String tag, String msgJson) {
        com.orhanobut.logger.Logger.t(tag).json(msgJson);
    }

}
