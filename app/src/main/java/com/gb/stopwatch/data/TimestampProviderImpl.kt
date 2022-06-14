package com.gb.stopwatch.data

import com.gb.stopwatch.domain.TimestampProvider

class TimestampProviderImpl: TimestampProvider {

    override fun getMilliseconds(): Long = System.currentTimeMillis()
}