package com.example.routing

import com.example.endPoints.BASE_PATH
import com.example.endPoints.RESOURCE
import com.example.models.*
import com.example.repositories.WatchLists
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.watchRoutes(){
    route(BASE_PATH){

        post {
                val responce = call.receive<Symbol>()
                call.respond(status = HttpStatusCode.Created, WatchLists.createWatchList(responce))
        }

        get (RESOURCE){
            val c = WatchLists.viewRecentWatchList()
            call.respond(c)
        }

        get {
            val c = WatchLists.viewRecentWatchList()
            call.respond(c[0])
        }

        put {
                val responce = call.receive<IdSymbol>()
                call.respond(WatchLists.updateWatchList(responce.id, responce.symbol))
        }

        delete {
                val id = call.receive<Id>()
                call.respond(WatchLists.deleteWatchList(id.id))
        }
    }
}