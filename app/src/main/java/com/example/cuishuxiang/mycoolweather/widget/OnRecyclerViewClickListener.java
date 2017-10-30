package com.example.cuishuxiang.mycoolweather.widget;

import android.view.View;

/**
 * @author cuishuxiang
 * @date 2017/10/30.
 *
 * RecyclerView item 点击接口
 */

public interface OnRecyclerViewClickListener {

    void setOnItemClickListener(View view, int position);

    void setOnLongClickListener(View view, int position);
}
