package com.example.cuishuxiang.mycoolweather.ui.activitys.model;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.WelcomeContract;
import com.example.cuishuxiang.mycoolweather.utils.HttpUtils;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author cuishuxiang
 * @date 2017/10/30.
 */

public class WelcomeModel implements WelcomeContract.Model {
    private static final String TAG = "WelcomeModel";
    @Override
    public void quryStartImg(String imgUrl, final OnUrlRequestCallBack<String> onUrlRequestCallBack) {
        HttpUtils.sendOkhttpRequest(imgUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, e.getMessage().toString());
                //接口回调，返回
                onUrlRequestCallBack.requestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String imgSourseUrl = response.body().string();

                onUrlRequestCallBack.requestSucceed(imgSourseUrl);

                LogUtils.d(TAG, "每日一图接口地址："+imgSourseUrl);
            }
        });
    }
}
