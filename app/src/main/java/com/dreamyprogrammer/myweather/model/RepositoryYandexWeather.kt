package com.dreamyprogrammer.myweather.model

class RepositoryYandexWeather : Repository {
    override fun getWeatherFromServer(): Weather {
        TODO("Not yet implemented")
    }

    override fun getWeatherFromLocalStorage(): List<Weather> {
        TODO("Not yet implemented")
    }

    override fun getForecastDayWeatherFromLocalStorage(): List<ForecastNowWeather> {
        TODO("Not yet implemented")
    }

    override fun getForecastWeekWeatherDate(): List<ForecastWeekWeather> {
        TODO("Not yet implemented")
    }
}