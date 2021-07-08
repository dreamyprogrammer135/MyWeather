package com.dreamyprogrammer.myweather.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage() : Weather
    fun getForecastNowWeatherFromLocalStorage(): List<ForecastNowWeather>
}