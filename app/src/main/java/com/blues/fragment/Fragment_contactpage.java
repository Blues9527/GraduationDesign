package com.blues.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blues.adapter.MyRecyclerAdapter;
import com.example.blues.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_contactpage extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactpage, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.contract_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        initData();
        return view;
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new MyRecyclerAdapter(getData());

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String[] a = {"蓝某建","张某祥","刘某良","尹某俊","韩某艺","周某星","张某林"};
        for (int i = 0; i < a.length; i++) {
            data.add(a[i]);
        }
        return data;
    }
}
