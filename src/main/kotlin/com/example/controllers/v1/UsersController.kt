package com.example.controllers.v1

import com.example.repositories.UsersRepo
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.post
import org.jetbrains.ktor.routing.route

fun Route.usersController() {
    var usersRepo = UsersRepo()

    route("/v1/users") {
        post("") {
            val name = call.parameters["name"]
            val surname = call.parameters["surname"]

            if (name == null || surname == null) {
                call.respond(HttpStatusCode(422, "Unprocessable Entity"))
            } else {
                val userId = usersRepo.create(name, surname)
                val user = usersRepo.get(userId)

                call.response.status(HttpStatusCode.Created)
                call.respond(user)
            }
        }
    }
}

