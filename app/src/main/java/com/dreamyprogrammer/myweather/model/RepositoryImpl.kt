package com.dreamyprogrammer.myweather.model

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }

    override fun getForecastNowWeatherFromLocalStorage(): List<ForecastNowWeather> {
        return getDefaultForecastNow()
    }
}