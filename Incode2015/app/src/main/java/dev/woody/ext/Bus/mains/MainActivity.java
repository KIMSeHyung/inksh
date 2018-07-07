package dev.woody.ext.Bus.mains;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import java.util.ArrayList;

import dev.woody.ext.Bus.fragments.dy_frag;
import dev.woody.ext.Bus.fragments.toolbar_frag;
import dev.woody.ext.Bus.fragments.yd_frag;
import dev.woody.ext.incode2015.R;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm;
    private ArrayList<Fragment> fragmentsArray;
    private TabHost tabHost;
    private FloatingActionButton fab;

    private int DY = 0; //대연
    private int YD = 1; //용당
    private int TB = 2; //

    public static int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeTable.class);
                startActivity(intent);
            }
        });
        //////////////////////////////
        //프래그먼트 셋업
        fragmentsArray = new ArrayList<Fragment>();
        fragmentsArray = setFragments();

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.toolbar_place, fragmentsArray.get(TB)).commit();
        fm.beginTransaction().replace(R.id.fragment_place, fragmentsArray.get(DY)).commit();

        //////////////////////////////
        //탭 셋업
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        setTabUp(tabHost.getContext(), "tab1", R.drawable.dtoy, R.id.tab1);
        setTabUp(tabHost.getContext(), "tab2", R.drawable.ytod, R.id.tab2);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i("tab", tabId);
                if (tabId.equals("tab1")) {
                    fm.beginTransaction().replace(R.id.fragment_place, fragmentsArray.get(DY)).commit();
                    flag = 0;
                    //tabHost.getCurrentTabView().findViewById(R.id.tabs_img).setAlpha(1);
                    tabHost.getTabWidget().getChildTabViewAt(0).findViewById(R.id.tabs_img).setAlpha(1);
                    tabHost.getTabWidget().getChildTabViewAt(1).findViewById(R.id.tabs_img).setAlpha(0.5f);
                } else {
                    fm.beginTransaction().replace(R.id.fragment_place, fragmentsArray.get(YD)).commit();
                    flag = 1;
                    tabHost.getTabWidget().getChildTabViewAt(0).findViewById(R.id.tabs_img).setAlpha(0.5f);
                    tabHost.getTabWidget().getChildTabViewAt(1).findViewById(R.id.tabs_img).setAlpha(1);
                }
            }
        });

    }

    private ArrayList<Fragment> setFragments(){
        ArrayList<Fragment> arry = new ArrayList<Fragment>();
        arry.add(new dy_frag());
        arry.add(new yd_frag());
        arry.add(new toolbar_frag());
        return arry;
    }
    public void setTabUp(Context c, String tag, int img, int tab){
        View view = LayoutInflater.from(c).inflate(R.layout.tabwidget_item, null);
        ImageView tabimg = (ImageView)view.findViewById(R.id.tabs_img);
        if(tag.equals("tab2")) tabimg.setAlpha(0.5f);
        tabimg.setImageResource(img);

        TabHost.TabSpec spec = tabHost.newTabSpec(tag);
        spec.setContent(tab);
        spec.setIndicator(view);
        tabHost.addTab(spec);
    }

}
