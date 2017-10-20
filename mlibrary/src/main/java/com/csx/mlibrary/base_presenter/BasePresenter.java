package com.csx.mlibrary.base_presenter;

/**
 * Created by cuishuxiang on 2017/10/20.
 */

public abstract class BasePresenter<V, M> {
    public V mView;
    public M mModel;



    public void setVM(V view, M model) {
        this.mModel = model;
        this.mView = view;
    }

}
