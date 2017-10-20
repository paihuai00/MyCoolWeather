package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_presenter;

import com.csx.mlibrary.base_presenter.BasePresenter;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract.MainContract;
import com.example.cuishuxiang.mycoolweather.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by cuishuxiang on 2017/10/20.
 */

public class MainPresenter extends MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    @Override
    public void mainRequest(String url) {
        mModel.queryResponse(url);


        mView.returnResponse(mModel.queryResponse(url));
    }
}
