package com.sadmanhasan.forecast.ui.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sadmanhasan.forecast.model.TodayModel


class TodayViewModel : ViewModel() {

    private val todayRepository = TodayRepository()

    private val _todayForecast = MutableLiveData<TodayModel>()
    val todayForecast: LiveData<TodayModel> = _todayForecast

    fun getCurrentData(cityName: String) {
        todayRepository.getCurrentWeather(cityName) { data ->
            _todayForecast.value = data
        }
    }
}