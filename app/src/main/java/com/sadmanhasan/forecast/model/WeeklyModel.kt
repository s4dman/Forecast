package com.sadmanhasan.forecast.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class WeeklyModel(
    val daily: List<Daily>,
) {
    class Deserializer : ResponseDeserializable<WeeklyModel> {
        override fun deserialize(content: String): WeeklyModel =
            Gson().fromJson(content, WeeklyModel::class.java)
    }
}

data class Daily(
    val dt: String,
    val temp: Temp,
    val weather: List<WeeklyWeather>,
    val pressure: String,
    val humidity: String,
    val wind_speed: String,
    val pop: String,
    var expandable: Boolean = false
)

data class Temp(
    val min: Double,
    val max: Double
)

data class WeeklyWeather(
    val main: String,
    val icon: String
)
