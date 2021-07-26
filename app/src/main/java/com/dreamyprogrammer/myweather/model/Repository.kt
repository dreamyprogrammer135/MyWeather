package com.dreamyprogrammer.myweather.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): List<Weather>
    fun getForecastDayWeatherFromLocalStorage(): List<ForecastNowWeather>
    fun getForecastWeekWeatherDate():List<ForecastWeekWeather>
}