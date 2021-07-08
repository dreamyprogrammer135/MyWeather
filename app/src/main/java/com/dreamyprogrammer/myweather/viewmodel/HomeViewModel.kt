package com.dreamyprogrammer.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamyprogrammer.myweather.model.RepositoryImpl
import java.lang.Thread.sleep

class HomeViewModel(
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getLiveDate() = liveDataToObserver

    fun getWeatherFromLocalSource() = getDataFromLocalSource()

    fun getForecastNowWeatherFromLocalStorage() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserver.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserver.postValue(
                AppState.Success(
                    repositoryImpl.getWeatherFromLocalStorage(),
                    repositoryImpl.getForecastNowWeatherFromLocalStorage()
                )
            )
        }.start()
    }
}