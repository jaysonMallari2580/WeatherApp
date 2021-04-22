package com.example.weatherapp
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.repositories.WeatherRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainViewModel : ViewModel() {

    private var _cityName = MutableLiveData<String>()
    val cityName get() = _cityName

    private val disposable = CompositeDisposable()
    private val weatherRepo: WeatherRepo by lazy {
        WeatherRepo()
    }

    init {

    }

    private var _mainInfo = MutableLiveData<String>()
    val mainInfo get() = _mainInfo

    private var _cityNameInfo = MutableLiveData<String>()
    val cityNameInfo get() = _cityNameInfo

    private var _weatherHint = MutableLiveData<String>()
    val weatherHint get() = _weatherHint

    private var _icon = MutableLiveData<String>()
    val icon get() = _icon

    private fun getWeather(cityName: String) =
        disposable.add(
            weatherRepo.getWeather(cityName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetWeatherSuccess, this::onGetWeatherError)
        )

    private fun onGetWeatherSuccess(weatherResponseDTO: WeatherResponseDTO) {

        //temp
        var temp = weatherResponseDTO.mainDTO.temp
        var tempDouble = calculateFahrenheit(temp!!.toDouble())
        var tempString  = tempDouble.toInt().toString()
        val degree = "\u2109"
        val tempOutput = "$tempString $degree"
        _mainInfo.value = tempOutput

        //cityName
        _cityNameInfo.value = weatherResponseDTO.cityNameDTO

        //weather Hint
        _weatherHint.value = weatherResponseDTO.weatherListDTO[0].description.toString()

        //weather Icon
        _icon.value = weatherResponseDTO.weatherListDTO[0].icon.toString()
    }


    private fun onGetWeatherError(e: Throwable) {
        e.message.let { Log.d(TAG, it.toString())}
    }

    fun passMeTheCityName(cityName:String){
        getWeather(cityName)
    }

    companion object {
        private val TAG = MainViewModel::class.java.name
    }

    private fun calculateFahrenheit(degrees: Double): Double {
        val degreesInFahrenheit = (degrees * 1.8) + 32
        return degreesInFahrenheit
    }



}


