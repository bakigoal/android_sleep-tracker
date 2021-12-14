package com.bakigoal.sleeptracker.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakigoal.sleeptracker.database.SleepDatabaseDao
import com.bakigoal.sleeptracker.database.SleepNight

class SleepDetailViewModel(
    sleepNightKey: Long = 0L,
    val database: SleepDatabaseDao
) : ViewModel() {

    private val _night = MediatorLiveData<SleepNight>()
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    val night: LiveData<SleepNight>
        get() = _night
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker


    init {
        _night.addSource(database.getNightWithId(sleepNightKey), _night::setValue)
    }

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }
}