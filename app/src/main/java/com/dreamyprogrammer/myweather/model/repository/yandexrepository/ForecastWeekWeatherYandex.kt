package com.dreamyprogrammer.myweather.model.repository.yandexrepository

import com.dreamyprogrammer.myweather.model.repository.ForecastNowWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastWeekWeatherYandex(
    @Json(name = "parts")
    val forecastWeekWeatherPartYandex: List<ForecastWeekWeatherPartYandex>
)
