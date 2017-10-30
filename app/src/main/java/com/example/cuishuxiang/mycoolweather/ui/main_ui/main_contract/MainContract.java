package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_presenter.BasePresenter;
import com.csx.mlibrary.base_view.BaseView;
import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/20.
 */

public interface MainContract {

    interface Model extends BaseModel {
        void queryProvinceResponse(String url, OnUrlRequestCallBack<List<Province>> onUrlRequestCallBack);

        void queryCityResponse(String url, OnUrlRequestCallBack<List<City>> onUrlRequestCallBack);


        void queryCountyResponse(String url, OnUrlRequestCallBack<List<County>> onUrlRequestCallBack);

    }

    interface View extends BaseView {
        void returnProvinceResponse(List<Province> provinceList);

        void returnCityResponse(List<City> cityList);

        void returnCountyResponse(List<County> countyList);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        //请求 省份
        public abstract void requestProvinceData(String urlProvince);

        //请求 所有的市
        public abstract void requestCityData(String urlCity);

        //请求 所有的县
        public abstract void requestCountyData(String urlCounty);
    }

}
