package com.sadmanhasan.forecast.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class TodayModel(
    val weather: List<Weather>,
    val main: Main,
    val visibility: String,
    val wind: Wind,
    val clouds: Clouds,
    val dt: String,
    val sys: Sys,
    val name: String
) {
    class Deserializer : ResponseDeserializable<TodayModel> {
        override fun deserialize(content: String): TodayModel =
            Gson().fromJson(content, TodayModel::class.java)
    }
}


data class Weather(
    val main: String,
    val icon: String
)

data class Main(
    val temp: String,
    val feelsLike: String,
    val tempMin: String,
    val tempMax: String,
    val pressure: String,
    val humidity: String
)

data class Wind(
    val speed: String,
    val deg: String
)

data class Clouds(
    val all: Long
)

data class Sys(
    val country: String,
    val sunrise: String,
    val sunset: String
)
