package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import com.bakigoal.sleeptracker.database.SleepNight

sealed class DataItem {

    data class SleepNightItem(val sleepNight: SleepNight) : DataItem() {
        override val id = sleepNight.nightId
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}