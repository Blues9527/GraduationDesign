package com.blues.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blues.adapter.MulAdapter;
import com.blues.been.Iteraction;
import com.example.blues.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_personalpage extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Iteraction iteraction1 = new Iteraction();
    Iteraction iteraction2 = new Iteraction();
    Iteraction iteraction3 = new Iteraction();
    Iteraction iteraction4 = new Iteraction();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalpage, container, false);

        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String current_time = date.format(new Date());
        
        iteraction1.setPic_iteraction(R.mipmap.icon3);
        iteraction1.setTitle_iteraction("尹某");
        iteraction1.setContext_iteraction("人在广东已经嫖到失联~");
        iteraction1.setView_iteraction(R.mipmap.pic97);
        iteraction1.setTime_iteraction(current_time);
        iteraction1.setSource_iteraction(null);

        iteraction2.setPic_iteraction(R.mipmap.icon1);
        iteraction2.setTitle_iteraction("刘某");
        iteraction2.setContext_iteraction("有时也怀念当初姿势多么经典~");
        iteraction2.setView_iteraction(R.mipmap.pic94);
        iteraction2.setTime_iteraction(current_time);
        iteraction2.setSource_iteraction("某日假新闻");

        iteraction3.setPic_iteraction(R.mipmap.icon2);
        iteraction3.setTitle_iteraction("张某");
        iteraction3.setContext_iteraction("躺在这床上将你我相连~");
        iteraction3.setView_iteraction(R.mipmap.pic96);
        iteraction3.setTime_iteraction(current_time);
        iteraction3.setSource_iteraction("QQ飞车");

        iteraction4.setPic_iteraction(R.mipmap.icon4);
        iteraction4.setTitle_iteraction("蓝某");
        iteraction4.setContext_iteraction("怀念你~");
        iteraction4.setView_iteraction(R.mipmap.pic91);
        iteraction4.setTime_iteraction(current_time);
        iteraction4.setSource_iteraction("某扑");

        initData();

        recyclerView = (RecyclerView) view.findViewById(R.id.iteraction_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;

    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new MulAdapter(getActivity(),getData());
    }

    private List<Iteraction> getData() {
        List<Iteraction> data = new ArrayList<>();
        data.add(iteraction1);
        data.add(iteraction2);
        data.add(iteraction3);
        data.add(iteraction4);
        return data;
    }

}
