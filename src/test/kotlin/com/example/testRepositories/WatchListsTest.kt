package com.example.testRepositories

import com.example.testDatabase.TestDatabaseFactory
import com.example.models.Message
import com.example.models.Watch
import com.example.repositories.WatchLists
import com.example.tables.RecentWatchList
import com.example.tables.WatchList
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
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import java.sql.Connection
import kotlin.test.assertFalse
import kotlin.test.assertTrue
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class WatchListsTest {
    private lateinit var database: Database

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
        val result = WatchLists.viewRecentWatchList()
        if (result.equals(Watch)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    @Order(1)
    fun createWatchListTest() = testApplication {
        val result = WatchLists.createWatchList(symbol)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }


    @Test
    @Order(2)
    fun updateWatchListTest() = testApplication {
        val result = WatchLists.updateWatchList(id, symbol)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    @Order(4)
    fun deleteWatchListTest() = testApplication {
        val result = WatchLists.deleteWatchList(id)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }
}