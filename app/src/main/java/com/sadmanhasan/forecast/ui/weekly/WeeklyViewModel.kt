package com.sadmanhasan.forecast.ui.weekly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sadmanhasan.forecast.model.Daily

class WeeklyViewModel:ViewModel() {

    private val weeklyRepository = WeeklyRepository()

    private val _weeklyForecast = MutableLiveData<List<Daily>>()
    val weeklyForecast: LiveData<List<Daily>> = _weeklyForecast

    fun getWeeklyData(lat: String, lon: String) {
        weeklyRepository.getWeeklyData(lat, lon) { data ->
            _weeklyForecast.value = data
        }
    }
}