package com.sadmanhasan.forecast.ui.weekly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.model.Daily
import com.sadmanhasan.forecast.model.WeeklyModel

class WeeklyRepository() {

    private val _weeklyForecast = MutableLiveData<List<Daily>>()
    val weeklyForecast: LiveData<List<Daily>> = _weeklyForecast

    fun getWeeklyData(lat: String, lon: String) {

        Fuel.get("${Generic.baseUrl}onecall?lat=$lat&lon=$lon&exclude=current,minutely,hourly&APPID=${Generic.appId}")
                .responseObject(WeeklyModel.Deserializer()) { _, _, result ->
                    val (weeklyModel, _) = result
                    if (weeklyModel != null) {
                        /* Dropping today's(first) weather data, as we need next 7 days data */
                        val data = weeklyModel.daily.drop(1)
                        _weeklyForecast.value = data
                    }
                }
    }
}


