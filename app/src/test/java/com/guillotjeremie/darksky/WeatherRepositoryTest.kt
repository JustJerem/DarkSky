package com.guillotjeremie.darksky

import com.guillotjeremie.darksky.di.getComponent
import com.guillotjeremie.darksky.presentation.features.week.WeekViewModel
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import java.io.File
import java.net.HttpURLConnection


@RunWith(JUnit4::class)
class WeatherRepositoryTest : KoinTest {

    private val viewModel by inject<WeekViewModel>()
    private lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
        startKoin(getComponent())
    }

    private fun mockResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(path: String): String {
        val file = File("//assets/$path")
        return String(file.readBytes())
    }

    //TODO not working
    @Test
    fun `get data and check if correct`() {
        mockResponse("data.json", HttpURLConnection.HTTP_OK)
        val weather = viewModel.getWeekWeather().value

        assertEquals(40.73061, weather?.latitude)
    }
}