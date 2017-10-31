package com.example.cuishuxiang.mycoolweather.ui.fragments.model;

import com.csx.mlibrary.base_model.OnUrlRequestCallBack;
import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;
import com.example.cuishuxiang.mycoolweather.ui.fragments.contract.ChooseAreaContract;
import com.example.cuishuxiang.mycoolweather.utils.HttpUtils;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

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

public class ChooseAreaModel implements ChooseAreaContract.Model {
    private static final String TAG = ChooseAreaModel.class.getSimpleName();

    private List<Province> provinceList = new ArrayList<>();

    private List<City> cityList = new ArrayList<>();

    private List<County> countyList = new ArrayList<>();

    /**
     * 获取 省 的数据
     * @param url
     * @param onUrlRequestCallBack
     */
    @Override
    public void queryProvinceResponse(String url, final OnUrlRequestCallBack<List<Province>> onUrlRequestCallBack) {
        HttpUtils.sendOkhttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, "获取 省  的数据失败： " + e.getMessage().toString());
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

    /**
     * 获取 市  的数据
     *
     * @param url
     * @param onUrlRequestCallBack
     */
    @Override
    public void queryCityResponse(String url, final int provinceId, final OnUrlRequestCallBack<List<City>> onUrlRequestCallBack) {

        HttpUtils.sendOkhttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, "获取 市  的数据失败： " + e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datas = response.body().string();

                try {
                    JSONArray jsonArray = new JSONArray(datas);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject cityObject = jsonArray.getJSONObject(i);

                        City city = new City();
                        city.setCityCode(cityObject.getInt("id"));
                        city.setCityName(cityObject.getString("name"));
                        city.setProvinceId(provinceId);
                        city.save();
                        cityList.add(city);
                    }

                    if (onUrlRequestCallBack != null) {
                        onUrlRequestCallBack.requestSucceed(cityList);
                    } else {
                        onUrlRequestCallBack.requestFailed();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取 县  的数据
     *
     * @param url
     * @param onUrlRequestCallBack
     */
    @Override
    public void queryCountyResponse(String url, final int cityId, final OnUrlRequestCallBack<List<County>> onUrlRequestCallBack) {
        HttpUtils.sendOkhttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d(TAG, "获取 县  的数据失败： " + e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datas = response.body().string();

                try {
                    JSONArray jsonArray = new JSONArray(datas);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject countyObject = jsonArray.getJSONObject(i);

                        County county = new County();
                        county.setCountyName(countyObject.getString("name"));
                        county.setCityId(cityId);
                        county.setWeatherId(countyObject.getString("weather_id"));
                        county.save();

                        countyList.add(county);

                    }

                    if (onUrlRequestCallBack != null) {
                        onUrlRequestCallBack.requestSucceed(countyList);
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
