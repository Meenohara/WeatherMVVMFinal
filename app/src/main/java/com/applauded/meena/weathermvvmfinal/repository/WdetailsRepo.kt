package com.applauded.meena.weathermvvmfinal.repository

import com.applauded.meena.weathermvvmfinal.WeatherApi

class WdetailsRepo {

    private val TAG = "AppFlow"

    var apiResponse:Any?=null
    var apiResponsegot = true

    object WSingleton
    {
        val instance = WdetailsRepo()
    }

     suspend fun getWeatherDetails(){

             try {
              // apiResponse = WeatherApi.retrofitService.getWithDetails("","a3dcc3f4700c8f2fac88f8aac0894499")
                 //    apiResponse = WeatherApi.retrofitService.getWithDetails("Kolkata","a3dcc3f4700c8f2fac88f8aac0894499")
                 // apiResponse = WeatherApi.retrofitService.getWithDetails("Mumbai","a3dcc3f4700c8f2fac88f8aac0894499")
                 //   apiResponse = WeatherApi.retrofitService.getWithDetails("New Delhi","a3dcc3f4700c8f2fac88f8aac0894499")
                    apiResponse = WeatherApi.retrofitService.getWithDetails("New York","a3dcc3f4700c8f2fac88f8aac0894499")
                //  apiResponse = WeatherApi.retrofitService.getWithDetails("Pune","a3dcc3f4700c8f2fac88f8aac0894499")
                 //  apiResponse = WeatherApi.retrofitService.getWithDetails("Bangalore","")

             } catch (e: Exception) {
                 apiResponse= e.message
                 apiResponsegot = false
             }
    }
}



//https://stackoverflow.com/questions/58100739/how-to-generate-a-json-object-in-kotlin
//https://stackoverflow.com/questions/34485420/how-do-you-put-an-image-file-in-a-json-object
