package com.example.cuishuxiang.mycoolweather.ui.activitys.model;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.app.AppConstant;
import com.example.cuishuxiang.mycoolweather.app.Urls;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.MainContract;
import com.example.cuishuxiang.mycoolweather.utils.HttpUtils;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.logging.Logger;

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

}
