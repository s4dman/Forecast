package com.sadmanhasan.forecast.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class WeeklyModel(
    var daily: List<Daily>
) {
    class Deserializer : ResponseDeserializable<WeeklyModel> {
        override fun deserialize(content: String): WeeklyModel =
            Gson().fromJson(content, WeeklyModel::class.java)
    }
}

data class Daily(
    var dt: String,
    var temp: Temp,
    var weather: List<WeeklyWeather>
)

data class Temp(
    var min: Double,
    var max: Double
)

data class WeeklyWeather(
    val main: String,
    val icon: String
)
