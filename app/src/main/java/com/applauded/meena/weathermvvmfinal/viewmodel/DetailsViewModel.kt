package com.applauded.meena.weathermvvmfinal.viewmodel

//import Json4Kotlin_Base
import android.util.Log
import androidx.lifecycle.*
import com.applauded.meena.weathermvvmfinal.WeatherApi
import com.applauded.meena.weathermvvmfinal.model.Json4Kotlin_Base
import com.applauded.meena.weathermvvmfinal.repository.WdetailsRepo
import kotlinx.coroutines.*

class DetailsViewModel() : ViewModel() {
    private val TAG: String = "AppFlow"

    var weatherDet: MutableLiveData<Json4Kotlin_Base>
    lateinit var wDet: Json4Kotlin_Base
    lateinit var weatherRepo: WdetailsRepo
    val mIsUpdating = MutableLiveData<Boolean>()
    var result: Any? = null
    val respNotRxd = MutableLiveData<Boolean>()
    //init()?

    init {
        getWeatherDetails()
        weatherDet = MutableLiveData()
    }

    fun getWeatherDetails() {

        viewModelScope.launch(Dispatchers.IO) {

            mIsUpdating.postValue(true)
            weatherRepo = WdetailsRepo.WSingleton.instance
            weatherRepo.getWeatherDetails()

            if (weatherRepo.apiResponsegot) {
                wDet = weatherRepo.apiResponse as Json4Kotlin_Base
                setMutability(wDet)
            } else {
                respNotRxd.postValue(true)
                result = weatherRepo.apiResponse
            }

            mIsUpdating.postValue(false)
        }
    }

    fun setMutability(pDet: Json4Kotlin_Base) {
        weatherDet.postValue(pDet)
        respNotRxd.postValue(false)
    }

    companion object {
        private val TAG = "DetailsViewModel"
    }

    fun getRespStatus(): LiveData<Boolean> {
        Log.d(TAG, "getRespStatus: " + respNotRxd.value)
        return respNotRxd
    }

    fun getIsUpdating(): LiveData<Boolean> {
        Log.d(TAG, "getIsUpdating: VM " + mIsUpdating.value)
        return mIsUpdating
    }
}


/*        weatherDet = liveData{
    emit(WdetailsRepo.WSingleton.instance.getWeatherDetails())
    Log.d(TAG, "WdetailsRepo livedata getWeatherDetails: "+WdetailsRepo.WSingleton.instance.getWeatherDetails())
} as MutableLiveData<Json4Kotlin_Base>*/