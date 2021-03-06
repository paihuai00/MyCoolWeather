package com.example.cuishuxiang.mycoolweather.ui.activitys.model;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.app.AppConstant;
import com.example.cuishuxiang.mycoolweather.app.Urls;
import com.example.cuishuxiang.mycoolweather.bean_db.AirQualityBean;
import com.example.cuishuxiang.mycoolweather.bean_db.ForecastWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.SuggestBean;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.MainContract;
import com.example.cuishuxiang.mycoolweather.utils.HttpUtils;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author cuishuxiang
 * @date 2017/11/1.
 */

public class MainModel implements MainContract.Model {
    private static final String TAG = "MainModel";
    private Gson gson = new Gson();
    /**
     * 接口：https://free-api.heweather.com/s6/weather/now?location=北京&key=defbffa06a1846fe8bab0b271a9eca6e
     * 需要拼接接口
     * @param locationName
     * @param onUrlRequestCallBack
     */
    @Override
    public void queryNowWeather(String locationName, final OnUrlRequestCallBack<NowWeatherBean> onUrlRequestCallBack) {

        HttpUtils.sendOkhttpRequest(Urls.NOW_WEATHER_URL + "?location=" + locationName + "&key=" + AppConstant.HEFENG_KEY,
                new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onUrlRequestCallBack.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datas = response.body().string();

                NowWeatherBean nowWeatherBean = gson.fromJson(datas, NowWeatherBean.class);

                LogUtils.d(TAG,"status = "+ nowWeatherBean.getHeWeather6().get(0).getStatus());
                onUrlRequestCallBack.requestSucceed(nowWeatherBean);
            }
        });
    }

    /**
     * https://free-api.heweather.com/s6/weather/forecast?location=北京&key=defbffa06a1846fe8bab0b271a9eca6e
     */
    @Override
    public void queryForecastWeather(String locationName, final OnUrlRequestCallBack<ForecastWeatherBean> onUrlRequestCallBack) {

        HttpUtils.sendOkhttpRequest(Urls.FORECAST_WEATHER_URL + "?location=" + locationName + "&key=" + AppConstant.HEFENG_KEY, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onUrlRequestCallBack.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datas = response.body().string();
                ForecastWeatherBean forecastWeatherBean = gson.fromJson(datas, ForecastWeatherBean.class);
                onUrlRequestCallBack.requestSucceed(forecastWeatherBean);
            }
        });
    }

    /**
     * https://free-api.heweather.com/s6/air/now?location=北京&key=defbffa06a1846fe8bab0b271a9eca6e
     * @param loactionName
     * @param qualityBeanOnUrlRequestCallBack
     */
    @Override
    public void queryAirQuality(String loactionName, final OnUrlRequestCallBack<AirQualityBean> qualityBeanOnUrlRequestCallBack) {

        HttpUtils.sendOkhttpRequest(Urls.AIR_URL + "?location=" + loactionName + "&key=" + AppConstant.HEFENG_KEY, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, "空气质量  onFailure");

                qualityBeanOnUrlRequestCallBack.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datas = response.body().string();

                AirQualityBean airQualityBean = gson.fromJson(datas, AirQualityBean.class);

                qualityBeanOnUrlRequestCallBack.requestSucceed(airQualityBean);

            }
        });
    }

    /**
     * https://free-api.heweather.com/s6/weather/lifestyle?location=北京&key=defbffa06a1846fe8bab0b271a9eca6e
     * @param location
     * @param suggestBeanOnUrlRequestCallBack
     */
    @Override
    public void querySuggestion(String location, final OnUrlRequestCallBack<SuggestBean> suggestBeanOnUrlRequestCallBack) {

        HttpUtils.sendOkhttpRequest(Urls.SUGGESTION_URL + "?location=" + location + "&key=" + AppConstant.HEFENG_KEY, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                suggestBeanOnUrlRequestCallBack.requestFailed();
                LogUtils.d(TAG,"生活建议 onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.d(TAG,"生活建议 onResponse");
                String jsonObj = response.body().string();

                SuggestBean suggestBean = gson.fromJson(jsonObj, SuggestBean.class);

                suggestBeanOnUrlRequestCallBack.requestSucceed(suggestBean);

            }
        });
    }


}
