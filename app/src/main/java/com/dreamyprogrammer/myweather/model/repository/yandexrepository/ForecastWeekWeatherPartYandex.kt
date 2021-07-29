package com.dreamyprogrammer.myweather.model.repository.yandexrepository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastWeekWeatherPartYandex(
    @Json(name = "part_name")
    val partName: String,
    @Json(name = "condition")
    val condition: String,
    @Json(name = "temp_avg")
    val temperature: Int
)
