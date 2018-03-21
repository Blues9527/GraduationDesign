package com.blues.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blues.adapter.MultipleItemAdapter;
import com.blues.been.Chat;
import com.example.blues.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_messagepage extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Chat chat1 = new Chat();
    Chat chat2 = new Chat();
    Chat chat3 = new Chat();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messagepage, container, false);

        /**
         * chat1\chat2\chat3为测试数据，实际应用时数据来源为数据库或其他存储工具。
         */

        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String current_time = date.format(new Date());

        chat1.setPic_chat(R.mipmap.icon1);
        chat1.setTitle_chat("刘某");
        chat1.setContext_chat("你好！很高兴见到你！");
        chat1.setTime_chat(current_time);

        chat2.setPic_chat(R.mipmap.icon2);
        chat2.setTitle_chat("张某");
        chat2.setContext_chat("你好！难过见到你！");
        chat2.setTime_chat(current_time);

        chat3.setPic_chat(R.mipmap.icon3);
        chat3.setTitle_chat("尹某");
        chat3.setContext_chat("你好！很不想见到你！");
        chat3.setTime_chat(current_time);

        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.chat_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new MultipleItemAdapter(getActivity(),getData());
    }

    private List<Chat> getData() {
        List<Chat> data = new ArrayList<>();
        data.add(chat1);
        data.add(chat2);
        data.add(chat3);
        return data;
    }

}
