package com.example.services

import com.example.dao.WatchListDao
import com.example.models.Message
import com.example.models.Symbol
import com.example.models.Watch

class WatchListServices(private val watchListDao:WatchListDao) {
    suspend fun watchListCreation(symbol:Symbol):Message{
        return watchListDao.createWatchList(symbol)
    }
    suspend fun watchListDeletion(id:Int):Message{
        return watchListDao.deleteWatchList(id)
    }
    suspend fun watchListUpdation(id:Int,symbol:Symbol):Message{
        return watchListDao.updateWatchList(id,symbol)
    }
    suspend fun allWatchList():List<Watch>{
        return watchListDao.viewRecentWatchList()
    }
    suspend fun recentWatchList():Watch{
        return watchListDao.viewRecentWatchList()[0]
    }
}