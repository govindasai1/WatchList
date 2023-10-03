package com.example.tables

import com.example.functions.jsonb
import org.jetbrains.exposed.dao.id.IntIdTable

object WatchList: IntIdTable() {
    val is_delete = varchar("is_delete",50)
    val created_at = varchar("created_at",50)
    val updated_at = varchar("updated_at",50)
    val symbols = jsonb("Symbols")

}