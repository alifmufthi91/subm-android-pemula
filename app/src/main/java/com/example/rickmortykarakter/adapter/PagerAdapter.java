package com.example.rickmortykarakter.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rickmortykarakter.pages.About;
import com.example.rickmortykarakter.pages.Home;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Home();
            case 1: return new About();
        }
        return null;
    }

    @Override

    public int getCount() {
        return 2;
    }

    @Override

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Home";
            case 1: return "About";
            default: return null;
        }
    }
}

