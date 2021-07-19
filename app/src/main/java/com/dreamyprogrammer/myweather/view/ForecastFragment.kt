package com.dreamyprogrammer.myweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dreamyprogrammer.myweather.adapters.ForecastWeekAdapter
import com.dreamyprogrammer.myweather.databinding.ForecastFragmentBinding
import com.dreamyprogrammer.myweather.viewmodel.AppState
import com.dreamyprogrammer.myweather.viewmodel.ForecastViewModel
import com.google.android.material.snackbar.Snackbar


class ForecastFragment : Fragment() {

    companion object {
        fun newInstance() =
            ForecastFragment()
    }

    private lateinit var viewModel: ForecastViewModel
    private var _binding: ForecastFragmentBinding? = null
    private val binding get() = _binding!!
    private val forecastWeekAdapter = ForecastWeekAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ForecastFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        viewModel.getLiveDate().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getForecastWeekWeatherFromLocalStorage()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() {
        val recyclerviewPlaying = binding.recyclerForecastWeek
        recyclerviewPlaying.adapter = forecastWeekAdapter
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val forecastWeekWeatherDate = appState.forecastWeekWeatherDate

                binding.loadingLayout.visibility = View.GONE
                forecastWeekAdapter.setData(forecastWeekWeatherDate)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.recyclerForecastWeek, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getForecastWeekWeatherFromLocalStorage() }
                    .show()
            }
        }
    }
}