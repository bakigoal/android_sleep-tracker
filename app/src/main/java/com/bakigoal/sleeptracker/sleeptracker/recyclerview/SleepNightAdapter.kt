package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bakigoal.sleeptracker.database.SleepNight

class SleepNightAdapter : ListAdapter<SleepNight, SleepNightViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SleepNightViewHolder.from(parent)

    override fun onBindViewHolder(holder: SleepNightViewHolder, position: Int) = holder.bind(getItem(position))

}