package com.sadmanhasan.forecast.ui.today

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.model.TodayModel
import kotlinx.android.synthetic.main.fragment_today.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class TodayRepository {

    private val _todayForecast = MutableLiveData<TodayModel>()
    val todayForecast: LiveData<TodayModel> = _todayForecast

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentWeather(city: String) {

        Fuel.get("${Generic.baseUrl}weather?q=$city&APPID=${Generic.appId}")
                .responseObject(TodayModel.Deserializer()) { _, _, result ->
                    val (todayModel, _) = result
                    if (todayModel != null) {
                        _todayForecast.setValue(todayModel)
                    }
                }
    }
}
