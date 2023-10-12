package com.example.testServices

import com.example.models.Message
import com.example.models.Watch
import com.example.repositories.WatchLists
import com.example.services.WatchListServices
import com.example.tables.RecentWatchList
import com.example.tables.WatchList
import com.example.testDatabase.TestDatabaseFactory
import com.example.testParameters.id
import com.example.testParameters.symbol
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Order
import java.sql.Connection
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WatchServiceTest {
    private lateinit var database: Database
    private val watchListServ = WatchListServices(WatchLists)

    @Before
    fun start(){
        database = TestDatabaseFactory.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database){
            SchemaUtils.create(RecentWatchList, WatchList)
        }
    }
    @After
    fun end(){
        transaction(database){
            SchemaUtils.drop(RecentWatchList, WatchList)
        }
    }

    @Test
    @Order(3)
    fun viewRecentTest() = testApplication {
        val result = watchListServ.allWatchList()
        if (result.equals(Watch)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    @Order(1)
    fun createWatchListTest() = testApplication {
        val result = watchListServ.watchListCreation(symbol)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }


    @Test
    @Order(2)
    fun updateWatchListTest() = testApplication {
        val result = watchListServ.watchListUpdation(id, symbol)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    @Order(4)
    fun deleteWatchListTest() = testApplication {
        val result = watchListServ.watchListDeletion(id)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

}