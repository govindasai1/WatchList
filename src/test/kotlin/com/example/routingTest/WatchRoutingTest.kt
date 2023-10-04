package com.example.routingTest

import com.example.module
import com.example.models.Id
import com.example.models.IdSymbol
import com.example.models.Symbol
import com.example.plugins.*
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
        val responce = client.post("/watchlist") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(
                Symbol("EQ",0.0,1,0.05,"21238_NSE","STK",1.0,"STK_AUBANK_EQ_NSE","AU SMALL FINANCE BANK LTD"
                    ,"2024-12-31",false,"AUBANK","Banks","NSG","INE949L01017","AUBANK")
            )
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
        val response = client.put("/watchlist"){
            setBody(IdSymbol(1, Symbol("EQ",0.1,1,0.05,"21238_NSE","STK",1.0,"STK_AUBANK_EQ_NSE","AU SMALL FINANCE BANK LTD"
                ,"2024-12-31",false,"AUBANK","Banks","NSG","INE949L01017","AUBANK")
            ))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK,response.status)
    }

    @Test
    fun testWatchList() = testApplication {
        application {
            module(configureRouting())
        }
        val responce = client.get("/watchlist"){}
        assertEquals(HttpStatusCode.OK,responce.status)
    }

    @Test
    fun testWatchLists() = testApplication {
        application {
            module(configureRouting())
        }
        val responce = client.get("/watchlist/all"){}
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
        val responce = client.delete("/watchlist"){
            setBody(Id(1))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK,responce.status)
    }

}
