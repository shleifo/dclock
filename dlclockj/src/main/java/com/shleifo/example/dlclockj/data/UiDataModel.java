package com.shleifo.example.dlclockj.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.shleifo.example.dlclockj.alarm.AlarmClockFragment;
import com.shleifo.example.dlclockj.clock.ClockFragment;
import com.shleifo.example.dlclockj.R;
import com.shleifo.example.dlclockj.stopwatch.StopwatchFragment;
import com.shleifo.example.dlclockj.timer.TimerFragment;

public class UiDataModel {

    public enum Tab {
        ALARMS(AlarmClockFragment.class, R.drawable.ic_tab_alarm, R.string.menu_alarm),
        CLOCKS(ClockFragment.class, R.drawable.ic_tab_clock, R.string.menu_clock),
        TIMERS(TimerFragment.class, R.drawable.ic_tab_timer, R.string.menu_timer),
        STOPWATCH(StopwatchFragment.class, R.drawable.ic_tab_stopwatch, R.string.menu_stopwatch);

        private String fragmentClassName;
        private @DrawableRes int iconResId;
        private @StringRes int labelResId;

        Tab(Class fragmentClass, int iconResId, int labelResId) {
            this.fragmentClassName = fragmentClass.getName();
            this.iconResId = iconResId;
            this.labelResId = labelResId;
        }

        public String getFragmentClassName() {
            return fragmentClassName;
        }

        public int getIconResId() {
            return iconResId;
        }

        public int getLabelResId() {
            return labelResId;
        }
    }

    static class TabModel {

    }

    /** The single instance of this data model that exists for the life of the application. */
    private static final UiDataModel sUiDataModel = new UiDataModel();

    public static UiDataModel instance() {
        return sUiDataModel;
    }

    public Tab tabAt(int ordinal) {
        return Tab.values()[ordinal];
    }

    public int tabCount() {
        return Tab.values().length;
    }
}
