package com.sadmanhasan.forecast.ui.today

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.TodayModel
import kotlinx.android.synthetic.main.fragment_today.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


class TodayFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search.setOnClickListener {
            val city: String = edit_city_name.text.toString()
            getCurrentWeather(city)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentWeather(city: String) {
        Fuel.get("https://api.openweathermap.org/data/2.5/weather?q=$city&APPID=9cd7f16ef1ce56b903a9fdd168e7952f")
            .responseObject(TodayModel.Deserializer()) { _, _, result ->
                val (currentWeatherModel, _) = result
                if (currentWeatherModel != null) {
                    val icon = currentWeatherModel.weather[0].icon
                    println(icon)
                    Glide.with(this)
                        .load("https://openweathermap.org/img/wn/$icon@4x.png")
                        .into(img_today_weather)

                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("EEEE hh:mm a, MMMM d, y")
                    val formatted = current.format(formatter)
                    today_date.text = formatted


                    today_temp.text = ((currentWeatherModel.main.temp - 273.15).roundToInt().toString() + " °C")
                    today_weather.text = currentWeatherModel.weather[0].description

                    today_feels_like.text =
                        ("Feels like " + (currentWeatherModel.main.feels_like - 273.15).roundToInt().toString() + " °C")
                    today_maximum.text =
                        ("Max " + (currentWeatherModel.main.temp_max - 273.15).roundToInt().toString() + " °C")
                    today_minimum.text =
                        ("Min " + (currentWeatherModel.main.temp_min - 273.15).roundToInt().toString() + " °C")

                    today_sunrise.text = Generic.formatTime(currentWeatherModel.sys.sunrise)
                    today_sunset.text = Generic.formatTime(currentWeatherModel.sys.sunset)

                    val windDir =
                        arrayOf("↑N", "↗NE", "→E", "↘SE", "↓S", "↙SW", "←W", "↖NW")
                    val wind = ((currentWeatherModel.wind.deg / 45).toDouble().roundToInt() % 8)
                    today_wind.text = ((currentWeatherModel.wind.speed * 3.6).roundToInt()
                        .toString() + " kph " + windDir[wind])

                    today_humidity.text = (currentWeatherModel.main.humidity + "%")
                    today_visibility.text =
                        ((currentWeatherModel.visibility / 1000).toString() + " km")
                    today_pressure.text =
                        ((currentWeatherModel.main.pressure / 10).toString() + " kPa")

                }
            }
    }


}