package com.example.cuishuxiang.mycoolweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuishuxiang.mycoolweather.R;
import com.example.cuishuxiang.mycoolweather.utils.LogUtils;
import com.example.cuishuxiang.mycoolweather.widget.OnRecyclerViewClickListener;

import java.util.List;

/**
 * @author cuishuxiang
 * @data 2017/10/28.
 */

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    private static final String TAG = "AreaAdapter";
    private List<String> dataList;
    private Context mContext;

    private OnRecyclerViewClickListener onRecyclerViewClickListener;

    public AreaAdapter(List<String> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_area_layout, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.area_txt.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView area_txt;

        public ViewHolder(View itemView) {
            super(itemView);

            area_txt = itemView.findViewById(R.id.area_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerViewClickListener != null) {
                        onRecyclerViewClickListener.setOnItemClickListener(v, getAdapterPosition());
                        LogUtils.d(TAG,"点击了 setOnItemClickListener");
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onRecyclerViewClickListener != null) {
                        onRecyclerViewClickListener.setOnLongClickListener(v, getAdapterPosition());
                        LogUtils.d(TAG,"点击了 setOnLongClickListener");
                    }
                    return true;
                }
            });

        }
    }


}
