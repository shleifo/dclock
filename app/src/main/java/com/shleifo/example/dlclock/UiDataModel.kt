package com.shleifo.example.dlclock

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


class UiDataModel {

    enum class Tab(
        fragmentClass: Class<*>,
        @DrawableRes iconResId: Int,
        @StringRes labelResId: Int
    ) {

        ALARMS(
            AlarmClockFragment::class.java,
            R.drawable.ic_launcher_background,
            R.string.app_name
        ),
        CLOCK(ClockFragment::class.java, R.drawable.ic_launcher_background, R.string.app_name),
        TIMER(TimerFragment::class.java, R.drawable.ic_launcher_background, R.string.app_name),
        STOPWATCH(
            StopwatchFragment::class.java,
            R.drawable.ic_launcher_background,
            R.string.app_name
        )

    }



    /** The single instance of this data model that exists for the life of the application.  */
    val sUiDataModel = UiDataModel()

    fun getUiDataModel(): UiDataModel? {
        return sUiDataModel
    }

    val tabCount = Tab.values().size
}