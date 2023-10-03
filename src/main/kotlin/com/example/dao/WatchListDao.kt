package com.example.dao

import com.example.models.Message
import com.example.models.Symbol
import com.example.models.Watch

interface WatchListDao {

    suspend fun createWatchList(symbol: Symbol): Message
    suspend fun deleteWatchList(id:Int):Message
    suspend fun updateWatchList(id:Int,symbol: Symbol):Message
    suspend fun viewRecentWatchList():List<Watch>
}