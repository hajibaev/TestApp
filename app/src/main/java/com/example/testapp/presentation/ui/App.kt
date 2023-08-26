package com.example.testapp.presentation.ui

import android.app.Application
import com.example.testapp.presentation.di.appModule
import com.example.testapp.presentation.di.dataSourceModule
import com.example.testapp.presentation.di.interactorModule
import com.example.testapp.presentation.di.repositoryModule
import com.example.testapp.presentation.di.retrofitModule
import com.example.testapp.presentation.di.roomModule
import com.example.testapp.presentation.di.viewModelModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    retrofitModule,
                    dataSourceModule,
                    repositoryModule,
                    roomModule,
                    viewModelModel,
                    interactorModule
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}