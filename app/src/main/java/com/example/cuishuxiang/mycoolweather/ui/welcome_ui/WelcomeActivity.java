package com.example.cuishuxiang.mycoolweather.ui.welcome_ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.app.Urls;
import com.example.cuishuxiang.mycoolweather.base.BaseActivity;
import com.example.cuishuxiang.mycoolweather.ui.main_ui.main_view.MainActivity;
import com.example.cuishuxiang.mycoolweather.utils.GlideApp;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public void showLoading() {
        LogUtils.d(TAG, "----------showLoading----------");
    }

    @Override
    public void hindLoading() {
        LogUtils.d(TAG, "----------hindLoading----------");
    }

    @Override
    public void showError() {
        LogUtils.d(TAG, "----------showError----------");
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

}
