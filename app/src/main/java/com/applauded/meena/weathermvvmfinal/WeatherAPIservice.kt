package com.applauded.meena.weathermvvmfinal

//import Json4Kotlin_Base
import android.util.Log
import com.applauded.meena.weathermvvmfinal.model.Json4Kotlin_Base
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.openweathermap.org"

    private val TAG = "AppFlow"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

//How retrofit talks to the web server using HTTP requests
interface WeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getWithDetails(@Query("q") q:String, @Query("appid") appid:String):
            Json4Kotlin_Base
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy {
       // Log.d(TAG, "object WeatherApi "+Thread.currentThread().name)
        retrofit.create(WeatherApiService::class.java)
        } //proxy pattern
}