package com.blues.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.blues.DoDownloadActivity;
import com.blues.DoUploadActivity;
import com.example.blues.myapplication.R;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_pluspage extends Fragment{

    private ImageButton doDownload,doUpload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pluspage, container, false);
        doDownload = (ImageButton) view.findViewById(R.id.doDownload);
        doDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent download_intent = new Intent(getActivity(), DoDownloadActivity.class);
                startActivity(download_intent);
            }
        });
        doUpload = (ImageButton) view.findViewById(R.id.doUpload);
        doUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upload_intent = new Intent(getActivity(), DoUploadActivity.class);
                startActivity(upload_intent);
            }
        });
        return view;
    }

}