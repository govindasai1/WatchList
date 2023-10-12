package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object RecentWatchList: IntIdTable() {
    val watchListId = integer("watchList_id")
}