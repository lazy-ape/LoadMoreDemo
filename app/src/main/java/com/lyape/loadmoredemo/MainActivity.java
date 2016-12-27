package com.lyape.loadmoredemo;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lyape.loadmore.ILoadMoreCallback;
import com.lyape.loadmore.LoadMoreListView;
import com.lyape.loadmore.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private LoadMoreRecyclerView mList;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mList = (LoadMoreRecyclerView) findViewById(R.id.list);
//        final List<String> list = new ArrayList<>();
//        for(int i = 0 ; i < 11 ; i++ ){
//            list.add("data" + i);
//        }
//
//        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
//        mList.setAdapter(adapter);
//        mList.setLoadMoreListener(new ILoadMoreCallback() {
//            @Override
//            public void loadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        for(int i = 0 ; i < 10 ; i ++ ){
//                            list.add("new data " + i + i);
//                        }
//                        mList.loadComplete(true);
//                    }
//                },2000);
//            }
//        });
//
//    }


    private LoadMoreRecyclerView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (LoadMoreRecyclerView) findViewById(R.id.list);
        final List<String> list = new ArrayList<>();
        for(int i = 0 ; i < 12; i++ ){
            list.add("data" + i);
        }


        mList.setLayoutManager(new LinearLayoutManager(this));
        final SimpleAdapter adapter = new SimpleAdapter(this,list);
        mList.setAdapter(adapter);
        mList.setLoadMoreListener(new ILoadMoreCallback() {
            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0 ; i < 10 ; i ++ ){
                            list.add("new data " + i + i);
                        }
                        if(list.size()>=30){
                            mList.loadComplete(false);
                        }else{
                            mList.loadComplete(true);
                        }

                    }
                },2000);
            }
        });

    }


    private class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder>{

        private List<String> mDatas;

        private LayoutInflater mInflater;
        public SimpleAdapter(Context context,List<String> data){
            mInflater = LayoutInflater.from(context);
            this.mDatas = data;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.text,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            public TextView tv;
            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }


}
