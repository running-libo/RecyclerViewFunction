package com.example.recyclerview.recyclerviewfunction;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {
    private XRecyclerView recyclerView;
    private ArrayList<Bean> datas = new ArrayList<>();
    private MultiItemTypeAdapter adapter;
    private int curPage = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadData();
    }

    private void init() {

        recyclerView = (XRecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new MyAdapter(getApplicationContext(),datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(this);
    }

    private void loadData(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final List list = new ArrayList();
                for(int i=curPage*10;i<curPage*10+10;i++){
                    list.add(new Bean("项目" + i));
                }

                datas.addAll(list);
                adapter.notifyDataSetChanged();
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        },1000);

    }


    @Override
    public void onRefresh() {
        datas.clear();
        curPage = 0;
        loadData();
    }

    @Override
    public void onLoadMore() {
        curPage++;
        loadData();
    }
}
