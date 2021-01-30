package com.sadmanhasan.forecast.ui.today

import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.model.TodayModel

class TodayRepository {

    fun getCurrentWeather(city: String, viewModelCallBack: (TodayModel) -> Unit) {

        Fuel.get("${Generic.baseUrl}weather?q=$city&APPID=${Generic.appId}")
                .responseObject(TodayModel.Deserializer()) { _, _, result ->
                    val (todayModel, _) = result
                    if (todayModel != null) {
                        viewModelCallBack(todayModel)
                    }
                }
    }

}

