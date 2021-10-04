package com.applauded.meena.weathermvvmfinal

import com.applauded.meena.weathermvvmfinal.model.Json4Kotlin_Base
import com.applauded.meena.weathermvvmfinal.repository.WdetailsRepo
import org.junit.jupiter.api.Assertions.*

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import retrofit2.HttpException

@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
class WeatherApiTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    fun testGetRetrofitService() {
        val repoObject:WdetailsRepo = WdetailsRepo.WSingleton.instance
        runBlocking {
            val wdetails =
                WeatherApi.retrofitService.getWithDetails("Mys", "a3dcc3f4700c8f2fac88f8aac0894499")
            println(wdetails.cod)
            assertNotNull(wdetails)
            assertEquals(200,wdetails.cod)
        }
        Assertions.assertTrue(repoObject.apiResponsegot)
        Assertions.assertNull(repoObject.apiResponse)
    }

    fun testGetRetrofitErr1() {

        var wdetails: Json4Kotlin_Base
        val repoObject:WdetailsRepo = WdetailsRepo.WSingleton.instance
        val exception: Exception =
                assertThrows(
                    HttpException::class.java
                ) {
                    runBlocking {
                        wdetails = WeatherApi.retrofitService.getWithDetails(
                            "",
                            "a3dcc3f4700c8f2fac88f8aac0894499"
                        )
                    }
                    assertEquals(400,wdetails.cod)
                }
        assertEquals(exception.javaClass.toString(), (HttpException::class).toString())
        Assertions.assertTrue(repoObject.apiResponsegot)
        Assertions.assertNull(repoObject.apiResponse)
    }


    fun testGetRetrofitErr2() {

        var wdetails: Json4Kotlin_Base
        val repoObject:WdetailsRepo = WdetailsRepo.WSingleton.instance
        val exception: Exception =
            assertThrows(
                HttpException::class.java
            ) {
                runBlocking {
                    wdetails = WeatherApi.retrofitService.getWithDetails(
                        "Pune",
                        ""
                    )
                }
                assertEquals(401,wdetails.cod)
            }
                assertEquals(exception.javaClass.toString(), (HttpException::class).toString())
                Assertions.assertTrue(repoObject.apiResponsegot)
                Assertions.assertNull(repoObject.apiResponse)
            }


    fun testGetRetrofitErr3() {

        var wdetails: Json4Kotlin_Base
        val repoObject:WdetailsRepo = WdetailsRepo.WSingleton.instance
        val exception: Exception =
            assertThrows(HttpException::class.java) {
                runBlocking {
                    wdetails = WeatherApi.retrofitService.getWithDetails("", "")
                }
                assertEquals(401,wdetails.cod)
            }
        println(exception.javaClass)
        assertEquals(exception.javaClass.toString(), (HttpException::class).toString())
        Assertions.assertTrue(repoObject.apiResponsegot)
        Assertions.assertNull(repoObject.apiResponse)
    }

}

//https://stackoverflow.com/questions/30331806/test-expected-exceptions-in-kotlin