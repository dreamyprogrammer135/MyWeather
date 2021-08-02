package com.dreamyprogrammer.myweather.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamyprogrammer.myweather.R
import com.dreamyprogrammer.myweather.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private val networkStatusReceiver: BroadcastReceiver = NetworkStatus()

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

        setBroadcastMessages()
    }

    private fun setBroadcastMessages() {
//        registerReceiver(networkStatusReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        //       registerReceiver(networkStatusReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(networkStatusReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        initNotificationChanne()
    }


    // инициализация канала нотификаций
    private fun initNotificationChanne() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("2", "name", importance)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(networkStatusReceiver)
        super.onDestroy()
    }
}