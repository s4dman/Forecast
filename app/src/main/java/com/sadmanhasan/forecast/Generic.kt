package com.sadmanhasan.forecast

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class Generic {

    companion object {

        const val baseUrl: String = "https://api.openweathermap.org/data/2.5/"
        const val appId: String = "9cd7f16ef1ce56b903a9fdd168e7952f"

        fun formatTime(dt: String): String {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val currTimeZone = Date(dt.toLong() * 1000)
            return sdf.format(currTimeZone)
        }

        fun tempConvert(temp: Double): String {
            return (temp - 273.15).roundToInt().toString() + " °C"
        }
    }
}