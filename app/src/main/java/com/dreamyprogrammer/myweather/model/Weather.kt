package com.dreamyprogrammer.myweather.model

data class Weather(
    val location: Location = getDefaultLocation(),
    val temperature: Int = 0,
    val wind: Double = 0.0,
    val humidity: Int = 0,
    val weather: String,
    val pressure: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultLocation() = Location(
    "Москва",
    55.755826,
    37.6172999000035
)
fun getDefaultWeather(): Weather {
    return Weather(getDefaultLocation(),27, 3.265, 48,"солнечно", 768)
}

