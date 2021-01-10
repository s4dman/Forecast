package com.sadmanhasan.forecast.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
                    println(currentWeatherModel.name)
                    println(currentWeatherModel.weather[0].main)
                }
            }
    }


}