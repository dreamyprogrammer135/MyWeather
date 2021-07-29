package com.dreamyprogrammer.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamyprogrammer.myweather.model.repository.RepositoryImpl

class ForecastViewModel(private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
                        private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {
    fun getLiveDate() = liveDataToObserver
    fun getForecastWeekWeatherFromLocalStorage() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserver.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserver.postValue(
                AppState.Success(
                    repositoryImpl.getWeatherFromServer(),
                    repositoryImpl.getForecastDayWeatherFromLocalStorage(),
                    repositoryImpl.getForecastWeekWeatherDate()
                )
            )
        }.start()
    }
}