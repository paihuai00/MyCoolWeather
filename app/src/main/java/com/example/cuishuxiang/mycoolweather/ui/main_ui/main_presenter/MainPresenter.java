package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_presenter;

import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract.MainContract;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/20.
 */

public class MainPresenter extends MainContract.Presenter implements OnUrlRequestCallBack<List<Province>>{
    private static final String TAG = "MainPresenter";

    @Override
    public void mainRequest(String url) {

        mModel.queryResponse(url, this);

    }

    @Override
    public void requestSucceed(List<Province> provinceList) {
        mView.returnResponse(provinceList);
    }

    @Override
    public void requestFailed() {

    }
}
