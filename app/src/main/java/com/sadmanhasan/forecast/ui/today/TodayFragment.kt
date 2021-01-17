package com.sadmanhasan.forecast.ui.today

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.TodayModel
import kotlinx.android.synthetic.main.fragment_today.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

        Fuel.get("${Generic.baseUrl}weather?q=$city&APPID=${Generic.appId}")
            .responseObject(TodayModel.Deserializer()) { _, _, result ->
                val (todayModel, _) = result
                if (todayModel != null) {

                    Generic.setSharedPref(requireContext(), "lat", todayModel.coord.lat)
                    Generic.setSharedPref(requireContext(), "lon", todayModel.coord.lon)

                    val icon = todayModel.weather[0].icon

                    Glide.with(this)
                        .load("https://openweathermap.org/img/wn/$icon@4x.png")
                        .into(img_today_weather)

                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("EEEE hh:mm a, MMMM d, y")
                    val formatted = current.format(formatter)
                    today_date.text = formatted

                    today_temp.text = Generic.tempConvert(todayModel.main.temp)
                    today_weather.text = todayModel.weather[0].description

                    today_feels_like.text =
                        ("Feels like " + Generic.tempConvert(todayModel.main.feels_like))
                    today_maximum.text =
                        ("Max " + Generic.tempConvert(todayModel.main.temp_max))
                    today_minimum.text =
                        ("Min " + Generic.tempConvert(todayModel.main.temp_min))

                    today_sunrise.text = Generic.formatTime(todayModel.sys.sunrise)
                    today_sunset.text = Generic.formatTime(todayModel.sys.sunset)

                    val windDir =
                        arrayOf("↑N", "↗NE", "→E", "↘SE", "↓S", "↙SW", "←W", "↖NW")
                    val wind = ((todayModel.wind.deg / 45).toDouble().roundToInt() % 8)
                    today_wind.text = ((todayModel.wind.speed * 3.6).roundToInt()
                        .toString() + " km/h " + windDir[wind])

                    today_humidity.text = (todayModel.main.humidity + "%")
                    today_visibility.text =
                        ((todayModel.visibility / 1000).toString() + " km")
                    today_pressure.text =
                        ((todayModel.main.pressure / 10).toString() + " kPa")

                }
            }
    }


}