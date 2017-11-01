package com.example.cuishuxiang.mycoolweather.ui.activitys.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.widget.ImageView;

import com.csx.mlibrary.utils.LocationMgr;
import com.csx.mlibrary.utils.SPUtils;
import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.app.Urls;
import com.example.cuishuxiang.mycoolweather.base.BaseActivity;
import com.example.cuishuxiang.mycoolweather.ui.activitys.presenter.WelComePresenter;
import com.example.cuishuxiang.mycoolweather.ui.activitys.contract.WelcomeContract;
import com.example.cuishuxiang.mycoolweather.ui.activitys.model.WelcomeModel;
import com.example.cuishuxiang.mycoolweather.utils.GlideApp;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

import butterknife.BindView;

/**
 * @author cuishuxiang
 * @date 2017/10/30.
 * 启动页面
 */

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {
    private static final String TAG = "WelcomeActivity";
    @BindView(R.id.welcom_img)
    ImageView welcomImg;

    private WelComePresenter presenter;

    private ObjectAnimator alphaAnimation;

    @Override
    public int setLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    public void init() {
        presenter = new WelComePresenter();

        presenter.setVM(this, new WelcomeModel());

        presenter.welcomeRequest(Urls.WELCOME_URL);

        initAnimator();

        getLocationMsg();
    }

    private void getLocationMsg() {
        if (LocationMgr.checkLocationPermission(WelcomeActivity.this)) {
            //成功返回位置信息
            LocationMgr.getMyLocation(WelcomeActivity.this, new LocationMgr.onLocationListener() {
                @Override
                public void onLocationChanged(int code, double lat1, double long1, String location) {
                    LogUtils.d(TAG, "当前位置为：" + location);
                    //将当前位置信息，存在 sharedPreference 里面
                    SPUtils.put(WelcomeActivity.this, "location_msg", location);
                }
            });
        }
    }

    private void initAnimator() {

        alphaAnimation = ObjectAnimator.ofFloat(welcomImg, "alpha", 1f, 0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtils.d(TAG,"渐变动画结束！");
                Intent startMainActivty = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(startMainActivty);
                finish();
            }
        });
    }

    @Override
    public void returnImgUrl(final String imgUrl) {
        LogUtils.d(TAG, "----------returnImgUrl----------" + imgUrl);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                GlideApp.with(WelcomeActivity.this)
                        .load(imgUrl)
                        .error(R.mipmap.ic_launcher)
                        .into(welcomImg);

                alphaAnimation.start();
            }
        });
    }

    @Override
    public void onShowLoading() {
        LogUtils.d(TAG, "----------showLoading----------");
    }

    @Override
    public void onLoadingSucceed() {
        LogUtils.d(TAG, "----------onLoadingSucceed----------");
    }

    @Override
    public void onLoadingError() {
        LogUtils.d(TAG, "----------showError----------");
    }
}
