package dev.woody.ext.ViewPagerFragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dev.woody.ext.adapter.ViewPager_main_Adapter;
import dev.woody.ext.incode2015.R;

public class ViewPager_Main extends FragmentActivity {
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager__main2);
        viewPager = (ViewPager)findViewById(R.id.viewPager_m);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new ViewPager_main_Adapter(getSupportFragmentManager()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
