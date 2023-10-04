package com.example.repositories

import com.example.dao.WatchListDao
import com.example.database.DatabaseFactory
import com.example.models.Message
import com.example.models.Symbol
import com.example.models.Watch
import com.example.tables.WatchList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


object WatchLists: WatchListDao {

    override suspend fun createWatchList(symbol: Symbol): Message {
            DatabaseFactory.dbQuery {
                WatchList.insert {
                    it[is_delete] = "No"
                    it[created_at] = createdDate()
                    it[updated_at] = "0"
                    it[symbols] = Json.encodeToString(symbol)
                }.resultedValues?.singleOrNull()
            }
            insertingRecent()
            return Message(" WATCHLIST CREATED SUCCESSFULLY ")
    }


    override suspend fun deleteWatchList(id: Int): Message {
        return if (checkingPresence(id)) {
            DatabaseFactory.dbQuery {
                WatchList.deleteWhere { WatchList.id.eq(id) }
            }
            insertingRecent()
            Message(" DELETED SUCCESSFULLY ")
        } else
            Message("ID NOT FOUND ")
    }


    override suspend fun updateWatchList(id: Int, symbol: Symbol) :Message {
        return if (checkingPresence(id)) {
            DatabaseFactory.dbQuery {
                WatchList.update({ WatchList.id.eq(id) }) {
                    it[updated_at] = createdDate()
                    it[symbols] = Json.encodeToString(symbol)
                }
            }
            Message("UPDATED SUCCESSFULLY")
        } else Message("ID NOT FOUND ")
    }


    override suspend fun viewRecentWatchList(): List<Watch> {
        return  DatabaseFactory.dbQuery {
            WatchList.selectAll().orderBy(WatchList.created_at, SortOrder.DESC).mapNotNull { rowToList(it) }
        }
    }

}
