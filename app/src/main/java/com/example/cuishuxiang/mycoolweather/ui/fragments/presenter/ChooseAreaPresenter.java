package com.example.cuishuxiang.mycoolweather.ui.fragments.presenter;

import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.fragments.contract.ChooseAreaContract;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/20.
 *
 * MVP 的代理层，持有model view对象
 */

public class ChooseAreaPresenter extends ChooseAreaContract.Presenter {
    private static final String TAG = ChooseAreaPresenter.class.getSimpleName();


    @Override
    public void requestProvinceData(String urlProvince) {
        mView.onShowLoading();
        mModel.queryProvinceResponse(urlProvince, new OnUrlRequestCallBack<List<Province>>() {
            @Override
            public void requestSucceed(List<Province> provinces) {
                mView.returnProvinceResponse(provinces);
                mView.onLoadingSucceed();
            }

            @Override
            public void requestFailed() {
                LogUtils.d(TAG, "获取 省 数据失败 requestFailed");
                mView.onLoadingError();
            }
        });
    }

    @Override
    public void requestCityData(String urlCity) {
        mView.onShowLoading();
        mModel.queryCityResponse(urlCity, new OnUrlRequestCallBack<List<City>>() {
            @Override
            public void requestSucceed(List<City> cityList) {
                mView.returnCityResponse(cityList);

                mView.onLoadingSucceed();
            }

            @Override
            public void requestFailed() {
                mView.onLoadingError();
                LogUtils.d(TAG, "获取 市 数据失败 requestFailed");
            }
        });
    }

    @Override
    public void requestCountyData(String urlCounty, int cityId) {
        mView.onShowLoading();
        mModel.queryCountyResponse(urlCounty, cityId, new OnUrlRequestCallBack<List<County>>() {
            @Override
            public void requestSucceed(List<County> countyList) {
                mView.returnCountyResponse(countyList);

                mView.onLoadingSucceed();
            }

            @Override
            public void requestFailed() {
                mView.onLoadingError();
                LogUtils.d(TAG, "获取 县 数据失败 requestFailed");
            }
        });
    }
}
