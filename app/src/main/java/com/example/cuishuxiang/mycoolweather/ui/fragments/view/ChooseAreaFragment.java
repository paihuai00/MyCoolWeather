package com.example.cuishuxiang.mycoolweather.ui.fragments.view;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.adapter.AreaAdapter;
import com.example.cuishuxiang.mycoolweather.app.Urls;
import com.example.cuishuxiang.mycoolweather.base.BaseFragment;
import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.activitys.view.MainActivity;
import com.example.cuishuxiang.mycoolweather.ui.fragments.contract.ChooseAreaContract;
import com.example.cuishuxiang.mycoolweather.ui.fragments.model.ChooseAreaModel;
import com.example.cuishuxiang.mycoolweather.ui.fragments.presenter.ChooseAreaPresenter;
import com.example.cuishuxiang.mycoolweather.widget.OnRecyclerViewClickListener;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cuishuxiang on 2017/10/19.
 * <p>
 * 选择area
 */

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.View {
    private static final String TAG = "ChooseAreaFragment";
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.list_view)
    RecyclerView recyclerView;

    public static final int LEVEL_PROVINCE = 0;

    public static final int LEVEL_CITY = 1;

    public static final int LEVEL_COUNTY = 2;

    private ProgressDialog progressDialog;

    private AreaAdapter areaAdapter;

    private List<String> dataList;

    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 市列表
     */
    private List<City> cityList;

    /**
     * 县列表
     */
    private List<County> countyList;

    /**
     * 选择的省份
     */
    private Province selectProvince;

    /**
     * 选择的城市
     */
    private City selectCity;

    /**
     * 当前选中的级别
     */
    private int currentLevel;

    private View rootView;

    private ChooseAreaPresenter mPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.choose_area;
    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        titleTxt.setText("中国");
        //初始化的时候，不可点击；只有存在二级列表的时候，才显示出来
        backBtn.setVisibility(View.INVISIBLE);
        backBtn.setClickable(false);

        areaAdapter = new AreaAdapter(dataList, getContext());
        areaAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                //Toast.makeText(getContext(), "点击了setOnItemClickListener  " + position + dataList.get(position), Toast.LENGTH_SHORT).show();

                if (currentLevel == LEVEL_PROVINCE) {
                    //如果当前的为 省 ，需要查询省下面的市
                    selectProvince = provinceList.get(position);

                    queryCitys();
                    titleTxt.setText(selectProvince.getProvinceName());
                    backBtn.setVisibility(View.VISIBLE);
                    backBtn.setClickable(true);

                } else if (currentLevel == LEVEL_CITY) {
                    //当前选择   市，需要查询市下面的 县
                    selectCity = cityList.get(position);

                    quryCountys();

                    titleTxt.setText(selectCity.getCityName());
                    backBtn.setVisibility(View.VISIBLE);
                    backBtn.setClickable(true);
                } else if (currentLevel == LEVEL_COUNTY) {
                    //当前选择  县，就跳转到具体页面
                    if (getActivity() instanceof MainActivity) {

                        MainActivity mainActivity = (MainActivity) getActivity();

                        mainActivity.mPresenter.requestNowWeatherData(dataList.get(position));

                        mainActivity.drawerLayout.closeDrawers();
                    }
                }
            }

            @Override
            public void setOnLongClickListener(View view, int position) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(areaAdapter);

        mPresenter = new ChooseAreaPresenter();
        mPresenter.setVM(this, new ChooseAreaModel());

        queryProvinces();

        //返回按钮
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了返回按钮！", Toast.LENGTH_SHORT).show();
                if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                    backBtn.setVisibility(View.INVISIBLE);
                    backBtn.setClickable(false);
                    titleTxt.setText("中 国");
                }

                if (currentLevel == LEVEL_COUNTY) {
                    queryCitys();
                    backBtn.setVisibility(View.VISIBLE);
                    backBtn.setClickable(true);
                }
            }
        });
    }

    /**
     * 查询  省
     */
    private void queryProvinces() {
        currentLevel = LEVEL_PROVINCE;

        //1,首先从数据库查询，看是否已经从服务器查询过了
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList != null && provinceList.size() > 0) {
            dataList.clear();

            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }

            //刷新 列表
            areaAdapter.notifyDataSetChanged();

        } else {
            //请求，所有省份的数据
            mPresenter.requestProvinceData(Urls.All_PROVINCE_URL);
        }
    }

    /**
     * 查询  市(首先从数据库查询，如果没有，就从接口查询)
     */
    private void queryCitys() {
        currentLevel = LEVEL_CITY;

        cityList = DataSupport.where("provinceid = ?", String.valueOf(selectProvince.getId())).find(City.class);
        if (cityList != null && cityList.size() > 0) {
            dataList.clear();

            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            areaAdapter.notifyDataSetChanged();
        }else {
            //接口 需要拼接
            mPresenter.requestCityData(Urls.All_CITY_URL + "/" + selectProvince.getProvinceCode(), selectProvince.getProvinceCode());
        }
    }

    /**
     * 查询 ， 县
     */
    private void quryCountys() {
        currentLevel = LEVEL_COUNTY;

        countyList = DataSupport.where("cityId = ?", String.valueOf(selectCity.getId())).find(County.class);
        if (countyList != null && countyList.size() > 0) {
            dataList.clear();

            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }

            areaAdapter.notifyDataSetChanged();
        }else {
            //接口 需要拼接
            mPresenter.requestCountyData(Urls.All_COUNTRY_URL + "/" + selectProvince.getProvinceCode() + "/" + selectCity.getCityCode(),
                    selectCity.getId());
        }
    }

    /**
     * 注意，此处不是主线程，如需更新ui，则需要切换到主线程
     *
     * @param provinceList
     */
    @Override
    public void returnProvinceResponse(final List<Province> provinceList) {
        if (provinceList != null) {
            this.provinceList = provinceList;
            dataList.clear();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "MainTAG: makeText " + "  " + Thread.currentThread().getName().toString());
                    Toast.makeText(getContext(), "接受到返回数据：" + provinceList.size(), Toast.LENGTH_LONG).show();

                    for (Province province : provinceList) {
                        dataList.add(province.getProvinceName());
                    }
                    areaAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void returnCityResponse(final List<City> cityList) {
        if (cityList != null) {
            this.cityList = cityList;
            dataList.clear();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "接受到返回数据 cityList ：" + cityList.size(), Toast.LENGTH_LONG).show();

                    for (City city : cityList) {
                        dataList.add(city.getCityName());
                    }
                    areaAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public void returnCountyResponse(final List<County> countyList) {
        if (countyList != null) {
            this.countyList = countyList;
            dataList.clear();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "接受到返回数据 countyList ：" + countyList.size(), Toast.LENGTH_LONG).show();

                    for (County county : countyList) {
                        dataList.add(county.getCountyName());
                    }
                    areaAdapter.notifyDataSetChanged();
                }
            });
        }
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
}
