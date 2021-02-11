package com.sadmanhasan.forecast.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class HourlyModel(
        val hourly: List<Hourly>
) {
    class Deserializer : ResponseDeserializable<HourlyModel> {
        override fun deserialize(content: String): HourlyModel =
                Gson().fromJson(content, HourlyModel::class.java)
    }
}

data class Hourly(
        val dt: String,
        val temp: Double,
        val weather: List<HourlyWeather>,
)

data class HourlyWeather(
        val icon: String
)