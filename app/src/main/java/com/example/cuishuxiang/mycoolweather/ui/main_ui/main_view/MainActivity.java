package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.app.Urls;
import com.example.cuishuxiang.mycoolweather.base.BaseActivity;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract.MainContract;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_model.MainModel;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_presenter.MainPresenter;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.x;

public class MainActivity extends BaseActivity implements MainContract.View{
    private static final String TAG = "MainActivity";

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter();
        mPresenter.setVM(this, new MainModel());

        mPresenter.mainRequest(Urls.All_PROVINCE_URL);

        LogUtils.d("TAG","---------s---------");

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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "MainTAG: makeText " +"  "+Thread.currentThread().getName().toString());
                    Toast.makeText(MainActivity.this, "接受到返回数据：" + provinceList.size(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
