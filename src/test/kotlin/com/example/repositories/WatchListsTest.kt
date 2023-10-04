package com.example.repositories

import com.example.database.TestDatabaseFactory
import com.example.models.Message
import com.example.models.Symbol
import com.example.models.Watch
import com.example.tables.RecentWatchList
import com.example.tables.WatchList
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class WatchListsTest {
    private lateinit var database: Database
    private val symbol = Symbol("EQ",0.0,1,0.05,"21238_NSE","STK",1.0,"STK_AUBANK_EQ_NSE","AU SMALL FINANCE BANK LTD"
         ,"2024-12-31",false,"AUBANK","Banks","NSG","INE949L01017","AUBANK")
    private val id = 1
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
    fun createWatchListTest() = testApplication {
        val result = WatchLists.createWatchList(symbol)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun viewRecentTest() = testApplication {
        val result = WatchLists.viewRecentWatchList()
        if (result.equals(Watch)) assertTrue(true)
        else assertFalse(false)
    }


    @Test
    fun updateWatchListTest() = testApplication {
        val result = WatchLists.updateWatchList(id,symbol)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun deleteWatchListTest() = testApplication {
        val result = WatchLists.deleteWatchList(id)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }
}