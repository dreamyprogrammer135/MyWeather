package com.dreamyprogrammer.myweather.model

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return getDefaultWeather();
    }

    override fun getWeatherFromLocalStorage(): List<Weather> {
        return getRussianCities();
    }

    override fun getForecastNowWeatherFromLocalStorage(): List<ForecastNowWeather> {
        return getDefaultForecastNow()
    }

    override fun getForecastWeekWeatherDate(): List<ForecastWeekWeather> {
        return getDefaultForecastWeek()
    }

}