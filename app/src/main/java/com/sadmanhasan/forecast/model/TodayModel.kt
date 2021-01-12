package com.sadmanhasan.forecast.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class TodayModel(
    val coord: Coord,
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
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


data class Coord(
    val lon: String,
    val lat: String
)

data class Weather(
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Float,
    val humidity: String
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Clouds(
    val all: Long
)

data class Sys(
    val country: String,
    val sunrise: String,
    val sunset: String
)
