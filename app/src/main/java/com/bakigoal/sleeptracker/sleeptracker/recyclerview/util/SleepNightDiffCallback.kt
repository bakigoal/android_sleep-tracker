package com.bakigoal.sleeptracker.sleeptracker.recyclerview.util

import androidx.recyclerview.widget.DiffUtil
import com.bakigoal.sleeptracker.sleeptracker.recyclerview.DataItem

class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem) =
        oldItem == newItem
}