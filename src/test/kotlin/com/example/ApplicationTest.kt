package com.example

import com.example.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testWatchList() = testApplication {
        application {
            configureRouting()
        }
        val responce = client.get("/"){}
        assertEquals(HttpStatusCode.OK,responce.status)
    }

}
