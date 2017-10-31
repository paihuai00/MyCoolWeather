package com.example.cuishuxiang.mycoolweather.ui.activitys.contract;

import com.csx.mlibrary.base_model.BaseModel;
import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.csx.mlibrary.base_presenter.BasePresenter;
import com.csx.mlibrary.base_view.BaseView;

/**
 * @author cuishuxiang
 * @date 2017/10/30.
 */

public interface WelcomeContract {

    interface Model extends BaseModel {
        void quryStartImg(String imgUrl, OnUrlRequestCallBack<String> onUrlRequestCallBack);
    }

    interface View extends BaseView {
        void returnImgUrl(String imgUrl);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void welcomeRequest(String url);
    }

}
