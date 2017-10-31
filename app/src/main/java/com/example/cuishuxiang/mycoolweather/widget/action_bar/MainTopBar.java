package com.example.cuishuxiang.mycoolweather.widget.action_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuishuxiang.mycoolweather.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by cuishuxiang on 2017/7/4.
 *
 * 自定义 一个topbar ，左边是一个返回按钮，中间是文字，右边是ViewStub
 */

public class MainTopBar extends AutoLinearLayout {
    private boolean isShowleft;
    private boolean isShowMiddle;
    private String middleTxt;
    private boolean isShowRight;
    private int rightResId;

    private ImageView leftBack;
    private TextView middleTextView;
    private ViewStub viewStub;
    //通过ViewStub加载的组件
    private View rightStubView;

    private TopBarImp topBarImp;


    public void setTopBarImp(TopBarImp topBarImp) {
        this.topBarImp = topBarImp;
    }

    public MainTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MainTopBar);
        isShowleft = typedArray.getBoolean(R.styleable.MainTopBar_isShowLeftImg, false);
        isShowMiddle = typedArray.getBoolean(R.styleable.MainTopBar_isShowMiddleTxt, false);

        if (isShowMiddle) {
            middleTxt = typedArray.getString(R.styleable.MainTopBar_middleTxt);
        }

        isShowRight = typedArray.getBoolean(R.styleable.MainTopBar_isShowRightView, false);

        if (isShowRight) {
            rightResId = typedArray.getResourceId(R.styleable.MainTopBar_rightView, R.layout.topbar_right_default_layout);
        }

        initView(context);

        typedArray.recycle();
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_topar_layout, this, true);

        if (leftBack == null) {
            leftBack = (ImageView) view.findViewById(R.id.topbar_left_img);
        }
        if (middleTextView == null) {
            middleTextView = (TextView) view.findViewById(R.id.topbar_middle_txt);
        }
        if (viewStub == null) {
            viewStub = (ViewStub) view.findViewById(R.id.topbar_right_view);
        }
        //左边的返回图标
        if (isShowleft) {
            leftBack.setVisibility(VISIBLE);
            leftBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    topBarImp.leftTopBackListener();
                }
            });
        }else {
            leftBack.setVisibility(GONE);
        }

        //中间的文字
        if (isShowMiddle) {
            middleTextView.setVisibility(VISIBLE);
            middleTextView.setText(middleTxt);
        }else {
            middleTextView.setVisibility(GONE);
        }

        //右边的ViewStub
        if (isShowRight && rightResId != 0) {
            viewStub.setVisibility(VISIBLE);
            viewStub.setLayoutResource(rightResId);
            rightStubView = viewStub.inflate();

            rightStubView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    topBarImp.rightTopBarViewListener();
                }
            });
        } else {
            viewStub.setVisibility(GONE);
        }
    }

    public TextView getMiddleTextView() {
        if (middleTextView == null) {
            return null;
        }
        return middleTextView;
    }
}
