package com.example.routing

import com.example.endPoints.BASE_PATH
import com.example.endPoints.RESOURCE
import com.example.models.*
import com.example.repositories.WatchLists
import com.example.services.WatchListServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val watchListServ = WatchListServices(WatchLists)
fun Route.watchRoutes(){
    route(BASE_PATH){

        post {
                val responce = call.receive<Symbol>()
                call.respond(status = HttpStatusCode.Created, watchListServ.watchListCreation(responce))
        }

        get (RESOURCE){
            val c = watchListServ.allWatchList()
            call.respond(c)
        }

        get {
            val c = watchListServ.recentWatchList()
            call.respond(c)
        }

        put {
                val responce = call.receive<IdSymbol>()
                call.respond(watchListServ.watchListUpdation(responce.id, responce.symbol))
        }

        delete {
                val id = call.receive<Id>()
                call.respond(watchListServ.watchListDeletion(id.id))
        }
    }
}