package com.example.fitnessapp.Adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ViewPagerAdapter extends FragmentPagerAdapter {

    @NonNull
    ArrayList<Fragment> fragments = new ArrayList<>();
    @NonNull
    ArrayList<String> tabTitles = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragments(Fragment fragments, String tabTitles) {
        this.fragments.add(fragments);
        this.tabTitles.add(tabTitles);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}