package com.example.weatherapplication.di

import com.example.weatherapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}