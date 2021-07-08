package com.dreamyprogrammer.myweather.model

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return getDefaultWeather();
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return getDefaultWeather();
    }

    override fun getForecastNowWeatherFromLocalStorage(): List<ForecastNowWeather> {
        return getDefaultForecastNow()
    }
}