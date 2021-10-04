package com.applauded.meena.weathermvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.applauded.meena.weathermvvmfinal.model.Json4Kotlin_Base
import com.applauded.meena.weathermvvmfinal.repository.WdetailsRepo
import com.applauded.meena.weathermvvmfinal.viewmodel.DetailsViewModel
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest : TestCase() {

    @Test
    fun testGetWeatherDet() {
        val detailsVM = DetailsViewModel()
        detailsVM.mIsUpdating.postValue(true)
        detailsVM.weatherDet = MutableLiveData()
        runBlocking (){
            val weatherRepo = WdetailsRepo.WSingleton.instance
            weatherRepo.getWeatherDetails()
            assertNotNull(weatherRepo.apiResponse)
            assertTrue(weatherRepo.apiResponsegot)
        }
    }

}