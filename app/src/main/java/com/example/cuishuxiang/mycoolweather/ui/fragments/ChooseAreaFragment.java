package com.example.cuishuxiang.mycoolweather.ui.fragments;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.adapter.AreaAdapter;
import com.example.cuishuxiang.mycoolweather.base.BaseFragment;
import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cuishuxiang on 2017/10/19.
 */

public class ChooseAreaFragment extends BaseFragment {
    private static final String TAG = "ChooseAreaFragment";
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.list_view)
    RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    private AreaAdapter areaAdapter;

    private List<String> dataList;

    /**
     *  省列表
     */
    private List<Province> provinceList;

    /**
     *  市列表
     */
    private List<City> cityList;

    /**
     *  县列表
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

    @Override
    public int getLayoutRes() {
        return R.layout.choose_area;
    }
    @Override
    public void initView() {
        dataList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            dataList.add(i + " s ");
        }


        areaAdapter = new AreaAdapter(dataList, getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(areaAdapter);

    }


}
