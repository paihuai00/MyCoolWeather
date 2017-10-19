package com.example.cuishuxiang.mycoolweather.utils;

import android.text.TextUtils;

import com.example.cuishuxiang.mycoolweather.bean_db.City;
import com.example.cuishuxiang.mycoolweather.bean_db.County;
import com.example.cuishuxiang.mycoolweather.bean_db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * 处理返回的 json 数据
 */

public class HandJson {
    /**
     * 解析从服务器，返回的省级数据，并存储到 数据库
     * @param response
     * @return
     * @throws JSONException
     */
    public static boolean handleProvinceRespinse(String response) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allProvinces = new JSONArray(response);

            for (int i = 0; i < allProvinces.length(); i++) {
                JSONObject provinceObj = allProvinces.getJSONObject(i);

                Province province = new Province();
                province.setProvinceCode(provinceObj.getInt("id"));
                province.setProvinceName(provinceObj.getString("name"));
                province.save();
            }

            return true;
        }
        return false;
    }

    /**
     * 处理服务器返回的  市级数据
     * @param response
     * @param provinceId
     * @return
     * @throws JSONException
     */
    public static boolean handleCityResponse(String response, int provinceId) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allCitys = new JSONArray(response);

            for (int i = 0; i < allCitys.length(); i++) {
                JSONObject cityObj = allCitys.getJSONObject(i);

                City city = new City();
                city.setCityName(cityObj.getString("name"));
                city.setCityCode(cityObj.getInt("id"));
                city.setProvinceId(provinceId);

                city.save();
            }

            return true;
        }

        return false;
    }

    /**
     * 解析，县级数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response, int cityId) throws JSONException {

        if (!TextUtils.isEmpty(response)) {
            JSONArray allCounties = new JSONArray(response);

            for (int i = 0; i < allCounties.length(); i++) {
                JSONObject jsonObject = allCounties.getJSONObject(i);

                County county = new County();
                county.setCountyName(jsonObject.getString("name"));
                county.setWeatherId(jsonObject.getString("weather_id"));
                county.setId(cityId);

                county.save();
            }

            return true;
        }

        return false;
    }

}
