package com.sadmanhasan.forecast.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.TodayModel
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.fragment_today.*

class TodayFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search.setOnClickListener {
            val city: String = edit_city_name.text.toString()
            getCurrentWeather(city)

        }
    }

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

                    today_date.text = currentWeatherModel.dt

                    today_temp.text = currentWeatherModel.main.temp
                    today_weather.text = currentWeatherModel.weather[0].main

                    today_feels_like.text = currentWeatherModel.main.feels_like
                    today_maximum.text = currentWeatherModel.main.temp_max
                    today_minimum.text = currentWeatherModel.main.temp_min

                    today_sunrise.text = currentWeatherModel.sys.sunrise
                    today_sunset.text = currentWeatherModel.sys.sunset
                    today_wind.text = currentWeatherModel.wind.speed
                    today_humidity.text = currentWeatherModel.main.humidity
                    today_visibility.text = currentWeatherModel.visibility
                    today_pressure.text = currentWeatherModel.main.pressure

                }
            }
    }


}