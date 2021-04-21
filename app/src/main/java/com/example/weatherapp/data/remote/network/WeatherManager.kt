package com.example.weatherapp.data.remote.network

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.WeatherResponseDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {

    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    fun getWeather(q:String,appid: String,units:String) = service.getWeather(q,appid,units)

    interface WeatherService {
        //ex zip=94040,us&
        //api.openweathermap.org/data/2.5/weather?q=New%20Jersey&appid=600788429c6ebf4322a78f9bba4f58da
        @GET("data/2.5/weather")
        fun getWeather(@Query("q")q:String,
                        @Query("appid")appid:String,
                        @Query("units")units:String) :Single<WeatherResponseDTO>

    }
}