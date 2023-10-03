package com.example.repositories

import com.example.database.DatabaseFactory
import com.example.models.Watch
import com.example.tables.RecentWatchList
import com.example.tables.WatchList
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime


fun createdDate():String{
    val current = LocalDateTime.now()
    return current.toString()
}

suspend fun checkingPresence(id:Int):Boolean{
    val result = DatabaseFactory.dbQuery { WatchList.select { (WatchList.id.eq(id)) }.map {it[WatchList.id].value}.singleOrNull() }
    return result!=null
}
fun rowToList(row: ResultRow): Watch {
    return Watch(row[WatchList.id].value,row[WatchList.is_delete],row[WatchList.created_at],row[WatchList.updated_at],row[WatchList.symbols])
}
suspend fun insertingRecent(){
    val watchList = WatchLists.viewRecentWatchList()
    DatabaseFactory.dbQuery {
        RecentWatchList.deleteAll()
    }
    DatabaseFactory.dbQuery {
        RecentWatchList.insert {
            it[watchListId] = watchList[0].id
        }
    }
}