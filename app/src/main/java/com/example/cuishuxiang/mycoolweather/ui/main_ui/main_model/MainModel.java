package com.example.cuishuxiang.mycoolweather.ui.main_ui.main_model;

import android.util.Log;

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

    private List<Province> provinceList;
    @Override
    public List<Province> queryResponse(String url) {

        HttpUtils.sendOkhttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                provinceList = new ArrayList<>();

                try {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    JSONArray allProvinces = new JSONArray(response.body().toString());

                    for (int i = 0; i < allProvinces.length(); i++) {
                        JSONObject provinceObj = allProvinces.getJSONObject(i);

                        Province province = new Province();
                        province.setProvinceCode(provinceObj.getInt("id"));
                        province.setProvinceName(provinceObj.getString("name"));

                        provinceList.add(province);

                        province.save();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        if (provinceList != null) {
            return provinceList;
        }

        return null;

    }
}
