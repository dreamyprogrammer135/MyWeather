package com.dreamyprogrammer.myweather.viewmodel

import com.dreamyprogrammer.myweather.model.repository.ForecastNowWeather
import com.dreamyprogrammer.myweather.model.repository.ForecastWeekWeather
import com.dreamyprogrammer.myweather.model.repository.Weather


sealed class AppState {
    data class Success(
        val weatherData: Weather,
        val forecastTodayWeatherDate: List<ForecastNowWeather>,
        val forecastWeekWeatherDate: List<ForecastWeekWeather>
    ) : AppState()

    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}