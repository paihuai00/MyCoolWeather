package com.example.cuishuxiang.mycoolweather.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.base.BaseFragment;
import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cuishuxiang on 2017/10/19.
 */

public class ChooseAreaFragment extends BaseFragment {
    private static final String TAG = "ChooseAreaFragment";
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.back_btn)
    Button backBtn;
    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;

    private ProgressDialog progressDialog;

    private ArrayAdapter<String> adapter;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_area, container, false);

        unbinder = ButterKnife.bind(this, view);


        initView();
        return view;
    }

    private void initView() {
        dataList = new ArrayList<>();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
