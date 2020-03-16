package com.shleifo.example.dlclock;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class UiData {
    /** Identifies each of the primary tabs within the application. */
    public enum Tab {
        ALARMS(AlarmClockFragment.class, R.drawable.ic_launcher_foreground, R.string.app_name),
        CLOCKS(ClockFragment.class, R.drawable.ic_launcher_foreground, R.string.app_name),
        TIMERS(TimerFragment.class, R.drawable.ic_launcher_foreground, R.string.app_name),
        STOPWATCH(StopwatchFragment.class, R.drawable.ic_launcher_foreground, R.string.app_name);

        private final String mFragmentClassName;
        private final @DrawableRes
        int mIconResId;
        private final @StringRes
        int mLabelResId;

        Tab(Class fragmentClass, @DrawableRes int iconResId, @StringRes int labelResId) {
            mFragmentClassName = fragmentClass.getName();
            mIconResId = iconResId;
            mLabelResId = labelResId;
        }

        public String getFragmentClassName() { return mFragmentClassName; }
        public @DrawableRes int getIconResId() { return mIconResId; }
        public @StringRes int getLabelResId() { return mLabelResId; }
    }

    /** The single instance of this data model that exists for the life of the application. */
    private static final UiDataModel sUiDataModel = new UiDataModel();
    public static UiDataModel getUiDataModel() {
        return sUiDataModel;
    }

}
