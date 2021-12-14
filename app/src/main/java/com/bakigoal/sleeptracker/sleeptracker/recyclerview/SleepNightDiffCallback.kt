package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.bakigoal.sleeptracker.database.SleepNight

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {

    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight) =
        oldItem.nightId == newItem.nightId

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight) =
        oldItem == newItem
}