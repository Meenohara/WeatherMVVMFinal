package com.applauded.meena.weathermvvmfinal.repository

import com.applauded.meena.weathermvvmfinal.WeatherApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WdetailsRepoTest {
    @Test
    fun testGetWeatherDetails() {
        val repoObject:WdetailsRepo = WdetailsRepo.WSingleton.instance
        runBlocking {
            repoObject.getWeatherDetails()
        }
        assertTrue(repoObject.apiResponsegot)
        assertNotNull(repoObject.apiResponse)
        println(repoObject.apiResponse)
    }
}


