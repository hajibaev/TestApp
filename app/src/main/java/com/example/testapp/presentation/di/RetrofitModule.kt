package com.example.testapp.presentation.di

import com.example.testapp.data.cloud.service.CameraService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_BASE_URL = "https://cars.cprogroup.ru"
const val BASE_URL = "$API_BASE_URL/api/"

const val CONNECT_TIMEOUT_SECONDS = 30L

val retrofitModule = module {

    single {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS).build()
    }
    single {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
        GsonConverterFactory.create(gson)
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>()).build()
    }

    factory {
        get<Retrofit>().create(CameraService::class.java)
    }


}