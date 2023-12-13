package com.sadmanhasan.forecast

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class Generic {

    companion object {

        const val baseUrl: String = "https://api.openweathermap.org/data/2.5/"
        const val appId: String = "9cd7f16ef1ce56b903a9fdd168e7952f"
        const val PERMISSION_REQUEST_ACCESS_LOCATION = 100

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatTime(dt: Long, pattern: String): String {

            val daTime = java.time.format.DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(dt)).toString()
            return OffsetDateTime.parse(daTime)
                .format(DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH));
        }

        fun formatDate(dt: String): String {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("E, dd MMM ", Locale.getDefault())
            val currTimeZone = Date(dt.toLong() * 1000)
            return sdf.format(currTimeZone)
        }

        fun tempConvert(temp: Double): String {
            return (temp - 273.15).roundToInt().toString() + "°C"
        }

        fun setSharedPref(context: Context?, key: String, value: String) {
            context?.getSharedPreferences(R.string.PREF_NAME.toString(), Context.MODE_PRIVATE)
                ?.edit()?.putString(key, value)?.apply()
        }

        fun getSharedPref(context: Context, key: String): String {
            return context.getSharedPreferences(R.string.PREF_NAME.toString(), Context.MODE_PRIVATE)
                .getString(key, "")!!
        }
    }
}