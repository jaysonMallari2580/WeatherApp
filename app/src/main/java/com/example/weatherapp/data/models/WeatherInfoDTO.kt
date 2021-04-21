package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WeatherInfoDTO (
    @SerializedName("main")
    val mainDTO: MainDTO
)