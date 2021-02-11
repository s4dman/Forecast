package com.sadmanhasan.forecast.ui.today

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.Hourly
import kotlinx.android.synthetic.main.item_hourly_weather.view.*
import java.util.*

class HourlyAdapter(private val context: Context, private val hourlyWeather: List<Hourly>) : RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_hourly_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = hourlyWeather.drop(0)[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return hourlyWeather.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(data: Hourly) {

            val timezone = Generic.getSharedPref(context, "timezone").toLong()

            itemView.text_hourly_time.text = Generic.formatTime(data.dt + timezone, "ha").toLowerCase(Locale.ROOT)
            Glide.with(context)
                    .load("https://openweathermap.org/img/wn/${data.weather[0].icon}@4x.png")
                    .into(itemView.img_hourly_weather)
            itemView.text_hourly_temp.text = Generic.tempConvert(data.temp)
        }

    }
}