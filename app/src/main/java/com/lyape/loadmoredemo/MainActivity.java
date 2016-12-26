package com.lyape.loadmoredemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lyape.loadmore.ILoadMoreCallback;
import com.lyape.loadmore.LoadMoreListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoadMoreListView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (LoadMoreListView) findViewById(R.id.list);
        final List<String> list = new ArrayList<>();
        for(int i = 0 ; i < 11 ; i++ ){
            list.add("data" + i);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        mList.setAdapter(adapter);
        mList.setLoadMoreCallback(new ILoadMoreCallback() {
            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0 ; i < 10 ; i ++ ){
                            list.add("new data " + i + i);
                        }
                        mList.loadComplete(true);
                    }
                },2000);
            }
        });

    }
}
