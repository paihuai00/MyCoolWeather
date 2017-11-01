package com.csx.mlibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * @author cuishuxiang
 * @date 2017/11/1.
 */

public class LocationMgr {
    private static String TAG = LocationMgr.class.getSimpleName();
    private static LocationListener mLocationListener;

    //监测定位权限
    public static boolean checkLocationPermission(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    /**
     * 根据经纬度解码地理位置
     * @param activity
     * @param location
     * @return
     */
    private static String getAddressFromLocation(final Activity activity, Location location) {
        Geocoder geocoder = new Geocoder(activity);
        try {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.d(TAG, "getAddressFromLocation->lat:" + latitude + ", long:" + longitude);
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                //返回当前位置，精度可调
                Address address = addresses.get(0);
                String sAddress;
                if (!TextUtils.isEmpty(address.getLocality())) {
                    if (!TextUtils.isEmpty(address.getFeatureName())) {
                        sAddress = address.getLocality() + " " + address.getFeatureName();
                    } else {
                        sAddress = address.getLocality();
                    }
                } else {
                    sAddress = "定位失败";
                }
                return sAddress;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取位置
     * @param activity
     * @param locationListener
     * @return
     */
    public static boolean getMyLocation(final Activity activity, final onLocationListener locationListener) {
        final LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        //判断网络定位是否可用
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //调用定位提示对话框，打开定位功能
            AlertDialog.Builder builder
                    = new AlertDialog.Builder(activity);
            builder.setMessage("尚未开启位置定位服务");
            builder.setPositiveButton("开启", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //启动定位Activity
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    activity.startActivity(intent);
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            builder.show();
            return false;
        }
        //权限检查
        if (!checkLocationPermission(activity)) {
            return true;
        }

        // 通过网络获取位置
        Location curLoc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (null == curLoc) {
            mLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //获取解码的地理位置
                    String strAddress = getAddressFromLocation(activity, location);
                    if (TextUtils.isEmpty(strAddress)) {
                        //定位失败回调
                        locationListener.onLocationChanged(-1, 0, 0, strAddress);
                    } else {
                        //定位成功回调
                        locationListener.onLocationChanged(0, location.getLatitude(), location.getLongitude(), strAddress);
                    }
                    //关闭 GPS 定位功能
                    locationManager.removeUpdates(this);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    //关闭 GPS 定位功能
                    locationManager.removeUpdates(this);
                }

                @Override
                public void onProviderEnabled(String provider) {
                    //关闭 GPS 定位功能
                    locationManager.removeUpdates(this);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    //关闭 GPS 定位功能
                    locationManager.removeUpdates(this);
                }
            };
            //设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,8000,0,mLocationListener);
        }else {
            //获取解码的地理位置
            String strAddress = getAddressFromLocation(activity,curLoc);
            if (TextUtils.isEmpty(strAddress)) {
                //定位失败回调
                locationListener.onLocationChanged(-1, 0, 0, strAddress);
            } else {
                //定位成功回调
                locationListener.onLocationChanged(0, curLoc.getLatitude(), curLoc.getLongitude(), strAddress);
            }
        }
        return true;
    }
    //自定义定位监听回调接口
    public interface onLocationListener {
        void onLocationChanged(int code, double lat1, double long1, String location);
    }
}
