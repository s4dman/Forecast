package com.sadmanhasan.forecast.ui.weekly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.Daily
import kotlinx.android.synthetic.main.item_weekly_weather.view.*
import java.util.*

class WeeklyAdapter(private val context: Context, private val weeklyWeather: List<Daily>) :
    RecyclerView.Adapter<WeeklyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_weekly_weather, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = weeklyWeather[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return weeklyWeather.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: Daily) {
            itemView.text_weekly_date.text = Generic.formatDate(data.dt)

            Glide.with(context)
                .load("https://openweathermap.org/img/wn/${data.weather[0].icon}@4x.png")
                .into(itemView.img_weekly_weather)
            itemView.text_weekly_maxmin.text =
                ("${Generic.tempConvert(data.temp.max)} / ${Generic.tempConvert(data.temp.min)}")
            itemView.text_weekly_weather.text = data.weather[0].main.capitalize(Locale.getDefault())

        }

    }
}