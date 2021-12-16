package com.bakigoal.sleeptracker.sleeptracker.recyclerview.util

import androidx.recyclerview.widget.DiffUtil
import com.bakigoal.sleeptracker.sleeptracker.recyclerview.SleepNightAdapter

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNightAdapter.DataItem>() {

    override fun areItemsTheSame(oldItem: SleepNightAdapter.DataItem, newItem: SleepNightAdapter.DataItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SleepNightAdapter.DataItem, newItem: SleepNightAdapter.DataItem) =
        oldItem == newItem
}