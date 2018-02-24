package com.blues.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blues.myapplication.R;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_personalpage extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalpage, container, false);
        return view;
    }

}
