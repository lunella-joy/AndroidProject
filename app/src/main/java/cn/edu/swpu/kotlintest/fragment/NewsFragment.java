package cn.edu.swpu.kotlintest.fragment;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.edu.swpu.kotlintest.R;
import cn.edu.swpu.kotlintest.entity.Webdata;
import cn.edu.swpu.kotlintest.tools.MyService;
import cn.edu.swpu.kotlintest.tools.NewsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private ArrayList<Webdata> webdatas = new ArrayList<Webdata>();
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private int flag = 0;
    private final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news, container, false);
        Intent StartSever = new Intent(getActivity(),MyService.class);
        getActivity().startService(StartSever);
        //接收消息的广播监听器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RECEIVEMSG");
        ReceiveMsg receiveMsg = new ReceiveMsg();
        getActivity().registerReceiver(receiveMsg, intentFilter);
        return view;
    }

    class ReceiveMsg extends BroadcastReceiver {
        private String nextPage;
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取返回的数据
            ArrayList<Webdata> webdata = (ArrayList<Webdata>) intent.getSerializableExtra("data");
            //获得下一页的URL
            nextPage = webdata.get(0).getNextpageurl();
            //将数据添加到变量中
            for (int i = 0 ;i < webdata.size();i++){
                webdatas.add(webdata.get(i));
            }
            //实例化RecyclerView
            recyclerView = view.findViewById(R.id.recyclerview);
            //设置加载哪一种布局
            recyclerView.setLayoutManager(layoutManager);
            newsAdapter = new NewsAdapter(webdatas);
            recyclerView.setAdapter(newsAdapter);
            //上拉加载
            //刷新数据
            //当数据发生变化时，更新View
            newsAdapter.notifyDataSetChanged();
            //调用自定义的方法，将Item定位到指定的位置
            MoveToPosition(layoutManager,recyclerView,flag);
            //为flag赋值
            flag = webdatas.size();
            newsAdapter.changeMoreStatus(NewsAdapter.PULLUP_LOAD_MORE);
            //为Recycrview添加屏幕滚动监听事件
            //并重写其中的方法，以获取数据
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    //设置加载的状态
                    newsAdapter.changeMoreStatus(NewsAdapter.LOADING_MORE);
                    //判断到底部的条件
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && layoutManager.findLastVisibleItemPosition() + 1 == newsAdapter.getItemCount()) {
                        //发送广播
                        Intent sendmsg = new Intent("SENDMESSAGE");
                        sendmsg.putExtra("nextpage",nextPage);
                        getActivity().sendBroadcast(sendmsg);
                    }
                }
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }
    }
    public static void MoveToPosition(GridLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

}
