package com.csx.mlibrary.base_model;

/**
 * Created by cuishuxiang on 2017/10/25.
 *
 * 数据请求的 接口回调
 */

public interface OnUrlRequestCallBack<T> {
    void requestSucceed(T t);

    void requestFailed();
}
