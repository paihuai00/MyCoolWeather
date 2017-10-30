package com.example.cuishuxiang.mycoolweather.ui.welcome_ui;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

/**
 * @author cuishuxiang
 * @date 2017/10/30.
 */

public class WelComePresenter extends WelcomeContract.Presenter implements OnUrlRequestCallBack<String> {
    private static final String TAG = "WelComePresenter";
    @Override
    public void welcomeRequest(String url) {
        mModel.quryStartImg(url, this);
    }

    @Override
    public void requestSucceed(String s) {
        mView.returnImgUrl(s);
    }

    @Override
    public void requestFailed() {
        LogUtils.d(TAG, "每日一图 ，数据请求失败!");
    }
}
