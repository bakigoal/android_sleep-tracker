package com.bakigoal.sleeptracker.sleeptracker.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bakigoal.sleeptracker.R
import com.bakigoal.sleeptracker.convertDurationToString
import com.bakigoal.sleeptracker.convertNumericQualityToString
import com.bakigoal.sleeptracker.database.SleepNight
import com.bakigoal.sleeptracker.databinding.ListItemSleepNightBinding

class ViewHolder private constructor(val binding: ListItemSleepNightBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val sleepLength: TextView = binding.sleepLength
    private val quality: TextView = binding.qualityString
    private val qualityImage: ImageView = binding.qualityImage

    fun bind(item: SleepNight) {
        val res = binding.root.resources
        sleepLength.text = convertDurationToString(item.startTimeMilli, item.endTimeMilli, res)
        quality.text = convertNumericQualityToString(item.sleepQuality, res)
        qualityImage.setImageResource(getImage(item.sleepQuality))
    }

    private fun getImage(sleepQuality: Int) = when (sleepQuality) {
        0 -> R.drawable.ic_sleep_0
        1 -> R.drawable.ic_sleep_1
        2 -> R.drawable.ic_sleep_2
        3 -> R.drawable.ic_sleep_3
        4 -> R.drawable.ic_sleep_4
        5 -> R.drawable.ic_sleep_5
        else -> R.drawable.ic_sleep_active
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}