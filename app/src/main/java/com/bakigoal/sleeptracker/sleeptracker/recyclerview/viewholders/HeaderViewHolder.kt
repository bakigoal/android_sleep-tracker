package com.bakigoal.sleeptracker.sleeptracker.recyclerview.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bakigoal.sleeptracker.R

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}
