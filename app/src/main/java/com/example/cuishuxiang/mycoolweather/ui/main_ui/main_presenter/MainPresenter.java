package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_presenter;

import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract.MainContract;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/20.
 *
 * MVP 的代理层，持有model view对象
 */

public class MainPresenter extends MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName();


    @Override
    public void requestProvinceData(String urlProvince) {
        mModel.queryProvinceResponse(urlProvince, new OnUrlRequestCallBack<List<Province>>() {
            @Override
            public void requestSucceed(List<Province> provinces) {
                mView.returnProvinceResponse(provinces);
            }

            @Override
            public void requestFailed() {
                LogUtils.d(TAG, "获取 省 数据失败 requestFailed");
            }
        });
    }

    @Override
    public void requestCityData(String urlCity) {

        mModel.queryCityResponse(urlCity, new OnUrlRequestCallBack<List<City>>() {
            @Override
            public void requestSucceed(List<City> cityList) {
                mView.returnCityResponse(cityList);
            }

            @Override
            public void requestFailed() {
                LogUtils.d(TAG, "获取 市 数据失败 requestFailed");
            }
        });
    }

    @Override
    public void requestCountyData(String urlCounty) {
        mModel.queryCountyResponse(urlCounty, new OnUrlRequestCallBack<List<County>>() {
            @Override
            public void requestSucceed(List<County> countyList) {
                mView.returnCountyResponse(countyList);
            }

            @Override
            public void requestFailed() {
                LogUtils.d(TAG, "获取 县 数据失败 requestFailed");
            }
        });
    }
}
