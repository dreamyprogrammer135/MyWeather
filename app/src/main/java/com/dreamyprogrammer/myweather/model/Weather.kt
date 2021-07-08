package com.dreamyprogrammer.myweather.model

data class Weather(
    val location: Location = getDefaultLocation(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultLocation() = Location(
    "Москва",
    55.755826,
    37.6172999000035
)

