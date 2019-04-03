package com.source.mmt.neighbourhood.customcalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.List;


public class CalendarPagerAdapter extends FragmentStatePagerAdapter {

    private List<CalendarFragment> fragments;
    private View updatableView;

    public CalendarPagerAdapter(FragmentManager fragmentManager, List<CalendarFragment> fragments){

        super(fragmentManager);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }
}
