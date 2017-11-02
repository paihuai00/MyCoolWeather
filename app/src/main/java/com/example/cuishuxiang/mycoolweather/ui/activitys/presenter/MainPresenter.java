package com.example.cuishuxiang.mycoolweather.ui.activitys.presenter;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.bean_db.AirQualityBean;
import com.example.cuishuxiang.mycoolweather.bean_db.ForecastWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.SuggestBean;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.MainContract;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

/**
 * @author cuishuxiang
 * @date 2017/11/1.
 */

public class MainPresenter extends MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    @Override
    public void requestNowWeatherData(String locationName) {
        mModel.queryNowWeather(locationName, new OnUrlRequestCallBack<NowWeatherBean>() {
            @Override
            public void requestSucceed(NowWeatherBean nowWeatherBean) {
                if (nowWeatherBean != null) {
                    mView.returnNowWeatherDatas(nowWeatherBean);
                }else {
                    LogUtils.d(TAG,"MainPresenter - nowWeatherBean = null");
                }

            }

            @Override
            public void requestFailed() {
                LogUtils.d(TAG,"MainPresenter - MainPresenter");
            }
        });
    }

    @Override
    public void requestForecastData(String locationName) {
        mModel.queryForecastWeather(locationName, new OnUrlRequestCallBack<ForecastWeatherBean>() {
            @Override
            public void requestSucceed(ForecastWeatherBean forecastWeatherBean) {
                mView.returnForecastDatas(forecastWeatherBean);
            }

            @Override
            public void requestFailed() {
                mView.onLoadingError();
                LogUtils.d(TAG,"requestForecastData  requestFailed() ");
            }
        });
    }

    @Override
    public void requestAirQualityData(String locationName) {
        mModel.queryAirQuality(locationName, new OnUrlRequestCallBack<AirQualityBean>() {
            @Override
            public void requestSucceed(AirQualityBean airQualityBean) {
                mView.returnAirQualityDatas(airQualityBean);
            }

            @Override
            public void requestFailed() {
                mView.onLoadingError();
                LogUtils.d(TAG,"requestAirQualityData  -- requestFailed");
            }
        });
    }

    @Override
    public void requestSuggestionDat(String locationName) {
        mModel.querySuggestion(locationName, new OnUrlRequestCallBack<SuggestBean>() {
            @Override
            public void requestSucceed(SuggestBean suggestBean) {
                mView.returnSuggestionDatas(suggestBean);
            }

            @Override
            public void requestFailed() {
                LogUtils.d(TAG, "生活建议 requestFailed");
                mView.onLoadingError();
            }
        });
    }
}
