package com.gb.stopwatch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.stopwatch.helpers.extension.DEFAULT_MS_FORMATTED
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val stopwatchStateHolder: StopwatchStateHolder,
    private val liveData: MutableLiveData<Pair<Int, String>> = MutableLiveData()
) : ViewModel() {

    companion object {
        const val UPDATE_TIMER = 20L
    }

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val jobs: MutableMap<Int, Job?> = mutableMapOf()

    fun getLiveData(): LiveData<Pair<Int, String>> = liveData

    fun start(number: Int) {
        if (!jobs.containsKey(number) || jobs[number] == null) startJob(number)
        stopwatchStateHolder.start(number)
    }

    private fun startJob(number: Int) {
        jobs[number] = scope.launch {
            while (isActive) {
                stopwatchStateHolder.getStringTimeRepresent(number)
                    .collect {
                        liveData.value = Pair(number, it)
                        delay(UPDATE_TIMER)
                    }
            }
        }
    }

    fun pause(number: Int) {
        stopwatchStateHolder.pause(number)
        stopJob(number)
    }

    fun stop(number: Int) {
        stopwatchStateHolder.stop(number)
        stopJob(number)
        clearValue(number)
    }

    private fun stopJob(number: Int) {
        jobs[number]?.cancel()
        jobs[number] = null
    }

    private fun clearValue(number: Int) {
        liveData.value = Pair(number, DEFAULT_MS_FORMATTED)
    }
}