package com.example.weatherapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.datamodel.WeatherResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel  @Inject constructor (
    private val repo: Repo
) : ViewModel() {
    val weatherDetailLiveData = MutableLiveData<WeatherResponseModel>()
    val isloading = MutableLiveData(false)

    fun getWeatherDetail(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //network is sent, make loader visible
            isloading.postValue(true)
            val response = repo.getWeatherDetail(city)
            if(response.isSuccessful) {
                weatherDetailLiveData.postValue(response.body())
                isloading.postValue(false)
            }
        }
    }
}