package com.dreamyprogrammer.myweather.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.viewmodel.SelectCityViewModel

class SelectCityFragment : Fragment() {

    companion object {
        fun newInstance() =
            SelectCityFragment()
    }

    private lateinit var viewModel: SelectCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_city_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelectCityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}