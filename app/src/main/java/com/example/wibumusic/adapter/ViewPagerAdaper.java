package com.example.wibumusic.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.wibumusic.fragment.HotFragment;
import com.example.wibumusic.fragment.NewFragment;

public class ViewPagerAdaper extends FragmentStatePagerAdapter {


    public ViewPagerAdaper(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ;
                return new NewFragment();
            case 1:
                ;
                return new HotFragment();
            default:
                return new NewFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "New";
                break;
            case 1:
                title = "Hot";
                break;

        }
        return title;
    }
}
