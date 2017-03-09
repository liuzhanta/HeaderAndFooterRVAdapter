package com.zterry.headerandfooteradapter_rv;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Desc:An Enhanced BaseRecyclerViewAdapter
 * Author: Terry
 * Date:2016-04-08
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    public static class ViewType {
        public static final int HEADER = 0;
        public static final int FOOTER = 1;
        public static final int NORMAL = 2;
    }

    private final Context mContext;
    private int footerLayoutId;
    private Resources mResources = null;
    private View headerView;
    private View footerView;

    private List<T> mDatas;

    public List<T> getmDatas() {
        return this.mDatas;
    }

    public void addFooter(int footerLayoutId) {
        this.footerLayoutId = footerLayoutId;
        containsHeader = true;
        notifyItemInserted(getItemCount() + 1);
    }

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }


    public void addHeaderView(View headerView) {
        this.headerView = headerView;
        notifyDataSetChanged();
    }

    public int getHeaderLayoutId() {
        return headerLayoutId;
    }

    public void addHeader(int headerLayoutId) {
        this.headerLayoutId = headerLayoutId;
        containsHeader = true;
        notifyItemInserted(0);
    }

    public void removeHeader() {
        this.headerLayoutId = 0;
        headerView = null;
        notifyItemRemoved(0);
    }

    public void removeFooter() {
        this.footerLayoutId = 0;
        footerView = null;
        notifyItemRemoved(getItemCount()-1);
    }

    private int headerLayoutId = 0;

    private boolean containsHeader = false;

    public boolean containsHeader() {
        return headerLayoutId != 0;
    }

    public boolean containsFooter() {
        return footerLayoutId != 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerLayoutId != 0 && position == 0) {
            return ViewType.HEADER;
        } else if (footerLayoutId != 0 && position == getItemCount() - 1) {
            return ViewType.FOOTER;
        } else {
            return ViewType.NORMAL;
        }
    }

    public View getHeaderView() {
        return headerView;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        if (viewType == ViewType.HEADER) {
            headerView = LayoutInflater.from(parent.getContext()).inflate(getHeaderLayoutId(), parent, false);
            if (onHeaderViewCreatedListener != null) {
                onHeaderViewCreatedListener.onHeaderViewCreated(headerView);
            }
            return new HeaderViewHolder(headerView);
        } else if (viewType == ViewType.FOOTER) {
            footerView = LayoutInflater.from(parent.getContext()).inflate(getFooterLayoutId(), parent, false);
            return new FooterViewHolder(footerView);
        } else {
            return onCreateNormalViewHolder(parent, viewType);
        }

    }

    private int getFooterLayoutId() {
        return footerLayoutId;
    }

    private RecyclerViewHolder onCreateNormalViewHolder(ViewGroup parent, final int viewType) {
        final RecyclerViewHolder viewHolder = RecyclerViewHolder.createViewHolder(mContext, parent, getLayoutId(), viewType);

        onViewHolderCreateAfter(parent, viewHolder, viewType);

        //set onItemClick Listener for item view
        if (onItemClickListener != null) {
            viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(viewHolder.getItemView(), viewHolder, viewHolder.getAdapterPosition());
                    }
                }
            });
        }

        //set onItemLongClick Listener for item view
        if (onItemLongClickListener != null) {
            viewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(viewHolder.getItemView(), viewHolder, viewHolder.getAdapterPosition());
                    }
                    return true;
                }
            });
        }
        return viewHolder;
    }


    protected void onViewHolderCreateAfter(ViewGroup parent, RecyclerViewHolder viewHolder, int viewType) {

    }

    /**
     * Return an ID for an XML layout resource to load (e.g., R.layout.main_page)
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * Called when the convert view has been created and set data in view.
     *
     * @param holder
     * @param t
     * @param position
     */
    protected abstract void onBindViewData(RecyclerViewHolder holder, T t, int position);


    private static final String TAG = "BaseRecyclerViewAdapter";
    ;

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: pos = " + position);
        if (holder instanceof HeaderViewHolder || holder instanceof FooterViewHolder) {
            //do nothing.
            //position start from 0.
        } else {
            int pos = containsHeader() ? position - 1 : position;

            onBindViewData(holder, mDatas.get(pos), pos);
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null || mDatas.isEmpty()) {
            return 0;
        }
        if (containsFooter() && containsHeader()) {
            return mDatas.size() + 2;
        }
        if (containsHeader() || containsFooter()) {
            return mDatas.size() + 1;
        }
        return mDatas.size();
    }

    public void setData(List<T> data) {
        mDatas = data;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        if (containsHeader) {
            position--;
        }
        return mDatas == null ? null : mDatas.get(position);
    }

    public void appendData(T data) {
        if (mDatas != null) {
            mDatas.add(data);
        }
        notifyDataSetChanged();
    }

    public void insert(T data, int position) {
        mDatas.add(position, data);
        notifyItemInserted(position);
    }

    public void appendData(List<T> data) {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.addAll(data);
        } else {
            mDatas = data;
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mDatas != null)
            mDatas.clear();
        notifyDataSetChanged();
    }

    public Resources getResources() {
        if (mResources == null) {
            mResources = mContext.getResources();
        }
        return mResources;
    }

    public String getString(int resId) {
        return getResources().getString(resId);
    }


    /**
     * Interface definition for a callback to be invoked when an item in this
     * RecyclerView has been clicked.
     */
    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this RecyclerView has
         * been clicked.
         *
         * @param itemView
         * @param holder
         * @param position
         */
        void onItemClick(View itemView, RecyclerView.ViewHolder holder, int position);
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * view has been clicked and held.
     */
    public interface OnItemLongClickListener {
        /**
         * Callback method to be invoked when an item in this view has been
         * clicked and held.
         *
         * @param itemView
         * @param position
         */
        void onItemLongClick(View itemView, RecyclerView.ViewHolder holder, int position);
    }

    private BaseRecyclerViewAdapter.OnHeaderViewCreatedListener onHeaderViewCreatedListener;

    public BaseRecyclerViewAdapter.OnHeaderViewCreatedListener getOnHeaderViewCreatedListener() {
        return onHeaderViewCreatedListener;
    }

    public void setOnHeaderViewCreatedListener(BaseRecyclerViewAdapter.OnHeaderViewCreatedListener onHeaderViewCreatedListener) {
        this.onHeaderViewCreatedListener = onHeaderViewCreatedListener;
    }

    public interface OnHeaderViewCreatedListener {
        void onHeaderViewCreated(View headerView);
    }
}

