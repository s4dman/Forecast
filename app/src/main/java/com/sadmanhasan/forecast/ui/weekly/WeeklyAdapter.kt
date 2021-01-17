package com.sadmanhasan.forecast.ui.weekly

import android.animation.LayoutTransition
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sadmanhasan.forecast.Generic
import com.sadmanhasan.forecast.R
import com.sadmanhasan.forecast.model.Daily
import kotlinx.android.synthetic.main.item_weekly_weather.view.*
import java.util.*
import kotlin.math.roundToInt


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
            itemView.text_weekly_humidity.text = (data.humidity + "%")
            itemView.text_weekly_percp.text = (data.pop + "%")
            itemView.text_weekly_wind.text =
                ((data.wind_speed.toDouble() * 3.6).roundToInt().toString() + " km/h")

            itemView.text_weekly_pressure.text =
                (((data.pressure.toInt() / 10).toString()) + " kPa")


            itemView.item_weekly_main_view.setOnClickListener {
                if (!data.expandable) {
                    itemView.expandable_layout.isVisible = true
                    data.expandable = true
                } else {
                    itemView.expandable_layout.isVisible = false
                    data.expandable = false
                }
            }

            applyLayoutTransition(itemView)
        }
    }

    private fun applyLayoutTransition(itemView: View) {
        val transition = LayoutTransition()
        transition.setDuration(300)
        transition.enableTransitionType(LayoutTransition.CHANGING)
        itemView.item_weekly_main_view.layoutTransition = transition
    }
}