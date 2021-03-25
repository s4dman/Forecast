package com.sadmanhasan.forecast.ui.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sadmanhasan.forecast.model.Hourly
import com.sadmanhasan.forecast.model.TodayModel


class TodayViewModel : ViewModel() {

    private val todayRepository = TodayRepository()

    private val _todayForecast = MutableLiveData<TodayModel>()
    val todayForecast: LiveData<TodayModel> = _todayForecast

    private val _hourlyForecast = MutableLiveData<List<Hourly>>()
    val hourlyForecast: LiveData<List<Hourly>> = _hourlyForecast

    fun getCurrentData(cityName: String) {
        todayRepository.getCurrentWeather(cityName) { data ->
            _todayForecast.value = data
        }
    }

    fun getHourlyData(lat: String, lon: String) {
        todayRepository.getHourlyData(lat, lon) { data ->
            _hourlyForecast.value = data
        }
    }
}