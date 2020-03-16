package com.shleifo.example.dlclockj;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.shleifo.example.dlclockj.data.UiDataModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout mTabLayout = findViewById(R.id.tabs);

        final FragmentPagerAdapterV1 mPagerAdapter = new FragmentPagerAdapterV1(this);
        final ViewPager mViewPager = findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAccessibilityDelegate(null);

        mViewPager.setAdapter(mPagerAdapter);

        // 设置ViewPager会导致TabIcon不显示
        // TabLayout的populateFromPagerAdapter()方法中中会调用removeAllTabs()方法，
        // 然后重新创建不含Icon的tab,
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.removeAllTabs();
        final int tabCount = UiDataModel.instance().tabCount();
        for (int i = 0; i < tabCount; i++) {
            final UiDataModel.Tab tabModel = UiDataModel.instance().tabAt(i);
            final TabLayout.Tab tab = mTabLayout.newTab()
                    .setTag(tabModel)
                    .setIcon(tabModel.getIconResId())
                    .setText(tabModel.getLabelResId())
                    .setContentDescription(tabModel.getLabelResId());
            mTabLayout.addTab(tab);
        }
    }

}
