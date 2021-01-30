package com.sadmanhasan.forecast.ui.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.MainActivity
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.Daily
import com.sadmanhasan.forecast.ui.today.TodayFragment
import kotlinx.android.synthetic.main.fragment_weekly.*


class WeeklyFragment : Fragment() {

    private val viewModel = WeeklyViewModel()

    companion object {
        private var cityName: String = ""
        private var location: String = ""
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
        location = Generic.getSharedPref(requireContext(), "location")

        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = location
        }

        val lat: String = Generic.getSharedPref(requireContext(), "lat")
        val lon: String = Generic.getSharedPref(requireContext(), "lon")

        viewModel.getWeeklyData(lat, lon)
        viewModel.weeklyForecast.observe(viewLifecycleOwner, Observer {
            initWeeklyRecyclerView(it)
        })
    }


    private fun initWeeklyRecyclerView(weeklyModel: List<Daily>) {
        recycler_weekly.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WeeklyAdapter(context, weeklyModel)
        }
    }
}


