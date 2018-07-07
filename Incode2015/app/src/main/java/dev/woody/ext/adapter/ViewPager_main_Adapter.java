package dev.woody.ext.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pools;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dev.woody.ext.ViewPagerFragment.vFrg_1;
import dev.woody.ext.ViewPagerFragment.vFrg_2;
import dev.woody.ext.ViewPagerFragment.vFrg_3;

/**
 * Created by kimsehyung on 2016-02-23.
 */
public class ViewPager_main_Adapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;

    public ViewPager_main_Adapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new vFrg_1());
        fragmentList.add(new vFrg_2());
        fragmentList.add(new vFrg_3());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
