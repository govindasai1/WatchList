package com.example.routing

import com.example.models.Id
import com.example.models.IdSymbol
import com.example.models.Symbol
import com.example.repositories.WatchLists
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.watchRoutes(){
    route("/watchlist"){

        post {
            val responce = call.receive<Symbol>()
            call.respond(status = HttpStatusCode.Created, WatchLists.createWatchList(responce))
        }

        get ("/all"){
            val c = WatchLists.viewRecentWatchList()
            call.respond(c)
        }

        get {
            val c = WatchLists.viewRecentWatchList()
            call.respond(c[0])
        }


        put {
//            val id = call.parameters["id"]?:return@put call.respond(Message("ID CANT BE EMPTY"))
            val responce = call.receive<IdSymbol>()
            call.respond(WatchLists.updateWatchList(responce.id,responce.symbol))
        }

        delete{
            val id = call.receive<Id>()
            call.respond(WatchLists.deleteWatchList(id.id))
        }
    }
}