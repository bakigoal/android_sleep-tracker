package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.bakigoal.sleeptracker.database.SleepNight

class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem) =
        oldItem == newItem
}