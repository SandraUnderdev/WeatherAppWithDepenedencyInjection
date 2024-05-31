package com.example.weatherapplication

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapplication.di.ApplicationClass
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  //  private lateinit var repo: Repo
    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var loader: ProgressBar

    private lateinit var editCityName: EditText
    private lateinit var btnGetWeather: Button
    private lateinit var imgWeather: ImageView
    private lateinit var textWeather: TextView
    private lateinit var textCityStateName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        (application as ApplicationClass).applicationComponent.inject(this)

        init()

//        val cityName = "London"
//        weatherViewModel.getWeatherDetail(cityName)

        btnGetWeather.setOnClickListener {
            weatherViewModel.getWeatherDetail(editCityName.text.toString())
        }

        weatherViewModel.weatherDetailLiveData.observe(this) {
           // Log.i("WeatherDetail", it.toString())
            val currentWeatherType = it.current.condition.text
            val currentTempInc = it.current.temp_c

            textWeather.text = "$currentWeatherType, $currentTempInc"

            val imageLink = "https:${it.current.condition.icon}"
            Glide.with(this)
                .load(imageLink)
                .into(imgWeather)

            val cityName = it.location.name
            val state = it.location.region

            val cityAndState = "$cityName, $state"
            textCityStateName.text = cityAndState
        }

        weatherViewModel.isloading.observe(this) {
            if(it) {
                loader.visibility = View.VISIBLE
            } else{
                loader.visibility = View.GONE
            }
        }

    }

   private fun init() {
//       repo = Repo(RetrofitBuilder.getInstance())
//       weatherViewModelFactory = WeatherViewModelFactory(repo)
       weatherViewModel = ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)

       loader = findViewById(R.id.loader)

       editCityName = findViewById(R.id.edt_city_name)
       btnGetWeather =  findViewById(R.id.btn_get_weather)
       imgWeather = findViewById(R.id.img_weather)
       textWeather = findViewById(R.id.text_weather)
       textCityStateName= findViewById(R.id.text_city_state)

   }
}