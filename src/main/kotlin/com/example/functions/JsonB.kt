package com.example.functions

import com.example.tables.WatchList
import org.jetbrains.exposed.sql.Column


fun jsonb(s: String): Column<String> {
    return WatchList.registerColumn(s, JsonbColumnType())
}