package com.sadmanhasan.forecast.ui.today

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.MainActivity
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.TodayModel
import kotlinx.android.synthetic.main.fragment_today.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


class TodayFragment : Fragment() {

    private val todayRepository = TodayRepository()

    companion object {
        var cityName: String = ""
    }

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

        cityName = Generic.getSharedPref(requireContext(), "city_name")

        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = cityName
        }

        todayRepository.getCurrentWeather(cityName)
        todayRepository.todayForecast.observe(viewLifecycleOwner, Observer {
            parseData(it)
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseData(todayModel: TodayModel) {

        Generic.setSharedPref(requireContext(), "lat", todayModel.coord.lat)
        Generic.setSharedPref(requireContext(), "lon", todayModel.coord.lon)

        val icon = todayModel.weather[0].icon

        Glide.with(this)
                .load("https://openweathermap.org/img/wn/$icon@4x.png")
                .into(img_today_weather)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, d MMM ")
        val formatted = current.format(formatter)
        text_today_date.text = formatted

        text_today_temp.text = Generic.tempConvert(todayModel.main.temp)
        text_today_weather.text = todayModel.weather[0].description.capitalize(Locale.ROOT)

        text_today_feels_like.text =
                ("Feels like " + Generic.tempConvert(todayModel.main.feels_like))
        text_today_maxmin.text =
                (Generic.tempConvert(todayModel.main.temp_max) + " / " + Generic.tempConvert(
                        todayModel.main.temp_min
                ))

        text_today_sunrise.text = Generic.formatTime(todayModel.sys.sunrise)
        text_today_sunset.text = Generic.formatTime(todayModel.sys.sunset)

        val windDir =
                arrayOf("↑N", "↗NE", "→E", "↘SE", "↓S", "↙SW", "←W", "↖NW")
        val wind = ((todayModel.wind.deg / 45).toDouble().roundToInt() % 8)
        text_today_wind.text = ((todayModel.wind.speed * 3.6).roundToInt()
                .toString() + " km/h " + windDir[wind])

        text_today_humidity.text = (todayModel.main.humidity + "%")
        text_today_visibility.text =
                ((todayModel.visibility / 1000).toString() + " km")
        text_today_pressure.text =
                ((todayModel.main.pressure / 10).toString() + " kPa")

    }
}