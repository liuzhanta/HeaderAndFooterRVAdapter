package com.zterry.headerandfooterRV;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zterry.headerandfooteradapter_rv.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    MyAdapter mAdapter;

    public List<String> getSampleData() {
        List<String> list = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            list.add("Item Position : " + i);
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        findViewById(R.id.addfooter).setOnClickListener(this);
        findViewById(R.id.addheader).setOnClickListener(this);

    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this);
        mAdapter.setData(getSampleData());
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnHeaderViewCreatedListener(new BaseRecyclerViewAdapter.OnHeaderViewCreatedListener() {
            @Override
            public void onHeaderViewCreated(View headerView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addfooter:
                if (mAdapter.containsFooter()) {
                    mAdapter.removeFooter();
                    ((Button) view).setText("add footer");
                } else {
                    mAdapter.addFooter(R.layout.adapter_item_footer);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                    ((Button) view).setText("remove footer");
                }
                break;
            case R.id.addheader:
                if (mAdapter.containsHeader()) {
                    mAdapter.removeHeader();
                    ((Button) view).setText("add header");
                } else {
                    mAdapter.addHeader(R.layout.adapter_item_header);
                    ((Button) view).setText("remove header");
                }
                mRecyclerView.scrollToPosition(0);
                break;
        }
    }
}
