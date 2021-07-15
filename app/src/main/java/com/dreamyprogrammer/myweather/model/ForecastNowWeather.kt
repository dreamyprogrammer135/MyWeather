package com.dreamyprogrammer.myweather.model

data class ForecastNowWeather(
    val time: String,
    val visible_weather: String,
    val temperature: String
)


fun getDefaultForecastNow(): List<ForecastNowWeather> {
    return listOf(
        ForecastNowWeather("08:00", "солнечно", "+20"),
        ForecastNowWeather("09:00", "солнечно", "+23"),
        ForecastNowWeather("10:00", "солнечно", "+24"),
        ForecastNowWeather("11:00", "солнечно", "+27"),
        ForecastNowWeather("12:00", "солнечно", "+23"),
        ForecastNowWeather("13:00", "солнечно", "+24"),
        ForecastNowWeather("14:00", "солнечно", "+27"),
        ForecastNowWeather("15:00", "солнечно", "+26"),
        ForecastNowWeather("16:00", "солнечно", "+24"),
        ForecastNowWeather("17:00", "солнечно", "+21"),
        ForecastNowWeather("18:00", "солнечно", "+19"),
        ForecastNowWeather("19:00", "солнечно", "+17"),
        ForecastNowWeather("20:00", "солнечно", "+15")
    )
}
