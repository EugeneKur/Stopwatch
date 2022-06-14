package com.geekbrains.stopwatch.helpers.extension

fun Long.toStringTime(): String {
    val millisecondsFormatted = (this % 1000).pad(3)
    val seconds = this / 1000
    val secondsFormatted = (seconds % 60).pad(2)
    val minutes = seconds / 60
    val minutesFormatted = (minutes % 60).pad(2)
    val hours = minutes / 60
    return if (hours > 0) {
        val hoursFormatted = (minutes / 60).pad(2)
        "$hoursFormatted:$minutesFormatted:$secondsFormatted"
    } else {
        "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
    }
}
fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')


const val DEFAULT_MS_FORMATTED = "00:00:000"