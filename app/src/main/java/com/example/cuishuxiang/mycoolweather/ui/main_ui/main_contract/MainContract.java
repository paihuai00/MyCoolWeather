package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_presenter.BasePresenter;
import com.csx.mlibrary.base_view.BaseView;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/20.
 */

public interface MainContract {

    interface Model extends BaseModel {
        void queryResponse(String url, OnUrlRequestCallBack<List<Province>> onUrlRequestCallBack);
    }

    interface View extends BaseView {
        void returnResponse(List<Province> provinceList);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void mainRequest(String url);
    }

}
