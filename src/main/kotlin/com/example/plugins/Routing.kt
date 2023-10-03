package com.example.plugins

import com.example.routing.watchRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        watchRoutes()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
