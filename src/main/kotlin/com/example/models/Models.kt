package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Symbol(
    val asset: String,
    val strike:Double,
    val lotSize:Int,
    val tickSize:Double,
    val streamSym:String,
    val instrument:String,
    val multiplier:Double,
    val displayName:String,
    val companyName:String,
    val expiry:String,
    val optionChain:Boolean,
    val symbolTag:String,
    val sector:String,
    val exchange:String,
    val isin:String,
    val baseSymbol:String)

@Serializable
data class Message(val message:String)

@Serializable
data class IdSymbol(val id:Int,val symbol:Symbol)

@Serializable
data class Id(val id:Int)

@Serializable
data class Watch(val id:Int,val is_delete:String,val created_at:String,val updated_at:String,val symbol:String)

