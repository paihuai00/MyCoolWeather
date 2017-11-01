package com.example.cuishuxiang.mycoolweather.app;

/**
 * Created by cuishuxiang on 2017/10/25.
 *
 * 接口列表
 */

public class Urls {
    /**
     * 获取 省份 列表
     */
    public static final String All_PROVINCE_URL = "http://guolin.tech/api/china";

    /**
     * 获取 市 列表
     * 需要在最后添加 城市code，例如：http://guolin.tech/api/china/13
     */
    public static final String All_CITY_URL = "http://guolin.tech/api/china";

    /**
     * 获取 县 列表
     * 需要在最后添加 县code，例如：http://guolin.tech/api/china/13/67
     */
    public static final String All_COUNTRY_URL = "http://guolin.tech/api/china";

    /**
     * 获取，启动页图片
     */
    public static final String WELCOME_URL = "http://guolin.tech/api/bing_pic";

    /**
     * 获取实时天气
     * https://free-api.heweather.com/s6/weather/now?location=北京&key=defbffa06a1846fe8bab0b271a9eca6e
     */
    public static final String NOW_WEATHER_URL = "https://free-api.heweather.com/s6/weather/now";
}
