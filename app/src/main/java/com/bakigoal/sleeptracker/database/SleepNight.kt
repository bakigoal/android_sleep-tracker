package com.bakigoal.sleeptracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(

    @PrimaryKey(autoGenerate = true)
    val nightId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    val endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "quality_rating")
    val sleepQuality: Int = -1
)