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

/**
 *
 */
public class MainActivity extends BaseActivity{
    private static final String TAG = "MainActivity";

    @Override
    public int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
    }
}
