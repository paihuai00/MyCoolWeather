package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_model;

import android.util.Log;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_contract.MainContract;
import com.example.cuishuxiang.mycoolweather.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by cuishuxiang on 2017/10/20.
 *
 * model 层，数据请求
 */

public class MainModel implements MainContract.Model {
    private static final String TAG = "MainModel";

    private List<Province> provinceList = new ArrayList<>();

    @Override
    public void queryResponse(String url, final OnUrlRequestCallBack<List<Province>> onUrlRequestCallBack) {
        HttpUtils.sendOkhttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String datas = response.body().string();

                try {
                    JSONArray allProvinces = new JSONArray(datas);

                    for (int i = 0; i < allProvinces.length(); i++) {
                        JSONObject provinceObj = allProvinces.getJSONObject(i);

                        Province province = new Province();
                        province.setProvinceCode(provinceObj.getInt("id"));
                        province.setProvinceName(provinceObj.getString("name"));

                        provinceList.add(province);
                        province.save();
                    }
                    /**
                     * 这里使用接口回调，将服务器请求的数据，回传
                     */
                    if (provinceList != null) {
                        onUrlRequestCallBack.requestSucceed(provinceList);
                    } else {
                        onUrlRequestCallBack.requestFailed();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
