package com.bakigoal.sleeptracker.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bakigoal.sleeptracker.database.SleepDatabaseDao
import com.bakigoal.sleeptracker.database.SleepNight
import com.bakigoal.sleeptracker.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var tonight = MutableLiveData<SleepNight?>()
    private val _nights = database.getAllNights()
    private val _showOnSnackbarEvent = MutableLiveData<Boolean>()
    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    private val _navigateToSleepDetail = MutableLiveData<Long>()

    val nights
        get() = _nights
    val navigateToSleepQuality
        get() = _navigateToSleepQuality
    val navigateToSleepDetail
        get() = _navigateToSleepDetail
    val startVisible
        get() = Transformations.map(tonight) { it == null }
    val stopVisible
        get() = Transformations.map(startVisible) { !it }
    val clearVisible
        get() = Transformations.map(_nights) { it.isNotEmpty() }
    val showOnSnackbarEvent
        get() = _showOnSnackbarEvent

    init {
        initTonight()
    }

    private fun initTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    fun onStopTracking() {
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            _navigateToSleepQuality.value = oldNight
            update(oldNight)
        }
    }

    fun onClearData() {
        uiScope.launch {
            clearData()
            tonight.value = null
            _showOnSnackbarEvent.value = true
        }
    }

    fun onSleepNightClicked(nightId: Long) {
        _navigateToSleepDetail.value = nightId
    }

    fun doneNavigatingToSleepQuality() {
        _navigateToSleepQuality.value = null
    }

    fun doneNavigatingToSleepDetail() {
        _navigateToSleepDetail.value = null
    }

    fun doneShowingSnackbar() {
        _showOnSnackbarEvent.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // --- coroutine suspended long-running IO functions ---

    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = database.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
    }

    private suspend fun insert(newNight: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(newNight)
        }
    }

    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    private suspend fun clearData() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private fun nightsToString(it: List<SleepNight>) =
        formatNights(it, getApplication<Application>().resources)

}

