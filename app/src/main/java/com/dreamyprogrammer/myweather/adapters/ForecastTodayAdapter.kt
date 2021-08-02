package com.dreamyprogrammer.myweather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.model.repository.ForecastNowWeather

class ForecastTodayAdapter : RecyclerView.Adapter<ForecastTodayAdapter.ViewHolder>() {

    private var forecastData: List<ForecastNowWeather> = listOf()

    fun setData(data: List<ForecastNowWeather>) {
        forecastData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_forecast_today, parent, false) as View
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastData[position])
    }

    override fun getItemCount(): Int {
        return forecastData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(forecast: ForecastNowWeather) {
            itemView.findViewById<TextView>(R.id.time_text_view).text = forecast.time
//            itemView.findViewById<TextView>(R.id.visible_weather_imageview).background = itemView.resources
            itemView.findViewById<TextView>(R.id.temperature_text_view).text = forecast.temperature
        }
    }

}