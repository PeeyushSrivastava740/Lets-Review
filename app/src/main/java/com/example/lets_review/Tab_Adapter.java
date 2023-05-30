package com.example.lets_review;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Tab_Adapter extends FragmentPagerAdapter {
    int No_Of_Tab;

    public Tab_Adapter(FragmentManager fm, int tabCount) {
        super(fm);

        this.No_Of_Tab = tabCount;

    }

    @Override
    public Fragment getItem(int i) {
        return  null;
    }

    @Override
    public int getCount() {
        return No_Of_Tab;
    }
}
