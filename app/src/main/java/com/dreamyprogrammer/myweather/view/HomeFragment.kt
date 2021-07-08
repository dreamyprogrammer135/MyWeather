package com.dreamyprogrammer.myweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.adapters.ForecastTodayAdapter
import com.dreamyprogrammer.myweather.databinding.MainFragmentBinding
import com.dreamyprogrammer.myweather.model.Weather
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveDate().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromLocalSource()
        viewModel.getForecastNowWeatherFromLocalStorage()
        initView()
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
                setData(weatherData)
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

    // С выводом пока не заморачивался. В константы пока не убирал но уберу
    private fun setData(weatherData: Weather) {
        binding.cityTextView.text = weatherData.location.city
        binding.temperatureTextView.text = if(weatherData.temperature > 0 ) "+"+weatherData.temperature.toString() else weatherData.temperature.toString()
        binding.weatherTextView.text = getString(R.string.home) + " - " + weatherData.weather
        binding.windTextView.text = getString(R.string.wind) +" "+ weatherData.wind.toString()
        binding.pressureTextView.text = getString(R.string.pressure) + " "+ weatherData.pressure.toString() + "мм р.с."
        binding.humidityTextView.text = getString(R.string.pressure) +" " + weatherData.humidity.toString() + "%"
    }


}