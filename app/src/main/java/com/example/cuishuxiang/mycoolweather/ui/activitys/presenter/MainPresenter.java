package com.example.cuishuxiang.mycoolweather.ui.activitys.presenter;

import android.widget.Toast;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
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
}
