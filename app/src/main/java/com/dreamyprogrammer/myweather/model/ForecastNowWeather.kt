package com.dreamyprogrammer.myweather.model

data class ForecastNowWeather(
    val time: String,
    val visible_weather: String,
    val temperature: String
)


fun getDefaultForecastNow(): List<ForecastNowWeather> {
    return listOf(
        ForecastNowWeather("Сейчас", "солнечно", "+20"),
        ForecastNowWeather("09:00", "солнечно", "+23"),
        ForecastNowWeather("10:00", "солнечно", "+24"),
        ForecastNowWeather("11:00", "солнечно", "+27")
    )
}
