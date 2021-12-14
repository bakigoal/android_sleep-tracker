package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import com.bakigoal.sleeptracker.database.SleepNight

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {

    fun onClick(night: SleepNight) = clickListener(night.nightId)
}