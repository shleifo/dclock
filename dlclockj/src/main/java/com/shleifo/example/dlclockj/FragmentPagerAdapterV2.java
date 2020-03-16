package com.shleifo.example.dlclockj;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.shleifo.example.dlclockj.data.UiDataModel;

public class FragmentPagerAdapterV2 extends FragmentPagerAdapter {

    public FragmentPagerAdapterV2(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return UiDataModel.instance().tabCount();
    }
}
