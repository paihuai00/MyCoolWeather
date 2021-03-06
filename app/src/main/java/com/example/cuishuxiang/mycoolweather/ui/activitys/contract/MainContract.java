package com.example.cuishuxiang.mycoolweather.ui.activitys.contract;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.csx.mlibrary.base_presenter.BasePresenter;
import com.csx.mlibrary.base_view.BaseView;
import com.example.cuishuxiang.mycoolweather.bean_db.AirQualityBean;
import com.example.cuishuxiang.mycoolweather.bean_db.ForecastWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.SuggestBean;

/**
 * @author cuishuxiang
 * @date 2017/11/1.
 */

public interface MainContract {

    interface Model extends BaseModel {
        void queryNowWeather(String locationName, OnUrlRequestCallBack<NowWeatherBean> nowWeatherBeanOnUrlRequestCallBack);

        void queryForecastWeather(String locationName, OnUrlRequestCallBack<ForecastWeatherBean> onUrlRequestCallBack);

        void queryAirQuality(String loactionName, OnUrlRequestCallBack<AirQualityBean> qualityBeanOnUrlRequestCallBack);

        void querySuggestion(String location, OnUrlRequestCallBack<SuggestBean> suggestBeanOnUrlRequestCallBack);
    }

    interface View extends BaseView {
        void returnNowWeatherDatas(NowWeatherBean nowWeatherBean);

        void returnForecastDatas(ForecastWeatherBean forecastWeatherBean);

        void returnAirQualityDatas(AirQualityBean airQualityBean);

        void returnSuggestionDatas(SuggestBean suggestBean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void requestNowWeatherData(String locationName);

        public abstract void requestForecastData(String locationName);

        public abstract void requestAirQualityData(String locationName);

        public abstract void requestSuggestionDat(String locationName);
    }

}
