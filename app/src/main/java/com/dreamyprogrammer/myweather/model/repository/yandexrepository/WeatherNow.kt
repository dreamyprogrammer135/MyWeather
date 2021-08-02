package com.dreamyprogrammer.myweather.model.repository.yandexrepository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherNow(
    @Json(name = "temp")
    val temperature: Int,
    @Json(name = "condition")
    val condition: String,
    @Json(name = "wind_speed")
    val wind: Double,
    @Json(name = "wind_dir")
    val windDirection: String,
    @Json(name = "pressure_mm")
    val pressureMillimeters: Int,
    @Json(name = "pressure_pa")
    val pressurePascal: String,
    @Json(name = "humidity")
    val humidity: Int
)
