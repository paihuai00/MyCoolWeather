package com.example.cuishuxiang.mycoolweather.ui.fragments;

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
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract.MainContract;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_model.MainModel;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_presenter.MainPresenter;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_view.MainActivity;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.example.cuishuxiang.mycoolweather.widget.OnRecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * 选择area
 */

public class ChooseAreaFragment extends BaseFragment implements MainContract.View{
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

    private MainPresenter mPresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.choose_area;
    }
    @Override
    public void initView() {
        dataList = new ArrayList<>();

        areaAdapter = new AreaAdapter(dataList, getContext());
        areaAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Toast.makeText(getContext(), "点击了setOnItemClickListener  " + position + dataList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setOnLongClickListener(View view, int position) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(areaAdapter);

        mPresenter = new MainPresenter();
        mPresenter.setVM(this, new MainModel());

        //请求，所有省份的数据
        mPresenter.mainRequestProvince(Urls.All_PROVINCE_URL);

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hindLoading() {

    }

    @Override
    public void showError() {

    }
    /**
     * 注意，此处不是主线程，如需更新ui，则需要切换到主线程
     * @param provinceList
     */
    @Override
    public void returnResponse(final List<Province> provinceList) {
        if (provinceList != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "MainTAG: makeText " +"  "+Thread.currentThread().getName().toString());
                    Toast.makeText(getContext(), "接受到返回数据：" + provinceList.size(), Toast.LENGTH_LONG).show();

                    for (Province province : provinceList) {
                        dataList.add(province.getProvinceName());
                    }
                    areaAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
