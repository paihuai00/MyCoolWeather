package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_presenter.BasePresenter;
import com.csx.mlibrary.base_view.BaseView;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;

import java.util.List;

import okhttp3.Response;

/**
 * Created by cuishuxiang on 2017/10/20.
 */

public interface MainContract {

    interface Model extends BaseModel {
        List<Province> queryResponse(String url);
    }

    interface View extends BaseView {
        void returnResponse(List<Province> provinceList);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void mainRequest(String url);
    }

}
