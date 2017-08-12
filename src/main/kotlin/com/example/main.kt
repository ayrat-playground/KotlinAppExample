package com.example

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("My Example", ContentType.Text.Html)
        }
    }
}