package com.example.weatherapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.remote.network.WeatherManager
import io.reactivex.Single

class WeatherRepo {

    fun getWeather(cityName:String): Single<WeatherResponseDTO>{
        return WeatherManager().getWeather(cityName,"600788429c6ebf4322a78f9bba4f58da","metric")
    }
}