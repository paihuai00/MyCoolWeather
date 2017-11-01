package com.example.cuishuxiang.mycoolweather.ui.activitys.contract;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.csx.mlibrary.base_presenter.BasePresenter;
import com.csx.mlibrary.base_view.BaseView;
import com.example.cuishuxiang.mycoolweather.bean_db.ForecastWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;

/**
 * @author cuishuxiang
 * @date 2017/11/1.
 */

public interface MainContract {

    interface Model extends BaseModel {
        void queryNowWeather(String locationName, OnUrlRequestCallBack<NowWeatherBean> nowWeatherBeanOnUrlRequestCallBack);

        void queryForecastWeather(String locationName, OnUrlRequestCallBack<ForecastWeatherBean> onUrlRequestCallBack);
    }

    interface View extends BaseView {
        void returnNowWeatherDatas(NowWeatherBean nowWeatherBean);

        void returnForecastDatas(ForecastWeatherBean forecastWeatherBean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void requestNowWeatherData(String locationName);

        public abstract void requestForecastData(String locationName);
    }

}
