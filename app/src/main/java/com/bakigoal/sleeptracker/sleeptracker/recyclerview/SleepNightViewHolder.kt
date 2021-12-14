package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bakigoal.sleeptracker.database.SleepNight
import com.bakigoal.sleeptracker.databinding.ListItemSleepNightBinding

class SleepNightViewHolder private constructor(val binding: ListItemSleepNightBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SleepNight, clickListener: SleepNightListener) {
        binding.sleep = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): SleepNightViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
            return SleepNightViewHolder(binding)
        }
    }
}