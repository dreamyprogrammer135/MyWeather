package com.dreamyprogrammer.myweather.view

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.adapters.ForecastTodayAdapter
import com.dreamyprogrammer.myweather.databinding.MainFragmentBinding
import com.dreamyprogrammer.myweather.model.WeatherLoader
import com.dreamyprogrammer.myweather.model.repository.ForecastNowWeather
import com.dreamyprogrammer.myweather.model.repository.Location
import com.dreamyprogrammer.myweather.model.repository.Weather
import com.dreamyprogrammer.myweather.model.repository.yandexrepository.WeatherFull
import com.dreamyprogrammer.myweather.viewmodel.AppState
import com.dreamyprogrammer.myweather.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

//    companion object {
//        fun newInstance() = HomeFragment()
//    }


    private lateinit var viewModel: HomeViewModel

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private val forecastTodayAdapter = ForecastTodayAdapter()
    private lateinit var thiscontext: Context

    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
        object : WeatherLoader.WeatherLoaderListener {
            override fun onLoaded(weatherFull: WeatherFull) {

                //TODO Обязательно вынесу. Этому коду здесь не место
                val weatherData = Weather(
                    Location("Москва", weatherFull.locationInfo.lat, weatherFull.locationInfo.lon),
                    weatherFull.weatherNow.temperature,
                    weatherFull.weatherNow.wind,
                    weatherFull.weatherNow.humidity,
                    weatherFull.weatherNow.condition,
                    weatherFull.weatherNow.pressureMillimeters
                )
                setData(weatherData)
                val forecastTodayWeatherDate = getForecastNowWeather(weatherFull)
                forecastTodayAdapter.setData(forecastTodayWeatherDate)

            }

            override fun onFailed(throwable: Throwable) {
                clickAlertDialogNoInternetAccess()
            }
        }

    private fun clickAlertDialogNoInternetAccess() {
        val builder = AlertDialog.Builder(thiscontext)
        builder.setTitle(R.string.title_alert_dialog_connect)
            .setMessage(R.string.message_allert_dialog_connect)
            .setIcon(R.drawable.ic_wifioff)
            .setPositiveButton(R.string.button_ok,
                DialogInterface.OnClickListener { dialog, id -> })
        val alert = builder.create()
        alert.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO ЗАГЛУШКА !!! Обязательно вынесу. Этому коду здесь не место
    fun getForecastNowWeather(weatherFull: WeatherFull): List<ForecastNowWeather> {

        return listOf(
            ForecastNowWeather(
                weatherFull.weatherForecast.forecastWeekWeatherPartYandex[0].partName,
                weatherFull.weatherForecast.forecastWeekWeatherPartYandex[0].condition,
                weatherFull.weatherForecast.forecastWeekWeatherPartYandex[0].temperature.toString()
            ),
            ForecastNowWeather(
                weatherFull.weatherForecast.forecastWeekWeatherPartYandex[1].partName,
                weatherFull.weatherForecast.forecastWeekWeatherPartYandex[1].condition,
                weatherFull.weatherForecast.forecastWeekWeatherPartYandex[1].temperature.toString()
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveDate().observe(viewLifecycleOwner, Observer { renderData(it) })

        viewModel.getWeatherFromLocalSource()
        viewModel.getForecastNowWeatherFromLocalStorage()
        initView()
        thiscontext = view.context
        //TODO когда будем передавать город тогда выведем
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() {

        val recyclerviewPlaying = binding.recyclerForecastToday
        recyclerviewPlaying.adapter = forecastTodayAdapter
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val loader = WeatherLoader(
                    onLoadListener,
                    appState.weatherData.location.lat,
                    appState.weatherData.location.lon
                )
                loader.loadWeather()

                binding.loadingLayout.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeatherFromLocalSource() }
                    .show()
            }
        }
    }

    private fun setData(weatherData: Weather) {
        binding.cityTextView.text = "Москва"
        binding.temperatureTextView.text =
            if (weatherData.temperature > 0) "+" + weatherData.temperature.toString() else weatherData.temperature.toString()
        binding.weatherTextView.text = getString(R.string.home) + " - " + weatherData.weather
        binding.windTextView.text = getString(R.string.wind) + " " + weatherData.wind.toString()
        binding.pressureTextView.text =
            getString(R.string.pressure) + " " + weatherData.pressure.toString() + "мм р.с."
        binding.humidityTextView.text =
            getString(R.string.pressure) + " " + weatherData.humidity.toString() + "%"
    }
}

