package com.bakigoal.sleeptracker.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bakigoal.sleeptracker.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<ViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

}