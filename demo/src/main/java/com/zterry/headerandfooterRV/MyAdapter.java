package com.zterry.headerandfooterRV;

import android.content.Context;

import com.zterry.headerandfooteradapter_rv.BaseRecyclerViewAdapter;
import com.zterry.headerandfooteradapter_rv.RecyclerViewHolder;

/**
 * Desc:
 * Author: Terry
 * Date:2017-03-09
 */
public class MyAdapter extends BaseRecyclerViewAdapter<String> {

    public MyAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_rv_item;
    }

    @Override
    protected void onBindViewData(RecyclerViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_name, s);
    }
}
