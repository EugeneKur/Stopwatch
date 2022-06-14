package com.gb.stopwatch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory (private val stopwatchStateHolder: StopwatchStateHolder): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(stopwatchStateHolder = stopwatchStateHolder) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}