package com.dreamyprogrammer.myweather.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.adapters.ForecastTodayAdapter
import com.dreamyprogrammer.myweather.databinding.MainFragmentBinding
import com.dreamyprogrammer.myweather.model.Weather
import com.dreamyprogrammer.myweather.model.WeatherFull
import com.dreamyprogrammer.myweather.model.WeatherLoader
import com.dreamyprogrammer.myweather.viewmodel.AppState
import com.dreamyprogrammer.myweather.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

//    companion object {
//        fun newInstance() = HomeFragment()
//    }

    private lateinit var viewModel: HomeViewModel

    private val forecastTodayAdapter = ForecastTodayAdapter()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var lat: Double = 0.0
    private var lon: Double = 0.0
//    private lateinit var weatherBundle: Weather

    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
        object : WeatherLoader.WeatherLoaderListener {
            override fun onLoaded(weatherFull: WeatherFull) {
                displayWeather(weatherFull)
            }
            override fun onFailed(throwable: Throwable) {
                //TODO Обработка ошибки
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveDate().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromLocalSource()
        viewModel.getForecastNowWeatherFromLocalStorage()
        initView()
        //TODO когда будем передавать город тогда выведем
        val loader = WeatherLoader(onLoadListener,lat,lon)
        loader.loadWeather()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() {

        val recyclerviewPlaying = binding.recyclerForecastToday
        recyclerviewPlaying.adapter = forecastTodayAdapter
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                val forecastTodayWeatherDate = appState.forecastTodayWeatherDate
                binding.loadingLayout.visibility = View.GONE
//                setData(weatherData)
                lat = weatherData.location.lat;
                lon = weatherData.location.lon;
                forecastTodayAdapter.setData(forecastTodayWeatherDate)
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

    //    private fun setData(weatherData: Weather) {
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

    private fun displayWeather(weatherFull: WeatherFull) {
        binding.mainView.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE
//        val city = weatherBundle.location
        binding.cityTextView.text = "Москва"


        binding.temperatureTextView.text =
            if (weatherFull.weatherNow.temperature > 0) "+" + weatherFull.weatherNow.temperature.toString() else weatherFull.weatherNow.temperature.toString()
        binding.weatherTextView.text = getString(R.string.home) + " - " + weatherFull.weatherNow.condition
        binding.windTextView.text = getString(R.string.wind) + " " + weatherFull.weatherNow.wind.toString()
        binding.pressureTextView.text =
            getString(R.string.pressure) + " " + weatherFull.weatherNow.pressureMillimeters.toString() + "мм р.с."
        binding.humidityTextView.text =
            getString(R.string.pressure) + " " + weatherFull.weatherNow.humidity.toString() + "%"
    }


}

