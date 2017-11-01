package com.example.cuishuxiang.mycoolweather.ui.activitys.view;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;

import com.csx.mlibrary.utils.SPUtils;
import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.base.BaseActivity;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.MainContract;
import com.example.cuishuxiang.mycoolweather.ui.activitys.model.MainModel;
import com.example.cuishuxiang.mycoolweather.ui.activitys.presenter.MainPresenter;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.example.cuishuxiang.mycoolweather.widget.action_bar.MainTopBar;
import com.example.cuishuxiang.mycoolweather.widget.action_bar.TopBarImp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class MainActivity extends BaseActivity implements TopBarImp, MainContract.View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.main_topbar)
    MainTopBar mainTopbar;
    @BindView(R.id.main_scrollview)
    ScrollView mainScrollview;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    public MainPresenter mPresenter;

    //当前查询的 city
    private String currentCity;

    @Override
    public int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mainTopbar.setTopBarImp(this);

        initPresenter();

    }

    private void initPresenter() {
        mPresenter = new MainPresenter();

        mPresenter.setVM(this, new MainModel());

        //获取存储到的，当前位置信息，如果没有定位到，就使用默认的  北京
        String currentLoction = (String) SPUtils.get(MainActivity.this, "location_msg", "南京");
        mPresenter.requestNowWeatherData(currentLoction);
    }

    @Override
    public void leftTopBackListener() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void rightTopBarViewListener() {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onLoadingSucceed() {

    }

    @Override
    public void onLoadingError() {

    }

    @Override
    public void returnNowWeatherDatas(NowWeatherBean nowWeatherBean) {
        //动态设置当前城市
        currentCity = nowWeatherBean.getHeWeather6().get(0).getBasic().getLocation();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainTopbar.getMiddleTextView().setText(currentCity);
            }
        });

        LogUtils.d(TAG, nowWeatherBean.getHeWeather6().get(0).getStatus());
    }

}
