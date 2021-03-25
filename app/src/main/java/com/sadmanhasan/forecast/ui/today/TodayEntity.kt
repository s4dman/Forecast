package com.sadmanhasan.forecast.ui.today

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_table")
data class TodayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val lon: String,
    val lat: String,
    val description: String,
    val icon: String,
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Float,
    val humidity: String,
    val visibility: Int? = null,
    val speed: Double,
    val deg: Int,
    val all: Long,
    val dt: Long? = null,
    val country: String? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val timezone: Long? = null,
    val name: String? = null
)
