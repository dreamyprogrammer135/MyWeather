package com.dreamyprogrammer.myweather.model.repository

data class ForecastWeekWeather(
    val date: String,
    val visible_weather: String,
    val temperature: String
)


fun getDefaultForecastWeek(): List<ForecastWeekWeather> {
    return listOf(
        ForecastWeekWeather("Сегодня", "солнечно", "+27"),
        ForecastWeekWeather("15 июля", "солнечно", "+23"),
        ForecastWeekWeather("16 июля", "солнечно", "+24"),
        ForecastWeekWeather("17 июля", "солнечно", "+27"),
        ForecastWeekWeather("18 июля", "солнечно", "+23"),
        ForecastWeekWeather("19 июля", "солнечно", "+24"),
        ForecastWeekWeather("20 июля", "солнечно", "+27"),
        ForecastWeekWeather("21 июля", "солнечно", "+26"),
        ForecastWeekWeather("22 июля", "солнечно", "+24"),
        ForecastWeekWeather("23 июля", "солнечно", "+21"),
        ForecastWeekWeather("24 июля", "солнечно", "+19"),
        ForecastWeekWeather("25 июля", "солнечно", "+17"),
        ForecastWeekWeather("26 июля", "солнечно", "+15")
    )
}