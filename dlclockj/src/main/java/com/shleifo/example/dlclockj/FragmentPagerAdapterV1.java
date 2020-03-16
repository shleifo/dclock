package com.shleifo.example.dlclockj;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.shleifo.example.dlclockj.data.UiDataModel;

import java.util.Map;

public class FragmentPagerAdapterV1 extends PagerAdapter {

    private final Context mContext;

    /**
     * The manager into which fragments are added.
     */
    private final FragmentManager mFragmentManager;

    /**
     * A fragment cache that can be accessed before {@link #instantiateItem} is called.
     */
    private final Map<UiDataModel.Tab, DeskClockFragment> mFragmentCache;

    /** The active fragment transaction if one exists. */
    private FragmentTransaction mCurrentTransaction;

    public FragmentPagerAdapterV1(MainActivity activity) {
        mContext = activity;
        mFragmentCache = new ArrayMap<>(getCount());
        this.mFragmentManager = activity.getSupportFragmentManager();
    }

    /**
     * 三步获取fragment实例
     * 1. 取缓存
     * 2. 查找tag，if exist 保存到缓存中
     * 3. 实例化新对象，保存到缓存中
     * @param position where the fragment from
     * @return fragment
     */
    private Fragment getDeskClockFragment(int position) {
        // 获取tab
        final UiDataModel.Tab tab = UiDataModel.instance().tabAt(position);

        // 先在本地缓存中获取看有没有该fragment
        DeskClockFragment fragment = mFragmentCache.get(tab);
        if (fragment != null) {
            return fragment;
        }

        // Next check the fragment manager;
        // 当App根据系统配置改变重新建立时， 当前Adapter就是一个新的Adapter并且导致mFragmentCache为空
        // 但是fragment manager则会根据原始的Application启动时保留Fragments.
        fragment = (DeskClockFragment) mFragmentManager.findFragmentByTag(tab.name());
        if (fragment != null) {
            mFragmentCache.put(tab, fragment);
            return fragment;
        }

        // Otherwise, build the fragment from scratch. 从头开始构建fragment
        final String fragmentClassName = tab.getFragmentClassName();
        fragment = (DeskClockFragment) Fragment.instantiate(mContext, fragmentClassName);
        mFragmentCache.put(tab, fragment);
        return fragment;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        if (container.getId() == View.NO_ID) {
            throw new IllegalStateException("ViewPager with adapter " + this + " has no id");
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        if (mCurrentTransaction == null) {
            mCurrentTransaction = mFragmentManager.beginTransaction();
        }

        final UiDataModel.Tab tab = UiDataModel.instance().tabAt(position);
        // 为什么这里不像getDeskClockFragment()一样直接先从缓存中取？
        // 这个就要问instantiateItem调用的时机是什么？
        // 当滑动到该界面创建该界面和相邻界面的对象，当滑出可
        // findFragmentByTag最终也是通过缓存去取，这是系统级别的缓存，比较稳定
        Fragment fragment = mFragmentManager.findFragmentByTag(tab.name());
        if (fragment != null) {
            mCurrentTransaction.attach(fragment);
        } else {
            fragment = getDeskClockFragment(position);
            mCurrentTransaction.add(container.getId(), fragment, tab.name());
        }
        return fragment;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurrentTransaction != null) {
            mCurrentTransaction.commitAllowingStateLoss();
            mCurrentTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

        @Override
    public int getCount() {
        return UiDataModel.instance().tabCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (mCurrentTransaction == null) {
            mCurrentTransaction = mFragmentManager.beginTransaction();
        }
        final DeskClockFragment fragment = (DeskClockFragment) object;
        mCurrentTransaction.detach(fragment);
    }
}
