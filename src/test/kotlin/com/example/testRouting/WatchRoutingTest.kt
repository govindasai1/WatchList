package com.example.testRouting

import com.example.endPoints.BASE_PATH
import com.example.endPoints.RESOURCE
import com.example.module
import com.example.models.Id
import com.example.plugins.*
import com.example.testParameters.id
import com.example.testParameters.idSymbol
import com.example.testParameters.symbol
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class WatchRoutingTest {

    @Test
    fun testInsert() = testApplication {
        application {
            module(configureRouting())
        }
        val client = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val responce = client.post(BASE_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(symbol)
        }
        assertEquals(HttpStatusCode.Created,responce.status)
    }

    @Test
    fun testUpdation() = testApplication {
        application { module(configureRouting()) }
        val client = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val response = client.put(BASE_PATH){
            setBody(idSymbol)
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK,response.status)
    }

    @Test
    fun testWatchList() = testApplication {
        application {
            module(configureRouting())
        }
        val responce = client.get(BASE_PATH){}
        assertEquals(HttpStatusCode.OK,responce.status)
    }

    @Test
    fun testWatchLists() = testApplication {
        application {
            module(configureRouting())
        }
        val responce = client.get(BASE_PATH + RESOURCE){}
        assertEquals(HttpStatusCode.OK,responce.status)
    }

    @Test
    fun testDeleteWatch() = testApplication {
        application {
            module(configureRouting())
        }
        val client = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val responce = client.delete(BASE_PATH){
            setBody(Id(id))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK,responce.status)
    }

}
