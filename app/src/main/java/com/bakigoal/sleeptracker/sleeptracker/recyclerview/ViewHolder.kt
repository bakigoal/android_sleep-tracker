package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bakigoal.sleeptracker.database.SleepNight
import com.bakigoal.sleeptracker.databinding.ListItemSleepNightBinding

class ViewHolder private constructor(val binding: ListItemSleepNightBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SleepNight) {
        binding.sleep = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}