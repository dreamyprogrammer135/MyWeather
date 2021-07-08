package com.dreamyprogrammer.myweather.viewmodel

import com.dreamyprogrammer.myweather.model.ForecastNowWeather
import com.dreamyprogrammer.myweather.model.Weather

sealed class AppState {
    data class Success(
        val weatherData: Weather,
        val forecastTodayWeatherDate: List<ForecastNowWeather>
    ) : AppState()

    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}