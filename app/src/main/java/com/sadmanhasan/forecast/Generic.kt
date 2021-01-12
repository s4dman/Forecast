package com.sadmanhasan.forecast

import java.text.SimpleDateFormat
import java.util.*

class Generic {

    companion object {
        fun formatTime(dt: String): String {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val currTimeZone = Date(dt.toLong() * 1000)
            return sdf.format(currTimeZone)
        }
    }
}