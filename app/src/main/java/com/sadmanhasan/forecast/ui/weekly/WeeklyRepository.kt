package com.sadmanhasan.forecast.ui.weekly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.model.Daily
import com.sadmanhasan.forecast.model.WeeklyModel

class WeeklyRepository() {

    fun getWeeklyData(lat: String, lon: String, viewModelCallBack: (List<Daily>) -> Unit) {

        Fuel.get("${Generic.baseUrl}onecall?lat=$lat&lon=$lon&exclude=current,minutely,hourly&APPID=${Generic.appId}")
                .responseObject(WeeklyModel.Deserializer()) { _, _, result ->
                    val (weeklyModel, _) = result
                    if (weeklyModel != null) {
                        /* Dropping today's(first) weather data, as we need next 7 days data */
                        val data = weeklyModel.daily.drop(1)
                        viewModelCallBack(data)
                    }
                }
    }
}


