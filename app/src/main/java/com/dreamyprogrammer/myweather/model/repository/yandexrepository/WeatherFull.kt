package com.dreamyprogrammer.myweather.model.repository.yandexrepository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherFull(
    @Json(name = "now")
    val dateNow: Long,
    @Json(name = "info")
    val locationInfo: LocationInfo,
    @Json(name = "fact")
    val weatherNow: WeatherNow,
    @Json(name = "forecast")
    val weatherForecast: ForecastWeekWeatherYandex
)


