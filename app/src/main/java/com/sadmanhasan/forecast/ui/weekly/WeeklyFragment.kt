package com.sadmanhasan.forecast.ui.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.MainActivity
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.Daily
import com.sadmanhasan.forecast.model.WeeklyModel
import kotlinx.android.synthetic.main.fragment_weekly.*


class WeeklyFragment : Fragment() {

    companion object {
        var cityName: String = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weekly, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityName = Generic.getSharedPref(requireContext(), "city_name")

        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = cityName
        }

        getWeeklyData()
    }

    private fun getWeeklyData() {

        val lat: String = Generic.getSharedPref(requireContext(), "lat")
        val lon: String = Generic.getSharedPref(requireContext(), "lon")

        Fuel.get("${Generic.baseUrl}onecall?lat=$lat&lon=$lon&exclude=current,minutely,hourly&APPID=${Generic.appId}")
            .responseObject(WeeklyModel.Deserializer()) { _, _, result ->
                val (weeklyModel, _) = result
                if (weeklyModel != null) {
                    /* Dropping today's(first) weather data, as we need next 7 days data */
                    val data = weeklyModel.daily.drop(1)
                    initWeeklyRecyclerView(data)
                }
            }
    }

    private fun initWeeklyRecyclerView(weeklyModel: List<Daily>) {
        recycler_weekly.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WeeklyAdapter(context, weeklyModel)
        }
    }
}

