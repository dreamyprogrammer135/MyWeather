package com.dreamyprogrammer.myweather.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): List<Weather>
    fun getForecastNowWeatherFromLocalStorage(): List<ForecastNowWeather>
    fun getForecastWeekWeatherDate():List<ForecastWeekWeather>
}