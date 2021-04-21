package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WeatherResponseDTO(

    @SerializedName("main")
    val mainDTO: MainDTO,
    //val list : List<WeatherInfoDTO>

    @SerializedName("name")
    val cityNameDTO: String?,

    @SerializedName("weather")
    val weatherListDTO:List<WeatherDTO>
)