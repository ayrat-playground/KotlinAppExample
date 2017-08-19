package com.example.controllers.v1

import com.example.main
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.http.HttpMethod
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.testing.handleRequest
import org.jetbrains.ktor.testing.withTestApplication
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class UsersControllerSpek : Spek({

    describe("post") {
        context("when params are valid") {
            it("creates user") {
                val name = "Paul"
                val surname = "McCartney"

                withTestApplication(Application::main) {
                    with(handleRequest(HttpMethod.Post, "/v1/users?name=$name&surname=$surname")) {
                        assertEquals(
                                HttpStatusCode.Created,
                                response.status()
                        )
                    }
                }
            }
        }

        context("when params are invalid") {
            it("creates user") {
                withTestApplication(Application::main) {
                    with(handleRequest(HttpMethod.Post, "/v1/users")) {
                        assertEquals(
                                HttpStatusCode(422, "Unprocessable Entity"),
                                response.status()
                        )
                    }
                }
            }
        }
    }
})
