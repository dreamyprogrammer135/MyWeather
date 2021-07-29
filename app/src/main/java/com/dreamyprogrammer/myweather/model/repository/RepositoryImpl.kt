package com.dreamyprogrammer.myweather.model.repository


class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return getDefaultWeather();
    }

    override fun getWeatherFromLocalStorage(): List<Weather> {
        return getRussianCities();
    }

    override fun getForecastDayWeatherFromLocalStorage(): List<ForecastNowWeather> {
        return getDefaultForecastNow()
    }

    override fun getForecastWeekWeatherDate(): List<ForecastWeekWeather> {
        return getDefaultForecastWeek()
    }

}