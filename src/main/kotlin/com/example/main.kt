package com.example

import com.example.controllers.v1.usersController
import com.example.domain.User
import com.google.gson.Gson
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.content.TextContent
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.netty.Netty
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.routing
import org.jetbrains.ktor.transform.transform

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)

    transform.register<User> {
        TextContent(Gson().toJson(it), ContentType.Application.Json)
    }
    routing {
        get("/healthcheck") {
            call.respondText("ok")
        }
        usersController()
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080,
            reloadPackages = listOf("BlogAppKt"),
            module = Application::module
    ).start()
}

