package dev.woody.ext.Bus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dev.woody.ext.Bus.mains.Map_popup;
import dev.woody.ext.incode2015.R;

/**
 * Created by kimsehyung on 2016-02-25.
 */
public class toolbar_frag extends Fragment {
    private Toolbar toolbar;
    private ImageView back, rabel, mapbtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toobar_frag, container, false);
        back = (ImageView)v.findViewById(R.id.backbtn_toolbar);
        rabel = (ImageView)v.findViewById(R.id.rabel_toolbar);
        mapbtn = (ImageView)v.findViewById(R.id.mapbtn_toolbar);

        Glide.with(this).load(R.drawable.backbtn).into(back);
        Glide.with(this).load(R.drawable.bus_actionbar_title).into(rabel);
        Glide.with(this).load(R.drawable.map_icon).into(mapbtn);

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        back.setOnClickListener(new View.OnClickListener() { //종료
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Map_popup.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
