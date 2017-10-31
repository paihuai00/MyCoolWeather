package com.csx.mlibrary.base_view;

/**
 * Created by cuishuxiang on 2017/10/20.
 *
 * View 的基础接口
 */

public interface BaseView {

    void onShowLoading();

    void onLoadingSucceed();

    void onLoadingError();

}
