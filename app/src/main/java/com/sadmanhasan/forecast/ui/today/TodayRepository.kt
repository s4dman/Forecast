package com.sadmanhasan.forecast.ui.today

import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.model.Hourly
import com.sadmanhasan.forecast.model.HourlyModel
import com.sadmanhasan.forecast.model.TodayModel

class TodayRepository {

    fun getCurrentWeather(city: String, todayModelCallBack: (TodayModel) -> Unit) {
        Fuel.get("${Generic.baseUrl}weather?q=$city&APPID=${Generic.appId}")
            .responseObject(TodayModel.Deserializer()) { _, _, result ->
                val (todayModel, error) = result
                if (todayModel != null) {
                    todayModelCallBack(todayModel)
                } else println(error?.message)
            }
    }

    fun getHourlyData(lat: String, lon: String, hourlyModelCallBack: (List<Hourly>) -> Unit) {

        Fuel.get("${Generic.baseUrl}onecall?lat=$lat&lon=$lon&exclude=current,minutely,daily&APPID=${Generic.appId}")
            .responseObject(HourlyModel.Deserializer()) { _, _, result ->
                val (hourlyModel, _) = result
                if (hourlyModel != null) {
                    hourlyModelCallBack(hourlyModel.hourly)
                }
            }
    }

}

