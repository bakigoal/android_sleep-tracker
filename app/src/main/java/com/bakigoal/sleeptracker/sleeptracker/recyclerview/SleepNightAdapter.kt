package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bakigoal.sleeptracker.database.SleepNight

class SleepNightAdapter : ListAdapter<SleepNight, ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

}