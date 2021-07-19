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

fun getRussianCities(): List<Weather> {
    return listOf(
        Weather(Location("Москва", 55.755826, 37.617299900000035), 27, 2.35,46, "солнечно", 768),
        Weather(Location("Санкт-Петербург", 59.9342802, 30.335098600000038), 21, 2.35,46, "пасмурно", 761),
        Weather(Location("Новосибирск", 55.00835259999999, 82.93573270000002), 25, 1.35,41, "солнечно", 764),
        Weather(Location("Екатеринбург", 56.83892609999999, 60.60570250000001), 29, 3.35,40, "солнечно", 760),
        Weather(Location("Нижний Новгород", 56.2965039, 43.936059),27, 2.35,46, "солнечно", 768)
    )
}
