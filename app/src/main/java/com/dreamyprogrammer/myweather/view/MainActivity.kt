package com.dreamyprogrammer.myweather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NavigationContainerFragment())
                .commitNow()
        }
    }
}