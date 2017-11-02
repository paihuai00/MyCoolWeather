package com.example.cuishuxiang.mycoolweather.ui.activitys.view;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.csx.mlibrary.utils.SPUtils;
import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.base.BaseActivity;
import com.example.cuishuxiang.mycoolweather.bean_db.AirQualityBean;
import com.example.cuishuxiang.mycoolweather.bean_db.ForecastWeatherBean;
import com.example.cuishuxiang.mycoolweather.bean_db.NowWeatherBean;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.MainContract;
import com.example.cuishuxiang.mycoolweather.ui.activitys.model.MainModel;
import com.example.cuishuxiang.mycoolweather.ui.activitys.presenter.MainPresenter;
import com.example.cuishuxiang.mycoolweather.utils.GlideApp;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.example.cuishuxiang.mycoolweather.widget.action_bar.MainTopBar;
import com.example.cuishuxiang.mycoolweather.widget.action_bar.TopBarImp;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class MainActivity extends BaseActivity implements TopBarImp, MainContract.View {

    @BindView(R.id.main_topbar)
    MainTopBar mainTopbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.background_img)
    ImageView backgroundImg;
    @BindView(R.id.degree_text)
    TextView degreeText;
    @BindView(R.id.weather_info_text)
    TextView weatherInfoText;
    @BindView(R.id.forecast_layout)
    AutoLinearLayout forecastLayout;
    @BindView(R.id.aqi_text)
    TextView aqiText;
    @BindView(R.id.pm25_text)
    TextView pm25Text;
    @BindView(R.id.comfort_text)
    TextView comfortText;
    @BindView(R.id.car_wash_text)
    TextView carWashText;
    @BindView(R.id.sport_text)
    TextView sportText;
    @BindView(R.id.weather_layout)
    ScrollView weatherLayout;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.update_time)
    TextView updateTimeText;

    //当前查询的 city
    private String currentCity;

    public MainPresenter mPresenter;

    @Override
    public int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mainTopbar.setTopBarImp(this);

        initPresenter();

        initView();

    }

    private void initView() {
        //从本地，获取存储的图片
        String imgUrl = (String) SPUtils.get(getApplicationContext(), "background_img", "null");
        GlideApp.with(MainActivity.this)
                .load(imgUrl)
                .error(R.mipmap.ic_launcher)
                .into(backgroundImg);
    }

    private void initPresenter() {
        mPresenter = new MainPresenter();

        mPresenter.setVM(this, new MainModel());

        //获取存储到的，当前位置信息，如果没有定位到，就使用默认的  北京
        String currentLoction = (String) SPUtils.get(getApplicationContext(), "location_msg", "南京");
        mPresenter.requestNowWeatherData(currentLoction);

        //获取 预报天气
        mPresenter.requestForecastData(currentLoction);

        //获取 空气质量
        mPresenter.requestAirQualityData(currentLoction);
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

    /**
     * 获取 实时天气bean
     * @param nowWeatherBean
     */
    @Override
    public void returnNowWeatherDatas(NowWeatherBean nowWeatherBean) {
        //动态设置当前城市
        final NowWeatherBean.HeWeather6Bean heWeather6Bean = nowWeatherBean.getHeWeather6().get(0);
        currentCity = heWeather6Bean.getBasic().getLocation();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainTopbar.getMiddleTextView().setText(currentCity);

                //设置当前温度
                degreeText.setText(heWeather6Bean.getNow().getTmp() + " °C");

                //设置当前天气状况
                weatherInfoText.setText(heWeather6Bean.getNow().getCond_txt());

                //更新时间
                updateTimeText.setText("更新于：" + heWeather6Bean.getUpdate().getLoc());
            }
        });

        LogUtils.d(TAG, nowWeatherBean.getHeWeather6().get(0).getStatus());
    }

    /**
     * 获取预报天气数据
     * @param forecastWeatherBean
     */
    @Override
    public void returnForecastDatas(final ForecastWeatherBean forecastWeatherBean) {
        final List<ForecastWeatherBean.HeWeather6Bean.DailyForecastBean> dailyForecastBeanList =
                forecastWeatherBean.getHeWeather6().get(0).getDaily_forecast();

        LogUtils.d(TAG, "预报了" + dailyForecastBeanList.size() + "天气");
        //设置 预报天气
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (ForecastWeatherBean.HeWeather6Bean.DailyForecastBean forecastWeatherBean: dailyForecastBeanList) {
                    View itemView = LayoutInflater.from(MainActivity.this).
                            inflate(R.layout.forecast_item, forecastLayout, false);
                    TextView dateText = (TextView) itemView.findViewById(R.id.date_text);
                    TextView infoText = (TextView) itemView.findViewById(R.id.info_text);
                    TextView maxText = (TextView) itemView.findViewById(R.id.max_text);
                    TextView minText = (TextView) itemView.findViewById(R.id.min_text);

                    dateText.setText(forecastWeatherBean.getDate());
                    infoText.setText("白天：" + forecastWeatherBean.getCond_txt_d() + "\n夜间：" + forecastWeatherBean.getCond_txt_n());
                    maxText.setText("最高："+forecastWeatherBean.getTmp_max()+" °");
                    minText.setText("最低："+forecastWeatherBean.getTmp_min()+" °");

                    forecastLayout.addView(itemView);
                }


            }
        });

    }

    /**
     * 获取 空气质量数据
     * @param airQualityBean
     */
    @Override
    public void returnAirQualityDatas(AirQualityBean airQualityBean) {

        final AirQualityBean.HeWeather6Bean.AirNowBean.AirCityBean airCityBean = airQualityBean.getHeWeather6().get(0).getAir_now().getAir_city();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aqiText.setText(airCityBean.getAqi());
                pm25Text.setText(airCityBean.getPm25());
            }
        });
    }


}
